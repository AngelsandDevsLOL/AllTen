import java.util.*;

public class AllTen {
    public static String[] arr = new String[10];
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> ar = new ArrayList<>();
        ArrayList<Boolean> orig = new ArrayList<>();
        ArrayList<String> path = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            ar.add((double) sc.nextInt());
        }
        
        orig.add(true); orig.add(true); orig.add(true); orig.add(true);
        for (double x : ar) {
            path.add(String.valueOf(x));
        }
        num(ar, orig, path);
        for (int i = 0; i < 10; i++) {
            System.out.println((i + 1) + ": " + arr[i]);
        }
    }
    public static void num (ArrayList<Double> ar, ArrayList<Boolean> orig, ArrayList<String> path) {
        if (ar.size() == 1) {
            if (ar.get(0) <= 10 && ar.get(0) >= 1 && (ar.get(0) == (int)((double)ar.get(0))) && arr[(int) (ar.get(0) - 1)] == null) {
                arr[(int) (ar.get(0) - 1)] = path.get(0);
            }
            return;
        }
        for (int i = 0; i < ar.size(); i++) {
            for (int j = i + 1; j < ar.size(); j++) {
                double x = ar.remove(j);
                double y = ar.remove(i);
                boolean origx = orig.remove(j);
                boolean origy = orig.remove(i);
                String pathx = path.remove(j);
                String pathy = path.remove(i);
                if (origx) {
                    pathx = String.valueOf((int)Double.parseDouble(pathx));
                }
                if (origy) {
                    pathy = String.valueOf((int)Double.parseDouble(pathy));
                }
                ar.add(x + y);
                orig.add(false);
                path.add("(" + pathx + " + " + pathy + ")");
                num( ar, orig, path);
                ar.remove(ar.size()-1);
                orig.remove(orig.size() - 1);
                path.remove(path.size() - 1);

                ar.add(x - y);
                orig.add(false);
                path.add("(" + pathx + " - " + pathy + ")");
                num( ar, orig, path);
                ar.remove(ar.size()-1);
                orig.remove(orig.size() - 1);
                path.remove(path.size() - 1);

                ar.add(y - x);
                orig.add(false);
                path.add("(" + pathy + " - " + pathx + ")");
                num( ar, orig, path);
                ar.remove(ar.size()-1);
                orig.remove(orig.size() - 1);
                path.remove(path.size() - 1);

                if (origx && origy) {
                    ar.add(x * 10 + y);
                    orig.add(false);
                    path.add(pathx + pathy);
                    num(ar, orig, path);
                    ar.remove(ar.size() - 1);
                    orig.remove(orig.size() - 1);
                    path.remove(path.size() - 1);

                    orig.add(false);
                    ar.add(y * 10 + x);
                    path.add(pathy + pathx);
                    num(ar, orig, path);
                    ar.remove(ar.size() - 1);
                    orig.remove(orig.size() - 1);
                    path.remove(path.size() - 1);
                }

                ar.add(x * y);
                orig.add(false);
                path.add("(" + pathx + " * " + pathy + ")");
                num( ar, orig, path);
                ar.remove(ar.size()-1);
                orig.remove(orig.size() - 1);
                path.remove(path.size() - 1);

                if (y != 0) {
                    ar.add(x / y);
                    orig.add(false);
                    path.add("(" + pathx + " / " + pathy + ")");
                    num(ar, orig, path);
                    ar.remove(ar.size() - 1);
                    orig.remove(orig.size() - 1);
                    path.remove(path.size() - 1);
                }
                if (x != 0) {
                    ar.add(y / x);
                    orig.add(false);
                    path.add("(" + pathy + " / " + pathx + ")");
                    num(ar, orig, path);
                    ar.remove(ar.size() - 1);
                    orig.remove(orig.size() - 1);
                    path.remove(path.size() - 1);
                }
                ar.add(i, y);
                ar.add(j, x);
                orig.add(i, origy);
                orig.add(j, origx);
                path.add(i, pathy);
                path.add(j, pathx);
            }
        }
    }
}
