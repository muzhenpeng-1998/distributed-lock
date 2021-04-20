package com.golike.apidriver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author yueyi2019
 */
@RestController
@RequestMapping("/grab")
public class GrabOrderController {
    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/do/{orderId}")
    public String grab(@PathVariable("orderId") int orderId, int driverId){
        String url = "http://service-order" + "/grab/do/"+orderId+"?driverId="+driverId;

        restTemplate.getForEntity(url, String.class).getBody();
        return "成功";
    }
}