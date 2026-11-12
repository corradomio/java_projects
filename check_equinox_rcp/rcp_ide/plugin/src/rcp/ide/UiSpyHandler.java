package rcp.ide;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.ui.handlers.HandlerUtil;

/** Opens the UI Spy from the "rcp.ide.uiSpy" command contributed to the E4 Spies menu. */
public final class UiSpyHandler extends AbstractHandler {
    @Override
    public Object execute(ExecutionEvent event) {
        UiSpyDialog.show(HandlerUtil.getActiveShell(event));
        return null;
    }
}
