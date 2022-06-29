package app;

class Base<T> {

     public class Inner<K> {

         T m(K p) { return null; }
     }
}

public class App {

    public static void main(String[] args) {
        Base<Integer>.Inner<String> bi = new Base<Integer>.Inner<String>();
    }
}
