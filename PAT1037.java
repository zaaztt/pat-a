import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PAT1037 {
    static int maximum = 0;
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n1 = Integer.parseInt(br.readLine());
        int[] coupons = new int[n1];
        String[] spl1 = br.readLine().split(" ");
        for (int i = 0; i < n1; i++) {
            coupons[i] = Integer.parseInt(spl1[i]);
        }
        int n2 = Integer.parseInt(br.readLine());
        Integer[] products = new Integer[n2];
        String[] spl2 = br.readLine().split(" ");
        for (int i = 0; i < n2; i++) {
            products[i] = Integer.parseInt(spl2[i]);
        }
        Arrays.sort(coupons);
        Arrays.sort(products);

        dfs (coupons, products, 0, 0);
        System.out.println(maximum);
    }

    private static void dfs (int[] coupons, Integer[] products, int start, int sum) {
        if (sum > maximum) {
            maximum = sum;
        }
        if (start == coupons.length) return;
        dfs (coupons, products, start + 1, sum);

        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                int temp = products[i];
                if (coupons[start] * products[i] <= 0) continue;
                sum += coupons[start] * products[i];
                products[i] = null;
                dfs(coupons, products, start + 1, sum);
                sum -= temp * coupons[start];
                products[i] = temp;
            }
        }
    }
}
