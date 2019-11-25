package outerApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: changjiale
 * @create: 2019/11/25 17:34
 * @description:
 */
@RestController
public class Arithmetic {

    @GetMapping
    public void sum(Integer a, Integer b) {
        System.out.println("sum: " + (a+b));
    }

}