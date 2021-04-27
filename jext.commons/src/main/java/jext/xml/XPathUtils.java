package jext.xml;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class XPathUtils {

    private static final Properties NO_PROPERTIES = new Properties();
    private static final String EMPTY_VALUE = "";

    private static EntityResolver SKIP_ENTITY_RESOLVER = new EntityResolver() {
        @Override
        public InputSource resolveEntity(String publicId, String systemId) {
            return new InputSource(new ByteArrayInputStream(EMPTY_VALUE.getBytes()));
        }
    };

    private static ErrorHandler SKIP_ERROR_HANDLER = new ErrorHandler() {
        @Override
        public void warning(SAXParseException exception) { }

        @Override
        public void error(SAXParseException exception) { }

        @Override
        public void fatalError(SAXParseException exception) { }
    };

    // ----------------------------------------------------------------------
    // Parse
    // ----------------------------------------------------------------------

    public static Document parse(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        factory.setFeature("http://apache.org/xml/features/continue-after-fatal-error", true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setEntityResolver(SKIP_ENTITY_RESOLVER);
        builder.setErrorHandler(SKIP_ERROR_HANDLER);
        return builder.parse(file);
    }

    public static Document parse(InputStream stream) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(stream);
    }

    public static Document parse(InputStream stream, boolean close) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(stream);
        if (close) stream.close();
        return doc;
    }

    public static Document parse(Class clazz, String resourcePath) throws IOException, SAXException, ParserConfigurationException {
        Document doc;
        InputStream stream;

        if (clazz == null) clazz = XPathUtils.class;
        stream = clazz.getResourceAsStream(resourcePath);
        doc = parse(stream, true);
        return doc;
    }

    public static Document parse(String xml) throws ParserConfigurationException, IOException, SAXException {
        InputStream stream = new ByteArrayInputStream(xml.getBytes());
        return parse(stream);
    }

    // ----------------------------------------------------------------------
    // Serialize
    // ----------------------------------------------------------------------

    public static void serialize(Document doc, Writer out) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(new DOMSource(doc), new StreamResult(out));
    }

    public static void serialize(Document doc, Writer out, boolean close) throws TransformerException, IOException {
        serialize(doc, out);
        if (close) out.close();
    }

    public static void serialize(Document doc, OutputStream out) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(new DOMSource(doc), new StreamResult(out));
    }

    public static void serialize(Document doc, OutputStream out, boolean close) throws TransformerException, IOException {
        serialize(doc, out);
        if (close) out.close();
    }

    public static String serialize(Document doc) throws TransformerFactoryConfigurationError, TransformerException {
        StringWriter out = new StringWriter();
        serialize(doc, out);
        return out.toString();
    }

    public static void serialize(Document doc, File file) throws TransformerException, IOException {
        FileWriter fw = new FileWriter(file);
        serialize(doc, fw, true);
    }

    // ----------------------------------------------------------------------
    // Resolve parameters
    // ----------------------------------------------------------------------
    // Replace "${name}" with the value passed as argument
    //

    public static Document resolveParameters(Document doc, Properties params) {
        resolveParameters(doc.getDocumentElement(), params);
        return doc;
    }

    public static void resolveParameters(Element el, Properties params) {
        // resolve for attributes
        NamedNodeMap attrs = el.getAttributes();
        for(int i=0; i<attrs.getLength(); ++i)
            resolveParams(attrs.item(i), params);

        // resolve for children
        NodeList nl = el.getChildNodes();
        for(int i=0; i<nl.getLength(); ++i) {
            Node node = nl.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
                resolveParams((Element)node, params);
            else
                resolveParams(node, params);
        }
    }

    private static String resolveParams(String text, Properties params, boolean original) {
        boolean updated = false;
        if (text == null || text.length() == 0)
            return text;

        int pos = -1, end;
        while((pos = text.indexOf("${", pos+1)) != -1) {
            String name, vvalue;

            // handle '${name...'  (missing '}')
            end = text.indexOf("}", pos+1);
            if (end != -1)
                name = text.substring(pos+2,end);
            else
                name = text.substring(pos+2);

            // ${env:...}
            if (name.startsWith("env:")) {
                vvalue = System.getenv(name.substring(4));
            }
            else if (name.startsWith("sys:")) {
                vvalue = System.getProperty(name.substring(4));
            }
            else {
                vvalue = params.containsKey(name) ? params.get(name).toString() : null;
            }

            // if the value is null, doesn't replace "${...}"
            if (vvalue == null) continue;

            text = text.substring(0,pos) + vvalue + text.substring(end+1);
            updated = true;
            pos = -1;
        }

        return (updated || original) ? text.trim() : null;
    }

    private static void resolveParams(Node node, Properties params) {
        if (params == null) return;
        String text = node.getTextContent().trim();
        String newt = resolveParams(text, params, false);
        if (newt != null)
            node.setTextContent(newt);
    }

    // ----------------------------------------------------------------------
    // getValue
    // ----------------------------------------------------------------------

    public static String getValue(Element elt, String xpath) {
        return getValue(elt, xpath, EMPTY_VALUE, NO_PROPERTIES);
    }

    public static String getValue(Element elt, String xpath, String defaultValue) {
        return getValue(elt, xpath, defaultValue, NO_PROPERTIES);
    }

    public static String getValue(Element elt, String xpath, String defaultValue, Properties params) {
        Node node = selectNode(elt, xpath, false, params);
        if (node == null) return defaultValue;
        String value = node.getTextContent().trim();
        return resolveValue(value, params);
    }

    public static List<String> getValues(Element elt, String xpath, int limit, Properties params) {
        List<String> values = new ArrayList<>();
        List<Node> selected = selectNodes(elt, xpath, limit, params);

        for(Node sel : selected) {
            String value = sel.getTextContent().trim();
            value = resolveValue(value, params);
            values.add(value);
        }

        return values;
    }

    private static String resolveValue(String value, Properties params) {
        return resolveParams(value, params, true);
    }

    // ----------------------------------------------------------------------

    public static boolean getValue(Element elt, String xpath, boolean defaultValue, Properties params) {
        String svalue = getValue(elt, xpath, EMPTY_VALUE, params);
        if (svalue.length() == 0)
            return defaultValue;
        else
            return Boolean.parseBoolean(svalue);
    }

    public static int getValue(Element elt, String xpath, int defaultValue, Properties params) {
        String svalue = getValue(elt, xpath, EMPTY_VALUE, params);
        if (svalue.length() == 0)
            return defaultValue;
        else
            return Integer.parseInt(svalue);
    }

    public static long getValue(Element elt, String xpath, long defaultValue, Properties params) {
        String svalue = getValue(elt, xpath, EMPTY_VALUE, params);
        if (svalue.length() == 0)
            return defaultValue;
        else
            return Long.parseLong(svalue);
    }

    // ----------------------------------------------------------------------
    // getValues
    // ----------------------------------------------------------------------

    public static List<String> getValues(Element elt, String xpath) {
        List<String> values = new ArrayList<>();
        List<Node> nodes = selectNodes(elt, xpath);
        for(Node node : nodes)
            values.add(node.getTextContent().trim());
        return values;
    }

    public static List<String> getValues(Element elt, String xpath, String attribute) {
        List<String> values = new ArrayList<>();
        List<Element> nodes = selectElements(elt, xpath);
        for(Element node : nodes)
            values.add(node.getAttribute(attribute));
        return values;
    }

    // ----------------------------------------------------------------------
    // getProperties()
    // ----------------------------------------------------------------------
    //  <root>
    //      <property name=..." value="..."/>
    //      <property name=...">value</property>
    //

    public static Properties getProperties(Element elt, String xpath) {

        return getProperties((Element)selectNode(elt, xpath));
    }

    public static Properties getProperties(Element elt) {
        Properties properties = new Properties();
        for(Element pelt : selectElements(elt, "property")) {
            String name = getValue(pelt, "@name");
            String value = getValue(pelt, "@value", null);
            if (value == null)
                value = getValue(pelt, "#text", "");

            properties.put(name, value);
        }
        return properties;
    }


    // ----------------------------------------------------------------------
    // Compose the XML
    // ----------------------------------------------------------------------
    // Set the value of the specified path.
    // Support a reduced XPath syntax:
    //
    //      .../name                : element
    //      .../@attr               : attribute
    //      .../name[index]         : index can be ${...} or -1 for append
    //      .../name[@attr=value]   : value can be ${...}
    //

    public static Node setValue(Document doc, String xpath, String value, Properties params) {
        return setValue(doc.getDocumentElement(), xpath, value, params);
    }

    public static Node setValue(Element elt, String xpath, String value, Properties params) {
        Node node = selectNode(elt, xpath, true, params);
        node.setTextContent(value);
        return node;
    }

    public static Node selectNode(Document doc, String xpath, boolean create, Properties params) {
        return selectNode(doc.getDocumentElement(), xpath, create, params);
    }

    public static void deleteNodes(Document doc, String xpath, Properties props) {

        // not efficient but simple implementation
        Node selected;
        while((selected = selectNode(doc,  xpath,  false,  props)) != null) {
            Node parent = selected.getParentNode();
            parent.removeChild(selected);
        }

    }

    // ----------------------------------------------------------------------
    // Exist nodes
    // ----------------------------------------------------------------------

    public static boolean existsNode(Element elt, String xpath, Properties params) {
        return selectNode(elt, xpath, false, params) != null;
    }

    // ----------------------------------------------------------------------
    // Select nodes
    // ----------------------------------------------------------------------

    public static List<Node> selectNodes(Element elt, String xpath) {
        return selectNodes(elt, xpath, Integer.MAX_VALUE, NO_PROPERTIES);
    }

    /**
     *
     * Support parent/elt,  parent/elt[@attr='value']
     *
     * @param elt
     * @param xpath
     * @param limit
     * @param params
     * @return
     */
    public static List<Node> selectNodes(Element elt, String xpath, int limit, Properties params) {
        Document owner = getOwnerDocument(elt);
        synchronized (owner) {
            List<Node> selected = new ArrayList<>();
            String aname = null, avalue = "";

            if (xpath.startsWith("/"))
                xpath = "<root>" + xpath;

            // parent/elt
            // parent/elt[@attr='value']

            String parentPath = parentOf(xpath);
            String step = stepOf(xpath);

            // ename[@aname=avalue]
            // ename[@name]
            if (step.contains("[")) {
                aname = anameOf(step);
                avalue = avalueOf(step, params);
                // KEEP last
                step = enameOf(step);
            }

            Element parentNode = (Element) selectNode(elt, parentPath, false, params);
            if (parentNode == null)
                return selected;

            NodeList children = parentNode.getChildNodes();
            for (int i = 0; i < children.getLength(); ++i) {
                Node node = children.item(i);
                if (node.getNodeType() != Node.ELEMENT_NODE)
                    continue;
                if (!"*".equals(step) && !node.getNodeName().equals(step))
                    continue;

                Element enode = (Element) node;

                // check for "ename[@aname=avalue]"
                if (aname != null && avalue != null) {
                    String value = enode.getAttribute(aname);
                    if (!avalue.equals(value))
                        continue;
                }
                // check for "ename[@aname]"
                else if (aname != null) {
                    Node attr = ((Element)node).getAttributeNode(aname);
                    selected.add(attr);
                }
                else {
                    selected.add(node);
                }

            }

            return selected;
        }
    }

    public static List<Element> selectElements(Element elt, String xpath) {
        return selectElements(elt, xpath, Integer.MAX_VALUE, NO_PROPERTIES);
    }

    public static List<Element> selectElements(Element elt, String xpath, int limit, Properties params) {
        List<?> nodes = selectNodes(elt, xpath, limit, params);
        return (List<Element>) nodes;
    }

    // ----------------------------------------------------------------------
    // Select node
    // ----------------------------------------------------------------------

    public static Node selectNode(Element elt, String xpath) {
        return selectNode(elt, xpath, false, NO_PROPERTIES);
    }

    /**
     * ]
     * @param elt
     * @param xpath
     * @param create
     * @param params
     * @return
     */
    public static Node selectNode(Element elt, String xpath, boolean create, Properties params) {
        Document owner = getOwnerDocument(elt);
        synchronized (owner) {
            Node current = elt;
            if (xpath.startsWith("/"))
                xpath = "<root>" + xpath;

            String[] steps = xpath.split("/");
            for (String step : steps) {

                // no node
                if (current == null)
                    break;

                // "."
                if (step.length() == 0 || ".".equals(step))
                    continue;

                    // ".."
                else if ("..".equals(step))
                    current = current.getParentNode();

                    // "/..."
                else if ("<root>".equals(step))
                    current = owner;

                    // "@attr"
                else if (step.startsWith("@"))
                    current = selectAttribute(current, step.substring(1), create);

                // #text | text()
                else if (step.equals("#text") || step.equals("text()"))
                    current = selectTextNode(current, step, create);

                // name
                else if (!step.contains("[") && !step.contains("("))
                    current = selectElement(current, step, create);

                // name[@attr=value] | name[@attr='value'] | name[@attr="value"]
                else if (step.contains("[@") && step.contains("="))
                    current = selectPredNode(current, step, create, params);

                // node[@attr]
                else if (step.contains("[@"))
                    current = selectElementAttribute(current, step, create);

                // node[index]   ONE-based!!!!
                // noe(index)   ZERO-based!!!
                else if (step.contains("[") || step.contains("("))
                    current = selectIndexedNode(current, step, create, params);
            }

            return current;
        }
    }

    private static Node selectAttribute(Node current, String aname, boolean create) {
        if (current == null)
            return null;
        Attr attr = ((Element) current).getAttributeNode(aname);
        if (attr == null && create) {
            ((Element) current).setAttribute(aname, "");
            attr = ((Element) current).getAttributeNode(aname);
        }
        return attr;
    }

    private static Node selectElement(Node current, String step, boolean create) {
        Element selected = null;
        NodeList nl = current.getChildNodes();
        for (int i = 0; i < nl.getLength(); ++i) {
            Node node = nl.item(i);
            if (node == null)
                break;
            if (node.getNodeType() != Node.ELEMENT_NODE)
                continue;
            if (!step.equals(((Element) node).getTagName()))
                continue;

            selected = (Element) node;
            break;
        }

        if (selected == null && create) {
            Document doc = getOwnerDocument(current);
            selected = doc.createElement(step);
            current.appendChild(selected);
        }
        return selected;
    }

    private static Node selectPredNode(Node current, String step, boolean create, Properties params) {
        Element selected = null;
        String ename = enameOf(step);
        String aname = anameOf(step);
        String check = avalueOf(step, params);

        NodeList nl = current.getChildNodes();

        for (int i = 0; i < nl.getLength(); ++i) {
            Node node = nl.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE)
                continue;
            if (!ename.equals(((Element) node).getTagName()))
                continue;

            String value = ((Element) node).getAttribute(aname);
            if (!check.equals(value))
                continue;

            selected = (Element) node;
            break;
        }

        if (selected == null && create) {
            Document doc = getOwnerDocument(current);
            selected = doc.createElement(ename);
            current.appendChild(selected);
            current = selected;
            Attr attr = doc.createAttribute(aname);
            attr.setTextContent(check);
            current.appendChild(attr);
        }
        return selected;
    }

    private static Node selectIndexedNode(Node current, String step, boolean create, Properties params) {
        // ename(index) | ename[index]
        Element selected = null;
        String ename = enameOf(step);

        // original index ZERO-based
        int oindex = indexOf(step, params);
        // current index ZERO-based
        int cindex = oindex;

        NodeList nl = current.getChildNodes();

        for (int i = 0; i < nl.getLength() && cindex >= 0; ++i) {
            Node node = nl.item(i);

            // not a <name>
            if (node.getNodeType() != Node.ELEMENT_NODE)
                continue;

            // not <ename>
            if (!ename.equals(((Element) node).getTagName()))
                continue;

            // not the correct position
            if (--cindex >= 0)
                continue;

            selected = (Element) node;
            break;
        }

        if (cindex == -1) cindex = 1;

        if (selected == null && create) {
            Document doc = getOwnerDocument(current);
            for (; cindex > 0; --cindex) {
                selected = doc.createElement(ename);
                current.appendChild(selected);
            }
        }

        return selected;
    }

    private static Node selectElementAttribute(Node current, String step, boolean create) {
        String ename = enameOf(step);
        String aname = anameOf(step);
        if (!ename.isEmpty())
            current = selectElement(current, ename, create);
        current = selectAttribute(current, aname, create);
        return current;
    }

    private static Node selectTextNode(Node current, String step, boolean create) {
        NodeList nl = current.getChildNodes();
        for (int i = 0; i < nl.getLength(); ++i) {
            Node node = nl.item(i);
            if (node.getNodeType() == Node.TEXT_NODE || node.getNodeType() == Node.CDATA_SECTION_NODE) {
                current = node;
                break;
            }
        }
        return current;
    }

    // parent/step
    private static String parentOf(String xpath) {
        int pos = xpath.lastIndexOf('/');
        if (pos == -1)
            return ".";
        else
            return xpath.substring(0,pos);
    }

    private static String stepOf(String xpath) {
        int pos = xpath.lastIndexOf('/');
        if (pos == -1)
            return xpath;
        else
            return xpath.substring(pos+1);
    }

    // ename[...]
    private static String enameOf(String step) {
        int pos = step.indexOf("[");
        if (pos == -1)
            pos = step.indexOf("(");
        return step.substring(0, pos);
    }

    // ...[@aname=...
    private static String anameOf(String step) {
        int pos = step.indexOf("[@");
        int eq  = step.indexOf('=');
        if (eq == -1)
            eq = step.indexOf(']');
        return step.substring(pos+2, eq);
    }

    // ...[@attr=value] | ...[@attr='value'] | ...[@attr="value"]
    private static String avalueOf(String step, Properties params) {
        int eq  = step.indexOf('=');
        int end = step.indexOf(']');
        if (eq == -1)
            return null;

        String value = step.substring(eq+1,end).trim();

        if (value.startsWith("'") && value.endsWith("'"))
            return value.substring(1, value.length()-1);
        if (value.startsWith("\"") && value.endsWith("\""))
            return value.substring(1, value.length()-1);

        value = resolveParams(value, params, true);
        return value;
    }

    // ...[index] | ...[-1] | ...[]
    // ...(index) | ...(-1) | ...()
    private static int indexOf(String step, Properties params) {
        String sindex;
        int pos, end;
        boolean one;    // is 1-based or 0-based
        if (step.contains("[")) {
            pos = step.indexOf('[');
            end = step.indexOf(']');
            one = true;
        }
        else {
            pos = step.indexOf('(');
            end = step.indexOf(')');
            one = false;
        }

        sindex = step.substring(pos+1, end);
        if (sindex.length() == 0)
            return -1;

        sindex = resolveParams(sindex, params, true);
        int index = Integer.parseInt(sindex);
        return one && index > 0 ? index - 1 : index;
    }

    private static Document getOwnerDocument(Node node) {
        if (node.getNodeType() == Node.DOCUMENT_NODE)
            return (Document) node;
        else
            return node.getOwnerDocument();
    }
}
