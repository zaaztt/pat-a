import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PAT1029 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split(" ");
        int n1 = Integer.parseInt(line1[0]);
        int[] arr1 = new int[n1 + 1];
        for (int i = 0; i < n1; i++) {
            arr1[i] = Integer.parseInt(line1[i + 1]);
        }
        arr1[arr1.length - 1] = Integer.MAX_VALUE;
        String[] line2 = br.readLine().split(" ");
        int n2 = Integer.parseInt(line2[0]);
        int[] arr2 = new int[n2 + 1];
        for (int i = 0; i < n2; i++) {
            arr2[i] = Integer.parseInt(line2[i + 1]);
        }
        arr2[arr2.length - 1] = Integer.MAX_VALUE;
        int i = 0; int j = 0; int cnt = 0;
        while (cnt != (n1 + n2 - 1) / 2) {
            if (arr1[i] < arr2[j]) {
                i++;
            } else {
                j++;
            }
            cnt++;
        }
        if (arr1[i] < arr2[j]) System.out.println(arr1[i]);
        else System.out.println(arr2[j]);
    }
}
