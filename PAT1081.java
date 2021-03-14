import java.util.Arrays;
import java.util.Scanner;

public class PAT1081 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Num[] nums = new Num[N];
        for (int i = 0; i < N; i++) {
            String s = sc.next();
            String[] spl = s.split("/");
            int num = Integer.valueOf(spl[0]);
            int dom = Integer.valueOf(spl[1]);
            nums[i] = new Num(num, dom);
        }
        Num temp = add(nums[0], nums[1]);
        for (int i = 2; i < N; i++) {
            temp = add(temp, nums[i]);
        }
        if (temp.num == 0) {
            System.out.println(0);
            return;
        }
        if (temp.num < 0) {
            System.out.print("-");
            temp.num = Math.abs(temp.num);
        }
        if (temp.num > temp.dom) {
            System.out.print(temp.num / temp.dom);
            if (temp.num % temp.dom == 0) {

            } else {
                System.out.print(" ");
            }
        }

        int rem = temp.num % temp.dom;
        if (rem != 0) {
            int maxD = maxDivisor(rem, temp.dom);
            rem = rem / maxD;
            int d = temp.dom;
            d = d / maxD;
            System.out.print(rem + "/" + d);
        }

    }

    private static class Num {
        int num;
        int dom;

        Num (int num, int dom) {
            this.num = num;
            this.dom = dom;
        }
    }

    private static Num add (Num n1, Num n2) {
        if (n1.num == 0) {
            return n2;
        }
        if (n2.num == 0) {
            return n1;
        }
        int minM = minMutiple(n1.dom, n2.dom);
        int sum = n1.num * minM / n1.dom + n2.num * minM / n2.dom;
        if (sum == 0) {
            return new Num(0, 1);
        }
        int sign = 1;
        if (sum < 0) {
            sign = -1;
        }
        sum = Math.abs(sum);
        int maxD = maxDivisor(sum, minM);
        sum = sum / maxD;
        minM = minM / maxD;
        return new Num(sign * sum, minM);
    }

    private static int maxDivisor (int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        int r = 0;
        while (b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    private static int minMutiple (int a, int b) {
        int maxD = maxDivisor(a, b);
        return a * b / maxD;
    }

}
