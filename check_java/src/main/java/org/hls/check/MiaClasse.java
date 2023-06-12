package org.hls.check;

public class MiaClasse {
    int n;
    int[] a;

    public boolean metB(MiaClasse m){
        return n==m.n;
    }

    public MiaClasse(){
        n=0;
        a=new int[10];
    }

    public int metA(int i){
        int r=-1;
        if(i>=0&&i<=a.length)
            r=a[i];
        return r;
    }
}
