package com.xxx.common.util;

import java.io.*;
import java.util.Calendar;

/**
 * Created by sks on 2018/1/2.
 */
public class FileUtil {
    /**
     * @param content
     * @param file
     * @param encoding
     * @throws Exception
     * @Description: 数据写入
     * @author gd.cui
     */
    public static void writerXmlFile(String content, File file, String encoding) throws Exception {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
            bw.write(content);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public static byte[] getAvailableBytes(InputStream inputStream, int timeout) throws Exception {
        Calendar cal = Calendar.getInstance();
        long startMillis = cal.getTimeInMillis();
        long endMillis = startMillis;
        int count = 0;
        try {
            while (count == 0 && (endMillis - startMillis) <= timeout * 1000) {
                endMillis = Calendar.getInstance().getTimeInMillis();
                count = inputStream.available();
            }
            if (count == 0 && (endMillis - startMillis) > timeout * 1000) {
                System.out.println("read server response time out !");
            }
            byte[] bytes = new byte[count];
            int readCount = 0; // 已经成功读取的字节的个数
            while (readCount < count) {
                readCount += inputStream.read(bytes, readCount, count - readCount);
            }
            System.out.println("==============back:" + new String(bytes, "GBK"));
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public static byte[] toByteArray(InputStream input) throws Exception {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int n = 0;
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }
            System.out.println("==============back:" + new String(output.toByteArray(), "GBK"));
            return output.toByteArray();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * @param fileName
     * @return
     * @throws Exception
     * @Description:读取文件内容
     * @author gd.cui
     */
    public String readFile(String fileName) throws Exception {
        File file = new File(fileName);
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String str = null;
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return sb.toString();
    }
}
