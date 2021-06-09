package org.hls.check;

public class App2 {

    public static void main(String[] args) {
        try {
            System.out.println("before");
            throw new RuntimeException("except");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            System.out.println("finally");
        }
    }
}
