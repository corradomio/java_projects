package ae.ac.ebtic.sql.bt.btproxy;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.logging.Logger;

public class POSTUtils {

    private static Logger logger = Logger.getLogger(POSTUtils.class.getCanonicalName());

    public static void setBody(HttpPost post, Map<String, Object> body) throws UnsupportedEncodingException {
        String sbody = JSONUtils.serialize(body);

        logger.fine(sbody);

        post.setHeader("Content-Type", "application/json");
        post.setHeader("User-Agent", "ae.ac.ebtic.sql.bt.btproxy");
        post.setEntity(new StringEntity(sbody));
    }

    public static void setToken(HttpPost post, String token) {
        post.setHeader("Authorization", String.format("Bearer %s", token));
    }
}
