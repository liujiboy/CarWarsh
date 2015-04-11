package cn.edu.cqu.carwarsh.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 * 车辆品牌
 * @author liuji
 *
 */
@Entity
public class VehicleBrand {
	/**
	 * 逻辑主键，自增长
	 */
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 品牌名，例如福特
	 */
	@Column(nullable=false,unique=true,length=50)
	private String name;
	/**
	 * 品牌名拼音首字母
	 */
	@Column(nullable=false,length=1)
	private String pinyin;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		VehicleBrand other = (VehicleBrand) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
