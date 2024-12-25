package com.sword.domain.tianxing;

import java.io.Serializable;
import lombok.Data;

/**
 * @author louis
 * @version 1.0
 * @date 2024/12/24 18:18
 */
@Data
public class WasteSortingDetails  implements Serializable {
    String name;
    Integer type;
    String aipre;
    String explain;
    String contain;
    String tip;
}
