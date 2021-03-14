import jdk.nashorn.internal.ir.BaseNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PAT1080 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split(" ");
        int N = Integer.valueOf(line1[0]);
        int M = Integer.valueOf(line1[1]);
        int K = Integer.valueOf(line1[2]);
        String[] line2 = br.readLine().split(" ");
        School[] schools = new School[M];
        for (int i = 0; i < M; i++) {
            schools[i] = new School(i, Integer.valueOf(line2[i]));
        }
        Student[] students = new Student[N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            int GE = Integer.valueOf(line[0]);
            int GI = Integer.valueOf(line[1]);
            students[i] = new Student(i, GE, GI, K);
            for (int j = 0; j < K; j++) {
                students[i].choices[j] = Integer.valueOf(line[j + 2]);
            }
        }
        Arrays.sort(students, new Comparator2());
        for (int i = 0; i < N; i++) {
            if (i == 0) {
                students[i].rank = 1;
            } else if (students[i].total == students[i - 1].total && students[i].GE == students[i - 1].GE){
                students[i].rank = students[i - 1].rank;
            } else {
                students[i].rank = i + 1;
            }
            for (int choice : students[i].choices) {
                if (i == 0 || schools[choice].quota > schools[choice].ads.size() || schools[choice].ads.get(schools[choice].ads.size() - 1).rank == students[i].rank ) {
                    schools[choice].ads.add(students[i]);
                    break;
                }
            }
        }
        for (School school : schools) {
            Collections.sort(school.ads, new Comparator3());
            StringBuilder strb = new StringBuilder();
            for (Student student : school.ads) {
                strb.append(student.id);
                strb.append(" ");
            }
            if (strb.length() > 0) {
                strb.deleteCharAt(strb.length() - 1);
                System.out.print(strb);
            }
            System.out.println("");
        }
    }

    private static class Comparator2 implements Comparator<Student> {

        @Override
        public int compare(Student s1, Student s2) {
            if (s2.total != s1.total) {
                return s2.total - s1.total;
            } else {
                return s2.GE - s1.GE;
            }

        }
    }

    private static class Comparator3 implements Comparator<Student> {

        @Override
        public int compare(Student s1, Student s2) {
            return s1.id - s2.id;

        }
    }

    private static class School {
        int id;
        int quota;
        List<Student> ads;
        School (int id, int quota) {
            this.id = id;
            this.quota = quota;
            ads = new ArrayList<>();
        }
    }

    private static class Student {
        int id;
        int GE;
        int GI;
        int total;
        int[] choices;
        int rank;

        Student (int id, int GE, int GI, int K) {
            this.id = id;
            this.GE = GE;
            this.GI = GI;
            this.total = GE + GI;
            choices = new int[K];
        }
    }

}
