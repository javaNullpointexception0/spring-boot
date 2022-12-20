package com.example.lzj.guavatest.strings;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.List;

public class TestStrings {

    public static void main(String[] args) {
        //skipNulls跳过null的值、useForNull用指定的内容替换掉null
        Joiner joiner = Joiner.on(",").skipNulls();
        String joinStr = joiner.join(1, 2, 3);
        System.out.println(joinStr);

        //omitEmptyStrings忽略null的值、trimResults对切分后的结果去掉前后空格
        List<String> splitToList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList("1,2,3");
        System.out.println(splitToList);

        System.out.println(CharMatcher.whitespace().removeFrom("abc def 124"));


    }
}
