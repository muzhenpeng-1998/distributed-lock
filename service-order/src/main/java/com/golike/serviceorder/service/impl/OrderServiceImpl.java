package com.golike.serviceorder.service.impl;

import com.golike.serviceorder.dao.TblOrderDao;
import com.golike.serviceorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl {

	@Autowired
	private TblOrderDao mapper;

}
