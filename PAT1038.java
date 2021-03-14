import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PAT1038 {
    static Map<Integer, List<String>> map = new HashMap<>();
    static StringBuilder strb = new StringBuilder();

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] spl = br.readLine().split(" ");
        for (int i = 1; i <= Integer.parseInt(spl[0]); i++) {
            int head = spl[i].charAt(0) - '0';
            if (!map.containsKey(head)) map.put(head, new ArrayList<String>());
            map.get(head).add(spl[i]);
        }
        for (int i = 0; i <= 9; i++) {
            if (map.containsKey(i)) {
                if (i == 0) {
                    Collections.sort(map.get(i));
                } else {
                    Collections.sort(map.get(i), new Comparator2());
                }
            }
        }
        for (int i = 0; i <= 9; i++) {
            if (map.containsKey(i)) {
                for (String s : map.get(i)) strb.append(s);
            }
        }
        int cnt = 0;
        for (int i = 0; i < strb.length(); i++) {
            if (strb.charAt(i) - '0' == 0) {
                cnt++;
            } else {
                break;
            }
        }
        String ans = strb.substring(cnt);
        System.out.println(ans);

    }

    private static class Comparator2 implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    private static class Comparator1 implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if (head0(o1) < head0(o2)) {
                return -1;
            } else if (head0(o1) > head0(o2)) {
                return 1;
            } else {
                if (o1.length() > o2.length()) {
                    return -1;
                } else if (o1.length() < o2.length()) {
                    return 1;
                } else {
                    for (int i = 0; i < o1.length(); i++) {
                        if (o1.charAt(i) - o2.charAt(i) != 0) {
                            return o2.charAt(i) - o1.charAt(i);
                        }
                    }
                    return 0;
                }
            }
        }
    }

    private static int head0 (String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) - '0' == 0) count++;
            else break;
        }
        return count;
    }
}
