import org.omg.CORBA.INTERNAL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PAT1047 {

    static Map<Integer, List<String>> map = new HashMap<>();

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info1 = br.readLine().split(" ");
        int N_stu = Integer.parseInt(info1[0]);
        int N_cou = Integer.parseInt(info1[1]);
        for (int i = 0; i < N_stu; i++) {
            String[] spl = br.readLine().split(" ");
            String name = spl[0];
            int temp_cou = Integer.parseInt(spl[1]);
            for (int j = 0; j < temp_cou; j++) {
                int cou_id = Integer.parseInt(spl[j + 2]);
                if (!map.containsKey(cou_id)) {
                    map.put(cou_id, new ArrayList<>());
                }
                map.get(cou_id).add(name);
            }
        }
        for (int i = 1; i <= N_cou; i++) {
            System.out.println(i + " " + map.get(i).size());
            Collections.sort(map.get(i));
            for (String s : map.get(i)) System.out.println(s);
        }
    }
}
