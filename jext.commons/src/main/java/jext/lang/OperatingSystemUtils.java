package jext.lang;

public class OperatingSystemUtils {

    public static OperatingSystem getOperatingSystem() {
        String osname = System.getProperty("os.name");
        if (osname.contains("Windows"))
            return OperatingSystem.WINDOWS;
        else if (osname.contains("Mac"))
            return OperatingSystem.MACOS;
        else if (osname.contains("Linux"))
            return OperatingSystem.LINUX;
        else
            return OperatingSystem.UNKNOWN;
    }
}
