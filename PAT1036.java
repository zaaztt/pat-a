import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PAT1036 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Student[] students = new Student[n];
        for (int i = 0; i < n; i++) {
            String[] spl = br.readLine().split(" ");
            students[i] = new Student(spl[0], spl[1], spl[2], Integer.parseInt(spl[3]));
        }
        List<Student> students_m = new ArrayList<>();
        List<Student> students_f = new ArrayList<>();
        for (Student s : students) {
            if (s.sex.equals("M")) {
                students_m.add(s);
            } else {
                students_f.add(s);
            }
        }
        Collections.sort(students_f, new ComparatorF());
        Collections.sort(students_m, new ComparatorM());
        if (students_f.size() == 0) System.out.println("Absent");
        else System.out.println(students_f.get(0).name + " " + students_f.get(0).course_id);
        if (students_m.size() == 0) System.out.println("Absent");
        else System.out.println(students_m.get(0).name + " " + students_m.get(0).course_id);
        if (students_f.size() == 0 || students_m.size() == 0) System.out.println("NA");
        else System.out.println(students_f.get(0).score - students_m.get(0).score);
    }

    private static class Student {
        String name;
        String sex;
        String course_id;
        int score;

        Student (String name, String sex, String course_id, int score) {
            this.name = name;
            this.sex = sex;
            this.course_id = course_id;
            this.score = score;
        }
    }

    private static class ComparatorM implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.score - o2.score;
        }
    }

    private static class ComparatorF implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o2.score - o1.score;
        }
    }
}
