package jext.vfs;

public abstract class FileSelectors {

    public static final VFileSelector IS_FOLDER = new VFileSelector() {
        public boolean accept(VFile file) {
            return file.isFolder();
        }
    };

    public static final VFileSelector IS_FILE = new VFileSelector() {
        public boolean accept(VFile file) {
            return file.isFile();
        }
    };

    public static final VFileSelector IS_SPL = new VFileSelector() {
        @Override
        public boolean accept(VFile file) {
            return file.getName().equals(".spl");
        }
    };

    public static final VFileSelector IS_HIDDEN = new VFileSelector() {
        @Override
        public boolean accept(VFile file) {
            return file.getName().startsWith(".");
        }
    };

    public static final VFileSelector IS_HIDDEN_FOLDER = new VFileSelector() {
        @Override
        public boolean accept(VFile file) {
            return file.getName().startsWith(".") && file.isFolder();
        }
    };

}
