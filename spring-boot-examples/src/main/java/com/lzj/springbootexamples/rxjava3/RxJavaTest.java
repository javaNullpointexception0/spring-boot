package com.lzj.springbootexamples.rxjava3;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.*;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


/**
 * @author Created by luzhenjiang
 * @date 2023/11/24 10:37
 * @description
 */
public class RxJavaTest {

    public static void main(String[] args) {
        //sendAndReceive();
        //ambTest();
        //combineLatestArrayTest();
        //concatTest();
        //createTest();
        //deferTest();
        //fromActionTest();
        //generateTest();
        //intervalTest();
        //justTest();
        //testMerge();
        //sequenceEqual();
        //switchOnNextTest();
        //timerTest();
        usingTest();
        //zipTest();
    }

    /**
     * zip()：对多个Publisher进行压缩，具体压缩逻辑有BiConsumer决定。压缩后的事件个数，取决于这些Publisher发射事件最小的个数
     */
    private static void zipTest() {
        //输出：a1 b2
        Flowable.zip(Flowable.just("a", "b", "c"), Flowable.just("1", "2"), (t1, t2) -> t1 + t2).subscribe(value -> System.out.println(value));
    }

    /**
     * using()：第一个参数提供一个事件，第二个参数对这个事件进行处理，并将处理后的事件发送给Subscriber，当事件发送完毕后，第三个参数将能消费到第一个参数提供的事件
     */
    private static void usingTest() {
        Flowable.using(new Supplier<Integer>() {
            @Override
            public Integer get() throws Throwable {
                return new Integer(1);
            }
        }, new Function<Integer, Publisher<?>>() {
            @Override
            public Publisher<?> apply(Integer integer) throws Throwable {
                return Flowable.just(integer, integer + 1);
            }
        }, new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println("consumer:" + integer);
            }
        }).subscribe(value -> System.out.println("subscriber:" + value));
    }

    /**
     * timer()：在指定的延时时间后，发送一个值为0L的事件，然后complete
     */
    private static void timerTest() {
        Flowable.timer(1000L, TimeUnit.MILLISECONDS).subscribe(value -> System.out.println(value));
        try {
            Thread.sleep(2000L);
        } catch (Exception e) {

        }
    }

    /**
     * switchOnNext()：假设有Publisher发射的事件也为Publisher，这些被发射的Publisher只有最近的Publisher发布的事件才会被通知到Subscriber
     */
    private static void switchOnNextTest() {
        Flowable<Integer> flowable1 = Flowable.just(1, 2, 3).subscribeOn(Schedulers.io());
        Flowable<Integer> flowable2 = Flowable.just(4, 5, 6).subscribeOn(Schedulers.io());
        Flowable<Integer> flowable3 = Flowable.just(7, 8, 9).subscribeOn(Schedulers.io());
        //输出 7 8 9
        Flowable.switchOnNext(Flowable.create((FlowableOnSubscribe<Flowable<Integer>>)emitter -> {
            Random random = new Random();
            List<Flowable<Integer>> floableList = Arrays.asList(flowable1, flowable2, flowable3);
            for (int i = 0; i < floableList.size(); i++) {
                int index = random.nextInt(3);
                System.out.println("index=" + index);
                emitter.onNext(floableList.get(index));
            }
        }, BackpressureStrategy.LATEST)).subscribe(value -> System.out.println(value));
        try {
            Thread.sleep(2000L);
        } catch (Exception e) {

        }
    }

    /**
     * sequenceEqual()：检测两个Publisher发射的数据序列是否相同，然后将最终的Boolean结果发射出去
     */
    private static void sequenceEqual() {
        //true
        Flowable.sequenceEqual(Flowable.just(1, 2, 3), Flowable.just(1, 2, 3)).subscribe(value -> System.out.println(value));
        //false
        Flowable.sequenceEqual(Flowable.just(1, 2, 3), Flowable.just(1, 4, 5)).subscribe(value -> System.out.println(value));
    }

    /**
     * merge()：将多个PUblisher连接在一次。多线程环境下与concat的区别是不会保证顺序（单线程环境下还是会保证顺序的）
     */
    private static void testMerge() {
        Flowable.merge(Flowable.create(emitter -> {
            for (Integer data : Arrays.asList(new Integer[] {1, 2, 3})) {
                emitter.onNext(data);
                Thread.sleep(200L);
            }
        }, BackpressureStrategy.LATEST).subscribeOn(Schedulers.io()), Flowable.create(emitter -> {
            for (Integer data : Arrays.asList(new Integer[] {4, 5, 6})) {
                emitter.onNext(data);
                Thread.sleep(300L);
            }
        }, BackpressureStrategy.LATEST)).subscribe(value -> System.out.println(value));
        try {
            Thread.sleep(10 * 1000L);
        } catch (Exception e) {

        }
    }

    /**
     * just()：接收常量，依次对常量事件进行发送，发送完后就complete。
     */
    private static void justTest() {
        Flowable.just(1, 2).subscribe(value -> System.out.println(value));
    }

    /**
     * interval()：发送Long类型事件，第一个为0，后面一次递增。可以指定延迟多久才发送第一个以及每个事件的中间事件间隔
     */
    private static void intervalTest() {
        Flowable.interval(1000L, 1000L, TimeUnit.MILLISECONDS)
                .subscribe(new FlowableSubscriber<Long>() {
            Subscription subscription;
            @Override
            public void onSubscribe(@NonNull Subscription s) {
                s.request(Long.MAX_VALUE);
                subscription = s;
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("value = " + aLong);
                if ("5".equals(aLong.toString())) {
                    //发到5时取消发送
                    subscription.cancel();
                }
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
        try {
            Thread.sleep(10 * 1000L);
        } catch (Exception e) {

        }
    }

    /**
     * generate()：不停的调用Consumner发射数据，直到调用onComplete()或发生异常
     */
    private static void generateTest() {
        Flowable<Integer> flowable = Flowable.generate(new Consumer<Emitter<Integer>>() {
            int i = 10;
            @Override
            public void accept(Emitter<Integer> emitter) throws Throwable {
                emitter.onNext(i);
                i--;
                if (i == 0) {
                    emitter.onComplete();
                }
            }
        });
        flowable.subscribe(new FlowableSubscriber<Integer>() {
            @Override
            public void onSubscribe(@NonNull Subscription s) {
                System.out.println("subscriber onSubscribe");
                s.request(Long.MAX_VALUE);
            }
            @Override
            public void onNext(Integer o) {
                System.out.println("subscriber onNext：" + o);
            }
            @Override
            public void onError(Throwable t) {
                System.out.println("subscriber onError");
            }
            @Override
            public void onComplete() {
                System.out.println("subscriber onComplete");
            }
        });
    }

    /**
     * fromAction()：通过执行指定action产生Publisher，针对每一个订阅者，都会执行一次指定的action。Publisher不会发射任何数据，所以只有观察者的onError()或onComplete()可能被执行
     */
    private static void fromActionTest() {
        Flowable<Object> flowable = Flowable.fromAction(() -> System.out.println("do action"));
        flowable.subscribe(new FlowableSubscriber<Object>() {
            @Override
            public void onSubscribe(@NonNull Subscription s) {
                System.out.println("subscriber1 onSubscribe");
                s.request(Long.MAX_VALUE);
            }
            @Override
            public void onNext(Object o) {
                System.out.println("subscriber1 onNext");
            }
            @Override
            public void onError(Throwable t) {
                System.out.println("subscriber1 onError");
            }
            @Override
            public void onComplete() {
                System.out.println("subscriber1 onComplete");
            }
        });
        flowable.subscribe(new FlowableSubscriber<Object>() {
            @Override
            public void onSubscribe(@NonNull Subscription s) {
                System.out.println("subscriber2 onSubscribe");
            }
            @Override
            public void onNext(Object o) {
                System.out.println("subscriber2 onNext");
            }
            @Override
            public void onError(Throwable t) {
                System.out.println("subscriber2 onError");
            }
            @Override
            public void onComplete() {
                System.out.println("subscriber2 onComplete");
            }
        });
    }


    /**
     * defer()：通过工厂创建Publisher，而且每一个订阅者订阅，都会调用工厂创建新的Publisher
     */
    private static void deferTest() {
        AtomicInteger index = new AtomicInteger(1);
        Flowable<Integer> defer = Flowable.defer(new Supplier<Publisher<? extends Integer>>() {
            @Override
            public Publisher<? extends Integer> get() throws Throwable {
                return Flowable.just(index.getAndIncrement());
            }
        });
        defer.subscribe(value -> System.out.println(value));
        defer.subscribe(value -> System.out.println(value));

    }

    /**
     * concat()：将多个PUblisher连接在一起，上一个Publisher发送完事件后，下一个Publisher才发送
     */
    private static void concatTest() {
        Flowable<Integer> just1 = Flowable.just(1, 2, 3);
        Flowable<Integer> just2 = Flowable.just(4, 5, 6);
        Flowable.concat(just1, just2).subscribe(value -> System.out.print(value + " "));
    }

    /**
     * create()：创建Publisher，并可以通过Emitter自定义事件发送逻辑
     */
    private static void createTest() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                emitter.onNext(2);
            }
        }, BackpressureStrategy.LATEST).subscribe(value -> System.out.println(value));
    }

    /**
     * combineLatestArray：如果还有一个Publisher的事件未发送，则将前几个Publisher发送的最新事件和未发送的Publisher发送的每一个事件进行组合
     */
    private static void combineLatestArrayTest() {
        Flowable<Integer> just1 = Flowable.just(1, 2, 3);
        Flowable<Integer> just2 = Flowable.just(4, 5, 6);
        Flowable<Integer> just3 = Flowable.just(7, 8, 9);
        Flowable.combineLatestArray(new Flowable[] {just1,just2, just3}, valueArray -> Arrays.asList(valueArray).stream().map(String::valueOf).collect(Collectors.joining(",")))
                .subscribe(value -> System.out.println(value));

        //只有发生了异常，就会终止整个组合过程
        just1 = Flowable.just(1, 2, 3);
        just2 = Flowable.error(new RuntimeException());
        just3 = Flowable.just(7, 8, 9);
        Flowable.combineLatestArray(new Flowable[] {just1,just2, just3}, valueArray -> Arrays.asList(valueArray).stream().map(String::valueOf).collect(Collectors.joining(",")))
                .subscribe(value -> System.out.println(value));
    }

    /**
     * amb函数，传入的多个Publisher实例，哪个Publisher发送了事件，就一直只接收该Publisher发送的事件，其它Publisher发送的事件将会被忽略
     */
    private static void ambTest() {
        //第一个先发送数据，则一直都是接收第一个Publisher的事件
        Flowable<Integer> just1 = Flowable.just(1, 2, 3);
        Flowable<Integer> just2 = Flowable.just(4, 5, 6);
        Flowable<Integer> just3 = Flowable.just(7, 8, 9);
        Flowable.ambArray(just1, just2, just3).subscribe(value -> System.out.print(value + " "));

        try {
            //第三个先发送，则一直都是接收第三个Publisher发送的事件
            just1 = Flowable.just(1, 2, 3).delay(1000L, TimeUnit.MILLISECONDS);
            just2 = Flowable.just(4, 5, 6).delay(300L, TimeUnit.MILLISECONDS);
            just3 = Flowable.just(7, 8, 9).delay(200L, TimeUnit.MILLISECONDS);
            System.out.println("");
            Flowable.ambArray(just1, just2, just3).subscribe(value -> System.out.print(value + " "));
            Thread.sleep(1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //第二个先执行，但失败了，其它的Publisher发送的事件也不会被处理
            just1 = Flowable.just(1, 2, 3).delay(1000L, TimeUnit.MILLISECONDS);
            just2 = Flowable.error(new RuntimeException());
            just3 = Flowable.just(7, 8, 9).delay(200L, TimeUnit.MILLISECONDS);
            System.out.println("");
            Flowable.ambArray(just1, just2, just3).subscribe(value -> System.out.print(value + " "), e -> System.out.print(e.getClass().getName()));
            Thread.sleep(1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void sendAndReceive() {
        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe() {
            @Override
            public void subscribe(@NonNull FlowableEmitter emitter) throws Throwable {
                List<Integer> dataList = Arrays.asList(1, 2, 3);
                //增加发射器销毁的自定义逻辑
                emitter.setDisposable(new Disposable() {
                    boolean dispose = false;
                    @Override
                    public void dispose() {
                        System.out.println("subscribe dispose.");
                        dispose = true;
                    }
                    @Override
                    public boolean isDisposed() {
                        return dispose;
                    }
                });
                for (Integer data : dataList) {
                    System.out.println("send " + data + ";request=" + emitter.requested());
                    //发射事件给观察者
                    emitter.onNext(data);
                    Thread.sleep(1000L);
                }
            }
        }, BackpressureStrategy.LATEST);
        //增加中间事件处理逻辑
        flowable = flowable.map(value -> {
            if ("2".equals(value.toString())) {
                //模拟事件处理异常
                value = value/(value - 2);
            }
            return value + 1;
        });
        flowable.subscribe(new FlowableSubscriber<Integer>() {
            @Override
            public void onSubscribe(@NonNull Subscription s) {
                //代表发射器最多发射多少个事件。如果为Long.MAX_VALUE，则代表不限制。如果不为Long.MAX_VALUE，则发送的事件数量达到指定值后，数据会达到发射器，但不会发送给订阅者
                s.request(Long.MAX_VALUE);
                System.out.println("subscriber1 started.");
            }
            @Override
            public void onNext(Integer integer) {
                //接收事件
                System.out.println("subscriber1 receive value:" + integer);
            }
            @Override
            public void onError(Throwable throwable) {
                System.out.println("subscriber1 receive error：" + throwable.getClass());
            }
            @Override
            public void onComplete() {
                System.out.println("subscriber1 receive complete.");
            }
        });
    }


    private void test() {
        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe() {
            @Override
            public void subscribe(@NonNull FlowableEmitter emitter) throws Throwable {
                List<Integer> dataList = Arrays.asList(1, 2, 3);
                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Throwable {
                        System.out.println("subscribe cancelled.");
                    }
                });
                emitter.setDisposable(new Disposable() {
                    boolean dispose = false;
                    @Override
                    public void dispose() {
                        System.out.println("subscribe disposabled.");
                        dispose = true;
                    }

                    @Override
                    public boolean isDisposed() {
                        return dispose;
                    }
                });
                for (Integer data : dataList) {
                    System.out.println("send " + data + ";request=" + emitter.requested());
                    emitter.onNext(data);
                    Thread.sleep(1000L);
                    /*if (dataList.get(1).equals(data)) {
                        //会调用订阅者的onComplete()，后续如果还有事件要发送，事件不会发送给发射器。
                        emitter.onComplete();
                    }*/
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.LATEST);
        /*flowable = flowable.map(value -> {
            if ("2".equals(value.toString())) {
                    value = value/(value - 2);
            }
            return value + 1;
        });*/
        flowable.subscribe(new FlowableSubscriber<Integer>() {

            @Override
            public void onSubscribe(@NonNull Subscription s) {
                //代表发射器最多发射多少个事件。如果为Long.MAX_VALUE，则代表不限制。如果不为Long.MAX_VALUE，则发送的事件数量达到指定值后，数据会达到发射器，但不会发送给订阅者
                s.request(100L);
                System.out.println("subscriber1 started.");
                //s.cancel();
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("subscriber1 receive value:" + integer);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("subscriber1 receive error：" + throwable.getClass());
            }

            @Override
            public void onComplete() {
                System.out.println("subscriber1 receive complete.");
            }
        });
        //flowable.subscribe(value -> System.out.println("subscriber2 receive value:" + value));
    }
}
