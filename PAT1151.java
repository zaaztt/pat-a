import java.util.*;

public class PAT1151 {
    static int[] inOrder_array;
    static int[] preOrder_array;
    static TreeNode root;
    static Map<Integer, TreeNode> map = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        inOrder_array = new int[N];
        preOrder_array = new int[N];
        for (int i = 0; i < N; i++) {
            inOrder_array[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            preOrder_array[i] = sc.nextInt();
        }
        root = buildTree(0, N - 1, 0, N - 1);
        root.id_strb = new StringBuilder();
        root.id_strb.append(0);
        dfs(root, root.id_strb);
        List<List<Integer>> query_list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            List<Integer> list = new ArrayList<>();
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            list.add(n1);
            list.add(n2);
            query_list.add(new ArrayList<>(list));
        }
        for (List<Integer> list : query_list) {
            lca(list.get(0), list.get(1));
        }
    }

    private static void lca (int n1, int n2) {
        boolean exist_1 = true;
        boolean exist_2 = true;
        if (!map.containsKey(n1)) {
            exist_1 = false;
        }
        if (!map.containsKey(n2)) {
            exist_2 = false;
        }
        if (!exist_1 && !exist_2) {
            System.out.println("ERROR: " + n1 + " and " + n2 + " are not found.");
        } else if (!exist_1) {
            System.out.println("ERROR: " + n1 + " is not found.");
        } else if (!exist_2) {
            System.out.println("ERROR: " + n2 + " is not found.");
        } else {
            boolean isEqual = true;
            TreeNode node1 = map.get(n1);
            TreeNode node2 = map.get(n2);
            StringBuilder strb1 = node1.id_strb;
            StringBuilder strb2 = node2.id_strb;
            int k = 0;
            for (k = 0; k < Math.min(strb1.length(), strb2.length()); k++) {
                if (strb1.charAt(k) != strb2.charAt(k)) {
                    break;
                }
            }

            if (k == strb1.length()) {
                System.out.println(n1 + " is an ancestor of " + n2 + ".");
                return;
            }
            if (k == strb2.length()) {
                System.out.println(n2 + " is an ancestor of " + n1 + ".");
                return;
            }
            TreeNode cur = root;
            for (int i = 1; i < k; i++) {
                if (strb1.charAt(i) == '0') {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            System.out.println("LCA of " + n1 + " and " + n2 + " is " + cur.val + ".");
        }
    }

    private static void dfs (TreeNode cur, StringBuilder strb) {
        map.put(cur.val, cur);
        if (cur.left != null) {
            strb.append(0);
            cur.left.id_strb = new StringBuilder(strb);
            dfs(cur.left, strb);
            strb.deleteCharAt(strb.length() - 1);
        }
        if (cur.right != null) {
            strb.append(1);
            cur.right.id_strb = new StringBuilder(strb);
            dfs(cur.right, strb);
            strb.deleteCharAt(strb.length() - 1);
        }
    }

    private static TreeNode buildTree (int preL, int preR, int inL, int inR) {
        if (preL > preR) {
            return null;
        }
        TreeNode cur = new TreeNode(preOrder_array[preL]);
        int k = 0;
        for (k = inL; k <= inR; k++) {
            if (inOrder_array[k] == preOrder_array[preL]) {
                break;
            }
        }
        int left_nums = k - inL;
        int right_nums = inR - k;
        cur.left = buildTree(preL + 1, preL + left_nums, inL, k - 1);
        cur.right = buildTree(preL + left_nums + 1, preR, k + 1, inR);
        return cur;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        StringBuilder id_strb;
        TreeNode (int val) {
            this.val = val;
        }
    }
}
