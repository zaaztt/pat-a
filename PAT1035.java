import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PAT1035 {
    static List<User> revised = new ArrayList<>();

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        User[] users = new User[n];
        for (int i = 0; i < n; i++) {
            String[] spl = br.readLine().split(" ");
            users[i] = new User(spl[0], spl[1]);
        }

        for (User user : users) {
            boolean needRevised = false;
            char[] pwd = user.password.toCharArray();
            for (int i = 0; i < pwd.length; i++) {
                if (pwd[i] == '1') {
                    pwd[i] = '@';
                    needRevised = true;
                }
                if (pwd[i] == '0') {
                    pwd[i] = '%';
                    needRevised = true;
                }
                if (pwd[i] == 'l') {
                    pwd[i] = 'L';
                    needRevised = true;
                }
                if (pwd[i] == 'O') {
                    pwd[i] = 'o';
                    needRevised = true;
                }
            }
            if (needRevised) {
                StringBuilder strb = new StringBuilder();
                for (char c : pwd) strb.append(c);
                user.password = strb.toString();
                revised.add(user);
            }
        }
        if (revised.size() > 0) {
            System.out.println(revised.size());
            for (User user : revised) {
                System.out.println(user.account + " " + user.password);
            }
        } else {
            if (users.length == 1) System.out.println("There is 1 account and no account is modified");
            else System.out.println("There is " + users.length + " accounts and no account is modified");
        }

    }

    private static class User {
        String account;
        String password;

        User (String account, String password) {
            this.account = account;
            this.password = password;
        }
    }

}
