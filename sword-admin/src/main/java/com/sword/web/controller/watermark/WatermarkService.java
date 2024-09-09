package com.sword.web.controller.watermark;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class WatermarkService {

    @Value("${font.path}")
    private String fontPath;

    @Value("${output.directory}")
    private String outputDirectory;


    public void addWatermarkForPDF(MultipartFile file, WaterMarkInfo waterMarkInfo) throws Exception {
        if (waterMarkInfo.getType().equals(WatermarkTypeEnum.TEXT)) {
            if (waterMarkInfo.getForm().equals(WatermarkFormEnum.SINGLE)) {
                addWatermarkTextSingle(file, waterMarkInfo);
            } else {
                addWatermarkTextMultiple(file, waterMarkInfo);
            }
        } else {
            addWatermarkImg(file, waterMarkInfo);
        }
    }


    /**
     * 添加水印文字 文字不重复
     *
     * @param waterMarkInfo
     */
    public void addWatermarkTextSingle(MultipartFile file, WaterMarkInfo waterMarkInfo) throws Exception {
        PDDocument document = PDDocument.load(file.getInputStream());
        File fontFile = getFontFile();
        PDType0Font font = PDType0Font.load(document, Files.newInputStream(fontFile.toPath()));
        for (PDPage page : document.getPages()) {
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
            // 设置透明度
            contentStream.setGraphicsStateParameters(createGraphicsState(waterMarkInfo.getOpacity()));
            contentStream.setFont(font, waterMarkInfo.getFontSize());
            // 获取页面尺寸
            float pageWidth = page.getMediaBox().getWidth();
            float pageHeight = page.getMediaBox().getHeight();
            // 计算水印文本的宽度和高度
            float watermarkWidth = font.getStringWidth(waterMarkInfo.getText()) / 1000 * waterMarkInfo.getFontSize();
            float watermarkHeight = font.getFontDescriptor().getCapHeight() / 1000 * waterMarkInfo.getFontSize();
            // 计算水印文本的起始位置，使其居中
            float xPosition = (pageWidth - watermarkWidth) / 2;
            float yPosition = (pageHeight - watermarkHeight) / 2;
            float rotationAngle = Float.parseFloat(waterMarkInfo.getRotation());
            // 应用旋转矩阵
            Matrix matrix = Matrix.getRotateInstance(Math.toRadians(rotationAngle), xPosition + watermarkWidth / 2, yPosition + watermarkHeight / 2);

            String colorHex = waterMarkInfo.getColourRgb().replace("#", "");
            int color = Integer.parseInt(colorHex, 16);
            Color awtColor = new Color(color);
            contentStream.setNonStrokingColor(awtColor);
            // 设置文本参数并绘制文本
            contentStream.beginText();
            contentStream.setTextMatrix(matrix);
            contentStream.newLineAtOffset(-watermarkWidth / 2, -watermarkHeight / 2);
            contentStream.showText(waterMarkInfo.getText());
            contentStream.endText();
            contentStream.close(); // do this before saving!
        }

        // 生成唯一文件名并保存带有水印的 PDF 文件
        String uniqueFileName = UUID.randomUUID().toString() + ".pdf";
        File outputDir = new File(outputDirectory);
        if (!outputDir.exists()) {
            throw new EOFException("输出目录不存在");
        }

        String outputFilePath = outputDir.getAbsolutePath() + File.separator + uniqueFileName;
        document.save(outputFilePath);
        document.close();
    }

    /**
     * 添加水印文字 文字重复
     *
     * @param waterMarkInfo
     */
    public void addWatermarkTextMultiple(MultipartFile file, WaterMarkInfo waterMarkInfo) throws Exception {
        PDDocument document = PDDocument.load(file.getInputStream());
        File fontFile = getFontFile();
        PDType0Font font = PDType0Font.load(document, Files.newInputStream(fontFile.toPath()));
        for (PDPage page : document.getPages()) {
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {
                contentStream.setFont(font, waterMarkInfo.getFontSize());
                String colorHex = waterMarkInfo.getColourRgb().replace("#", "");
                int color = Integer.parseInt(colorHex, 16);
                Color awtColor = new Color(color);
                contentStream.setNonStrokingColor(awtColor);
                contentStream.setGraphicsStateParameters(createGraphicsState(waterMarkInfo.getOpacity()));

                float pageWidth = page.getMediaBox().getWidth();
                float pageHeight = page.getMediaBox().getHeight();

                // 计算水印文本的宽度和高度
                float stringWidth = font.getStringWidth(waterMarkInfo.getText()) / 1000 * waterMarkInfo.getFontSize();
                float stringHeight = font.getFontDescriptor().getCapHeight() / 1000 * waterMarkInfo.getFontSize();

                // 逐行添加水印文本
                /**
                 * 步长的作用是控制水印文本在页面上的排列间距。步长决定了每一行和每一列水印文本之间的距离。
                 * 合理设置步长可以确保水印文本均匀地覆盖整个页面，而不会有太多的重叠或过大的间隙
                 */
                // 计算旋转后的文本对角线长度
                float diagonal = (float) Math.sqrt(stringWidth * stringWidth + stringHeight * stringHeight);

                float stepY = diagonal + 20;  // 计算步长，使水印文本之间有适当间隔
                float stepX = diagonal + 0;  // 计算步长，使水印文本之间有适当间隔
                for (float y = -pageHeight; y < pageHeight * 2; y += stepY) {
                    for (float x = -pageWidth; x < pageWidth * 2; x += stepX) {
                        contentStream.saveGraphicsState();
                        contentStream.beginText();
                        // 创建旋转变换矩阵
                        Matrix transform = Matrix.getRotateInstance(Math.toRadians(Float.parseFloat(waterMarkInfo.getRotation())), x, y);
                        contentStream.setTextMatrix(transform);
                        // 由于已经设置了旋转变换，这里使用 (0, 0)
                        contentStream.newLineAtOffset(0, 0);
                        contentStream.showText(waterMarkInfo.getText());
                        contentStream.endText();
                    }
                }
            }
        }

        // 生成唯一文件名并保存带有水印的 PDF 文件
        String uniqueFileName = UUID.randomUUID().toString() + ".pdf";
        File outputDir = new File(outputDirectory);
        if (!outputDir.exists()) {
            throw new EOFException("输出目录不存在");
        }

        String outputFilePath = outputDir.getAbsolutePath() + File.separator + uniqueFileName;
        document.save(outputFilePath);
        document.close();
    }

    public void addWatermarkImg(MultipartFile file, WaterMarkInfo waterMarkInfo) throws Exception {
        String imageUrl = "https://img1.baidu.com/it/u=2945251565,68484224&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=515";
        PDDocument document = PDDocument.load(file.getInputStream());
        // 从URL获取图片
        PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, toByteArray(new URL(imageUrl).openStream()), "image");

        File fontFile = getFontFile();
        PDType0Font font = PDType0Font.load(document, Files.newInputStream(fontFile.toPath()));
        for (PDPage page : document.getPages()) {
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true)) {
                contentStream.setFont(font, waterMarkInfo.getFontSize());
                String colorHex = waterMarkInfo.getColourRgb().replace("#", "");
                int color = Integer.parseInt(colorHex, 16);
                Color awtColor = new Color(color);
                contentStream.setNonStrokingColor(awtColor);
                contentStream.setGraphicsStateParameters(createGraphicsState(waterMarkInfo.getOpacity()));

                float pageWidth = page.getMediaBox().getWidth();
                float pageHeight = page.getMediaBox().getHeight();

                float imageWidth = pdImage.getWidth() * waterMarkInfo.getScale();
                float imageHeight = pdImage.getHeight() * waterMarkInfo.getScale();

                // 计算图片覆盖范围
                float stepX = imageWidth * 1.5f;
                float stepY = imageHeight * 1.5f;

                // 在每页上添加图片水印
                for (float y = -pageHeight; y < pageHeight * 2; y += stepY) {
                    for (float x = -pageWidth; x < pageWidth * 2; x += stepX) {
                        contentStream.saveGraphicsState();
                        contentStream.transform(Matrix.getRotateInstance(Math.toRadians(Float.parseFloat(waterMarkInfo.getRotation())), x + imageWidth / 2, y + imageHeight / 2));
                        // 设置图片位置和尺寸
                        contentStream.drawImage(pdImage, x, y, imageWidth, imageHeight);
                        contentStream.restoreGraphicsState();

                    }
                }
            }
        }
        // 生成唯一文件名并保存带有水印的 PDF 文件
        String uniqueFileName = UUID.randomUUID().toString() + ".pdf";
        File outputDir = new File(outputDirectory);
        if (!outputDir.exists()) {
            throw new EOFException("输出目录不存在");
        }

        String outputFilePath = outputDir.getAbsolutePath() + File.separator + uniqueFileName;
        document.save(outputFilePath);
        document.close();
    }


    private File getFontFile() throws Exception {
        boolean isLocally = System.getProperty("os.name").toLowerCase().contains("window");
        if (isLocally) {
            File fontDir = new File(fontPath);
            if (!fontDir.exists() || !fontDir.isDirectory()) {
                throw new Exception("字体文件夹不存在");
            }
            File[] fontFiles = fontDir.listFiles();
            assert fontFiles != null;
            for (File fontFile : fontFiles) {
                if (fontFile.isFile()) {
                    return fontFile;
                }
            }
        }
        return new File(fontPath);
    }

    private static PDExtendedGraphicsState createGraphicsState(float opacity) {
        PDExtendedGraphicsState graphicsState = new PDExtendedGraphicsState();
        graphicsState.setNonStrokingAlphaConstant(opacity);
        return graphicsState;
    }

    private static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        return buffer.toByteArray();
    }
}

