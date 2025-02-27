package tech.tablesaw.plotly.traces;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Map;

import tech.tablesaw.plotly.Utils;
import tech.tablesaw.plotly.components.Domain;

public class PieTrace extends AbstractTrace {

  private final double[] values;
  private final Object[] labels;
  private final Domain domain;

  private PieTrace(PieBuilder builder) {
    super(builder);
    this.values = builder.values;
    this.labels = builder.labels;
    this.domain = builder.domain;
  }

  public String asJavascript(int i) {
    Writer writer = new StringWriter();
    PebbleTemplate compiledTemplate;

    try {
      compiledTemplate = engine.getTemplate("pie_trace_template.html");
      compiledTemplate.evaluate(writer, getContext(i));
    } catch (PebbleException e) {
      throw new IllegalStateException(e);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
    return writer.toString();
  }

  private Map<String, Object> getContext(int i) {

    Map<String, Object> context = super.getContext();
    context.put("variableName", "trace" + i);
    context.put("values", Arrays.toString(values));
    if (labels != null) {
      context.put("labels", Arrays.toString(Utils.dataAsArrayofStrings(labels)));
    }
    if (domain != null) {
      context.put("domain", domain.asJSON());
    }
    return context;
  }

  public static PieBuilder builder(Object[] labels, double[] values) {
    return new PieBuilder(labels, values);
  }

  public static class PieBuilder extends TraceBuilder {

    private final String type = "pie";
    private final double[] values;
    private final Object[] labels;
    private Domain domain;

    private PieBuilder(Object[] labels, double[] values) {
      this.labels = labels;
      this.values = values;
    }

    public PieBuilder domain(Domain domain) {
      this.domain = domain;
      return this;
    }

    public PieTrace build() {
      return new PieTrace(this);
    }

    @Override
    protected String getType() {
      return type;
    }

    @Override
    public PieBuilder showLegend(boolean b) {
      super.showLegend(b);
      return this;
    }
  }
}
