# jext.colors

Matplotlib 3.11.2 built-in colormaps in `jext.awt.color`, with one public Java
class per forward/reversed pair (91 classes containing 182 maps, including aliases). Pandas uses these same
Matplotlib colormaps for plotting and table gradients; it does not require a
separate set of palette definitions.

```java
import java.awt.Color;
import jext.awt.color.*;

ColorMap map = new Viridis(); // or Viridis.INSTANCE
Color middle = map.getColor(0.5);       // normalized scalar
Color first = map.getColor(0);          // zero-based table index
Color[] series = map.sample(5);         // evenly spaced, pandas-style
Color[] palette = Tab10.INSTANCE.getColors();
ColorMap reversed = Viridis.REVERSED;   // or new Viridis(true)
ColorMap byName = ColorMaps.get("RdYlBu_r");
// ColorMaps.names() lists all case-sensitive Python names.
```

Class names capitalize the first letter of each underscore-separated word,
preserving other capitalization (`gist_earth` becomes `GistEarth`, `RdYlBu`
stays `RdYlBu`). Each class contains `INSTANCE` and `REVERSED` maps.
The registry retains both Python names, e.g. `viridis` and `viridis_r`.
The former separate `ViridisReversed`-style classes are replaced by the
corresponding class's `REVERSED` field or boolean constructor.

Tables retain Matplotlib's default resolution (usually 256 entries; categorical
and twilight maps retain their native sizes). RGB components are rounded to
8-bit sRGB using `matplotlib.colors.to_hex`. Normalized lookup uses discrete
bins, clips values outside [0, 1], and returns transparent black for NaN.
Use a double for normalized values: `getColor(1.0)` is the final color,
whereas `getColor(1)` is the second table entry. Arrays returned by the API are
copies. Custom Matplotlib colormaps, configurable under/over/bad colors, and
arbitrary-resolution resampling are not included.

Build and test with JDK 17 or later: `mvn test`.

Named colors are collected in `jext.awt.color.NamedColors`:

```java
Color css = jext.awt.color.NamedColors.get("rebeccapurple");
Color tableau = jext.awt.color.NamedColors.get("tab:blue");
Color xkcd = jext.awt.color.NamedColors.get("xkcd:sky blue");
```

`NamedColors.MATPLOTLIB` contains all 1,163 built-in Matplotlib names, including
base shorthand and gray/grey aliases. `NamedColors.CSS` contains the 148 HTML/CSS
names. `NamedColors.ALL` combines these and adds `transparent`; `names()` lists
its keys. All maps and name sets are immutable. `get(name)` ignores case and
surrounding whitespace and rejects unknown names. Prefixes distinguish XKCD and
Tableau colors from CSS names. Context-dependent values such as `currentColor`
and `C0`, numeric/hex color syntax, and Matplotlib's special `none` value are
outside this named-color catalog.

To regenerate the Java definitions and Python reference test fixture:

```text
python -m venv .venv-colormaps
.venv-colormaps\Scripts\python -m pip install matplotlib==3.11.2
.venv-colormaps\Scripts\python tools/generate_colormaps.py
.venv-colormaps\Scripts\python tools/generate_named_colors.py
```

Python is needed only for regeneration. Java has no runtime dependencies beyond
the JDK. The generated data retain the upstream license in
`MATPLOTLIB-LICENSE.txt`.

Sources:
- https://matplotlib.org/stable/gallery/color/colormap_reference.html
- https://pandas.pydata.org/docs/user_guide/visualization.html#colormaps
- https://matplotlib.org/stable/gallery/color/named_colors.html
- https://www.w3.org/TR/css-color/#named-colors
