package cn.edu.cqu.carwarsh.vos;

import java.util.HashMap;
import java.util.List;
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
	/**
	 * 返回列表全部信息
	 * @param list 列表
	 */
	public void setList(List<?> list){
		this.put("list", list);
	}
	/**
	 * 返回列表特定信息
	 * @param list 列表
	 */
//	public<T> void setThe(Class<T> entityClass){
//		this.put("entityClass", entityClass);
//	}
}
