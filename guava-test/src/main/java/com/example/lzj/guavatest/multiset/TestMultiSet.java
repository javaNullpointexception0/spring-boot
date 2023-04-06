package com.example.lzj.guavatest.multiset;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.LinkedHashMultiset;

public class TestMultiSet {

    public static void main(String[] args) {
        ArrayListMultimap<String, String> arrayListMultimap = ArrayListMultimap.<String, String>create();
        arrayListMultimap.put("name", "lzj");
        arrayListMultimap.put("name", "maying");
        System.out.println(arrayListMultimap); //{name=[lzj, maying]}

        LinkedHashMultiset<String> linkedHashMultiset = LinkedHashMultiset.<String>create();
        linkedHashMultiset.add("1");
        linkedHashMultiset.add("2");
        linkedHashMultiset.add("1");
        System.out.println(linkedHashMultiset); //[1 x 2, 2]
    }
}
