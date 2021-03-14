import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class PAT1054 {
    static PriorityQueue<Color> pq = new PriorityQueue<>(new Comparator<Color>() {
        @Override
        public int compare(Color o1, Color o2) {
            return o2.times - o1.times;
        }
    });
    static Map<Integer, Color> map = new HashMap<>();

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info1 = br.readLine().split(" ");
        int col = Integer.parseInt(info1[0]);
        int row = Integer.parseInt(info1[1]);
        for (int r = 0; r < row; r++) {
            String[] line = br.readLine().split(" ");
            for (int c = 0; c < col; c++) {
                int id = Integer.parseInt(line[c]);
                if (!map.containsKey(id)) {
                    map.put(id, new Color(id));
                }
                map.get(id).times++;

            }
        }
        for (int key : map.keySet()) {
            pq.add(map.get(key));
        }
        System.out.println(pq.peek().id);
    }

    private static class Color {
        int id;
        int times;
        Color (int id) {
            this.id = id;
            this.times = 0;
        }
    }
}
