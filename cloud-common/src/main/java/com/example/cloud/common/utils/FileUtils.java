package com.example.cloud.common.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import static org.apache.commons.io.FileUtils.readFileToByteArray;

/**
 * @Author candy33
 * @Date 2022/1/18 10:59
 * @ClassName com.example.cloud.common.utils
 **/
@Component
@Slf4j
public class FileUtils {

    public static final FileUtils INSTANCE = new FileUtils();

    private FileUtils() {
        super();
    }



    /**
     * 判断文件是否存在
     * @param path 文件路径
     * @param fileName 文件名
     * @return 是否存在，存在true，不存在false
     */
    public boolean existsFile(String path, String fileName) {
        File file = new File(path + fileName);
        return file.exists();
    }

    /**
     * 图片转base64
     *
     * @param imgFilePath
     * @return
     */
    public static String imageToBase64(final String imgFilePath) throws IOException {
        byte[] src = readFileToByteArray(new File(imgFilePath));
        return Base64Utils.encodeToString(src);
    }

    /**
     * 将MultipartFile 图片文件编码为base64
     * @param fileImage
     * @return
     */
    public static String generateBase64(MultipartFile fileImage){
        if (fileImage == null) {
            return "";
        }
        String base64EncoderImg = "";
        byte[] buffer;
        try {
            InputStream inputStream = fileImage.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = inputStream.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            inputStream.close();
            bos.close();
            buffer = bos.toByteArray();
            base64EncoderImg = Base64.getEncoder().encodeToString(buffer);
            // base64EncoderImg = "data:image/png;base64," + base64EncoderImg;
        } catch (IOException e) {
           log.info("图片转base64换异常！");
        }
        return base64EncoderImg;
    }

    /**
     * 根据文件路径删除文件
     * @param path
     * @date 2022/7/20 15:19
     * @author wy
     * @return boolean
     */
    public static boolean removeFile(String path){
        File file = new File(path);

        if (!file.getParentFile().exists()){
            return false;
        }
        return file.delete();
    }


}
