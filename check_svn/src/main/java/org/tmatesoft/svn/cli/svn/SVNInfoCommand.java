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

import org.tmatesoft.svn.cli.SVNCommandUtil;
import org.tmatesoft.svn.core.*;
import org.tmatesoft.svn.core.internal.util.*;
import org.tmatesoft.svn.core.internal.wc.*;
import org.tmatesoft.svn.core.internal.wc17.SVNWCUtils;
import org.tmatesoft.svn.core.wc.*;
import org.tmatesoft.svn.util.SVNLogType;

import java.io.File;
import java.util.*;


/**
 * @version 1.3
 * @author  TMate Software Ltd.
 */
public class SVNInfoCommand extends SVNXMLCommand implements ISVNInfoHandler {

    private boolean myIsMultipleTargets;
    private boolean myIsStartNewLine;
    private SVNInfoItemType myPrintWhat;
    private boolean myTargetIsPath;
    private static final int SIM_RANGE_MAX = 1000000;

    public SVNInfoCommand() {
        super("info", null);
    }

    protected Collection createSupportedOptions() {
        Collection options = new LinkedList();
        options.add(SVNOption.REVISION);
        options.add(SVNOption.RECURSIVE);
        options.add(SVNOption.DEPTH);
        options.add(SVNOption.TARGETS);
        options.add(SVNOption.INCREMENTAL);
        options.add(SVNOption.XML);
        options.add(SVNOption.CHANGELIST);
        options.add(SVNOption.SHOW_ITEM);
        options.add(SVNOption.NO_NEWLINE);
        return options;
    }

    public void run() throws SVNException {
        List targets = new ArrayList(); 
        if (getSVNEnvironment().getTargets() != null) {
            targets.addAll(getSVNEnvironment().getTargets());
        }
        targets = getSVNEnvironment().combineTargets(targets, true);
        if (targets.isEmpty()) {
            targets.add("");
        }
        if (getSVNEnvironment().isXML()) {
            if (getSVNEnvironment().getShowItem() != null) {
                SVNErrorMessage errorMessage = SVNErrorMessage.create(SVNErrorCode.CL_ARG_PARSING_ERROR, "--show-item is not valid in --xml mode");
                SVNErrorManager.error(errorMessage, SVNLogType.CLIENT);
            }
            if (getSVNEnvironment().isNoNewLine()) {
                SVNErrorMessage errorMessage = SVNErrorMessage.create(SVNErrorCode.CL_ARG_PARSING_ERROR, "--no-newline is not valid in --xml mode");
                SVNErrorManager.error(errorMessage, SVNLogType.CLIENT);
            }
            if (!getSVNEnvironment().isIncremental()) {
                printXMLHeader("info");
            }
        } else if (getSVNEnvironment().getShowItem() != null) {
            if (getSVNEnvironment().isIncremental()) {
                SVNErrorManager.error(SVNErrorMessage.create(SVNErrorCode.CL_ARG_PARSING_ERROR, "'incremental' option only valid in XML mode"), SVNLogType.CLIENT);
            }
            this.myIsMultipleTargets = (getSVNEnvironment().getDepth().compareTo(SVNDepth.EMPTY) > 0) || targets.size() > 1;
            if (myIsMultipleTargets && getSVNEnvironment().isNoNewLine()) {
                SVNErrorMessage errorMessage = SVNErrorMessage.create(SVNErrorCode.CL_ARG_PARSING_ERROR, "--no-newline is only available for single-target, non-recursive info operations");
                SVNErrorManager.error(errorMessage, SVNLogType.CLIENT);
            }
            findPrintWhat(getSVNEnvironment().getShowItem());
            myIsStartNewLine = false;
        } else {
            if (getSVNEnvironment().isIncremental()) {
                SVNErrorManager.error(SVNErrorMessage.create(SVNErrorCode.CL_ARG_PARSING_ERROR, "'incremental' option only valid in XML mode"), SVNLogType.CLIENT);
            }
            if (getSVNEnvironment().isNoNewLine()) {
                SVNErrorMessage errorMessage = SVNErrorMessage.create(SVNErrorCode.CL_ARG_PARSING_ERROR, "--no-newline' is only valid with --show-item");
                SVNErrorManager.error(errorMessage, SVNLogType.CLIENT);
            }
        }
        SVNDepth depth = getSVNEnvironment().getDepth();
        if (depth == SVNDepth.UNKNOWN) {
            depth = SVNDepth.EMPTY;
        }
        SVNWCClient client = getSVNEnvironment().getClientManager().getWCClient();
        SVNNotifyPrinter printer = new SVNNotifyPrinter(getSVNEnvironment());
        client.setEventHandler(printer);
        boolean seenNonExistingTargets = false;
        for(int i = 0; i < targets.size(); i++) {
            String targetName = (String) targets.get(i);
            SVNPath target = new SVNPath(targetName, true);
            SVNRevision pegRevision = target.getPegRevision();

            if (target.isURL()) {
                if (pegRevision == SVNRevision.UNDEFINED) {
                    pegRevision = SVNRevision.HEAD;
                }
                myTargetIsPath = false;
            } else {
                myTargetIsPath = true;
            }

            try {
                if (target.isFile()) {
                    client.doInfo(target.getFile(), pegRevision, getSVNEnvironment().getStartRevision(), depth, 
                            getSVNEnvironment().getChangelistsCollection(), this);
                } else {
                    client.doInfo(target.getURL(), pegRevision, getSVNEnvironment().getStartRevision(), depth, this);
                }
            } catch (SVNException e) {
                SVNErrorMessage err = e.getErrorMessage();
                if (err.getErrorCode() == SVNErrorCode.UNVERSIONED_RESOURCE) {
                    getSVNEnvironment().getErr().print(SVNCommandUtil.getLocalPath(target.getTarget()) + ": (Not a versioned resource)\n\n");
                }  else {
                    getSVNEnvironment().handleWarning(err, new SVNErrorCode[] {SVNErrorCode.RA_ILLEGAL_URL, SVNErrorCode.WC_PATH_NOT_FOUND}, 
                        getSVNEnvironment().isQuiet());
                    getSVNEnvironment().getErr().println();
                    seenNonExistingTargets = true;
                }
            }
        }
        if (getSVNEnvironment().isXML() && !getSVNEnvironment().isIncremental()) {
            printXMLFooter("info");
        } else if (getSVNEnvironment().getShowItem() != null && !getSVNEnvironment().isNoNewLine() && myIsStartNewLine) {
            getSVNEnvironment().getOut().print('\n');
        }
        if (seenNonExistingTargets) {
            SVNErrorManager.error(SVNErrorMessage.create(SVNErrorCode.ILLEGAL_TARGET, "Could not display info for all targets because some targets don't exist"), SVNLogType.CLIENT);
        }
    }

    public void handleInfo(SVNInfo info) throws SVNException {
        if (getSVNEnvironment().isXML()) {
            printInfoXML(info);
        } else if (getSVNEnvironment().getShowItem() != null) {
            printInfoItem(info);
        } else {
            printInfo(info);
        }
    }

    protected void printInfo(SVNInfo info) {
        StringBuffer buffer = new StringBuffer();
        String path = null;
        if (info.getFile() != null) {
            path = getSVNEnvironment().getRelativePath(info.getFile());
            path = SVNCommandUtil.getLocalPath(path);
        } else {
            path = info.getPath();
            if ("".equals(path) && info.getURL() != null) {
                path = SVNPathUtil.tail(info.getURL().getPath());
            }
        }
        buffer.append("Path: " + path + "\n");
        if (info.getKind() != SVNNodeKind.DIR) {
            buffer.append("Name: " + SVNPathUtil.tail(path.replace(File.separatorChar, '/')) + "\n");
        }
        if (info.getWorkingCopyRoot() != null) {
            buffer.append("Working Copy Root Path: " + SVNPathUtil.validateFilePath(info.getWorkingCopyRoot().getAbsolutePath())+ "\n");
        }
        if (info.getURL() != null) {
            buffer.append("URL: " + info.getURL() + "\n");
        }
        if (info.getRepositoryRootURL() != null) {
            if (info.getURL() != null) {
                final String relativeURL = SVNURLUtil.getRelativeURL(info.getRepositoryRootURL(), info.getURL(), true);
                buffer.append("Relative URL: ^/" + relativeURL + "\n");
            }
            buffer.append("Repository Root: " + info.getRepositoryRootURL() + "\n");
        }
        if (info.getRepositoryUUID() != null) {
            buffer.append("Repository UUID: " + info.getRepositoryUUID() + "\n");
        }
        if (info.getRevision() != null && info.getRevision().isValid()) {
            buffer.append("Revision: " + info.getRevision() + "\n");
        }
        String kind = info.getKind() == SVNNodeKind.DIR ? "directory" : (info.getKind() != null ? info.getKind().toString() : "none");
        buffer.append("Node Kind: " + kind + "\n");
        if (!info.isRemote()) {
            if (info.getSchedule() == null) {
                buffer.append("Schedule: normal\n");
            } else {
                buffer.append("Schedule: " + info.getSchedule() + "\n");
            }
            if (info.getDepth() != null) {
                if (info.getDepth() != SVNDepth.UNKNOWN && info.getDepth() != SVNDepth.INFINITY) {
                    buffer.append("Depth: " + info.getDepth() + "\n");
                }
            }
            if (info.getCopyFromURL() != null) {
                buffer.append("Copied From URL: " + info.getCopyFromURL() + "\n");
            }
            if (info.getCopyFromRevision() != null && info.getCopyFromRevision().getNumber() >= 0) {
                buffer.append("Copied From Rev: " + info.getCopyFromRevision() + "\n");
            }
            if (info.getMovedFromPath() != null) {
                final File relativePath = SVNWCUtils.skipAncestor(info.getWorkingCopyRoot(), info.getMovedFromPath());
                if (relativePath != null && !"".equals(relativePath.getPath())) {
                    buffer.append("Moved From: " + relativePath + "\n");
                } else {
                    buffer.append("Moved From: " + info.getMovedFromPath() + "\n");
                }
            }
            if (info.getMovedToPath() != null) {
                final File relativePath = SVNWCUtils.skipAncestor(info.getWorkingCopyRoot(), info.getMovedToPath());
                if (relativePath != null && !"".equals(relativePath.getPath())) {
                    buffer.append("Moved To: " + relativePath + "\n");
                } else {
                    buffer.append("Moved To: " + info.getMovedToPath() + "\n");
                }
            }
        }
        if (info.getAuthor() != null) {
            buffer.append("Last Changed Author: " + info.getAuthor() + "\n");
        }
        if (info.getCommittedRevision() != null && info.getCommittedRevision().getNumber() >= 0) {
            buffer.append("Last Changed Rev: " + info.getCommittedRevision() + "\n");
        }
        if (info.getCommittedDate() != null) {
            buffer.append("Last Changed Date: " +
                    SVNDate.formatHumanDate(info.getCommittedDate(), getSVNEnvironment().getClientManager().getOptions()) + "\n");
        }
        if (!info.isRemote()) {
            if (info.getTextTime() != null) {
                buffer.append("Text Last Updated: " +
                        SVNDate.formatHumanDate(info.getTextTime(), getSVNEnvironment().getClientManager().getOptions()) + "\n");
            }
            if (info.getPropTime() != null) {
                buffer.append("Properties Last Updated: " +
                        SVNDate.formatHumanDate(info.getPropTime(), getSVNEnvironment().getClientManager().getOptions()) + "\n");
            }
            if (info.getChecksum() != null) {
                buffer.append("Checksum: " + info.getChecksum() + "\n");
            }
            if (info.getConflictOldFile() != null) {
                String cpath = getSVNEnvironment().getRelativePath(info.getConflictOldFile());
                cpath = SVNCommandUtil.getLocalPath(cpath);
                buffer.append("Conflict Previous Base File: " + cpath + "\n");
            }
            if (info.getConflictWrkFile() != null) {
                String cpath = getSVNEnvironment().getRelativePath(info.getConflictWrkFile());
                cpath = SVNCommandUtil.getLocalPath(cpath);
                buffer.append("Conflict Previous Working File: " + cpath + "\n");
            }
            if (info.getConflictNewFile() != null) {
                String cpath = getSVNEnvironment().getRelativePath(info.getConflictNewFile());
                cpath = SVNCommandUtil.getLocalPath(cpath);
                buffer.append("Conflict Current Base File: " + cpath + "\n");
            }
            if (info.getPropConflictFile() != null) {
                String cpath = getSVNEnvironment().getRelativePath(info.getPropConflictFile());
                cpath = SVNCommandUtil.getLocalPath(cpath);
                buffer.append("Conflict Properties File: " + cpath + "\n");
            }
        }
        if (info.getLock() != null) {
            SVNLock lock = info.getLock();
            if (lock.getID() != null) {
                buffer.append("Lock Token: " + lock.getID() + "\n");
            }
            if (lock.getOwner() != null) {
                buffer.append("Lock Owner: " + lock.getOwner() + "\n");
            }
            if (lock.getCreationDate() != null && lock.getCreationDate().getTime() != 0) {
                buffer.append("Lock Created: " +
                        SVNDate.formatHumanDate(lock.getCreationDate(), getSVNEnvironment().getClientManager().getOptions()) + "\n");
            }
            if (lock.getExpirationDate() != null && lock.getExpirationDate().getTime() != 0) {
                buffer.append("Lock Expires: " +
                        SVNDate.formatHumanDate(lock.getExpirationDate(), getSVNEnvironment().getClientManager().getOptions()) + "\n");
            }
            if (lock.getComment() != null) {
                buffer.append("Lock Comment ");
                int lineCount = SVNCommandUtil.getLinesCount(lock.getComment());
                buffer.append(lineCount > 1 ? "(" + lineCount + " lines)" : "(1 line)");
                buffer.append(":\n");
                buffer.append(lock.getComment());
                buffer.append("\n");
            }
        }
        if (info.getChangelistName() != null) {
            buffer.append("Changelist: " + info.getChangelistName() + "\n");
        }
        if (info.getTreeConflict() != null) {
        	SVNTreeConflictDescription tc = info.getTreeConflict();
        	String description = SVNTreeConflictUtil.getHumanReadableConflictDescription(tc);
            buffer.append("Tree conflict: " + description + "\n");
            SVNConflictVersion left = tc.getSourceLeftVersion();
            buffer.append("  Source  left: " + SVNTreeConflictUtil.getHumanReadableConflictVersion(left) + "\n");
            SVNConflictVersion right = tc.getSourceRightVersion();
            buffer.append("  Source right: " + SVNTreeConflictUtil.getHumanReadableConflictVersion(right) + "\n");
        }
        buffer.append("\n");
        getSVNEnvironment().getOut().print(buffer.toString());
    }

    private void printInfoItem(SVNInfo info) throws SVNException {
        String targetPath = !myIsMultipleTargets ? null
                : (!myTargetIsPath ? info.getURL().toString()
        : getSVNEnvironment().getRelativePath(info.getFile()));

        if (myIsStartNewLine) {
            getSVNEnvironment().getOut().print('\n');
        }

        switch (myPrintWhat) {
            case KIND:
                printInfoItemString(info.getKind().toString(), targetPath);
                break;
            case URL:
                printInfoItemString(info.getURL().toString(), targetPath);
                break;
            case RELATIVE_URL:
                printInfoItemString(relativeUrl(info), targetPath);
                break;
            case REPS_ROOT_URL:
                printInfoItemString(info.getRepositoryRootURL().toString(), targetPath);
                break;
            case REPOS_UUID:
                printInfoItemString(info.getRepositoryUUID(), targetPath);
                break;
            case REVISION:
                printInfoItemRevision(info.getRevision(), targetPath);
                break;
            case LAST_CHANGED_REV:
                printInfoItemRevision(info.getCommittedRevision(), targetPath);
                break;
            case LAST_CHANGED_DATE:
                printInfoItemString(info.getCommittedDate() == null ? null : SVNDate.formatDate(info.getCommittedDate()), targetPath);
                break;
            case LAST_CHANGED_AUTHOR:
                printInfoItemString(info.getAuthor(), targetPath);
                break;
            case WC_ROOT:
                printInfoItemString(info.getWorkingCopyRoot() == null ? null : SVNFileUtil.getFilePath(info.getWorkingCopyRoot().getAbsoluteFile()), targetPath);
                break;
            default:
                SVNErrorManager.assertionFailure(false, null, SVNLogType.CLIENT);
                break;
        }
        myIsStartNewLine = true;
    }

    private String relativeUrl(SVNInfo info) {
        final String relativePath = SVNPathUtil.getRelativePath(info.getRepositoryRootURL().toString(), info.getURL().toString());
        return "^/" + relativePath;
    }

    private void printInfoItemString(String text, String targetPath) {
        if (text != null) {
            if (targetPath != null) {
                getSVNEnvironment().getOut().printf("%s %s", text, targetPath);
            } else {
                getSVNEnvironment().getOut().print(text);
            }
        } else if (targetPath != null) {
            getSVNEnvironment().getOut().printf("%s %s", "", targetPath);
        }
    }

    private void printInfoItemRevision(SVNRevision revision, String targetPath) {
        if (SVNRevision.isValidRevisionNumber(revision.getNumber())) {
            if (targetPath != null) {
                getSVNEnvironment().getOut().printf("%s %s", revision.getNumber(), targetPath);
            } else {
                getSVNEnvironment().getOut().printf("%s", revision.getNumber());
            }
        } else if (targetPath != null) {
            getSVNEnvironment().getOut().printf("%s %s", "", targetPath);
        }
    }

    protected void printInfoXML(SVNInfo info) {
        StringBuffer buffer = new StringBuffer();
        String path = null;
        if (info.getFile() != null) {
            path = getSVNEnvironment().getRelativePath(info.getFile());
            path = SVNCommandUtil.getLocalPath(path);
        } else {
            path = info.getPath();
        }
        Map attrs = new LinkedHashMap();
        attrs.put("kind", info.getKind().toString());
        attrs.put("path", path);
        attrs.put("revision", info.getRevision() != SVNRevision.UNDEFINED ? info.getRevision().toString() : "Resource is not under version control.");
        buffer = openXMLTag("entry", SVNXMLUtil.XML_STYLE_NORMAL, attrs, buffer);
        
        String url = info.getURL() != null ? info.getURL().toString() : null;
        buffer = openCDataTag("url", url, buffer);
        
        String rootURL = info.getRepositoryRootURL() != null ? info.getRepositoryRootURL().toString() : null;
        if (rootURL != null && url != null) {
            final String relativeURL = SVNURLUtil.getRelativeURL(info.getRepositoryRootURL(), info.getURL(), true);
            buffer = openCDataTag("relative-url", "^/" + relativeURL, buffer);
        }
        String uuid = info.getRepositoryUUID();
        if (rootURL != null || uuid != null) {
            buffer = openXMLTag("repository", SVNXMLUtil.XML_STYLE_NORMAL, null, buffer);
            buffer = openCDataTag("root", rootURL, buffer);
            buffer = openCDataTag("uuid", uuid, buffer);
            buffer = closeXMLTag("repository", buffer);
        }   
        if (info.getFile() != null) {
            buffer = openXMLTag("wc-info", SVNXMLUtil.XML_STYLE_NORMAL, null, buffer);
            if (info.getWorkingCopyRoot() != null) {
                buffer = openCDataTag("wcroot-abspath", SVNPathUtil.validateFilePath(info.getWorkingCopyRoot().getAbsolutePath()), buffer);
            }
            String schedule = info.getSchedule();
            if (schedule == null || "".equals(schedule)) {
                schedule = "normal";
            }
            buffer = openCDataTag("schedule", schedule, buffer);
            if (info.getDepth() != null) {
                SVNDepth depth = info.getDepth();
                if (depth == SVNDepth.UNKNOWN && info.getKind() == SVNNodeKind.FILE) {
                    depth = SVNDepth.INFINITY;
                }
                buffer = openCDataTag("depth", depth.getName(), buffer);
            }
            if (info.getCopyFromURL() != null) {
                buffer = openCDataTag("copy-from-url", info.getCopyFromURL().toString(), buffer);
            }
            if (info.getCopyFromRevision() != null && info.getCopyFromRevision().isValid()) {
                buffer = openCDataTag("copy-from-rev", info.getCopyFromRevision().toString(), buffer);
            }
            if (info.getTextTime() != null) {
                buffer = openCDataTag("text-updated", ((SVNDate) info.getTextTime()).format(), buffer);
            }
            if (info.getPropTime() != null) {
                buffer = openCDataTag("prop-updated", ((SVNDate) info.getPropTime()).format(), buffer);
            }
            buffer = openCDataTag("checksum", info.getChecksum(), buffer);
            buffer = openCDataTag("changelist", info.getChangelistName(), buffer);
            if (info.getMovedFromPath() != null) {
                final File relativePath = SVNWCUtils.skipAncestor(info.getWorkingCopyRoot(), info.getMovedFromPath());
                if (relativePath != null && !"".equals(relativePath.getPath())) {
                    buffer = openCDataTag("moved-from", relativePath.getPath(), buffer);
                } else {
                    buffer = openCDataTag("moved-from", info.getMovedFromPath().getPath(), buffer);
                }
            }
            if (info.getMovedToPath() != null) {
                final File relativePath = SVNWCUtils.skipAncestor(info.getWorkingCopyRoot(), info.getMovedToPath());
                if (relativePath != null && !"".equals(relativePath.getPath())) {
                    buffer = openCDataTag("moved-to", relativePath.getPath(), buffer);
                } else {
                    buffer = openCDataTag("moved-to", info.getMovedToPath().getPath(), buffer);
                }
            }
            buffer = closeXMLTag("wc-info", buffer);
        }
        if (info.getAuthor() != null || info.getCommittedRevision().isValid() ||
                info.getCommittedDate() != null) {
            openXMLTag("commit", SVNXMLUtil.XML_STYLE_NORMAL, "revision", info.getCommittedRevision().toString(), buffer);
            buffer = openCDataTag("author", info.getAuthor(), buffer);
            if (info.getCommittedDate() != null) {
                buffer = openCDataTag("date", ((SVNDate) info.getCommittedDate()).format(), buffer);
            }
            buffer = closeXMLTag("commit", buffer);
        }
        
        if (info.getConflictNewFile() != null || info.getConflictOldFile() != null || info.getConflictWrkFile() != null ||
                info.getPropConflictFile() != null) {
            buffer = openXMLTag("conflict", SVNXMLUtil.XML_STYLE_NORMAL, null, buffer);
            if (info.getConflictOldFile() != null) {
                buffer = openCDataTag("prev-base-file", info.getConflictOldFile().getName(), buffer);
            }
            if (info.getConflictWrkFile() != null) {
                buffer = openCDataTag("prev-wc-file", info.getConflictWrkFile().getName(), buffer);
            }
            if (info.getConflictNewFile() != null) {
                buffer = openCDataTag("cur-base-file", info.getConflictNewFile().getName(), buffer);
            }
            if (info.getPropConflictFile() != null) {
                buffer = openCDataTag("prop-file", info.getPropConflictFile().getName(), buffer);
            }
            buffer = closeXMLTag("conflict", buffer);
        }
        
        if (info.getLock() != null) {
            SVNLock lock = info.getLock();
            buffer = openXMLTag("lock", SVNXMLUtil.XML_STYLE_NORMAL, null, buffer);
            buffer = openCDataTag("token", lock.getID(), buffer);
            buffer = openCDataTag("owner", lock.getOwner(), buffer);
            buffer = openCDataTag("comment", lock.getComment(), buffer);
            if (lock.getCreationDate() != null) {
                buffer = openCDataTag("created", ((SVNDate) lock.getCreationDate()).format(), buffer);
            }
            if (lock.getExpirationDate() != null) {
                buffer = openCDataTag("expires", ((SVNDate) lock.getExpirationDate()).format(), buffer);
            }
            buffer = closeXMLTag("lock", buffer);
        }
        if (info.getTreeConflict() != null) {
        	SVNTreeConflictDescription tc = info.getTreeConflict();
        	Map attributes = new SVNHashMap();
        	attributes.put("victim", tc.getPath().getName());
        	attributes.put("kind", tc.getNodeKind().toString());
        	attributes.put("operation", tc.getOperation().getName());
        	if (tc.getConflictAction() == SVNConflictAction.EDIT) {
            	attributes.put("action", "edit");
        	} else if (tc.getConflictAction() == SVNConflictAction.ADD) {
            	attributes.put("action", "add");
        	} else if (tc.getConflictAction() == SVNConflictAction.DELETE) {
            	attributes.put("action", "delete");
        	}
        	if (tc.getConflictReason() == SVNConflictReason.EDITED) {
            	attributes.put("reason", "edit");
        	} else if (tc.getConflictReason() == SVNConflictReason.OBSTRUCTED) {
            	attributes.put("reason", "obstruction");
        	} else if (tc.getConflictReason() == SVNConflictReason.DELETED) {
        		attributes.put("reason", "delete");
        	} else if (tc.getConflictReason() == SVNConflictReason.ADDED) {
        		attributes.put("reason", "add");
        	} else if (tc.getConflictReason() == SVNConflictReason.MISSING) {
        		attributes.put("reason", "missing");
        	} else if (tc.getConflictReason() == SVNConflictReason.UNVERSIONED) {
        		attributes.put("reason", "unversioned");
        	}
        	buffer = openXMLTag("tree-conflict", SVNXMLUtil.XML_STYLE_NORMAL, attributes, buffer);
        	SVNConflictVersion left = tc.getSourceLeftVersion();
        	if (left != null) {
        		buffer = printConflictVersionXML(left, "source-left", buffer);        		
        	}
        	SVNConflictVersion right = tc.getSourceLeftVersion();
        	if (right != null) {
        		buffer = printConflictVersionXML(right, "source-right", buffer);        		
        	}
        	buffer = closeXMLTag("tree-conflict", buffer);
        }
        buffer = closeXMLTag("entry", buffer);
        
        getSVNEnvironment().getOut().print(buffer.toString());
    }

    private StringBuffer printConflictVersionXML(SVNConflictVersion version, String name, StringBuffer target) {
    	Map attributes = new SVNHashMap();
    	attributes.put("side", name);
    	if (version.getRepositoryRoot() != null) {
    		attributes.put("repos-url", version.getRepositoryRoot().toString());
    	}
    	if (version.getPath() != null) {
    		attributes.put("path-in-repos", version.getPath());
    	}
    	if (version.getPegRevision() >= 0) {
    		attributes.put("revision", Long.toString(version.getPegRevision()));
    	}
    	if (version.getKind() != SVNNodeKind.UNKNOWN) {
    		attributes.put("kind", version.getKind().toString());
    	}    	
    	return openXMLTag("version", SVNXMLUtil.XML_STYLE_SELF_CLOSING, attributes, target);
    }

    private void findPrintWhat(String keyword) throws SVNException {
        final List<String> tokens = new ArrayList<String>();
        for (SVNInfoItemType itemType : SVNInfoItemType.values()) {
            tokens.add(itemType.getName());
        }
        final int similarity = similarityCheck(keyword, tokens);
        switch (similarity) {
            case 0:
                myPrintWhat = SVNInfoItemType.forName(keyword);
                break;
            case 1:
                SVNErrorMessage errorMessage = SVNErrorMessage.create(SVNErrorCode.CL_ARG_PARSING_ERROR, "''{0}'' is not a valid value for --show-item", keyword);
                SVNErrorManager.error(errorMessage, SVNLogType.CLIENT);
                break;
            case 2:
                errorMessage = SVNErrorMessage.create(SVNErrorCode.CL_ARG_PARSING_ERROR, "''{0}'' is not a valid value for --show-item; did you mean ''{1}''?", keyword, tokens.get(0));
                SVNErrorManager.error(errorMessage, SVNLogType.CLIENT);
                break;
            case 3:
                errorMessage = SVNErrorMessage.create(SVNErrorCode.CL_ARG_PARSING_ERROR, "''{0}'' is not a valid value for --show-item; did you mean ''{1}'' or ''{2}''?", keyword, tokens.get(0), tokens.get(1));
                SVNErrorManager.error(errorMessage, SVNLogType.CLIENT);
                break;
            default:
                errorMessage = SVNErrorMessage.create(SVNErrorCode.CL_ARG_PARSING_ERROR, "''{0}'' is not a valid value for --show-item; did you mean ''{1}'', ''{2}'' or ''{3}''?", keyword, tokens.get(0), tokens.get(1), tokens.get(2));
                SVNErrorManager.error(errorMessage, SVNLogType.CLIENT);
                break;
        }
    }

    private int similarityCheck(String key, List<String> tokens) {
        final int[] scores = new int[tokens.size()];
        for (int i = 0; i < tokens.size(); i++) {
            scores[i] = stringDiff(key, tokens.get(i));
        }
        final List<Integer> tokenIndices = new ArrayList<Integer>(tokens.size());
        for (int i = 0; i < tokens.size(); i++) {
            tokenIndices.add(i);
        }
        Collections.sort(tokenIndices, new Comparator<Integer>() {
            @Override
            public int compare(Integer index1, Integer index2) {
                return -(scores[index1] - scores[index2]);
            }
        });
        int result = 1;
        for (Integer index : tokenIndices) {
            if (scores[index] >= (2 * SIM_RANGE_MAX + 1) / 3) {
                result++;
            }
        }
        //exact match
        if (tokens.get(tokenIndices.get(0)).equals(key)) {
            return 0;
        }
        final List<String> sortedTokens = new ArrayList<String>(tokens.size());
        for (Integer index : tokenIndices) {
            sortedTokens.add(tokens.get(index));
        }
        tokens.clear();
        tokens.addAll(sortedTokens);

        return result;
    }

    private int stringDiff(String s1, String s2) {
        int start1 = 0;
        int start2 = 0;
        int end1 = s1.length();
        int end2 = s2.length();
        int lcs = 0;
        int total = s1.length() + s2.length();

        while (start1 < end1 && start2 < end2 && s1.charAt(start1) == s2.charAt(start2)) {
            start1++;
            start2++;
            lcs++;
        }

        while (start1 < end1 && start2 < end2 && s1.charAt(end1 - 1) == s2.charAt(end2 - 1)) {
            end1--;
            end2--;
            lcs++;
        }

        if (start1 < end1 && start2 < end2) {
            int rest1 = end1 - start1;
            int rest2 = end2 - start2;
            int slots = Math.min(rest1, rest2);

            String s;
            int p;

            if (rest1 < rest2) {
                p = start1;
                start1 = start2;
                start2 = p;

                p = end1;
                end1 = end2;
                end2 = p;

                s = s1;
                s1 = s2;
                s2 = s;
            }

            int[] curr = new int[2 * (slots + 1)];
            int[] prev = new int[2 * (slots + 1)];

            for (p = start1; p < end1; p++) {
                for (int i = 1; i < slots; i++) {
                    if (s1.charAt(p) == s2.charAt(i - 1)) {
                        curr[i] = prev[i - 1];
                    } else {
                        curr[i] = (curr[i - 1] > prev[i]) ? curr[i - 1] : prev[i];
                    }
                }
                {
                    int[] tmp = curr;
                    curr = prev;
                    prev = tmp;
                }
                lcs += prev[slots];
            }
        }

        if (total != 0) {
            return (int) (((2L * SIM_RANGE_MAX * lcs + total / 2)) / total);
        } else {
            return SIM_RANGE_MAX;
        }
    }

    private static enum SVNInfoItemType {
        KIND("kind"),
        URL("url"),
        RELATIVE_URL("relative-url"),
        REPS_ROOT_URL("repos-root-url"),
        REPOS_UUID("repos-uuid"),
        REVISION("revision"),
        LAST_CHANGED_REV("last-changed-revision"),
        LAST_CHANGED_DATE("last-changed-date"),
        LAST_CHANGED_AUTHOR("last-changed-author"),
        WC_ROOT("wc-root");

        public static SVNInfoItemType forName(String name) {
            final SVNInfoItemType[] values = values();
            for (SVNInfoItemType value : values) {
                if (value.getName().equals(name)) {
                    return value;
                }
            }
            return null;
        }

        private final String name;

        private SVNInfoItemType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
