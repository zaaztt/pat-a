import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PAT1034 {
    static Map<String, Person> map_person_time = new HashMap<>();
    static List<Set<String>> clusters = new ArrayList<>();
    static Map<Integer, Integer> map_clusters_time = new HashMap<>();
    static List<String> ans_name = new ArrayList<>();


    public static void main (String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        int n = Integer.parseInt(info[0]);
        int boundary = Integer.parseInt(info[1]);
        for (int i = 0; i < n; i++) {
            String[] spl = br.readLine().split(" ");
            String name_A = spl[0];
            String name_B = spl[1];
            int time = Integer.parseInt(spl[2]);
            if (!map_person_time.containsKey(name_A)) {
                map_person_time.put(name_A, new Person(name_A, 0));
            }
            if (!map_person_time.containsKey(name_B)) {
                map_person_time.put(name_B, new Person(name_B, 0));
            }
            map_person_time.get(name_A).call_time += time;
            map_person_time.get(name_B).call_time += time;

            boolean find_cluster = false;
            for (int j = 0; j < clusters.size(); j++) {
                // 成员可以在clusters里找到同伴
                if (clusters.get(j).contains(name_A)) {
                    find_cluster = true;
                    clusters.get(j).add(name_A);
                    clusters.get(j).add(name_B);
                    map_clusters_time.put(j, map_clusters_time.get(j) + time);
                    break;
                }
                if (clusters.get(j).contains(name_B)) {
                    find_cluster = true;
                    clusters.get(j).add(name_A);
                    clusters.get(j).add(name_B);
                    map_clusters_time.put(j, map_clusters_time.get(j) + time);
                    break;
                }
            }

            if (find_cluster == false || clusters.size() == 0) {
                // 成员无法在clusters里找到同伴，自己开创一个cluster
                Set<String> temp_cluster = new HashSet<>();
                temp_cluster.add(name_A);
                temp_cluster.add(name_B);
                clusters.add(temp_cluster);
                int temp_id = clusters.size() - 1;
                if (!map_clusters_time.containsKey(temp_id)) {
                    map_clusters_time.put(temp_id, 0);
                }
                map_clusters_time.put(temp_id, map_clusters_time.get(temp_id) + time);
            }
        }

        for (int i = 0; i < clusters.size(); i++) {
            if (clusters.get(i).size() >= 3 && map_clusters_time.get(i) > boundary) {
                String maxOne = null;
                int maxTime = 0;
                for (String s : clusters.get(i)) {
                    if (map_person_time.get(s).call_time > maxTime) {
                        maxOne = s;
                        maxTime = map_person_time.get(s).call_time;
                    }
                }
                ans_name.add(maxOne);
                map_person_time.get(maxOne).size = clusters.get(i).size();
            }
        }

        Collections.sort(ans_name);
        System.out.println(ans_name.size());
        if (ans_name.size() != 0) {
            for (String s : ans_name) {
                System.out.print(s + " " + map_person_time.get(s).size);
                System.out.println();
            }
        }



        /*
        for (int i = 0; i < clusters.size(); i++) {
            System.out.println(map_clusters_time.get(i));
        }

         */

        /*
        for (Set<String> s : clusters) {
            System.out.println(s);
        }

         */
    }

    private static class Person {
        String nameA;
        int size;
        int call_time;

        Person (String nameA, int call_time) {
            this.nameA = nameA;
            this.call_time = call_time;
        }
    }
}
