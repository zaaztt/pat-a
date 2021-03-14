import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PAT1100 {
    static Set<String> set = new HashSet<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.valueOf(sc.nextLine());
        String[] ss = new String[N];
        for (int i = 0; i < N; i++) {
            ss[i] = sc.nextLine();
        }
        for (int i = 1; i <= 12; i++) {
            set.add(sec_digit_exchange(i));
        }
        for (int i = 0; i < N; i++) {
            if (ss[i].charAt(0) >= '0' && ss[i].charAt(0) <= '9') {
                System.out.println(exchange(Integer.valueOf(ss[i])));
            } else {
                System.out.println(exchange(ss[i]));
            }
        }
    }

    private static int exchange(String s) {
        String[] spl = s.split(" ");
        if (spl.length == 1) {
            if (set.contains(spl[0])) {
                return 13 * sec_digit_exchange(spl[0]);
            }
            return first_digit_exchange(spl[0]);
        }
        if (spl.length == 2) {
            return sec_digit_exchange(spl[0]) * 13 + first_digit_exchange(spl[1]);
        }
        return -1;
    }

    private static String exchange(int n) {
        if (n / 13 > 0 && n % 13 == 0) {
            String s1 = sec_digit_exchange(n / 13);
            return s1;
        }
        if (n / 13 > 0) {
            String s1 = sec_digit_exchange(n / 13);
            String s2 = first_digit_exchange(n % 13);
            return s1 + " " + s2;
        } else {
            String s1 = first_digit_exchange(n);
            return s1;
        }

    }

    private static int first_digit_exchange (String s) {
        if (s.equals("jan")) return 1;
        if (s.equals("feb")) return 2;
        if (s.equals("mar")) return 3;
        if (s.equals("apr")) return 4;
        if (s.equals("may")) return 5;
        if (s.equals("jun")) return 6;
        if (s.equals("jly")) return 7;
        if (s.equals("aug")) return 8;
        if (s.equals("sep")) return 9;
        if (s.equals("oct")) return 10;
        if (s.equals("nov")) return 11;
        if (s.equals("dec")) return 12;
        if (s.equals("tret")) return 0;
        return -1;
    }

    private static int sec_digit_exchange (String s) {
        if (s.equals("tam")) return 1;
        if (s.equals("hel")) return 2;
        if (s.equals("maa")) return 3;
        if (s.equals("huh")) return 4;
        if (s.equals("tou")) return 5;
        if (s.equals("kes")) return 6;
        if (s.equals("hei")) return 7;
        if (s.equals("elo")) return 8;
        if (s.equals("syy")) return 9;
        if (s.equals("lok")) return 10;
        if (s.equals("mer")) return 11;
        if (s.equals("jou")) return 12;
        if (s.equals("tret")) return 0;
        return -1;
    }

    private static String first_digit_exchange (int n) {
        if (n == 1) return "jan";
        if (n == 2) return "feb";
        if (n == 3) return "mar";
        if (n == 4) return "apr";
        if (n == 5) return "may";
        if (n == 6) return "jun";
        if (n == 7) return "jly";
        if (n == 8) return "aug";
        if (n == 9) return "sep";
        if (n == 10) return "oct";
        if (n == 11) return "nov";
        if (n == 12) return "dec";
        if (n == 0) return "tret";
        return null;
    }

    private static String sec_digit_exchange (int n) {
        if (n == 1) return "tam";
        if (n == 2) return "hel";
        if (n == 3) return "maa";
        if (n == 4) return "huh";
        if (n == 5) return "tou";
        if (n == 6) return "kes";
        if (n == 7) return "hei";
        if (n == 8) return "elo";
        if (n == 9) return "syy";
        if (n == 10) return "lok";
        if (n == 11) return "mer";
        if (n == 12) return "jou";
        if (n == 0) return "tret";
        return null;
    }
}
