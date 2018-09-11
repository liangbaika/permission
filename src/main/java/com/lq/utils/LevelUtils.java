package com.lq.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Auther: LQ
 * @Date: 2018/9/10 22:00
 * @Description:
 */
public class LevelUtils {

    private static final String SPILTOR = ".";
    public  static final String ROOT = "0";

    public static String calculateLevel(String parentLevel, Integer parentId) {
        return StringUtils.isNotBlank(parentLevel) ? StringUtils.join(parentLevel, SPILTOR, parentId) : ROOT;
    }
}
