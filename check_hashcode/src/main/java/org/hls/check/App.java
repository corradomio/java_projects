package org.hls.check;

public class App {

    public static void main(String[] args) {
        String s1 = "1";
        String s2 = "2";

        int i = 0;
        String digits = "01234567890";
        while (s1.hashCode() != s2.hashCode()) {
            s1 = s1 + digits.substring(i, i+1);
            s2 = s2 + digits.substring(i, i+1);
            i = (i+1) % 10;
            if (s1.length() > 128) break;
        }

        System.out.println(s1);
        System.out.println(s2);
    }
}
