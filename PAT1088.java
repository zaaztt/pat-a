import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PAT1088 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        Num[] nums = new Num[2];
        for (int i = 0; i < 2; i++) {
            String num = sc.next();
            Num n = new Num();
            nums[i] = n;
            if (num.charAt(0) == '-') {
                n.pos = -1;
                num = num.substring(1);
            } else {
                n.pos = 1;
            }
            String[] spl = num.split("/");
            n.numerator = Integer.parseInt(spl[0]);
            n.dominator = Integer.parseInt(spl[1]);
        }
        Num add_ans = add(nums[0], nums[1]);
        Num minus_ans = minus(nums[0], nums[1]);
        Num multiple_ans = multiple(nums[0], nums[1]);
        Num divide_ans = divide(nums[0], nums[1]);
        char[] ca = new char[4];
        ca[0] = '+';
        ca[1] = '-';
        ca[2] = '*';
        ca[3] = '/';
        simplify(add_ans);simplify(minus_ans);simplify(multiple_ans);simplify(divide_ans);
        simplify(nums[0]);simplify(nums[1]);
        String num1 = exchange(nums[0]);
        String num2 = exchange(nums[1]);
        String add_s = exchange(add_ans);
        String minus_s = exchange(minus_ans);
        String multi_s = exchange(multiple_ans);
        String divide_s = exchange(divide_ans);
        List<String> list = new ArrayList<>();
        list.add(add_s);list.add(minus_s);list.add(multi_s);list.add(divide_s);
        for (int i = 0; i < 4; i++) {
            System.out.println(num1 + " " + ca[i] + " " + num2 + " = " + list.get(i));
        }
    }

    private static Num add(Num n1, Num n2) {
        Num ret = new Num();
        if (n1.numerator == 0) {
            return n2;
        }
        if (n2.numerator == 0) {
            return n1;
        }
        int numer1 = n1.numerator;
        int numer2 = n2.numerator;
        int dom1 = n1.dominator;
        int dom2 = n2.dominator;
        int min_multi = min_mul(dom1, dom2);
        int new_numer = numer1 * n1.pos * min_multi / dom1 + numer2 * n2.pos * min_multi / dom2;
        if (new_numer == 0) {
            ret.numerator = 0;
            ret.dominator = 1;
            ret.front = 0;
            return ret;
        } else {
            if (new_numer < 0) {
                ret.pos = -1;
                new_numer = Math.abs(new_numer);
            } else {
                ret.pos = 1;
            }
            ret.numerator = new_numer;
            ret.dominator = min_multi;
            return ret;
        }
    }

    private static String exchange (Num n1) {
        StringBuilder strb = new StringBuilder();
        if (n1.dominator == 0) {
            return "Inf";
        } else if (n1.numerator == 0 && n1.front == 0) {
            return "0";
        } else if (n1.numerator == 0 && n1.front != 0) {
            if (n1.pos == -1) {
                return "(-" + String.valueOf(n1.front) + ")";
            } else {
                return String.valueOf(n1.front);
            }
        } else {
            if (n1.pos == -1) {
                strb.append("(-");
            }
            if (n1.front != 0) {
                strb.append(n1.front + " ");
            }
            strb.append(n1.numerator + "/" + n1.dominator);
            if (n1.pos == -1) {
                strb.append(")");
            }
            return strb.toString();
        }
    }

    private static Num minus(Num n1, Num n2) {
        Num rev_n2 = new Num();
        rev_n2.dominator = n2.dominator;
        rev_n2.numerator = n2.numerator;
        rev_n2.front = n2.front;
        rev_n2.pos  = n2.pos * -1;
        return add(n1, rev_n2);
    }

    private static Num multiple(Num n1, Num n2) {
        Num ret = new Num();
        ret.pos = n1.pos * n2.pos;
        if (n1.numerator == 0 || n2.numerator == 0) {
            ret.front = 0;
            ret.numerator = 0;
            ret.dominator = 1;
            return ret;
        } else {
            int numer1 = n1.numerator;
            int numer2 = n2.numerator;
            int dom1 = n1.dominator;
            int dom2 = n2.dominator;
            ret.numerator = numer1 * numer2;
            ret.dominator = dom1 * dom2;
            return ret;
        }
    }

    private static Num divide(Num n1, Num n2) {
        Num ret = new Num();
        ret.pos = n1.pos / n2.pos;
        if (n2.numerator == 0) {
            ret.numerator = 1;
            ret.dominator = 0;
            return ret;
        }
        if (n1.numerator == 0) {
            ret.numerator = 0;
            ret.dominator = 1;
            return ret;
        }

        int numer1 = n1.numerator;
        int numer2 = n2.numerator;
        int dom1 = n1.dominator;
        int dom2 = n2.dominator;
        ret.numerator = numer1 * dom2;
        ret.dominator = dom1 * numer2;
        return ret;
    }

    private static void simplify(Num n) {
        if (n.numerator == 0) {
            n.front = 0;
        }  else if (n.dominator == 0) {
            n.front = 0;
            n.numerator = 1;
        } else if (n.numerator % n.dominator == 0) {
            n.front = n.numerator / n.dominator;
            n.numerator = 0;
        } else if (n.numerator > n.dominator) {
            n.front = n.numerator / n.dominator;
            n.numerator = n.numerator % n.dominator;
        }

        if (n.numerator != 0) {
            int com = max_common(n.dominator, n.numerator);
            n.numerator = n.numerator / com;
            n.dominator = n.dominator / com;
        }

    }

    private static int min_mul (int a, int b) {
        int com = max_common(a, b);
        return a * b / com;
    }

    private static int max_common (int a, int b) {
        if (b > a) {
            int temp = b;
            b = a;
            a = temp;
        }
        int c;
        while (b != 0) {
            c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

    private static class Num {
        int pos;
        int numerator;
        int dominator;
        int front;
    }
}
