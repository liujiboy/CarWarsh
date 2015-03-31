package cn.edu.cqu.carwarsh.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
/**
 * 洗车工
 * @author liuji
 *
 */
@Entity
public class Worker {
	/**
	 * 逻辑主键，自增长
	 */
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 工号，同时也是登录名，物理主键,长度13位
	 */
	@Column(nullable=false,unique=true,length=6)
	private String wid;
	/**
	 * 密码，经过MD5加密,长度50位
	 */
	@Column(nullable=false,length=50)
	private String pwd;
	/**
	 * 姓名
	 */
	@Column(nullable=true,length=50)
	private String name;
	/**
	 *职工状态，表示是否离职
	 */
	@Column(nullable=true)
	private Boolean status;
	/**
	 *关联洗车站点
	 */
	@ManyToOne(optional=false)
	private WarshStation warshStation;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setName(Boolean status) {
		this.status = status;
	}
	public WarshStation getWarshStation() {
		return warshStation;
	}
	public void setWarshStation(WarshStation warshStation) {
		this.warshStation = warshStation;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((wid == null) ? 0 : wid.hashCode());
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
		Worker other = (Worker) obj;
		if (wid == null) {
			if (other.wid != null)
				return false;
		} else if (!wid.equals(other.wid))
			return false;
		return true;
	}

	
}
