RCP File Explorer
=================

Requirements: JDK 17 and an Eclipse SDK installation with the RCP features.

Build and run on this machine from PowerShell:
    .\build.ps1 -Run

Build only:
    .\build.ps1

For another installation:
    .\build.ps1 -EclipseHome 'C:\eclipse' -JavaHome 'C:\jdk-17' -Run

The script compiles and packages build/rcp.ide_1.0.0.jar. Its launch uses
the dependencies listed in rcp_ide.product, a separate build/configuration,
and runtime-workspace. It does not modify the Eclipse installation.

The product targets the local Eclipse 4.30 (2023-12) Windows x86_64 runtime.
Open rcp_ide.product in PDE to launch or export it. When changing Eclipse
versions, update the product dependencies against the new target platform.

In Eclipse PDE, import this directory as an existing project, select a
Java 17 JRE, and use Run Configurations > Eclipse Application. Select
product rcp.ide.product (or application rcp.ide.application). On the
Plug-ins tab select this project and Add Required Plug-ins, then Validate
Plug-ins and Run.

File > Open and the Open toolbar button show a file dialog. Double-click
a file in the left tree to open it directly. Directories load on expansion.
The divider can be dragged to resize the two columns.

The right side contains Text Editor and Graphic Canvas tabs. Text files
must be UTF-8 and at most 8 MiB. Ctrl+S saves edits to the original file.
An asterisk indicates unsaved changes. Close clears the text editor;
opening another file and exiting also offer Save / Discard / Cancel.
Ctrl+O opens a file; Ctrl+W closes it.

On the canvas, drag the left mouse button to draw and right-click to clear.
Drawings survive tab changes and repainting but are not saved to disk.
Help > About displays the application information.

Window > Spies > UI Spy opens a non-modal tree of the live SWT widget
hierarchy (shells, controls, menus, tool items). Each node shows its type
and id: the E4 model elementId, else the widget "name" data, else the CSS
id. Select a node for details; Refresh rebuilds the tree.

Sources are in plugin/src/rcp/ide. Application and the workbench advisors
manage startup, the window, menus and toolbar. Perspective hosts the fixed
ExplorerView, which owns the resizable filesystem tree and the two tabs.
