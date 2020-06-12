java.io.File
-----------------------------------------------------------------------------


    public class File implements Serializable, Comparable<File>
        public static final char separatorChar = fs.getSeparator();
        public static final String separator = "" + separatorChar;
        public static final char pathSeparatorChar = fs.getPathSeparator();
        public static final String pathSeparator = "" + pathSeparatorChar;

        public File(String pathname) {
        public File(String parent, String child) {
        public File(File parent, String child) {
        public File(URI uri) {

       +public String getName() {
       +public String getPath() {

       -public String getParent() {
       +public File getParentFile() {

       -public boolean isAbsolute() {
       -public String getAbsolutePath() {
       -public File getAbsoluteFile() {
       -public String getCanonicalPath() throws IOException {
       -public File getCanonicalFile() throws IOException {

        public URI toURI() {

       +public boolean exists() {
       +public boolean isDirectory() {
       +public boolean isFile() {
        public boolean isHidden() {

        public boolean canRead() {
        public boolean canWrite() {
        public boolean canExecute() {

       +public long lastModified() {
       +public long length() {

        public boolean createNewFile() throws IOException {
       +public boolean delete() {

        public String[] list() {
        public String[] list(FilenameFilter filter) {
        public File[]   listFiles() {
        public File[]   listFiles(FilenameFilter filter) {
        public File[]   listFiles(FileFilter filter) {

        public boolean mkdir() {
        public boolean mkdirs() {

        public boolean renameTo(File dest) {

        public boolean setLastModified(long time) {

        public boolean setReadOnly() {
        public boolean setWritable(boolean writable, boolean ownerOnly) {
        public boolean setWritable(boolean writable) {
        public boolean setReadable(boolean readable, boolean ownerOnly) {
        public boolean setReadable(boolean readable) {
        public boolean setExecutable(boolean executable, boolean ownerOnly) {
        public boolean setExecutable(boolean executable) {

        public static File[] listRoots() {
        public long getTotalSpace() {
        public long getFreeSpace() {
        public long getUsableSpace() {

        public static File createTempFile(String prefix, String suffix, File directory) throws IOException
        public static File createTempFile(String prefix, String suffix) throws IOException

        public int compareTo(File pathname) {
        public boolean equals(Object obj) {
        public int hashCode() {
        public String toString() {

        public Path toPath() {
    }
