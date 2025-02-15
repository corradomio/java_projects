package jext.problems.vrp;

public class VRPUtils {

    private static int sum(int[] arr) {
        int s = 0;
        for (int e : arr)
            s += e;
        return s;
    }

    //
    // create the vector where for each vehicle it is specified the deposit
    //
    public static int[] composeVehiclesDeposit(int[] deposits, int[] dvehicles) {
        int n = deposits.length;
        int m = sum(dvehicles);
        int[] dv = new int[m];

        for (int i=0,k=0; i<n; ++i) {
            int nvehicles = dvehicles[i];
            for (int j=0; j<nvehicles; ++j, ++k)
                dv[k] = deposits[i];
        }
        return dv;
    }
}
