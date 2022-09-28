import java.util.*;


class MapFunctions {

    public static void calcTheSamePairs(Map<String, String> map1, Map<String, String> map2) {
        int counter = 0;
        for (Map.Entry<String, String> entry1 : map1.entrySet()) {
            for (Map.Entry<String, String> entry2 : map2.entrySet()) {
                if (entry1.equals(entry2)) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }
}
