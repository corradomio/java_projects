Dump structure (as string):

-----------------------------------------------------------------------------
    Type(jdk.ThreadDump) 9/15/20 4:21:13 PM 2020-09-15 16:21:13
    Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.241-b07 mixed mode):

    "RMI TCP Connection(91)-127.0.0.1" #265 daemon prio=5 os_prio=0 tid=0x0000000034897800 nid=0x3acc runnable [0x000000004d0cd000]
       java.lang.Thread.State: RUNNABLE
        at java.net.SocketInputStream.socketRead0(Native Method)
            ...
        at java.io.BufferedInputStream.read(BufferedInputStream.java:265)
        - locked <0x000000076a8115c0> (a java.io.BufferedInputStream)
        at java.io.FilterInputStream.read(FilterInputStream.java:83)
            ...
        at java.lang.Thread.run(Thread.java:748)

    "RMI TCP Connection(90)-127.0.0.1" #264 daemon prio=5 os_prio=0 tid=0x0000000034896000 nid=0x2bc8 in Object.wait() [0x000000003a80c000]
        ...

    ...

    "Service Thread" #16 daemon prio=9 os_prio=0 tid=0x000000002ca9b800 nid=0x3424 runnable [0x0000000000000000]
       java.lang.Thread.State: RUNNABLE

    ...

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