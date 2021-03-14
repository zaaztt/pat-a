import java.util.*;

public class PAT1069 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        String s = null;
        while (!n.equals("0000") && !n.equals("6174")) {
            n = blackhole(n);
        }
    }

    private static String blackhole (String n) {
        String s1;
        String s2;
        StringBuilder strb = new StringBuilder();
        if (n.length() < 4) {
            for (int i = 0; i < 4 - n.length(); i++) {
                strb.append(0);
            }
        }
        strb.append(n);
        n = strb.toString();
        char[] arr = n.toCharArray();
        List<Character> list1 = new ArrayList<>();
        for (char c : arr) {
            list1.add(c);
        }
        List<Character> list2 = new ArrayList<>(list1);
        Collections.sort(list1);
        Collections.sort(list2, new Comparator2());
        strb = new StringBuilder();
        for (char c : list1) {
            strb.append(c);
        }
        s1 = strb.toString();
        int n1 = Integer.parseInt(strb.toString());
        strb = new StringBuilder();
        for (char c : list2) {
            strb.append(c);
        }
        s2 = strb.toString();
        int n2 = Integer.parseInt(strb.toString());
        System.out.println(s2 + " - " + s1 + " = " + String.format("%04d", n2 - n1));
        return String.format("%04d", n2 - n1);
    }

    private static class Comparator2 implements Comparator<Character> {

        @Override
        public int compare(Character o1, Character o2) {
            return o2.charValue() - o1.charValue();
        }
    }

    private static class Comparator3 implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }
}
