import java.util.*;

public class PAT1016 {
    static Map<String, List<Bill>> map = new HashMap<>();
    static Map<String, List<Record>> map_ans = new HashMap<>();
    static int[] fees_a;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        fees_a = new int[24];
        for (int i = 0; i < 24; i++) {
            fees_a[i] = sc.nextInt();
        }
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            String name = sc.next();
            String time = sc.next();
            String state = sc.next();
            if (!map.containsKey(name)) {
                map.put(name, new ArrayList<>());
            }
            map.get(name).add(new Bill(name, time, state));
        }
        List<String> n_list = new ArrayList<>();
        for (String key : map.keySet()) {
            n_list.add(key);
            if (!map_ans.containsKey(key)) {
                map_ans.put(key, new ArrayList<>());
            }
            List<Record> r_list = map_ans.get(key);
            List<Bill> b_list = map.get(key);
            Collections.sort(b_list, new Comp2());
            for (int i = 0; i < b_list.size() - 1; i++) {
                Bill b_s = b_list.get(i);
                Bill b_e = b_list.get(i + 1);
                if (b_s.isOnline && !b_e.isOnline) {
                    int cost = calCost(b_s.time, b_e.time);
                    r_list.add(new Record(convert(b_s.time), convert(b_e.time), cost));
                }
            }

        }
        Collections.sort(n_list);
        for (String name : n_list) {
            double sum = 0;
            List<Record> r_list = map_ans.get(name);
            System.out.println(name + " " + String.format("%02d", r_list.get(0).start_time / (32 * 24 * 60)));
            for (Record record : r_list) {
                double cur_cost = (double)record.cost / 100;
                System.out.println(convert_without_MM(record.start_time) + " " + convert_without_MM(record.end_time) + " " + (record.end_time - record.start_time) + " $" + String.format("%.02f", cur_cost));
                sum += cur_cost;
            }
            System.out.println("Total amount: $" + String.format("%.02f", sum));
        }

    }

    private static int calCost (String time_start, String time_end) {
        int t_s = convert(time_start);
        int t_e = convert(time_end);
        int sum = 0;
        while (t_e > up_bound(t_s)) {
            sum += (up_bound(t_s) - t_s) * cur_floor_cost(t_s);
            t_s = up_bound(t_s);
        }
        sum += (t_e - t_s) * cur_floor_cost(t_s);
        return sum;
    }

    private static int cur_floor_cost (int time) {
        return fees_a[(time % (24 * 60)) / 60];
    }


    private static int up_bound (int time) {
        return time + (60 - time % 60);
    }

    private static int convert (String time) {
        int sum = 0;
        String[] spl = time.split(":");
        int MM = Integer.parseInt(spl[0]);
        int dd = Integer.parseInt(spl[1]);
        int HH = Integer.parseInt(spl[2]);
        int mm = Integer.parseInt(spl[3]);
        sum += MM;
        sum *= 32;
        sum += dd;
        sum *= 24;
        sum += HH;
        sum *= 60;
        sum += mm;
        return sum;
    }

    private static String convert (int time) {

        int MM = time / (32 * 24 * 60);
        time = time % (32 * 24 * 60);
        int dd = time / (24 * 60);
        time = time % (24 * 60);
        int HH = time / 60;
        time = time % 60;
        int mm = time;

        return String.format("%02d", MM) + ":" + String.format("%02d", dd) + ":" + String.format("%02d", HH) + ":" + String.format("%02d", mm);
    }

    private static String convert_without_MM (int time) {

        int MM = time / (32 * 24 * 60);
        time = time % (32 * 24 * 60);
        int dd = time / (24 * 60);
        time = time % (24 * 60);
        int HH = time / 60;
        time = time % 60;
        int mm = time;

        return String.format("%02d", dd) + ":" + String.format("%02d", HH) + ":" + String.format("%02d", mm);
    }

    private static class Comp2 implements Comparator<Bill> {

        @Override
        public int compare(Bill o1, Bill o2) {
            return o1.time.compareTo(o2.time);
        }
    }

    private static class Record {
        int start_time;
        int end_time;
        int cost;
        Record(int start_time, int end_time, int cost) {
            this.start_time = start_time;
            this.end_time = end_time;
            this.cost = cost;
        }
    }

    private static class Bill {
        String name;
        String time;
        boolean isOnline;
        Bill (String name, String time, String state) {
            this.name = name;
            this.time = time;
            if (state.equals("on-line")) {
                isOnline = true;
            } else {
                isOnline = false;
            }
        }
    }
}
