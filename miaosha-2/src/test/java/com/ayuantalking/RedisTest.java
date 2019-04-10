package com.ayuantalking;

import com.ayuantalking.redis.MiaoshaGoodsKey;
import com.ayuantalking.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/29/0029 11:18
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    class RedisUtils{


        /**
         *
         * @param key
         * @param value
         * @return
         */
        public long incrBy(String key ,long value){
            return redisTemplate.getConnectionFactory().getConnection().incrBy(
                    redisTemplate.getKeySerializer().serialize(key), value
            );
        }
        /**
         *
         * @param key
         * @return
         */
        public long incr(String key){
            return redisTemplate.getConnectionFactory().getConnection().incr( redisTemplate.getKeySerializer().serialize(key) );
        }

        /**
         *
         * @param key
         * @return
         */
        public long decr(String key){
            return redisTemplate.getConnectionFactory().getConnection().decr(redisTemplate.getKeySerializer().serialize(key));
        }


        /**
         *
         * @param key
         * @return
         */
        public long removeByKey(String key){
            return redisTemplate.getConnectionFactory().getConnection().del(redisTemplate.getKeySerializer().serialize(key));
        }

        public long getValueByKey(String key){
            byte[] bytes = redisTemplate.getConnectionFactory().getConnection().get(redisTemplate.getKeySerializer().serialize(key));
            //Object deserialize = redisTemplate.getKeySerializer().deserialize(bytes);
           // return deserialize == null ? 0L:(long) deserialize;
            //Object o = redisTemplate.opsForValue().get(key);
            return 0L;

        }

        /**
         *
         * @param key
         * @return
         */
        public long decrBy(String key,long value){
            return redisTemplate.getConnectionFactory().getConnection().decrBy(redisTemplate.getKeySerializer().serialize(key),value);
        }


    }

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void test(){
//        Long increment = redisUtil.increment(MiaoshaGoodsKey.goodsStock.getPrefix(), 10);
//        System.out.println(increment);
//        System.out.println(redisUtil.get(MiaoshaGoodsKey.goodsStock.getPrefix()));
//        String key = "myKey1";
////        long num = stringRedisTemplate.boundValueOps(key).increment(-1);
////        System.out.println(num);
////        String s = stringRedisTemplate.opsForValue().get(key);
////        if (s != null) {
////            System.out.println(Long.valueOf(s));
////
////        }
////        String goodsStockKey="goodsStockKey:1";
////        String a = stringRedisTemplate.opsForValue().get(goodsStockKey);
////        System.out.println(a);
////        stringRedisTemplate.delete(goodsStockKey);
////
////        String c = stringRedisTemplate.opsForValue().get(goodsStockKey);
////        System.out.println(c);
////        String goodsStockKey1="goodsStockKey:2";
////        String b = stringRedisTemplate.opsForValue().get(goodsStockKey1);
////        System.out.println(b);
        String goodsStockKey="goodsStockKey:222";
//        System.out.println(stringRedisTemplate.opsForValue().increment(goodsStockKey, 10));
//        System.out.println(stringRedisTemplate.opsForValue().increment(goodsStockKey, -1));

        RedisUtils redisUtils = new RedisUtils();

        //System.out.println(redisUtils.decr(goodsStockKey));
        // 1 初始化商品库存
        // 1.1 先删除库存
        // 1.2 把库存放到redis
        long l = redisUtils.removeByKey(goodsStockKey);
        System.out.println(l);
        redisTemplate.opsForValue();
        System.out.println(redisUtils.incrBy(goodsStockKey,10));






//        long valueByKey = redisUtils.getValueByKey(goodsStockKey);
//        System.out.println(valueByKey);

        //System.out.println(redisTemplate.opsForValue().get(goodsStockKey));

        //System.out.println(redisUtils.decr(goodsStockKey));
        //System.out.println(redisUtils.decr(goodsStockKey));




    }


}
