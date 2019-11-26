package sum;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import outerApi.Arithmetic;

/**
 * @author: changjiale
 * @create: 2019/11/25 22:00
 * @description:
 */
public class Add {


    @Test
    public void test(){
        Arithmetic arithmetic = new Arithmetic();
        arithmetic.sum(1, 2);
    }
}
