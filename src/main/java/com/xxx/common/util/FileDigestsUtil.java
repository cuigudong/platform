package com.xxx.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sks on 2018/1/2.
 */
public class FileDigestsUtil {

    private static final String SHA1 = "SHA-1";

    public static String getSha1Str(File file) {
        String str = "";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[2048];
            int c;
            while ((c = fis.read(buf)) > 0) {
                baos.write(buf, 0, c);
            }
            fis.close();
            str = SHA1(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return str;
    }

    // 处理字节数组获取SHA1摘要
    public static String SHA1(byte[] byteArray) {
        try {
            MessageDigest digest = MessageDigest.getInstance(SHA1);
            digest.update(byteArray);
            byte messageDigest[] = digest.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
    }
}
