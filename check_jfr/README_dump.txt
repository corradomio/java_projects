D:\Java\Jdk1.8.0.x64\bin\java.exe -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:49319,suspend=y,server=n -Dvisualvm.id=90440131199711 "-javaagent:C:\Users\CORRAD~1\AppData\Local\Temp\captureAgent4395jars\debugger-agent.jar" -Dfile.encoding=UTF-8 -classpath "D:\Java\Jdk1.8.0.x64\jre\lib\charsets.jar;D:\Java\Jdk1.8.0.x64\jre\lib\deploy.jar;D:\Java\Jdk1.8.0.x64\jre\lib\ext\access-bridge-64.jar;D:\Java\Jdk1.8.0.x64\jre\lib\ext\cldrdata.jar;D:\Java\Jdk1.8.0.x64\jre\lib\ext\dnsns.jar;D:\Java\Jdk1.8.0.x64\jre\lib\ext\jaccess.jar;D:\Java\Jdk1.8.0.x64\jre\lib\ext\jfxrt.jar;D:\Java\Jdk1.8.0.x64\jre\lib\ext\localedata.jar;D:\Java\Jdk1.8.0.x64\jre\lib\ext\nashorn.jar;D:\Java\Jdk1.8.0.x64\jre\lib\ext\sunec.jar;D:\Java\Jdk1.8.0.x64\jre\lib\ext\sunjce_provider.jar;D:\Java\Jdk1.8.0.x64\jre\lib\ext\sunmscapi.jar;D:\Java\Jdk1.8.0.x64\jre\lib\ext\sunpkcs11.jar;D:\Java\Jdk1.8.0.x64\jre\lib\ext\zipfs.jar;D:\Java\Jdk1.8.0.x64\jre\lib\javaws.jar;D:\Java\Jdk1.8.0.x64\jre\lib\jce.jar;D:\Java\Jdk1.8.0.x64\jre\lib\jfr.jar;D:\Java\Jdk1.8.0.x64\jre\lib\jfxswt.jar;D:\Java\Jdk1.8.0.x64\jre\lib\jsse.jar;D:\Java\Jdk1.8.0.x64\jre\lib\management-agent.jar;D:\Java\Jdk1.8.0.x64\jre\lib\plugin.jar;D:\Java\Jdk1.8.0.x64\jre\lib\resources.jar;D:\Java\Jdk1.8.0.x64\jre\lib\rt.jar;D:\Java\Jdk1.8.0.x64\jre\lib\tzdb.jar;D:\Projects.github\java_projects\check_jfr\target\classes;C:\Users\Corrado Mio\.m2\repository\org\jetbrains\intellij\deps\jmc-common\8.0.0-1\jmc-common-8.0.0-1.jar;C:\Users\Corrado Mio\.m2\repository\org\jetbrains\intellij\deps\jmc-flightrecorder\8.0.0-1\jmc-flightrecorder-8.0.0-1.jar;C:\Users\Corrado Mio\.m2\repository\org\lz4\lz4-java\1.7.1\lz4-java-1.7.1.jar;C:\Program Files\JetBrains\IntelliJ IDEA 2020.1.1\lib\idea_rt.jar" org.hls.check.LoadRecording
Connected to the target VM, address: '127.0.0.1:49319', transport: 'socket'
Type(jdk.ObjectCountAfterGC): 0
Type(jdk.CompilationFailure): 28
  Type(jdk.CompilationFailure) 9/15/20 10:17:43 AM C2 CompilerThread0 retry without subsuming loads 12,418 null
  Type(jdk.CompilationFailure) 9/15/20 10:17:48 AM C2 CompilerThread2 retry without subsuming loads 13,013 null
  Type(jdk.CompilationFailure) 9/15/20 10:17:55 AM C2 CompilerThread0 retry without subsuming loads 13,713 null
  Type(jdk.CompilationFailure) 9/15/20 10:17:55 AM C2 CompilerThread2 retry without subsuming loads 13,729 null
  Type(jdk.CompilationFailure) 9/15/20 10:17:56 AM C2 CompilerThread0 retry without subsuming loads 13,808 null
  Type(jdk.CompilationFailure) 9/15/20 10:17:56 AM C2 CompilerThread2 retry without subsuming loads 13,838 null
  Type(jdk.CompilationFailure) 9/15/20 10:17:57 AM C2 CompilerThread0 retry without subsuming loads 13,858 null
  Type(jdk.CompilationFailure) 9/15/20 10:17:57 AM C2 CompilerThread0 retry without subsuming loads 13,895 null
  Type(jdk.CompilationFailure) 9/15/20 10:17:58 AM C2 CompilerThread0 retry without subsuming loads 13,905 null
  Type(jdk.CompilationFailure) 9/15/20 10:18:01 AM C2 CompilerThread0 retry without subsuming loads 14,091 null
  Type(jdk.CompilationFailure) 9/15/20 10:18:02 AM C2 CompilerThread2 retry without subsuming loads 14,127 null
  Type(jdk.CompilationFailure) 9/15/20 10:18:02 AM C2 CompilerThread0 retry without subsuming loads 14,128 null
Type(jdk.JavaMonitorEnter): 191
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@82548686 java.util.HashMap pool-3-thread-3 0x3A897590
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:39 AM 9/15/20 10:14:40 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-3 0x3A8930D0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@42277a5d ae.ebtic.spl.analysis.dependencyv2.ListenableDependencyAnalyzer pool-3-thread-5 0x3A896CA0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:41 AM 9/15/20 10:17:41 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-6 0x2D39C470
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:41 AM 9/15/20 10:17:42 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-6 0x2D39C470
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:42 AM 9/15/20 10:17:42 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-7 0x39F64DF0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:44 AM 9/15/20 10:17:44 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-4 0x2D63C100
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:45 AM 9/15/20 10:17:45 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-7 0x2D63C100
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:45 AM 9/15/20 10:17:46 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-2 0x2D63C100
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:46 AM 9/15/20 10:17:46 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-5 0x2D63C100
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:46 AM 9/15/20 10:17:46 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-6 0x25B023C0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:47 AM 9/15/20 10:17:47 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-4 0x2D49D6A0
Type(jdk.JavaMonitorEnter): 162
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@82548686 java.util.HashMap pool-3-thread-6 0x3A897590
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:39 AM 9/15/20 10:14:40 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-2 0x3A8930D0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@42277a5d ae.ebtic.spl.analysis.dependencyv2.ListenableDependencyAnalyzer pool-3-thread-6 0x3A896CA0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:45 AM 9/15/20 10:17:45 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-3 0x2D63C100
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:45 AM 9/15/20 10:17:45 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-7 0x2D63C100
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:47 AM 9/15/20 10:17:47 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-5 0x2D49D6A0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:48 AM 9/15/20 10:17:48 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@74942e2e jext.javaparser.JavaParserPool pool-3-thread-2 0x2D407E50
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:48 AM 9/15/20 10:17:49 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@2f29ca4e jext.javaparser.JavaParserPool pool-3-thread-5 0x2D407E50
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:49 AM 9/15/20 10:17:49 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@2f29ca4e jext.javaparser.JavaParserPool pool-3-thread-2 0x2D407E50
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:49 AM 9/15/20 10:17:49 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@2f29ca4e jext.javaparser.JavaParserPool pool-3-thread-1 0x2D407E50
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:49 AM 9/15/20 10:17:49 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@af609e2e jext.javaparser.JavaParserPool pool-3-thread-3 0x38421690
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:49 AM 9/15/20 10:17:50 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@af609e2e jext.javaparser.JavaParserPool pool-3-thread-3 0x2D49D120
Type(jdk.JavaMonitorEnter): 169
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@82548686 java.util.HashMap pool-3-thread-4 0x3A897590
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:39 AM 9/15/20 10:14:40 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-6 0x3A8930D0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@42277a5d ae.ebtic.spl.analysis.dependencyv2.ListenableDependencyAnalyzer pool-3-thread-3 0x3A896CA0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@d02ce50d java.lang.Object pool-3-thread-7 0x30D765E0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@de3d4a60 java.lang.Class pool-3-thread-2 0x3A894990
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:40 AM 9/15/20 10:14:41 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@77b742e0 ae.ebtic.spl.analysis.sourcecode.analyzer.java.DirectoryLibrary pool-3-thread-7 0x30D76320
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:41 AM 9/15/20 10:14:41 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@2f3dd659 javassist.ClassPool pool-3-thread-7 0x30D76270
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:41 AM 9/15/20 10:14:41 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@77b742e0 ae.ebtic.spl.analysis.sourcecode.analyzer.java.DirectoryLibrary pool-3-thread-1 0x30D75820
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:41 AM 9/15/20 10:17:41 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-7 0x2D39C470
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:42 AM 9/15/20 10:17:42 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-3 0x2D39C470
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:42 AM 9/15/20 10:17:42 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-2 0x2D39C470
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:43 AM 9/15/20 10:17:43 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-1 0x2D407140
Type(jdk.JavaMonitorEnter): 173
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@82548686 java.util.HashMap pool-3-thread-2 0x3A897590
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:39 AM 9/15/20 10:14:40 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-4 0x3A8930D0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@d02ce50d java.lang.Object pool-3-thread-6 0x30D765E0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@de3d4a60 java.lang.Class pool-3-thread-3 0x3A894990
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@77b742e0 ae.ebtic.spl.analysis.sourcecode.analyzer.java.DirectoryLibrary pool-3-thread-5 0x30D76320
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:40 AM 9/15/20 10:14:41 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@58096ee7 ae.ebtic.spl.analysis.sourcecode.analyzer.java.DirectoryLibrary pool-3-thread-1 0x30D76320
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:41 AM 9/15/20 10:14:41 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@77b742e0 ae.ebtic.spl.analysis.sourcecode.analyzer.java.DirectoryLibrary pool-3-thread-4 0x30D75820
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:41 AM 9/15/20 10:14:41 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@94383678 ae.ebtic.spl.analysis.sourcecode.analyzer.java.DirectoryLibrary pool-3-thread-6 0x30D75820
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:41 AM 9/15/20 10:17:41 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-5 0x2D39C470
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:41 AM 9/15/20 10:17:41 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-2 0x2D39C470
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:42 AM 9/15/20 10:17:42 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-5 0x2D39C470
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:42 AM 9/15/20 10:17:42 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-3 0x39F64DF0
Type(jdk.JavaMonitorEnter): 169
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@42277a5d ae.ebtic.spl.analysis.dependencyv2.ListenableDependencyAnalyzer pool-3-thread-1 0x3A896CA0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@d02ce50d java.lang.Object pool-3-thread-2 0x30D765E0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:40 AM 9/15/20 10:14:41 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@77b742e0 ae.ebtic.spl.analysis.sourcecode.analyzer.java.DirectoryLibrary pool-3-thread-5 0x30D76320
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:41 AM 9/15/20 10:14:41 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@77b742e0 ae.ebtic.spl.analysis.sourcecode.analyzer.java.DirectoryLibrary pool-3-thread-5 0x30D76320
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:41 AM 9/15/20 10:14:41 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@94383678 ae.ebtic.spl.analysis.sourcecode.analyzer.java.DirectoryLibrary pool-3-thread-6 0x30D75820
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:42 AM 9/15/20 10:14:42 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@9873dbd javassist.ClassPool pool-3-thread-5 0x2D639870
  Type(jdk.JavaMonitorEnter) 9/15/20 10:16:42 AM 9/15/20 10:16:42 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@63ed25ca ae.ebtic.spl.analysis.dependencies.util.LibrariesRegistry pool-3-thread-5 0x3A895F90
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:41 AM 9/15/20 10:17:41 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-2 0x2D39C470
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:41 AM 9/15/20 10:17:41 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-6 0x2D39C470
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:41 AM 9/15/20 10:17:41 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-5 0x2D39C470
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:42 AM 9/15/20 10:17:42 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-1 0x2D39C470
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:42 AM 9/15/20 10:17:42 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-1 0x39F64DF0
Type(jdk.JavaMonitorEnter): 180
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@82548686 java.util.HashMap pool-3-thread-1 0x3A897590
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:39 AM 9/15/20 10:14:40 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-5 0x3A8930D0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@42277a5d ae.ebtic.spl.analysis.dependencyv2.ListenableDependencyAnalyzer pool-3-thread-7 0x3A896CA0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@d02ce50d java.lang.Object pool-3-thread-4 0x30D765E0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:41 AM 9/15/20 10:17:41 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-1 0x2D39C470
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:41 AM 9/15/20 10:17:42 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-4 0x2D39C470
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:43 AM 9/15/20 10:17:43 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-5 0x2D407140
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:44 AM 9/15/20 10:17:44 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-5 0x2D63C100
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:45 AM 9/15/20 10:17:45 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-6 0x2D63C100
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:45 AM 9/15/20 10:17:45 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-3 0x2D63C100
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:45 AM 9/15/20 10:17:46 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-5 0x2D63C100
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:46 AM 9/15/20 10:17:46 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-7 0x2D63C100
Type(jdk.JavaMonitorEnter): 127
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@82548686 java.util.HashMap pool-3-thread-7 0x3A897590
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:39 AM 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-1 0x3A8930D0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@42277a5d ae.ebtic.spl.analysis.dependencyv2.ListenableDependencyAnalyzer pool-3-thread-4 0x3A896CA0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@d02ce50d java.lang.Object pool-3-thread-5 0x30D765E0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:40 AM 9/15/20 10:14:41 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@77b742e0 ae.ebtic.spl.analysis.sourcecode.analyzer.java.DirectoryLibrary pool-3-thread-6 0x30D76320
  Type(jdk.JavaMonitorEnter) 9/15/20 10:14:41 AM 9/15/20 10:14:41 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@56563c91 ae.ebtic.spl.analysis.sourcecode.analyzer.java.DirectoryLibrary pool-3-thread-2 0x30D75820
  Type(jdk.JavaMonitorEnter) 9/15/20 10:16:42 AM 9/15/20 10:16:42 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@63ed25ca ae.ebtic.spl.analysis.dependencies.util.LibrariesRegistry pool-3-thread-1 0x3A895F90
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:41 AM 9/15/20 10:17:41 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-3 0x2D39C470
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:41 AM 9/15/20 10:17:42 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-1 0x2D39C470
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:42 AM 9/15/20 10:17:42 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-7 0x2D39C470
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:43 AM 9/15/20 10:17:43 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-6 0x2D407140
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:44 AM 9/15/20 10:17:44 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@b2b10e6e jext.javaparser.JavaParserPool pool-3-thread-7 0x2D63C100
Type(jdk.JavaMonitorEnter): 2
  Type(jdk.JavaMonitorEnter) 9/15/20 10:17:45 AM 9/15/20 10:17:45 AM Neo4jDriverIO-2-6 org.openjdk.jmc.common.util.MCStackTrace@759eab6a java.lang.Object Neo4jDriverIO-2-1 0x3B1DC9C0
  Type(jdk.JavaMonitorEnter) 9/15/20 10:18:18 AM 9/15/20 10:18:18 AM Neo4jDriverIO-2-1 org.openjdk.jmc.common.util.MCStackTrace@759eab6a java.lang.Object Neo4jDriverIO-2-6 0x39F63060
Type(jdk.AllocationRequiringGC): 0
Type(jdk.CompilerPhase): 0
Type(jdk.ThreadContextSwitchRate): 29
  Type(jdk.ThreadContextSwitchRate) 9/15/20 10:14:00 AM 61,901 null
  Type(jdk.ThreadContextSwitchRate) 9/15/20 10:14:10 AM 63,979 null
  Type(jdk.ThreadContextSwitchRate) 9/15/20 10:14:20 AM 67,692 null
  Type(jdk.ThreadContextSwitchRate) 9/15/20 10:14:30 AM 63,902 null
  Type(jdk.ThreadContextSwitchRate) 9/15/20 10:14:40 AM 66,664 null
  Type(jdk.ThreadContextSwitchRate) 9/15/20 10:14:50 AM 67,309 null
  Type(jdk.ThreadContextSwitchRate) 9/15/20 10:15:00 AM 63,100 null
  Type(jdk.ThreadContextSwitchRate) 9/15/20 10:15:10 AM 67,793 null
  Type(jdk.ThreadContextSwitchRate) 9/15/20 10:15:20 AM 66,252 null
  Type(jdk.ThreadContextSwitchRate) 9/15/20 10:15:30 AM 65,417 null
  Type(jdk.ThreadContextSwitchRate) 9/15/20 10:15:40 AM 72,288 null
  Type(jdk.ThreadContextSwitchRate) 9/15/20 10:15:50 AM 65,989 null
Type(jdk.EvacuationInformation): 0
Type(jdk.DoubleFlag): 10
  Type(jdk.DoubleFlag) 9/15/20 10:18:50 AM FLSLargestBlockCoalesceProximity 0.99 Default null
  Type(jdk.DoubleFlag) 9/15/20 10:18:50 AM CMSSmallCoalSurplusPercent 1.05 Default null
  Type(jdk.DoubleFlag) 9/15/20 10:18:50 AM CMSLargeCoalSurplusPercent 0.95 Default null
  Type(jdk.DoubleFlag) 9/15/20 10:18:50 AM CMSSmallSplitSurplusPercent 1.1 Default null
  Type(jdk.DoubleFlag) 9/15/20 10:18:50 AM CMSLargeSplitSurplusPercent 1 Default null
  Type(jdk.DoubleFlag) 9/15/20 10:18:50 AM MaxRAMPercentage 25 Default null
  Type(jdk.DoubleFlag) 9/15/20 10:18:50 AM MinRAMPercentage 50 Default null
  Type(jdk.DoubleFlag) 9/15/20 10:18:50 AM InitialRAMPercentage 1.56 Default null
  Type(jdk.DoubleFlag) 9/15/20 10:18:50 AM G1ConcMarkStepDurationMillis 10 Default null
  Type(jdk.DoubleFlag) 9/15/20 10:18:50 AM EscapeAnalysisTimeout 20 Default null
Type(jdk.CodeCacheConfiguration): 1
  Type(jdk.CodeCacheConfiguration) 9/15/20 10:18:50 AM 2.44 MiB 240 MiB 64 KiB 4 B 0x027A0000 0x117A0000
Type(jdk.SocketWrite): 2
  Type(jdk.SocketWrite) 9/15/20 10:17:54 AM 9/15/20 10:17:54 AM RMI TCP Connection(8)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@a8db54e0 4.62 KiB 59,496 127.0.0.1  null
  Type(jdk.SocketWrite) 9/15/20 10:17:56 AM 9/15/20 10:17:56 AM RMI TCP Connection(8)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@61c7e304 1 B 59,496 127.0.0.1  null
Type(jdk.CodeCacheFull): 0
Type(jdk.GCConfiguration): 1
  Type(jdk.GCConfiguration) 9/15/20 10:18:50 AM ParallelScavenge ParallelOld 8 0 false false false -292 ×10⁶ y 99 null
Type(jdk.MetaspaceChunkFreeListSummary): 40
  Type(jdk.MetaspaceChunkFreeListSummary) 9/15/20 10:14:38 AM 11 Before GC Metadata 0 0 B 0 0 B 0 0 B 0 ...
  Type(jdk.MetaspaceChunkFreeListSummary) 9/15/20 10:14:38 AM 11 Before GC Class 0 0 B 0 0 B 0 0 B 0 ...
  Type(jdk.MetaspaceChunkFreeListSummary) 9/15/20 10:14:38 AM 11 After GC Metadata 0 0 B 0 0 B 0 0 B 0 ...
  Type(jdk.MetaspaceChunkFreeListSummary) 9/15/20 10:14:38 AM 11 After GC Class 0 0 B 0 0 B 0 0 B 0 ...
  Type(jdk.MetaspaceChunkFreeListSummary) 9/15/20 10:14:40 AM 12 Before GC Metadata 0 0 B 0 0 B 0 0 B 0 ...
  Type(jdk.MetaspaceChunkFreeListSummary) 9/15/20 10:14:40 AM 12 Before GC Class 0 0 B 0 0 B 0 0 B 0 ...
  Type(jdk.MetaspaceChunkFreeListSummary) 9/15/20 10:14:40 AM 12 After GC Metadata 0 0 B 0 0 B 0 0 B 0 ...
  Type(jdk.MetaspaceChunkFreeListSummary) 9/15/20 10:14:40 AM 12 After GC Class 0 0 B 0 0 B 0 0 B 0 ...
  Type(jdk.MetaspaceChunkFreeListSummary) 9/15/20 10:14:40 AM 13 Before GC Metadata 0 0 B 0 0 B 0 0 B 0 ...
  Type(jdk.MetaspaceChunkFreeListSummary) 9/15/20 10:14:40 AM 13 Before GC Class 0 0 B 0 0 B 0 0 B 0 ...
  Type(jdk.MetaspaceChunkFreeListSummary) 9/15/20 10:14:40 AM 13 After GC Metadata 6 6 KiB 6 24 KiB 0 0 B 0 ...
  Type(jdk.MetaspaceChunkFreeListSummary) 9/15/20 10:14:40 AM 13 After GC Class 6 6 KiB 0 0 B 0 0 B 0 ...
Type(jdk.GarbageCollection): 10
  Type(jdk.GarbageCollection) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM 11 ParallelScavenge Allocation Failure 33.681 ms 33.681 ms
  Type(jdk.GarbageCollection) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 12 ParallelScavenge Metadata GC Threshold 25.137 ms 25.137 ms
  Type(jdk.GarbageCollection) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 13 ParallelOld Metadata GC Threshold 228.882 ms 228.882 ms
  Type(jdk.GarbageCollection) 9/15/20 10:17:48 AM 9/15/20 10:17:48 AM 14 ParallelScavenge Allocation Failure 96.956 ms 96.956 ms
  Type(jdk.GarbageCollection) 9/15/20 10:18:01 AM 9/15/20 10:18:01 AM 15 ParallelScavenge Allocation Failure 128.717 ms 128.717 ms
  Type(jdk.GarbageCollection) 9/15/20 10:18:08 AM 9/15/20 10:18:08 AM 16 ParallelScavenge Allocation Failure 46.161 ms 46.161 ms
  Type(jdk.GarbageCollection) 9/15/20 10:18:15 AM 9/15/20 10:18:15 AM 17 ParallelScavenge Allocation Failure 33.005 ms 33.005 ms
  Type(jdk.GarbageCollection) 9/15/20 10:18:38 AM 9/15/20 10:18:38 AM 18 ParallelScavenge Allocation Failure 34.564 ms 34.564 ms
  Type(jdk.GarbageCollection) 9/15/20 10:18:48 AM 9/15/20 10:18:48 AM 19 ParallelScavenge Allocation Failure 136.987 ms 136.987 ms
  Type(jdk.GarbageCollection) 9/15/20 10:18:48 AM 9/15/20 10:18:49 AM 20 ParallelOld Ergonomics 1.100 s 1.100 s
Type(jdk.DoubleFlagChanged): 0
Type(jdk.CPUTimeStampCounter): 1
  Type(jdk.CPUTimeStampCounter) 9/15/20 10:18:50 AM true true 2.34 ×10⁶ 2.4 ×10⁹
Type(jdk.ExecutionSample): 2403
  Type(jdk.ExecutionSample) 9/15/20 10:13:51 AM http-nio-8080-ClientPoller-1 org.openjdk.jmc.common.util.MCStackTrace@7063a6c9 STATE_RUNNABLE null
  Type(jdk.ExecutionSample) 9/15/20 10:13:53 AM ajp-nio-8009-ClientPoller-0 org.openjdk.jmc.common.util.MCStackTrace@b3c0cb87 STATE_RUNNABLE null
  Type(jdk.ExecutionSample) 9/15/20 10:13:55 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@6dd093dd STATE_RUNNABLE null
  Type(jdk.ExecutionSample) 9/15/20 10:13:56 AM ajp-nio-8009-ClientPoller-1 org.openjdk.jmc.common.util.MCStackTrace@caa59bf7 STATE_RUNNABLE null
  Type(jdk.ExecutionSample) 9/15/20 10:13:59 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@396d7d03 STATE_RUNNABLE null
  Type(jdk.ExecutionSample) 9/15/20 10:14:05 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e6885234 STATE_RUNNABLE null
  Type(jdk.ExecutionSample) 9/15/20 10:14:11 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@32de9f41 STATE_RUNNABLE null
  Type(jdk.ExecutionSample) 9/15/20 10:14:11 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@7d701332 STATE_RUNNABLE null
  Type(jdk.ExecutionSample) 9/15/20 10:14:11 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@5b2e0f90 STATE_RUNNABLE null
  Type(jdk.ExecutionSample) 9/15/20 10:14:11 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@872ecdbd STATE_RUNNABLE null
  Type(jdk.ExecutionSample) 9/15/20 10:14:11 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@75f4fd13 STATE_RUNNABLE null
  Type(jdk.ExecutionSample) 9/15/20 10:14:12 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@9e0dcec3 STATE_RUNNABLE null
Type(jdk.StringFlagChanged): 0
Type(jdk.GCHeapSummary): 20
  Type(jdk.GCHeapSummary) 9/15/20 10:14:38 AM 11 Before GC 0x5C0A00000 0x616E80000 1.35 GiB 0x7C0000000 7.99 GiB 1.04 GiB
  Type(jdk.GCHeapSummary) 9/15/20 10:14:38 AM 11 After GC 0x5C0A00000 0x62B700000 1.67 GiB 0x7C0000000 7.99 GiB 72 MiB
  Type(jdk.GCHeapSummary) 9/15/20 10:14:40 AM 12 Before GC 0x5C0A00000 0x62B700000 1.67 GiB 0x7C0000000 7.99 GiB 284 MiB
  Type(jdk.GCHeapSummary) 9/15/20 10:14:40 AM 12 After GC 0x5C0A00000 0x633D00000 1.8 GiB 0x7C0000000 7.99 GiB 81.5 MiB
  Type(jdk.GCHeapSummary) 9/15/20 10:14:40 AM 13 Before GC 0x5C0A00000 0x633D00000 1.8 GiB 0x7C0000000 7.99 GiB 81.5 MiB
  Type(jdk.GCHeapSummary) 9/15/20 10:14:40 AM 13 After GC 0x5C0A00000 0x638000000 1.87 GiB 0x7C0000000 7.99 GiB 67.4 MiB
  Type(jdk.GCHeapSummary) 9/15/20 10:17:48 AM 14 Before GC 0x5C0A00000 0x638000000 1.87 GiB 0x7C0000000 7.99 GiB 1.53 GiB
  Type(jdk.GCHeapSummary) 9/15/20 10:17:48 AM 14 After GC 0x5C0A00000 0x650780000 2.25 GiB 0x7C0000000 7.99 GiB 148 MiB
  Type(jdk.GCHeapSummary) 9/15/20 10:18:01 AM 15 Before GC 0x5C0A00000 0x650780000 2.25 GiB 0x7C0000000 7.99 GiB 1.99 GiB
  Type(jdk.GCHeapSummary) 9/15/20 10:18:01 AM 15 After GC 0x5C0A00000 0x651400000 2.26 GiB 0x7C0000000 7.99 GiB 208 MiB
  Type(jdk.GCHeapSummary) 9/15/20 10:18:08 AM 16 Before GC 0x5C0A00000 0x651400000 2.26 GiB 0x7C0000000 7.99 GiB 2.05 GiB
  Type(jdk.GCHeapSummary) 9/15/20 10:18:08 AM 16 After GC 0x5C0A00000 0x677780000 2.86 GiB 0x7C0000000 7.99 GiB 218 MiB
Type(jdk.LongFlag): 270
  Type(jdk.LongFlag) 9/15/20 10:18:50 AM ObjectAlignmentInBytes 8 Default null
  Type(jdk.LongFlag) 9/15/20 10:18:50 AM UseSSE 4 Default null
  Type(jdk.LongFlag) 9/15/20 10:18:50 AM SuspendRetryCount 50 Default null
  Type(jdk.LongFlag) 9/15/20 10:18:50 AM SuspendRetryDelay 5 Default null
  Type(jdk.LongFlag) 9/15/20 10:18:50 AM EmitSync 0 Default null
  Type(jdk.LongFlag) 9/15/20 10:18:50 AM MonitorBound 0 Default null
  Type(jdk.LongFlag) 9/15/20 10:18:50 AM SyncFlags 0 Default null
  Type(jdk.LongFlag) 9/15/20 10:18:50 AM SyncVerbose 0 Default null
  Type(jdk.LongFlag) 9/15/20 10:18:50 AM ClearFPUAtPark 0 Default null
  Type(jdk.LongFlag) 9/15/20 10:18:50 AM hashCode 5 Default null
  Type(jdk.LongFlag) 9/15/20 10:18:50 AM WorkAroundNPTLTimedWaitHang 1 Default null
  Type(jdk.LongFlag) 9/15/20 10:18:50 AM MaxJNILocalCapacity 65,536 Default null
Type(jdk.CodeSweeperConfiguration): 1
  Type(jdk.CodeSweeperConfiguration) 9/15/20 10:18:50 AM true true
Type(jdk.JavaExceptionThrow): 0
Type(jdk.JavaErrorThrow): 25
  Type(jdk.JavaErrorThrow) 9/15/20 10:14:11 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@216b950d java.lang.NoSuchMethodError java.lang.Object.lambda$thenComparing$36697e65$1(Ljava/util/Comparator;Ljava/lang/Object;Ljava/lang/Object;)I
  Type(jdk.JavaErrorThrow) 9/15/20 10:14:12 AM Neo4jDriverIO-2-2 org.openjdk.jmc.common.util.MCStackTrace@594499e9 org.neo4j.driver.internal.shaded.io.netty.util.Signal
  Type(jdk.JavaErrorThrow) 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@79bb15af java.lang.NoSuchMethodError java.lang.Object.lambda$getBegin$0(Lcom/github/javaparser/Range;)Lcom/github/javaparser/Position;
  Type(jdk.JavaErrorThrow) 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@19a62d11 java.lang.NoSuchMethodError java.lang.Object.lambda$getEnd$1(Lcom/github/javaparser/Range;)Lcom/github/javaparser/Position;
  Type(jdk.JavaErrorThrow) 9/15/20 10:14:40 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@7aa233b0 java.lang.NoSuchMethodError java.lang.Object.lambda$getMethods$1(Lcom/github/javaparser/ast/body/BodyDeclaration;)Z
  Type(jdk.JavaErrorThrow) 9/15/20 10:14:40 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@47d63619 java.lang.NoSuchMethodError java.lang.Object.lambda$getMethods$2(Lcom/github/javaparser/ast/body/BodyDeclaration;)Lcom/github/javaparser/ast/body/MethodDeclaration;
  Type(jdk.JavaErrorThrow) 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@7e3a80ab java.lang.NoSuchMethodError java.lang.Object.lambda$getJavadocComment$0(Lcom/github/javaparser/ast/comments/Comment;)Z
  Type(jdk.JavaErrorThrow) 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@564d0552 java.lang.NoSuchMethodError java.lang.Object.lambda$getJavadocComment$1(Lcom/github/javaparser/ast/comments/Comment;)Lcom/github/javaparser/ast/comments/JavadocComment;
  Type(jdk.JavaErrorThrow) 9/15/20 10:14:41 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@31696399 java.lang.NoSuchMethodError java.lang.Object.lambda$solveSymbol$0()Ljava/lang/RuntimeException;
  Type(jdk.JavaErrorThrow) 9/15/20 10:14:41 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@7c9facd5 java.lang.NoSuchMethodError java.lang.Object.lambda$getVisibleFields$4(Lcom/github/javaparser/resolution/declarations/ResolvedFieldDeclaration;)Z
  Type(jdk.JavaErrorThrow) 9/15/20 10:14:41 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@a75408f8 java.lang.NoSuchMethodError java.lang.Object.lambda$findAncestor$0(Ljava/lang/Object;)Z
  Type(jdk.JavaErrorThrow) 9/15/20 10:14:41 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@789cf858 java.lang.NoSuchMethodError java.lang.Object.lambda$hasVisibleField$3(Ljava/lang/String;Lcom/github/javaparser/resolution/declarations/ResolvedFieldDeclaration;)Z
Type(jdk.SystemProcess): 180
  Type(jdk.SystemProcess) 9/15/20 10:18:50 AM 2548 C:\Users\Corrado Mio\AppData\Local\Microsoft\Teams\current\Teams.exe
  Type(jdk.SystemProcess) 9/15/20 10:18:50 AM 15300 D:\Java\Jdk10.0.x64\bin\jmc.exe
  Type(jdk.SystemProcess) 9/15/20 10:18:50 AM 12936 C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe
  Type(jdk.SystemProcess) 9/15/20 10:18:50 AM 15784 D:\Java\Jdk1.8.0.x64\bin\java.exe
  Type(jdk.SystemProcess) 9/15/20 10:18:50 AM 10812 C:\Windows\system32\conhost.exe
  Type(jdk.SystemProcess) 9/15/20 10:18:50 AM 16008 C:\Windows\system32\cmd.exe
  Type(jdk.SystemProcess) 9/15/20 10:18:50 AM 5752 C:\Windows\system32\conhost.exe
  Type(jdk.SystemProcess) 9/15/20 10:18:50 AM 15952 D:\Java\Jdk1.8.0.x64\bin\java.exe
  Type(jdk.SystemProcess) 9/15/20 10:18:50 AM 9920 D:\Java\Jdk1.8.0.x64\jre\bin\java.exe
  Type(jdk.SystemProcess) 9/15/20 10:18:50 AM 13976 C:\Windows\System32\WindowsPowerShell\v1.0\powershell.exe
  Type(jdk.SystemProcess) 9/15/20 10:18:50 AM 1364 D:\Java\Jdk1.8.0.x64\bin\java.exe
  Type(jdk.SystemProcess) 9/15/20 10:18:50 AM 14052 C:\Windows\system32\taskmgr.exe
Type(jdk.CPULoad): 299
  Type(jdk.CPULoad) 9/15/20 10:13:51 AM 2.04 % 0.234 % 10.3 % null
  Type(jdk.CPULoad) 9/15/20 10:13:52 AM 0.388 % 0.388 % 4.34 % null
  Type(jdk.CPULoad) 9/15/20 10:13:53 AM 0 % 0.198 % 7.25 % null
  Type(jdk.CPULoad) 9/15/20 10:13:54 AM 0 % 0.195 % 6.69 % null
  Type(jdk.CPULoad) 9/15/20 10:13:55 AM 0.195 % 0 % 6.69 % null
  Type(jdk.CPULoad) 9/15/20 10:13:56 AM 195 ×10⁻⁶ % 0.195 % 4.94 % null
  Type(jdk.CPULoad) 9/15/20 10:13:57 AM 0 % 0.194 % 6.95 % null
  Type(jdk.CPULoad) 9/15/20 10:13:58 AM 0.196 % 0 % 14.2 % null
  Type(jdk.CPULoad) 9/15/20 10:13:59 AM 196 ×10⁻⁶ % 0.196 % 18.9 % null
  Type(jdk.CPULoad) 9/15/20 10:14:00 AM 0.193 % 0.385 % 9.53 % null
  Type(jdk.CPULoad) 9/15/20 10:14:01 AM 0 % 0.196 % 10.5 % null
  Type(jdk.CPULoad) 9/15/20 10:14:02 AM 384 ×10⁻⁶ % 0.193 % 6.85 % null
Type(jdk.ObjectAllocationOutsideTLAB): 54
  Type(jdk.ObjectAllocationOutsideTLAB) 9/15/20 10:14:38 AM pool-2-thread-1 org.openjdk.jmc.common.util.MCStackTrace@db59ac37 char[] 112 B
  Type(jdk.ObjectAllocationOutsideTLAB) 9/15/20 10:14:39 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@55e4440f byte[] 194 KiB
  Type(jdk.ObjectAllocationOutsideTLAB) 9/15/20 10:14:39 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@55e4440f byte[] 194 KiB
  Type(jdk.ObjectAllocationOutsideTLAB) 9/15/20 10:14:39 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@55e4440f byte[] 194 KiB
  Type(jdk.ObjectAllocationOutsideTLAB) 9/15/20 10:14:39 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@8af5c28d byte[] 96.2 KiB
  Type(jdk.ObjectAllocationOutsideTLAB) 9/15/20 10:14:39 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@8af5c28d byte[] 96.2 KiB
  Type(jdk.ObjectAllocationOutsideTLAB) 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@98121f05 byte[] 67 KiB
  Type(jdk.ObjectAllocationOutsideTLAB) 9/15/20 10:16:38 AM pool-2-thread-2 org.openjdk.jmc.common.util.MCStackTrace@8e470bcf java.util.HashMap$Node[] 128 KiB
  Type(jdk.ObjectAllocationOutsideTLAB) 9/15/20 10:16:38 AM pool-2-thread-2 org.openjdk.jmc.common.util.MCStackTrace@264f07b2 java.util.HashMap$Node[] 128 KiB
  Type(jdk.ObjectAllocationOutsideTLAB) 9/15/20 10:16:39 AM pool-2-thread-2 org.openjdk.jmc.common.util.MCStackTrace@264f07b2 java.util.HashMap$Node[] 32 KiB
  Type(jdk.ObjectAllocationOutsideTLAB) 9/15/20 10:16:40 AM pool-2-thread-2 org.openjdk.jmc.common.util.MCStackTrace@a7c14626 java.util.HashMap$Node[] 128 KiB
  Type(jdk.ObjectAllocationOutsideTLAB) 9/15/20 10:16:40 AM pool-2-thread-2 org.openjdk.jmc.common.util.MCStackTrace@a7c14626 java.util.HashMap$Node[] 128 KiB
Type(jdk.ObjectCount): 0
Type(jdk.ActiveSetting): 293
  Type(jdk.ActiveSetting) 9/15/20 10:13:50 AM Flight Recording enabled true null
  Type(jdk.ActiveSetting) 9/15/20 10:13:50 AM Flight Recording stacktrace false null
  Type(jdk.ActiveSetting) 9/15/20 10:13:50 AM Flight Recording period everyChunk null
  Type(jdk.ActiveSetting) 9/15/20 10:13:50 AM Recording Setting enabled true null
  Type(jdk.ActiveSetting) 9/15/20 10:13:50 AM Recording Setting stacktrace false null
  Type(jdk.ActiveSetting) 9/15/20 10:13:50 AM Recording Setting period everyChunk null
  Type(jdk.ActiveSetting) 9/15/20 10:13:50 AM Java Thread Start enabled true null
  Type(jdk.ActiveSetting) 9/15/20 10:13:50 AM Java Thread Start stacktrace false null
  Type(jdk.ActiveSetting) 9/15/20 10:13:50 AM Java Thread Start period everyChunk null
  Type(jdk.ActiveSetting) 9/15/20 10:13:50 AM Java Thread End enabled true null
  Type(jdk.ActiveSetting) 9/15/20 10:13:50 AM Java Thread End stacktrace false null
  Type(jdk.ActiveSetting) 9/15/20 10:13:50 AM Java Thread End period everyChunk null
Type(jdk.ExecuteVMOperation): 8
  Type(jdk.ExecuteVMOperation) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM VM Thread ParallelGCFailedAllocation true true pool-2-thread-1
  Type(jdk.ExecuteVMOperation) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM VM Thread CollectForMetadataAllocation true true pool-3-thread-3
  Type(jdk.ExecuteVMOperation) 9/15/20 10:17:48 AM 9/15/20 10:17:48 AM VM Thread ParallelGCFailedAllocation true true pool-3-thread-2
  Type(jdk.ExecuteVMOperation) 9/15/20 10:18:01 AM 9/15/20 10:18:01 AM VM Thread ParallelGCFailedAllocation true true pool-3-thread-5
  Type(jdk.ExecuteVMOperation) 9/15/20 10:18:08 AM 9/15/20 10:18:08 AM VM Thread ParallelGCFailedAllocation true true pool-3-thread-5
  Type(jdk.ExecuteVMOperation) 9/15/20 10:18:15 AM 9/15/20 10:18:15 AM VM Thread ParallelGCFailedAllocation true true pool-3-thread-3
  Type(jdk.ExecuteVMOperation) 9/15/20 10:18:38 AM 9/15/20 10:18:38 AM VM Thread ParallelGCFailedAllocation true true pool-3-thread-2
  Type(jdk.ExecuteVMOperation) 9/15/20 10:18:48 AM 9/15/20 10:18:49 AM VM Thread ParallelGCFailedAllocation true true pool-3-thread-4
Type(jdk.FileWrite): 0
Type(jdk.BooleanFlagChanged): 0
Type(jdk.MetaspaceGCThreshold): 2
  Type(jdk.MetaspaceGCThreshold) 9/15/20 10:14:40 AM 57.6 MiB 96.1 MiB compute_new_size null
  Type(jdk.MetaspaceGCThreshold) 9/15/20 10:18:49 AM 96.1 MiB 112 MiB compute_new_size null
Type(jdk.InitialSystemProperty): 32
  Type(jdk.InitialSystemProperty) 9/15/20 10:18:50 AM java.library.path D:\Java\Jdk1.8.0.x64\bin;C:\Windows\Sun\Java\bin;C:\Windows\system32;C:\Windows;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\Wolfram Research\WolframScript\;C:\Program Files\Wolfram Research\WolframScript\;C:\Bin;D:\Python;D:\Java;D:\Java\Jdk11.0.x64\bin;D:\Python\Anaconda3-2019.10;D:\Python\Anaconda3-2019.10\condabin;D:\Python\Anaconda3-2019.10\Scripts;D:\Programming\git\bin;D:\Javascript\node-v12.18.2-win-x64;.
  Type(jdk.InitialSystemProperty) 9/15/20 10:18:50 AM java.vm.specification.name Java Virtual Machine Specification
  Type(jdk.InitialSystemProperty) 9/15/20 10:18:50 AM java.vm.version 25.241-b07
  Type(jdk.InitialSystemProperty) 9/15/20 10:18:50 AM java.vm.name Java HotSpot(TM) 64-Bit Server VM
  Type(jdk.InitialSystemProperty) 9/15/20 10:18:50 AM java.vm.info mixed mode, sharing
  Type(jdk.InitialSystemProperty) 9/15/20 10:18:50 AM java.ext.dirs D:\Java\Jdk1.8.0.x64\jre\lib\ext;C:\Windows\Sun\Java\lib\ext
  Type(jdk.InitialSystemProperty) 9/15/20 10:18:50 AM java.endorsed.dirs D:\Java\Jdk1.8.0.x64\jre\lib\endorsed
  Type(jdk.InitialSystemProperty) 9/15/20 10:18:50 AM sun.boot.library.path D:\Java\Jdk1.8.0.x64\jre\bin
  Type(jdk.InitialSystemProperty) 9/15/20 10:18:50 AM java.home D:\Java\Jdk1.8.0.x64\jre
  Type(jdk.InitialSystemProperty) 9/15/20 10:18:50 AM java.class.path D:\Java\Tomcat-8.5.37\bin\bootstrap.jar;D:\Java\Tomcat-8.5.37\bin\tomcat-juli.jar;C:\Users\CORRAD~1\AppData\Local\Temp\captureAgent3480jars\debugger-agent.jar;C:\Users\CORRAD~1\AppData\Local\Temp\groovyHotSwap1719jars\gragent.jar
  Type(jdk.InitialSystemProperty) 9/15/20 10:18:50 AM sun.boot.class.path D:\Java\Jdk1.8.0.x64\jre\lib\resources.jar;D:\Java\Jdk1.8.0.x64\jre\lib\rt.jar;D:\Java\Jdk1.8.0.x64\jre\lib\sunrsasign.jar;D:\Java\Jdk1.8.0.x64\jre\lib\jsse.jar;D:\Java\Jdk1.8.0.x64\jre\lib\jce.jar;D:\Java\Jdk1.8.0.x64\jre\lib\charsets.jar;D:\Java\Jdk1.8.0.x64\jre\lib\jfr.jar;D:\Java\Jdk1.8.0.x64\jre\classes
  Type(jdk.InitialSystemProperty) 9/15/20 10:18:50 AM java.vm.specification.vendor Oracle Corporation
Type(jdk.ThreadAllocationStatistics): 149
  Type(jdk.ThreadAllocationStatistics) 9/15/20 10:13:50 AM 304 KiB RMI TCP Connection(idle)
  Type(jdk.ThreadAllocationStatistics) 9/15/20 10:13:50 AM 16.3 KiB JMX server connection timeout 73
  Type(jdk.ThreadAllocationStatistics) 9/15/20 10:13:50 AM 9.65 MiB RMI TCP Connection(5)-127.0.0.1
  Type(jdk.ThreadAllocationStatistics) 9/15/20 10:13:50 AM 32 B MessageBroker-2
  Type(jdk.ThreadAllocationStatistics) 9/15/20 10:13:50 AM 32 B MessageBroker-2
  Type(jdk.ThreadAllocationStatistics) 9/15/20 10:13:50 AM 18 KiB MessageBroker-1
  Type(jdk.ThreadAllocationStatistics) 9/15/20 10:13:50 AM 19.1 KiB MessageBroker-1
  Type(jdk.ThreadAllocationStatistics) 9/15/20 10:13:50 AM 10.7 MiB DelayedTimer
  Type(jdk.ThreadAllocationStatistics) 9/15/20 10:13:50 AM 0 B FileWatchdog
  Type(jdk.ThreadAllocationStatistics) 9/15/20 10:13:50 AM 13.3 KiB ajp-nio-8009-AsyncTimeout
  Type(jdk.ThreadAllocationStatistics) 9/15/20 10:13:50 AM 80 B ajp-nio-8009-Acceptor-0
  Type(jdk.ThreadAllocationStatistics) 9/15/20 10:13:50 AM 22.9 KiB ajp-nio-8009-ClientPoller-1
Type(jdk.ConcurrentModeFailure): 0
Type(jdk.BooleanFlag): 799
  Type(jdk.BooleanFlag) 9/15/20 10:18:50 AM UseCompressedOops true Ergonomic null
  Type(jdk.BooleanFlag) 9/15/20 10:18:50 AM UseCompressedClassPointers true Ergonomic null
  Type(jdk.BooleanFlag) 9/15/20 10:18:50 AM CheckCompressedOops true Default null
  Type(jdk.BooleanFlag) 9/15/20 10:18:50 AM AssumeMP false Default null
  Type(jdk.BooleanFlag) 9/15/20 10:18:50 AM UseMembar false Default null
  Type(jdk.BooleanFlag) 9/15/20 10:18:50 AM CleanChunkPoolAsync true Default null
  Type(jdk.BooleanFlag) 9/15/20 10:18:50 AM JavaMonitorsInStackTrace true Default null
  Type(jdk.BooleanFlag) 9/15/20 10:18:50 AM UseLargePages false Default null
  Type(jdk.BooleanFlag) 9/15/20 10:18:50 AM UseLargePagesIndividualAllocation false Ergonomic null
  Type(jdk.BooleanFlag) 9/15/20 10:18:50 AM LargePagesIndividualAllocationInjectError false Default null
  Type(jdk.BooleanFlag) 9/15/20 10:18:50 AM UseLargePagesInMetaspace false Default null
  Type(jdk.BooleanFlag) 9/15/20 10:18:50 AM TracePageSizes false Default null
Type(jdk.UnsignedLongFlagChanged): 0
Type(jdk.JavaMonitorWait): 82
  Type(jdk.JavaMonitorWait) 9/15/20 10:14:38 AM 9/15/20 10:14:40 AM RMI TCP Connection(idle) org.openjdk.jmc.common.util.MCStackTrace@6cc3966d com.sun.jmx.remote.internal.ArrayNotificationBuffer Service Thread 1 min false 0x2D49DF90
  Type(jdk.JavaMonitorWait) 9/15/20 10:17:29 AM 9/15/20 10:17:30 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@35c94420 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:17:30 AM 9/15/20 10:17:31 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@35c94420 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:17:31 AM 9/15/20 10:17:32 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@35c94420 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:17:32 AM 9/15/20 10:17:33 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@35c94420 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:17:33 AM 9/15/20 10:17:34 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@35c94420 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:17:34 AM 9/15/20 10:17:35 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@35c94420 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:17:35 AM 9/15/20 10:17:36 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@35c94420 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:17:36 AM 9/15/20 10:17:37 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@35c94420 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:17:37 AM 9/15/20 10:17:38 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@35c94420 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:17:38 AM 9/15/20 10:17:39 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@35c94420 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:17:39 AM 9/15/20 10:17:40 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@35c94420 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
Type(jdk.JavaMonitorWait): 10
  Type(jdk.JavaMonitorWait) 9/15/20 10:14:38 AM 9/15/20 10:14:40 AM JMX server connection timeout 73 org.openjdk.jmc.common.util.MCStackTrace@f0241226 int[] RMI TCP Connection(idle) 2 min false 0x2D49F7A0
  Type(jdk.JavaMonitorWait) 9/15/20 10:14:40 AM 9/15/20 10:15:40 AM JMX server connection timeout 73 org.openjdk.jmc.common.util.MCStackTrace@f0241226 int[] RMI TCP Connection(5)-127.0.0.1 2 min false 0x2D49F7A0
  Type(jdk.JavaMonitorWait) 9/15/20 10:15:40 AM 9/15/20 10:16:40 AM JMX server connection timeout 73 org.openjdk.jmc.common.util.MCStackTrace@f0241226 int[] RMI TCP Connection(5)-127.0.0.1 2 min false 0x2D49F7A0
  Type(jdk.JavaMonitorWait) 9/15/20 10:16:40 AM 9/15/20 10:17:40 AM JMX server connection timeout 73 org.openjdk.jmc.common.util.MCStackTrace@f0241226 int[] RMI TCP Connection(5)-127.0.0.1 2 min false 0x2D49F7A0
  Type(jdk.JavaMonitorWait) 9/15/20 10:17:40 AM 9/15/20 10:17:48 AM JMX server connection timeout 73 org.openjdk.jmc.common.util.MCStackTrace@f0241226 int[] RMI TCP Connection(5)-127.0.0.1 2 min false 0x2D49F7A0
  Type(jdk.JavaMonitorWait) 9/15/20 10:17:48 AM 9/15/20 10:18:01 AM JMX server connection timeout 73 org.openjdk.jmc.common.util.MCStackTrace@f0241226 int[] RMI TCP Connection(8)-127.0.0.1 2 min false 0x2D49F7A0
  Type(jdk.JavaMonitorWait) 9/15/20 10:18:01 AM 9/15/20 10:18:08 AM JMX server connection timeout 73 org.openjdk.jmc.common.util.MCStackTrace@f0241226 int[] RMI TCP Connection(5)-127.0.0.1 2 min false 0x2D49F7A0
  Type(jdk.JavaMonitorWait) 9/15/20 10:18:08 AM 9/15/20 10:18:15 AM JMX server connection timeout 73 org.openjdk.jmc.common.util.MCStackTrace@f0241226 int[] RMI TCP Connection(5)-127.0.0.1 2 min false 0x2D49F7A0
  Type(jdk.JavaMonitorWait) 9/15/20 10:18:15 AM 9/15/20 10:18:38 AM JMX server connection timeout 73 org.openjdk.jmc.common.util.MCStackTrace@f0241226 int[] RMI TCP Connection(5)-127.0.0.1 2 min false 0x2D49F7A0
  Type(jdk.JavaMonitorWait) 9/15/20 10:18:38 AM 9/15/20 10:18:49 AM JMX server connection timeout 73 org.openjdk.jmc.common.util.MCStackTrace@f0241226 int[] RMI TCP Connection(5)-127.0.0.1 2 min false 0x2D49F7A0
Type(jdk.JavaMonitorWait): 9
  Type(jdk.JavaMonitorWait) 9/15/20 10:14:40 AM 9/15/20 10:15:40 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@6cc3966d com.sun.jmx.remote.internal.ArrayNotificationBuffer Unknown Thread Name 1 min true 0x2D4A0E50
  Type(jdk.JavaMonitorWait) 9/15/20 10:15:40 AM 9/15/20 10:16:40 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@6cc3966d com.sun.jmx.remote.internal.ArrayNotificationBuffer Unknown Thread Name 1 min true 0x2D4A0E50
  Type(jdk.JavaMonitorWait) 9/15/20 10:16:40 AM 9/15/20 10:17:40 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@6cc3966d com.sun.jmx.remote.internal.ArrayNotificationBuffer Unknown Thread Name 1 min true 0x2D4A0E50
  Type(jdk.JavaMonitorWait) 9/15/20 10:17:40 AM 9/15/20 10:17:48 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@6cc3966d com.sun.jmx.remote.internal.ArrayNotificationBuffer Service Thread 1 min false 0x2D4A0E50
  Type(jdk.JavaMonitorWait) 9/15/20 10:17:48 AM 9/15/20 10:18:01 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@6cc3966d com.sun.jmx.remote.internal.ArrayNotificationBuffer Service Thread 1 min false 0x2D4A0DA0
  Type(jdk.JavaMonitorWait) 9/15/20 10:18:01 AM 9/15/20 10:18:08 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@6cc3966d com.sun.jmx.remote.internal.ArrayNotificationBuffer Service Thread 1 min false 0x2D4A0DA0
  Type(jdk.JavaMonitorWait) 9/15/20 10:18:08 AM 9/15/20 10:18:15 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@6cc3966d com.sun.jmx.remote.internal.ArrayNotificationBuffer Service Thread 1 min false 0x2D4A0DA0
  Type(jdk.JavaMonitorWait) 9/15/20 10:18:15 AM 9/15/20 10:18:38 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@6cc3966d com.sun.jmx.remote.internal.ArrayNotificationBuffer Service Thread 1 min false 0x2D4A0DA0
  Type(jdk.JavaMonitorWait) 9/15/20 10:18:38 AM 9/15/20 10:18:49 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@b2d1104f com.sun.jmx.remote.internal.ArrayNotificationBuffer Service Thread 1 min false 0x2D4A0DA0
Type(jdk.JavaMonitorWait): 7
  Type(jdk.JavaMonitorWait) 9/15/20 10:14:38 AM 9/15/20 10:14:40 AM Finalizer org.openjdk.jmc.common.util.MCStackTrace@6415c23e java.lang.ref.ReferenceQueue$Lock Reference Handler 0 s false 0x25B02310
  Type(jdk.JavaMonitorWait) 9/15/20 10:14:40 AM 9/15/20 10:17:48 AM Finalizer org.openjdk.jmc.common.util.MCStackTrace@6415c23e java.lang.ref.ReferenceQueue$Lock Reference Handler 0 s false 0x25B02310
  Type(jdk.JavaMonitorWait) 9/15/20 10:17:48 AM 9/15/20 10:18:01 AM Finalizer org.openjdk.jmc.common.util.MCStackTrace@6415c23e java.lang.ref.ReferenceQueue$Lock Reference Handler 0 s false 0x38422660
  Type(jdk.JavaMonitorWait) 9/15/20 10:18:01 AM 9/15/20 10:18:08 AM Finalizer org.openjdk.jmc.common.util.MCStackTrace@6415c23e java.lang.ref.ReferenceQueue$Lock Reference Handler 0 s false 0x38422660
  Type(jdk.JavaMonitorWait) 9/15/20 10:18:08 AM 9/15/20 10:18:15 AM Finalizer org.openjdk.jmc.common.util.MCStackTrace@6415c23e java.lang.ref.ReferenceQueue$Lock Reference Handler 0 s false 0x38422660
  Type(jdk.JavaMonitorWait) 9/15/20 10:18:15 AM 9/15/20 10:18:38 AM Finalizer org.openjdk.jmc.common.util.MCStackTrace@6415c23e java.lang.ref.ReferenceQueue$Lock Reference Handler 0 s false 0x38422660
  Type(jdk.JavaMonitorWait) 9/15/20 10:18:38 AM 9/15/20 10:18:49 AM Finalizer org.openjdk.jmc.common.util.MCStackTrace@6415c23e java.lang.ref.ReferenceQueue$Lock Reference Handler 0 s false 0x38422660
Type(jdk.JavaMonitorWait): 7
  Type(jdk.JavaMonitorWait) 9/15/20 10:14:38 AM 9/15/20 10:14:40 AM Reference Handler org.openjdk.jmc.common.util.MCStackTrace@40ddec97 java.lang.ref.Reference$Lock pool-3-thread-3 0 s false 0x25B02260
  Type(jdk.JavaMonitorWait) 9/15/20 10:14:40 AM 9/15/20 10:17:48 AM Reference Handler org.openjdk.jmc.common.util.MCStackTrace@1b34e897 java.lang.ref.Reference$Lock pool-3-thread-2 0 s false 0x25B02260
  Type(jdk.JavaMonitorWait) 9/15/20 10:17:48 AM 9/15/20 10:18:01 AM Reference Handler org.openjdk.jmc.common.util.MCStackTrace@1b34e897 java.lang.ref.Reference$Lock pool-3-thread-5 0 s false 0x25B021B0
  Type(jdk.JavaMonitorWait) 9/15/20 10:18:01 AM 9/15/20 10:18:08 AM Reference Handler org.openjdk.jmc.common.util.MCStackTrace@1b34e897 java.lang.ref.Reference$Lock pool-3-thread-5 0 s false 0x25B02100
  Type(jdk.JavaMonitorWait) 9/15/20 10:18:08 AM 9/15/20 10:18:15 AM Reference Handler org.openjdk.jmc.common.util.MCStackTrace@1b34e897 java.lang.ref.Reference$Lock pool-3-thread-3 0 s false 0x25B02100
  Type(jdk.JavaMonitorWait) 9/15/20 10:18:15 AM 9/15/20 10:18:38 AM Reference Handler org.openjdk.jmc.common.util.MCStackTrace@1b34e897 java.lang.ref.Reference$Lock pool-3-thread-2 0 s false 0x25B02100
  Type(jdk.JavaMonitorWait) 9/15/20 10:18:38 AM 9/15/20 10:18:49 AM Reference Handler org.openjdk.jmc.common.util.MCStackTrace@1b34e897 java.lang.ref.Reference$Lock pool-3-thread-4 0 s false 0x25B02100
Type(jdk.JavaMonitorWait): 220
  Type(jdk.JavaMonitorWait) 9/15/20 10:13:50 AM 9/15/20 10:13:51 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@42450000 java.util.TaskQueue null 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:13:51 AM 9/15/20 10:13:52 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@42450000 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:13:52 AM 9/15/20 10:13:53 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@42450000 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:13:53 AM 9/15/20 10:13:54 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@42450000 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:13:54 AM 9/15/20 10:13:55 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@42450000 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:13:55 AM 9/15/20 10:13:56 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@42450000 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:13:56 AM 9/15/20 10:13:57 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@42450000 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:13:57 AM 9/15/20 10:13:58 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@42450000 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:13:58 AM 9/15/20 10:13:59 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@42450000 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:13:59 AM 9/15/20 10:14:00 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@42450000 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:14:00 AM 9/15/20 10:14:01 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@42450000 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
  Type(jdk.JavaMonitorWait) 9/15/20 10:14:01 AM 9/15/20 10:14:02 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@42450000 java.util.TaskQueue Unknown Thread Name 1 s true 0x25B06670
Type(jdk.ClassUnload): 0
Type(jdk.ClassLoadingStatistics): 299
  Type(jdk.ClassLoadingStatistics) 9/15/20 10:13:51 AM 7,156 0
  Type(jdk.ClassLoadingStatistics) 9/15/20 10:13:52 AM 7,156 0
  Type(jdk.ClassLoadingStatistics) 9/15/20 10:13:53 AM 7,156 0
  Type(jdk.ClassLoadingStatistics) 9/15/20 10:13:54 AM 7,156 0
  Type(jdk.ClassLoadingStatistics) 9/15/20 10:13:55 AM 7,156 0
  Type(jdk.ClassLoadingStatistics) 9/15/20 10:13:56 AM 7,156 0
  Type(jdk.ClassLoadingStatistics) 9/15/20 10:13:57 AM 7,156 0
  Type(jdk.ClassLoadingStatistics) 9/15/20 10:13:58 AM 7,157 0
  Type(jdk.ClassLoadingStatistics) 9/15/20 10:13:59 AM 7,157 0
  Type(jdk.ClassLoadingStatistics) 9/15/20 10:14:00 AM 7,157 0
  Type(jdk.ClassLoadingStatistics) 9/15/20 10:14:01 AM 7,157 0
  Type(jdk.ClassLoadingStatistics) 9/15/20 10:14:02 AM 7,157 0
Type(jdk.JavaThreadStatistics): 299
  Type(jdk.JavaThreadStatistics) 9/15/20 10:13:51 AM 53 48 62 53
  Type(jdk.JavaThreadStatistics) 9/15/20 10:13:52 AM 53 48 62 53
  Type(jdk.JavaThreadStatistics) 9/15/20 10:13:53 AM 53 48 62 53
  Type(jdk.JavaThreadStatistics) 9/15/20 10:13:54 AM 53 48 62 53
  Type(jdk.JavaThreadStatistics) 9/15/20 10:13:55 AM 53 48 62 53
  Type(jdk.JavaThreadStatistics) 9/15/20 10:13:56 AM 53 48 62 53
  Type(jdk.JavaThreadStatistics) 9/15/20 10:13:57 AM 53 48 62 53
  Type(jdk.JavaThreadStatistics) 9/15/20 10:13:58 AM 53 48 62 53
  Type(jdk.JavaThreadStatistics) 9/15/20 10:13:59 AM 53 48 62 53
  Type(jdk.JavaThreadStatistics) 9/15/20 10:14:00 AM 53 48 62 53
  Type(jdk.JavaThreadStatistics) 9/15/20 10:14:01 AM 53 48 62 53
  Type(jdk.JavaThreadStatistics) 9/15/20 10:14:02 AM 53 48 62 53
Type(jdk.GCReferenceStatistics): 40
  Type(jdk.GCReferenceStatistics) 9/15/20 10:14:38 AM 11 Soft reference 0 null
  Type(jdk.GCReferenceStatistics) 9/15/20 10:14:38 AM 11 Weak reference 1,640 null
  Type(jdk.GCReferenceStatistics) 9/15/20 10:14:38 AM 11 Final reference 7,619 null
  Type(jdk.GCReferenceStatistics) 9/15/20 10:14:38 AM 11 Phantom reference 15 null
  Type(jdk.GCReferenceStatistics) 9/15/20 10:14:40 AM 12 Soft reference 0 null
  Type(jdk.GCReferenceStatistics) 9/15/20 10:14:40 AM 12 Weak reference 552 null
  Type(jdk.GCReferenceStatistics) 9/15/20 10:14:40 AM 12 Final reference 850 null
  Type(jdk.GCReferenceStatistics) 9/15/20 10:14:40 AM 12 Phantom reference 21 null
  Type(jdk.GCReferenceStatistics) 9/15/20 10:14:40 AM 13 Soft reference 0 null
  Type(jdk.GCReferenceStatistics) 9/15/20 10:14:40 AM 13 Weak reference 1,340 null
  Type(jdk.GCReferenceStatistics) 9/15/20 10:14:40 AM 13 Final reference 567 null
  Type(jdk.GCReferenceStatistics) 9/15/20 10:14:40 AM 13 Phantom reference 39 null
Type(jdk.DataLoss): 0
Type(jdk.PromotionFailed): 0
Type(http://www.oracle.com/hotspot/jdk/java/x509_certificate): 0
Type(jdk.StringFlag): 27
  Type(jdk.StringFlag) 9/15/20 10:18:50 AM DeoptimizeOnlyAt  Default null
  Type(jdk.StringFlag) 9/15/20 10:18:50 AM OnError  Default null
  Type(jdk.StringFlag) 9/15/20 10:18:50 AM OnOutOfMemoryError  Default null
  Type(jdk.StringFlag) 9/15/20 10:18:50 AM HeapDumpPath  Default null
  Type(jdk.StringFlag) 9/15/20 10:18:50 AM NativeMemoryTracking off Default null
  Type(jdk.StringFlag) 9/15/20 10:18:50 AM SyncKnobs  Default null
  Type(jdk.StringFlag) 9/15/20 10:18:50 AM TraceJVMTI  Default null
  Type(jdk.StringFlag) 9/15/20 10:18:50 AM ErrorFile  Default null
  Type(jdk.StringFlag) 9/15/20 10:18:50 AM AbortVMOnException  Default null
  Type(jdk.StringFlag) 9/15/20 10:18:50 AM AbortVMOnExceptionMessage  Default null
  Type(jdk.StringFlag) 9/15/20 10:18:50 AM SuppressErrorAt  Default null
  Type(jdk.StringFlag) 9/15/20 10:18:50 AM CompileOnly  Default null
Type(jdk.ExceptionStatistics): 299
  Type(jdk.ExceptionStatistics) 9/15/20 10:13:51 AM JFR request timer 11,047
  Type(jdk.ExceptionStatistics) 9/15/20 10:13:52 AM JFR request timer 11,047
  Type(jdk.ExceptionStatistics) 9/15/20 10:13:53 AM JFR request timer 11,047
  Type(jdk.ExceptionStatistics) 9/15/20 10:13:54 AM JFR request timer 11,047
  Type(jdk.ExceptionStatistics) 9/15/20 10:13:55 AM JFR request timer 11,047
  Type(jdk.ExceptionStatistics) 9/15/20 10:13:56 AM JFR request timer 11,047
  Type(jdk.ExceptionStatistics) 9/15/20 10:13:57 AM JFR request timer 11,047
  Type(jdk.ExceptionStatistics) 9/15/20 10:13:58 AM JFR request timer 11,047
  Type(jdk.ExceptionStatistics) 9/15/20 10:13:59 AM JFR request timer 11,047
  Type(jdk.ExceptionStatistics) 9/15/20 10:14:00 AM JFR request timer 11,047
  Type(jdk.ExceptionStatistics) 9/15/20 10:14:01 AM JFR request timer 11,047
  Type(jdk.ExceptionStatistics) 9/15/20 10:14:02 AM JFR request timer 11,047
Type(jdk.ExecutionSampling): 0
Type(jdk.YoungGarbageCollection): 8
  Type(jdk.YoungGarbageCollection) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM 11 1 null
  Type(jdk.YoungGarbageCollection) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 12 1 null
  Type(jdk.YoungGarbageCollection) 9/15/20 10:17:48 AM 9/15/20 10:17:48 AM 14 1 null
  Type(jdk.YoungGarbageCollection) 9/15/20 10:18:01 AM 9/15/20 10:18:01 AM 15 1 null
  Type(jdk.YoungGarbageCollection) 9/15/20 10:18:08 AM 9/15/20 10:18:08 AM 16 1 null
  Type(jdk.YoungGarbageCollection) 9/15/20 10:18:15 AM 9/15/20 10:18:15 AM 17 1 null
  Type(jdk.YoungGarbageCollection) 9/15/20 10:18:38 AM 9/15/20 10:18:38 AM 18 2 null
  Type(jdk.YoungGarbageCollection) 9/15/20 10:18:48 AM 9/15/20 10:18:48 AM 19 1 null
Type(jdk.CPUInformation): 1
  Type(jdk.CPUInformation) 9/15/20 10:18:50 AM Intel Sandy Bridge (HT) SSE SSE2 SSE3 SSSE3 SSE4.1 SSE4.2 Core Intel64 Brand:       Intel(R) Core(TM) i7-2760QM CPU @ 2.40GHz, Vendor: GenuineIntel
Family: Sandy Bridge (0x6), Model: Sandy Bridge (0x2a), Stepping: 0x7
Ext. family: 0x0, Ext. model: 0x2, Type: 0x0, Signature: 0x000206a7
Features: ebx: 0x04100800, ecx: 0x1fbae3ff, edx: 0xbfebfbff
Ext. features: eax: 0x00000000, ebx: 0x00000000, ecx: 0x00000001, edx: 0x28100800
Supports: On-Chip FPU, Virtual Mode Extensions, Debugging Extensions, Page Size Extensions, Time Stamp Counter, Model Specific Registers, Physical Address Extension, Machine Check Exceptions, CMPXCHG8B Instruction, On-Chip APIC, Fast System Call, Memory Type Range Registers, Page Global Enable, Machine Check Architecture, Conditional Mov Instruction, Page Attribute Table, 36-bit Page Size Extension, CLFLUSH Instruction, Debug Trace Store feature, ACPI registers in MSR space, Intel Architecture MMX Technology, Fast Float Point Save and Restore, Streaming SIMD extensions, Streaming SIMD extensions 2, Self-Snoop, Hyper Threading, Thermal Monitor, Streaming SIMD Extensions 3, PCLMULQDQ, 64-bit DS Area, MONITOR/MWAIT instructions, CPL Qualified Debug Store, Virtual Machine Extensions, Safer Mode Extensions, Enhanced Intel SpeedStep technology, Thermal Monitor 2, Supplemental Streaming SIMD Extensions 3, CMPXCHG16B, xTPR Update Control, Perfmon and Debug Capability, Process-context identifiers, Streaming SIMD extensions 4.1, Streaming SIMD extensions 4.2, x2APIC, Popcount instruction, TSC-Deadline, AESNI, XSAVE, OSXSAVE, AVX, LAHF/SAHF instruction support, SYSCALL/SYSRET, Execute Disable Bit, RDTSCP, Intel 64 Architecture, Invariant TSC 1 4 8 null
Type(jdk.YoungGenerationConfiguration): 1
  Type(jdk.YoungGenerationConfiguration) 9/15/20 10:18:50 AM 170 MiB 2.66 GiB 2 null
Type(jdk.ObjectAllocationInNewTLAB): 5095
  Type(jdk.ObjectAllocationInNewTLAB) 9/15/20 10:13:51 AM JFR request timer org.openjdk.jmc.common.util.MCStackTrace@507109f7 java.lang.String 24 B 20.5 MiB null
  Type(jdk.ObjectAllocationInNewTLAB) 9/15/20 10:14:11 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@31ba8086 java.lang.ThreadLocal$ThreadLocalMap$Entry[] 80 B 20.5 MiB null
  Type(jdk.ObjectAllocationInNewTLAB) 9/15/20 10:14:11 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@cf86e7f0 byte[] 168 B 20.5 MiB null
  Type(jdk.ObjectAllocationInNewTLAB) 9/15/20 10:14:11 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@6dcabe5f byte[] 304 B 20.5 MiB null
  Type(jdk.ObjectAllocationInNewTLAB) 9/15/20 10:14:11 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@9ae3bd0c byte[] 400 B 20.5 MiB null
  Type(jdk.ObjectAllocationInNewTLAB) 9/15/20 10:14:12 AM Neo4jDriverIO-2-1 org.openjdk.jmc.common.util.MCStackTrace@ddc7d980 byte[] 232 B 2.28 MiB null
  Type(jdk.ObjectAllocationInNewTLAB) 9/15/20 10:14:12 AM Neo4jDriverIO-2-1 org.openjdk.jmc.common.util.MCStackTrace@f1473a7e char[] 152 B 2.28 MiB null
  Type(jdk.ObjectAllocationInNewTLAB) 9/15/20 10:14:12 AM Neo4jDriverIO-2-1 org.openjdk.jmc.common.util.MCStackTrace@bae7f6d char[] 176 B 2.28 MiB null
  Type(jdk.ObjectAllocationInNewTLAB) 9/15/20 10:14:12 AM Neo4jDriverIO-2-1 org.openjdk.jmc.common.util.MCStackTrace@2187b62d java.lang.StringBuilder 24 B 2.28 MiB null
  Type(jdk.ObjectAllocationInNewTLAB) 9/15/20 10:14:12 AM Neo4jDriverIO-2-1 org.openjdk.jmc.common.util.MCStackTrace@ac52bb7f char[] 168 B 2.28 MiB null
  Type(jdk.ObjectAllocationInNewTLAB) 9/15/20 10:14:12 AM Neo4jDriverIO-2-1 org.openjdk.jmc.common.util.MCStackTrace@46af58aa byte[] 2.75 KiB 2.28 MiB null
  Type(jdk.ObjectAllocationInNewTLAB) 9/15/20 10:14:12 AM Neo4jDriverIO-2-1 org.openjdk.jmc.common.util.MCStackTrace@9a7b3f5e char[] 152 B 2.28 MiB null
Type(jdk.CodeSweeperStatistics): 2
  Type(jdk.CodeSweeperStatistics) 9/15/20 10:13:50 AM 23 858 76.668 ms 1.216 ms 10.302 ms null
  Type(jdk.CodeSweeperStatistics) 9/15/20 10:18:50 AM 85 3,239 1.133 s 15.519 ms 29.041 ms null
Type(jdk.CodeCacheStatistics): 2
  Type(jdk.CodeCacheStatistics) 9/15/20 10:13:50 AM 0x027A0000 0x03AE0000 0x117A0000 5,447 4,958 409 221 MiB 0
  Type(jdk.CodeCacheStatistics) 9/15/20 10:18:50 AM 0x027A0000 0x05CC0000 0x117A0000 12,961 12,377 502 187 MiB 0
Type(jdk.FileRead): 0
Type(jdk.OSInformation): 1
  Type(jdk.OSInformation) 9/15/20 10:18:50 AM OS: Windows 7 , 64 bit Build 7601 (6.1.7601.24545)
 null
Type(jdk.LongFlagChanged): 0
Type(jdk.InitialEnvironmentVariable): 75
  Type(jdk.InitialEnvironmentVariable) 9/15/20 10:18:50 AM JAVA_OPTS -Dvisualvm.id=68841956335182 -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:64828,suspend=y,server=n -javaagent:C:\Users\CORRAD~1\AppData\Local\Temp\captureAgent3480jars\debugger-agent.jar -javaagent:C:\Users\CORRAD~1\AppData\Local\Temp\groovyHotSwap1719jars\gragent.jar -XX:+UnlockCommercialFeatures -XX:+FlightRecorder  -Dcom.sun.management.jmxremote= -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false "-Dcom.sun.management.jmxremote.password.file=C:\Users\Corrado Mio\AppData\Local\JetBrains\IntelliJIdea2020.2\tomcat\Unnamed_splproject2_6_3\jmxremote.password" "-Dcom.sun.management.jmxremote.access.file=C:\Users\Corrado Mio\AppData\Local\JetBrains\IntelliJIdea2020.2\tomcat\Unnamed_splproject2_6_3\jmxremote.access" -Djava.rmi.server.hostname=127.0.0.1 "-Djdk.tls.ephemeralDHKeySize=2048" -Djava.protocol.handler.pkgs=org.apache.catalina.webresources
  Type(jdk.InitialEnvironmentVariable) 9/15/20 10:18:50 AM ACTION start
  Type(jdk.InitialEnvironmentVariable) 9/15/20 10:18:50 AM ALLUSERSPROFILE C:\ProgramData
  Type(jdk.InitialEnvironmentVariable) 9/15/20 10:18:50 AM APPDATA C:\Users\Corrado Mio\AppData\Roaming
  Type(jdk.InitialEnvironmentVariable) 9/15/20 10:18:50 AM CATALINA_BASE C:\Users\Corrado Mio\AppData\Local\JetBrains\IntelliJIdea2020.2\tomcat\Unnamed_splproject2_6_3
  Type(jdk.InitialEnvironmentVariable) 9/15/20 10:18:50 AM CATALINA_HOME D:\Java\Tomcat-8.5.37
  Type(jdk.InitialEnvironmentVariable) 9/15/20 10:18:50 AM CATALINA_OPTS -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false
  Type(jdk.InitialEnvironmentVariable) 9/15/20 10:18:50 AM CATALINA_TMPDIR D:\Java\Tomcat-8.5.37\temp
  Type(jdk.InitialEnvironmentVariable) 9/15/20 10:18:50 AM CLASSPATH D:\Java\Tomcat-8.5.37\bin\bootstrap.jar;D:\Java\Tomcat-8.5.37\bin\tomcat-juli.jar
  Type(jdk.InitialEnvironmentVariable) 9/15/20 10:18:50 AM CommonProgramFiles C:\Program Files\Common Files
  Type(jdk.InitialEnvironmentVariable) 9/15/20 10:18:50 AM CommonProgramFiles(x86) C:\Program Files (x86)\Common Files
  Type(jdk.InitialEnvironmentVariable) 9/15/20 10:18:50 AM CommonProgramW6432 C:\Program Files\Common Files
Type(jdk.ThreadSleep): 299
  Type(jdk.ThreadSleep) 9/15/20 10:13:51 AM 9/15/20 10:13:52 AM DelayedTimer org.openjdk.jmc.common.util.MCStackTrace@3f771250 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:52 AM 9/15/20 10:13:53 AM DelayedTimer org.openjdk.jmc.common.util.MCStackTrace@3f771250 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:53 AM 9/15/20 10:13:53 AM DelayedTimer org.openjdk.jmc.common.util.MCStackTrace@3f771250 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:53 AM 9/15/20 10:13:54 AM DelayedTimer org.openjdk.jmc.common.util.MCStackTrace@3f771250 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:54 AM 9/15/20 10:13:55 AM DelayedTimer org.openjdk.jmc.common.util.MCStackTrace@3f771250 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:55 AM 9/15/20 10:13:56 AM DelayedTimer org.openjdk.jmc.common.util.MCStackTrace@3f771250 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:56 AM 9/15/20 10:13:57 AM DelayedTimer org.openjdk.jmc.common.util.MCStackTrace@3f771250 999 ms
  Type(jdk.ThreadSleep) 9/15/20 10:13:57 AM 9/15/20 10:13:58 AM DelayedTimer org.openjdk.jmc.common.util.MCStackTrace@3f771250 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:58 AM 9/15/20 10:13:59 AM DelayedTimer org.openjdk.jmc.common.util.MCStackTrace@3f771250 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:59 AM 9/15/20 10:14:00 AM DelayedTimer org.openjdk.jmc.common.util.MCStackTrace@3f771250 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:14:00 AM 9/15/20 10:14:01 AM DelayedTimer org.openjdk.jmc.common.util.MCStackTrace@3f771250 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:14:01 AM 9/15/20 10:14:02 AM DelayedTimer org.openjdk.jmc.common.util.MCStackTrace@3f771250 1 s
Type(jdk.ThreadSleep): 187
  Type(jdk.ThreadSleep) 9/15/20 10:13:50 AM 9/15/20 10:13:51 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:51 AM 9/15/20 10:13:52 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:52 AM 9/15/20 10:13:53 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:53 AM 9/15/20 10:13:54 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:54 AM 9/15/20 10:13:55 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:55 AM 9/15/20 10:13:56 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:56 AM 9/15/20 10:13:57 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:57 AM 9/15/20 10:13:58 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:58 AM 9/15/20 10:13:59 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:59 AM 9/15/20 10:14:00 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:14:00 AM 9/15/20 10:14:01 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:14:01 AM 9/15/20 10:14:02 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
Type(jdk.ThreadSleep): 299
  Type(jdk.ThreadSleep) 9/15/20 10:13:50 AM 9/15/20 10:13:51 AM http-nio-8080-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:51 AM 9/15/20 10:13:52 AM http-nio-8080-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:52 AM 9/15/20 10:13:53 AM http-nio-8080-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:53 AM 9/15/20 10:13:54 AM http-nio-8080-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:54 AM 9/15/20 10:13:55 AM http-nio-8080-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:55 AM 9/15/20 10:13:56 AM http-nio-8080-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:56 AM 9/15/20 10:13:57 AM http-nio-8080-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:57 AM 9/15/20 10:13:58 AM http-nio-8080-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:58 AM 9/15/20 10:13:59 AM http-nio-8080-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:13:59 AM 9/15/20 10:14:00 AM http-nio-8080-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:14:00 AM 9/15/20 10:14:01 AM http-nio-8080-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:14:01 AM 9/15/20 10:14:02 AM http-nio-8080-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
Type(jdk.ThreadSleep): 29
  Type(jdk.ThreadSleep) 9/15/20 10:13:56 AM 9/15/20 10:14:05 AM ContainerBackgroundProcessor[StandardEngine[Catalina]] org.openjdk.jmc.common.util.MCStackTrace@d66ef887 10 s
  Type(jdk.ThreadSleep) 9/15/20 10:14:05 AM 9/15/20 10:14:15 AM ContainerBackgroundProcessor[StandardEngine[Catalina]] org.openjdk.jmc.common.util.MCStackTrace@d66ef887 10 s
  Type(jdk.ThreadSleep) 9/15/20 10:14:15 AM 9/15/20 10:14:25 AM ContainerBackgroundProcessor[StandardEngine[Catalina]] org.openjdk.jmc.common.util.MCStackTrace@d66ef887 10 s
  Type(jdk.ThreadSleep) 9/15/20 10:14:25 AM 9/15/20 10:14:35 AM ContainerBackgroundProcessor[StandardEngine[Catalina]] org.openjdk.jmc.common.util.MCStackTrace@d66ef887 10 s
  Type(jdk.ThreadSleep) 9/15/20 10:14:35 AM 9/15/20 10:14:45 AM ContainerBackgroundProcessor[StandardEngine[Catalina]] org.openjdk.jmc.common.util.MCStackTrace@d66ef887 10 s
  Type(jdk.ThreadSleep) 9/15/20 10:14:45 AM 9/15/20 10:14:55 AM ContainerBackgroundProcessor[StandardEngine[Catalina]] org.openjdk.jmc.common.util.MCStackTrace@d66ef887 10 s
  Type(jdk.ThreadSleep) 9/15/20 10:14:55 AM 9/15/20 10:15:05 AM ContainerBackgroundProcessor[StandardEngine[Catalina]] org.openjdk.jmc.common.util.MCStackTrace@d66ef887 10 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:05 AM 9/15/20 10:15:15 AM ContainerBackgroundProcessor[StandardEngine[Catalina]] org.openjdk.jmc.common.util.MCStackTrace@d66ef887 10 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:15 AM 9/15/20 10:15:25 AM ContainerBackgroundProcessor[StandardEngine[Catalina]] org.openjdk.jmc.common.util.MCStackTrace@d66ef887 10 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:25 AM 9/15/20 10:15:35 AM ContainerBackgroundProcessor[StandardEngine[Catalina]] org.openjdk.jmc.common.util.MCStackTrace@d66ef887 10 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:35 AM 9/15/20 10:15:45 AM ContainerBackgroundProcessor[StandardEngine[Catalina]] org.openjdk.jmc.common.util.MCStackTrace@d66ef887 10 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:45 AM 9/15/20 10:15:55 AM ContainerBackgroundProcessor[StandardEngine[Catalina]] org.openjdk.jmc.common.util.MCStackTrace@d66ef887 10 s
Type(jdk.ThreadSleep): 19
  Type(jdk.ThreadSleep) 9/15/20 10:13:55 AM 9/15/20 10:14:10 AM FileWatchdog org.openjdk.jmc.common.util.MCStackTrace@ede1f961 15 s
  Type(jdk.ThreadSleep) 9/15/20 10:14:10 AM 9/15/20 10:14:25 AM FileWatchdog org.openjdk.jmc.common.util.MCStackTrace@ede1f961 15 s
  Type(jdk.ThreadSleep) 9/15/20 10:14:25 AM 9/15/20 10:14:40 AM FileWatchdog org.openjdk.jmc.common.util.MCStackTrace@ede1f961 15 s
  Type(jdk.ThreadSleep) 9/15/20 10:14:40 AM 9/15/20 10:14:55 AM FileWatchdog org.openjdk.jmc.common.util.MCStackTrace@ede1f961 15 s
  Type(jdk.ThreadSleep) 9/15/20 10:14:55 AM 9/15/20 10:15:10 AM FileWatchdog org.openjdk.jmc.common.util.MCStackTrace@ede1f961 15 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:10 AM 9/15/20 10:15:25 AM FileWatchdog org.openjdk.jmc.common.util.MCStackTrace@ede1f961 15 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:25 AM 9/15/20 10:15:40 AM FileWatchdog org.openjdk.jmc.common.util.MCStackTrace@ede1f961 15 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:40 AM 9/15/20 10:15:55 AM FileWatchdog org.openjdk.jmc.common.util.MCStackTrace@ede1f961 15 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:55 AM 9/15/20 10:16:10 AM FileWatchdog org.openjdk.jmc.common.util.MCStackTrace@ede1f961 15 s
  Type(jdk.ThreadSleep) 9/15/20 10:16:10 AM 9/15/20 10:16:25 AM FileWatchdog org.openjdk.jmc.common.util.MCStackTrace@ede1f961 15 s
  Type(jdk.ThreadSleep) 9/15/20 10:16:25 AM 9/15/20 10:16:39 AM FileWatchdog org.openjdk.jmc.common.util.MCStackTrace@ede1f961 15 s
  Type(jdk.ThreadSleep) 9/15/20 10:16:39 AM 9/15/20 10:16:54 AM FileWatchdog org.openjdk.jmc.common.util.MCStackTrace@ede1f961 15 s
Type(jdk.ThreadSleep): 117
  Type(jdk.ThreadSleep) 9/15/20 10:15:46 AM 9/15/20 10:15:47 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:47 AM 9/15/20 10:15:48 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:48 AM 9/15/20 10:15:49 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:49 AM 9/15/20 10:15:50 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:50 AM 9/15/20 10:15:51 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:51 AM 9/15/20 10:15:52 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:52 AM 9/15/20 10:15:53 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:53 AM 9/15/20 10:15:54 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:54 AM 9/15/20 10:15:55 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:55 AM 9/15/20 10:15:56 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:56 AM 9/15/20 10:15:57 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
  Type(jdk.ThreadSleep) 9/15/20 10:15:57 AM 9/15/20 10:15:58 AM ajp-nio-8009-AsyncTimeout org.openjdk.jmc.common.util.MCStackTrace@1147c4d6 1 s
Type(jdk.UnsignedLongFlag): 194
  Type(jdk.UnsignedLongFlag) 9/15/20 10:18:50 AM HeapBaseMinAddress 2.15 ×10⁹ Default null
  Type(jdk.UnsignedLongFlag) 9/15/20 10:18:50 AM NUMAInterleaveGranularity 2.1 ×10⁶ Default null
  Type(jdk.UnsignedLongFlag) 9/15/20 10:18:50 AM NUMAChunkResizeWeight 20 Default null
  Type(jdk.UnsignedLongFlag) 9/15/20 10:18:50 AM NUMASpaceResizeRate 1.07 ×10⁹ Default null
  Type(jdk.UnsignedLongFlag) 9/15/20 10:18:50 AM NUMAPageScanRate 256 Default null
  Type(jdk.UnsignedLongFlag) 9/15/20 10:18:50 AM LargePageSizeInBytes 0 Default null
  Type(jdk.UnsignedLongFlag) 9/15/20 10:18:50 AM LargePageHeapSizeThreshold 134 ×10⁶ Default null
  Type(jdk.UnsignedLongFlag) 9/15/20 10:18:50 AM ErrorHandlerTest 0 Default null
  Type(jdk.UnsignedLongFlag) 9/15/20 10:18:50 AM SegmentedHeapDumpThreshold 2.15 ×10⁹ Default null
  Type(jdk.UnsignedLongFlag) 9/15/20 10:18:50 AM HeapDumpSegmentSize 1.07 ×10⁹ Default null
  Type(jdk.UnsignedLongFlag) 9/15/20 10:18:50 AM WarnOnStalledSpinLock 0 Default null
  Type(jdk.UnsignedLongFlag) 9/15/20 10:18:50 AM PreallocatedOutOfMemoryErrorCount 4 Default null
Type(http://www.oracle.com/hotspot/jdk/java/tls_handshake): 0
Type(jdk.ThreadEnd): 83
  Type(jdk.ThreadEnd) 9/15/20 10:16:39 AM HandshakeCompletedNotify-Thread HandshakeCompletedNotify-Thread
  Type(jdk.ThreadEnd) 9/15/20 10:16:45 AM HandshakeCompletedNotify-Thread HandshakeCompletedNotify-Thread
  Type(jdk.ThreadEnd) 9/15/20 10:16:46 AM HandshakeCompletedNotify-Thread HandshakeCompletedNotify-Thread
  Type(jdk.ThreadEnd) 9/15/20 10:16:48 AM HandshakeCompletedNotify-Thread HandshakeCompletedNotify-Thread
  Type(jdk.ThreadEnd) 9/15/20 10:16:49 AM HandshakeCompletedNotify-Thread HandshakeCompletedNotify-Thread
  Type(jdk.ThreadEnd) 9/15/20 10:16:49 AM Keep-Alive-Timer Keep-Alive-Timer
  Type(jdk.ThreadEnd) 9/15/20 10:16:49 AM HandshakeCompletedNotify-Thread HandshakeCompletedNotify-Thread
  Type(jdk.ThreadEnd) 9/15/20 10:16:51 AM HandshakeCompletedNotify-Thread HandshakeCompletedNotify-Thread
  Type(jdk.ThreadEnd) 9/15/20 10:16:52 AM HandshakeCompletedNotify-Thread HandshakeCompletedNotify-Thread
  Type(jdk.ThreadEnd) 9/15/20 10:16:53 AM HandshakeCompletedNotify-Thread HandshakeCompletedNotify-Thread
  Type(jdk.ThreadEnd) 9/15/20 10:16:53 AM HandshakeCompletedNotify-Thread HandshakeCompletedNotify-Thread
  Type(jdk.ThreadEnd) 9/15/20 10:16:55 AM HandshakeCompletedNotify-Thread HandshakeCompletedNotify-Thread
Type(jdk.G1GarbageCollection): 0
Type(jdk.ThreadPark): 395
  Type(jdk.ThreadPark) 9/15/20 10:14:42 AM 9/15/20 10:14:43 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@7f50a6a8 java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 0 s 0x5C332A6C0
  Type(jdk.ThreadPark) 9/15/20 10:14:43 AM 9/15/20 10:14:43 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@511549b1 java.util.concurrent.CompletableFuture$Signaller 0 s 0x72D45C540
  Type(jdk.ThreadPark) 9/15/20 10:14:43 AM 9/15/20 10:16:42 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@7f50a6a8 java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 0 s 0x5C332A6C0
  Type(jdk.ThreadPark) 9/15/20 10:16:42 AM 9/15/20 10:16:42 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@7372b255 java.util.concurrent.CompletableFuture$Signaller 0 s 0x72D9E1FE8
  Type(jdk.ThreadPark) 9/15/20 10:16:42 AM 9/15/20 10:16:42 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@a45117d java.util.concurrent.CompletableFuture$Signaller 0 s 0x751F9E450
  Type(jdk.ThreadPark) 9/15/20 10:16:42 AM 9/15/20 10:16:42 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@96a1d7ec java.util.concurrent.CompletableFuture$Signaller 0 s 0x751FA1BD0
  Type(jdk.ThreadPark) 9/15/20 10:16:42 AM 9/15/20 10:16:42 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@a45117d java.util.concurrent.CompletableFuture$Signaller 0 s 0x751FA6CE0
  Type(jdk.ThreadPark) 9/15/20 10:16:42 AM 9/15/20 10:16:42 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@a7e6b3bf java.util.concurrent.CompletableFuture$Signaller 0 s 0x751FC0658
  Type(jdk.ThreadPark) 9/15/20 10:16:43 AM 9/15/20 10:16:43 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@a80abfaf java.util.concurrent.CompletableFuture$Signaller 0 s 0x75201C490
  Type(jdk.ThreadPark) 9/15/20 10:16:43 AM 9/15/20 10:16:43 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@a80abfaf java.util.concurrent.CompletableFuture$Signaller 0 s 0x7529F0968
  Type(jdk.ThreadPark) 9/15/20 10:16:43 AM 9/15/20 10:16:43 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@a7e6b3bf java.util.concurrent.CompletableFuture$Signaller 0 s 0x7529F40E8
  Type(jdk.ThreadPark) 9/15/20 10:16:43 AM 9/15/20 10:16:43 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@a7e6b3bf java.util.concurrent.CompletableFuture$Signaller 0 s 0x752A45598
Type(jdk.ThreadPark): 64
  Type(jdk.ThreadPark) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@5396b597 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DD56370
  Type(jdk.ThreadPark) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@a461c225 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DD58130
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@7edf9eb4 java.util.concurrent.CompletableFuture$Signaller 0 s 0x720309FC8
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@752f52a2 java.util.concurrent.CompletableFuture$Signaller 0 s 0x720327150
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@e043e2aa java.util.concurrent.CompletableFuture$Signaller 0 s 0x720327268
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@ce7dad1b java.util.concurrent.CompletableFuture$Signaller 0 s 0x7203312D8
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@a646b992 java.util.concurrent.CompletableFuture$Signaller 0 s 0x720333A10
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@ade4699e java.util.concurrent.CompletableFuture$Signaller 0 s 0x720334A50
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@7f50a6a8 java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 0 s 0x718E58BA0
  Type(jdk.ThreadPark) 9/15/20 10:14:41 AM 9/15/20 10:14:41 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@11dc9fd6 java.util.concurrent.Semaphore$NonfairSync 0 s 0x71EA07B58
  Type(jdk.ThreadPark) 9/15/20 10:14:41 AM 9/15/20 10:14:41 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@491665f7 java.util.concurrent.Semaphore$NonfairSync 0 s 0x71C97C010
  Type(jdk.ThreadPark) 9/15/20 10:14:41 AM 9/15/20 10:14:41 AM pool-3-thread-6 org.openjdk.jmc.common.util.MCStackTrace@491665f7 java.util.concurrent.Semaphore$NonfairSync 0 s 0x71C97C010
Type(jdk.ThreadPark): 235
  Type(jdk.ThreadPark) 9/15/20 10:14:41 AM 9/15/20 10:14:41 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@696cd481 java.util.concurrent.Semaphore$NonfairSync 0 s 0x71EA62FF8
  Type(jdk.ThreadPark) 9/15/20 10:14:42 AM 9/15/20 10:14:42 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@7f50a6a8 java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 0 s 0x5C332A6C0
  Type(jdk.ThreadPark) 9/15/20 10:14:42 AM 9/15/20 10:14:42 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@7be4445 java.util.concurrent.CompletableFuture$Signaller 0 s 0x728A3C7A0
  Type(jdk.ThreadPark) 9/15/20 10:14:42 AM 9/15/20 10:14:42 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@53094c57 java.util.concurrent.CompletableFuture$Signaller 0 s 0x728A42648
  Type(jdk.ThreadPark) 9/15/20 10:14:42 AM 9/15/20 10:14:42 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@b4bb9263 java.util.concurrent.CompletableFuture$Signaller 0 s 0x728A43688
  Type(jdk.ThreadPark) 9/15/20 10:14:42 AM 9/15/20 10:14:42 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@d518a8f8 java.util.concurrent.CompletableFuture$Signaller 0 s 0x72A286B98
  Type(jdk.ThreadPark) 9/15/20 10:14:42 AM 9/15/20 10:14:42 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@633f336b java.util.concurrent.CompletableFuture$Signaller 0 s 0x72A2C8FA0
  Type(jdk.ThreadPark) 9/15/20 10:14:42 AM 9/15/20 10:14:42 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@317e449 java.util.concurrent.CompletableFuture$Signaller 0 s 0x72A2D69E0
  Type(jdk.ThreadPark) 9/15/20 10:14:42 AM 9/15/20 10:14:42 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@d518a8f8 java.util.concurrent.CompletableFuture$Signaller 0 s 0x72A2DA160
  Type(jdk.ThreadPark) 9/15/20 10:14:42 AM 9/15/20 10:14:42 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@633f336b java.util.concurrent.CompletableFuture$Signaller 0 s 0x72A2DD8C8
  Type(jdk.ThreadPark) 9/15/20 10:14:43 AM 9/15/20 10:14:43 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@7c40b0d8 java.util.concurrent.CompletableFuture$Signaller 0 s 0x72A3030D0
  Type(jdk.ThreadPark) 9/15/20 10:14:43 AM 9/15/20 10:14:43 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@af267fa5 java.util.concurrent.CompletableFuture$Signaller 0 s 0x72D0BFBF0
Type(jdk.ThreadPark): 386
  Type(jdk.ThreadPark) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@c683c7f7 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DE823A0
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@e5ad56ec java.util.concurrent.Semaphore$NonfairSync 0 s 0x720398C90
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@752f52a2 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DEC5FE0
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@4605705d java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DEC9BA0
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@11ec5077 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DECD580
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@ce7dad1b java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DED0730
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@a646b992 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DED2E68
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@ade4699e java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DED3EA8
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@ce7dad1b java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DED5708
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@ade4699e java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DED8E80
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@41eb3900 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DEDB5B8
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-7 org.openjdk.jmc.common.util.MCStackTrace@4988e90c java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DEDC5F8
Type(jdk.ThreadPark): 49
  Type(jdk.ThreadPark) 9/15/20 10:14:45 AM 9/15/20 10:14:45 AM RMI Scheduler(0) org.openjdk.jmc.common.util.MCStackTrace@6b32723b java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 3 h 30 min 0x5C15B96B0
  Type(jdk.ThreadPark) 9/15/20 10:14:45 AM 9/15/20 10:14:45 AM RMI Scheduler(0) org.openjdk.jmc.common.util.MCStackTrace@6b32723b java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 6 d 2 h 0x5C15B96B0
  Type(jdk.ThreadPark) 9/15/20 10:14:45 AM 9/15/20 10:14:46 AM RMI Scheduler(0) org.openjdk.jmc.common.util.MCStackTrace@6b32723b java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 d 13 h 0x5C15B96B0
  Type(jdk.ThreadPark) 9/15/20 10:14:46 AM 9/15/20 10:15:00 AM RMI Scheduler(0) org.openjdk.jmc.common.util.MCStackTrace@6b32723b java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 23 wk 6 d 0x5C15B96B0
  Type(jdk.ThreadPark) 9/15/20 10:15:00 AM 9/15/20 10:17:26 AM RMI Scheduler(0) org.openjdk.jmc.common.util.MCStackTrace@6b32723b java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 4 y 34 wk 0x5C15B96B0
  Type(jdk.ThreadPark) 9/15/20 10:18:05 AM 9/15/20 10:18:06 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@75f3734c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C157CF58
  Type(jdk.ThreadPark) 9/15/20 10:18:06 AM 9/15/20 10:18:07 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@75f3734c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C157CF58
  Type(jdk.ThreadPark) 9/15/20 10:18:07 AM 9/15/20 10:18:08 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@75f3734c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C157CF58
  Type(jdk.ThreadPark) 9/15/20 10:18:08 AM 9/15/20 10:18:09 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@75f3734c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C157CF58
  Type(jdk.ThreadPark) 9/15/20 10:18:09 AM 9/15/20 10:18:10 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@75f3734c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C157CF58
  Type(jdk.ThreadPark) 9/15/20 10:18:10 AM 9/15/20 10:18:11 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@75f3734c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C157CF58
  Type(jdk.ThreadPark) 9/15/20 10:18:11 AM 9/15/20 10:18:12 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@75f3734c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C157CF58
Type(jdk.ThreadPark): 315
  Type(jdk.ThreadPark) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@b893fe58 java.util.concurrent.CompletableFuture$Signaller 0 s 0x718FDF9E0
  Type(jdk.ThreadPark) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@b893fe58 java.util.concurrent.CompletableFuture$Signaller 0 s 0x718FEE460
  Type(jdk.ThreadPark) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@b706ce95 java.util.concurrent.CompletableFuture$Signaller 0 s 0x718FF3DA8
  Type(jdk.ThreadPark) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@b706ce95 java.util.concurrent.CompletableFuture$Signaller 0 s 0x718FFD650
  Type(jdk.ThreadPark) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@b706ce95 java.util.concurrent.CompletableFuture$Signaller 0 s 0x719006E68
  Type(jdk.ThreadPark) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@b706ce95 java.util.concurrent.CompletableFuture$Signaller 0 s 0x7190105B0
  Type(jdk.ThreadPark) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@add6adbd java.util.concurrent.CompletableFuture$Signaller 0 s 0x71C5081A0
  Type(jdk.ThreadPark) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@4de567cf java.util.concurrent.CompletableFuture$Signaller 0 s 0x71C552860
  Type(jdk.ThreadPark) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@7f50a6a8 java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 0 s 0x718E58BA0
  Type(jdk.ThreadPark) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@c683c7f7 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71C59A300
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@34b6fb2c java.util.concurrent.Semaphore$NonfairSync 0 s 0x720307708
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-1 org.openjdk.jmc.common.util.MCStackTrace@46315526 java.util.concurrent.CompletableFuture$Signaller 0 s 0x72039E3E8
Type(jdk.ThreadPark): 417
  Type(jdk.ThreadPark) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@c683c7f7 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71D8A65E8
  Type(jdk.ThreadPark) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@5396b597 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71D8A6690
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@d825a40e java.util.concurrent.Semaphore$NonfairSync 0 s 0x720307708
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@5411f9e3 java.util.concurrent.CompletableFuture$Signaller 0 s 0x72052FFE8
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@752f52a2 java.util.concurrent.CompletableFuture$Signaller 0 s 0x720532D18
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@4605705d java.util.concurrent.CompletableFuture$Signaller 0 s 0x7205366A0
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@ce7dad1b java.util.concurrent.CompletableFuture$Signaller 0 s 0x72054FAA0
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@a646b992 java.util.concurrent.CompletableFuture$Signaller 0 s 0x7205521D8
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@149de8d java.util.concurrent.CompletableFuture$Signaller 0 s 0x720553158
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@ade4699e java.util.concurrent.CompletableFuture$Signaller 0 s 0x720553218
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@ce7dad1b java.util.concurrent.CompletableFuture$Signaller 0 s 0x720554B58
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-2 org.openjdk.jmc.common.util.MCStackTrace@41eb3900 java.util.concurrent.CompletableFuture$Signaller 0 s 0x72055A8E0
Type(jdk.ThreadPark): 342
  Type(jdk.ThreadPark) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@c683c7f7 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DA15F08
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@e5ad56ec java.util.concurrent.Semaphore$NonfairSync 0 s 0x71DCFD370
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@752f52a2 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DA6F9F0
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@4605705d java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DA73480
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@ce7dad1b java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DA79AF0
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@a646b992 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DA7C148
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@ade4699e java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DA7D188
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@41eb3900 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DA84850
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@4988e90c java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DA85890
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@7f50a6a8 java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 0 s 0x718E58BA0
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:42 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@7f50a6a8 java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 0 s 0x5C332A6C0
  Type(jdk.ThreadPark) 9/15/20 10:14:42 AM 9/15/20 10:14:42 AM pool-3-thread-3 org.openjdk.jmc.common.util.MCStackTrace@5619d26f java.util.concurrent.CompletableFuture$Signaller 0 s 0x71B10B590
Type(jdk.ThreadPark): 360
  Type(jdk.ThreadPark) 9/15/20 10:13:51 AM 9/15/20 10:13:52 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@f8ef336c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C1753360
  Type(jdk.ThreadPark) 9/15/20 10:13:52 AM 9/15/20 10:13:53 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@f8ef336c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C1753360
  Type(jdk.ThreadPark) 9/15/20 10:13:53 AM 9/15/20 10:13:54 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@f8ef336c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C1753360
  Type(jdk.ThreadPark) 9/15/20 10:13:54 AM 9/15/20 10:13:55 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@f8ef336c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C1753360
  Type(jdk.ThreadPark) 9/15/20 10:13:55 AM 9/15/20 10:13:56 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@f8ef336c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C1753360
  Type(jdk.ThreadPark) 9/15/20 10:13:56 AM 9/15/20 10:13:57 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@f8ef336c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C1753360
  Type(jdk.ThreadPark) 9/15/20 10:13:57 AM 9/15/20 10:13:58 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@f8ef336c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C1753360
  Type(jdk.ThreadPark) 9/15/20 10:13:58 AM 9/15/20 10:13:59 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@f8ef336c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C1753360
  Type(jdk.ThreadPark) 9/15/20 10:13:59 AM 9/15/20 10:14:00 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@f8ef336c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C1753360
  Type(jdk.ThreadPark) 9/15/20 10:14:00 AM 9/15/20 10:14:01 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@f8ef336c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C1753360
  Type(jdk.ThreadPark) 9/15/20 10:14:01 AM 9/15/20 10:14:02 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@f8ef336c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C1753360
  Type(jdk.ThreadPark) 9/15/20 10:14:02 AM 9/15/20 10:14:03 AM AsyncFileHandlerWriter-140799417 org.openjdk.jmc.common.util.MCStackTrace@f8ef336c java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 1 wk 5 d 0x5C1753360
Type(jdk.ThreadPark): 416
  Type(jdk.ThreadPark) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@5396b597 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DC2A9D0
  Type(jdk.ThreadPark) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@a1663ce1 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DC2B460
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@34b6fb2c java.util.concurrent.Semaphore$NonfairSync 0 s 0x720307708
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@36555ff7 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DD02B88
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@752f52a2 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DD0AF08
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@996ae54c java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DD0E910
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@4605705d java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DD0E9D0
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@ce7dad1b java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DD14FC8
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@a646b992 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DD17700
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@149de8d java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DD18680
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@ade4699e java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DD18740
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-5 org.openjdk.jmc.common.util.MCStackTrace@ce7dad1b java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DD1A080
Type(jdk.ThreadPark): 369
  Type(jdk.ThreadPark) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@c683c7f7 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DAFE5B8
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@e5ad56ec java.util.concurrent.Semaphore$NonfairSync 0 s 0x71DCFCC30
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@4605705d java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DB29A18
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@11ec5077 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DB2D420
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@ce7dad1b java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DB30BC0
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@a646b992 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DB332F8
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@ade4699e java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DB34338
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@ce7dad1b java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DB35C78
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@ade4699e java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DB393F0
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@41eb3900 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DB3BB28
  Type(jdk.ThreadPark) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@4988e90c java.util.concurrent.CompletableFuture$Signaller 0 s 0x71DB3CB68
  Type(jdk.ThreadPark) 9/15/20 10:14:41 AM 9/15/20 10:14:41 AM pool-3-thread-4 org.openjdk.jmc.common.util.MCStackTrace@b252050b java.util.concurrent.CompletableFuture$Signaller 0 s 0x71E50F1F8
Type(jdk.ThreadPark): 1
  Type(jdk.ThreadPark) 9/15/20 10:14:48 AM 9/15/20 10:16:57 AM http-nio-8080-exec-3 org.openjdk.jmc.common.util.MCStackTrace@f35f8e9 java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 0 s 0x5C119D1E0
Type(jdk.ThreadPark): 1
  Type(jdk.ThreadPark) 9/15/20 10:14:47 AM 9/15/20 10:16:37 AM http-nio-8080-exec-2 org.openjdk.jmc.common.util.MCStackTrace@f35f8e9 java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 0 s 0x5C119D1E0
Type(jdk.ThreadPark): 25
  Type(jdk.ThreadPark) 9/15/20 10:14:37 AM 9/15/20 10:14:37 AM pool-2-thread-1 org.openjdk.jmc.common.util.MCStackTrace@435808bd java.util.concurrent.CompletableFuture$Signaller 0 s 0x753E12760
  Type(jdk.ThreadPark) 9/15/20 10:14:37 AM 9/15/20 10:14:38 AM pool-2-thread-1 org.openjdk.jmc.common.util.MCStackTrace@a648b4a1 java.util.concurrent.CompletableFuture$Signaller 0 s 0x753E13C48
  Type(jdk.ThreadPark) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM pool-2-thread-1 org.openjdk.jmc.common.util.MCStackTrace@bb1207ef java.util.concurrent.CompletableFuture$Signaller 0 s 0x753E15B60
  Type(jdk.ThreadPark) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM pool-2-thread-1 org.openjdk.jmc.common.util.MCStackTrace@b8c6e6a2 java.util.concurrent.CompletableFuture$Signaller 0 s 0x753E31628
  Type(jdk.ThreadPark) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM pool-2-thread-1 org.openjdk.jmc.common.util.MCStackTrace@b0c08809 java.util.concurrent.CompletableFuture$Signaller 0 s 0x753E418E0
  Type(jdk.ThreadPark) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM pool-2-thread-1 org.openjdk.jmc.common.util.MCStackTrace@234f3735 java.util.concurrent.CompletableFuture$Signaller 0 s 0x753E42A18
  Type(jdk.ThreadPark) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM pool-2-thread-1 org.openjdk.jmc.common.util.MCStackTrace@d2551c36 java.util.concurrent.CompletableFuture$Signaller 0 s 0x753E4A6A8
  Type(jdk.ThreadPark) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM pool-2-thread-1 org.openjdk.jmc.common.util.MCStackTrace@44e3cb62 java.util.concurrent.CompletableFuture$Signaller 0 s 0x753E4B930
  Type(jdk.ThreadPark) 9/15/20 10:14:38 AM 9/15/20 10:14:39 AM pool-2-thread-1 org.openjdk.jmc.common.util.MCStackTrace@f4a4728 java.util.concurrent.FutureTask 0 s 0x718E58E18
  Type(jdk.ThreadPark) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-2-thread-1 org.openjdk.jmc.common.util.MCStackTrace@6ed3d820 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71D7365D8
  Type(jdk.ThreadPark) 9/15/20 10:14:39 AM 9/15/20 10:14:39 AM pool-2-thread-1 org.openjdk.jmc.common.util.MCStackTrace@a4df1792 java.util.concurrent.CompletableFuture$Signaller 0 s 0x71D767E38
  Type(jdk.ThreadPark) 9/15/20 10:14:39 AM 9/15/20 10:14:40 AM pool-2-thread-1 org.openjdk.jmc.common.util.MCStackTrace@632abe81 java.util.concurrent.FutureTask 0 s 0x71D7872D8
Type(jdk.ThreadPark): 1
  Type(jdk.ThreadPark) 9/15/20 10:14:43 AM 9/15/20 10:16:29 AM http-nio-8080-exec-10 org.openjdk.jmc.common.util.MCStackTrace@f35f8e9 java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 0 s 0x5C119D1E0
Type(jdk.ThreadPark): 3
  Type(jdk.ThreadPark) 9/15/20 10:14:37 AM 9/15/20 10:14:37 AM http-nio-8080-exec-9 org.openjdk.jmc.common.util.MCStackTrace@2ed2d555 java.util.concurrent.CompletableFuture$Signaller 0 s 0x752195CE0
  Type(jdk.ThreadPark) 9/15/20 10:14:37 AM 9/15/20 10:14:37 AM http-nio-8080-exec-9 org.openjdk.jmc.common.util.MCStackTrace@8384fd08 java.util.concurrent.CompletableFuture$Signaller 0 s 0x752197448
  Type(jdk.ThreadPark) 9/15/20 10:14:37 AM 9/15/20 10:16:24 AM http-nio-8080-exec-9 org.openjdk.jmc.common.util.MCStackTrace@f35f8e9 java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 0 s 0x5C119D1E0
Type(jdk.ThreadPark): 1
  Type(jdk.ThreadPark) 9/15/20 10:14:33 AM 9/15/20 10:16:15 AM http-nio-8080-exec-8 org.openjdk.jmc.common.util.MCStackTrace@f35f8e9 java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 0 s 0x5C119D1E0
Type(jdk.ThreadPark): 5
  Type(jdk.ThreadPark) 9/15/20 10:14:26 AM 9/15/20 10:14:26 AM http-nio-8080-exec-7 org.openjdk.jmc.common.util.MCStackTrace@33027447 java.util.concurrent.CompletableFuture$Signaller 0 s 0x74F88B698
  Type(jdk.ThreadPark) 9/15/20 10:14:26 AM 9/15/20 10:14:26 AM http-nio-8080-exec-7 org.openjdk.jmc.common.util.MCStackTrace@f9273aa java.util.concurrent.CompletableFuture$Signaller 0 s 0x74F88CD28
  Type(jdk.ThreadPark) 9/15/20 10:14:26 AM 9/15/20 10:14:26 AM http-nio-8080-exec-7 org.openjdk.jmc.common.util.MCStackTrace@c85e9479 java.util.concurrent.CompletableFuture$Signaller 0 s 0x74F88E490
  Type(jdk.ThreadPark) 9/15/20 10:14:26 AM 9/15/20 10:14:26 AM http-nio-8080-exec-7 org.openjdk.jmc.common.util.MCStackTrace@2c4d9c4c java.util.concurrent.CompletableFuture$Signaller 0 s 0x74F88FC30
  Type(jdk.ThreadPark) 9/15/20 10:14:26 AM 9/15/20 10:15:26 AM http-nio-8080-exec-7 org.openjdk.jmc.common.util.MCStackTrace@f35f8e9 java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 0 s 0x5C119D1E0
Type(jdk.ThreadPark): 1
  Type(jdk.ThreadPark) 9/15/20 10:14:22 AM 9/15/20 10:15:06 AM http-nio-8080-exec-6 org.openjdk.jmc.common.util.MCStackTrace@f35f8e9 java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject 0 s 0x5C119D1E0
Type(jdk.ThreadPark): 13
  Type(jdk.ThreadPark) 9/15/20 10:14:12 AM 9/15/20 10:14:13 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@5ca29709 java.util.concurrent.CompletableFuture$Signaller 0 s 0x744587BC8
  Type(jdk.ThreadPark) 9/15/20 10:14:13 AM 9/15/20 10:14:13 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@2434b5ba java.util.concurrent.CompletableFuture$Signaller 0 s 0x749670BD8
  Type(jdk.ThreadPark) 9/15/20 10:14:13 AM 9/15/20 10:14:13 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@487cb446 java.util.concurrent.CompletableFuture$Signaller 0 s 0x7496FB1F0
  Type(jdk.ThreadPark) 9/15/20 10:14:13 AM 9/15/20 10:14:13 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@b172e23b java.util.concurrent.CompletableFuture$Signaller 0 s 0x7496FC500
  Type(jdk.ThreadPark) 9/15/20 10:14:13 AM 9/15/20 10:14:13 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@199be757 java.util.concurrent.CompletableFuture$Signaller 0 s 0x749766730
  Type(jdk.ThreadPark) 9/15/20 10:14:13 AM 9/15/20 10:14:13 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@9431460f java.util.concurrent.CompletableFuture$Signaller 0 s 0x7497BB370
  Type(jdk.ThreadPark) 9/15/20 10:14:13 AM 9/15/20 10:14:13 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@49aca6f8 java.util.concurrent.CompletableFuture$Signaller 0 s 0x7497E07E0
  Type(jdk.ThreadPark) 9/15/20 10:14:14 AM 9/15/20 10:14:14 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@e73c00ac java.util.concurrent.CompletableFuture$Signaller 0 s 0x749A6CC58
  Type(jdk.ThreadPark) 9/15/20 10:14:14 AM 9/15/20 10:14:14 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@17bd4cbe java.util.concurrent.CompletableFuture$Signaller 0 s 0x749A7C030
  Type(jdk.ThreadPark) 9/15/20 10:14:14 AM 9/15/20 10:14:14 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@8b49a7ef java.util.concurrent.CompletableFuture$Signaller 0 s 0x749A81418
  Type(jdk.ThreadPark) 9/15/20 10:14:14 AM 9/15/20 10:14:14 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@e2909b3c java.util.concurrent.CompletableFuture$Signaller 0 s 0x749A87EA0
  Type(jdk.ThreadPark) 9/15/20 10:14:14 AM 9/15/20 10:14:14 AM http-nio-8080-exec-5 org.openjdk.jmc.common.util.MCStackTrace@4233b6db java.util.concurrent.CompletableFuture$Signaller 0 s 0x749A8D2F0
Type(jdk.GCSurvivorConfiguration): 1
  Type(jdk.GCSurvivorConfiguration) 9/15/20 10:18:50 AM 15 7
Type(jdk.OldGarbageCollection): 2
  Type(jdk.OldGarbageCollection) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 13
  Type(jdk.OldGarbageCollection) 9/15/20 10:18:48 AM 9/15/20 10:18:49 AM 20
Type(jdk.SweepCodeCache): 0
Type(jdk.ThreadStart): 110
  Type(jdk.ThreadStart) 9/15/20 10:14:12 AM Neo4jDriverIO-2-1 Neo4jDriverIO-2-1
  Type(jdk.ThreadStart) 9/15/20 10:14:12 AM Neo4jDriverIO-2-2 Neo4jDriverIO-2-2
  Type(jdk.ThreadStart) 9/15/20 10:14:13 AM Neo4jDriverIO-2-3 Neo4jDriverIO-2-3
  Type(jdk.ThreadStart) 9/15/20 10:14:13 AM Neo4jDriverIO-2-4 Neo4jDriverIO-2-4
  Type(jdk.ThreadStart) 9/15/20 10:14:13 AM Neo4jDriverIO-2-5 Neo4jDriverIO-2-5
  Type(jdk.ThreadStart) 9/15/20 10:14:13 AM Neo4jDriverIO-2-6 Neo4jDriverIO-2-6
  Type(jdk.ThreadStart) 9/15/20 10:14:13 AM Neo4jDriverIO-2-7 Neo4jDriverIO-2-7
  Type(jdk.ThreadStart) 9/15/20 10:14:13 AM Neo4jDriverIO-2-8 Neo4jDriverIO-2-8
  Type(jdk.ThreadStart) 9/15/20 10:14:13 AM Neo4jDriverIO-2-9 Neo4jDriverIO-2-9
  Type(jdk.ThreadStart) 9/15/20 10:14:13 AM Neo4jDriverIO-2-10 Neo4jDriverIO-2-10
  Type(jdk.ThreadStart) 9/15/20 10:14:13 AM Neo4jDriverIO-2-11 Neo4jDriverIO-2-11
  Type(jdk.ThreadStart) 9/15/20 10:14:13 AM Neo4jDriverIO-2-12 Neo4jDriverIO-2-12
Type(jdk.ThreadDump): 4
  Type(jdk.ThreadDump) 9/15/20 10:14:50 AM 2020-09-15 10:14:50
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.241-b07 mixed mode):

"pool-3-thread-7" #98 prio=5 os_prio=0 tid=0x000000003100b800 nid=0x3ab8 waiting on condition [0x000000004307f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c2428aa8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c255b1b0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2582f28> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c255b0e0> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c24f13d0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c24f1428> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c24f1300> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2501140> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2501198> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2501070> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2513df0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2513e48> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2513d20> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2520a28> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c27c56a0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2520958> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c27ce568> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c27ce5c0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c27ce498> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c283a218> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c283a270> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c283a148> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c284c0c8> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c284c120> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c284bff8> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-8" #82 prio=10 os_prio=2 tid=0x000000003c9b8000 nid=0x174 runnable [0x000000003ea1f000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c2859378> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c28593d0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c28592a8> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2598028> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2598080> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2597f58> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c273e5e8> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c273f6c0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c273e508> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c26400e8> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2640140> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2640018> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c25f39c0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c261ade0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c25f38e0> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c25b7dd0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c25b7e28> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c25b7d00> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c261b4a8> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c27229f0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c261b3c8> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2752648> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c27526a0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2752578> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"RMI TCP Connection(6)-127.0.0.1" #74 daemon prio=5 os_prio=0 tid=0x000000002d300000 nid=0x3340 runnable [0x000000003048d000]
   java.lang.Thread.State: RUNNABLE
	at java.net.SocketInputStream.socketRead0(Native Method)
	at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
	at java.net.SocketInputStream.read(SocketInputStream.java:171)
	at java.net.SocketInputStream.read(SocketInputStream.java:141)
	at java.io.BufferedInputStream.fill(BufferedInputStream.java:246)
	at java.io.BufferedInputStream.read(BufferedInputStream.java:265)
	- locked <0x00000005c2ad4280> (a java.io.BufferedInputStream)
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

"JMX server connection timeout 73" #73 daemon prio=5 os_prio=0 tid=0x000000002d2ff800 nid=0x2a54 in Object.wait() [0x000000003a7df000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at com.sun.jmx.remote.internal.ServerCommunicatorAdmin$Timeout.run(ServerCommunicatorAdmin.java:168)
	- locked <0x00000005c249a730> (a [I)
	at java.lang.Thread.run(Thread.java:748)

"RMI TCP Connection(5)-127.0.0.1" #72 daemon prio=5 os_prio=0 tid=0x000000002d2f8800 nid=0x3744 in Object.wait() [0x00000000302fc000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at com.sun.jmx.remote.internal.ArrayNotificationBuffer.fetchNotifications(ArrayNotificationBuffer.java:449)
	- locked <0x00000005c23bd058> (a com.sun.jmx.remote.internal.ArrayNotificationBuffer)
	at com.sun.jmx.remote.internal.ArrayNotificationBuffer$ShareBuffer.fetchNotifications(ArrayNotificationBuffer.java:227)
	at com.sun.jmx.remote.internal.ServerNotifForwarder.fetchNotifs(ServerNotifForwarder.java:274)
	at javax.management.remote.rmi.RMIConnectionImpl$4.run(RMIConnectionImpl.java:1270)
	at javax.management.remote.rmi.RMIConnectionImpl$4.run(RMIConnectionImpl.java:1268)
	at javax.management.remote.rmi.RMIConnectionImpl.fetchNotifications(RMIConnectionImpl.java:1274)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
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

"MessageBroker-2" #71 prio=5 os_prio=0 tid=0x000000002d2fb800 nid=0x3898 waiting on condition [0x000000003457f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25f1118> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c25f0050> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c25f1118> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c25f0050> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c1179d68> (a java.lang.Object)
	at org.apache.tomcat.util.net.NioEndpoint$Acceptor.run(NioEndpoint.java:482)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-ClientPoller-1" #57 daemon prio=5 os_prio=0 tid=0x000000002d2f6800 nid=0x31bc runnable [0x0000000033f9e000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c1180598> (a sun.nio.ch.Util$3)
	- locked <0x00000005c1180588> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c1180418> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c1189f18> (a sun.nio.ch.Util$3)
	- locked <0x00000005c1189f08> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c1181e10> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioEndpoint$Poller.run(NioEndpoint.java:825)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-exec-10" #55 daemon prio=5 os_prio=0 tid=0x000000002d2f5000 nid=0x31e4 waiting on condition [0x0000000032e9e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c11b5548> (a java.lang.Object)
	at org.apache.tomcat.util.net.NioEndpoint$Acceptor.run(NioEndpoint.java:482)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-ClientPoller-1" #43 daemon prio=5 os_prio=0 tid=0x000000002d06a800 nid=0x33dc runnable [0x0000000032c2f000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c11bbd70> (a sun.nio.ch.Util$3)
	- locked <0x00000005c11bbd60> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c11bbbf0> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c11bd768> (a sun.nio.ch.Util$3)
	- locked <0x00000005c11bd758> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c11bd5e8> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioEndpoint$Poller.run(NioEndpoint.java:825)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-exec-10" #41 daemon prio=5 os_prio=0 tid=0x000000002d069000 nid=0x24c4 waiting on condition [0x000000003284e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c1178158> (a sun.nio.ch.Util$3)
	- locked <0x00000005c1178148> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c1177fd8> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioBlockingSelector$BlockPoller.run(NioBlockingSelector.java:298)

"NioBlockingSelector.BlockPoller-1" #28 daemon prio=5 os_prio=0 tid=0x000000002d4dd800 nid=0x36ec runnable [0x000000002e5ff000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c11af9b0> (a sun.nio.ch.Util$3)
	- locked <0x00000005c11af9a0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c11af840> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioBlockingSelector$BlockPoller.run(NioBlockingSelector.java:298)

"GC Daemon" #27 daemon prio=2 os_prio=-2 tid=0x000000002f885000 nid=0x918 in Object.wait() [0x000000003073f000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x00000005c105f1b0> (a sun.misc.GC$LatencyLock)
	at sun.misc.GC$Daemon.run(GC.java:117)
	- locked <0x00000005c105f1b0> (a sun.misc.GC$LatencyLock)

"RMI Scheduler(0)" #25 daemon prio=5 os_prio=0 tid=0x000000002d07d800 nid=0x32c4 waiting on condition [0x00000000305be000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c15b96b0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c10a39a8> (a java.net.SocksSocketImpl)
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
	- locked <0x00000005c109ea90> (a java.net.SocksSocketImpl)
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
	- locked <0x00000005c10ac9d0> (a java.net.SocksSocketImpl)
	at java.net.ServerSocket.implAccept(ServerSocket.java:545)
	at java.net.ServerSocket.accept(ServerSocket.java:513)
	at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.executeAcceptLoop(TCPTransport.java:405)
	at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.run(TCPTransport.java:377)
	at java.lang.Thread.run(Thread.java:748)

"AsyncFileHandlerWriter-140799417" #19 daemon prio=5 os_prio=0 tid=0x000000002cc8e000 nid=0x17c8 waiting on condition [0x000000002e23e000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c157cf58> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c1168940> (a java.util.TaskQueue)
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

"Finalizer" #3 daemon prio=8 os_prio=1 tid=0x0000000025b07800 nid=0xfb4 in Object.wait() [0x0000000027c2e000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:144)
	- locked <0x00000005c0e64f98> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:165)
	at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:216)

"Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x0000000025b00000 nid=0x3fc8 in Object.wait() [0x0000000027f6f000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at java.lang.Object.wait(Object.java:502)
	at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
	- locked <0x00000005c1600330> (a java.lang.ref.Reference$Lock)
	at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

"main" #1 prio=5 os_prio=0 tid=0x0000000001d2c000 nid=0x1f50 runnable [0x00000000023ae000]
   java.lang.Thread.State: RUNNABLE
	at java.net.DualStackPlainSocketImpl.accept0(Native Method)
	at java.net.DualStackPlainSocketImpl.socketAccept(DualStackPlainSocketImpl.java:131)
	at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:409)
	at java.net.PlainSocketImpl.accept(PlainSocketImpl.java:199)
	- locked <0x00000005c119c1b0> (a java.net.SocksSocketImpl)
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

JNI global references: 28414

 null
  Type(jdk.ThreadDump) 9/15/20 10:15:50 AM 2020-09-15 10:15:50
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.241-b07 mixed mode):

"pool-3-thread-7" #98 prio=5 os_prio=0 tid=0x000000003100b800 nid=0x3ab8 waiting on condition [0x000000004307f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c2428aa8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c255b1b0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2582f28> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c255b0e0> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c24f13d0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c24f1428> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c24f1300> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2501140> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2501198> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2501070> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2513df0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2513e48> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2513d20> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2520a28> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c27c56a0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2520958> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c27ce568> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c27ce5c0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c27ce498> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c283a218> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c283a270> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c283a148> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c284c0c8> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c284c120> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c284bff8> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-8" #82 prio=10 os_prio=2 tid=0x000000003c9b8000 nid=0x174 runnable [0x000000003ea1f000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c2859378> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c28593d0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c28592a8> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2598028> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2598080> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2597f58> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c273e5e8> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c273f6c0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c273e508> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c26400e8> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2640140> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2640018> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c25f39c0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c261ade0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c25f38e0> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c25b7dd0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c25b7e28> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c25b7d00> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c261b4a8> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c27229f0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c261b3c8> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2752648> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c27526a0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2752578> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"RMI TCP Connection(6)-127.0.0.1" #74 daemon prio=5 os_prio=0 tid=0x000000002d300000 nid=0x3340 runnable [0x000000003048d000]
   java.lang.Thread.State: RUNNABLE
	at java.net.SocketInputStream.socketRead0(Native Method)
	at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
	at java.net.SocketInputStream.read(SocketInputStream.java:171)
	at java.net.SocketInputStream.read(SocketInputStream.java:141)
	at java.io.BufferedInputStream.fill(BufferedInputStream.java:246)
	at java.io.BufferedInputStream.read(BufferedInputStream.java:265)
	- locked <0x00000005c2ad4280> (a java.io.BufferedInputStream)
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

"JMX server connection timeout 73" #73 daemon prio=5 os_prio=0 tid=0x000000002d2ff800 nid=0x2a54 in Object.wait() [0x000000003a7df000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at com.sun.jmx.remote.internal.ServerCommunicatorAdmin$Timeout.run(ServerCommunicatorAdmin.java:168)
	- locked <0x00000005c249a730> (a [I)
	at java.lang.Thread.run(Thread.java:748)

"RMI TCP Connection(5)-127.0.0.1" #72 daemon prio=5 os_prio=0 tid=0x000000002d2f8800 nid=0x3744 in Object.wait() [0x00000000302fc000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at com.sun.jmx.remote.internal.ArrayNotificationBuffer.fetchNotifications(ArrayNotificationBuffer.java:449)
	- locked <0x00000005c23bd058> (a com.sun.jmx.remote.internal.ArrayNotificationBuffer)
	at com.sun.jmx.remote.internal.ArrayNotificationBuffer$ShareBuffer.fetchNotifications(ArrayNotificationBuffer.java:227)
	at com.sun.jmx.remote.internal.ServerNotifForwarder.fetchNotifs(ServerNotifForwarder.java:274)
	at javax.management.remote.rmi.RMIConnectionImpl$4.run(RMIConnectionImpl.java:1270)
	at javax.management.remote.rmi.RMIConnectionImpl$4.run(RMIConnectionImpl.java:1268)
	at javax.management.remote.rmi.RMIConnectionImpl.fetchNotifications(RMIConnectionImpl.java:1274)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
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

"MessageBroker-2" #71 prio=5 os_prio=0 tid=0x000000002d2fb800 nid=0x3898 waiting on condition [0x000000003457f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25f1118> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c25f0050> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c25f1118> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c25f0050> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c1179d68> (a java.lang.Object)
	at org.apache.tomcat.util.net.NioEndpoint$Acceptor.run(NioEndpoint.java:482)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-ClientPoller-1" #57 daemon prio=5 os_prio=0 tid=0x000000002d2f6800 nid=0x31bc runnable [0x0000000033f9e000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c1180598> (a sun.nio.ch.Util$3)
	- locked <0x00000005c1180588> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c1180418> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c1189f18> (a sun.nio.ch.Util$3)
	- locked <0x00000005c1189f08> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c1181e10> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioEndpoint$Poller.run(NioEndpoint.java:825)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-exec-10" #55 daemon prio=5 os_prio=0 tid=0x000000002d2f5000 nid=0x31e4 waiting on condition [0x0000000032e9e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c11b5548> (a java.lang.Object)
	at org.apache.tomcat.util.net.NioEndpoint$Acceptor.run(NioEndpoint.java:482)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-ClientPoller-1" #43 daemon prio=5 os_prio=0 tid=0x000000002d06a800 nid=0x33dc runnable [0x0000000032c2f000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c11bbd70> (a sun.nio.ch.Util$3)
	- locked <0x00000005c11bbd60> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c11bbbf0> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c11bd768> (a sun.nio.ch.Util$3)
	- locked <0x00000005c11bd758> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c11bd5e8> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioEndpoint$Poller.run(NioEndpoint.java:825)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-exec-10" #41 daemon prio=5 os_prio=0 tid=0x000000002d069000 nid=0x24c4 waiting on condition [0x000000003284e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c1178158> (a sun.nio.ch.Util$3)
	- locked <0x00000005c1178148> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c1177fd8> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioBlockingSelector$BlockPoller.run(NioBlockingSelector.java:298)

"NioBlockingSelector.BlockPoller-1" #28 daemon prio=5 os_prio=0 tid=0x000000002d4dd800 nid=0x36ec runnable [0x000000002e5ff000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c11af9b0> (a sun.nio.ch.Util$3)
	- locked <0x00000005c11af9a0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c11af840> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioBlockingSelector$BlockPoller.run(NioBlockingSelector.java:298)

"GC Daemon" #27 daemon prio=2 os_prio=-2 tid=0x000000002f885000 nid=0x918 in Object.wait() [0x000000003073f000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x00000005c105f1b0> (a sun.misc.GC$LatencyLock)
	at sun.misc.GC$Daemon.run(GC.java:117)
	- locked <0x00000005c105f1b0> (a sun.misc.GC$LatencyLock)

"RMI Scheduler(0)" #25 daemon prio=5 os_prio=0 tid=0x000000002d07d800 nid=0x32c4 waiting on condition [0x00000000305be000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c15b96b0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c10a39a8> (a java.net.SocksSocketImpl)
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
	- locked <0x00000005c109ea90> (a java.net.SocksSocketImpl)
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
	- locked <0x00000005c10ac9d0> (a java.net.SocksSocketImpl)
	at java.net.ServerSocket.implAccept(ServerSocket.java:545)
	at java.net.ServerSocket.accept(ServerSocket.java:513)
	at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.executeAcceptLoop(TCPTransport.java:405)
	at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.run(TCPTransport.java:377)
	at java.lang.Thread.run(Thread.java:748)

"AsyncFileHandlerWriter-140799417" #19 daemon prio=5 os_prio=0 tid=0x000000002cc8e000 nid=0x17c8 waiting on condition [0x000000002e23e000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c157cf58> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c1168940> (a java.util.TaskQueue)
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

"Finalizer" #3 daemon prio=8 os_prio=1 tid=0x0000000025b07800 nid=0xfb4 in Object.wait() [0x0000000027c2e000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:144)
	- locked <0x00000005c0e64f98> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:165)
	at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:216)

"Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x0000000025b00000 nid=0x3fc8 in Object.wait() [0x0000000027f6f000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at java.lang.Object.wait(Object.java:502)
	at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
	- locked <0x00000005c1600330> (a java.lang.ref.Reference$Lock)
	at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

"main" #1 prio=5 os_prio=0 tid=0x0000000001d2c000 nid=0x1f50 runnable [0x00000000023ae000]
   java.lang.Thread.State: RUNNABLE
	at java.net.DualStackPlainSocketImpl.accept0(Native Method)
	at java.net.DualStackPlainSocketImpl.socketAccept(DualStackPlainSocketImpl.java:131)
	at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:409)
	at java.net.PlainSocketImpl.accept(PlainSocketImpl.java:199)
	- locked <0x00000005c119c1b0> (a java.net.SocksSocketImpl)
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

JNI global references: 28414

 null
  Type(jdk.ThreadDump) 9/15/20 10:16:50 AM 2020-09-15 10:16:50
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.241-b07 mixed mode):

"pool-2-thread-2" #100 prio=5 os_prio=0 tid=0x0000000037a63000 nid=0x3ea8 runnable [0x000000004a60d000]
   java.lang.Thread.State: RUNNABLE
	at java.net.SocketInputStream.socketRead0(Native Method)
	at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
	at java.net.SocketInputStream.read(SocketInputStream.java:171)
	at java.net.SocketInputStream.read(SocketInputStream.java:141)
	at sun.security.ssl.InputRecord.readFully(InputRecord.java:465)
	at sun.security.ssl.InputRecord.read(InputRecord.java:503)
	at sun.security.ssl.SSLSocketImpl.readRecord(SSLSocketImpl.java:975)
	- locked <0x0000000754b7a018> (a java.lang.Object)
	at sun.security.ssl.SSLSocketImpl.readDataRecord(SSLSocketImpl.java:933)
	at sun.security.ssl.AppInputStream.read(AppInputStream.java:105)
	- locked <0x0000000754b7a118> (a sun.security.ssl.AppInputStream)
	at java.io.BufferedInputStream.fill(BufferedInputStream.java:246)
	at java.io.BufferedInputStream.read1(BufferedInputStream.java:286)
	at java.io.BufferedInputStream.read(BufferedInputStream.java:345)
	- locked <0x0000000754bffc20> (a java.io.BufferedInputStream)
	at sun.net.www.http.HttpClient.parseHTTPHeader(HttpClient.java:735)
	at sun.net.www.http.HttpClient.parseHTTP(HttpClient.java:678)
	at sun.net.www.protocol.http.HttpURLConnection.getInputStream0(HttpURLConnection.java:1593)
	- locked <0x0000000754b79080> (a sun.net.www.protocol.https.DelegateHttpsURLConnection)
	at sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1498)
	- locked <0x0000000754b79080> (a sun.net.www.protocol.https.DelegateHttpsURLConnection)
	at sun.net.www.protocol.https.HttpsURLConnectionImpl.getInputStream(HttpsURLConnectionImpl.java:268)
	- locked <0x0000000754b78e40> (a sun.net.www.protocol.https.HttpsURLConnectionImpl)
	at java.net.URL.openStream(URL.java:1067)
	at jext.maven.MavenDownloader.downloadFromUrl(MavenDownloader.java:786)
	at jext.maven.MavenDownloader.downloadFromRepositories(MavenDownloader.java:687)
	at jext.maven.MavenDownloader.downloadFile(MavenDownloader.java:668)
	at jext.maven.MavenDownloader.getLatestVersion(MavenDownloader.java:456)
	at jext.maven.MavenDownloader.getVersioned(MavenDownloader.java:180)
	at jext.maven.MavenPom.lambda$getDependencies$2(MavenPom.java:590)
	at jext.maven.MavenPom$$Lambda$194/621167272.accept(Unknown Source)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jext.maven.MavenPom.getDependencies(MavenPom.java:562)
	at jext.maven.MavenPom.getDependencies(MavenPom.java:524)
	at jext.maven.MavenDownloader.getDependencies(MavenDownloader.java:375)
	at ae.ebtic.spl.analysis.sourcecode.analyzer.maven.MavenLibrary.getDependencies(MavenLibrary.java:117)
	at ae.ebtic.spl.analysis.sourcecode.analyzer.maven.MavenLibrary.getDependencies(MavenLibrary.java:109)
	at ae.ebtic.spl.analysis.dependencyv2.DependencyAnalyzer.lambda$analyzeLibraryDependencies$13(DependencyAnalyzer.java:471)
	at ae.ebtic.spl.analysis.dependencyv2.DependencyAnalyzer$$Lambda$252/1499312980.accept(Unknown Source)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at ae.ebtic.spl.analysis.dependencyv2.DependencyAnalyzer.analyzeLibraryDependencies(DependencyAnalyzer.java:470)
	at ae.ebtic.spl.analysis.dependencyv2.ListenableDependencyAnalyzer.analyzeLibraryDependencies(ListenableDependencyAnalyzer.java:182)
	at ae.ebtic.spl.analysis.dependencyv2.DependencyAnalyzer.lambda$analyzeLibraryDependencies$12(DependencyAnalyzer.java:456)
	at ae.ebtic.spl.analysis.dependencyv2.DependencyAnalyzer$$Lambda$248/1243304480.accept(Unknown Source)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at ae.ebtic.spl.analysis.dependencyv2.DependencyAnalyzer.analyzeLibraryDependencies(DependencyAnalyzer.java:455)
	at ae.ebtic.spl.analysis.dependencyv2.ListenableDependencyAnalyzer.analyzeLibraryDependencies(ListenableDependencyAnalyzer.java:174)
	at ae.ebtic.spl.analysis.dependencyv2.DependencyAnalyzer.analyze(DependencyAnalyzer.java:270)
	at ae.ebtic.spl.analysis.dependencyv2.ListenableDependencyAnalyzer.analyze(ListenableDependencyAnalyzer.java:72)
	at ae.ebtic.spl.tasks.AnalyzeDependencyTask.analyzeSources(AnalyzeDependencyTask.java:170)
	at ae.ebtic.spl.tasks.AnalyzeDependencyTask.process(AnalyzeDependencyTask.java:57)
	at jext.tasks.AbstractTask.run(AbstractTask.java:159)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run$$$capture(FutureTask.java:266)
	at java.util.concurrent.FutureTask.run(FutureTask.java)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"RMI TCP Connection(7)-127.0.0.1" #99 daemon prio=5 os_prio=0 tid=0x0000000037a62800 nid=0x1690 runnable [0x000000004aa7d000]
   java.lang.Thread.State: RUNNABLE
	at java.net.SocketInputStream.socketRead0(Native Method)
	at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
	at java.net.SocketInputStream.read(SocketInputStream.java:171)
	at java.net.SocketInputStream.read(SocketInputStream.java:141)
	at java.io.BufferedInputStream.fill(BufferedInputStream.java:246)
	at java.io.BufferedInputStream.read(BufferedInputStream.java:265)
	- locked <0x0000000738961a88> (a java.io.BufferedInputStream)
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

"pool-3-thread-7" #98 prio=5 os_prio=0 tid=0x000000003100b800 nid=0x3ab8 waiting on condition [0x000000004307f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c2428aa8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c255b1b0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2582f28> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c255b0e0> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c24f13d0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c24f1428> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c24f1300> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2501140> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2501198> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2501070> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2513df0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2513e48> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2513d20> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2520a28> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c27c56a0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2520958> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c27ce568> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c27ce5c0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c27ce498> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c283a218> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c283a270> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c283a148> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c284c0c8> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c284c120> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c284bff8> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-8" #82 prio=10 os_prio=2 tid=0x000000003c9b8000 nid=0x174 runnable [0x000000003ea1f000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c2859378> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c28593d0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c28592a8> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2598028> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2598080> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2597f58> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c273e5e8> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c273f6c0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c273e508> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c26400e8> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2640140> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2640018> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c25f39c0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c261ade0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c25f38e0> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c25b7dd0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c25b7e28> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c25b7d00> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c261b4a8> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c27229f0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c261b3c8> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2752648> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c27526a0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2752578> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"RMI TCP Connection(idle)" #74 daemon prio=5 os_prio=0 tid=0x000000002d300000 nid=0x3340 waiting on condition [0x000000003048e000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c11dadf8> (a java.util.concurrent.SynchronousQueue$TransferStack)
	at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:215)
	at java.util.concurrent.SynchronousQueue$TransferStack.awaitFulfill(SynchronousQueue.java:460)
	at java.util.concurrent.SynchronousQueue$TransferStack.transfer(SynchronousQueue.java:362)
	at java.util.concurrent.SynchronousQueue.poll(SynchronousQueue.java:941)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1073)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"JMX server connection timeout 73" #73 daemon prio=5 os_prio=0 tid=0x000000002d2ff800 nid=0x2a54 in Object.wait() [0x000000003a7df000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at com.sun.jmx.remote.internal.ServerCommunicatorAdmin$Timeout.run(ServerCommunicatorAdmin.java:168)
	- locked <0x00000005c249a730> (a [I)
	at java.lang.Thread.run(Thread.java:748)

"RMI TCP Connection(5)-127.0.0.1" #72 daemon prio=5 os_prio=0 tid=0x000000002d2f8800 nid=0x3744 in Object.wait() [0x00000000302fc000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at com.sun.jmx.remote.internal.ArrayNotificationBuffer.fetchNotifications(ArrayNotificationBuffer.java:449)
	- locked <0x00000005c23bd058> (a com.sun.jmx.remote.internal.ArrayNotificationBuffer)
	at com.sun.jmx.remote.internal.ArrayNotificationBuffer$ShareBuffer.fetchNotifications(ArrayNotificationBuffer.java:227)
	at com.sun.jmx.remote.internal.ServerNotifForwarder.fetchNotifs(ServerNotifForwarder.java:274)
	at javax.management.remote.rmi.RMIConnectionImpl$4.run(RMIConnectionImpl.java:1270)
	at javax.management.remote.rmi.RMIConnectionImpl$4.run(RMIConnectionImpl.java:1268)
	at javax.management.remote.rmi.RMIConnectionImpl.fetchNotifications(RMIConnectionImpl.java:1274)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
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

"MessageBroker-2" #71 prio=5 os_prio=0 tid=0x000000002d2fb800 nid=0x3898 waiting on condition [0x000000003457f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25f1118> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c25f0050> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c25f1118> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c25f0050> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c1179d68> (a java.lang.Object)
	at org.apache.tomcat.util.net.NioEndpoint$Acceptor.run(NioEndpoint.java:482)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-ClientPoller-1" #57 daemon prio=5 os_prio=0 tid=0x000000002d2f6800 nid=0x31bc runnable [0x0000000033f9e000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c1180598> (a sun.nio.ch.Util$3)
	- locked <0x00000005c1180588> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c1180418> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c1189f18> (a sun.nio.ch.Util$3)
	- locked <0x00000005c1189f08> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c1181e10> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioEndpoint$Poller.run(NioEndpoint.java:825)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-exec-10" #55 daemon prio=5 os_prio=0 tid=0x000000002d2f5000 nid=0x31e4 waiting on condition [0x0000000032e9e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c11b5548> (a java.lang.Object)
	at org.apache.tomcat.util.net.NioEndpoint$Acceptor.run(NioEndpoint.java:482)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-ClientPoller-1" #43 daemon prio=5 os_prio=0 tid=0x000000002d06a800 nid=0x33dc runnable [0x0000000032c2f000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c11bbd70> (a sun.nio.ch.Util$3)
	- locked <0x00000005c11bbd60> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c11bbbf0> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c11bd768> (a sun.nio.ch.Util$3)
	- locked <0x00000005c11bd758> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c11bd5e8> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioEndpoint$Poller.run(NioEndpoint.java:825)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-exec-10" #41 daemon prio=5 os_prio=0 tid=0x000000002d069000 nid=0x24c4 waiting on condition [0x000000003284e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c1178158> (a sun.nio.ch.Util$3)
	- locked <0x00000005c1178148> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c1177fd8> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioBlockingSelector$BlockPoller.run(NioBlockingSelector.java:298)

"NioBlockingSelector.BlockPoller-1" #28 daemon prio=5 os_prio=0 tid=0x000000002d4dd800 nid=0x36ec runnable [0x000000002e5ff000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c11af9b0> (a sun.nio.ch.Util$3)
	- locked <0x00000005c11af9a0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c11af840> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioBlockingSelector$BlockPoller.run(NioBlockingSelector.java:298)

"GC Daemon" #27 daemon prio=2 os_prio=-2 tid=0x000000002f885000 nid=0x918 in Object.wait() [0x000000003073f000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x00000005c105f1b0> (a sun.misc.GC$LatencyLock)
	at sun.misc.GC$Daemon.run(GC.java:117)
	- locked <0x00000005c105f1b0> (a sun.misc.GC$LatencyLock)

"RMI Scheduler(0)" #25 daemon prio=5 os_prio=0 tid=0x000000002d07d800 nid=0x32c4 waiting on condition [0x00000000305be000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c15b96b0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c10a39a8> (a java.net.SocksSocketImpl)
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
	- locked <0x00000005c109ea90> (a java.net.SocksSocketImpl)
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
	- locked <0x00000005c10ac9d0> (a java.net.SocksSocketImpl)
	at java.net.ServerSocket.implAccept(ServerSocket.java:545)
	at java.net.ServerSocket.accept(ServerSocket.java:513)
	at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.executeAcceptLoop(TCPTransport.java:405)
	at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.run(TCPTransport.java:377)
	at java.lang.Thread.run(Thread.java:748)

"AsyncFileHandlerWriter-140799417" #19 daemon prio=5 os_prio=0 tid=0x000000002cc8e000 nid=0x17c8 waiting on condition [0x000000002e23e000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c157cf58> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c1168940> (a java.util.TaskQueue)
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

"Finalizer" #3 daemon prio=8 os_prio=1 tid=0x0000000025b07800 nid=0xfb4 in Object.wait() [0x0000000027c2e000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:144)
	- locked <0x00000005c0e64f98> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:165)
	at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:216)

"Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x0000000025b00000 nid=0x3fc8 in Object.wait() [0x0000000027f6f000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at java.lang.Object.wait(Object.java:502)
	at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
	- locked <0x00000005c1600330> (a java.lang.ref.Reference$Lock)
	at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

"main" #1 prio=5 os_prio=0 tid=0x0000000001d2c000 nid=0x1f50 runnable [0x00000000023ae000]
   java.lang.Thread.State: RUNNABLE
	at java.net.DualStackPlainSocketImpl.accept0(Native Method)
	at java.net.DualStackPlainSocketImpl.socketAccept(DualStackPlainSocketImpl.java:131)
	at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:409)
	at java.net.PlainSocketImpl.accept(PlainSocketImpl.java:199)
	- locked <0x00000005c119c1b0> (a java.net.SocksSocketImpl)
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

"VM Periodic Task Thread" os_prio=2 tid=0x000000002eb20800 nid=0x393c runnable

JNI global references: 28963

 null
  Type(jdk.ThreadDump) 9/15/20 10:17:50 AM 2020-09-15 10:17:50
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.241-b07 mixed mode):

"Keep-Alive-Timer" #184 daemon prio=8 os_prio=1 tid=0x0000000037a67800 nid=0x3c30 waiting on condition [0x000000004ba9f000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
	at java.lang.Thread.sleep(Native Method)
	at sun.net.www.http.KeepAliveCache.run(KeepAliveCache.java:172)
	at java.lang.Thread.run(Thread.java:748)

"Keep-Alive-SocketCleaner" #183 daemon prio=8 os_prio=1 tid=0x0000000037a66000 nid=0x1ef0 in Object.wait() [0x000000004afaf000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at sun.net.www.http.KeepAliveStreamCleaner.run(KeepAliveStreamCleaner.java:101)
	- locked <0x0000000791c172a8> (a sun.net.www.http.KeepAliveStreamCleaner)
	at java.lang.Thread.run(Thread.java:748)

"RMI TCP Connection(9)-127.0.0.1" #172 daemon prio=5 os_prio=0 tid=0x0000000037a64000 nid=0x3910 runnable [0x0000000042f6e000]
   java.lang.Thread.State: RUNNABLE
	at java.net.SocketInputStream.socketRead0(Native Method)
	at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
	at java.net.SocketInputStream.read(SocketInputStream.java:171)
	at java.net.SocketInputStream.read(SocketInputStream.java:141)
	at java.io.BufferedInputStream.fill(BufferedInputStream.java:246)
	at java.io.BufferedInputStream.read(BufferedInputStream.java:265)
	- locked <0x000000079186f780> (a java.io.BufferedInputStream)
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

"RMI TCP Connection(8)-127.0.0.1" #167 daemon prio=5 os_prio=0 tid=0x000000002d300000 nid=0x2c0c runnable [0x00000000304be000]
   java.lang.Thread.State: RUNNABLE
	at java.net.SocketInputStream.socketRead0(Native Method)
	at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
	at java.net.SocketInputStream.read(SocketInputStream.java:171)
	at java.net.SocketInputStream.read(SocketInputStream.java:141)
	at java.io.BufferedInputStream.fill(BufferedInputStream.java:246)
	at java.io.BufferedInputStream.read(BufferedInputStream.java:265)
	- locked <0x00000007918aa060> (a java.io.BufferedInputStream)
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

"pool-2-thread-2" #100 prio=5 os_prio=0 tid=0x0000000037a63000 nid=0x3ea8 waiting on condition [0x000000004a60e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x000000071e03abb0> (a java.util.concurrent.FutureTask)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.FutureTask.awaitDone(FutureTask.java:429)
	at java.util.concurrent.FutureTask.get(FutureTask.java:191)
	at java.util.concurrent.AbstractExecutorService.invokeAll(AbstractExecutorService.java:244)
	at jext.util.concurrent.Parallel.invokeAll(Parallel.java:108)
	at jext.util.concurrent.Parallelize.invokeAll(Parallelize.java:58)
	at jext.util.concurrent.Parallelize.forEach(Parallelize.java:35)
	at ae.ebtic.spl.analysis.dependencyv2.DependencyAnalyzer.analyzeTypeDeclarations(DependencyAnalyzer.java:531)
	at ae.ebtic.spl.analysis.dependencyv2.ListenableDependencyAnalyzer.analyzeTypeDeclarations(ListenableDependencyAnalyzer.java:226)
	at ae.ebtic.spl.analysis.dependencyv2.DependencyAnalyzer.lambda$analyzeTypeDeclarations$15(DependencyAnalyzer.java:519)
	at ae.ebtic.spl.analysis.dependencyv2.DependencyAnalyzer$$Lambda$259/1389058921.accept(Unknown Source)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at ae.ebtic.spl.analysis.dependencyv2.DependencyAnalyzer.analyzeTypeDeclarations(DependencyAnalyzer.java:519)
	at ae.ebtic.spl.analysis.dependencyv2.ListenableDependencyAnalyzer.analyzeTypeDeclarations(ListenableDependencyAnalyzer.java:218)
	at ae.ebtic.spl.analysis.dependencyv2.DependencyAnalyzer.analyze(DependencyAnalyzer.java:280)
	at ae.ebtic.spl.analysis.dependencyv2.ListenableDependencyAnalyzer.analyze(ListenableDependencyAnalyzer.java:72)
	at ae.ebtic.spl.tasks.AnalyzeDependencyTask.analyzeSources(AnalyzeDependencyTask.java:170)
	at ae.ebtic.spl.tasks.AnalyzeDependencyTask.process(AnalyzeDependencyTask.java:57)
	at jext.tasks.AbstractTask.run(AbstractTask.java:159)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run$$$capture(FutureTask.java:266)
	at java.util.concurrent.FutureTask.run(FutureTask.java)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"RMI TCP Connection(7)-127.0.0.1" #99 daemon prio=5 os_prio=0 tid=0x0000000037a62800 nid=0x1690 runnable [0x000000004aa7d000]
   java.lang.Thread.State: RUNNABLE
	at java.net.SocketInputStream.socketRead0(Native Method)
	at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
	at java.net.SocketInputStream.read(SocketInputStream.java:171)
	at java.net.SocketInputStream.read(SocketInputStream.java:141)
	at java.io.BufferedInputStream.fill(BufferedInputStream.java:246)
	at java.io.BufferedInputStream.read(BufferedInputStream.java:265)
	- locked <0x00000007918b42d0> (a java.io.BufferedInputStream)
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

"pool-3-thread-7" #98 prio=5 os_prio=0 tid=0x000000003100b800 nid=0x3ab8 waiting on condition [0x000000004307e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"pool-3-thread-4" #95 prio=5 os_prio=0 tid=0x0000000031009800 nid=0x30f8 waiting on condition [0x0000000042d0e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000007216f1578> (a java.util.concurrent.CompletableFuture$Signaller)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.CompletableFuture$Signaller.block(CompletableFuture.java:1707)
	at java.util.concurrent.ForkJoinPool.managedBlock(ForkJoinPool.java:3323)
	at java.util.concurrent.CompletableFuture.waitingGet(CompletableFuture.java:1742)
	at java.util.concurrent.CompletableFuture.get(CompletableFuture.java:1908)
	at org.neo4j.driver.internal.util.Futures.blockingGet(Futures.java:107)
	at org.neo4j.driver.internal.NetworkSession.run(NetworkSession.java:94)
	at org.neo4j.driver.internal.NetworkSession.run(NetworkSession.java:76)
	at org.neo4j.driver.internal.AbstractStatementRunner.run(AbstractStatementRunner.java:41)
	at org.neo4j.driver.internal.AbstractStatementRunner.run(AbstractStatementRunner.java:53)
	at jext.graph.neo4j.Neo4JOnlineSession.find(Neo4JOnlineSession.java:733)
	at jext.graph.neo4j.Neo4JQuery.id(Neo4JQuery.java:92)
	at jext.graph.neo4j.Neo4JQuery.id(Neo4JQuery.java:59)
	at jext.graph.neo4j.Neo4JOnlineSession.findEdge(Neo4JOnlineSession.java:604)
	at ae.ebtic.spl.analysis.dependencies.DependencyGraph.createEdge(DependencyGraph.java:838)
	at ae.ebtic.spl.analysis.dependencies.DependencyGraph.createEdge(DependencyGraph.java:816)
	at ae.ebtic.spl.analysis.dependencyv2.DAtoDG.createEdge(DAtoDG.java:792)
	at ae.ebtic.spl.analysis.dependencyv2.java.ASTTypeDeclarations.createTokenNodes(ASTTypeDeclarations.java:81)
	at ae.ebtic.spl.analysis.dependencyv2.java.ASTTypeDeclarations.createTypeNode(ASTTypeDeclarations.java:40)
	at ae.ebtic.spl.analysis.dependencyv2.java.ASTTypeDeclarations$$Lambda$426/1196757218.accept(Unknown Source)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at ae.ebtic.spl.analysis.dependencyv2.java.ASTTypeDeclarations.analyze(ASTTypeDeclarations.java:26)
	at ae.ebtic.spl.analysis.dependencyv2.java.ASTAnalyzer.analyze(ASTAnalyzer.java:104)
	at ae.ebtic.spl.analysis.dependencyv2.java.ASTTypeDeclarations.analyze(ASTTypeDeclarations.java:19)
	at ae.ebtic.spl.analysis.dependencyv2.DependencyAnalyzer.analyzeTypeDeclarations(DependencyAnalyzer.java:547)
	at ae.ebtic.spl.analysis.dependencyv2.ListenableDependencyAnalyzer.analyzeTypeDeclarations(ListenableDependencyAnalyzer.java:235)
	at ae.ebtic.spl.analysis.dependencyv2.DependencyAnalyzer.lambda$analyzeTypeDeclarations$17(DependencyAnalyzer.java:532)
	at ae.ebtic.spl.analysis.dependencyv2.DependencyAnalyzer$$Lambda$260/905825583.accept(Unknown Source)
	at jext.util.concurrent.Parallel$Task.call(Parallel.java:49)
	at jext.util.concurrent.Parallel$Task.call(Parallel.java:38)
	at java.util.concurrent.FutureTask.run$$$capture(FutureTask.java:266)
	at java.util.concurrent.FutureTask.run(FutureTask.java)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"pool-3-thread-3" #94 prio=5 os_prio=0 tid=0x0000000031008800 nid=0x3d98 waiting on condition [0x0000000042bae000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c332a6c0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
	at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
	at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"pool-3-thread-1" #92 prio=5 os_prio=0 tid=0x0000000031007000 nid=0x3d90 waiting on condition [0x000000004286e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000007222157d0> (a java.util.concurrent.CompletableFuture$Signaller)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.CompletableFuture$Signaller.block(CompletableFuture.java:1707)
	at java.util.concurrent.ForkJoinPool.managedBlock(ForkJoinPool.java:3323)
	at java.util.concurrent.CompletableFuture.waitingGet(CompletableFuture.java:1742)
	at java.util.concurrent.CompletableFuture.get(CompletableFuture.java:1908)
	at org.neo4j.driver.internal.util.Futures.blockingGet(Futures.java:107)
	at org.neo4j.driver.internal.InternalStatementResult.blockingGet(InternalStatementResult.java:134)
	at org.neo4j.driver.internal.InternalStatementResult.next(InternalStatementResult.java:70)
	at jext.graph.neo4j.Neo4JOnlineSession.create(Neo4JOnlineSession.java:697)
	at jext.graph.neo4j.Neo4JOnlineSession.createNode(Neo4JOnlineSession.java:152)
	at ae.ebtic.spl.analysis.dependencies.DependencyGraph.createNode(DependencyGraph.java:761)
	at ae.ebtic.spl.analysis.dependencyv2.DAtoDG.createTokenNode(DAtoDG.java:788)
	at ae.ebtic.spl.analysis.dependencyv2.java.ASTTypeDeclarations.createTokenNodes(ASTTypeDeclarations.java:80)
	at ae.ebtic.spl.analysis.dependencyv2.java.ASTTypeDeclarations.createTypeNode(ASTTypeDeclarations.java:40)
	at ae.ebtic.spl.analysis.dependencyv2.java.ASTTypeDeclarations$$Lambda$426/1196757218.accept(Unknown Source)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at ae.ebtic.spl.analysis.dependencyv2.java.ASTTypeDeclarations.analyze(ASTTypeDeclarations.java:26)
	at ae.ebtic.spl.analysis.dependencyv2.java.ASTAnalyzer.analyze(ASTAnalyzer.java:104)
	at ae.ebtic.spl.analysis.dependencyv2.java.ASTTypeDeclarations.analyze(ASTTypeDeclarations.java:19)
	at ae.ebtic.spl.analysis.dependencyv2.DependencyAnalyzer.analyzeTypeDeclarations(DependencyAnalyzer.java:547)
	at ae.ebtic.spl.analysis.dependencyv2.ListenableDependencyAnalyzer.analyzeTypeDeclarations(ListenableDependencyAnalyzer.java:235)
	at ae.ebtic.spl.analysis.dependencyv2.DependencyAnalyzer.lambda$analyzeTypeDeclarations$17(DependencyAnalyzer.java:532)
	at ae.ebtic.spl.analysis.dependencyv2.DependencyAnalyzer$$Lambda$260/905825583.accept(Unknown Source)
	at jext.util.concurrent.Parallel$Task.call(Parallel.java:49)
	at jext.util.concurrent.Parallel$Task.call(Parallel.java:38)
	at java.util.concurrent.FutureTask.run$$$capture(FutureTask.java:266)
	at java.util.concurrent.FutureTask.run(FutureTask.java)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

"pool-2-thread-1" #91 prio=5 os_prio=0 tid=0x000000002d2f9800 nid=0x2784 waiting on condition [0x0000000041f7f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c2428aa8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c255b1b0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2582f28> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c255b0e0> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c24f13d0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c24f1428> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c24f1300> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2501140> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2501198> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2501070> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2513df0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2513e48> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2513d20> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2520a28> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c27c56a0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2520958> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c27ce568> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c27ce5c0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c27ce498> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c283a218> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c283a270> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c283a148> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c284c0c8> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c284c120> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c284bff8> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2859378> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c28593d0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c28592a8> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c2598028> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2598080> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2597f58> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c273e5e8> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c273f6c0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c273e508> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c26400e8> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c2640140> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c2640018> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c25f39c0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c261ade0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c25f38e0> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c25b7dd0> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c25b7e28> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c25b7d00> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c261b4a8> (a org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000005c27229f0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c261b3c8> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:753)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:409)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"Neo4jDriverIO-2-1" #75 prio=10 os_prio=2 tid=0x000000003c9b3000 nid=0x2be4 runnable [0x00000000340be000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl.setWakeupSocket0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl.setWakeupSocket(WindowsSelectorImpl.java:464)
	at sun.nio.ch.WindowsSelectorImpl.wakeup(WindowsSelectorImpl.java:606)
	- locked <0x00000005c25b7470> (a java.lang.Object)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.SelectedSelectionKeySetSelector.wakeup(SelectedSelectionKeySetSelector.java:73)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.wakeup(NioEventLoop.java:708)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor.execute(SingleThreadEventExecutor.java:776)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.DefaultPromise.safeExecute(DefaultPromise.java:764)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.DefaultPromise.notifyListeners(DefaultPromise.java:432)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.DefaultPromise.setSuccess(DefaultPromise.java:94)
	at org.neo4j.driver.internal.shaded.io.netty.channel.pool.FixedChannelPool$AcquireListener.operationComplete(FixedChannelPool.java:417)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.DefaultPromise.notifyListener0(DefaultPromise.java:511)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.DefaultPromise.notifyListenersNow(DefaultPromise.java:485)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.DefaultPromise.access$000(DefaultPromise.java:33)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.DefaultPromise$1.run(DefaultPromise.java:435)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:163)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:404)
	at org.neo4j.driver.internal.shaded.io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:463)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at org.neo4j.driver.internal.shaded.io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)

"JMX server connection timeout 73" #73 daemon prio=5 os_prio=0 tid=0x000000002d2ff800 nid=0x2a54 in Object.wait() [0x000000003a7df000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at com.sun.jmx.remote.internal.ServerCommunicatorAdmin$Timeout.run(ServerCommunicatorAdmin.java:168)
	- locked <0x00000005c249a730> (a [I)
	at java.lang.Thread.run(Thread.java:748)

"RMI TCP Connection(5)-127.0.0.1" #72 daemon prio=5 os_prio=0 tid=0x000000002d2f8800 nid=0x3744 in Object.wait() [0x00000000302fc000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at com.sun.jmx.remote.internal.ArrayNotificationBuffer.fetchNotifications(ArrayNotificationBuffer.java:449)
	- locked <0x00000005c23bd058> (a com.sun.jmx.remote.internal.ArrayNotificationBuffer)
	at com.sun.jmx.remote.internal.ArrayNotificationBuffer$ShareBuffer.fetchNotifications(ArrayNotificationBuffer.java:227)
	at com.sun.jmx.remote.internal.ServerNotifForwarder.fetchNotifs(ServerNotifForwarder.java:274)
	at javax.management.remote.rmi.RMIConnectionImpl$4.run(RMIConnectionImpl.java:1270)
	at javax.management.remote.rmi.RMIConnectionImpl$4.run(RMIConnectionImpl.java:1268)
	at javax.management.remote.rmi.RMIConnectionImpl.fetchNotifications(RMIConnectionImpl.java:1274)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
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

"MessageBroker-2" #71 prio=5 os_prio=0 tid=0x000000002d2fb800 nid=0x3898 waiting on condition [0x000000003457f000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c25f1118> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c25f0050> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c25f1118> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c25f0050> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c1179d68> (a java.lang.Object)
	at org.apache.tomcat.util.net.NioEndpoint$Acceptor.run(NioEndpoint.java:482)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-ClientPoller-1" #57 daemon prio=5 os_prio=0 tid=0x000000002d2f6800 nid=0x31bc runnable [0x0000000033f9e000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c1180598> (a sun.nio.ch.Util$3)
	- locked <0x00000005c1180588> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c1180418> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c1189f18> (a sun.nio.ch.Util$3)
	- locked <0x00000005c1189f08> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c1181e10> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioEndpoint$Poller.run(NioEndpoint.java:825)
	at java.lang.Thread.run(Thread.java:748)

"ajp-nio-8009-exec-10" #55 daemon prio=5 os_prio=0 tid=0x000000002d2f5000 nid=0x31e4 waiting on condition [0x0000000032e9e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c1169880> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c11b5548> (a java.lang.Object)
	at org.apache.tomcat.util.net.NioEndpoint$Acceptor.run(NioEndpoint.java:482)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-ClientPoller-1" #43 daemon prio=5 os_prio=0 tid=0x000000002d06a800 nid=0x33dc runnable [0x0000000032c2f000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c11bbd70> (a sun.nio.ch.Util$3)
	- locked <0x00000005c11bbd60> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c11bbbf0> (a sun.nio.ch.WindowsSelectorImpl)
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
	- locked <0x00000005c11bd768> (a sun.nio.ch.Util$3)
	- locked <0x00000005c11bd758> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c11bd5e8> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioEndpoint$Poller.run(NioEndpoint.java:825)
	at java.lang.Thread.run(Thread.java:748)

"http-nio-8080-exec-10" #41 daemon prio=5 os_prio=0 tid=0x000000002d069000 nid=0x24c4 waiting on condition [0x000000003284e000]
   java.lang.Thread.State: WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- parking to wait for  <0x00000005c119d1e0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c1178158> (a sun.nio.ch.Util$3)
	- locked <0x00000005c1178148> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c1177fd8> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioBlockingSelector$BlockPoller.run(NioBlockingSelector.java:298)

"NioBlockingSelector.BlockPoller-1" #28 daemon prio=5 os_prio=0 tid=0x000000002d4dd800 nid=0x36ec runnable [0x000000002e5ff000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
	at sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
	at sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x00000005c11af9b0> (a sun.nio.ch.Util$3)
	- locked <0x00000005c11af9a0> (a java.util.Collections$UnmodifiableSet)
	- locked <0x00000005c11af840> (a sun.nio.ch.WindowsSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at org.apache.tomcat.util.net.NioBlockingSelector$BlockPoller.run(NioBlockingSelector.java:298)

"GC Daemon" #27 daemon prio=2 os_prio=-2 tid=0x000000002f885000 nid=0x918 in Object.wait() [0x000000003073f000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x00000005c105f1b0> (a sun.misc.GC$LatencyLock)
	at sun.misc.GC$Daemon.run(GC.java:117)
	- locked <0x00000005c105f1b0> (a sun.misc.GC$LatencyLock)

"RMI Scheduler(0)" #25 daemon prio=5 os_prio=0 tid=0x000000002d07d800 nid=0x32c4 waiting on condition [0x00000000305be000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c15b96b0> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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
	- locked <0x00000005c10a39a8> (a java.net.SocksSocketImpl)
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
	- locked <0x00000005c109ea90> (a java.net.SocksSocketImpl)
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
	- locked <0x00000005c10ac9d0> (a java.net.SocksSocketImpl)
	at java.net.ServerSocket.implAccept(ServerSocket.java:545)
	at java.net.ServerSocket.accept(ServerSocket.java:513)
	at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.executeAcceptLoop(TCPTransport.java:405)
	at sun.rmi.transport.tcp.TCPTransport$AcceptLoop.run(TCPTransport.java:377)
	at java.lang.Thread.run(Thread.java:748)

"AsyncFileHandlerWriter-140799417" #19 daemon prio=5 os_prio=0 tid=0x000000002cc8e000 nid=0x17c8 waiting on condition [0x000000002e23e000]
   java.lang.Thread.State: TIMED_WAITING (parking)
	at sun.misc.Unsafe.park(Native Method)
	- parking to wait for  <0x00000005c157cf58> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
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

"C2 CompilerThread0" #12 daemon prio=9 os_prio=2 tid=0x000000002ca0b000 nid=0x2350 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"VM JFR Buffer Thread" #11 daemon prio=5 os_prio=0 tid=0x000000002c9fa800 nid=0x3f5c waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"JFR request timer" #9 daemon prio=5 os_prio=0 tid=0x0000000028704000 nid=0x3130 in Object.wait() [0x000000002c8cf000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at java.util.TimerThread.mainLoop(Timer.java:552)
	- locked <0x00000005c1168940> (a java.util.TaskQueue)
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

"Finalizer" #3 daemon prio=8 os_prio=1 tid=0x0000000025b07800 nid=0xfb4 in Object.wait() [0x0000000027c2e000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:144)
	- locked <0x00000005c0e64f98> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:165)
	at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:216)

"Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x0000000025b00000 nid=0x3fc8 in Object.wait() [0x0000000027f6f000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	at java.lang.Object.wait(Object.java:502)
	at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
	- locked <0x00000005c1600330> (a java.lang.ref.Reference$Lock)
	at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

"main" #1 prio=5 os_prio=0 tid=0x0000000001d2c000 nid=0x1f50 runnable [0x00000000023ae000]
   java.lang.Thread.State: RUNNABLE
	at java.net.DualStackPlainSocketImpl.accept0(Native Method)
	at java.net.DualStackPlainSocketImpl.socketAccept(DualStackPlainSocketImpl.java:131)
	at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:409)
	at java.net.PlainSocketImpl.accept(PlainSocketImpl.java:199)
	- locked <0x00000005c119c1b0> (a java.net.SocksSocketImpl)
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

JNI global references: 29774

 null
Type(jdk.MetaspaceSummary): 20
  Type(jdk.MetaspaceSummary) 9/15/20 10:14:38 AM 11 Before GC 57.6 MiB 52.9 MiB 51 MiB 1.05 GiB 46.5 MiB 45 MiB 48 MiB 6.38 MiB ...
  Type(jdk.MetaspaceSummary) 9/15/20 10:14:38 AM 11 After GC 57.6 MiB 52.9 MiB 51 MiB 1.05 GiB 46.5 MiB 45 MiB 48 MiB 6.38 MiB ...
  Type(jdk.MetaspaceSummary) 9/15/20 10:14:40 AM 12 Before GC 57.6 MiB 57.6 MiB 54.9 MiB 1.05 GiB 50.5 MiB 48.4 MiB 52 MiB 7.12 MiB ...
  Type(jdk.MetaspaceSummary) 9/15/20 10:14:40 AM 12 After GC 57.6 MiB 57.6 MiB 54.9 MiB 1.05 GiB 50.5 MiB 48.4 MiB 52 MiB 7.12 MiB ...
  Type(jdk.MetaspaceSummary) 9/15/20 10:14:40 AM 13 Before GC 57.6 MiB 57.6 MiB 54.9 MiB 1.05 GiB 50.5 MiB 48.4 MiB 52 MiB 7.12 MiB ...
  Type(jdk.MetaspaceSummary) 9/15/20 10:14:40 AM 13 After GC 96.1 MiB 57.6 MiB 54.9 MiB 1.05 GiB 50.5 MiB 48.4 MiB 52 MiB 7.12 MiB ...
  Type(jdk.MetaspaceSummary) 9/15/20 10:17:48 AM 14 Before GC 96.1 MiB 64.9 MiB 60.6 MiB 1.06 GiB 57 MiB 53.5 MiB 58 MiB 7.88 MiB ...
  Type(jdk.MetaspaceSummary) 9/15/20 10:17:48 AM 14 After GC 96.1 MiB 64.9 MiB 60.6 MiB 1.06 GiB 57 MiB 53.5 MiB 58 MiB 7.88 MiB ...
  Type(jdk.MetaspaceSummary) 9/15/20 10:18:01 AM 15 Before GC 96.1 MiB 66 MiB 61.4 MiB 1.06 GiB 58 MiB 54.2 MiB 58 MiB 8 MiB ...
  Type(jdk.MetaspaceSummary) 9/15/20 10:18:01 AM 15 After GC 96.1 MiB 66 MiB 61.4 MiB 1.06 GiB 58 MiB 54.2 MiB 58 MiB 8 MiB ...
  Type(jdk.MetaspaceSummary) 9/15/20 10:18:08 AM 16 Before GC 96.1 MiB 66 MiB 61.6 MiB 1.06 GiB 58 MiB 54.4 MiB 58 MiB 8 MiB ...
  Type(jdk.MetaspaceSummary) 9/15/20 10:18:08 AM 16 After GC 96.1 MiB 66 MiB 61.6 MiB 1.06 GiB 58 MiB 54.4 MiB 58 MiB 8 MiB ...
Type(jdk.PhysicalMemory): 2
  Type(jdk.PhysicalMemory) 9/15/20 10:13:50 AM 32 GiB 14.6 GiB
  Type(jdk.PhysicalMemory) 9/15/20 10:18:50 AM 32 GiB 17 GiB
Type(jdk.GCPhasePauseLevel1): 44
  Type(jdk.GCPhasePauseLevel1) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM 11 Scavenge null
  Type(jdk.GCPhasePauseLevel1) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM 11 References null
  Type(jdk.GCPhasePauseLevel1) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM 11 StringTable null
  Type(jdk.GCPhasePauseLevel1) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM 11 Prune Scavenge Root Methods null
  Type(jdk.GCPhasePauseLevel1) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 12 Scavenge null
  Type(jdk.GCPhasePauseLevel1) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 12 References null
  Type(jdk.GCPhasePauseLevel1) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 12 StringTable null
  Type(jdk.GCPhasePauseLevel1) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 12 Prune Scavenge Root Methods null
  Type(jdk.GCPhasePauseLevel1) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 13 pre compact null
  Type(jdk.GCPhasePauseLevel1) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 13 marking phase null
  Type(jdk.GCPhasePauseLevel1) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 13 summary phase null
  Type(jdk.GCPhasePauseLevel1) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 13 adjust roots null
Type(http://www.oracle.com/hotspot/jdk/java/security_property): 0
Type(jdk.GCPhasePauseLevel3): 0
Type(jdk.GCPhasePauseLevel2): 56
  Type(jdk.GCPhasePauseLevel2) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM 11 SoftReference null
  Type(jdk.GCPhasePauseLevel2) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM 11 WeakReference null
  Type(jdk.GCPhasePauseLevel2) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM 11 FinalReference null
  Type(jdk.GCPhasePauseLevel2) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM 11 PhantomReference null
  Type(jdk.GCPhasePauseLevel2) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM 11 JNI Weak Reference null
  Type(jdk.GCPhasePauseLevel2) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 12 SoftReference null
  Type(jdk.GCPhasePauseLevel2) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 12 WeakReference null
  Type(jdk.GCPhasePauseLevel2) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 12 FinalReference null
  Type(jdk.GCPhasePauseLevel2) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 12 PhantomReference null
  Type(jdk.GCPhasePauseLevel2) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 12 JNI Weak Reference null
  Type(jdk.GCPhasePauseLevel2) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 13 par mark null
  Type(jdk.GCPhasePauseLevel2) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 13 reference processing null
Type(jdk.MetaspaceOOM): 0
Type(jdk.ClassLoad): 0
Type(jdk.ParallelOldGarbageCollection): 2
  Type(jdk.ParallelOldGarbageCollection) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 13 0x5C0A00000 null
  Type(jdk.ParallelOldGarbageCollection) 9/15/20 10:18:48 AM 9/15/20 10:18:49 AM 20 0x5C0A00000 null
Type(jdk.Compilation): 55
  Type(jdk.Compilation) 9/15/20 10:14:11 AM 9/15/20 10:14:13 AM C2 CompilerThread2 org.openjdk.jmc.common.util.MCMethod@97cafded 5,977 4 true true 248 KiB 3.46 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:14 AM 9/15/20 10:14:15 AM C2 CompilerThread2 org.openjdk.jmc.common.util.MCMethod@ee10d91a 7,322 4 true false 144 KiB 7.19 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM C2 CompilerThread2 org.openjdk.jmc.common.util.MCMethod@e692dcf5 7,778 4 true false 32.7 KiB 2.6 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:41 AM 9/15/20 10:14:41 AM C2 CompilerThread2 org.openjdk.jmc.common.util.MCMethod@fa3c8816 8,953 4 true false 47.7 KiB 2.86 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:57 AM 9/15/20 10:14:57 AM C2 CompilerThread2 org.openjdk.jmc.common.util.MCMethod@c7f7adee 9,839 4 true false 72.2 KiB 4.63 KiB null
  Type(jdk.Compilation) 9/15/20 10:16:40 AM 9/15/20 10:16:41 AM C2 CompilerThread2 org.openjdk.jmc.common.util.MCMethod@52132988 10,474 4 true false 117 KiB 5.65 KiB null
  Type(jdk.Compilation) 9/15/20 10:16:43 AM 9/15/20 10:16:43 AM C2 CompilerThread2 org.openjdk.jmc.common.util.MCMethod@5b234aa5 10,676 4 true false 71.6 KiB 6.82 KiB null
  Type(jdk.Compilation) 9/15/20 10:17:24 AM 9/15/20 10:17:24 AM C2 CompilerThread2 org.openjdk.jmc.common.util.MCMethod@3187e378 11,257 4 true false 123 KiB 4.45 KiB null
  Type(jdk.Compilation) 9/15/20 10:17:41 AM 9/15/20 10:17:42 AM C2 CompilerThread2 org.openjdk.jmc.common.util.MCMethod@a7a10727 11,958 4 true false 47.6 KiB 4.45 KiB null
  Type(jdk.Compilation) 9/15/20 10:17:42 AM 9/15/20 10:17:42 AM C2 CompilerThread2 org.openjdk.jmc.common.util.MCMethod@32f06c47 12,062 4 true true 26 KiB 2.82 KiB null
  Type(jdk.Compilation) 9/15/20 10:17:43 AM 9/15/20 10:17:43 AM C2 CompilerThread2 org.openjdk.jmc.common.util.MCMethod@a7a0fb68 12,370 4 true false 110 KiB 7.12 KiB null
  Type(jdk.Compilation) 9/15/20 10:17:44 AM 9/15/20 10:17:45 AM C2 CompilerThread2 org.openjdk.jmc.common.util.MCMethod@ce51d930 12,581 4 true false 65.4 KiB 7.46 KiB null
Type(jdk.Compilation): 59
  Type(jdk.Compilation) 9/15/20 10:14:11 AM 9/15/20 10:14:11 AM C2 CompilerThread0 org.openjdk.jmc.common.util.MCMethod@49b0ca20 5,984 4 true false 74.9 KiB 4.71 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:14 AM 9/15/20 10:14:15 AM C2 CompilerThread0 org.openjdk.jmc.common.util.MCMethod@68c71e44 7,320 4 true false 170 KiB 7.63 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:23 AM 9/15/20 10:14:23 AM C2 CompilerThread0 org.openjdk.jmc.common.util.MCMethod@148281b9 7,423 4 true false 36.6 KiB 2.81 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:37 AM 9/15/20 10:14:38 AM C2 CompilerThread0 org.openjdk.jmc.common.util.MCMethod@64793c2 7,645 4 true false 81.2 KiB 4.51 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM C2 CompilerThread0 org.openjdk.jmc.common.util.MCMethod@39ef9ba3 7,837 4 true false 23.1 KiB 1.48 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:40 AM 9/15/20 10:14:41 AM C2 CompilerThread0 org.openjdk.jmc.common.util.MCMethod@2e1cddd5 8,621 4 true false 57.7 KiB 4.07 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:41 AM 9/15/20 10:14:41 AM C2 CompilerThread0 org.openjdk.jmc.common.util.MCMethod@6e6afdb3 8,779 4 true false 49.9 KiB 2.67 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:41 AM 9/15/20 10:14:41 AM C2 CompilerThread0 org.openjdk.jmc.common.util.MCMethod@9441cb5c 8,893 4 true false 37.5 KiB 2.31 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:42 AM 9/15/20 10:14:42 AM C2 CompilerThread0 org.openjdk.jmc.common.util.MCMethod@4bf82212 9,176 4 true false 107 KiB 7.15 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:57 AM 9/15/20 10:14:57 AM C2 CompilerThread0 org.openjdk.jmc.common.util.MCMethod@d61f22e6 9,878 4 true false 43 KiB 2.16 KiB null
  Type(jdk.Compilation) 9/15/20 10:15:23 AM 9/15/20 10:15:23 AM C2 CompilerThread0 org.openjdk.jmc.common.util.MCMethod@4475cd64 10,015 4 true false 37.4 KiB 1.29 KiB null
  Type(jdk.Compilation) 9/15/20 10:16:39 AM 9/15/20 10:16:39 AM C2 CompilerThread0 org.openjdk.jmc.common.util.MCMethod@f40f61d2 10,271 4 true false 17.8 KiB 1.99 KiB null
Type(jdk.Compilation): 66
  Type(jdk.Compilation) 9/15/20 10:14:11 AM 9/15/20 10:14:11 AM C2 CompilerThread1 org.openjdk.jmc.common.util.MCMethod@76b26f7e 5,965 4 true false 43.7 KiB 2.95 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:41 AM 9/15/20 10:14:41 AM C2 CompilerThread1 org.openjdk.jmc.common.util.MCMethod@b9179d0b 8,736 4 true false 33.7 KiB 2.52 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:41 AM 9/15/20 10:14:41 AM C2 CompilerThread1 org.openjdk.jmc.common.util.MCMethod@bc20b5dd 8,928 4 true false 18.7 KiB 1.6 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:41 AM 9/15/20 10:14:41 AM C2 CompilerThread1 org.openjdk.jmc.common.util.MCMethod@bc20b5dd 9,015 4 true false 21.2 KiB 1.61 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:43 AM 9/15/20 10:14:43 AM C2 CompilerThread1 org.openjdk.jmc.common.util.MCMethod@ecffb325 9,466 4 true false 52.1 KiB 4.56 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:45 AM 9/15/20 10:14:46 AM C2 CompilerThread1 org.openjdk.jmc.common.util.MCMethod@14ea4a8e 9,628 4 true true 186 KiB 6.76 KiB null
  Type(jdk.Compilation) 9/15/20 10:14:57 AM 9/15/20 10:14:57 AM C2 CompilerThread1 org.openjdk.jmc.common.util.MCMethod@53368ed2 9,853 4 true false 32.8 KiB 2.75 KiB null
  Type(jdk.Compilation) 9/15/20 10:16:09 AM 9/15/20 10:16:10 AM C2 CompilerThread1 org.openjdk.jmc.common.util.MCMethod@5673aece 10,072 4 true false 142 KiB 7.57 KiB null
  Type(jdk.Compilation) 9/15/20 10:16:33 AM 9/15/20 10:16:33 AM C2 CompilerThread1 org.openjdk.jmc.common.util.MCMethod@14ea4a8e 10,146 4 true false 124 KiB 6.49 KiB null
  Type(jdk.Compilation) 9/15/20 10:16:40 AM 9/15/20 10:16:40 AM C2 CompilerThread1 org.openjdk.jmc.common.util.MCMethod@309d5b99 10,476 4 true false 51.8 KiB 3.28 KiB null
  Type(jdk.Compilation) 9/15/20 10:16:43 AM 9/15/20 10:16:44 AM C2 CompilerThread1 org.openjdk.jmc.common.util.MCMethod@7d66b1c8 10,688 4 true false 90.7 KiB 3.63 KiB null
  Type(jdk.Compilation) 9/15/20 10:16:49 AM 9/15/20 10:16:49 AM C2 CompilerThread1 org.openjdk.jmc.common.util.MCMethod@8548c0d6 10,807 4 true false 86.7 KiB 3.31 KiB null
Type(jdk.Compilation): 1
  Type(jdk.Compilation) 9/15/20 10:17:48 AM 9/15/20 10:17:48 AM C1 CompilerThread3 org.openjdk.jmc.common.util.MCMethod@7ec01668 13,049 3 true false 23.2 KiB 329 B null
Type(jdk.SocketRead): 365
  Type(jdk.SocketRead) 9/15/20 10:16:28 AM 9/15/20 10:16:28 AM RMI TCP Connection(10)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,421 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:16:28 AM 9/15/20 10:16:29 AM RMI TCP Connection(10)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,421 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:16:29 AM 9/15/20 10:16:29 AM RMI TCP Connection(10)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,421 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:16:29 AM 9/15/20 10:16:30 AM RMI TCP Connection(10)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,421 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:16:30 AM 9/15/20 10:16:30 AM RMI TCP Connection(10)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,421 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:16:30 AM 9/15/20 10:16:31 AM RMI TCP Connection(10)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,421 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:16:31 AM 9/15/20 10:16:31 AM RMI TCP Connection(10)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,421 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:16:31 AM 9/15/20 10:16:32 AM RMI TCP Connection(10)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,421 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:16:32 AM 9/15/20 10:16:32 AM RMI TCP Connection(10)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,421 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:16:32 AM 9/15/20 10:16:33 AM RMI TCP Connection(10)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,421 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:16:33 AM 9/15/20 10:16:33 AM RMI TCP Connection(10)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,421 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:16:33 AM 9/15/20 10:16:34 AM RMI TCP Connection(10)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,421 127.0.0.1
Type(jdk.SocketRead): 33
  Type(jdk.SocketRead) 9/15/20 10:15:41 AM 9/15/20 10:15:41 AM RMI TCP Connection(idle) org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,277 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:15:41 AM 9/15/20 10:15:42 AM RMI TCP Connection(idle) org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,277 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:15:42 AM 9/15/20 10:15:42 AM RMI TCP Connection(idle) org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,277 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:15:42 AM 9/15/20 10:15:42 AM RMI TCP Connection(idle) org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,277 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:15:42 AM 9/15/20 10:15:43 AM RMI TCP Connection(idle) org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,277 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:15:43 AM 9/15/20 10:15:43 AM RMI TCP Connection(idle) org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,277 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:15:43 AM 9/15/20 10:15:44 AM RMI TCP Connection(idle) org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,277 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:15:44 AM 9/15/20 10:15:44 AM RMI TCP Connection(idle) org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,277 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:15:44 AM 9/15/20 10:15:45 AM RMI TCP Connection(idle) org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,277 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:15:45 AM 9/15/20 10:15:45 AM RMI TCP Connection(idle) org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,277 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:15:45 AM 9/15/20 10:15:46 AM RMI TCP Connection(idle) org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,277 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:15:46 AM 9/15/20 10:15:46 AM RMI TCP Connection(idle) org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,277 127.0.0.1
Type(jdk.SocketRead): 60
  Type(jdk.SocketRead) 9/15/20 10:14:21 AM 9/15/20 10:14:21 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:14:21 AM 9/15/20 10:14:22 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:14:22 AM 9/15/20 10:14:22 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:14:22 AM 9/15/20 10:14:23 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:14:23 AM 9/15/20 10:14:23 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:14:23 AM 9/15/20 10:14:24 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:14:24 AM 9/15/20 10:14:24 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:14:24 AM 9/15/20 10:14:25 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:14:25 AM 9/15/20 10:14:25 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:14:25 AM 9/15/20 10:14:26 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:14:26 AM 9/15/20 10:14:26 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:14:26 AM 9/15/20 10:14:27 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
Type(jdk.SocketRead): 457
  Type(jdk.SocketRead) 9/15/20 10:13:50 AM 9/15/20 10:13:51 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:13:51 AM 9/15/20 10:13:51 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:13:51 AM 9/15/20 10:13:52 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:13:52 AM 9/15/20 10:13:52 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:13:52 AM 9/15/20 10:13:53 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:13:53 AM 9/15/20 10:13:53 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:13:53 AM 9/15/20 10:13:54 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:13:54 AM 9/15/20 10:13:54 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:13:54 AM 9/15/20 10:13:55 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:13:55 AM 9/15/20 10:13:55 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:13:55 AM 9/15/20 10:13:56 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
  Type(jdk.SocketRead) 9/15/20 10:13:56 AM 9/15/20 10:13:56 AM RMI TCP Connection(5)-127.0.0.1 org.openjdk.jmc.common.util.MCStackTrace@e8dfa1a0 1 B 2 h 59,276 127.0.0.1
Type(jdk.ActiveRecording): 1
  Type(jdk.ActiveRecording) 9/15/20 10:13:50 AM false 9/15/20 10:13:50 AM 0 B 0 s 5 min recording.jfr My Recording 1
Type(jdk.MetaspaceAllocationFailure): 1
  Type(jdk.MetaspaceAllocationFailure) 9/15/20 10:14:40 AM org.openjdk.jmc.common.util.MCStackTrace@1245f89c null true 16 B Metadata TypeArrayU8
Type(jdk.GCPhasePause): 10
  Type(jdk.GCPhasePause) 9/15/20 10:14:38 AM 9/15/20 10:14:38 AM 11 GC Pause null
  Type(jdk.GCPhasePause) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 12 GC Pause null
  Type(jdk.GCPhasePause) 9/15/20 10:14:40 AM 9/15/20 10:14:40 AM 13 GC Pause null
  Type(jdk.GCPhasePause) 9/15/20 10:17:48 AM 9/15/20 10:17:48 AM 14 GC Pause null
  Type(jdk.GCPhasePause) 9/15/20 10:18:01 AM 9/15/20 10:18:01 AM 15 GC Pause null
  Type(jdk.GCPhasePause) 9/15/20 10:18:08 AM 9/15/20 10:18:08 AM 16 GC Pause null
  Type(jdk.GCPhasePause) 9/15/20 10:18:15 AM 9/15/20 10:18:15 AM 17 GC Pause null
  Type(jdk.GCPhasePause) 9/15/20 10:18:38 AM 9/15/20 10:18:38 AM 18 GC Pause null
  Type(jdk.GCPhasePause) 9/15/20 10:18:48 AM 9/15/20 10:18:48 AM 19 GC Pause null
  Type(jdk.GCPhasePause) 9/15/20 10:18:48 AM 9/15/20 10:18:49 AM 20 GC Pause null
Type(jdk.CompilerStatistics): 299
  Type(jdk.CompilerStatistics) 9/15/20 10:13:51 AM 5,653 3 0 50 5,603 0 B 0 B 20.4 MiB 11.7 MiB 1.833 s ...
  Type(jdk.CompilerStatistics) 9/15/20 10:13:52 AM 5,654 3 0 50 5,604 0 B 0 B 20.4 MiB 11.7 MiB 1.833 s ...
  Type(jdk.CompilerStatistics) 9/15/20 10:13:53 AM 5,655 3 0 50 5,605 0 B 0 B 20.4 MiB 11.7 MiB 1.833 s ...
  Type(jdk.CompilerStatistics) 9/15/20 10:13:54 AM 5,655 3 0 50 5,605 0 B 0 B 20.4 MiB 11.7 MiB 1.833 s ...
  Type(jdk.CompilerStatistics) 9/15/20 10:13:55 AM 5,658 3 0 50 5,608 0 B 0 B 20.4 MiB 11.7 MiB 1.833 s ...
  Type(jdk.CompilerStatistics) 9/15/20 10:13:56 AM 5,659 3 0 50 5,609 0 B 0 B 20.4 MiB 11.7 MiB 1.833 s ...
  Type(jdk.CompilerStatistics) 9/15/20 10:13:57 AM 5,660 3 0 50 5,610 0 B 0 B 20.4 MiB 11.7 MiB 1.833 s ...
  Type(jdk.CompilerStatistics) 9/15/20 10:13:58 AM 5,660 3 0 50 5,610 0 B 0 B 20.4 MiB 11.7 MiB 1.833 s ...
  Type(jdk.CompilerStatistics) 9/15/20 10:13:59 AM 5,664 3 0 50 5,614 0 B 0 B 20.4 MiB 11.7 MiB 1.833 s ...
  Type(jdk.CompilerStatistics) 9/15/20 10:14:00 AM 5,666 3 0 50 5,616 0 B 0 B 20.4 MiB 11.7 MiB 1.833 s ...
  Type(jdk.CompilerStatistics) 9/15/20 10:14:01 AM 5,667 3 0 50 5,617 0 B 0 B 20.4 MiB 11.7 MiB 1.833 s ...
  Type(jdk.CompilerStatistics) 9/15/20 10:14:02 AM 5,669 3 0 50 5,619 0 B 0 B 20.4 MiB 11.7 MiB 1.833 s ...
Type(jdk.CompilerConfiguration): 1
  Type(jdk.CompilerConfiguration) 9/15/20 10:18:50 AM 4 true
Type(jdk.EvacuationFailed): 0
Type(jdk.GCHeapConfiguration): 1
  Type(jdk.GCHeapConfiguration) 9/15/20 10:18:50 AM 6.5 MiB 7.99 GiB 512 MiB true Zero based 8 B 32 null
Type(http://www.oracle.com/hotspot/jdk/java/x509_validation): 0
Type(jdk.PSHeapSummary): 20
  Type(jdk.PSHeapSummary) [29]
  Type(jdk.PSHeapSummary) [29]
  Type(jdk.PSHeapSummary) [29]
  Type(jdk.PSHeapSummary) [29]
  Type(jdk.PSHeapSummary) [29]
  Type(jdk.PSHeapSummary) [29]
  Type(jdk.PSHeapSummary) [29]
  Type(jdk.PSHeapSummary) [29]
  Type(jdk.PSHeapSummary) [29]
  Type(jdk.PSHeapSummary) [29]
  Type(jdk.PSHeapSummary) [29]
  Type(jdk.PSHeapSummary) [29]
Type(jdk.JVMInformation): 1
  Type(jdk.JVMInformation) 9/15/20 10:18:50 AM Java HotSpot(TM) 64-Bit Server VM Java HotSpot(TM) 64-Bit Server VM (25.241-b07) for windows-amd64 JRE (1.8.0_241-b07), built on Dec 11 2019 10:21:40 by "rejava" with MS VC++ 10.0 (VS2010) -Djava.util.logging.config.file=C:\Users\Corrado Mio\AppData\Local\JetBrains\IntelliJIdea2020.2\tomcat\Unnamed_splproject2_6_3\conf\logging.properties -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager -Dvisualvm.id=68841956335182 -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:64828,suspend=y,server=n -javaagent:C:\Users\CORRAD~1\AppData\Local\Temp\captureAgent3480jars\debugger-agent.jar -javaagent:C:\Users\CORRAD~1\AppData\Local\Temp\groovyHotSwap1719jars\gragent.jar -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -Dcom.sun.management.jmxremote= -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.password.file=C:\Users\Corrado Mio\AppData\Local\JetBrains\IntelliJIdea2020.2\tomcat\Unnamed_splproject2_6_3\jmxremote.password -Dcom.sun.management.jmxremote.access.file=C:\Users\Corrado Mio\AppData\Local\JetBrains\IntelliJIdea2020.2\tomcat\Unnamed_splproject2_6_3\jmxremote.access -Djava.rmi.server.hostname=127.0.0.1 -Djdk.tls.ephemeralDHKeySize=2048 -Djava.protocol.handler.pkgs=org.apache.catalina.webresources -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dignore.endorsed.dirs= -Dcatalina.base=C:\Users\Corrado Mio\AppData\Local\JetBrains\IntelliJIdea2020.2\tomcat\Unnamed_splproject2_6_3 -Dcatalina.home=D:\Java\Tomcat-8.5.37 -Djava.io.tmpdir=D:\Java\Tomcat-8.5.37\temp  org.apache.catalina.startup.Bootstrap start 9/15/20 10:09:43 AM
Type(jdk.GCTLABConfiguration): 1
  Type(jdk.GCTLABConfiguration) 9/15/20 10:18:50 AM true 2 KiB 64 B null
Disconnected from the target VM, address: '127.0.0.1:49319', transport: 'socket'

Process finished with exit code 0
