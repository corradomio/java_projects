package check;

import java.util.ArrayList;
import java.util.List;

public class CheckInsert {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<10; ++i) {
            list.add(i);
        }

        System.out.println(list.toString());
        list.remove(5);
        System.out.println(list.toString());
        list.add(5, Integer.valueOf(55));
        System.out.println(list.toString());
    }
}
