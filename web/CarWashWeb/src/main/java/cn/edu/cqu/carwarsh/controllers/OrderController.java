package cn.edu.cqu.carwarsh.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.cqu.carwarsh.services.CustomerService;
import cn.edu.cqu.carwarsh.services.OrderService;
/**
 * 订单操作服务端接口
 * @author liuji
 *
 */
@RestController
public class OrderController {
	/**
	 * 用于输出日志
	 */
	private static Logger logger = LoggerFactory
			.getLogger(OrderController.class);
	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomerService customerService;
}
