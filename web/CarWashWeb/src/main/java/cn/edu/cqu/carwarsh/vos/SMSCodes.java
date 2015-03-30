package cn.edu.cqu.carwarsh.vos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 短信验证码集合，增加了线程安全性
 * @author liuji
 *
 */
public class SMSCodes {
	/**
	 * 验证码
	 */
	private Map<String, SMSCode> codes = new HashMap<String, SMSCode>();
	/**
	 * 获取验证码
	 * @param mobile 手机号
	 * @return 返回验证码
	 */
	public SMSCode get(String mobile) {
		synchronized (codes) {
			return codes.get(mobile);
		}
	}
	/**
	 * 添加验证码
	 * @param mobile	手机号
	 * @param code	验证码
	 */
	public void put(String mobile, SMSCode code) {
		synchronized (codes) {
			codes.put(mobile, code);
		}
	}
	/**
	 * 移除验证码
	 * @param mobile		手机号
	 */
	public void remove(String mobile)
	{
		synchronized (codes) {
			codes.remove(mobile);
		}
	}
	/**
	 * 移除过期的验证码
	 */
	public void removeInValid() {
		synchronized (codes) {
			List<String> removes=new ArrayList<String>();
			for (String mobile : codes.keySet()) {
				SMSCode code = codes.get(mobile);
				if (!code.isValid()) {
					removes.add(mobile);
				}
			}
			for(String mobile:removes)
			{
				codes.remove(mobile);
			}
		}
	}
}
