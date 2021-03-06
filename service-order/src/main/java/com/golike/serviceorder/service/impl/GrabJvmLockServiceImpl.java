package com.golike.serviceorder.service.impl;

import com.golike.serviceorder.service.GrabService;
import com.golike.serviceorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("grabJvmLockService")
public class GrabJvmLockServiceImpl implements GrabService {
	
	@Autowired
	OrderService orderService;
	
	@Override
	public String grabOrder(int orderId, int driverId) {
		String lock = (orderId+"");
		
		synchronized (lock.intern()) {
			try {
				System.out.println("司机:"+driverId+" 执行抢单逻辑");
				
	            boolean b = orderService.grab(orderId, driverId);
	            if(b) {
	            	System.out.println("司机:"+driverId+" 抢单成功");
	            }else {
	            	System.out.println("司机:"+driverId+" 抢单失败");
	            }
	            
	        } finally {
	        	
	            
	        }
		}
		
		
		return null;
	}

}
