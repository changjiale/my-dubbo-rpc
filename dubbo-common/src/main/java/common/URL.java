package common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author: changjiale
 * @create: 2019/12/05 11:16
 * @description:
 */
@Data
@AllArgsConstructor
public class URL implements Serializable {

    private String hostname;
    private Integer port;

}

