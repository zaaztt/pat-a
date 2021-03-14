import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PAT1057 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> stack = new ArrayList<>();
        String[][] lines = new String[N][];
        for (int i = 0; i < N; i++) {
            lines[i] = br.readLine().split(" ");
        }
        for (int i = 0; i < N; i++) {
            if (lines[i][0].equals("Pop")) {
                if (stack.size() == 0) {
                    System.out.println("Invalid");
                } else {
                    System.out.println(stack.remove(stack.size() - 1));
                }
            }
            if (lines[i][0].equals("PeekMedian")) {

                if (stack.size() == 0) {
                    System.out.println("Invalid");
                } else {
                    List<Integer> temp_list = new ArrayList<>(stack);
                    Collections.sort(temp_list);
                    System.out.println(temp_list.get((temp_list.size() - 1) / 2));
                }
            }
            if (lines[i][0].equals("Push")) {
                stack.add(Integer.parseInt(lines[i][1]));

            }
        }
    }
}
