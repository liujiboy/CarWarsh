package cn.edu.cqu.carwarsh.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.cqu.carwarsh.domains.Customer;
import cn.edu.cqu.carwarsh.services.CustomerService;
import cn.edu.cqu.carwarsh.vos.JSONResult;
import cn.edu.cqu.carwarsh.vos.SMSCode;
import cn.edu.cqu.carwarsh.vos.SMSCodes;

/**
 * 客户信息管理服务端接口。
 * 
 * @RestController标注表示，本类的所有方法均以json的方式返回数据
 * @author liuji
 *
 */
@RestController
public class CustomerController {
	/**
	 * 用于输出日志
	 */
	private static Logger logger = LoggerFactory
			.getLogger(CustomerController.class);
	/**
	 * 存储随机生成的4位注册验证码
	 */
	private static SMSCodes registerCodes = new SMSCodes();
	/**
	 * 存储随机生成的4位忘记密码验证码
	 */
	private static SMSCodes forgetCodes = new SMSCodes();
	/**
	 * 短信服务提供商发送短信的URL接口地址
	 */
	private static String SMS_URL;
	/**
	 * 短信服务的用户名
	 */
	private static String SMS_USER;
	/**
	 * 短信服务的密码
	 */
	private static String SMS_PWD;
	static {
		// 初始化静态变量
		logger.debug("开始初始化");
		String dir = System.getenv("CAR_WARSH_WEB_CONFIG");
		logger.debug("CAR_WARSH_WEB_CONFIG的值是{}", dir);
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(new File(dir, "sms.properties")));
			SMS_URL = (String) p.getProperty("SMS_URL");
			SMS_USER = (String) p.getProperty("SMS_USER");
			SMS_PWD = (String) p.getProperty("SMS_PWD");
			logger.debug("SMS_URL={},SMS_USER={},SMS_PWD={}", new Object[] {
					SMS_URL, SMS_USER, SMS_PWD });
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 启动一个线程定期清空registerCodes和forgetCodes中过期的验证码（此处使用jdk8的语法）
		new Thread(() -> {
			while (true) {
				try {
					logger.debug("开始清除registerCodes中过期的验证码");
					registerCodes.removeInValid();
					logger.debug("结束清除registerCodes中过期的验证码");
					logger.debug("开始清除forgetCodes中过期的验证码");
					forgetCodes.removeInValid();
					logger.debug("结束清除forgetCodes中过期的验证码");

					// 每2小时清空一次
				Thread.sleep(7200000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	).start();

	}
	/**
	 * 操作Customer表的对象
	 */
	@Autowired
	private CustomerService customerService;

	/**
	 * 请求生成4位注册验证码，并以短信的方式发送给用户 接口映射到/customer/requestRegisterCode.do
	 * 
	 * @param mobile
	 *            手机号码
	 * @return true表示验证码生成成功，false表示生成失败
	 */
	@RequestMapping(value = "/customer/requestRegisterCode.do")
	public JSONResult requestRegisterCode(String mobile) {
		// 在registerCodes中查找是否有验证码，若有判断其是否在1分钟之前创建，若是1分钟内创建的，返回false
		// 生成验证码，将其设置到registerCodes中
		// 将验证码发送到用户的手机，返回true
		// 若上述逻辑执行异常返回false
		JSONResult result = new JSONResult();
		SMSCode code = registerCodes.get(mobile);
		if (code != null && !code.isAfter1m()) {

			result.setMsg("1分钟内不能申请新验证码");
			result.setState(false);

		} else {
			code = new SMSCode();
			registerCodes.put(mobile, code);
			logger.debug("为手机{}生成的验证码为{}",
					new Object[] { mobile, code.getValue() });

			if (sendSMSCode(mobile, code.getValue())) {
				result.setMsg("验证码生成成功");
				result.setState(true);
			} else {
				result.setMsg("验证码发送失败");
				result.setState(false);
			}

		}

		return result;
	}

	/**
	 * 发送验证码到手机
	 * 
	 * @param mobile
	 *            手机
	 * @param code
	 *            验证码 return true表示成功，false表示失败
	 */
	private Boolean sendSMSCode(String mobile, String code) {
		// TODO 把code发送到手机上面
		return true;
	}

	/**
	 * 用户注册 接口映射到/customer/register.do
	 * 
	 * @param mobile
	 *            手机号码
	 * @param code
	 *            验证码
	 * @param pwd
	 *            密码
	 * @return 返回“注册成功”表示注册成功，其他信息表示注册失败（其他信息包括“手机号已注册”、“验证码过期或不存在”等）。
	 */
	@RequestMapping(value = "/customer/register.do")
	public JSONResult register(String mobile, String code, String pwd) {
		JSONResult result = new JSONResult();
		SMSCode smsCode = registerCodes.get(mobile);
		if (smsCode == null || !smsCode.getValue().equals(code)
				|| !smsCode.isValid()) {
			result.setMsg("验证码不合法");
			result.setState(false);
		} else {
			try {
				Customer c = customerService.findByMobile(mobile);
				if (c != null) {
					result.setMsg("用户已经存在");
					result.setState(false);
				} else {
					c = new Customer();
					c.setMobile(mobile);
					// TODO pwd应该MD5加密
					c.setPwd(pwd);
					customerService.add(c);
					result.setMsg("用户注册成功");
					result.setState(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setMsg("服务器异常，用户注册失败");
				result.setState(false);
			}
		}

		return result;
	}

	/**
	 * 用户登录 接口映射到/customer/login.do
	 * 
	 * @param mobile
	 *            手机号码
	 * @param pwd
	 *            密码
	 * @return true表示登录成功，false表示登录失败
	 */
	@RequestMapping(value = "/customer/login.do")
	public JSONResult login(String mobile, String pwd) {

		JSONResult result = new JSONResult();
		if (customerService.isValid(mobile, pwd)) {
			result.setMsg("登录成功");
			result.setState(true);
		} else {
			result.setMsg("登录失败");
			result.setState(false);
		}
		return result;
	}

	/**
	 * 修改密码 接口映射到/customer/changePwd.do
	 * 
	 * @param mobile
	 *            手机号码
	 * @param pwd
	 *            密码
	 * @param newPwd
	 *            新密码
	 * @return true表示修改成功，false表示修改失败
	 */
	@RequestMapping(value = "/customer/changePwd.do")
	public JSONResult changePwd(String mobile, String pwd, String newPwd) {
		JSONResult result = new JSONResult();
		try {
			Customer customer= customerService.findByMobile(mobile);
			if(customerService.isValid(mobile, pwd)){
				customer.setPwd(newPwd);
				customerService.edit(customer);
				result.setMsg("密码修改成功");
				result.setState(true);
			}else{
				result.setMsg("用户不存在或者密码错误");
				result.setState(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("服务器异常，密码修改失败");
			result.setState(true);
		}
		return result;
	}

	/**
	 * 请求生成4位忘记密码验证码，并以短信的方式发送给用户 接口映射到/customer/requestForgetCode.do
	 * 
	 * @param mobile
	 *            手机号码
	 * @return true表示验证码生成成功，false表示生成失败
	 */
	@RequestMapping(value = "/customer/requestForgetCode.do")
	public JSONResult requestForgetCode(String mobile) {
		// 在forgetCodes中查找是否有验证码，若有判断其是否在1分钟之前创建，若是1分钟内创建的，返回false
		// 生成验证码，将其设置到forgetCodes中
		// 将验证码发送到用户的手机，返回true
		// 若上述逻辑执行异常返回false
		JSONResult result = new JSONResult();
		
		SMSCode code = forgetCodes.get(mobile);
		if (code != null && !code.isAfter1m()) {

			result.setMsg("1分钟内不能申请新验证码");
			result.setState(false);

		} else {
			code = new SMSCode();
			forgetCodes.put(mobile, code);
			logger.debug("为手机{}生成的验证码为{}",
					new Object[] { mobile, code.getValue() });

			if (sendSMSCode(mobile, code.getValue())) {
				result.setMsg("验证码生成成功");
				result.setState(true);
			} else {
				result.setMsg("验证码发送失败");
				result.setState(false);
			}
		}
		
		return result;
	}

	/**
	 * 重置用户密码
	 * 
	 * @param mobile
	 *            手机号
	 * @param code
	 *            短信验证码
	 * @param newPwd
	 *            新密码
	 * @return true表示修改成功，false表示修改失败
	 */
	@RequestMapping(value = "/customer/resetPwd.do")
	public JSONResult resetPwd(String mobile, String code, String newPwd) {
		JSONResult result = new JSONResult();
		SMSCode smsCode = forgetCodes.get(mobile);
		if (smsCode == null || !smsCode.getValue().equals(code)
				|| !smsCode.isValid()) {
			result.setMsg("验证码不合法");
			result.setState(false);
		} else {
			try {
				Customer customer = customerService.findByMobile(mobile);
				customer.setPwd(newPwd);
				customerService.edit(customer);
				result.setMsg("重置密码成功");
				result.setState(true);
			} catch (Exception e) {
				e.printStackTrace();
				result.setMsg("服务器异常，重置密码失败");
				result.setState(false);
			}
		}
		return result;
	}
	/**
	 * 获取用户信息
	 * @param mobile 手机号
	 * @param pwd 密码
	 * @return 用户信息
	 */
	public Customer getCustomer(String mobile,String pwd)
	{
		//TODO 待实现
		return null;
	}
}
