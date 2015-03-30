package cn.edu.cqu.carwarsh.vos;

import java.util.HashMap;
/**
 * JSON返回数据格式
 * @author liuji
 *
 */
public class JSONResult extends HashMap<String,Object>{

	private static final long serialVersionUID = -2063495440061515228L;
	
	/**
	 * 设置消息
	 * @param msg 消息
	 */
	public void setMsg(String msg) {
		this.put("msg", msg);
	}
	/**
	 * 设置状态
	 * @param state	状态
	 */
	public void setState(Boolean state) {
		this.put("state", state);
	}
	
	
}
