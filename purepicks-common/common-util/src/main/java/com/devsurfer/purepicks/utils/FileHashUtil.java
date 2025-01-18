package com.devsurfer.purepicks.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHashUtil {

    public static String getFileHash(InputStream inputStream, String algorithm) throws NoSuchAlgorithmException, IOException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        byte[] buffer = new byte[1024];
        int bytesRead;

        // 读取文件数据并更新哈希计算
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            messageDigest.update(buffer, 0, bytesRead);
        }

        // 计算哈希值
        byte[] hashBytes = messageDigest.digest();

        // 转换为16进制字符串
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }

}
