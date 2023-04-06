package com.example.lzj.guavatest.collectionutil;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestCollectionUtil {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("1");

        //Iterables.addAll(list, Arrays.asList("2")); //1 2

        Iterable<Integer> concat = Iterables.concat(Ints.asList(1, 2), Ints.asList(3, 4));
        //System.out.println(concat); //1 2 3 4

        boolean any = Iterables.any(concat, new Predicate<Integer>() {
            @Override
            public boolean apply(@Nullable Integer integer) {
                return integer == 3;
            }
        });

        boolean all = Iterables.all(concat, new Predicate<Integer>() {
            @Override
            public boolean apply(@Nullable Integer integer) {
                return integer < 5;
            }
        });
        //System.out.println(any + "  " + all);

        //获取目标元素在Iterable中的个数
        //int frequency = Iterables.frequency(concat, 4);
        //System.out.println(frequency);

        //获取iterable中唯一的元素，如果iterable为空或有多个元素，则快速失败
        //Integer onlyElement = Iterables.getOnlyElement(concat);
        //System.out.println(onlyElement); //Exception in thread "main" java.lang.IllegalArgumentException: expected one element but was: <1, 2, 3, 4>

        //把iterable按指定大小分割，得到的子集都不能进行修改操作
        //Iterable<List<Integer>> partition = Iterables.partition(concat, 2);
        //System.out.println(partition);

        //Sets提供了集合运算
        Sets.SetView<Integer> union = Sets.union(Sets.newHashSet(1, 2), Sets.newHashSet(2, 3));
        System.out.println(union);
    }
}
