import java.util.*;

public class PAT1095 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        Map<String, Car> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
             String plate_number = sc.next();
             String time = sc.next();
             String in_or_out = sc.next();
             if (!map.containsKey(plate_number)) {
                 map.put(plate_number, new Car(plate_number));
             }
             Car car = map.get(plate_number);
             if (in_or_out.equals("in")) {
                 car.in_time.add(time);
             } else {
                 car.out_time.add(time);
             }
        }
        String[] queries = new String[M];
        for (int i = 0; i < M; i++) {
            queries[i] = sc.next();
        }

        int[] counts = new int[M];

        for (String s : map.keySet()) {
            Car car = map.get(s);
            valid_time(car);
        }
        for (int i = 0; i < M; i++) {
            int sum = 0;
            int time = exchange_time(queries[i]);
            for (String s : map.keySet()) {
                Car car = map.get(s);
                for (int j = 0; j < car.valid_exchange_in_time.size(); j++) {
                    if (car.valid_exchange_in_time.get(j) <= time && car.valid_exchange_out_time.get(j) > time) {
                        sum++;
                        break;
                    }
                }
            }
            counts[i] = sum;
        }
        int max_time = 0;
        List<Car> max_time_cars = new ArrayList<>();
        for (String s : map.keySet()) {
            Car car = map.get(s);
            for (int i = 0; i < car.valid_exchange_in_time.size(); i++) {
                car.total_stay_time += car.valid_exchange_out_time.get(i) - car.valid_exchange_in_time.get(i);
            }
            if (car.total_stay_time > max_time) {
                max_time_cars.clear();
                max_time = car.total_stay_time;
                max_time_cars.add(car);
            } else if (car.total_stay_time == max_time) {
                max_time_cars.add(car);
            }
        }
        for (int i : counts) {
            System.out.println(i);
        }
        Collections.sort(max_time_cars, new Comparator_Car());
        for (Car car : max_time_cars) {
            System.out.print(car.plate_number + " ");
        }
        System.out.print(exchange_time(max_time));
    }

    private static void valid_time (Car car) {
        List<Integer> ex_in_time = new ArrayList<>();
        List<Integer> ex_out_time = new ArrayList<>();
        List<Integer> valid_in_time = new ArrayList<>();
        List<Integer> valid_out_time = new ArrayList<>();
        for (String s : car.in_time) {
            ex_in_time.add(exchange_time(s));
        }
        for (String s : car.out_time) {
            ex_out_time.add(exchange_time(s));
        }
        Collections.sort(ex_in_time);
        Collections.sort(ex_out_time);
        int temp_out = 0;
        for (int i = 0; i < ex_in_time.size(); i++) {
            if (ex_in_time.get(i) < ex_out_time.get(temp_out)) {
                if (i == ex_in_time.size() - 1) {
                    valid_in_time.add(ex_in_time.get(i));
                    valid_out_time.add(ex_out_time.get(temp_out));
                    temp_out++;
                } else if (ex_in_time.get(i + 1) < ex_out_time.get(temp_out)) {
                    while (i < ex_in_time.size() - 1 && ex_in_time.get(i + 1) < ex_out_time.get(temp_out)) {
                        i++;
                    }
                    valid_in_time.add(ex_in_time.get(i));
                    valid_out_time.add(ex_out_time.get(temp_out));
                    temp_out++;
                } else {
                    valid_in_time.add(ex_in_time.get(i));
                    valid_out_time.add(ex_out_time.get(temp_out));
                    temp_out++;
                }
            } else {
                temp_out++;
                i--;
            }
            if (temp_out >= ex_out_time.size()) {
                break;
            }
        }
        car.valid_exchange_in_time = new ArrayList<>(valid_in_time);
        car.valid_exchange_out_time = new ArrayList<>(valid_out_time);

    }

    private static class Comparator_Car implements Comparator<Car> {

        @Override
        public int compare(Car o1, Car o2) {
            return o1.plate_number.compareTo(o2.plate_number);
        }
    }

    private static int exchange_time (String time) {
        String[] spl = time.split(":");
        int hour = Integer.valueOf(spl[0]);
        int min = Integer.valueOf(spl[1]);
        int sec = Integer.valueOf(spl[2]);
        return hour * 3600 + min * 60 + sec;
    }

    private static String exchange_time (int time) {
        int hour = time / 3600;
        time = time % 3600;
        int min = time / 60;
        time = time % 60;
        int sec = time;
        String s = String.format("%02d", hour) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec);
        return s;
    }

    private static class Car {
        String plate_number;
        List<String> in_time;
        List<String> out_time;
        List<Integer> valid_exchange_in_time;
        List<Integer> valid_exchange_out_time;
        int total_stay_time = 0;
        Car (String plate_number) {
            this.plate_number = plate_number;
            in_time = new ArrayList<>();
            out_time = new ArrayList<>();
        }
    }
}
