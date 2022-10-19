package com.example.cloud.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author candy33
 * @Date 2022/1/18 11:37
 * @ClassName com.example.cloud.common.utils
 **/
@Slf4j
public class FTPUtils {

    private FTPClient ftpClient;

    private final String ip;
    private final String username;
    private final String password;
    private final Integer port;


    public FTPUtils(String ip, String username, String password, Integer port) {
        this.ip = ip;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    /**
     * 连接FTP
     */
    private void openClient() {
        FTPClient client = new FTPClient();
        try {
            client.connect(ip, port);
            client.login(username, password);
            if (!FTPReply.isPositiveCompletion(client.getReplyCode())) {
                log.error("========FTP连接失败========");
                client.disconnect();
            } else {
                log.info("========FTP连接成功========");
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("========FTP连接异常========", e);
        }
        this.ftpClient = client;
    }

    /**
     * 切换到父级目录
     *
     * @return true：成功，false：失败
     */
    private boolean changeToParentDir() {
        if (!ftpClient.isConnected()) {
            return false;
        }
        try {
            return ftpClient.changeToParentDirectory();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("========FTP切换父目录异常========", e);
            return false;
        }
    }

    /**
     * 切换至指定目录
     *
     * @param dir 目录
     * @return true：成功，false：失败
     */
    private boolean cd(String dir) {
        if (!ftpClient.isConnected()) {
            return false;
        }
        try {
            return ftpClient.changeWorkingDirectory(dir);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("========FTP切换" + dir + "目录异常========", e);
            return false;
        }
    }

    /**
     * 获取指定目录下的文件，如果目录为空，则获取当前所在目录下的文件
     *
     * @param filePath 指定目录
     * @return 返回null表示已断开连接或者获取异常
     */
    public FTPFile[] getFileList(String filePath) {
        this.openClient();
        if (!ftpClient.isConnected()) {
            return null;
        }
        try {
            if (StringUtils.isNotEmpty(filePath)) {
                return ftpClient.listFiles(filePath);
            }
            return ftpClient.listFiles();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("========FTP获取" + (StringUtils.isNotEmpty(filePath) ? filePath : "当前") + "目录文件异常========", e);
            return null;
        } finally {
            this.close();
        }
    }

    /**
     * 切换工作目录
     *
     * @param ftpPath 目录
     * @return true：成功，false：失败
     */
    private boolean changeDir(String ftpPath) {
        if (!ftpClient.isConnected()) {
            return false;
        }
        try {
            // 统一路径中的斜杠
            char[] chars = ftpPath.toCharArray();
            StringBuffer sbStr = new StringBuffer(256);
            for (int i = 0; i < chars.length; i++) {
                if ('\\' == chars[i]) {
                    sbStr.append('/');
                } else {
                    sbStr.append(chars[i]);
                }
            }
            ftpPath = sbStr.toString();
            if (ftpPath.indexOf('/') == -1) {
                ftpClient.changeWorkingDirectory(new String(ftpPath.getBytes(StandardCharsets.ISO_8859_1)));
            } else {
                String[] paths = ftpPath.split("/");
                for (int i = 0; i < paths.length; i++) {
                    ftpClient.changeWorkingDirectory(new String(paths[i].getBytes(StandardCharsets.ISO_8859_1)));
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("========FTP切换工作目录" + ftpPath + "异常========", e);
            return false;
        }
    }

    /**
     * 创建指定目录且切换到该目录下
     *
     * @param ftpPath 路径
     * @return true：成功，false：失败
     */
    private boolean mkDir(String ftpPath) {
        if (!ftpClient.isConnected()) {
            return false;
        }
        try {
            // 统一路径中的斜杠
            char[] chars = ftpPath.toCharArray();
            StringBuffer sbStr = new StringBuffer(256);
            for (int i = 0; i < chars.length; i++) {
                if ('\\' == chars[i]) {
                    sbStr.append('/');
                } else {
                    sbStr.append(chars[i]);
                }
            }
            ftpPath = sbStr.toString();
            if (ftpPath.indexOf('/') == -1) {
                ftpClient.makeDirectory(new String(ftpPath.getBytes(StandardCharsets.ISO_8859_1)));
                ftpClient.changeWorkingDirectory(new String(ftpPath.getBytes(StandardCharsets.ISO_8859_1)));
            } else {
                String[] paths = ftpPath.split("/");
                for (int i = 0; i < paths.length; i++) {
                    ftpClient.makeDirectory(new String(paths[i].getBytes(StandardCharsets.ISO_8859_1)));
                    ftpClient.changeWorkingDirectory(new String(paths[i].getBytes(StandardCharsets.ISO_8859_1)));
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("========FTP创建目录" + ftpPath + "异常========", e);
            return false;
        }
    }

    /**
     * 上传单个文件
     *
     * @param inputStream 文件流对象
     * @param newFileName 新文件名
     * @param folder 自定义保存的文件夹
     * @return 上传结果
     */
    public boolean upload(InputStream inputStream, String newFileName, String folder) {
        this.openClient();
        if (!ftpClient.isConnected()) {
            log.error("========FTP服务已断开========");
            return false;
        }
        boolean result = false;
        if (ftpClient != null) {
            try {
                if (StringUtils.isNotEmpty(folder)) {
                    this.mkDir(folder);
                }
                ftpClient.setBufferSize(100000);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                result = ftpClient.storeFile(new String(newFileName.getBytes(StandardCharsets.ISO_8859_1)), inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("========FTP文件上传异常========", e);
                return false;
            } finally {
                this.close();
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("========FTP文件上传异常，流关闭失败========", e);
                }
            }
        }
        return result;
    }

    /**
     * 上传单个文件
     *
     * @param file 文件流对象
     * @param newFileName 新文件名
     * @param folder 自定义保存的文件夹
     * @return 上传结果
     */
    public boolean upload(File file, String newFileName, String folder) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return this.upload(inputStream, newFileName, folder);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("========FTP文件上传异常========", e);
            return false;
        }
    }

    /**
     * 批量上传文件
     *
     * @param inputStreams 文件流 上传完成/失败后会自动关闭
     * @param fileNames 文件名
     * @param folder 上传目录
     * @return 上传目录+文件名
     */
    public List<String> uploadBatch(List<InputStream> inputStreams, List<String> fileNames, String folder) {
        List<String> fileUrls = new ArrayList<>();
        this.openClient();
        if (!ftpClient.isConnected()) {
            log.error("========FTP服务已断开========");
            return fileUrls;
        }
        if (ftpClient != null) {
            try {
                if (StringUtils.isNotEmpty(folder)) {
                    this.mkDir(folder);
                }
                for (int i = 0; i < inputStreams.size(); i++) {
                    if (inputStreams.get(i) != null) {
                        ftpClient.setBufferSize(100000);
                        ftpClient.setControlEncoding("UTF-8");
                        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                        ftpClient.storeFile(new String(fileNames.get(i).getBytes(StandardCharsets.ISO_8859_1)), inputStreams.get(i));
                        fileUrls.add(folder + fileNames.get(i));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error("========FTP文件批量上传异常========", e);
            } finally {
                this.close();
                for (InputStream stream : inputStreams) {
                    try {
                        if (stream != null)
                            stream.close();
                    } catch (IOException e) {
                        log.error("========FTP文件批量上传异常，流关闭失败========", e);
                    }
                }
            }
        }
        return fileUrls;
    }

    /**
     * 下载文件
     *
     * @param path 文件路径
     * @param fileName 文件名
     * @return 文件流
     */
    public final OutputStream downloadFile(String path, String fileName, String savePath) {
        this.openClient();
        if (!ftpClient.isConnected()) {
            log.error("========FTP服务已断开========");
            return null;
        }
        ftpClient.enterLocalPassiveMode();
        this.changeDir(path);
        try {
            OutputStream outputStream = new FileOutputStream(savePath);
            ftpClient.retrieveFile(fileName, outputStream);
            return outputStream;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("========FTP文件下载异常，流关闭失败========", e);
            return null;
        } finally {
            this.close();
        }
    }

    /**
     * 读取指定目录下的文件名
     * @param pathName 路径
     * @return 文件名数组
     */
    public String[] getFileNameList(String pathName) {
        this.openClient();
        if (!ftpClient.isConnected()) {
            log.error("========FTP服务已断开========");
            return null;
        }
        try {
            return ftpClient.listNames(pathName);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("========FTP文件名读取异常========", e);
            return null;
        } finally {
            this.close();
        }
    }

    /**
     * 删除文件
     * @param dirPath 路径+文件名
     * @return 是否成功
     */
    public boolean deleteFile(String dirPath) {
        this.openClient();
        if (!ftpClient.isConnected()) {
            log.error("========FTP服务已断开========");
            return false;
        }
        try {
            return ftpClient.deleteFile(dirPath);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("========FTP文件" + dirPath + "删除失败========", e);
            return false;
        } finally {
            this.close();
        }
    }

    /**
     * 删除FTP目录
     *
     * @param ftpDirectory 指定目录
     * @return 是否删除
     */
    public boolean deleteDirectory(String ftpDirectory) {
        this.openClient();
        if (!ftpClient.isConnected()) {
            log.error("========FTP服务已断开========");
            return false;
        }
        try {
            return ftpClient.removeDirectory(ftpDirectory);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("========FTP目录" + ftpDirectory + "删除失败========", e);
            return false;
        }finally {
            this.close();
        }
    }

    /**
     * 关闭连接
     */
    public void close() {
        try {
            if (ftpClient != null && ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("========FTP连接关闭失败========", e);
        }
    }
}
