package com.example.lzj.guavatest.ordering;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class TestOrdering {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4, 2, 5, 6, 8, 9, 1, 3, 7);
        Ordering<Comparable> ordering = Ordering.natural();
        //System.out.println(ordering.greatestOf(list, 3)); //获取最大的3个数，leastOf获取最小的
        //System.out.println(ordering.isOrdered(list)); //list是否按照当前排序器排好序
        //System.out.println(ordering.sortedCopy(list)); //对原数据拷贝一份出来排序并返回，immutableSortedCopy返回的结果不允许修改
        System.out.println(ordering.isStrictlyOrdered(list)); //是否已严格的按照排序器排好序，排好序返回true，否则返回false，有重复的数据直接返回false
        System.out.println(ordering.thenComparing(new Comparator<Comparable>() {
            @Override
            public int compare(Comparable o1, Comparable o2) {
                return o1.compareTo(o2);
            }
        })); //如果两个值相等，可以用指定的比较器继续排序
    }
}
