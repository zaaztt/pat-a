import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PAT1056 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info1 = br.readLine().split(" ");
        int N = Integer.parseInt(info1[0]);
        int M = Integer.parseInt(info1[1]);
        Mice[] mice = new Mice[N];
        String[] info2 = br.readLine().split(" ");
        String[] info3 = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            mice[i] = new Mice();
            mice[i].id = i;
            mice[i].weight = Integer.parseInt(info2[i]);
            mice[i].playing_order = Integer.parseInt(info3[i]);
        }
        List<Mice> mice_po_l = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            mice_po_l.add(mice[Integer.parseInt(info3[i])]);
        }
        StringBuilder strb = new StringBuilder();
        play(mice_po_l, M);
        for (Mice m : mice) {
            strb.append(m.rank);
            strb.append(" ");
        }
        strb.deleteCharAt(strb.length() - 1);
        System.out.println(strb);
    }

    private static class Mice {
        int id;
        int weight;
        int playing_order;
        int rank;
    }

    private static class Comparator_po implements Comparator<Mice> {

        @Override
        public int compare(Mice o1, Mice o2) {
            return o1.playing_order - o2.playing_order;
        }
    }

    private static class Comparator_w implements Comparator<Mice> {

        @Override
        public int compare(Mice o1, Mice o2) {
            return o1.weight - o2.weight;
        }
    }

    private static void play(List<Mice> cur, int M) {
        if (cur.size() == 1) {
            cur.get(0).rank = 1;
            return;
        }
        int cur_rank = cur.size() / M + 1;
        if (cur.size() % M > 0) cur_rank += 1;
        List<Mice> levelups = new ArrayList<>();
        int temp = 0;
        int temp_loc = 0;
        List<Mice> comp = new ArrayList<>();
        for (int i = 0; i < cur.size(); i++) {
            temp++;
            int c = i % M;
            comp.add(cur.get(i));
            if (temp % M == 0) {
                Collections.sort(comp, new Comparator_w());
                for (int j = 0; j < M - 1; j++) {
                    comp.get(j).rank = cur_rank;
                }
                levelups.add(comp.get(M - 1));
                temp_loc += 1;
                comp.clear();
            }
        }

        if (cur.size() % M > 0) {
            Collections.sort(comp, new Comparator_w());
            for (int j = 0; j < cur.size() % M - 1; j++) {
                comp.get(j).rank = cur_rank;
            }
            levelups.add(comp.get(comp.size() - 1));
        }
        play(levelups, M);
    }
}
