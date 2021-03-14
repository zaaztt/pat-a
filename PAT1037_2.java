import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PAT1037_2 {
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
        int neg_n_coupons = 0;
        int pos_n_coupons = 0;
        int neg_n_products = 0;
        int pos_n_products = 0;
        for (int i : coupons) {
            if (i > 0) pos_n_coupons++;
            if (i < 0) neg_n_coupons++;
        }
        for (int i : products) {
            if (i > 0) pos_n_products++;
            if (i < 0) neg_n_products++;
        }
        int neg_com = Math.min(neg_n_coupons, neg_n_products);
        int pos_com = Math.min(pos_n_coupons, pos_n_products);
        int sum = 0;
        for (int i = 0; i < neg_com; i++) {
            sum += coupons[i] * products[i];
        }
        for (int i = 0; i < pos_com; i++) {
            sum += coupons[coupons.length - i - 1] * products[products.length - i - 1];
        }

        System.out.println(sum);
    }
}
