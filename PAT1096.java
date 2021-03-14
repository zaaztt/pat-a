import java.util.Scanner;

public class PAT1096 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 0;
        int temp = 1;
        int max_start = 0;
        int max_end = 0;
        int start = 0;
        int end = 0;
        for (int i = 2; i < Math.sqrt(n) + 1; i++) {
            temp = temp * i;
            if (n % temp == 0) {
                if (start == 0) {
                    start = i;
                }
                end = i;
                int count = end - start + 1;
                if (count > sum) {
                    max_start = start;
                    max_end = end;
                    sum = count;
                }
            } else {
                temp = 1;
                start = 0;
                end = 0;
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
