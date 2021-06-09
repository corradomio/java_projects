package jext.main;

import jext.math.linear.Dim;
import jext.math.linear.Matrices;
import jext.math.linear.sparse.Loc;
import jext.math.linear.sparse.SparseMatrix;

public class App6 {

    public static void main(String[] args) {

        SparseMatrix m = (SparseMatrix)new SparseMatrix(new Dim(4,4))
            .set(0,0,0)
            .set(1, 1,1)
            .set(2,2,2)
            .set(3,3,3);

        // SparseMatrix m = Matrices.sparse(4,4);
        //
        // m.set(0, 1, 1);
        // m.set(0, 2, 11);
        // m.set(1, 2, 2);
        // m.set(1, 3, 22);
        // m.set(2,3, 3);
        // m.set(3,0, 4);

        Matrices.print(m);

        for(Loc l : m)
            System.out.printf("%d, %d\n", l.r, l.c);

        for(int row=0; row<4; ++row)
            for(Loc l : m.cols(row))
                System.out.printf("%d: %d, %d\n", row, l.r, l.c);

        for(int col=0; col<4; ++col)
            for(Loc l : m.rows(col))
                System.out.printf("%d: %d, %d\n", col, l.r, l.c);

        //
        // for(Loc r : m.rows()) {
        //     System.out.println(r.loc);
        // }
        //
        // for(Loc c : m.cols()) {
        //     System.out.println(c.loc);
        // }

        // Matrix r = m.dot(m);
        //
        // Matrices.print(r);
    }
}
