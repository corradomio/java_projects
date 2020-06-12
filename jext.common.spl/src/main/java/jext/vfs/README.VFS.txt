public interface UserAuthenticator {
    UserAuthenticationData requestAuthentication(UserAuthenticationData.Type[] types);


public interface RandomAccessContent extends DataOutput, DataInput {
    void close() throws IOException;
    long getFilePointer() throws IOException;
    InputStream getInputStream() throws IOException;
    long length() throws IOException;
    void seek(long pos) throws IOException;
    void setLength(long newLength) throws IOException;


public interface FileSystemManager {
    FileObject getBaseFile() throws FileSystemException;

    FileObject resolveFile(String name) throws FileSystemException;
    FileObject resolveFile(String name, FileSystemOptions fileSystemOptions) throws FileSystemException;
    FileObject resolveFile(FileObject baseFile, String name) throws FileSystemException;
    FileObject resolveFile(File baseFile, String name) throws FileSystemException;
    FileObject resolveFile(URI uri) throws FileSystemException;
    FileObject resolveFile(URL url) throws FileSystemException;

    FileName resolveName(FileName root, String name) throws FileSystemException;
    FileName resolveName(FileName root, String name, NameScope scope) throws FileSystemException;
    FileName resolveURI(String uri) throws FileSystemException;

    FileObject toFileObject(File file) throws FileSystemException;
    FileObject createFileSystem(String provider, FileObject file) throws FileSystemException;
    FileObject createFileSystem(FileObject file) throws FileSystemException;
    FileObject createVirtualFileSystem(String rootUri) throws FileSystemException;
    FileObject createVirtualFileSystem(FileObject rootFile) throws FileSystemException;

    void closeFileSystem(FileSystem filesystem);

    URLStreamHandlerFactory getURLStreamHandlerFactory();

    boolean canCreateFileSystem(FileObject file) throws FileSystemException;
    FilesCache getFilesCache();
    CacheStrategy getCacheStrategy();

    Class<?> getFileObjectDecorator();
    Constructor<?> getFileObjectDecoratorConst();
    FileContentInfoFactory getFileContentInfoFactory();

    String[] getSchemes();
    boolean hasProvider(String scheme);
    Collection<Capability> getProviderCapabilities(String scheme) throws FileSystemException;

    void setLogger(Log log);

    void addOperationProvider(String scheme, FileOperationProvider operationProvider) throws FileSystemException;
    void addOperationProvider(String[] schemes, FileOperationProvider operationProvider) throws FileSystemException;
    FileOperationProvider[] getOperationProviders(String scheme) throws FileSystemException;

    FileSystemConfigBuilder getFileSystemConfigBuilder(String scheme) throws FileSystemException;


public interface FileSystem {
    FileObject getRoot() throws FileSystemException;
    FileName getRootName();
    String getRootURI();

    boolean hasCapability(Capability capability);

    FileObject getParentLayer() throws FileSystemException;

    Object getAttribute(String attrName) throws FileSystemException;
    void setAttribute(String attrName, Object value) throws FileSystemException;

    FileObject resolveFile(FileName name) throws FileSystemException;
    FileObject resolveFile(String name) throws FileSystemException;

    void addListener(FileObject file, FileListener listener);
    void removeListener(FileObject file, FileListener listener);

    void addJunction(String junctionPoint, FileObject targetFile) throws FileSystemException;
    void removeJunction(String junctionPoint) throws FileSystemException;

    File replicateFile(FileObject file, FileSelector selector) throws FileSystemException;

    FileSystemOptions getFileSystemOptions();
    FileSystemManager getFileSystemManager();
    double getLastModTimeAccuracy();


public interface FileSelector {
    boolean includeFile(FileSelectInfo fileInfo) throws Exception;
    boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception;


public interface FileSelectInfo {
    FileObject getBaseFolder();
    FileObject getFile();
    int getDepth();


public interface FilesCache {
    void putFile(final FileObject file);
    boolean putFileIfAbsent(final FileObject file);
    FileObject getFile(final FileSystem filesystem, final FileName name);
    void clear(final FileSystem fileSystem);
    void close();
    void removeFile(final FileSystem filesystem, final FileName name);



public interface FileObject extends Comparable<FileObject>, Iterable<FileObject>, Closeable {
    boolean canRenameTo(FileObject newfile);
    void close() throws FileSystemException;
    void copyFrom(FileObject srcFile, FileSelector selector) throws FileSystemException;

    void createFile() throws FileSystemException;
    void createFolder() throws FileSystemException;

    boolean delete() throws FileSystemException;
    int delete(FileSelector selector) throws FileSystemException;
    int deleteAll() throws FileSystemException;

    boolean exists() throws FileSystemException;

    FileObject[] findFiles(FileSelector selector) throws FileSystemException;
    void findFiles(FileSelector selector, boolean depthwise, List<FileObject> selected) throws FileSystemException;

    FileObject getChild(String name) throws FileSystemException;
    FileObject[] getChildren() throws FileSystemException;

    FileContent getContent() throws FileSystemException;
    FileOperations getFileOperations() throws FileSystemException;

    FileSystem getFileSystem();
    FileName getName();

    FileObject getParent() throws FileSystemException;
    String getPublicURIString();

    FileType getType() throws FileSystemException;
    URL getURL() throws FileSystemException;

    boolean isAttached();
    boolean isContentOpen();
    boolean isExecutable() throws FileSystemException;
    boolean isFile() throws FileSystemException;
    boolean isFolder() throws FileSystemException;
    boolean isHidden() throws FileSystemException;
    boolean isReadable() throws FileSystemException;
    boolean isWriteable() throws FileSystemException;

    void moveTo(FileObject destFile) throws FileSystemException;

    void refresh() throws FileSystemException;
    FileObject resolveFile(String path) throws FileSystemException;
    FileObject resolveFile(String name, NameScope scope) throws FileSystemException;

    boolean setExecutable(boolean executable, boolean ownerOnly) throws FileSystemException;
    boolean setReadable(boolean readable, boolean ownerOnly) throws FileSystemException;
    boolean setWritable(boolean writable, boolean ownerOnly) throws FileSystemException;



public interface FileName extends Comparable<FileName> {
    char SEPARATOR_CHAR = '/';
    String SEPARATOR = "/";
    String ROOT_PATH = "/";

    String getBaseName();
    String getPath();

    String getPathDecoded() throws FileSystemException;
    String getExtension();
    int getDepth();

    String getScheme();
    String getURI();
    String getRootURI();

    FileName getRoot();
    FileName getParent();

    String getRelativeName(FileName name) throws FileSystemException;

    boolean isAncestor(FileName ancestor);
    boolean isDescendent(FileName descendent);
    boolean isDescendent(FileName descendent, NameScope nameScope);

    boolean isFile() throws FileSystemException;

    FileType getType();
    String getFriendlyURI();
