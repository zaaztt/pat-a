import java.io.IOException;
import java.util.*;

public class PAT1083 {
    public static void main (String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Student[] students = new Student[N];
        PriorityQueue<Student> pq = new PriorityQueue<>(new Comparator2());
        for (int i = 0; i < N; i++) {
            String name = sc.next();
            String id = sc.next();
            int grade = sc.nextInt();
            students[i] = new Student(name, id, grade);
        }
        int bound_min = sc.nextInt();
        int bound_max = sc.nextInt();
        for (Student s : students) {
            if (s.grade >= bound_min && s.grade <= bound_max) {
                pq.add(s);
            }
        }
        char c;
        if (pq.size() == 0) {
            System.out.println("NONE");
        } else {
            while (!pq.isEmpty()) {
                Student temp = pq.poll();
                System.out.println(temp.name + " " + temp.id);
            }
        }
    }

    private static class Student {
        String name;
        String id;
        int grade;

        Student(String name, String id, int grade) {
            this.name = name;
            this.id = id;
            this.grade = grade;
        }
    }

    private static class Comparator2 implements Comparator<Student> {

        @Override
        public int compare(Student s1, Student s2) {
            return s2.grade - s1.grade;
        }
    }
}
