import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Account {
    int code;

    Account(int c) {
        code = c;
    }

    int getCode() {
        return code;
    }
}



public class Main {

    public static <T> Predicate<T> distinctByKey(
        Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static void main(String[] args) {
        List<Account> myList = Arrays.asList(new Account(1), new Account(3), new Account(2), new Account(3), new Account(1));
        List<Account> response = myList.stream().filter(distinctByKey(Account::getCode)).collect(Collectors.toList());
        System.out.println(response);
    }
}
