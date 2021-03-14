import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PAT1079 {
    public static void main (String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        double unit_price = sc.nextDouble();
        double r = sc.nextDouble();
        int[] a = new int[N];
        a[0] = 0;
        List<Retailer> retailers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int n = sc.nextInt();
            if (n == 0) {
                retailers.add(new Retailer(sc.nextInt(), i));
            } else {
                for (int j = 0; j < n; j++) {
                    a[sc.nextInt()] = i;
                }
            }
        }
        double sum = 0;
        for (Retailer retailer : retailers) {
            int level = 0;
            int temp = retailer.id;
            while (a[temp] != temp) {
                level++;
                temp = a[temp];
            }
            sum += retailer.m * unit_price * Math.pow(1 + r / 100, level);
        }
        System.out.println(String.format("%.1f", sum));
    }

    private static class Retailer {
        int m;
        int id;
        Retailer(int m, int id) {
            this.m = m;
            this.id = id;
        }
    }
}
