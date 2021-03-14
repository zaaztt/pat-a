import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PAT1061 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line1 = br.readLine();
        String line2 = br.readLine();
        String line3 = br.readLine();
        String line4 = br.readLine();
        int week = 0;
        int hour = 0;
        int len1 = Math.min(line1.length(), line2.length());
        int len2 = Math.min(line3.length(), line4.length());
        int count = 0;
        for (int i = 0; i < len1; i++) {
            char c1 = line1.charAt(i);
            char c2 = line2.charAt(i);
            if (c1 == c2 && count == 0 && isUpperCharAG(c1) && isUpperCharAG(c2)) {
                week = line1.charAt(i) - 'A' + 1;
                count++;
                continue;
            }
            if (c1 == c2 && count == 1 && (isUpperCharAN(c1) || isNum(c1)) && (isUpperCharAN(c2) || isNum(c2))) {
                if (isNum(c1)) {
                    hour = line1.charAt(i) - '0';
                } else {
                    hour = line1.charAt(i) - 'A' + 10;
                }
                break;
            }
        }
        int minute = 0;
        for (int i = 0; i < len2; i++) {
            char c3 = line3.charAt(i);
            char c4 = line4.charAt(i);


            if ( c3 == c4 && (isLowerChar(c3) || isUpperChar(c3)) && (isLowerChar(c4) || isUpperChar(c4))) {
                minute = i;
            }
        }

        System.out.println(weekEx(week) + " " + hour + ":" + String.format("%02d", minute));


    }

    private static boolean isNum (char c) {
        if (c - '0' >= 0 && c - '0' <= 9) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isLowerChar (char c) {
        if (c - 'a' >= 0 && c - 'a' <= 25) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isUpperCharAG (char c) {
        if (c - 'A' >= 0 && 'G' - c >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isUpperChar (char c) {
        if (c - 'A' >= 0 && 'Z' - c >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isUpperCharAN (char c) {
        if (c - 'A' >= 0 && 'N' - c >= 0) {
            return true;
        } else {
            return false;
        }
    }



    private static String weekEx (int i) {
        if (i == 1) {
            return "MON";
        }
        if (i == 2) {
            return "TUE";
        }
        if (i == 3) {
            return "WED";
        }
        if (i == 4) {
            return "THU";
        }
        if (i == 5) {
            return "FRI";
        }
        if (i == 6) {
            return "SAT";
        }
        if (i == 7) {
            return "SUN";
        }
        return null;
    }
}
