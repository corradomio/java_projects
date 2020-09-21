package jext.jfr;

import jext.logging.Logger;
import org.openjdk.jmc.common.item.IItem;
import org.openjdk.jmc.common.item.IItemIterable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ThreadDumps {

    private static class ThreadDump {

        private String name;
        private List<String> callStack;

        public ThreadDump(String name, List<String> callStack) {
            this.name = name;
            this.callStack = callStack;

            // reverse the call stack to have the entry point
            // at the beginning of the list
            Collections.reverse(this.callStack);
        }

        /**
         * Name of the thread
         */
        public String getName() {
            return this.name;
        }

        /**
         * Thread call stack.
         * Note: the stack is ""reverse""
         */
        public List<String> getCallStack() {
            return callStack;
        }

        /**
         * First entry point in one of teh specified namespaces
         */
        public Optional<String> getEntryPoint(List<String> namespaces) {
            for (String call : callStack)
                for (String namespace : namespaces)
                    if (call.startsWith(namespace))
                        return Optional.of(call);
            return Optional.empty();
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private List<IItemIterable> threadDumps;
    private List<ThreadDump> dumps = new ArrayList<>();
    private static Logger logger = Logger.getLogger(ThreadDumps.class);

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public ThreadDumps(List<IItemIterable> threadDumps) {
        this.threadDumps = threadDumps;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public int size() {
        int[] size = new int[1];
        threadDumps.forEach(tdump -> {
            size[0] += tdump.getItemCount();
        });
        return size[0];
    }

    public List<ThreadDump> getDumps() {
        analyze();
        return dumps;
    }

    public Set<String> getEntryPoints(List<String> namespaces) {
        Set<String> entryPoints = new HashSet<>();

        getDumps().forEach(dump -> {
            dump.getEntryPoint(namespaces)
                .ifPresent(entryPoints::add);
        });

        return entryPoints;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private void analyze() {
        if (!dumps.isEmpty())
            return;

        for (IItemIterable threadDump : threadDumps) {
            for(IItem item : threadDump) {
                String dump = item.toString();
                parseDump(dump);
            }
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private enum ParseState {
        TIMESTAMP,
        COMMENT,
        EMPTY_LINE,
        THREAD_HEADER,
        THREAD_BODY,
        THREAD_END,
        DONE
    }

    private static final String NULL = "null";

    private ParseState state;
    private String threadName;
    private List<String> callStack;

    private void parseDump(String dump) {
        // 1) split 'dump' in lines
        String[] lines = dump.split("\\n");

        // 2) initial state of the parser
        state = ParseState.TIMESTAMP;

        // 3) scan the lines
        for (int i = 0; i<lines.length-1 && state != ParseState.DONE; ++i) {
            switch (state) {
                case TIMESTAMP:     parseTimestamp(lines[i], lines[i+1]); break;
                case COMMENT:       parseComment(lines[i], lines[i+1]); break;
                case EMPTY_LINE:    parseEmptyLine(lines[i], lines[i+1]); break;
                case THREAD_HEADER: parseThreadHeader(lines[i], lines[i+1]); break;
                case THREAD_BODY:   parseThreadBody(lines[i], lines[i+1]); break;
                case THREAD_END:    parseThreadEnd(lines[i], lines[i+1]); break;
                case DONE:          break;
            }
        }
    }

    private void parseTimestamp(String line, String nline) {
        if (nline.isEmpty())
            state = ParseState.EMPTY_LINE;
        else
            state = ParseState.COMMENT;
    }

    private void parseComment(String line, String nline) {
        if (nline.isEmpty())
            state = ParseState.EMPTY_LINE;
        else
            state = ParseState.THREAD_HEADER;
    }

    private void parseEmptyLine(String line, String nline) {
        if (NULL.equals(nline.trim()))
            state = ParseState.DONE;
        else if (!nline.isEmpty())
            state = ParseState.THREAD_HEADER;
    }

    private void parseThreadHeader(String line, String nline) {
        //
        // "RMI TCP Connection(91)-127.0.0.1" #265 daemon prio=5 os_prio=0 tid=0x0000000034897800 nid=0x3acc runnable [0x000000004d0cd000]
        // "pool-3-thread-7" #98 prio=5 os_prio=0 tid=0x000000003100b800 nid=0x3ab8 waiting on condition [0x000000004307f000]
        // "ajp-nio-8009-exec-3" #48 daemon prio=5 os_prio=0 tid=0x000000002d06e800 nid=0x3150 waiting on condition [0x000000003353e000]
        // "main" #1 prio=5 os_prio=0 tid=0x0000000001d2c000 nid=0x1f50 runnable [0x00000000023ae000]
        // "VM Thread" os_prio=2 tid=0x0000000026b63000 nid=0x35a4 runnable
        // "GC task thread#0 (ParallelGC)" os_prio=0 tid=0x0000000001d44800 nid=0x1278 runnable
        // JNI global references: 30510
        //  null
        //
        int end = line.indexOf("\"", 1);
        if (end != -1) {
            this.threadName = line.substring(1, end);
        } else {
            this.threadName = line.trim();
        }

        this.callStack = new ArrayList<>();

        if (nline.isEmpty())
            state = ParseState.THREAD_END;
        else
            state = ParseState.THREAD_BODY;
    }

    private void parseThreadBody(String line, String nline) {
        //
        // java.lang.Thread.State: RUNNABLE
        // java.lang.Thread.State: WAITING (on object monitor)
        // at java.lang.Object.wait(Native Method)
        // at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:144)
        // - locked <0x00000005c0e64de8> (a java.lang.ref.ReferenceQueue$Lock)
        // - parking to wait for  <0x00000005c25d3618> (a java.util.concurrent.locks....)
        // at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:165)
        //
        line = line.trim();

        if (line.startsWith("java.")) {

        }
        else if (line.startsWith("at ")) {
            String methodCall;
            int pos = line.indexOf(" ") + 1;
            int end = line.indexOf("(");

            if (end != -1)
                methodCall = line.substring(pos, end);
            else
                methodCall = line.substring(pos);

            callStack.add(methodCall);
        }
        else if (line.startsWith("- ")) {
            // - locked
            // - parking to wait for
        }
        else {
            logger.warnf("Unparsed '%s'", line);
        }

        if (nline.isEmpty())
            state = ParseState.THREAD_END;
    }

    private void parseThreadEnd(String line, String nline) {

        this.dumps.add(new ThreadDump(this.threadName, this.callStack));
        this.callStack = new ArrayList<>();

        if (nline.isEmpty())
            state = ParseState.EMPTY_LINE;
        else
            state = ParseState.THREAD_HEADER;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}


/*
Type(jdk.ThreadDump) 9/15/20 4:21:13 PM 2020-09-15 16:21:13
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.241-b07 mixed mode):

"RMI TCP Connection(91)-127.0.0.1" #265 daemon prio=5 os_prio=0 tid=0x0000000034897800 nid=0x3acc runnable [0x000000004d0cd000]
   java.lang.Thread.State: RUNNABLE
	at java.net.SocketInputStream.socketRead0(Native Method)
	at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
	at java.net.SocketInputStream.read(SocketInputStream.java:171)
	at java.net.SocketInputStream.read(SocketInputStream.java:141)
	at java.io.BufferedInputStream.fill(BufferedInputStream.java:246)
	at java.io.BufferedInputStream.read(BufferedInputStream.java:265)
	- locked <0x000000076a8115c0> (a java.io.BufferedInputStream)
	at java.io.FilterInputStream.read(FilterInputStream.java:83)
	at sun.rmi.transport.tcp.TCPTransport.handleMessages(TCPTransport.java:555)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run0(TCPTransport.java:834)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.lambda$run$0(TCPTransport.java:688)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler$$Lambda$5/1504689369.run(Unknown Source)
	at java.security.AccessController.doPrivileged(Native Method)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run(TCPTransport.java:687)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"RMI TCP Connection(90)-127.0.0.1" #264 daemon prio=5 os_prio=0 tid=0x0000000034896000 nid=0x2bc8 in Object.wait() [0x000000003a80c000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at com.sun.jmx.remote.internal.ArrayNotificationBuffer.fetchNotifications(ArrayNotificationBuffer.java:449)
	- locked <0x00000005c23b2840> (a com.sun.jmx.remote.internal.ArrayNotificationBuffer)
	at com.sun.jmx.remote.internal.ArrayNotificationBuffer$ShareBuffer.fetchNotifications(ArrayNotificationBuffer.java:227)
	at com.sun.jmx.remote.internal.ServerNotifForwarder.fetchNotifs(ServerNotifForwarder.java:274)
	at javax.management.remote.rmi.RMIConnectionImpl$4.run(RMIConnectionImpl.java:1270)
	at javax.management.remote.rmi.RMIConnectionImpl$4.run(RMIConnectionImpl.java:1268)
	at javax.management.remote.rmi.RMIConnectionImpl.fetchNotifications(RMIConnectionImpl.java:1274)
	at sun.reflect.GeneratedMethodAccessor235.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at sun.rmi.server.UnicastServerRef.dispatch(UnicastServerRef.java:357)
	at sun.rmi.transport.Transport$1.run(Transport.java:200)
	at sun.rmi.transport.Transport$1.run(Transport.java:197)
	at java.security.AccessController.doPrivileged(Native Method)
	at sun.rmi.transport.Transport.serviceCall(Transport.java:196)
	at sun.rmi.transport.tcp.TCPTransport.handleMessages(TCPTransport.java:573)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run0(TCPTransport.java:834)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.lambda$run$0(TCPTransport.java:688)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler$$Lambda$5/1504689369.run(Unknown Source)
	at java.security.AccessController.doPrivileged(Native Method)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run(TCPTransport.java:687)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"JMX server connection timeout 263" #263 daemon prio=5 os_prio=0 tid=0x0000000034895800 nid=0x47bc in Object.wait() [0x000000004c7af000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at java.lang.Object.wait(Object.java:502)
	at com.sun.jmx.remote.internal.ServerCommunicatorAdmin$Timeout.run(ServerCommunicatorAdmin.java:150)
	- locked <0x0000000769a40f60> (a [I)
	at java.lang.Thread.run(Thread.java:748)

"RMI TCP Connection(89)-127.0.0.1" #262 daemon prio=5 os_prio=0 tid=0x000000002d2ff800 nid=0x5300 runnable [0x000000003028d000]
   java.lang.Thread.State: RUNNABLE
	at java.net.SocketInputStream.socketRead0(Native Method)
	at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
	at java.net.SocketInputStream.read(SocketInputStream.java:171)
	at java.net.SocketInputStream.read(SocketInputStream.java:141)
	at java.io.BufferedInputStream.fill(BufferedInputStream.java:246)
	at java.io.BufferedInputStream.read(BufferedInputStream.java:265)
	- locked <0x0000000769a37510> (a java.io.BufferedInputStream)
	at java.io.FilterInputStream.read(FilterInputStream.java:83)
	at sun.rmi.transport.tcp.TCPTransport.handleMessages(TCPTransport.java:555)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run0(TCPTransport.java:834)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.lambda$run$0(TCPTransport.java:688)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler$$Lambda$5/1504689369.run(Unknown Source)
	at java.security.AccessController.doPrivileged(Native Method)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run(TCPTransport.java:687)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"MessageBroker-8" #234 prio=5 os_prio=0 tid=0x0000000034894800 nid=0x46b8 waiting on condition [0x000000004c96e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25d3618> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1088)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"MessageBroker-8" #233 prio=5 os_prio=0 tid=0x0000000034894000 nid=0x4b70 waiting on condition [0x000000004cf4e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25d2550> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1088)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"MessageBroker-7" #224 prio=5 os_prio=0 tid=0x000000002ee12800 nid=0x4a9c waiting on condition [0x0000000035a6f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25d3618> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1088)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"MessageBroker-7" #223 prio=5 os_prio=0 tid=0x000000002d2f8800 nid=0x45c8 waiting on condition [0x000000004cc2f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25d2550> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1088)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"MessageBroker-6" #215 prio=5 os_prio=0 tid=0x000000002ee11000 nid=0x3d3c waiting on condition [0x000000004ca6e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25d3618> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1088)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"MessageBroker-6" #214 prio=5 os_prio=0 tid=0x000000002ee10000 nid=0x4120 waiting on condition [0x000000004ad8f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25d2550> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1088)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"MessageBroker-5" #206 prio=5 os_prio=0 tid=0x000000002ee0f800 nid=0x45c4 waiting on condition [0x000000003041e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25d3618> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1088)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"MessageBroker-5" #205 prio=5 os_prio=0 tid=0x000000002ee0e800 nid=0x355c waiting on condition [0x000000004b3de000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25d2550> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1088)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"MessageBroker-4" #198 prio=5 os_prio=0 tid=0x000000002ee0e000 nid=0x1ef0 waiting on condition [0x000000004b02e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25d3618> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1088)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"MessageBroker-4" #197 prio=5 os_prio=0 tid=0x000000002d300000 nid=0x810 waiting on condition [0x000000004296e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25d2550> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1088)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"MessageBroker-3" #188 prio=5 os_prio=0 tid=0x0000000037a61800 nid=0x40f0 waiting on condition [0x000000004aeff000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25d3618> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1088)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"MessageBroker-3" #187 prio=5 os_prio=0 tid=0x0000000037a64000 nid=0x4328 waiting on condition [0x000000004aa0e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25d2550> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1088)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"pool-2-thread-2" #100 prio=5 os_prio=0 tid=0x0000000037a63000 nid=0x3ea8 waiting on condition [0x000000004a60f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c241d238> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"pool-3-thread-7" #98 prio=5 os_prio=0 tid=0x000000003100b800 nid=0x3ab8 waiting on condition [0x000000004307f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c3069ad0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"pool-3-thread-6" #97 prio=5 os_prio=0 tid=0x000000003100b000 nid=0x2fe0 waiting on condition [0x0000000042e6f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c3069ad0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"pool-3-thread-5" #96 prio=5 os_prio=0 tid=0x000000003100a000 nid=0x2b30 waiting on condition [0x00000000426ce000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c3069ad0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"pool-3-thread-4" #95 prio=5 os_prio=0 tid=0x0000000031009800 nid=0x30f8 waiting on condition [0x0000000042d0f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c3069ad0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"pool-3-thread-3" #94 prio=5 os_prio=0 tid=0x0000000031008800 nid=0x3d98 waiting on condition [0x0000000042bae000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c3069ad0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"pool-3-thread-2" #93 prio=5 os_prio=0 tid=0x0000000031008000 nid=0x2954 waiting on condition [0x0000000042a6e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c3069ad0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"pool-3-thread-1" #92 prio=5 os_prio=0 tid=0x0000000031007000 nid=0x3d90 waiting on condition [0x000000004286f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c3069ad0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"pool-2-thread-1" #91 prio=5 os_prio=0 tid=0x000000002d2f9800 nid=0x2784 waiting on condition [0x0000000041f7f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c241d238> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-16" #90 prio=10 os_prio=2 tid=0x0000000031006800 nid=0x21c8 runnable [0x000000004219f000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c253d768> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c25654d0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c253d698> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-15" #89 prio=10 os_prio=2 tid=0x0000000031005800 nid=0x180c runnable [0x000000003ef9f000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c24d3d28> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c24d3d80> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c24d3c58> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-14" #88 prio=10 os_prio=2 tid=0x0000000031005000 nid=0x34f4 runnable [0x000000004208f000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c24e3a48> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c24e3aa0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c24e3978> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-13" #87 prio=10 os_prio=2 tid=0x000000003cb94000 nid=0x3468 runnable [0x0000000041e3e000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c24f6650> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c24f66a8> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c24f6580> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-12" #86 prio=10 os_prio=2 tid=0x000000003cb93800 nid=0x2d30 runnable [0x0000000041cff000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c2502ff0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c27a6690> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2502f20> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-11" #85 prio=10 os_prio=2 tid=0x000000003cb92800 nid=0x1b84 runnable [0x0000000041bef000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c27af558> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c27af5b0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c27af488> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-10" #84 prio=10 os_prio=2 tid=0x000000003cb92000 nid=0x1af0 runnable [0x000000003f7bf000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c281b1e8> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c281b240> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c281b118> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-9" #83 prio=10 os_prio=2 tid=0x000000003cb91000 nid=0x9cc runnable [0x000000003f5ef000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c282d098> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c282d0f0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c282cfc8> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-8" #82 prio=10 os_prio=2 tid=0x000000003c9b8000 nid=0x174 runnable [0x000000003ea1e000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c283a348> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c283a3a0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c283a278> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-7" #81 prio=10 os_prio=2 tid=0x000000003c9b7800 nid=0x3e44 runnable [0x000000003f4be000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c257a5d0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c257a628> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c257a500> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-6" #80 prio=10 os_prio=2 tid=0x000000003c9b6800 nid=0x3b54 runnable [0x000000003ee7e000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c271f830> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2720908> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c271f750> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-5" #79 prio=10 os_prio=2 tid=0x000000003c9b6000 nid=0x2354 runnable [0x000000003ed3e000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c2621790> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c26217e8> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c26216c0> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-4" #78 prio=10 os_prio=2 tid=0x000000003c9b5000 nid=0x3834 runnable [0x000000003eb5e000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c25d5e70> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c25fc5a0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c25d5d90> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-3" #77 prio=10 os_prio=2 tid=0x000000003c9b4800 nid=0x3090 runnable [0x000000003bb8f000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c259a378> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c259a3d0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c259a2a8> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-2" #76 prio=10 os_prio=2 tid=0x000000003c9b3800 nid=0x32e8 runnable [0x000000003f0ef000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c25fcc68> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2703e08> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c25fcb88> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-1" #75 prio=10 os_prio=2 tid=0x000000003c9b3000 nid=0x2be4 runnable [0x00000000340be000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c2733890> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c27338e8> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c27337c0> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"MessageBroker-2" #71 prio=5 os_prio=0 tid=0x000000002d2fb800 nid=0x3898 waiting on condition [0x000000003457f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25d3618> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1088)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"MessageBroker-2" #70 prio=5 os_prio=0 tid=0x000000002d061000 nid=0x3bf8 waiting on condition [0x0000000033d2e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25d2550> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1088)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"MessageBroker-1" #68 prio=5 os_prio=0 tid=0x000000002d2fe800 nid=0x1acc waiting on condition [0x000000003702f000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25d3618> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:215)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2078)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1093)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"MessageBroker-1" #67 prio=5 os_prio=0 tid=0x000000002d2fe000 nid=0x379c waiting on condition [0x0000000036e0e000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25d2550> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:215)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2078)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1093)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"DelayedTimer" #66 daemon prio=5 os_prio=0 tid=0x000000002d2fd000 nid=0x3cf4 waiting on condition [0x00000000342ef000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
	at java.lang.Thread.sleep(Native Method)
	at jext.util.DelayedTimer.run(DelayedTimer.java:124)

"FileWatchdog" #64 daemon prio=5 os_prio=0 tid=0x000000002d2fa000 nid=0x2970 waiting on condition [0x0000000036f0f000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
	at java.lang.Thread.sleep(Native Method)
	at org.apache.log4j.helpers.FileWatchdog.run(FileWatchdog.java:104)

"ajp-nio-8009-AsyncTimeout" #59 daemon prio=5 os_prio=0 tid=0x000000002d2f8000 nid=0x3f6c waiting on condition [0x00000000343ff000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
	at java.lang.Thread.sleep(Native Method)
	at org.apache.coyote.AbstractProtocol$AsyncTimeout.run(AbstractProtocol.java:1149)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-Acceptor-0" #58 daemon prio=5 os_prio=0 tid=0x000000002d2f7000 nid=0x26fc runnable [0x00000000341df000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.ServerSocketChannelImpl.accept0(Native Method)
	at sun.nio.ch.ServerSocketChannelImpl.accept(ServerSocketChannelImpl.java:422)
	at sun.nio.ch.ServerSocketChannelImpl.accept(ServerSocketChannelImpl.java:250)
	- locked <0x00000005c11796a0> (a java.lang.Object)
	at org.apache.tomcat.util.net.NioEndpoint$Acceptor.run(NioEndpoint.java:482)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-ClientPoller-1" #57 daemon prio=5 os_prio=0 tid=0x000000002d2f6800 nid=0x31bc runnable [0x0000000033f9e000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c117fed0> (a sun.nio.ch.Util$3)
	- locked <0x00000005c117fec0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c117fd50> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioEndpoint$Poller.run(NioEndpoint.java:825)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-ClientPoller-0" #56 daemon prio=5 os_prio=0 tid=0x000000002d2f5800 nid=0x3544 runnable [0x0000000033e5e000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c1189850> (a sun.nio.ch.Util$3)
	- locked <0x00000005c1189840> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c1181748> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioEndpoint$Poller.run(NioEndpoint.java:825)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-exec-10" #55 daemon prio=5 os_prio=0 tid=0x000000002d2f5000 nid=0x31e4 waiting on condition [0x0000000032e9e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c11691b8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-exec-9" #54 daemon prio=5 os_prio=0 tid=0x000000002d2f4000 nid=0x2b44 waiting on condition [0x0000000033c2f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c11691b8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-exec-8" #53 daemon prio=5 os_prio=0 tid=0x000000002d2f3800 nid=0x2e60 waiting on condition [0x0000000033a7e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c11691b8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-exec-7" #52 daemon prio=5 os_prio=0 tid=0x000000002d2f2800 nid=0xe34 waiting on condition [0x00000000332bf000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c11691b8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-exec-6" #51 daemon prio=5 os_prio=0 tid=0x000000002d2f2000 nid=0x31ac waiting on condition [0x000000003389e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c11691b8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-exec-5" #50 daemon prio=5 os_prio=0 tid=0x000000002d2f1000 nid=0x39a4 waiting on condition [0x000000003377e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c11691b8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-exec-4" #49 daemon prio=5 os_prio=0 tid=0x000000002d06f000 nid=0x2884 waiting on condition [0x000000003364e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c11691b8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-exec-3" #48 daemon prio=5 os_prio=0 tid=0x000000002d06e800 nid=0x3150 waiting on condition [0x000000003353e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c11691b8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-exec-2" #47 daemon prio=5 os_prio=0 tid=0x000000002d06d800 nid=0x3f90 waiting on condition [0x00000000333fe000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c11691b8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-exec-1" #46 daemon prio=5 os_prio=0 tid=0x000000002d06d000 nid=0x3cd8 waiting on condition [0x00000000331ae000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c11691b8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-AsyncTimeout" #45 daemon prio=5 os_prio=0 tid=0x000000002d06c000 nid=0x28d8 waiting on condition [0x0000000032fff000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
	at java.lang.Thread.sleep(Native Method)
	at org.apache.coyote.AbstractProtocol$AsyncTimeout.run(AbstractProtocol.java:1149)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-Acceptor-0" #44 daemon prio=5 os_prio=0 tid=0x000000002d06b800 nid=0x2a00 runnable [0x0000000032d9f000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.ServerSocketChannelImpl.accept0(Native Method)
	at sun.nio.ch.ServerSocketChannelImpl.accept(ServerSocketChannelImpl.java:422)
	at sun.nio.ch.ServerSocketChannelImpl.accept(ServerSocketChannelImpl.java:250)
	- locked <0x00000005c11b4e40> (a java.lang.Object)
	at org.apache.tomcat.util.net.NioEndpoint$Acceptor.run(NioEndpoint.java:482)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-ClientPoller-1" #43 daemon prio=5 os_prio=0 tid=0x000000002d06a800 nid=0x33dc runnable [0x0000000032c2f000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c11bb668> (a sun.nio.ch.Util$3)
	- locked <0x00000005c11bb658> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c11bb4e8> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioEndpoint$Poller.run(NioEndpoint.java:825)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-ClientPoller-0" #42 daemon prio=5 os_prio=0 tid=0x000000002d06a000 nid=0xf08 runnable [0x0000000032b1f000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c11bd060> (a sun.nio.ch.Util$3)
	- locked <0x00000005c11bd050> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c11bcee0> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioEndpoint$Poller.run(NioEndpoint.java:825)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-exec-10" #41 daemon prio=5 os_prio=0 tid=0x000000002d069000 nid=0x24c4 waiting on condition [0x000000003284e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c119cb18> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-exec-9" #40 daemon prio=5 os_prio=0 tid=0x000000002d068800 nid=0x1f88 waiting on condition [0x000000003294e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c119cb18> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-exec-8" #39 daemon prio=5 os_prio=0 tid=0x000000002d067800 nid=0x31dc waiting on condition [0x00000000326ae000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c119cb18> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-exec-7" #38 daemon prio=5 os_prio=0 tid=0x000000002d067000 nid=0x20f4 waiting on condition [0x000000002e34e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c119cb18> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-exec-6" #37 daemon prio=5 os_prio=0 tid=0x000000002d066000 nid=0x3c10 waiting on condition [0x0000000031d9f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c119cb18> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-exec-5" #36 daemon prio=5 os_prio=0 tid=0x000000002d065800 nid=0x2e50 waiting on condition [0x00000000324ce000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c119cb18> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-exec-4" #35 daemon prio=5 os_prio=0 tid=0x000000002d064800 nid=0x3b28 waiting on condition [0x000000003215f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c119cb18> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-exec-3" #34 daemon prio=5 os_prio=0 tid=0x000000002d064000 nid=0x3b90 waiting on condition [0x000000003236e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c119cb18> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-exec-2" #33 daemon prio=5 os_prio=0 tid=0x000000002d063000 nid=0x3174 waiting on condition [0x000000003226f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c119cb18> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-exec-1" #32 daemon prio=5 os_prio=0 tid=0x000000002d062800 nid=0x3c84 waiting on condition [0x000000003204f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c119cb18> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:103)
	at org.apache.tomcat.util.threads.TaskQueue.take(TaskQueue.java:31)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)

"ContainerBackgroundProcessor[StandardEngine[Catalina]]" #31 daemon prio=5 os_prio=0 tid=0x000000002d061800 nid=0xf20 waiting on condition [0x0000000031eaf000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
	at java.lang.Thread.sleep(Native Method)
	at org.apache.catalina.core.ContainerBase$ContainerBackgroundProcessor.run(ContainerBase.java:1359)
	at java.lang.Thread.run(Thread.java:748)

"NioBlockingSelector.BlockPoller-2" #29 daemon prio=5 os_prio=0 tid=0x000000002d060000 nid=0x2778 runnable [0x0000000030a8f000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c1177a90> (a sun.nio.ch.Util$3)
	- locked <0x00000005c1177a80> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c1177910> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioBlockingSelector$BlockPoller.run(NioBlockingSelector.java:298)

"NioBlockingSelector.BlockPoller-1" #28 daemon prio=5 os_prio=0 tid=0x000000002d4dd800 nid=0x36ec runnable [0x000000002e5ff000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c11af2a8> (a sun.nio.ch.Util$3)
	- locked <0x00000005c11af298> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c11af138> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioBlockingSelector$BlockPoller.run(NioBlockingSelector.java:298)

"GC Daemon" #27 daemon prio=2 os_prio=-2 tid=0x000000002f885000 nid=0x918 in Object.wait() [0x000000003073f000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x00000005c105ed28> (a sun.misc.GC$LatencyLock)
	at sun.misc.GC$Daemon.run(GC.java:117)
	- locked <0x00000005c105ed28> (a sun.misc.GC$LatencyLock)

"RMI Scheduler(0)" #25 daemon prio=5 os_prio=0 tid=0x000000002d07d800 nid=0x32c4 waiting on condition [0x00000000305be000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c15b86b8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:215)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2078)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1093)
	at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"RMI TCP Accept-0" #22 daemon prio=5 os_prio=0 tid=0x000000002d5dc000 nid=0x3b84 runnable [0x000000002fc7e000]
   java.lang.Thread.State: RUNNABLE
	at java.net.DualStackPlainSocketImpl.accept0(Native Method)
	at java.net.DualStackPlainSocketImpl.socketAccept(DualStackPlainSocketImpl.java:131)
	at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:409)
	at java.net.PlainSocketImpl.accept(PlainSocketImpl.java:199)
	- locked <0x00000005c10a32e0> (a java.net.SocksSocketImpl)
	at java.net.ServerSocket.implAccept(ServerSocket.java:545)
	at java.net.ServerSocket.accept(ServerSocket.java:513)
	at sun.management.jmxremote.LocalRMIServerSocketFactory$1.accept(LocalRMIServerSocketFactory.java:52)
	at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.executeAcceptLoop(TCPTransport.java:405)
	at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.run(TCPTransport.java:377)
	at java.lang.Thread.run(Thread.java:748)

"RMI TCP Accept-1099" #21 daemon prio=5 os_prio=0 tid=0x000000002d511800 nid=0x3dd0 runnable [0x000000002e7de000]
   java.lang.Thread.State: RUNNABLE
	at java.net.DualStackPlainSocketImpl.accept0(Native Method)
	at java.net.DualStackPlainSocketImpl.socketAccept(DualStackPlainSocketImpl.java:131)
	at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:409)
	at java.net.PlainSocketImpl.accept(PlainSocketImpl.java:199)
	- locked <0x00000005c109e3c8> (a java.net.SocksSocketImpl)
	at java.net.ServerSocket.implAccept(ServerSocket.java:545)
	at java.net.ServerSocket.accept(ServerSocket.java:513)
	at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.executeAcceptLoop(TCPTransport.java:405)
	at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.run(TCPTransport.java:377)
	at java.lang.Thread.run(Thread.java:748)

"RMI TCP Accept-0" #20 daemon prio=5 os_prio=0 tid=0x000000002d43c000 nid=0x3034 runnable [0x000000002df3f000]
   java.lang.Thread.State: RUNNABLE
	at java.net.DualStackPlainSocketImpl.accept0(Native Method)
	at java.net.DualStackPlainSocketImpl.socketAccept(DualStackPlainSocketImpl.java:131)
	at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:409)
	at java.net.PlainSocketImpl.accept(PlainSocketImpl.java:199)
	- locked <0x00000005c10ac308> (a java.net.SocksSocketImpl)
	at java.net.ServerSocket.implAccept(ServerSocket.java:545)
	at java.net.ServerSocket.accept(ServerSocket.java:513)
	at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.executeAcceptLoop(TCPTransport.java:405)
	at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.run(TCPTransport.java:377)
	at java.lang.Thread.run(Thread.java:748)

"AsyncFileHandlerWriter-140799417" #19 daemon prio=5 os_prio=0 tid=0x000000002cc8e000 nid=0x17c8 waiting on condition [0x000000002e23e000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c157bf60> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:215)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2078)
	at java.util.concurrent.LinkedBlockingDeque.pollFirst(LinkedBlockingDeque.java:522)
	at java.util.concurrent.LinkedBlockingDeque.poll(LinkedBlockingDeque.java:684)
	at org.apache.juli.AsyncFileHandler$LoggerThread.run(AsyncFileHandler.java:160)

"Service Thread" #16 daemon prio=9 os_prio=0 tid=0x000000002ca9b800 nid=0x3424 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C1 CompilerThread3" #15 daemon prio=9 os_prio=2 tid=0x000000002c9be800 nid=0x39cc waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread2" #14 daemon prio=9 os_prio=2 tid=0x000000002c9be000 nid=0x318 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread1" #13 daemon prio=9 os_prio=2 tid=0x000000002ca0e000 nid=0x3aac waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread0" #12 daemon prio=9 os_prio=2 tid=0x000000002ca0b000 nid=0x2350 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"VM JFR Buffer Thread" #11 daemon prio=5 os_prio=0 tid=0x000000002c9fa800 nid=0x3f5c waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"JFR request timer" #9 daemon prio=5 os_prio=0 tid=0x0000000028704000 nid=0x3130 in Object.wait() [0x000000002c8cf000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at java.util.TimerThread.mainLoop(Timer.java:552)
	- locked <0x00000005c1168278> (a java.util.TaskQueue)
	at java.util.TimerThread.run(Timer.java:505)

"JDWP Command Reader" #8 daemon prio=10 os_prio=0 tid=0x0000000026eea000 nid=0x2a84 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"JDWP Event Helper Thread" #7 daemon prio=10 os_prio=0 tid=0x0000000028690800 nid=0x336c runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"JDWP Transport Listener: dt_socket" #6 daemon prio=10 os_prio=0 tid=0x0000000028683800 nid=0x3488 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Attach Listener" #5 daemon prio=5 os_prio=2 tid=0x0000000026ed9000 nid=0x37f0 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Signal Dispatcher" #4 daemon prio=9 os_prio=2 tid=0x0000000026ed8000 nid=0x2e14 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Finalizer" #3 daemon prio=8 os_prio=1 tid=0x0000000025b07800 nid=0xfb4 in Object.wait() [0x0000000027c2f000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:144)
	- locked <0x00000005c0e64de8> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:165)
	at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:216)

"Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x0000000025b00000 nid=0x3fc8 in Object.wait() [0x0000000027f6f000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at java.lang.Object.wait(Object.java:502)
	at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
	- locked <0x00000005c15ff088> (a java.lang.ref.Reference$Lock)
	at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

"main" #1 prio=5 os_prio=0 tid=0x0000000001d2c000 nid=0x1f50 runnable [0x00000000023ae000]
   java.lang.Thread.State: RUNNABLE
	at java.net.DualStackPlainSocketImpl.accept0(Native Method)
	at java.net.DualStackPlainSocketImpl.socketAccept(DualStackPlainSocketImpl.java:131)
	at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:409)
	at java.net.PlainSocketImpl.accept(PlainSocketImpl.java:199)
	- locked <0x00000005c119bae8> (a java.net.SocksSocketImpl)
	at java.net.ServerSocket.implAccept(ServerSocket.java:545)
	at java.net.ServerSocket.accept(ServerSocket.java:513)
	at org.apache.catalina.core.StandardServer.await(StandardServer.java:466)
	at org.apache.catalina.startup.Catalina.await(Catalina.java:775)
	at org.apache.catalina.startup.Catalina.start(Catalina.java:721)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.apache.catalina.startup.Bootstrap.start(Bootstrap.java:353)
	at org.apache.catalina.startup.Bootstrap.main(Bootstrap.java:493)

"VM Thread" os_prio=2 tid=0x0000000026b63000 nid=0x35a4 runnable

"GC task thread#0 (ParallelGC)" os_prio=0 tid=0x0000000001d44800 nid=0x1278 runnable

"GC task thread#1 (ParallelGC)" os_prio=0 tid=0x0000000001d46000 nid=0x165c runnable

"GC task thread#2 (ParallelGC)" os_prio=0 tid=0x0000000001d47800 nid=0x29b0 runnable

"GC task thread#3 (ParallelGC)" os_prio=0 tid=0x0000000001d49000 nid=0x332c runnable

"GC task thread#4 (ParallelGC)" os_prio=0 tid=0x0000000001d4c800 nid=0x10a4 runnable

"GC task thread#5 (ParallelGC)" os_prio=0 tid=0x0000000001d4e000 nid=0x3e24 runnable

"GC task thread#6 (ParallelGC)" os_prio=0 tid=0x0000000001d4f000 nid=0x2848 runnable

"GC task thread#7 (ParallelGC)" os_prio=0 tid=0x0000000001d50000 nid=0x3d4c runnable

"VM Periodic Task Thread" os_prio=2 tid=0x000000002eb20800 nid=0x393c waiting on condition

JNI global references: 30510

 null
 */