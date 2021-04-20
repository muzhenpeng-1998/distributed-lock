package com.golike.serviceorder.service.impl;

import java.util.concurrent.TimeUnit;

import com.golike.serviceorder.service.GrabService;
import com.golike.serviceorder.service.OrderService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


/**
 * @author yueyi2019
 */
@Service("grabRedisRedissonService")
public class GrabRedisRedissonServiceImpl implements GrabService {

	@Autowired
	RedissonClient redissonClient;

//	@Autowired
//	Redisson redisson;

	@Autowired
    OrderService orderService;
	
    @Override
    public String grabOrder(int orderId , int driverId){
        //生成key
    	String lock = "order_"+(orderId+"");
    	
    	RLock rlock = redissonClient.getLock(lock.intern());

//        RLock lock1 = redisson.getLock(lock.intern());

        try {
    		// 此代码默认 设置key 超时时间30秒，过10秒，再延时
    		rlock.lock();

//            lock1.lock();
            try {
                TimeUnit.MINUTES.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            lock1.lock();
			System.out.println("司机:"+driverId+" 执行抢单逻辑");
			
            boolean b = orderService.grab(orderId, driverId);
            if(b) {
            	System.out.println("司机:"+driverId+" 抢单成功");
            }else {
            	System.out.println("司机:"+driverId+" 抢单失败");
            }
            
        } finally {
        	rlock.unlock();
//            lock1.unlock();
        }
        return null;
    }
}