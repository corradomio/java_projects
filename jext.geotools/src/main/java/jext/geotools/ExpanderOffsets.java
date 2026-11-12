package jext.geotools;

public class ExpanderOffsets {
    private double defaultOffset = 2.0;
    private double[][] offsets;

    public ExpanderOffsets() {
        this(0);
    }

    public ExpanderOffsets(double offset) {
        this(new double[][] {
            {1.e38, offset}
        });
    }

    public ExpanderOffsets(double... offsets) {
        int n = offsets.length/2;
        this.offsets = new double[n][2];
        for (int i = 0; i < n; i++) {
            this.offsets[i][0] = offsets[i*2  ];
            this.offsets[i][1] = offsets[i*2+1];
        }
    }

    public ExpanderOffsets(double[][] offsets) {
        this.offsets = offsets;
    }

    public ExpanderOffsets initialize() {
        offsets = new double[][] {
            {10,1},
            {20,2},
            {30,3},
            {40,3},
        };
        return this;
    }

    public double get() {
        return defaultOffset;
    }

    public double get(double length) {
        int l = offsets.length - 1;
        if (length <= offsets[0][0])
            return offsets[0][1];

        for (int i = 0; i < l; ++i) {
            double l1 = offsets[i][0];
            double l2 = offsets[i+1][0];
            if (length < (l1 + (l2-l1)/2))
                return offsets[i][1];
            if (length < l2)
                return offsets[i+1][1];
        }
        return offsets[l][1];
    }
}
