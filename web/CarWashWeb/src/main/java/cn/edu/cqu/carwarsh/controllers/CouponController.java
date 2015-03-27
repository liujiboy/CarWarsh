package cn.edu.cqu.carwarsh.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.cqu.carwarsh.services.CouponService;
import cn.edu.cqu.carwarsh.services.CustomerService;
/**
 * 优惠券操作服务端接口
 * @author liuji
 *
 */
@RestController
public class CouponController {
	/**
	 * 用于输出日志
	 */
	private static Logger logger = LoggerFactory
			.getLogger(CouponController.class);
	@Autowired
	private CouponService couponService;
	@Autowired
	private CustomerService customerService;
	public void ShowDetils(){
		
	}
}
