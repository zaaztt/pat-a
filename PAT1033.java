import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PAT1033 {
    static double tank_capacity;
    static double destination;
    static double unit_gas_dis;
    static double furthestDis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        tank_capacity = Double.parseDouble(info[0]);
        destination = Double.parseDouble(info[1]);
        unit_gas_dis = Double.parseDouble(info[2]);
        furthestDis = tank_capacity * unit_gas_dis;
        int n = Integer.parseInt(info[3]);
        Station[] stations = new Station[n];
        for (int i = 0; i < n; i++) {
            stations[i] = new Station();
            String[] spl = br.readLine().split(" ");
            stations[i].unit_price = Double.parseDouble(spl[0]);
            stations[i].dis_from_start = Double.parseDouble(spl[1]);
        }
        Arrays.sort(stations, new Comparator1());
        double sum = bestStrategy(stations, 0, 0, 0, 0);
        if (sum != -1) {
            System.out.println(String.format("%.2f",sum));
        }
        Map<Integer, Integer> map = new HashMap<>();
    }

    private static double bestStrategy (Station[] stations, int cur_station, double cur_loc, double cur_tank, double cur_cost) {

        if (stations[0].dis_from_start != 0) {
            System.out.println("The maximum travel distance = 0.00");
            return -1;
        }

        // 找到下一站比当前站便宜的
        for (int i = cur_station + 1; i < stations.length; i++) {
            if (stations[i].unit_price <= stations[cur_station].unit_price && (stations[i].dis_from_start - cur_loc) <= furthestDis) {
                double tank_need = (stations[i].dis_from_start - cur_loc) / unit_gas_dis;
                if (cur_tank >= tank_need) {
                    return bestStrategy(stations, i, stations[i].dis_from_start, cur_tank - tank_need, cur_cost);
                } else {
                    double temp_cost = (tank_need - cur_tank) * stations[cur_station].unit_price;
                    return bestStrategy(stations, i, stations[i].dis_from_start, 0, cur_cost + temp_cost);
                }
            }
        }

        // 找不到比当前站便宜的
        if (destination - cur_loc <= furthestDis) { //可以直接到终点
            double temp_cost = ((destination - cur_loc) / unit_gas_dis) * stations[cur_station].unit_price;
            return cur_cost + temp_cost;
        }

        // 前面啥站台都没了 还够不到终点
        if (cur_station == stations.length - 1) {
            System.out.println("The maximum travel distance = " + String.format("%.2f",(cur_loc+furthestDis)));
            return -1;
        }

        // 都大于当前站的费用 且 够不到终点
        int secMinStaID = cur_station + 1;
        Station secMinSta = stations[secMinStaID];
        for (int i = cur_station + 1; i < stations.length; i++) {
            if (stations[i].unit_price < secMinSta.unit_price && stations[i].dis_from_start - cur_loc <= furthestDis) {
                secMinStaID = i;
                secMinSta = stations[i];
            }
            if (stations[i].dis_from_start - cur_loc > furthestDis) break;
        }
        double temp_tank = tank_capacity - (stations[secMinStaID].dis_from_start - cur_loc) / unit_gas_dis;
        double temp_cos = (tank_capacity - cur_tank) * stations[cur_station].unit_price;
        return bestStrategy(stations, secMinStaID, stations[secMinStaID].dis_from_start, temp_tank, cur_cost + temp_cos);
    }

    private static class Station {
        double unit_price;
        double dis_from_start;
    }

    private static class Comparator1 implements Comparator<Station> {

        @Override
        public int compare(Station o1, Station o2) {
            if (o1.dis_from_start > o2.dis_from_start) return 1;
            else if (o1.dis_from_start < o2.dis_from_start) return -1;
            else return 0;
        }
    }
}
