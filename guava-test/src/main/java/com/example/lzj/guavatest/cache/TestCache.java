package com.example.lzj.guavatest.cache;

import com.google.common.cache.*;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.Callables;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class TestCache {

    public static void main(String[] args) throws Exception {

        LoadingCache<String, String> cache = CacheBuilder.newBuilder().concurrencyLevel(1).recordStats().removalListener(new RemovalListener<String, String>() {
            //缓存移除监听器，可以做一些清理工作
            //监听器是和键移除同步执行，如果监听器内部的逻辑耗时较长，可以使用异步执行：RemovalListeners.asynchronous(RemovalListener, Executor)
            @Override
            public void onRemoval(RemovalNotification<String, String> removalNotification) {
                System.out.println("remove key-value-cause：" + removalNotification.getKey() + "-" + removalNotification.getValue() + "-" + removalNotification.getCause());
            }
        }).build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                //如果key对应的值不存在时，会在这里进行加载，然后放到内存中
                System.out.println("key=" + key);
                return key + "-value";
            }
            @Override
            public Map<String, String> loadAll(Iterable<? extends String> keys) throws Exception {
                //针对不存在的Key，默认每个key会调用一次load方法进行加载（return super.loadAll(keys);）
                //如果希望提升性能，支持批量加载：
                // 1：如果调用getAll：返回map，map里必须针对每个未缓存的key都有值，否则报错
                // 2：如果调用getAllPresent，则不会进入该方法，只返回目前缓存存在key的值
                System.out.println("keys=" + keys);
                HashMap<String, String> hashMap = Maps.newHashMap();
                hashMap.put("name2", "name2-value");
                hashMap.put("name3", "name3-value");
                return hashMap;
            }
        });
        System.out.println(cache.get("name"));
        System.out.println(cache.getAllPresent(Arrays.asList("name", "name2", "name3")));
        String name4Value = cache.get("name4", new Callable<String>() {
            @Override
            public String call() throws Exception {
                //返回值会进行缓存，下次获取时直接从缓存获取即可
                System.out.println("call");
                return "name4-value";
            }
        });
        System.out.println(name4Value);
        System.out.println(cache.getIfPresent("name4"));

        //主动设置缓存失效
        cache.invalidate("name4");
        System.out.println(cache.getIfPresent("name4"));

        CacheStats stats = cache.stats();
        System.out.println(stats);

    }
}
