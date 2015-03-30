package cn.edu.cqu.carwarsh.vos;

import java.util.Date;
import java.util.Random;

/**
 * 手机验证码
 * @author liuji
 *
 */
public class SMSCode {
	/**
	 * 验证码值
	 */
	private String value="";
	/**
	 * 创建时间
	 */
	private Date created;

	public SMSCode()
	{
		
		created=new Date();
		//产生6位验证码
		Random r=new Random();
		for(int i=0;i<4;i++)
		{
			value+=r.nextInt(10);
		}
	}
	/**
	 * 验证码是否过期
	 * @return true表示过期，false表示不过期
	 */
	public Boolean isValid()
	{
		Date now=new Date();
		Long time=now.getTime()-created.getTime();
		//默认10分钟后过期
		return time<=600000;
	}
	/**
	 * 验证码是否在1分钟之前创建
	 * @return true表示在1分钟前创建，false表示不是
	 */
	public Boolean isAfter1m()
	{
		Date now=new Date();
		Long time=now.getTime()-created.getTime();
		return time>60000;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SMSCode other = (SMSCode) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
}
