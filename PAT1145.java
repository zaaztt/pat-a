import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PAT1145 {
    static int count = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int MSize = sc.nextInt();
        int N = sc.nextInt();
        int M = sc.nextInt();
        MSize = findNearestPrime(MSize);
        int[] a = new int[MSize];
        Arrays.fill(a, -1);
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }
        int[] q = new int[M];
        for (int i = 0; i < M; i++) {
            q[i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            int n = nums[i];
            insert(a, n, MSize);
        }
        for (int i = 0; i < M; i++) {
            findX(a, q[i], MSize);
        }

        System.out.println(String.format("%.1f", ((double)count / M)));

    }

    private static void insert(int[] a, int target, int MSize) {
        int temp = 0;
        while (temp <= MSize) {
            int cur_loc = (target + temp * temp) % MSize;
            if (a[cur_loc] == -1) {
                a[cur_loc] = target;
                break;
            } else {
                temp++;
            }
            if (temp > MSize) {
                System.out.println(target + " cannot be inserted.");
            }
        }
    }

    private static void findX (int[] a, int target, int MSize) {

        for (int i = 0; i <= MSize; i++) {
            count++;
            int cur_loc = (target + i * i) % MSize;
            if (a[cur_loc] == target) {
                return;
            }
            if (a[cur_loc] == -1) {
                return;
            }
        }
    }

    private static int findNearestPrime (int n) {
        boolean[] isPrime = new boolean[10011];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        for (int i = 2; i <= 10010; i++) {
            if (isPrime[i] == true) {
                for (int j = i + i; j <= i * i && j <= 10010; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        for (int i = n; i <= 10010; i++) {
            if (isPrime[i]) {
                return i;
            }
        }
        return -1;
    }
}
