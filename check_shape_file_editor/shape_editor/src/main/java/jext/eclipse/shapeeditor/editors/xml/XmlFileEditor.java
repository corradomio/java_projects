package jext.eclipse.shapeeditor.editors.xml;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXParseException;

public class XmlFileEditor extends EditorPart {

    public static final String ID = "jext.eclipse.shapeeditor.editors.xml.XmlFileEditor";

    private TabFolder tabFolder;
    private TreeViewer viewer;
    private StyledText sourceText;
    private Color tagColor;
    private Color attributeColor;
    private Color attributeValueColor;

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        if (!(input instanceof XmlFileEditorInput)) {
            throw new PartInitException("Invalid editor input: " + (input == null ? "null" : input.getName()));
        }
        setSite(site);
        setInput(input);
        setPartName(input.getName());
    }

    @Override
    public void createPartControl(Composite parent) {
        tagColor = new Color(parent.getDisplay(), 0, 0, 160);
        attributeColor = new Color(parent.getDisplay(), 128, 0, 128);
        attributeValueColor = new Color(parent.getDisplay(), 0, 128, 0);

        tabFolder = new TabFolder(parent, SWT.BORDER);

        TabItem treeTab = new TabItem(tabFolder, SWT.NONE);
        treeTab.setText("Tree");
        Composite treeComposite = new Composite(tabFolder, SWT.NONE);
        treeComposite.setLayout(new FillLayout());
        viewer = new TreeViewer(treeComposite, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        viewer.setContentProvider(new XmlTreeContentProvider());
        TreeViewerColumn treeColumn = new TreeViewerColumn(viewer, SWT.NONE);
        treeColumn.setLabelProvider(new XmlTreeLabelProvider(tagColor, attributeColor, attributeValueColor));
        treeColumn.getColumn().setWidth(600);
        treeTab.setControl(treeComposite);

        TabItem textTab = new TabItem(tabFolder, SWT.NONE);
        textTab.setText("Text");
        sourceText = new StyledText(
                tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.READ_ONLY);
        sourceText.setFont(JFaceResources.getFont(JFaceResources.TEXT_FONT));
        textTab.setControl(sourceText);

        tabFolder.setSelection(treeTab);
        loadXmlFile();
    }

    private void loadXmlFile() {
        XmlFileEditorInput input = (XmlFileEditorInput) getEditorInput();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(input.getFile());
            String encoding = document.getXmlEncoding();
            Charset charset = encoding == null ? StandardCharsets.UTF_8 : Charset.forName(encoding);
            String source = Files.readString(input.getFile().toPath(), charset);

            viewer.setInput(document);
            viewer.expandToLevel(2);
            sourceText.setText(source);
            sourceText.setStyleRanges(createStyleRanges(source));
        } catch (Exception ex) {
            viewer.setInput(null);
            sourceText.setText("");
            MessageDialog.openError(
                    getSite().getShell(),
                    "Open XML File",
                    "Failed to open '" + input.getName() + "':\n" + message(ex));
        }
    }

    @Override
    public void setFocus() {
        if (tabFolder == null || tabFolder.isDisposed()) {
            return;
        }
        TabItem[] selection = tabFolder.getSelection();
        if (selection.length == 0) {
            return;
        }
        if (selection[0].getControl() == sourceText) {
            sourceText.setFocus();
        } else {
            viewer.getControl().setFocus();
        }
    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
    }

    @Override
    public void doSaveAs() {
    }

    @Override
    public void dispose() {
        if (tagColor != null) {
            tagColor.dispose();
        }
        if (attributeColor != null) {
            attributeColor.dispose();
        }
        if (attributeValueColor != null) {
            attributeValueColor.dispose();
        }
        super.dispose();
    }

    private StyleRange[] createStyleRanges(String source) {
        List<StyleRange> ranges = new ArrayList<>();
        int offset = 0;
        while (offset < source.length()) {
            int tagStart = source.indexOf('<', offset);
            if (tagStart < 0) {
                break;
            }
            if (source.startsWith("<!--", tagStart)) {
                offset = afterDelimiter(source, tagStart, "-->");
                continue;
            }
            if (source.startsWith("<![CDATA[", tagStart)) {
                offset = afterDelimiter(source, tagStart, "]]>");
                continue;
            }

            int tagEnd = findTagEnd(source, tagStart + 1);
            if (tagEnd < 0) {
                break;
            }
            addTagStyles(source, tagStart, tagEnd + 1, ranges);
            offset = tagEnd + 1;
        }
        return ranges.toArray(new StyleRange[0]);
    }

    private void addTagStyles(String source, int tagStart, int tagEnd, List<StyleRange> ranges) {
        List<StyleRange> attributeRanges = new ArrayList<>();
        int contentEnd = tagEnd - 1;
        int cursor = tagStart + 1;
        if (cursor < contentEnd && source.charAt(cursor) == '?') {
            cursor++;
        }
        if (cursor >= contentEnd
                || source.charAt(cursor) == '/'
                || source.charAt(cursor) == '!') {
            addStyle(ranges, tagStart, tagEnd, tagColor);
            return;
        }

        cursor = skipTagName(source, cursor, contentEnd);
        while (cursor < contentEnd) {
            cursor = skipWhitespace(source, cursor, contentEnd);
            if (cursor >= contentEnd
                    || source.charAt(cursor) == '/'
                    || source.charAt(cursor) == '?') {
                break;
            }

            int nameStart = cursor;
            cursor = skipTagName(source, cursor, contentEnd);
            if (nameStart == cursor) {
                cursor++;
                continue;
            }
            addStyle(attributeRanges, nameStart, cursor, attributeColor);

            cursor = skipWhitespace(source, cursor, contentEnd);
            if (cursor >= contentEnd || source.charAt(cursor) != '=') {
                continue;
            }
            cursor = skipWhitespace(source, cursor + 1, contentEnd);
            if (cursor >= contentEnd) {
                break;
            }

            char quote = source.charAt(cursor);
            if (quote == '\'' || quote == '"') {
                int valueStart = ++cursor;
                while (cursor < contentEnd && source.charAt(cursor) != quote) {
                    cursor++;
                }
                addStyle(attributeRanges, valueStart, cursor, attributeValueColor);
                if (cursor < contentEnd) {
                    cursor++;
                }
            } else {
                int valueStart = cursor;
                cursor = skipTagName(source, cursor, contentEnd);
                addStyle(attributeRanges, valueStart, cursor, attributeValueColor);
            }
        }

        int segmentStart = tagStart;
        for (StyleRange attributeRange : attributeRanges) {
            addStyle(ranges, segmentStart, attributeRange.start, tagColor);
            ranges.add(attributeRange);
            segmentStart = attributeRange.start + attributeRange.length;
        }
        addStyle(ranges, segmentStart, tagEnd, tagColor);
    }

    private static int findTagEnd(String source, int offset) {
        char quote = 0;
        for (int i = offset; i < source.length(); i++) {
            char c = source.charAt(i);
            if (quote != 0) {
                if (c == quote) {
                    quote = 0;
                }
            } else if (c == '\'' || c == '"') {
                quote = c;
            } else if (c == '>') {
                return i;
            }
        }
        return -1;
    }

    private static int afterDelimiter(String source, int offset, String delimiter) {
        int end = source.indexOf(delimiter, offset);
        return end < 0 ? source.length() : end + delimiter.length();
    }

    private static int skipTagName(String source, int offset, int end) {
        int cursor = offset;
        while (cursor < end) {
            char c = source.charAt(cursor);
            if (Character.isWhitespace(c) || c == '=' || c == '/' || c == '?') {
                break;
            }
            cursor++;
        }
        return cursor;
    }

    private static int skipWhitespace(String source, int offset, int end) {
        int cursor = offset;
        while (cursor < end && Character.isWhitespace(source.charAt(cursor))) {
            cursor++;
        }
        return cursor;
    }

    private static void addStyle(List<StyleRange> ranges, int start, int end, Color color) {
        if (start >= end) {
            return;
        }
        StyleRange range = new StyleRange();
        range.start = start;
        range.length = end - start;
        range.foreground = color;
        ranges.add(range);
    }

    private static String message(Exception ex) {
        if (ex instanceof SAXParseException) {
            SAXParseException parseException = (SAXParseException) ex;
            return "Line " + parseException.getLineNumber()
                    + ", column " + parseException.getColumnNumber()
                    + ": " + parseException.getMessage();
        }
        String message = ex.getMessage();
        return message == null || message.isEmpty() ? ex.getClass().getSimpleName() : message;
    }

    private static class XmlTreeContentProvider implements ITreeContentProvider {

        @Override
        public Object[] getElements(Object inputElement) {
            if (inputElement instanceof Document) {
                Element root = ((Document) inputElement).getDocumentElement();
                return root == null ? new Object[0] : new Object[] { root };
            }
            return new Object[0];
        }

        @Override
        public Object[] getChildren(Object parentElement) {
            if (!(parentElement instanceof Node)) {
                return new Object[0];
            }

            Node parent = (Node) parentElement;
            List<Node> children = new ArrayList<>();
            if (parent instanceof Element) {
                NamedNodeMap attributes = parent.getAttributes();
                for (int i = 0; i < attributes.getLength(); i++) {
                    children.add(attributes.item(i));
                }
            }

            NodeList nodes = parent.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (isWhitespace(node)) {
                    continue;
                }
                children.add(node);
            }
            return children.toArray();
        }

        @Override
        public Object getParent(Object element) {
            if (element instanceof Attr) {
                return ((Attr) element).getOwnerElement();
            }
            if (element instanceof Node) {
                return ((Node) element).getParentNode();
            }
            return null;
        }

        @Override
        public boolean hasChildren(Object element) {
            return getChildren(element).length > 0;
        }

        @Override
        public void dispose() {
        }

        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }

        private static boolean isWhitespace(Node node) {
            short type = node.getNodeType();
            return (type == Node.TEXT_NODE || type == Node.CDATA_SECTION_NODE)
                    && node.getNodeValue().trim().isEmpty();
        }
    }

    private static class XmlTreeLabelProvider extends StyledCellLabelProvider {

        private final StyledString.Styler tagStyler;
        private final StyledString.Styler attributeStyler;
        private final StyledString.Styler attributeValueStyler;

        private XmlTreeLabelProvider(Color tagColor, Color attributeColor, Color attributeValueColor) {
            tagStyler = createStyler(tagColor);
            attributeStyler = createStyler(attributeColor);
            attributeValueStyler = createStyler(attributeValueColor);
        }

        @Override
        public void update(ViewerCell cell) {
            StyledString text = new StyledString();
            Object element = cell.getElement();
            if (element instanceof Node) {
                appendNode(text, (Node) element);
            } else {
                text.append(String.valueOf(element));
            }
            cell.setText(text.getString());
            cell.setStyleRanges(text.getStyleRanges());
            super.update(cell);
        }

        private void appendNode(StyledString text, Node node) {
            switch (node.getNodeType()) {
                case Node.ELEMENT_NODE:
                    text.append("<" + node.getNodeName() + ">", tagStyler);
                    break;
                case Node.ATTRIBUTE_NODE:
                    text.append("@" + node.getNodeName(), attributeStyler);
                    text.append(" = ");
                    text.append("\"" + singleLine(node.getNodeValue()) + "\"", attributeValueStyler);
                    break;
                case Node.TEXT_NODE:
                case Node.CDATA_SECTION_NODE:
                    text.append(singleLine(node.getNodeValue()));
                    break;
                case Node.COMMENT_NODE:
                    text.append("<!-- " + singleLine(node.getNodeValue()) + " -->");
                    break;
                case Node.PROCESSING_INSTRUCTION_NODE:
                    text.append("<?" + node.getNodeName() + " "
                            + singleLine(node.getNodeValue()) + "?>", tagStyler);
                    break;
                case Node.DOCUMENT_TYPE_NODE:
                    text.append("<!DOCTYPE " + node.getNodeName() + ">", tagStyler);
                    break;
                default:
                    text.append(node.getNodeName());
                    break;
            }
        }

        private static StyledString.Styler createStyler(Color color) {
            return new StyledString.Styler() {
                @Override
                public void applyStyles(TextStyle style) {
                    style.foreground = color;
                }
            };
        }

        private static String singleLine(String value) {
            return value == null ? "" : value.trim().replaceAll("\\s+", " ");
        }
    }
}
