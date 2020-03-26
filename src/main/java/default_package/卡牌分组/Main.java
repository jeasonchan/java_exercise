package default_package.卡牌分组;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> table = new HashMap<>();
        int length = deck.length;
        int min = 0;//统计出重复最少的数字

        for (int value : deck) {
            if (table.containsKey(value)) {
                table.put(value, table.get(value) + 1);
            } else {
                table.put(value, 1);
            }

            min = min >= table.get(value) ? table.get(value) : min;
        }

        if (min < 2) {
            return false;
        }

        for (int value : table.values()) {
            if (0 != length % min || 0 != value % min) {
                return false;
            }
        }

        return true;


    }
}
