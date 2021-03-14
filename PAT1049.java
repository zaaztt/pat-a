import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PAT1049 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int left = 0; int now = 0; int right = 0; int a = 1; int count = 0;
        while (n / a != 0) {
            left = n / a / 10;
            right = n % a;
            now = n / a % 10;
            if (now == 0) count += left * a;
            if (now == 1) count += 1 + right + left * a;
            if (now >= 2) count += a + left * a;
            a *= 10;
        }
        System.out.println(count);
    }
}
