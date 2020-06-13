package jext.xml;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
            end = text.indexOf("}", pos+1);
            if (end == -1) continue;
            String name = text.substring(pos+2,end);
            if (params == null) continue;
            if (!params.containsKey(name)) continue;
            text = text.substring(0,pos) + params.getProperty(name) + text.substring(end+1);
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
        List<Element> selected = selectNodes(elt, xpath, limit, params);

        for(Element sel : selected) {
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

    public static int getValue(Element elt, String xpath, int defaultValue, Properties params) {
        String svalue = getValue(elt, xpath, EMPTY_VALUE, params);
        if (svalue.length() == 0)
            return defaultValue;
        else
            return Integer.parseInt(svalue);
    }

    public static boolean getValue(Element elt, String xpath, boolean defaultValue, Properties params) {
        String svalue = getValue(elt, xpath, EMPTY_VALUE, params);
        if (svalue.length() == 0)
            return defaultValue;
        else
            return Boolean.parseBoolean(svalue);
    }

    // ----------------------------------------------------------------------
    // getValues
    // ----------------------------------------------------------------------

    public static List<String> getValues(Element elt, String xpath) {
        List<String> values = new ArrayList<>();
        List<Element> nodes = selectNodes(elt, xpath);
        for(Element node : nodes)
            values.add(node.getTextContent().trim());
        return values;
    }

    public static List<String> getValues(Element elt, String xpath, String attribute) {
        List<String> values = new ArrayList<>();
        List<Element> nodes = selectNodes(elt, xpath);
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

    public static Properties getProperties(Element elt) {
        Properties properties = new Properties();
        for(Element pelt : selectNodes(elt, "property")) {
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

    public static List<Element> selectNodes(Element elt, String xpath) {
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
    public static List<Element> selectNodes(Element elt, String xpath, int limit, Properties params) {
        List<Element> selected = new ArrayList<>();
        String aname = null, avalue = "";

        if (xpath.startsWith("/"))
            xpath = "<root>" + xpath;

        // parent/elt
        // parent/elt[@attr='value']

        String parentPath  = parentOf(xpath);
        String step = stepOf(xpath);

        if (step.contains("[")) {
            aname = anameOf(step);
            avalue = avalueOf(step, params);
            step = enameOf(step);
        }

        Element parentNode = (Element)selectNode(elt, parentPath, false, params);
        if (parentNode == null)
            return selected;

        NodeList children = parentNode.getChildNodes();
        for(int i=0; i<children.getLength(); ++i) {
            Node node = children.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE)
                continue;
            if(!"*".equals(step) && !node.getNodeName().equals(step))
                continue;

            Element enode = (Element)node;

            if (aname != null) {
                String value = enode.getAttribute(aname);
                if (!avalue.equals(value))
                    continue;
            }

            selected.add((Element)node);
        }

        return selected;
    }

    // ----------------------------------------------------------------------
    // Select node
    // ----------------------------------------------------------------------

    /**
     * ]
     * @param elt
     * @param xpath
     * @param create
     * @param params
     * @return
     */
    public static Node selectNode(Element elt, String xpath, boolean create, Properties params) {
        Node current = elt;
        if (xpath.startsWith("/"))
            xpath = "<root>" + xpath;

        String[] steps = xpath.split("/");
        for(String step : steps) {

            if(current == null)
                break;

            // "."
            if (step.length() == 0 || ".".equals(step))
                continue;
                // ".."
            else if("..".equals(step))
                current = current.getParentNode();
                // "/..."
            else if("<root>".equals(step))
                current = getOwnerDocument(current);
                // "@attr"
            else if (step.startsWith("@")) {
                String aname = step.substring(1);
                Attr attr = ((Element) current).getAttributeNode(aname);
                if (attr == null && create) {
                    ((Element) current).setAttribute(aname,  "");
                    attr = ((Element) current).getAttributeNode(aname);
                }
                current = attr;
            }
            // #text | text()
            else if (step.equals("#text") || step.equals("text()")) {
                NodeList nl = current.getChildNodes();
                for(int i=0; i<nl.getLength(); ++i) {
                    Node node = nl.item(i);
                    if (node.getNodeType() == Node.TEXT_NODE || node.getNodeType() == Node.CDATA_SECTION_NODE) {
                        current = node;
                        break;
                    }
                }
            }
            // name
            else if (!step.contains("[")) {
                Element selected = null;
                NodeList nl = current.getChildNodes();
                for(int i=0; i<nl.getLength(); ++i) {
                    Node node = nl.item(i);
                    if (node.getNodeType() != Node.ELEMENT_NODE)
                        continue;
                    if (!step.equals(((Element)node).getTagName()))
                        continue;

                    selected = (Element)node;
                    break;
                }

                if(selected == null && create) {
                    Document doc = getOwnerDocument(current);
                    selected = doc.createElement(step);
                    current.appendChild(selected);
                }
                current = selected;
            }
            // name[@attr=value] | name[@attr='value'] | name[@attr="value"]
            else if(step.contains("[@")) {
                Element selected = null;
                String ename = enameOf(step);
                String aname = anameOf(step);
                String check = avalueOf(step, params);

                NodeList nl = current.getChildNodes();

                for(int i=0; i<nl.getLength(); ++i) {
                    Node node = nl.item(i);
                    if (node.getNodeType() != Node.ELEMENT_NODE)
                        continue;
                    if (!ename.equals(((Element)node).getTagName()))
                        continue;

                    String value = ((Element)node).getAttribute(aname);
                    if(!check.equals(value))
                        continue;

                    selected = (Element)node;
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
                current = selected;
            }
            // node[index]   ONE-based!!!!
            else if(step.contains("[")) {
                Element selected = null;
                String ename = enameOf(step);
                int oindex = indexOf(step, params);
                int index = oindex;

                NodeList nl = current.getChildNodes();

                for(int i=0; i<nl.getLength() && index > 0; ++i) {
                    Node node = nl.item(i);

                    // not a <name>
                    if (node.getNodeType() != Node.ELEMENT_NODE)
                        continue;

                    // not <ename>
                    if (!ename.equals(((Element)node).getTagName()))
                        continue;

                    // not the correct position
                    if (--index > 0)
                        continue;

                    selected = (Element)node;
                    break;
                }

                if (index == -1) index = 1;

                if (selected == null && create) {
                    Document doc = getOwnerDocument(current);
                    for(; index>0; --index) {
                        selected = doc.createElement(ename);
                        current.appendChild(selected);
                    }
                }

                current = selected;
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
        return step.substring(0, pos);
    }

    // ...[@aname=...
    private static String anameOf(String step) {
        int pos = step.indexOf("[@");
        int eq  = step.indexOf('=');
        return step.substring(pos+2, eq);

    }

    // ...[@attr=value] | ...[@attr='value'] | ...[@attr="value"]
    private static String avalueOf(String step, Properties params) {
        int eq  = step.indexOf('=');
        int end = step.indexOf(']');
        String value = step.substring(eq+1,end).trim();

        if (value.startsWith("'") && value.endsWith("'"))
            return value.substring(1, value.length()-1);
        if (value.startsWith("\"") && value.endsWith("\""))
            return value.substring(1, value.length()-1);

        value = resolveParams(value, params, true);
        return value;
    }

    // ...[index] | ...[-1] | ...[]
    private static int indexOf(String step, Properties params) {
        String sindex;
        int pos = step.indexOf('[');
        int end = step.indexOf(']');

        sindex = step.substring(pos+1, end);
        if (sindex.length() == 0)
            return -1;

        sindex = resolveParams(sindex, params, true);
        return Integer.parseInt(sindex);
    }

    private static Document getOwnerDocument(Node node) {
        if (node.getNodeType() == Node.DOCUMENT_NODE)
            return (Document) node;
        else
            return node.getOwnerDocument();
    }
}
