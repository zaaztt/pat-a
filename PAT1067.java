import org.omg.PortableInterceptor.INACTIVE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class PAT1067 {
    static HashMap<Integer, Integer> map = new HashMap<>();
    static int[] arr;
    static int sum = 0;
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new HashMap<>();
        int N = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(line[i]);
            arr[i] = num;
            map.put(num, i);
        }
        while (true) {
            boolean flag = true;
            for (int i : map.keySet()) {
                if (map.get(i) != i) {
                    flag = false;
                    break;
                }
            }
            if (flag == false) {
                swap();
            } else {
                break;
            }
        }
        System.out.println(sum);
    }

    private static void swap () {
        int loc_0 = map.get(0);
        int loc_n = map.get(loc_0);
        int n = arr[loc_n];
        if (loc_0 == 0) {
            for (int i : map.keySet()) {
                if (map.get(i) != i) {
                    loc_n = map.get(i);
                    n = i;
                    arr[loc_0] = arr[loc_n];
                    arr[loc_n] = 0;
                    map.put(0, loc_n);
                    map.put(n, loc_0);
                    sum++;
                    break;
                }
            }

        } else {
            arr[loc_0] = arr[loc_n];
            arr[loc_n] = 0;
            map.put(0, loc_n);
            map.put(n, loc_0);
            sum++;
        }
    }
}
