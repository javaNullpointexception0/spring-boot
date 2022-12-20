package com.example.lzj.guavatest.preconditions;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public class TestPreconditions {

    public static void main(String[] args) {
        String s1 = "lzj";
        String s2 = "lzj";
        Preconditions.checkArgument(s1.equals(s2), "fail", 10L);//如果表达式不成里，抛出IllegalArgumentException异常

        //检查index作为索引值对某个列表、字符串或数组是否有效。index>=0 && index<size，index超出size时抛出IndexOutOfBoundsException异常
        //与checkPositionIndex同效果
        Preconditions.checkElementIndex(1, Lists.newArrayList().size());

        //检查value是否为null，null时抛出NullPointerException异常，否则返回其值。
        Preconditions.checkNotNull(null);

        //用来检查对象的某些状态。不相等时返回IllegalStateException
        Preconditions.checkState(1 == 2);
    }
}
