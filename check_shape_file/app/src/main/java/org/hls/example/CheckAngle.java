package org.hls.example;

import jext.geotools.ShapeUtils;
import jext.util.StringUtils;
import org.locationtech.jts.geom.Coordinate;

public class CheckAngle {

    public static void main(String[] args) {

        Coordinate px = new Coordinate(1, 0);
        Coordinate py = new Coordinate(0, 1);
        Coordinate mx = new Coordinate(-1, 0);
        Coordinate my = new Coordinate(0, -1);
        Coordinate pp = new Coordinate(1,1);
        Coordinate mp = new Coordinate(-1,1);
        Coordinate pm = new Coordinate(1,-1);
        Coordinate mm = new Coordinate(-1,-1);

        Coordinate[] coords = new Coordinate[]{
            px,py, mx, my, pp, mp, pm, mm
        };

        int n = coords.length;

        for (int i=0; i<n; i++) {
            for(int j=0; j<n; ++j) {
                System.out.printf("%s->%s -> %.2f\n",
                    ShapeUtils.toString(coords[i]),
                    ShapeUtils.toString(coords[j]),
                    Math.toDegrees(ShapeUtils.angle(coords[i], coords[j]))
                );
            }
        }

    }

    public static void main2(String[] args) {

        System.out.printf("%.2f\n", Math.toDegrees(
            ShapeUtils.externalAngle(
                new Coordinate(-1, 0),
                new Coordinate(0,0),
                new Coordinate(-1, -1)
            ))
        );

        System.out.printf("%.2f\n", Math.toDegrees(
            ShapeUtils.externalAngle(
                new Coordinate(-1, 0),
                new Coordinate(0,0),
                new Coordinate(0, -1)
            ))
        );

        System.out.printf("%.2f\n", Math.toDegrees(
            ShapeUtils.externalAngle(
                new Coordinate(-1, 0),
                new Coordinate(0,0),
                new Coordinate(1, -1)
            ))
        );

        System.out.printf("%.2f\n", Math.toDegrees(
            ShapeUtils.externalAngle(
                new Coordinate(-1, 0),
                new Coordinate(0,0),
                new Coordinate(1, 0)
            ))
        );

        System.out.printf("%.2f\n", Math.toDegrees(
            ShapeUtils.externalAngle(
                new Coordinate(-1, 0),
                new Coordinate(0,0),
                new Coordinate(1, 1)
            ))
        );

        System.out.printf("%.2f\n", Math.toDegrees(
            ShapeUtils.externalAngle(
                new Coordinate(-1, 0),
                new Coordinate(0,0),
                new Coordinate(0, 1)
            ))
        );

        System.out.printf("%.2f\n", Math.toDegrees(
            ShapeUtils.externalAngle(
                new Coordinate(-1, 0),
                new Coordinate(0,0),
                new Coordinate(-1, 1)
            ))
        );
    }

    public static void main3(String[] args) {

        double TWO_PI = 2*Math.PI;
        double DANGLE = Math.PI/4;
        Coordinate x = new Coordinate(1, 0);

        Coordinate a = new Coordinate(0,0);

        for (double alpha = 0; alpha<TWO_PI; alpha += DANGLE) {
            System.out.println("---");
            Coordinate b = ShapeUtils.rotate(x, alpha);

            for (double beta = 0; beta < TWO_PI; beta += DANGLE) {
                Coordinate r = ShapeUtils.rotate(b, beta);
                Coordinate c = new Coordinate(b.x+r.x, b.y+r.y);

                double angle = ShapeUtils.externalAngle(a, b, c);

                System.out.printf("%.2f -> %.2f\n", Math.toDegrees(beta), Math.toDegrees(angle));
            }
        }
        System.out.println("---");
    }
}
