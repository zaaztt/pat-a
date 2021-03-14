import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PAT1041 {
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] spl = br.readLine().split(" ");
        int N = Integer.parseInt(spl[0]);
        for (int i = 1; i <= N ; i++) {
            int temp = Integer.parseInt(spl[i]);
            if (!map.containsKey(temp)) {
                map.put(temp, 0);
            }
            map.put(temp, map.get(temp) + 1);
        }
        Set<Integer> set = new HashSet<>();
        for (int i : map.keySet()) {
            if (map.get(i) == 1) {
                set.add(i);
            }
        }
        for (int i = 1; i <= N ; i++) {
            int temp = Integer.parseInt(spl[i]);
            if (set.contains(temp)) {
                System.out.println(temp);
                return;
            }
        }
        System.out.println("None");
    }
}
