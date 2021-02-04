package jext.compress.rar;

import com.github.junrar.Archive;
import com.github.junrar.Volume;
import com.github.junrar.VolumeManager;

import java.io.IOException;

public class ByteArrayVolumeManager implements VolumeManager {

    private byte[] content;

    public ByteArrayVolumeManager(byte[] c) {
        content = c;
    }


    @Override
    public Volume nextArchive(Archive archive, Volume lastVolume) throws IOException {
        if (lastVolume == null)
            return new ByteArrayVolume(archive, content);
        return null;
    }
}
