import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PAT1030 {
    static Map<Integer, City> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        int N = Integer.parseInt(info[0]);
        int M = Integer.parseInt(info[1]);
        int start = Integer.parseInt(info[2]);
        int end = Integer.parseInt(info[3]);
        map = new HashMap<>();
        for (int i = 0; i < M; i+=1) {
            map.put(i, new City(N, i));
        }
        for (int i = 0; i < M; i++) {
            String[] line = br.readLine().split(" ");
            int city1_id = Integer.parseInt(line[0]);
            int city2_id = Integer.parseInt(line[1]);
            int dis = Integer.parseInt(line[2]);
            int cost = Integer.parseInt(line[3]);
            map.get(city1_id).disTo[city2_id] = dis;
            map.get(city1_id).costTo[city2_id] = cost;
            map.get(city2_id).disTo[city1_id] = dis;
            map.get(city2_id).costTo[city1_id] = cost;
        }

        for (int i = 0; i < N; i++) {
            if (i == start) {
                map.get(i).leastCost = 0;
                map.get(i).shortestCity = -1;
                map.get(i).shortestDis = 0;
                continue;
            }
            map.get(i).leastCost = Integer.MAX_VALUE;
            map.get(i).shortestCity = -1;
            map.get(i).shortestDis = Integer.MAX_VALUE;
        }

        PriorityQueue<City> pq = new PriorityQueue<>(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.shortestDis - o2.shortestDis;
            }
        });
        for (int i = 0; i < N; i++) {
            pq.add(map.get(i));
        }
        while (!pq.isEmpty()) {
            City cur = pq.remove();
            for (int i = 0; i < N; i++) {
                if (cur.disTo[i] != -1) {
                    if (cur.disTo[i] + cur.shortestDis < map.get(i).shortestDis) {
                        map.get(i).shortestDis = cur.disTo[i] + cur.shortestDis;
                        map.get(i).shortestCity = cur.val;
                        map.get(i).leastCost = cur.leastCost + cur.costTo[i];
                        pq.add(map.get(i));
                    } else if (cur.disTo[i] + cur.shortestDis == map.get(i).shortestDis) {
                        if (cur.costTo[i] + cur.leastCost < map.get(i).leastCost) {
                            map.get(i).shortestCity = cur.val;
                            map.get(i).leastCost = cur.leastCost + cur.costTo[i];
                            pq.add(map.get(i));
                        }
                    }

                }
            }
        }
        /**
        for (int i = 0; i < N; i++) {
            System.out.print(i + " ");
            System.out.print(map.get(i).shortestCity + " ");
            int j = i;
            while (map.get(j).shortestCity != -1) {
                j = map.get(j).shortestCity;
                System.out.print(map.get(j).shortestCity + " ");
            }
            System.out.print(map.get(i).shortestDis + " ");
            System.out.println(map.get(i).leastCost);
        } */
        StringBuilder strb = new StringBuilder();
        strb.append(end);
        int temp = end;
        while (map.get(temp).shortestCity != -1) {
            temp = map.get(temp).shortestCity;
            strb.append(" "); strb.append(temp);
        }
        strb.reverse();
        strb.append(" ");
        strb.append(map.get(end).shortestDis);
        strb.append(" ");
        strb.append(map.get(end).leastCost);
        System.out.println(strb);


    }

    private static void dfs (int N) {

    }

    private static class City {
        int[] disTo;
        int[] costTo;
        int shortestDis;
        int shortestCity;
        int leastCost;
        int val;

        City (int n, int val) {
            disTo = new int[n];
            Arrays.fill(disTo, -1);
            costTo = new int[n];
            Arrays.fill(costTo, -1);
            this.val = val;
        }
    }
}
