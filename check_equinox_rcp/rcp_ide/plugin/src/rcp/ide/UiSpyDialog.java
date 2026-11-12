package rcp.ide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.e4.ui.model.application.MApplicationElement;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;

/** Non-modal dialog showing the live SWT widget hierarchy of every open shell. */
final class UiSpyDialog extends Dialog {
    private static final String MODEL_ELEMENT = "modelElement";
    private static final String CSS_ID = "org.eclipse.e4.ui.css.id";
    private static final String CSS_CLASS = "org.eclipse.e4.ui.css.CssClassName";
    private static final int REFRESH_ID = IDialogConstants.CLIENT_ID + 1;
    private static UiSpyDialog instance;
    private TreeViewer tree;
    private Text details;

    private UiSpyDialog(Shell parent) {
        super(parent);
        setShellStyle(SWT.SHELL_TRIM | SWT.MODELESS);
        setBlockOnOpen(false);
    }

    static void show(Shell parent) {
        if (instance != null && instance.getShell() != null && !instance.getShell().isDisposed()) {
            instance.refresh();
            instance.getShell().setActive();
            return;
        }
        instance = new UiSpyDialog(parent);
        instance.open();
    }

    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("UI Spy");
        shell.setData("name", "ui.spy");
    }

    @Override protected Point getInitialSize() { return new Point(760, 640); }

    @Override protected boolean isResizable() { return true; }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite area = (Composite) super.createDialogArea(parent);
        SashForm split = new SashForm(area, SWT.VERTICAL);
        split.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        tree = new TreeViewer(split, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL);
        tree.setContentProvider(new WidgetTreeProvider());
        tree.setLabelProvider(new LabelProvider() {
            @Override public String getText(Object element) { return label((Widget) element); }
        });
        tree.addSelectionChangedListener(event ->
                details.setText(describe((Widget) tree.getStructuredSelection().getFirstElement())));
        details = new Text(split, SWT.MULTI | SWT.READ_ONLY | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        split.setWeights(new int[] { 75, 25 });
        refresh();
        return area;
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, REFRESH_ID, "Refresh", false);
        createButton(parent, IDialogConstants.CLOSE_ID, IDialogConstants.CLOSE_LABEL, true);
    }

    @Override
    protected void buttonPressed(int buttonId) {
        if (buttonId == REFRESH_ID) refresh();
        else if (buttonId == IDialogConstants.CLOSE_ID) close();
        else super.buttonPressed(buttonId);
    }

    private void refresh() {
        tree.setInput(getShell().getDisplay());
        tree.expandToLevel(4);
        details.setText("");
    }

    /** Preferred identifier: E4 model element id, then the widget "name" data, then the CSS id. */
    private static String id(Widget widget) {
        if (widget.getData(MODEL_ELEMENT) instanceof MApplicationElement element && element.getElementId() != null)
            return element.getElementId();
        if (widget.getData("name") instanceof String name) return name;
        if (widget.getData(CSS_ID) instanceof String cssId) return cssId;
        return null;
    }

    private static String text(Widget widget) {
        String text = null;
        if (widget instanceof Decorations) text = ((Decorations) widget).getText();
        else if (widget instanceof Item) text = ((Item) widget).getText();
        else if (widget instanceof Button) text = ((Button) widget).getText();
        else if (widget instanceof Label) text = ((Label) widget).getText();
        else if (widget instanceof Group) text = ((Group) widget).getText();
        if (text == null || text.isBlank()) return null;
        text = text.lines().findFirst().orElse("").replace("&", "");
        return text.length() > 40 ? text.substring(0, 40) + "..." : text;
    }

    private static String type(Widget widget) {
        String type = widget.getClass().getSimpleName();
        return type.isEmpty() ? widget.getClass().getName() : type;
    }

    private static String label(Widget widget) {
        if (widget.isDisposed()) return "<disposed>";
        String id = id(widget);
        String text = text(widget);
        return type(widget) + (id == null ? "" : "  #" + id) + (text == null ? "" : "  \"" + text + "\"");
    }

    private static String describe(Widget widget) {
        if (widget == null) return "";
        if (widget.isDisposed()) return "Widget disposed. Press Refresh.";
        StringBuilder out = new StringBuilder();
        out.append("Class: ").append(widget.getClass().getName()).append('\n');
        out.append("Id: ").append(id(widget)).append('\n');
        if (widget.getData(MODEL_ELEMENT) instanceof MApplicationElement element)
            out.append("Model element: ").append(element.getClass().getSimpleName())
                    .append(" [").append(element.getElementId()).append("]\n");
        out.append("Name: ").append(widget.getData("name")).append('\n');
        out.append("CSS id: ").append(widget.getData(CSS_ID)).append('\n');
        out.append("CSS class: ").append(widget.getData(CSS_CLASS)).append('\n');
        out.append("Style: 0x").append(Integer.toHexString(widget.getStyle())).append('\n');
        if (widget instanceof Control control) {
            out.append("Bounds: ").append(control.getBounds()).append('\n');
            out.append("Visible: ").append(control.isVisible())
                    .append(", Enabled: ").append(control.isEnabled()).append('\n');
        }
        return out.toString();
    }

    private final class WidgetTreeProvider implements ITreeContentProvider {
        @Override
        public Object[] getElements(Object input) {
            return Arrays.stream(((Display) input).getShells())
                    .filter(shell -> !shell.isDisposed() && shell != getShell())
                    .toArray();
        }

        @Override
        public Object[] getChildren(Object parent) {
            Widget widget = (Widget) parent;
            if (widget.isDisposed()) return new Object[0];
            List<Widget> children = new ArrayList<>();
            if (widget instanceof Decorations && ((Decorations) widget).getMenuBar() != null)
                children.add(((Decorations) widget).getMenuBar());
            if (widget instanceof Control && ((Control) widget).getMenu() != null)
                children.add(((Control) widget).getMenu());
            if (widget instanceof Composite) children.addAll(Arrays.asList(((Composite) widget).getChildren()));
            if (widget instanceof ToolBar) children.addAll(Arrays.asList(((ToolBar) widget).getItems()));
            if (widget instanceof Menu) children.addAll(Arrays.asList(((Menu) widget).getItems()));
            if (widget instanceof MenuItem && ((MenuItem) widget).getMenu() != null)
                children.add(((MenuItem) widget).getMenu());
            return children.toArray();
        }

        @Override public Object getParent(Object element) { return null; }

        @Override public boolean hasChildren(Object element) { return getChildren(element).length > 0; }
    }
}
