
package com.TripMap.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

public class SaveImageUtil {
    /**
     * @function 将Image转换为BufferedImage
     * @param img Image对象
     * @return BufferedImage对象
     */
    public static BufferedImage convertToBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img; // 如果已经是BufferedImage，直接返回
        }

        // 创建一个新的BufferedImage
        BufferedImage bImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // 使用Graphics将Image绘制到BufferedImage中
        Graphics g = bImage.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose(); // 释放Graphics对象

        return bImage;
    }

    /**
     * @function 将BufferedImage保存为文件
     * @param image BufferedImage对象
     * @param filePath 文件路径
     */
    public static void saveImageToFile(BufferedImage image, String filePath) {
        try {
            File outputFile = new File(filePath);
            ImageIO.write(image, "png", outputFile); // 保存为PNG格式
            System.out.println("图像已成功保存到 " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @function 将MultipartFile转换为BufferedImage
     * @param file MultipartFile对象
     * @return BufferedImage对象
     * @throws IOException
     */
    public static BufferedImage convertMultipartFileToBufferedImage(MultipartFile file) throws IOException {
    // 将MultipartFile转换为BufferedImage
        return ImageIO.read(file.getInputStream());
    }
}
