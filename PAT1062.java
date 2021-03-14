import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PAT1062 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        int N = Integer.parseInt(info[0]);
        int L = Integer.parseInt(info[1]);
        int H = Integer.parseInt(info[2]);
        List<Person> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            int id = Integer.parseInt(line[0]);
            int virtue = Integer.parseInt(line[1]);
            int talent = Integer.parseInt(line[2]);
            Person p = new Person(id, virtue, talent);
            if (virtue >= L && talent >= L) {
                if (virtue >= H && talent >= H) {
                    p.state = 4;
                } else if (virtue >= H && talent < H) {
                    p.state = 3;
                } else if (virtue < H && talent < H) {
                    p.state = 2;
                } else {
                    p.state = 1;
                }
                list.add(p);
            }
        }
        Person[] arr = new Person[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        Arrays.sort(arr, new Comparator2());
        System.out.println(list.size());
        for (Person p : arr) {
            System.out.println(p.id + " " + p.virtue + " " + p.talent);
        }

    }

    private static class Comparator2 implements Comparator<Person> {

        @Override
        public int compare(Person p1, Person p2) {
            if (p1.state != p2.state) {
                return p2.state - p1.state;
            } else {
                if (p1.virtue + p1.talent != p2.virtue + p2.talent) {
                    return p2.virtue + p2.talent - (p1.virtue + p1.talent);
                } else {
                    if (p1.virtue != p2.virtue) {
                        return p2.virtue - p1.virtue;
                    } else {
                        return p1.id - p2.id;
                    }
                }
            }
        }
    }

    private static class Person {
        int id;
        int talent;
        int virtue;
        int state = 0;

        Person(int id, int virtue, int talent) {
            this.id = id;
            this.talent = talent;
            this.virtue = virtue;
        }
    }
}
