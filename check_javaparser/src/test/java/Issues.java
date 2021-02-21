import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Issues {

    public static Path adaptPath(String relativePath) {
        return Paths.get(relativePath);
    }
}
