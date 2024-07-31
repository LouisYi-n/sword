package com.icarus.sword.core.page;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class PageRequest  implements Serializable, Cloneable {
    private int pageNo = 1;
    private int pageSize = 20;

    private boolean isShowAll = false;

    private Map<String, String[]> criterias = new HashMap<String, String[]>();

    private List<String> orderItems = null;
    private boolean isDesc = true;
}
