import java.util.Scanner;

public class PAT1082 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        if (s.charAt(0) == '-') {
            System.out.print("Fu ");
            s = s.substring(1);
        }
        if (s.charAt(0) == '0') {
            System.out.println("ling");
            return;
        }
        int[] a;

        String[] ss= new String[s.length()];
        boolean flag0 = false;
        boolean flag1 = false;
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int n = s.charAt(i) - '0';
            if (n == 0) {
                flag0 = true;
                flag1 = false;
                if (s.length() - 1 - i == 8) {
                    strb.append("Yi ");
                } else if (s.length() - 1 - i == 4) {
                    boolean f1 = true;
                    boolean f2 = true;
                    boolean f3 = true;
                    if (i - 1 >= 0 && s.charAt(i - 1) != '0') {
                        f1 = false;
                    }
                    if (i - 2 >= 0 && s.charAt(i - 2) != '0') {
                        f2 = false;
                    }
                    if (i - 3 >= 0 && s.charAt(i - 3) != '0') {
                        f3 = false;
                    }
                    if (f1 && f2 && f3) {
                        continue;
                    } else {
                        strb.append("Wan ");
                    }
                }
                continue;
            } else {
                flag1 = true;
            }
            if (flag0 && flag1) {
                flag0 = false;
                flag1 = false;
                strb.append(numToC(0));
            }
            strb.append(numToC(n));
            if ((s.length() - 1 - i) % 4 != 0) {
                strb.append(loctoC((s.length() - 1 - i) % 4));
            }
            if (s.length() - 1 - i == 8) {
                strb.append("Yi ");
            } else if (s.length() - 1 - i == 4) {
                strb.append("Wan ");
            }
        }
        strb.deleteCharAt(strb.length() - 1);
        System.out.println(strb);
    }


    private static String loctoC (int loc) {
        if (loc == 3) {
            return "Qian ";
        }
        if (loc == 2) {
            return "Bai ";
        }
        if (loc == 1) {
            return "Shi ";
        }
        return null;
    }

    private static String numToC (int i) {
        if (i == 1) {
            return "yi ";
        }
        if (i == 2) {
            return "er ";
        }
        if (i == 3) {
            return "san ";
        }
        if (i == 4) {
            return "si ";
        }
        if (i == 5) {
            return "wu ";
        }
        if (i == 6) {
            return "liu ";
        }
        if (i == 7) {
            return "qi ";
        }
        if (i == 8) {
            return "ba ";
        }
        if (i == 9) {
            return "jiu ";
        }
        if (i == 0) {
            return "ling ";
        }
        return null;
    }
}
