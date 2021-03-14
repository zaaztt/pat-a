import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PAT1064 {

    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode (int val) {
            this.val = val;
        }
    }
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        String[] nums = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(nums[i]);
        }
        Arrays.sort(arr);
        TreeNode root = dfs(arr, N, 0, arr.length - 1);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder strb = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) queue.offer(cur.left);
            if (cur.right != null) queue.offer(cur.right);
            strb.append(cur.val);
            strb.append(" ");
        }
        strb.deleteCharAt(strb.length() - 1);
        System.out.println(strb);
    }

    private static TreeNode dfs (int[] arr, int N, int l, int r) {
        if (l > r) {
            return null;
        }
        if (l == r) {
            return new TreeNode(arr[l]);
        }
        int n = 1;
        int sum = 1;
        while (true) {
            if (N > sum && N <= sum + 2 * n) {
                break;
            }
            n *= 2;
            sum += n;
        }
        int right; int left;
        if (sum + 2*n - N <= n) {
            right = N - 2*n;
            left = sum;
        } else {
            right = sum - n;
            left = right + N - sum;
        }
        TreeNode cur = new TreeNode(arr[l + left]);
        cur.left = dfs(arr, left, l, l + left - 1);
        cur.right = dfs(arr, right, l + left + 1, r);
        return cur;
    }
}
