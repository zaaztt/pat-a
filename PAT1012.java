import java.util.*;

public class PAT1012 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        Map<Integer, Student> map = new HashMap<>();
        List<Student> C_list = new ArrayList<>();
        List<Student> M_list = new ArrayList<>();
        List<Student> E_list = new ArrayList<>();
        List<Student> A_list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int id = sc.nextInt();
            int C = sc.nextInt();
            int M_s = sc.nextInt();
            int E = sc.nextInt();
            Student student = new Student(id, C, M_s, E);
            C_list.add(student);
            M_list.add(student);
            E_list.add(student);
            A_list.add(student);
            map.put(id, student);

        }
        List<Integer> query_list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            query_list.add(sc.nextInt());
        }
        Collections.sort(C_list, new Comp_C());
        Collections.sort(M_list, new Comp_M());
        Collections.sort(E_list, new Comp_E());
        Collections.sort(A_list, new Comp_A());

        for (int i = 0; i < C_list.size(); i++) {
            if (i == 0) {
                C_list.get(i).C_rank = 1;
            } else {
                if (C_list.get(i).C_score == C_list.get(i - 1).C_score) {
                    C_list.get(i).C_rank = C_list.get(i - 1).C_rank;
                } else {
                    C_list.get(i).C_rank = i + 1;
                }
            }
        }

        for (int i = 0; i < M_list.size(); i++) {
            if (i == 0) {
                M_list.get(i).M_rank = 1;
            } else {
                if (M_list.get(i).M_score == M_list.get(i - 1).M_score) {
                    M_list.get(i).M_rank = M_list.get(i - 1).M_rank;
                } else {
                    M_list.get(i).M_rank = i + 1;
                }
            }
        }

        for (int i = 0; i < E_list.size(); i++) {
            if (i == 0) {
                E_list.get(i).E_rank = 1;
            } else {
                if (E_list.get(i).E_score == E_list.get(i - 1).E_score) {
                    E_list.get(i).E_rank = E_list.get(i - 1).E_rank;
                } else {
                    E_list.get(i).E_rank = i + 1;
                }
            }
        }

        for (int i = 0; i < A_list.size(); i++) {
            if (i == 0) {
                A_list.get(i).A_rank = 1;
            } else {
                if (A_list.get(i).A_score == A_list.get(i - 1).A_score) {
                    A_list.get(i).A_rank = A_list.get(i - 1).A_rank;
                } else {
                    A_list.get(i).A_rank = i + 1;
                }
            }
        }

        for (int id : query_list) {
            if (!map.containsKey(id)) {
                System.out.println("N/A");
                continue;
            }
            Student student = map.get(id);
            int max_rank = student.A_rank;
            char max_subject = 'A';

            if (student.C_rank < max_rank) {
                max_rank = student.C_rank;
                max_subject = 'C';
            }
            if (student.M_rank < max_rank) {
                max_rank = student.M_rank;
                max_subject = 'M';
            }
            if (student.E_rank < max_rank) {
                max_rank = student.E_rank;
                max_subject = 'E';
            }


            System.out.println(max_rank + " " + max_subject);
        }

    }

    private static class Comp_C implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o2.C_score - o1.C_score;
        }
    }

    private static class Comp_M implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o2.M_score - o1.M_score;
        }
    }

    private static class Comp_E implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o2.E_score - o1.E_score;
        }
    }

    private static class Comp_A implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o2.A_score - o1.A_score;
        }
    }

    private static class Student {
        int id;
        int C_score;
        int M_score;
        int E_score;
        int A_score;
        int C_rank;
        int M_rank;
        int E_rank;
        int A_rank;
        Student (int id, int C, int M, int E) {
            this.id = id;
            this.C_score = C;
            this.M_score = M;
            this.E_score = E;
            this.A_score = Math.round((C + M + E) / 3);
        }
    }
}
