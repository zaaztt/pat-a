import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PAT1065_2 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> A_list = new ArrayList<>();
        List<String> B_list = new ArrayList<>();
        List<String> C_list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            A_list.add(line[0]);
            B_list.add(line[1]);
            C_list.add(line[2]);
        }
        for (int i = 0; i < N; i++) {
            System.out.println("Case #" + (i+1) + ": " + isLarger(A_list.get(i),B_list.get(i),C_list.get(i)));
        }
    }

    private static boolean isLarger (String A, String B, String C) {
        long A_long = Long.parseLong(A);
        long B_long = Long.parseLong(B);
        long C_long = Long.parseLong(C);

        if (A_long >= 0 && B_long >= 0) {
            if (C_long >= 0) {
                return A_long > C_long - B_long;
            } else {
                return true;
            }
        } else if (A_long <= 0 && B_long >= 0) {
            if (C_long >= 0) return A_long > C_long - B_long;
            else return B_long > C_long - A_long;
        } else if (A_long >= 0 && B_long <= 0) {
            if (C_long >= 0) return B_long > C_long - A_long;
            else return A_long > C_long - B_long;
        } else {
            if (C_long >= 0) {
                return false;
            } else {
                return A_long > C_long - B_long;
            }
        }

    }
}
