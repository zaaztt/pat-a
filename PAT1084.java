import java.util.HashSet;
import java.util.Scanner;

public class PAT1084 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String line1 = sc.next();
        String line2 = sc.next();
        char[] c1 = line1.toCharArray();
        char[] c2 = line2.toCharArray();
        HashSet<Character> set = new HashSet<>();
        StringBuilder strb = new StringBuilder();
        int cnt1 = 0;
        int cnt2 = 0;
        while (cnt1 < c1.length || cnt2 < c2.length) {
            while (cnt1 < c1.length && cnt2 < c2.length && c1[cnt1] == c2[cnt2]) {
                cnt1++;
                cnt2++;
            }
            if (cnt1 == c1.length) {
                break;
            }
            char err = exchange(c1[cnt1]);
            if (!set.contains(err)) {
                set.add(err);
                strb.append(err);
            }
            cnt1++;
        }
        if (cnt1 != c1.length) {
            for (int i = cnt1; i < c1.length; i++) {
                char err = exchange(c1[i]);
                if (!set.contains(err)) {
                    set.contains(err);
                    strb.append(err);
                }
            }
        }
        System.out.println(strb);
    }

    private static char exchange (char c) {
        if (c >= '0' && c <= '9') {
            return c;
        } else if (c >= 'a' && c <= 'z') {
            return (char) (c - 'a' + 'A');
        } else if (c >= 'A' && c <= 'Z') {
            return c;
        } else {
            return c;
        }
    }
}
