package sum;

import org.junit.Test;
import outerApi.Api;

/**
 * @author: changjiale
 * @create: 2019/11/25 22:00
 * @description:
 */
public class Add {


    @Test
    public void test(){
        Api api = new Api();
        api.sum(1, 2);
    }
}
