package com.leyou.upload.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exceptions.CustomException;
import com.leyou.upload.web.UploadController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {

    @Value("${leyou.image.upload.dir}")
    private String uploadDir;

    @Value("${leyou.image.upload.domain}")
    private String uploadDomain;

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    private static final List<String> suffixes = Arrays.asList("image/png", "image/jpeg");

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    public String uploadImage(MultipartFile file) {

        try {
            // 1.图片信息效验
            // 2.效验文件类型
            String type = file.getContentType();
            if (!suffixes.contains(type)) {
                logger.info("上传失败,文件类型不匹配:{}", type);
                throw new CustomException(ExceptionEnum.IMAGE_UPLOAD_INVALID_TYPE);
            }
            // 2.效验图片内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                logger.info("上传失败,文件内容不符合要求");
                throw new CustomException(ExceptionEnum.IMAGE_UPLOAD_INVALID_CONTENT);
            }
            // 3.生成图片保存目录
            File dir = new File(this.uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // 4.保存图片
            file.transferTo(new File(dir, file.getOriginalFilename()));
            // 5.返回图片地址
            String url = this.uploadDomain + file.getOriginalFilename();

            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
