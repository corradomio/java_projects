package org.hls.check;

import org.yuanheng.cookjson.CookJsonProvider;

import javax.json.spi.JsonProvider;
import javax.json.stream.JsonParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class CheckBJSON {

    public static void main(String[] args) throws FileNotFoundException {
        File srcFile = null;
        JsonProvider provider = new CookJsonProvider();
        HashMap<String, Object> config = new HashMap<String, Object> ();
        // Either Boolean.TRUE or "true" can be used.
        config.put (CookJsonProvider.COMMENT, Boolean.TRUE);
        JsonParser p = provider.createParserFactory (config).createParser (new FileInputStream(srcFile));
    }
}
