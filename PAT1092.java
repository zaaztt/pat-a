import java.util.Scanner;

public class PAT1092 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String ori = sc.next();
        String want = sc.next();
        int[] oris = new int[128];
        int[] wants = new int[128];
        for (int i = 0; i < ori.length(); i++) {
            oris[ori.charAt(i)]++;
        }
        for (int i = 0; i < want.length(); i++) {
            wants[want.charAt(i)]++;
        }
        boolean flag = true;
        int count = 0;
        for (int i = 0; i < 128; i++) {
            if (wants[i] == 0) {
                continue;
            } else {
                if (oris[i] >= wants[i]) {
                    continue;
                } else {
                    flag = false;
                    count += (wants[i] - oris[i]);
                }
            }

        }
        if (flag) {
            System.out.println("Yes " + (ori.length() - want.length()));
        } else {
            System.out.println("No " + count);
        }
    }
}
