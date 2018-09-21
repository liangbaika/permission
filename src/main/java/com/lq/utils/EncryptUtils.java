package com.lq.utils;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @Auther: LQ
 * @Date: 2018/9/16 18:43
 * @Description:encyypt utils
 */
public class EncryptUtils {

    private static final String KEY = "permission";
    private static final String DEFAULT_PASSWD = "permissionWXSA64serwiosiopX49A1m";
    private static final String INSTANCE = "MD5";
    public final static char[] hexDigits = {
            '1', '2', '3', '4', '5', '7', '8', '9', '0', 's', 'W', 'j', 'J', 'q', 'Q',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'm', 'n', 'x', 'y', 'p', 'o', 'O',
            'A', 'B', 'C', 'D', 'E', 'W', 'E', 'F', 'G', 'M', 'T', 'K'
    };


    public static String Md5Encrypt(String input) {
        if (StringUtils.isBlank(input)) {
            return DEFAULT_PASSWD;
        }
        byte[] inputBytes = input.getBytes();
        try {
            MessageDigest md5Inatance = MessageDigest.getInstance(INSTANCE);
            String inputWapperStr = input + KEY;
            md5Inatance.update(inputWapperStr.getBytes());
            byte[] encryptBytes = md5Inatance.digest();
            int len = encryptBytes.length;
            char[] str = new char[len * 2];
            int k = 0;
            for (int i = 0; i < len; i++) {
                byte aByte = encryptBytes[i];
                str[k++] = hexDigits[aByte >>> 4 & 0xf];
                str[k++] = hexDigits[aByte & 0xf];
            }
            String tmpStr = new String(str);

            md5Inatance.update(tmpStr.getBytes());
            byte[] digest = md5Inatance.digest();
            int tmpLen = digest.length;
            char[] tmpChars = new char[tmpLen * 2];
            int m = 0;
            for (int i = 0; i < tmpLen; i++) {
                byte aByte = digest[i];
                tmpChars[m++] = hexDigits[aByte >>> 4 & 0xf];
                tmpChars[m++] = hexDigits[aByte & 0xf];
            }
            return new String(tmpChars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return DEFAULT_PASSWD;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public static String getRandomNumber() {
        Random random = new Random(System.nanoTime());
        StringBuffer buffer = new StringBuffer();
        for (int j = 2; j > 0; j--) {
            int i = random.nextInt(9999) + 10000;
            buffer.append(i);
        }
        return buffer.toString();
    }

    public static String getUniqueNumber() {
        String now = System.nanoTime() + "";
        Random random = new Random(System.nanoTime());
        int r = random.nextInt(9999) + 10000;
        return now + r;
    }

    public static void main(String[] args) {

        System.out.println(Md5Encrypt("root"));

    }
}
