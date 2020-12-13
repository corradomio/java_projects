class C {
    public void m() { }
    public void p() {}
}



public class Main {

    public static void main(String[] args) {
        int n = 0;

        C c = new C();

        c.m();
        c.m();
        c.m();
        c.m();
        c.m();
        c.m();
        c.m();
        c.m();
        c.m();
        c.m();
        c.m();
        c.m();
        c.m();
        c.m();
        c.m();
        c.m();
        c.m();
        c.m();
        c.m();

        c.m(); // 1                     // 1
        c.m(); // 2                     //
        for (int i=0; i<n; ++i) {       //
            c.m();      // 3            //
        }
        c.m(   );  // 4                 //

        c.p();                          // 2
        c.m();                          // 3<<<   ? or skip ?


    }
}
