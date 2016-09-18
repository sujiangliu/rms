package com.jack.rms.common;
//package com.jack.rms.util;
//
//import net.spy.memcached.AddrUtil;
//import net.spy.memcached.BinaryConnectionFactory;
//import net.spy.memcached.MemcachedClient;
//import net.spy.memcached.internal.OperationFuture;
//
//import java.io.IOException;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * Created by Lucky on 15/04/18.
// */
//public final class MemCachedUtils {
//
//    final String host = "2774fe00b51a42eb.m.cnhzaliqshpub001.ocs.aliyuncs.com"; //控制台上的“内网地址”
//    final String port = "11211"; //默认端口 11211，不用改
//
//    private static MemCachedUtils instance = null;
//
//    private final static ReentrantLock REENTRANT_LOCK = new ReentrantLock();
//
//    private final static ReentrantLock REENTRANT_CACHE_LOCK = new ReentrantLock();
//
//    private final static String USER_NAME = "8dol@8dol.com";
//
//    private final static String PASS_WORD = "Nj8dol123456";
//
//    MemcachedClient cache = null;
//
//    private MemCachedUtils() {
//
//    }
//
//    public static MemCachedUtils getMemCacheUtilsInstance() {
//        REENTRANT_LOCK.lock();
//        try {
//            if (instance == null) {
//                instance = new MemCachedUtils();
//            }
//        } finally {
//            REENTRANT_LOCK.unlock();
//        }
//        return instance;
//    }
//
//    private void initCache() throws IOException {
//        cache = new MemcachedClient(new BinaryConnectionFactory(), AddrUtil.getAddresses(host + ":" + port));
//    }
//
//    private void confirmCache() throws IOException {
//        REENTRANT_CACHE_LOCK.lock();
//        try {
//            if (cache == null) {
//                initCache();
//            }
//        } finally {
//            REENTRANT_CACHE_LOCK.unlock();
//        }
//    }
//
//    /**
//     * 向OCS中设置值
//     *
//     * @param key     键值
//     * @param seconds 失效时间
//     * @param value   设置的值
//     * @throws java.io.IOException
//     * @throws InterruptedException
//     * @throws java.util.concurrent.ExecutionException
//     */
//
//    public void set(String key, int seconds, Object value) throws IOException, InterruptedException, ExecutionException {
//        confirmCache();
//        OperationFuture future = cache.set(key, seconds, value);
//        future.get();
//    }
//
//    /**
//     * 获取OSC中的值
//     *
//     * @param key 设置的键值
//     * @return 返回VALUE
//     */
//    public Object get(String key) throws IOException {
//        confirmCache();
//        return cache.get(key);
//    }
//}
