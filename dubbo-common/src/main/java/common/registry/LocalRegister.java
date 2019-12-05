package common.registry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: changjiale
 * @create: 2019/12/05 21:42
 * @description:
 */
public class LocalRegister {

    private static Map<String, Class> map = new HashMap<>();

    public static void regist(String interfaceName, Class implClass) {
        map.put(interfaceName, implClass);
    }

    public static Class get(String interfaceName) {
        return map.get(interfaceName);
    }
}
