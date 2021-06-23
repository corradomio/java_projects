package jext.net;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * parse a URL with the extended syntax:
 *
 *      scheme[://authority][/path][?query][(#|!|@)fragment]
 *
 *      scheme      ::=  [protocol:]* protocol:
 *                       [protocol+]* protocol:
 *      protocol    ::=  any seq di chars, '.', '-', '+'
 *      auhority    ::=  [userinfo@]host[:port]
 *      userinfo    ::=  username[:password]
 *      query       ::=  key=value[&key=value | ;key=value]*
 *      fragment    ::=  any seq of chars
 *
 *
 *  Inside a zip:
 *      scheme:[//authority][/path][#zpath]
 *      scheme:[//authority][/path][!/zpath]
 *
 */
public class URL {

    public static final String URL = "url";

    // ----------------------------------------------------------------------
    // Factory methods
    // ----------------------------------------------------------------------

    public static URL parse(String url) {
        return new URL(url);
    }

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    private static final String NONE = "";

    private String url;

    private String scheme;
    private String authority;
    private String path;
    private String query;
    private String fragment;

    private String[] protocols;
    private String   protocol;
    private String hostPort;
    private String   host;
    private String   port;
    private String userInfo;
    private String   username;
    private String   password;
    private Map<String,String> params = new HashMap<>();

    private static Pattern URL_PATTERN = Pattern.compile("" +
        "([^/?#!]{3,})" + "?" +     // schema (optional)
        "(//[^/?#!]*)"  + "?" +     // authority (optional)
        "(/[^?#!]*)"    + "?" +     // path (optional)
        "(\\?[^#!@]*)"  + "?" +     // query (optional)
        "((.*))"                    // fragment (optional)
    );

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public URL(String url) {
        this.url = url;
        normalize();
        parse();
        parseParts();
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public String getUrl() { return getUrl(false); }

    public String getUrl(boolean skipFirstProtocol) {
        if (!skipFirstProtocol)
            return url;

        // {protocol1}:...{protocol}://{rest}
        // {protocol1}+...{protocol}://{rest}

        int pos = url.indexOf("://");
        String protocols = url.substring(0, pos);

        int ppos = protocols.indexOf(':');
        if (ppos == -1)
            ppos = protocols.indexOf('+');
        if (ppos == -1)
            return url;
        else
            return url.substring(ppos+1);
    }

    public boolean hasUserInfo() {
        return this.params.containsKey(USERNAME) && this.params.containsKey(PASSWORD);
    }

    public String getProtocol() {
        return protocol;
    }

    public String getProtocols() {
        if (protocols.length <= 1)
            return protocol;

        String proto = protocol;
        for(int i=1; i<protocols.length; ++i)
            proto += ":" + protocols[i];
        return proto;
    }

    public String getProtocolHostPort() {
        return String.format("%s://%s", protocol, hostPort);
    }

    public String getHostPort() { return hostPort; }

    public String getHostPortPath() {
        String hpp = hostPort;
        if (path != null && path.length() > 0)
            if (path.startsWith("/"))
                hpp += path;
            else
            hpp += "/" + path;
        return hpp;
    }

    public String getPath() { return path; }

    public String getFragment() {
        return fragment;
    }

    public Map<String,String> getParameters() { return params; }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public String toString() { return url; }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void normalize() {
        //   \ -> /
        if (url.indexOf('\\') != -1)
            url = url.replace('\\', '/');

        //  d:/...  ->  file:///d:/...
        if (url.indexOf(":/") == 1)
            url = "file:///" + url;

        //  /... -> file:///...
        if (url.startsWith("/"))
            url = "file://" + url;

        //  protocol://...//... -> protocol://.../...
        int pos = url.indexOf("://")+3;
        int ext = url.indexOf("//", pos);
        while (ext != -1) {
            url = url.substring(0, ext) + url.substring(ext+1);
            ext = url.indexOf("//", pos);
        }

        //  remove last slash: .../ -> ...
        while (url.endsWith("/"))
            url = url.substring(0, url.length()-1);
    }

    private void parse() {
        // scheme://authority/path?query#fragment!zpath

        Matcher mat = URL_PATTERN.matcher(url);
        if(mat.find()) {

            scheme = group(mat, 1);
            authority = group(mat, 2);
            path = group(mat, 3);
            query = group(mat, 4);
            fragment = group(mat, 5);
        }
        else {

            scheme = NONE;
            authority = NONE;
            path = NONE;
            query = NONE;
            fragment = NONE;
        }

        if (fragment.length() > 0)
            fragment = fragment.substring(1);
    }

    private String group(Matcher mat, int g) {
        String value = mat.group(g);
        return value != null ? value : NONE;
    }

    private void parseParts() {

        int pos;
        // String userInfo, username, password;

        // schema ::= protocol[:protocol]*:
        //            protocol[+protocol]*:
        {
            protocols = scheme.split(":+");
            protocol = protocols[0];
        }

        // auhority ::= //[userinfo@]host[:port]
        {
            pos = authority.indexOf('@');
            if (pos != -1) {
                userInfo = authority.substring(2,pos);
                hostPort = authority.substring(pos+1);
            }
            else {
                userInfo = NONE;
                hostPort = authority.substring(2);
            }
        }

        // userInfo ::= username[:password]
        {
            pos = userInfo.indexOf(':');
            if (pos != -1) {
                username = userInfo.substring(0,pos);
                password = userInfo.substring(pos+1);

                this.params.put(USERNAME, username);
                this.params.put(PASSWORD, password);
            }
            else {
                username = NONE;
                password = NONE;
            }
        }

        // hostPort ::= host[:port]
        {
            pos = hostPort.indexOf(':');
            if (pos != -1) {
                host = hostPort.substring(0, pos);
                port = hostPort.substring(pos+1);
            }
            else {
                host = hostPort;
                port = NONE;
            }
        }

        // path ::= /...
        {
            if (path.startsWith("/") && path.length() >= 3 && path.charAt(2) == ':')
                path = path.substring(1);
        }

        // query ::= ?key=value [&key=value]*
        //                      [;key=value]*
        {
            // <([{\^-=$!|]})?*+.>
            String name, value;
            String[] params = query.split("[?&;,]");
            for(String param : params) {
                pos = param.indexOf('=');
                if (pos != -1) {
                    name = param.substring(0, pos);
                    value = param.substring(pos+1);
                }
                else {
                    name = param;
                    value = NONE;
                }

                if (name.length() > 0)
                    this.params.put(name, value);
            }

        }

    }

    // ----------------------------------------------------------------------
    // Debug
    // ----------------------------------------------------------------------

    public void dump() {
        System.out.println(String.format("url:       %s", url));
        System.out.println(String.format("scheme:    %s", scheme));
        System.out.println(String.format("authority: %s", authority));
        System.out.println(String.format("path:      %s", path));
        System.out.println(String.format("query:     %s", query));
        System.out.println(String.format("fragment:  %s", fragment));
        System.out.println(String.format("userInfo:  %s", userInfo));
        System.out.println(String.format("  username:%s", username));
        System.out.println(String.format("  password:%s", password));
        System.out.println(String.format("hostPort:  %s", hostPort));
        System.out.println(String.format("  host:    %s", host));
        System.out.println(String.format("  port:    %s", port));
        System.out.println(String.format("params"));
        for(String key : params.keySet())
            System.out.println(String.format("  %s:\t%s", key, params.get(key)));
    }
}
