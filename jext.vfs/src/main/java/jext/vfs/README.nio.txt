public interface Path extends Comparable<Path>, Iterable<Path>, Watchable
    FileSystem getFileSystem();
    boolean isAbsolute();
    Path getRoot();
    Path getFileName();
    Path getParent();
    int getNameCount();
    Path getName(int index);
    Path subpath(int beginIndex, int endIndex);
    boolean startsWith(Path other);
    boolean startsWith(String other);
    boolean endsWith(Path other);
    boolean endsWith(String other);
    Path normalize();
    Path resolve(Path other);
    Path resolve(String other);
    Path resolveSibling(Path other);
    Path resolveSibling(String other);
    Path relativize(Path other);

    URI toUri();
    Path toAbsolutePath();
    Path toRealPath(LinkOption... options) throws IOException;
    File toFile();

    WatchKey register(WatchService watcher, WatchEvent.Kind<?>[] events, WatchEvent.Modifier... modifiers) throws IOException;
    WatchKey register(WatchService watcher, WatchEvent.Kind<?>... events) throws IOException;
    Iterator<Path> iterator();

    int compareTo(Path other);

    boolean equals(Object other);
    int hashCode();
    String toString();



public abstract class FileSystem implements Closeable
    public abstract FileSystemProvider provider();
    public abstract void close() throws IOException;
    public abstract boolean isOpen();
    public abstract boolean isReadOnly();
    public abstract String getSeparator();
    public abstract Iterable<Path> getRootDirectories();
    public abstract Iterable<FileStore> getFileStores();
    public abstract Set<String> supportedFileAttributeViews();
    public abstract Path getPath(String first, String... more);
    public abstract PathMatcher getPathMatcher(String syntaxAndPattern);
    public abstract UserPrincipalLookupService getUserPrincipalLookupService();
    public abstract WatchService newWatchService() throws IOException;



public final class FileSystems {
    public static FileSystem getDefault() {
    public static FileSystem getFileSystem(URI uri) {
    public static FileSystem newFileSystem(URI uri, Map<String,?> env) throws IOException
    public static FileSystem newFileSystem(URI uri, Map<String,?> env, ClassLoader loader) throws IOException
    public static FileSystem newFileSystem(Path path, ClassLoader loader) throws IOException



public final class Paths {
    public static Path get(String first, String... more) {
    public static Path get(URI uri) {




public final class Files {
    public static InputStream newInputStream(Path path, OpenOption... options) throws IOException
    public static OutputStream newOutputStream(Path path, OpenOption... options) throws IOException
    public static SeekableByteChannel newByteChannel(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs) throws IOException
    public static SeekableByteChannel newByteChannel(Path path, OpenOption... options) throws IOException
    public static DirectoryStream<Path> newDirectoryStream(Path dir) throws IOException
    public static DirectoryStream<Path> newDirectoryStream(Path dir, String glob) throws IOException
    public static DirectoryStream<Path> newDirectoryStream(Path dir, DirectoryStream.Filter<? super Path> filter) throws IOException
    public static Path createFile(Path path, FileAttribute<?>... attrs) throws IOException
    public static Path createDirectory(Path dir, FileAttribute<?>... attrs) throws IOException
    public static Path createDirectories(Path dir, FileAttribute<?>... attrs) throws IOException
    public static Path createTempFile(Path dir, String prefix, String suffix, FileAttribute<?>... attrs) throws IOException
    public static Path createTempFile(String prefix, String suffix, FileAttribute<?>... attrs) throws IOException
    public static Path createTempDirectory(Path dir, String prefix, FileAttribute<?>... attrs) throws IOException
    public static Path createTempDirectory(String prefix, FileAttribute<?>... attrs) throws IOException
    public static Path createSymbolicLink(Path link, Path target, FileAttribute<?>... attrs) throws IOException
    public static Path createLink(Path link, Path existing) throws IOException {
    public static void delete(Path path) throws IOException {
    public static boolean deleteIfExists(Path path) throws IOException {
    public static Path copy(Path source, Path target, CopyOption... options) throws IOException
    public static Path move(Path source, Path target, CopyOption... options) throws IOException
    public static Path readSymbolicLink(Path link) throws IOException {
    public static FileStore getFileStore(Path path) throws IOException {
    public static boolean isSameFile(Path path, Path path2) throws IOException {
    public static boolean isHidden(Path path) throws IOException {
    public static String probeContentType(Path path) throws IOException
    public static <V extends FileAttributeView> V getFileAttributeView(Path path, Class<V> type, LinkOption... options)
    public static <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> type, LinkOption... options) throws IOException
    public static Path setAttribute(Path path, String attribute, Object value, LinkOption... options) throws IOException
    public static Object getAttribute(Path path, String attribute, LinkOption... options) throws IOException
    public static Map<String,Object> readAttributes(Path path, String attributes, LinkOption... options) throws IOException
    public static Set<PosixFilePermission> getPosixFilePermissions(Path path, LinkOption... options) throws IOException
    public static Path setPosixFilePermissions(Path path,vSet<PosixFilePermission> perms) throws IOException
    public static UserPrincipal getOwner(Path path, LinkOption... options) throws IOException {
    public static Path setOwner(Path path, UserPrincipal owner)vthrows IOException
    public static boolean isSymbolicLink(Path path) {
    public static boolean isDirectory(Path path, LinkOption... options) {
    public static boolean isRegularFile(Path path, LinkOption... options) {
    public static FileTime getLastModifiedTime(Path path, LinkOption... options) throws IOException
    public static Path setLastModifiedTime(Path path, FileTime time) throws IOException
    public static long size(Path path) throws IOException {
    public static boolean exists(Path path, LinkOption... options) {
    public static boolean notExists(Path path, LinkOption... options) {
    public static boolean isReadable(Path path) {
    public static boolean isWritable(Path path) {
    public static boolean isExecutable(Path path) {
    public static Path walkFileTree(Path onStart, Set<FileVisitOption> options, int maxDepth, FileVisitor<? super Path> visitor) throws IOException
    public static Path walkFileTree(Path onStart, FileVisitor<? super Path> visitor) throws IOException
    public static BufferedReader newBufferedReader(Path path, Charset cs) throws IOException
    public static BufferedReader newBufferedReader(Path path) throws IOException {
    public static BufferedWriter newBufferedWriter(Path path, Charset cs, OpenOption... options) throws IOException
    public static BufferedWriter newBufferedWriter(Path path, OpenOption... options) throws IOException {
    public static long copy(InputStream in, Path target, CopyOption... options) throws IOException
    public static long copy(Path source, OutputStream out) throws IOException {
    public static byte[] readAllBytes(Path path) throws IOException {
    public static List<String> readAllLines(Path path, Charset cs) throws IOException {
    public static List<String> readAllLines(Path path) throws IOException {
    public static Path write(Path path, byte[] bytes, OpenOption... options) throws IOException
    public static Path write(Path path, Iterable<? extends CharSequence> lines, Charset cs, OpenOption... options) throws IOException
    public static Path write(Path path, Iterable<? extends CharSequence> lines, OpenOption... options) throws IOException
    public static Stream<Path> list(Path dir) throws IOException {
    public static Stream<Path> walk(Path onStart, int maxDepth, FileVisitOption... options) throws IOException
    public static Stream<Path> walk(Path onStart, FileVisitOption... options) throws IOException {
    public static Stream<Path> find(Path onStart, int maxDepth, BiPredicate<Path, BasicFileAttributes> matcher, FileVisitOption... options) throws IOException
    public static Stream<String> lines(Path path, Charset cs) throws IOException {
    public static Stream<String> lines(Path path) throws IOException {
