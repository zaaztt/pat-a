import java.util.*;

public class PAT1153 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        Map<Character, List<Person>> type1_map = new HashMap<>();
        Map<Integer, List<Integer>> type2_map = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> type3_map = new HashMap<>();
        Map<String, Person> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String s = sc.next();
            int score = sc.nextInt();
            Person p = new Person(s, score);
            map.put(s, p);
            char level = s.charAt(0);
            int site_number = Integer.valueOf(s.substring(1, 4));
            int date = Integer.valueOf(s.substring(4, 10));
            int testee_number = Integer.valueOf(s.substring(10, 13));

            if (!type1_map.containsKey(level)) {
                type1_map.put(level, new ArrayList<>());
            }
            if (!type2_map.containsKey(site_number)) {
                type2_map.put(site_number, new ArrayList<>());
            }
            if (!type3_map.containsKey(date)) {
                type3_map.put(date, new HashMap<>());
            }

            type1_map.get(level).add(p);
            type2_map.get(site_number).add(score);
            Map<Integer, Integer> type3_helper_map = type3_map.get(date);
            if (!type3_helper_map.containsKey(site_number)) {
                type3_helper_map.put(site_number, 0);
            }
            type3_helper_map.put(site_number, type3_helper_map.get(site_number) + 1);
        }

        List<List<String>> query_list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            List<String> list = new ArrayList<>();
            String n1 = sc.next();
            String n2 = sc.next();
            list.add(n1);
            list.add(n2);
            query_list.add(new ArrayList<>(list));
        }
        int cnt = 0;
        for (List<String> list : query_list) {
            cnt++;
            int type = Integer.valueOf(list.get(0));
            System.out.println("Case " + cnt + ": " + type + " " + list.get(1));
            if (type == 1) {
                if (!type1_map.containsKey(list.get(1).charAt(0))) {
                    System.out.println("NA");
                    continue;
                }
                List<Person> ans_list = type1_map.get(list.get(1).charAt(0));
                Collections.sort(ans_list, new Comp2());
                for (Person p : ans_list) {
                    System.out.println(p.id + " " + p.score);
                }
            } else if (type == 2) {
                int site = Integer.valueOf(list.get(1));
                int sum = 0;
                if (!type2_map.containsKey(site)) {
                    System.out.println("NA");
                    continue;
                }
                List<Integer> temp_list = type2_map.get(site);
                for (int i : temp_list) {
                    sum += i;
                }
                System.out.println(temp_list.size() + " " + sum);
            } else {
                int date = Integer.valueOf(list.get(1));
                if (!type3_map.containsKey(date)) {
                    System.out.println("NA");
                    continue;
                }
                Map<Integer, Integer> t3_h_map = type3_map.get(date);
                List<Site> temp_list = new ArrayList<>();
                for (int key : t3_h_map.keySet()) {
                    temp_list.add(new Site(key, t3_h_map.get(key)));
                }
                Collections.sort(temp_list, new Comp3());
                for (Site st : temp_list) {
                    System.out.println(st.site + " " + st.number);
                }
            }
        }
    }

    private static class Comp3 implements Comparator<Site> {


        @Override
        public int compare(Site o1, Site o2) {
            if (o1.number != o2.number) {
                return o2.number - o1.number;
            } else {
                return o1.site - o2.site;
            }
        }
    }

    private static class Comp2 implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            if (o1.score != o2.score) {
                return o2.score - o1.score;
            } else {
                return o1.id.compareTo(o2.id);
            }
        }
    }

    private static class Site {
        int site;
        int number;
        Site (int site, int number) {
            this.site = site;
            this.number = number;
        }
    }

    private static class Person {
        String id;
        char level;
        int site_number;
        int date;
        int testee_number;
        int score;
        Person (String id, int score) {
            this.id = id;
            level = id.charAt(0);
            site_number = Integer.valueOf(id.substring(1, 4));
            date = Integer.valueOf(id.substring(4, 10));
            testee_number = Integer.valueOf(id.substring(10, 13));
            this.score = score;
        }
    }
}
