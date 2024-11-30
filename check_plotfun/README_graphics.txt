Image
    VolatileImage
        SunVolatileImage
            AccelTypedVolatileImage
    ToolkitImage
        MultiResolutionToolkitImage
    BufferedImage
        OffScreenImage
    AbstractMultiResolutionImage
        ...






public abstract class Graphics {
    public abstract Graphics create();
    public Graphics create(int x, int y, int width, int height) {
    public abstract void translate(int x, int y);

    public abstract Color getColor();
    public abstract void setColor(Color c);
    public abstract void setPaintMode();
    public abstract void setXORMode(Color c1);

    public abstract Font getFont();
    public abstract void setFont(Font font);
    public FontMetrics getFontMetrics() {
    public abstract FontMetrics getFontMetrics(Font f);

    public abstract Rectangle getClipBounds();
    public abstract void clipRect(int x, int y, int width, int height);
    public abstract void setClip(int x, int y, int width, int height);
    public abstract Shape getClip();
    public abstract void setClip(Shape clip);
    public abstract void copyArea(int x, int y, int width, int height, int dx, int dy);
    public abstract void drawLine(int x1, int y1, int x2, int y2);
    public abstract void fillRect(int x, int y, int width, int height);
    public void drawRect(int x, int y, int width, int height) {
    public abstract void clearRect(int x, int y, int width, int height);
    public abstract void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight);
    public abstract void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight);
    public void draw3DRect(int x, int y, int width, int height, boolean raised) {
    public void fill3DRect(int x, int y, int width, int height, boolean raised) {
    public abstract void drawOval(int x, int y, int width, int height);
    public abstract void fillOval(int x, int y, int width, int height);
    public abstract void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle);
    public abstract void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle);
    public abstract void drawPolyline(int[] xPoints, int[] yPoints, int nPoints);
    public abstract void drawPolygon(int[] xPoints, int[] yPoints, int nPoints);
    public void drawPolygon(Polygon p) {
    public abstract void fillPolygon(int[] xPoints, int[] yPoints, int nPoints);
    public void fillPolygon(Polygon p) {

    }
    public abstract void drawString(String str, int x, int y);
    public abstract void drawString(AttributedCharacterIterator iterator, int x, int y);

    public void drawChars(char[] data, int offset, int length, int x, int y) {
    public void drawBytes(byte[] data, int offset, int length, int x, int y) {

    }
    public abstract boolean drawImage(Image img, int x, int y, ImageObserver observer);
    public abstract boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer);
    public abstract boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer);
    public abstract boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer);
    public abstract boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer);
    public abstract boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer);

    public Rectangle getClipRect() {
    public boolean hitClip(int x, int y, int width, int height) {
    public Rectangle getClipBounds(Rectangle r) {



public abstract class Graphics2D extends Graphics {
    public void draw3DRect(int x, int y, int width, int height, boolean raised) {
    public void fill3DRect(int x, int y, int width, int height, boolean raised) {
    public abstract void draw(Shape s);

    public abstract boolean drawImage(Image img, AffineTransform xform, ImageObserver obs);
    public abstract void drawImage(BufferedImage img, BufferedImageOp op, int x, int y);
    public abstract void drawRenderedImage(RenderedImage img, AffineTransform xform);
    public abstract void drawRenderableImage(RenderableImage img, AffineTransform xform);

    public abstract void drawString(String str, int x, int y);
    public abstract void drawString(String str, float x, float y);
    public abstract void drawString(AttributedCharacterIterator iterator, int x, int y);
    public abstract void drawString(AttributedCharacterIterator iterator, float x, float y);

    public abstract void drawGlyphVector(GlyphVector g, float x, float y);
    public abstract void fill(Shape s);
    public abstract boolean hit(Rectangle rect, Shape s, boolean onStroke);
    public abstract GraphicsConfiguration getDeviceConfiguration();
    public abstract void setComposite(Composite comp);
    public abstract void setPaint( Paint paint );
    public abstract void setStroke(Stroke s);
    public abstract void setRenderingHint(Key hintKey, Object hintValue);
    public abstract Object getRenderingHint(Key hintKey);
    public abstract void setRenderingHints(Map<?,?> hints);
    public abstract void addRenderingHints(Map<?,?> hints);
    public abstract RenderingHints getRenderingHints();
    public abstract void translate(int x, int y);
    public abstract void translate(double tx, double ty);
    public abstract void rotate(double theta);
    public abstract void rotate(double theta, double x, double y);
    public abstract void scale(double sx, double sy);
    public abstract void shear(double shx, double shy);
    public abstract void transform(AffineTransform Tx);
    public abstract void setTransform(AffineTransform Tx);
    public abstract AffineTransform getTransform();
    public abstract Paint getPaint();
    public abstract Composite getComposite();
    public abstract void setBackground(Color color);
    public abstract Color getBackground();
    public abstract Stroke getStroke();
    public abstract void clip(Shape s);
    public abstract FontRenderContext getFontRenderContext();
