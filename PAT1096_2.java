import java.util.Scanner;
import java.util.Set;

public class PAT1096_2 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 0;
        int temp = 1;
        int max_start = 0;
        int max_end = 0;
        int start = 0;
        int end = 0;
        for (int i = 2; i <= Math.sqrt(n) + 1; i++) {
            int j;
            if (n % i == 0) {
                int multi = 1;
                for (j = i; j <= Math.sqrt(n) + 1; j++) {
                    multi *= j;
                    if (n % multi == 0) {
                        continue;
                    } else {
                        break;
                    }
                }
                int count = j - i;
                if (count > sum) {
                    sum = count;
                    max_start = i;
                    max_end = j - 1;
                }
            }

        }
        StringBuilder strb = new StringBuilder();
        if (max_start == 0) {
            strb.append(n);
            sum = 1;
        } else {
            for (int i = max_start; i <= max_end; i++) {
                strb.append(i).append('*');
            }
            strb.deleteCharAt(strb.length() - 1);
        }
        System.out.println(sum);
        System.out.println(strb);
    }
}
