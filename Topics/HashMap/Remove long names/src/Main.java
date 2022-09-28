import java.util.*;


class MapFunctions {

    public static void removeLongNames(Map<String, String> map) {
        List<String> keys = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().length() > 7 || entry.getValue().length() > 7) {
                keys.add(entry.getKey());
            }
        }
        keys.forEach(map::remove);
    }
}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> map = new HashMap<>();

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] pair = s.split(" ");
            map.put(pair[0], pair[1]);
        }

        MapFunctions.removeLongNames(map);

        System.out.println(map.size());
    }
}