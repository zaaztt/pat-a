import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PAT1058 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        String[] item1 = line[0].split("\\.");
        String[] item2 = line[1].split("\\.");
        int item1_r = Integer.parseInt(item1[2]);
        int item1_m = Integer.parseInt(item1[1]);
        int item1_l = Integer.parseInt(item1[0]);
        int item2_m = Integer.parseInt(item2[1]);
        int item2_r = Integer.parseInt(item2[2]);
        int item2_l = Integer.parseInt(item2[0]);
        int item_r = (item1_r + item2_r) % 29;
        int item_m = (item1_m + item2_m + (item1_r + item2_r) / 29) % 17;
        int item_l = (item1_l + item2_l + (item1_m + item2_m + (item1_r + item2_r) / 29) / 17);
        System.out.println(item_l + "." + item_m + "." + item_r);
    }
}
