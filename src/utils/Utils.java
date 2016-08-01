package utils;

import java.util.ArrayList;

/**
 * Created by cadebryant on 7/31/16.
 */
public class Utils {
    public static String listToString(ArrayList<String> list, String delimiter) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int len = list.size();
        for (; i < len; i++) {
            String str = list.get(i);
            if (i == len - 1) {
                sb.append(str);
            } else {
                sb.append(str).append(delimiter);
            }
        }
        return sb.toString();
    }

    public static String removePunctuation(String input) {
        return input
                .replace(".", "")
                .replace(",", "")
                .replace("?", "")
                .replace("!", "")
                .replaceAll("\\s+", " ");
    }
}
