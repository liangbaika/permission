package com.lq.utils;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: LQ
 * @Date: 2018/10/8 21:59
 * @Description: 处理字符串  1.2.3.4
 */
public class StringUtils {

    public static List<Integer> split2ListInt(String str) {
        try {
            List<String> list = Splitter.on(Const.SEPARATOR_POINT).trimResults().omitEmptyStrings().splitToList(str);
            return list.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Lists.newArrayList();
        }

    }
}
