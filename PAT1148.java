import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PAT1148 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] a = new int[N + 1];
        int[] sentences = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sentences[i] = sc.nextInt();
        }

        List<Integer> liar_list = new ArrayList<>();

        for (int i = 1; i <= N - 1; i++) {
            for (int j = i + 1; j <= N; j++) {
                liar_list.clear();
                Arrays.fill(a, 1);
                a[i] = -1;
                a[j] = -1;

                for (int k = 1; k <= N; k++) {
                    int p2 = Math.abs(sentences[k]);
                    int sentence = sentences[k];
                    if (sentence * a[p2] < 0) {
                        liar_list.add(k);
                    }
                }

                if (liar_list.size() == 2 && a[liar_list.get(0)] * a[liar_list.get(1)] < 0) {
                    System.out.println(i + " " + j);
                    return;
                }
            }
        }

        System.out.println("No Solution");
    }
}
