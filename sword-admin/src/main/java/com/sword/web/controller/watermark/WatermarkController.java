package com.sword.web.controller.watermark;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("watermark")
public class WatermarkController {


    @Autowired
    WatermarkService watermarkService;

    @PostMapping("doc")
    public void doc() {

    }

    @PostMapping("/pdf")
    public void addToPDF(@RequestPart("file") MultipartFile file, @RequestPart("waterMarkInfo") WaterMarkInfo waterMarkInfo) throws Exception {
        log.info("Received file: {}", file.getOriginalFilename());
        log.info("Received WaterMarkInfo: {}", waterMarkInfo);
        watermarkService.addWatermarkForPDF(file, waterMarkInfo);
    }


}
