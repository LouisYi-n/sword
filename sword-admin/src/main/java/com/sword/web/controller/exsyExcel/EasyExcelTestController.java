package com.sword.web.controller.exsyExcel;

import com.sword.system.service.test.easyExcel.JsonToExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author louis
 * @version 1.0
 * @date 2024/11/21 14:47
 */
@RestController
@RequestMapping("/test/exsyExcel")
public class EasyExcelTestController {

    @Autowired
    private JsonToExcelService jsonToExcelService;

    @PostMapping("/convert")
    public String convertJsonToExcel(@RequestBody String jsonString, @RequestParam String directoryPath,  @RequestParam String fileName) {
        try {
            jsonToExcelService.convertJsonToExcel(jsonString, directoryPath, fileName);
            return "Conversion successful. Excel file saved at " + directoryPath;
        } catch (IOException e) {
            return "Conversion failed: " + e.getMessage();
        }
    }
}
