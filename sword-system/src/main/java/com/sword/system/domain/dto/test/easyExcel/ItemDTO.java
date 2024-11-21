package com.sword.system.domain.dto.test.easyExcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author louis
 * @version 1.0
 * @date 2024/11/21 14:50
 */
@Data
public class ItemDTO {

    @ExcelProperty("Item Code")
    private String itemCode;
    @ExcelProperty("Item Name")
    private String itemName;
    @ExcelProperty("Check Type")
    private String checkType;
    @ExcelProperty("Check Method Code")
    private String checkMethodCode;
    @ExcelProperty("Check Method")
    private String checkMethod;
}
