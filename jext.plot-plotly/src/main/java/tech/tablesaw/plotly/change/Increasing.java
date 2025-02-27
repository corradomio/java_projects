package tech.tablesaw.plotly.change;

public class Increasing extends Change {

  private Increasing(IncreasingBuilder builder) {
    super(builder);
  }

  public static IncreasingBuilder builder() {
    return new IncreasingBuilder();
  }

  public static class IncreasingBuilder extends ChangeBuilder {

    @Override
    public IncreasingBuilder fillColor(String color) {
      this.fillColor = color;
      return this;
    }

    @Override
    public IncreasingBuilder changeLine(ChangeLine line) {
      this.changeLine = line;
      return this;
    }

    public Increasing build() {
      return new Increasing(this);
    }
  }
}
