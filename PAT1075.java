import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class PAT1075 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split(" ");
        int N = Integer.parseInt(line1[0]);
        int N_pro = Integer.parseInt(line1[1]);
        int N_que = Integer.parseInt(line1[2]);
        String[] line2 = br.readLine().split(" ");
        int[] total = new int[N_pro + 1];
        for (int i = 1; i <= N_pro; i++) {
            total[i] = Integer.parseInt(line2[i - 1]);
        }
        Student[] students = new Student[N + 1];
        for (int i = 0; i <= N; i++) {
            students[i] = new Student(i, N_pro + 1);
        }
        for (int i = 0; i < N_que; i++) {
            String[] line = br.readLine().split(" ");
            int id = Integer.parseInt(line[0]);
            int pro = Integer.parseInt(line[1]);
            int score = Integer.parseInt(line[2]);
            if (score == -1) {
                score = 0;
            } else {
                students[id].pro_ack[pro] = 1;
            }
            if (students[id].scores[pro] < score) {
                students[id].total_score += score - students[id].scores[pro];
                students[id].scores[pro] = score;
                if (score == total[pro]) {
                    students[id].perfect++;
                }
            }
        }

        Arrays.sort(students, new Comparator2());
        int temp = 1;
        for (Student s : students) {
            if (s.total_score == 0) {
                continue;
            }
            StringBuilder strb = new StringBuilder();
            if (temp == 1 || s.total_score < students[temp - 2].total_score) {
                strb.append(temp);
                s.rank = temp;
            } else {
                strb.append(students[temp - 2].rank);
                s.rank = students[temp - 2].rank;
            }
            strb.append(" ");
            strb.append(String.format("%05d", s.id));
            strb.append(" ");
            strb.append(s.total_score);
            for (int i = 1; i <= N_pro; i++) {
                strb.append(" ");
                if (s.pro_ack[i] != 0) {
                    strb.append(s.scores[i]);
                } else {
                    strb.append("-");
                }
            }
            temp++;
            System.out.println(strb);
        }

    }

    private static class Student {
        int id;
        int[] scores;
        int[] pro_ack;
        int total_score;
        int perfect;
        int rank;

        Student(int id, int n) {
            this.id = id;
            scores = new int[n];
            pro_ack = new int[n];
            total_score = 0;
            perfect = 0;
        }
    }

    private static class Comparator2 implements Comparator<Student> {

        @Override
        public int compare(Student s1, Student s2) {
            if (s1.total_score != s2.total_score) {
                return s2.total_score - s1.total_score;
            } else {
                if (s1.perfect != s2.perfect) {
                    return s2.perfect - s1.perfect;
                } else {
                    return s1.id - s2.id;
                }
            }
        }
    }
}
