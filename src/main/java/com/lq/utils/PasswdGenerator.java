package com.lq.utils;

import java.util.Date;
import java.util.Random;

/**
 * @Auther: LQ
 * @Date: 2018/9/16 18:17
 * @Description:
 */
public class PasswdGenerator {

    public final static char[] words = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'm', 'n', 'x', 'y', 'p',
            'A', 'B', 'C', 'D', 'E', 'W', 'E', 'F', 'G', 'M', 'T', 'K'
    };
    public final static int[] nums = {2, 3, 4, 5, 6, 7, 8, 9};

    public static String generatePasswdRandom() {

        StringBuffer buffer = new StringBuffer();
        Random random = new Random(System.nanoTime());
        buffer.append("pm");
        int len = random.nextInt(3) + 6;
        boolean flag = false;
        for (int i = 0; i < len; i++) {
            if (i % 2 == 0) {
                buffer.append(nums[random.nextInt(nums.length)]);
            } else if (i % 2 != 0 && flag) {
                buffer.append(words[random.nextInt(nums.length)]);
            } else {
                buffer.append(nums[random.nextInt(nums.length)]);
            }

            flag = !flag;
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(generatePasswdRandom());
    }
}

