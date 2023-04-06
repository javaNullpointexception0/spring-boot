package com.example.lzj.guavatest.optional;


import com.google.common.base.Optional;

public class TestOptional {

    public static void main(String[] args) {
        Optional<Integer> optional1 = Optional.absent(); //创建引用缺失的Optional实例
        Optional<Integer> optional2 = Optional.of(1); //创建指定引用的Optional实例，若引用为null则报错
        Optional<Integer> optional3 = Optional.fromNullable(null); //创建指定引用的Optional实例，若引用为null则表示缺失
        System.out.println(optional1.isPresent()); //是否存在引用，false
        System.out.println(optional2.get()); //return 1
        System.out.println(optional1.or(2)); //若引用缺失，返回指定的值，return 2
        System.out.println(optional3.orNull()); //若应用存在，则返回引用，否则返回null

        //java.util.Optional类，只有empty/of/fromNullable方法，但实例方法更多
    }
}
