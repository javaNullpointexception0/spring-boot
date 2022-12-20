package com.example.lzj.guavatest.immutablecollection;

import com.google.common.collect.*;

import java.util.Arrays;

public class ImmutableCollection {

    public static void main(String[] args) {
        //生成不可变集合的集中方式
        ImmutableList<String> immutableList = ImmutableList.<String>builder().add("1").build();
        immutableList = ImmutableList.of("1", "2");
        immutableList = ImmutableList.copyOf(Arrays.asList("1", "2"));
        System.out.println(immutableList.reverse());
    }
}
