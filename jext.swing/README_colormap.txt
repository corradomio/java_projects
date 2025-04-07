Copy & past code from

    https://github.com/mahdilamb/colormap


The main access to the colormaps is through njext.swing.colormap.Colormap

    Colormap.named()
    Colormaps.reversedColormap()
    Colormaps.fluidColormap()

Using reference colormaps

    Reference colormaps can either be accessed through Colormaps.get(String), which can take a String such as "
    sequential.viridis", "viridis" or "sequential.viridis.reversed". Alternatively, they can be accessed using a
    class path embedded in the Colormaps class e.g. Colormaps.Sequential.Viridis() (and then, for exampled made
    fluid Colormaps.fluidColormap(Colormaps.Sequential.Viridis())).

Creating colormaps

    Colormaps can be created in two ways either using a builder Colormaps.buildSequential() and
    Colormaps.buildQualitative(), or by extending jext.swing.colormap.SequentialColormap and
    jext.swing.colormap.QualitativeColormap. Reference colormaps are created by the latter approach.

Fluid colormaps example

    As the fluid colormaps are autoranging, they fire an even when the color is changed. The below example shows how
    this might be done using a lambda expression. The original value is 0, but this changes when a new color is
    requested from the colormap, and the range is changed, and a new event is triggered.


    import jext.swing.colormap.Colormaps;
    import jext.swing.colormap.FluidColormap;

    public class Test {
        public static void main(final String[] args) {
            final FluidColormap viridis = Colormaps.fluidColormap(Colormaps.get("Viridis"));
            viridis.get(0f, color -> {
                System.out.println(color);
            });

            viridis.get(-1);
        }
    }
