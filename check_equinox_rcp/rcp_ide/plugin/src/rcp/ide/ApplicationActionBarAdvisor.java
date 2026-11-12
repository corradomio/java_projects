package rcp.ide;

import java.util.function.Consumer;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.application.*;

public final class ApplicationActionBarAdvisor extends ActionBarAdvisor {
    private static final String SPY_BUNDLE = "org.eclipse.pde.spy.core";
    private Action open;
    private Action close;
    private Action exit;
    private Action uiSpy;
    private Action about;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) { super(configurer); }

    @Override
    protected void makeActions(IWorkbenchWindow window) {
        open = action("Open", "rcp.ide.open", () -> withView(window, ExplorerView::openFile));
        open.setAccelerator(SWT.MOD1 | 'O');
        close = action("Close", "rcp.ide.close", () -> withView(window, ExplorerView::closeFile));
        close.setAccelerator(SWT.MOD1 | 'W');
        uiSpy = action("UI Spy", "rcp.ide.uiSpy", () -> UiSpyDialog.show(window.getShell()));
        exit = action("Exit", "rcp.ide.exit", () -> window.getWorkbench().close());
        about = action("About", "rcp.ide.about", () -> MessageDialog.openInformation(
                window.getShell(), "About RCP File Explorer",
                "RCP File Explorer 1.0\n\nBrowse files, edit UTF-8 text, and draw on the graphic canvas."));
    }

    private Action action(String label, String id, Runnable runnable) {
        Action action = new Action(label) {
            @Override public void run() { runnable.run(); }
        };
        action.setId(id);
        action.setToolTipText(label);
        register(action);
        return action;
    }

    private static void withView(IWorkbenchWindow window, Consumer<ExplorerView> task) {
        try {
            task.accept((ExplorerView) window.getActivePage().showView(ExplorerView.ID));
        } catch (PartInitException exception) {
            MessageDialog.openError(window.getShell(), "File Explorer unavailable", exception.getMessage());
        }
    }

    @Override
    protected void fillMenuBar(IMenuManager menuBar) {
        MenuManager file = new MenuManager("&File", "file");
        file.add(open);
        file.add(close);
        file.add(new Separator());
        file.add(exit);
        menuBar.add(file);
        MenuManager windowMenu = new MenuManager("&Window", "window");
        // With org.eclipse.pde.spy.core installed, plugin.xml adds UI Spy to its Spies submenu instead.
        if (Platform.getBundle(SPY_BUNDLE) == null) {
            MenuManager spies = new MenuManager("Spies", "rcp.ide.spies");
            spies.add(uiSpy);
            windowMenu.add(spies);
        }
        menuBar.add(windowMenu);
        MenuManager help = new MenuManager("&Help", "help");
        help.add(about);
        menuBar.add(help);
    }

    @Override
    protected void fillCoolBar(ICoolBarManager coolBar) {
        ToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        toolbar.add(open);
        toolbar.add(close);
        coolBar.add(new ToolBarContributionItem(toolbar, "main"));
    }
}
