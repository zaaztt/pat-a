import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PAT1063 {
    static List<Set<Integer>> total_list = new ArrayList<>();
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N_set = Integer.parseInt(br.readLine());
        for (int i = 0; i < N_set; i++) {
            String[] line = br.readLine().split(" ");
            int N = Integer.parseInt(line[0]);
            Set<Integer> set = new HashSet<>();
            for (int j = 1; j <= N; j++) {
                set.add(Integer.parseInt(line[j]));
            }
            total_list.add(new HashSet<>(set));
        }
        int N_query = Integer.parseInt(br.readLine());
        List<List<Integer>> query_list = new ArrayList<>();
        for (int i = 0; i < N_query; i++) {
            String[] line = br.readLine().split(" ");
            List<Integer> list = new ArrayList<>();
            list.add(Integer.parseInt(line[0]));
            list.add(Integer.parseInt(line[1]));
            query_list.add(new ArrayList<>(list));
        }
        for (int i = 0; i < query_list.size(); i++) {
            Set<Integer> set1 = total_list.get(query_list.get(i).get(0) - 1);
            Set<Integer> set2 = total_list.get(query_list.get(i).get(1) - 1);
            Set<Integer> set = new HashSet<>();
            for (int num : set1) {
                set.add(num);
            }
            for (int num : set2) {
                set.add(num);
            }
            int Nc = set1.size() + set2.size() - set.size();
            int Nt = set.size();
            double sim = (double) Nc / (double) Nt * 100;
            System.out.println(String.format("%.1f", sim) + "%");
        }
    }
}
