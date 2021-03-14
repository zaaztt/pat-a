import java.util.*;

public class PAT1087 {
    static HashMap<String, City> map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line1 = sc.nextLine().split(" ");
        int N = Integer.valueOf(line1[0]);
        int K = Integer.valueOf(line1[1]);
        String startCity = line1[2];
        map = new HashMap<>();
        map.put(startCity, new City(startCity, 0));
        for (int i = 0; i < N - 1; i++) {
            String[] line2 = sc.nextLine().split(" ");
            String name = line2[0];
            int happiness = Integer.valueOf(line2[1]);
            map.put(name, new City(name, happiness));
        }
        for (int i = 0; i < K; i++) {
            String[] line3 = sc.nextLine().split(" ");
            City city1 = map.get(line3[0]);
            City city2 = map.get(line3[1]);
            int dist = Integer.valueOf(line3[2]);
            city1.distTo.put(city2, dist);
            city2.distTo.put(city1, dist);
        }
        PriorityQueue<City> pq = new PriorityQueue<>(new Comparator2());
        for (String name : map.keySet()) {
            City city = map.get(name);
            if (name.equals(startCity)) {
                city.cost = 0;
                pq.add(city);
            } else {
                city.cost = Integer.MAX_VALUE;
            }

        }
        int count = 0;
        while (!pq.isEmpty()) {
            City cur_city = pq.poll();
            for (City c : cur_city.distTo.keySet()) {
                if (cur_city.cost + cur_city.distTo.get(c) < c.cost) {
                    c.cost = cur_city.cost + cur_city.distTo.get(c);
                    c.cur_happy = cur_city.cur_happy + c.happiness;
                    c.from = cur_city;
                    c.same_route = cur_city.same_route;
                    count = 1;
                    pq.add(c);
                } else if (cur_city.cost + cur_city.distTo.get(c) == c.cost) {
                    if (c.cur_happy < c.happiness + cur_city.cur_happy) {
                        c.from = cur_city;
                        c.cur_happy = c.happiness + cur_city.cur_happy;
                    } else {

                    }
                    c.same_route += cur_city.same_route;
                    count++;
                    pq.add(c);
                }
            }
        }
        City end_city = map.get("ROM");
        List<String> list = new ArrayList<>();
        City temp = end_city;
        while (temp != null) {
            list.add(temp.name);
            temp = temp.from;
        }
        Collections.reverse(list);
        System.out.println(count + " " + end_city.cost + " " + end_city.cur_happy + " " + end_city.cur_happy / (list.size() - 1));
        StringBuilder strb = new StringBuilder();
        for (String s : list) {
            strb.append(s);
            strb.append("->");
        }
        strb.deleteCharAt(strb.length() - 1);
        strb.deleteCharAt(strb.length() - 1);
        System.out.println(strb);
    }

    private static class Comparator2 implements Comparator<City> {

        @Override
        public int compare(City o1, City o2) {
            return o1.cost - o2.cost;
        }
    }

    private static class City {
        String name;
        int happiness;
        HashMap<City, Integer> distTo;
        int cost;
        int cur_happy;
        City from;
        int same_route = 1;
        boolean visited = false;
        City (String name, int happiness) {
            this.name = name;
            this.happiness = happiness;
            distTo = new HashMap<>();
            cur_happy = 0;
        }
    }
}
