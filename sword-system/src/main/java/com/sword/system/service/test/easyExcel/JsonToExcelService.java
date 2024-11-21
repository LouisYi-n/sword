package com.sword.system.service.test.easyExcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sword.system.domain.dto.test.easyExcel.ItemDTO;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author louis
 * @version 1.0
 * @date 2024/11/21 14:55
 */
@Service
public class JsonToExcelService {
    public void convertJsonToExcel(String jsonString, String directoryPath, String fileName) throws IOException {
        // 生成唯一的文件名
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        fileName = fileName + "_" + timestamp  + ".xlsx";
        String excelFilePath = directoryPath + File.separator + fileName;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        List<ItemDTO> itemList = new ArrayList<>();

        for (JsonNode itemNode : jsonNode.get("list")) {
            String itemCode = itemNode.get("itemCode").asText();
            String itemName = itemNode.get("itemName").asText();
            String checkType = itemNode.get("checkType").asText();
            for (JsonNode methodNode : itemNode.get("checkMethodInfoList")) {
                String checkMethodCode = methodNode.get("checkMethodCode").asText();
                String checkMethod = methodNode.get("checkMethod").asText();

                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setItemCode(itemCode);
                itemDTO.setItemName(itemName);
                itemDTO.setCheckMethodCode(checkMethodCode);
                itemDTO.setCheckMethod(checkMethod);

                itemList.add(itemDTO);
            }
        }

        ExcelWriterBuilder writerBuilder = EasyExcel.write(excelFilePath, ItemDTO.class);
        ExcelWriterSheetBuilder sheetBuilder = writerBuilder.sheet("Sheet1");
        sheetBuilder.doWrite(itemList);
    }
}
