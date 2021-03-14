import java.util.*;

public class PAT1027 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int G = sc.nextInt();
        int B = sc.nextInt();
        String s = "#";
        s = s + convertToRadix13(R) + convertToRadix13(G) + convertToRadix13(B);
        System.out.println(s);
    }

    private static String convertToRadix13 (int i) {
        int i1 = i / 13;
        int i2 = i % 13;
        String s = convertSingleToRadix13(i1) + convertSingleToRadix13(i2);
        return s;
    }

    private static String convertSingleToRadix13 (int i) {
        if (i == 10) {
            return "A";
        }
        if (i == 11) {
            return "B";
        }
        if (i == 12) {
            return "C";
        }
        return String.valueOf(i);
    }

}
