JPlotter concepts
-----------------

   In order:

        simple object:   Point, Line, Text, ...

        simple container:  Points, Lines, Curves, Triangles, Texts...
            properties
                name, color, properties specific of the primitive object
                bounds

        completeRenderer:   contains lists of simple containers (.points, .lines, ...)

        coordSysRenderer:   define the plot rectangle in the objects coordinates
            contain an instance of completeRenderer

        canvas: define the screen plot area
            contains an instance of coordSysRenderer


    canvas: JPlotterCanvas
        coordSysRenderer: CoordSysRenderer
            content: CoordSysRenderer
                points: PointsRenderer
                    itemsToRender: List<Points>
                lines:  LinesRenderer
                    itemsToRender:  List<Lines>
                traingles: TrianglesRenderer
                    itemsToRender:  List<Triangles>
                curves: CurvesRenderer
                    itemsToRender:  List<Curves>
                text: TextRenderer
                    itemsToRender:  List<Text>




JPlotter extensions
-------------------

    jext.jplotter.renderables.Point|Line
        Aggiunto gli oggetti semplici che possono essere inizializzati in diversi modi:

        Point(x,y)

        Line addPoint(Point)
        Line addPoints(Point...)
        Line addPoints(List<Point>)
        Line addPoint(x, y)
        Line addPoints(x[], y[])
        Line addPoints(xy[][])
        Line addPoints(coord...)


    jext.jplotter.renderables.Points|Lines
            invece di assegnare le proprita' di disegno ad ogni singolo elemento (punto, segmento, linea, ...)
            Points e Lines (e ...) vengono inizializzati con le proproprieta' comuni

        Points
            name
            color
            point size
        Lines
            name
            color
            thickness
            pattern

        quando si aggiunge un oggetto, viene inizializzato con le proprita' di default

        inoltre e' possibile assegnare un nome. In questo modo diventa semplice creare la leggenda: tutte le info
        necessarie sono gia' contenute in Points, Lines, ...
        possono essere popolati con Point e Line

    jext.jplotter.renderers.CompleteRenderer
            added support to compute the bounds considering all items contained
            added methods to add one or multiple elements:

        Bounds getBounds()
        CompleteRenderer addItemToRender(Renderable item)
        CompleteRenderer add(Renderable item)
        CompleteRenderer addAll(Renderable... items)
        CompleteRenderer addAll(List<Renderable> items)


    jext.jplotter.renderers.CoordSysRenderer
            added support for automatic bounds


Swing
-----

    JPlotterCanvas
        e' un JComponent e quindi puo' essere usato all'interno di un container Swing
        Dovrebbe essere inizializzato con

            canvas: JPlotterCanvas
                coordSysRenderer: CoordSysRenderer
                    content: CompleteRenderer

        se necessario, vengono creati automaticamente.


    JPlotterFrame: JFrame
        e' un semplice JFrame contenente un JPlotterCanvas che puo' essere usato per
        fare delle visualizzazioni 'al volo'




-----------------------------------------------------------------------------

Finalmente: JPlotter fa quello che serve!
In teoria puo' essere usato anche con LWJGL
MA l'installazione di questa libreria e' un incubo.
Comunque, funziona ANCHE SENZA.

Oggetti utilizzati per il rendering

    Renderable
        Points
        Lines
        Triangles
        Curves
        Text
        Legend


Mancano oggetti come

        Polygons
        Graphs


---------------------------------
Warn: JPlotterCanvas E' un componente Swing.
    Viene convertito con

    JPlotterCanvas
        BlankCanvas             <-- if LWJGL is available!
        BlankCanvasFallback     <-- for Swing ONLY
