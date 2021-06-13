package a.b.c.d;

class Processor {
    public enum Arch {
        BIT_32("32-bit");
        private final String label;
        Arch(final String label) {
            this.label = label;
        }
        public String getLabel() { return label; }
    }
    enum Type {
        X86,
        UNKNOWN
    }
    public Processor(final Processor.Arch arch, final Processor.Type type) {}
}

public class TestHLS7 {

    private static void init_X86_32Bit() {
        final Processor processor = new Processor(Processor.Arch.BIT_32, Processor.Type.X86);
    }

    public static void main(String[] args) {

    }
}
