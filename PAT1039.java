import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PAT1039 {
    static Map<String, Student> map = new HashMap<>();
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        int N = Integer.parseInt(info[0]);
        int K = Integer.parseInt(info[1]);
        for (int i = 0; i < K; i++) {
            String[] spl1 = br.readLine().split(" ");
            String[] spl2 = br.readLine().split(" ");
            int course_id = Integer.parseInt(spl1[0]);
            int course_stus = Integer.parseInt(spl1[1]);
            for (int j = 0; j < course_stus; j++) {
                if (!map.containsKey(spl2[j])) map.put(spl2[j], new Student(spl2[j]));
                map.get(spl2[j]).courses.add(course_id);
            }
        }
        String[] students = br.readLine().split(" ");
        for (String s : students) {
            if (!map.containsKey(s)) {
                System.out.println(s + " " + 0);
            } else {
                Collections.sort(map.get(s).courses);
                System.out.print(s + " " + map.get(s).courses.size());
                for (int i : map.get(s).courses) {
                    System.out.print(" " + i);
                }
                System.out.println();
            }
        }
    }

    private static class Student {
        String name;
        List<Integer> courses;

        Student (String name) {
            this.name = name;
            courses = new ArrayList<>();
        }
    }
}
