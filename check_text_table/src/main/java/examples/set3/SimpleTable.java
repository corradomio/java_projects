package examples.set3;

import wagu.Board;
import wagu.Table;

import java.util.Arrays;
import java.util.List;

//
// https://github.com/thedathoudarya/WAGU-data-in-table-view
//

public class SimpleTable {

    public static void main(String[] args) {
        List<String> headersList = Arrays.asList("NAME", "GENDER", "MARRIED", "AGE", "SALARY($)");
        List<List<String>> rowsList = Arrays.asList(
            Arrays.asList("Eddy", "Male", "No", "23", "1200.27"),
            Arrays.asList("Libby", "Male", "No", "17", "800.50"),
            Arrays.asList("Rea", "Female", "No", "30", "10000.00"),
            Arrays.asList("Deandre", "Female", "No", "19", "18000.50"),
            Arrays.asList("Alice", "Male", "Yes", "29", "580.40"),
            Arrays.asList("Alyse", "Female", "No", "26", "7000.89"),
            Arrays.asList("Venessa", "Female", "No", "22", "100700.50")
        );
        //bookmark 1
        Board board = new Board(75);
        String tableString = board.setInitialBlock(new Table(board, 75, headersList, rowsList).tableToBlocks()).build().getPreview();
        System.out.println(tableString);
    }
}
