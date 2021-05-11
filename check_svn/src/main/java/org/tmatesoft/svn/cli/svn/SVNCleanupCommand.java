/*
 * ====================================================================
 * Copyright (c) 2004-2012 TMate Software Ltd.  All rights reserved.
 *
 * This software is licensed as described in the file COPYING, which
 * you should have received as part of this distribution.  The terms
 * are also available at http://svnkit.com/license.html.
 * If newer versions of this license are posted there, you may use a
 * newer version instead, at your option.
 * ====================================================================
 */
package org.tmatesoft.svn.cli.svn;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.tmatesoft.svn.core.SVNErrorCode;
import org.tmatesoft.svn.core.SVNErrorMessage;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.internal.wc.SVNErrorManager;
import org.tmatesoft.svn.core.internal.wc.SVNPath;
import org.tmatesoft.svn.core.wc.SVNWCClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import org.tmatesoft.svn.util.SVNLogType;


/**
 * @version 1.3
 * @author  TMate Software Ltd.
 */
public class SVNCleanupCommand extends SVNCommand {

    public SVNCleanupCommand() {
        super("cleanup", null);
    }

    protected Collection createSupportedOptions() {
        Collection options = new LinkedList();
        options.add(SVNOption.DIFF3_CMD);
        options.add(SVNOption.REMOVE_UNVERSIONED);
        options.add(SVNOption.REMOVE_IGNORED);
        options.add(SVNOption.INCLUDE_EXTERNALS);
        return options;
    }

    public void run() throws SVNException {
        List targets = getSVNEnvironment().combineTargets(getSVNEnvironment().getTargets(), true);
        if (targets.isEmpty()) {
            targets.add("");
        }
        SVNWCClient client = getSVNEnvironment().getClientManager().getWCClient();
        SVNNotifyPrinter eventHandler = new SVNNotifyPrinter(getSVNEnvironment());
        client.setEventHandler(eventHandler);

        boolean removeUnversioned = getSVNEnvironment().isRemoveUnversioned();
        boolean removeIgnored = getSVNEnvironment().isRemoveIgnored();
        boolean includeExternals = getSVNEnvironment().isIncludeExternals();

        for (Iterator ts = targets.iterator(); ts.hasNext();) {
            String targetName = (String) ts.next();
            SVNPath target = new SVNPath(targetName);

            eventHandler.checkCancelled();

            if (removeUnversioned || removeIgnored) {
                try {
                    client.doCleanup(target.getFile(), false, false, false, removeUnversioned, removeIgnored, includeExternals);
                } catch (SVNException e) {
                    if (e.getErrorMessage().getErrorCode() == SVNErrorCode.WC_LOCKED) {
                        SVNErrorMessage errorMessage = SVNErrorMessage.create(SVNErrorCode.WC_LOCKED, "Working copy locked; if no other " +
                                "Subversion client is currently " +
                                "using the working copy, try running " +
                                "'svn cleanup' without the " +
                                "--remove-unversioned and " +
                                "--remove-ignored options first.", e);
                        SVNErrorManager.error(errorMessage, SVNLogType.WC);
                    } else if (e.getErrorMessage().getErrorCode() == SVNErrorCode.WC_NOT_WORKING_COPY) {
                        SVNErrorMessage errorMessage = SVNErrorMessage.create(SVNErrorCode.CL_ARG_PARSING_ERROR, "Cannot remove unversioned or ignored " +
                                                        "items from something that is not a " +
                                                        "working copy", e);
                        SVNErrorManager.error(errorMessage, SVNLogType.WC);
                    } else {
                        throw e;
                    }
                }
            } else {
                try {
                    client.doCleanup(target.getFile(), true, true, true, false, false, includeExternals);
                } catch (SVNException e) {
                    if (e.getErrorMessage().getErrorCode() == SVNErrorCode.WC_LOCKED) {
                        File workingCopyRoot = null;
                        try {
                            workingCopyRoot = SVNWCUtil.getWorkingCopyRoot(target.getFile(), true);
                        } catch (SVNException second) {
                            SVNErrorManager.error(e.getErrorMessage(), second.getErrorMessage(), e, SVNLogType.WC);
                        }
                        SVNErrorMessage errorMessage = SVNErrorMessage.create(SVNErrorCode.WC_LOCKED, "Working copy locked; try running " +
                                "'svn cleanup' on the root of the " +
                                "working copy (''{0}'') instead.", new Object[]{workingCopyRoot}, e);
                        SVNErrorManager.error(errorMessage, SVNLogType.WC);
                    } else {
                        throw e;
                    }
                }
            }
        }
    }

}
