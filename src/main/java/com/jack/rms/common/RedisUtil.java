package com.jack.rms.common;
//package com.jack.rms.util;
//
//import redis.clients.jedis.Jedis;
//
///**
// * Created by jack on 2015/8/31.
// */
//public class RedisUtil {
//
//    private static String ip = "120.26.77.244";
////    private static String ip = "10.117.31.210";
//    private static int port = 6379;
//
//    private static Jedis redisClient = null;
//
//    public static Jedis getRedis () {
//        if (null == redisClient) {
//            redisClient = new Jedis(ip, port);
//        }
//        return redisClient;
//    }
//
//    public static String get(String key) {
//        String val = getRedis().get(key);
//        closeRedis();
//        return val;
//    }
//
//    public static boolean isExist(String key) {
//        boolean isExist = getRedis().exists(key);
//        closeRedis();
//        return isExist;
//    }
//
//    public static void add(String key, String value) {
//        add(key, value, Boolean.TRUE);
//    }
//
//    public static void add(String key, String value, boolean isAppend) {
//        if (!isAppend && redisClient.exists(key))  {
//            getRedis().expire(key, 0);
//        }
//
//        getRedis().append(key, value);
//
//        closeRedis();
//    }
//
//    public static void expire(String key) {
//        expire(key, 0);
//    }
//
//    public static void closeRedis() {
//        redisClient.close();
//        redisClient = null;
//    }
//
//    public static void expire(String key, int second) {
//        getRedis().expire(key, second);
//        closeRedis();
//    }
//
////    public static void main(String[] args) {
////
//////        String key = "logistics_verify_code_123";
//////        RedisUtil.add(key, "11", false);
//////        System.out.println(RedisUtil.get(key));
//////        RedisUtil.add(key, "23", false);
//////        System.out.println(RedisUtil.get(key));
////
//////        String channel = "logistics_verify_code";
//////        for(int i = 0; i < 10000; i++) {
//////            Jedis redis = new Jedis("120.26.77.244", 6379);
//////            System.out.println(i + "\t" + redis.get("logistics_verify_code_123"));
//////            redis.close();
//////
//////            if (null == redis) {
//////                System.out.println("-------");
//////            }
//////            else {
//////                System.out.println("========");
//////                System.out.println(redis);
//////            }
//////        }
//////
//////        String key = "logistics_verify_code_123";
//////
//////        if (redis.exists(key)) {
//////            System.out.println(redis.get(key));
//////            System.out.println("=== return ===");
//////            return;
//////        }
//////
//////        redis.append(key, "6789");
//////        redis.expire(key, 10);
//////
//////        String result = redis.get(key);
//////        System.out.println(result);
////
//////        while (true) {
//////            String result = redis.get(key);
//////            System.out.println(result);
//////
//////            if (null == result) {
//////                break;
//////            }
//////
//////            try {
//////                Thread.sleep(1000);
//////            } catch (InterruptedException e) {
//////                e.printStackTrace();
//////            }
//////        }
////    }
//
//}
