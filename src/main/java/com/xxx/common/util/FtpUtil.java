package com.xxx.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sks on 2018/1/2.
 */
public class FtpUtil {
    private Logger logger = LoggerFactory.getLogger(FtpUtil.class);

    private String defaultEncoding = System.getProperty("file.encoding");

    private String ip;
    private int port = 21;
    private String username;
    private String password;
    private String directory;
    private FTPClient ftpClient;

    private String rteFname;

    public FtpUtil(String ip, int port, String username, String password, String directory) {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
        this.directory = directory;
    }

    public boolean login() throws Exception {
        boolean isSuccessed = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.setControlEncoding(defaultEncoding);
            ftpClient.connect(ip, port);
            // 检验是否连接成功
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                throw new Exception("FTP服务器拒绝了连接.");
            }
            if (!ftpClient.login(username, password)) {
                ftpClient.disconnect();
                throw new Exception("用户" + username + "登陆ftp服务器" + password + "失败。");
            }
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            isSuccessed = true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return isSuccessed;

    }

    public boolean uploadFile(String ftpFileName, InputStream inputStream, String encoding) throws Exception {
        boolean isSuccessed = false;
        try {
            if (login()) {
                changeWorkingDirectory(directory);
                /*
				 * ftpClient.enterLocalPassiveMode(); 这个方法的意思就是每次数据连接之前，ftp
				 * client告诉ftp server开通一个端口来传输数据。 为什么要这样做呢，因为ftp
				 * server可能每次开启不同的端口来传输数据， 但是在linux上，由于安全限制，可能某些端口没有开启，所以就出现阻塞
				 */
                ftpClient.enterLocalPassiveMode();
                if (ftpClient.storeFile(ftpFileName, inputStream)) {
                    isSuccessed = true;
                } else {
                    throw new Exception("文件" + ftpFileName + "上传失败.");
                }
            } else {
                // 登陆失败
                throw new Exception("用户" + username + "登陆ftp服务器" + password + "失败。");
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            ftpDispose();
        }
        return isSuccessed;
    }

    public boolean downFile(String ftpFileName, String localFileName, String encoding) throws Exception {
        boolean isSuccessed = false;
        try {
            if (login()) {
                changeWorkingDirectory(directory);
                File file = new File(localFileName);
				/*
				 * ftpClient.enterLocalPassiveMode(); 这个方法的意思就是每次数据连接之前，ftp
				 * client告诉ftp server开通一个端口来传输数据。 为什么要这样做呢，因为ftp
				 * server可能每次开启不同的端口来传输数据， 但是在linux上，由于安全限制，可能某些端口没有开启，所以就出现阻塞
				 */
                ftpClient.enterLocalPassiveMode();
                InputStream ins = ftpClient.retrieveFileStream(ftpFileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(ins, encoding));
                String line;
                StringBuilder sb = new StringBuilder(150);
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
                if (ins != null) {
                    ins.close();
                }
                FileUtil.writerXmlFile(sb.toString(), file, "UTF-8");
                isSuccessed = true;
            } else {
                isSuccessed = false;
                throw new Exception("用户" + username + "登陆ftp服务器" + password + "失败。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage() + ":文件下载失败");
        } finally {
            ftpDispose();
        }
        return isSuccessed;
    }

    /**
     * Description: 判断某目录下是否存在某文件      
     *
     * @param
     * @return      
     */
    public boolean exist(String ftpPath, String rteFname) throws Exception {
        boolean result = false;
        try {
            if (login()) {
                ftpClient.changeWorkingDirectory(new String(ftpPath.getBytes(defaultEncoding), "iso-8859-1"));
                // 获取文件列表
                ftpClient.enterLocalPassiveMode();
                FTPFile[] fs = ftpClient.listFiles();
                for (FTPFile ff : fs) {
                    if (ff.getName().equals(rteFname)) {
                        if (bytes2Kb(ff.getSize()) <= 5) {
                            result = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage() + ":文件校验失败");
        } finally {
            ftpDispose();
        }
        return result;
    }

    public boolean downLoadFile(String ftpPath, String rteFname, String localFname) throws Exception {
        boolean result = false;
        OutputStream out = null;
        try {
            if (login()) {
                ftpClient.changeWorkingDirectory(new String(ftpPath.getBytes(defaultEncoding), "iso-8859-1"));
                // 获取文件列表
                ftpClient.enterLocalPassiveMode();
                FTPFile[] fs = ftpClient.listFiles();
                for (FTPFile ff : fs) {
                    if (ff.getName().equals(rteFname)) {
                        out = new FileOutputStream(localFname);
                        ftpClient.retrieveFile(ff.getName(), out);
                    }
                }
                result = true;
            }
        } catch (Exception e) {
            logger.error("ftp下载失败！！！！！！！", e);
        } finally {
            ftpDispose();
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ioe) {
                    logger.error("FtpUtil.downFile out.close error", ioe);
                }
            }
        }
        logger.error("ftp下载: " + rteFname + " " + result);
        return result;
    }

    public float bytes2Kb(long bytes) {
        BigDecimal filesize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
        if (returnValue > 1)
            return returnValue;
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP).floatValue();
        return 1;
    }

    /**
     * 退出登录并关闭FTP连接
     */
    public void ftpDispose() {
        try {
            ftpClient.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!ftpClient.isConnected()) {
            try {
                ftpClient.disconnect();
            } catch (IOException ioe) {
            }
        }
    }

    /**
     * 切换工作目录
     */
    public void changeWorkingDirectory(String directory) throws Exception {
        try {
            ftpClient.changeWorkingDirectory(new String(directory.getBytes(defaultEncoding), "iso-8859-1"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("切换工作目录失败");
        }
    }

    public String readFile(String ftpFileName, String localFileName, String encoding) throws Exception {
        InputStream ins = null;
        StringBuilder builder = null;
        try {
            if (login()) {
                changeWorkingDirectory(directory);
                // OutputStream os = new FileOutputStream(localFileName);
				/*
				 * ftpClient.enterLocalPassiveMode(); 这个方法的意思就是每次数据连接之前，ftp
				 * client告诉ftp server开通一个端口来传输数据。 为什么要这样做呢，因为ftp
				 * server可能每次开启不同的端口来传输数据， 但是在linux上，由于安全限制，可能某些端口没有开启，所以就出现阻塞
				 */
                ftpClient.enterLocalPassiveMode();
                ins = ftpClient.retrieveFileStream(ftpFileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(ins, encoding));
                String line;
                builder = new StringBuilder(150);
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                logger.error("****************:" + builder.toString());
                reader.close();
                if (ins != null) {
                    ins.close();
                }
            } else {
                throw new Exception("用户" + username + "登陆ftp服务器" + password + "失败。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } finally {
            ftpDispose();
        }

        return builder.toString();
    }

    public List<String> getFileName(String ftpPath, String localFilePath) throws Exception {
        List<String> fileNameList = new ArrayList<String>();
        logger.error("ftpPath:" + ftpPath + "   " + "localFilePath:" + localFilePath);
        try {
            if (login()) {
                logger.error("登陆成功.....success");
                ftpClient.changeWorkingDirectory(new String(ftpPath.getBytes(defaultEncoding), "iso-8859-1"));
                // 获取文件列表
                ftpClient.enterLocalPassiveMode();
                FTPFile[] fs = ftpClient.listFiles();
                logger.error("fs的大小:" + fs.length);
                for (FTPFile ff : fs) {
                    logger.error("获取的文件名称为:" + ff.getName());
                    Date date = ff.getTimestamp().getTime();
                    String strDate = DateUtil.format(date, "yyyyMMdd");
                    if (StringUtils.equals(strDate, DateUtil.getCurrDate("yyyyMMdd"))) {
                        fileNameList.add(ff.getName());
                        File localFile = new File(localFilePath + ff.getName());
                        OutputStream ios = new FileOutputStream(localFile);
                        ftpClient.retrieveFile(ff.getName(), ios);
                        ios.close();
                        //清除ftp已经处理的文件
                        //ftpClient.deleteFile(ff.getName());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("ftp下载失败！！！！！！！", e);
        } finally {
            ftpDispose();
        }
        logger.error("ftp下载: " + fileNameList + " ，文件数： " + fileNameList.size());
        return fileNameList;
    }

}
