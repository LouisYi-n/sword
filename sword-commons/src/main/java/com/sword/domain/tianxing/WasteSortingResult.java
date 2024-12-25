package com.sword.domain.tianxing;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author louis
 * @version 1.0
 * @date 2024/12/24 17:59
 */
@Data
public class WasteSortingResult  implements Serializable {
    List<WasteSortingDetails> list;

}
