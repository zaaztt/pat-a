import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PAT1055 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info1 = br.readLine().split(" ");
        int N_person = Integer.parseInt(info1[0]);
        int N_query = Integer.parseInt(info1[1]);
        Person[] people = new Person[N_person];
        for (int i = 0; i < N_person; i++) {
            String[] info2 = br.readLine().split(" ");
            String name = info2[0];
            int age = Integer.parseInt(info2[1]);
            int worth = Integer.parseInt(info2[2]);
            people[i] = new Person(name, age, worth);
        }
        String[][] queries = new String[N_query][];
        for (int i = 0; i < N_query; i++) {
            queries[i] = br.readLine().split(" ");
        }
        for (int i = 0; i < N_query; i++) {
            String[] q = queries[i];
            int capacity = Integer.parseInt(q[0]);
            int min_age = Integer.parseInt(q[1]);
            int max_age = Integer.parseInt(q[2]);
            PriorityQueue<Person> pq = new PriorityQueue<>(new Comparator2());
            for (Person p : people) {
                if (p.age <= max_age && p.age >= min_age) {
                    pq.add(p);
                }
            }
            System.out.println("Case #" + (i + 1) + ":");
            if (pq.size() == 0) {
                System.out.println("None");
            } else {

                for (int j = 0; j < capacity; j++) {
                    Person cur = pq.remove();
                    System.out.println(cur.name + " " + cur.age + " " + cur.worth);
                    if (pq.size() == 0) break;
                }
            }
        }
    }

    private static class Person {
        String name;
        int age;
        int worth;
        Person (String name, int age, int worth) {
            this.name = name;
            this.age = age;
            this.worth = worth;
        }
    }

    private static class Comparator2 implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            if (o1.worth != o2.worth) {
                return o2.worth - o1.worth;
            } else {
                if (o1.age != o2.age) {
                    return o1.age - o2.age;
                } else {
                    return o1.name.compareTo(o2.name);
                }
            }
        }
    }
}
