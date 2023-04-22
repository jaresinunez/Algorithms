package Assignment3;

import java.util.ArrayList;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;

public class CutRodProblem {
    public static void main(String[] args) {

    }

    // Recursive top-down implementation
    public static int cutRod(ArrayList<Integer> p, int n){
        if (n == 0){
            return 0;
        }

        int q = MIN_VALUE;
        for (int i = 1; i <= n; i++){
            q = max(q, p.get(i) + cutRod(p, n - i));
        }
        return q;
    }

    // Algorithm 2: recursive top-down implementation with memorization

    public static int memorizedCutRod(ArrayList<Integer> p, int n){
        ArrayList<Integer> r = new ArrayList<>();
        for (int i = 0; i <= n; i++) { r.add(i); }
        for (int i = 0; i <= n; i++){
            r.set(i, MIN_VALUE);
        }
        return memorizedCutRodAux(p, n, r);
    }

    private static int memorizedCutRodAux(ArrayList<Integer> p, int n, ArrayList<Integer> r) {
        if (r.get(n) >= 0){
            return r.get(n);
        }
        int q;
        if (n == 0)
            q = 0;
        else {
            q = MIN_VALUE;
            for (int i = 1; i <= n; i++){
                q = max(q, p.get(i) + memorizedCutRodAux(p, n - i, r));
            }
        }
        r.set(n, q);
        return q;
    }

    // Algorithm 3: Recursive bottom-up implementation with memorization
    public static int extendedBottomUpCutRod(ArrayList<Integer> p, int n){
        int q;
        ArrayList<Integer> r = new ArrayList<>();
        for (int i = 0; i <= n; i++) { r.add(i); }
        ArrayList<Integer> s = new ArrayList<>();
        for (int i = 1; i <= n; i++) { r.add(i); }
        r.set(0, 0);
        for(int j = 1; j <= n; j++){
            q = MIN_VALUE;
            for (int i = 1; i <= j; i++){
                if (q < p.get(i) + r.get(j - i)){
                    q = p.get(i) + r.get(j - i);
                    s.set(j, i);
                }
            }
            r.set(j, q);
        }
        return -2;
    }
}
