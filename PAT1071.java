import java.util.*;

public class PAT1071 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        StringBuilder strb = new StringBuilder();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                c = (char) (c - 'A' + 'a');
                strb.append(c);
            } else if (c >= 'a' && c <= 'z') {
                strb.append(c);
            } else if (c >= '0' && c <= '9') {
                strb.append(c);
            } else {
                String s = strb.toString();
                if (!s.equals("")) {
                    if (!map.containsKey(s)) {
                        map.put(s, 0);
                    }
                    map.put(s, map.get(s) + 1);
                    strb = new StringBuilder();
                }
            }
        }
        String s = strb.toString();
        if (!s.equals("")) {
            if (!map.containsKey(s)) {
                map.put(s, 0);
            }
            map.put(s, map.get(s) + 1);
            s = "";
        }
        String max_str = null;
        int max_count = 0;
        for (String str : map.keySet()) {
            int count = map.get(str);
            if (count > max_count) {
                max_str = str;
                max_count = count;
            } else if (count == max_count) {
                if (str.compareTo(max_str) < 0) {
                    max_str = str;
                }
            }
        }
        System.out.println(max_str + " " + max_count);
    }
}
