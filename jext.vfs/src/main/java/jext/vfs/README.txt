

VFileSystem fs = VFileSystemManager.getFileSystem(...)
          fs.connect();
VFile r = fs.getRoot();
          fs.close()

VFile p       = f.getParentFile()
List<VFile> l = f.listFiles()
                f.listFiles(selector)