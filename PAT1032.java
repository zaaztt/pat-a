import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PAT1032 {
    static Map<String, Word> map = new HashMap<>();
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        String start1 = info[0];
        String start2 = info[1];
        int n = Integer.parseInt(info[2]);
        for (int i = 0; i < n; i++) {
            String[] spl = br.readLine().split(" ");
            map.put(spl[0], new Word(spl[0], spl[1], spl[2]));
        }
        int len1 = 0; int len2 = 0;
        String temp = start1;
        while (map.containsKey(temp)) {
            len1++;
            temp = map.get(temp).next;
        }
        temp = start2;
        while (map.containsKey(temp)) {
            len2++;
            temp = map.get(temp).next;
        }
        String temp1 = start1;
        String temp2 = start2;
        for (int i = 0; i < len1 + len2 - 1; i++) {
            temp1 = map.get(temp1).next;
            temp2 = map.get(temp2).next;
            if (temp1.equals("-1")) {
                temp1 = start2;
            }
            if (temp2.equals("-1")) {
                temp2 = start1;
            }
            if (map.get(temp1).addr.equals(map.get(temp2).addr)) {
                if (map.get(temp1).next.equals(map.get(temp2).next)) {
                    System.out.println(map.get(temp1).addr);
                    return;
                }

            }
        }
        System.out.println("-1");
    }

    private static class Word {
        String addr;
        String c;
        String next;
        Word (String addr, String c, String next) {
            this.addr = addr;
            this.c = c;
            this.next = next;
        }
    }
}
