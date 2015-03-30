package cn.edu.cqu.carwarsh.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 余额帐户
 * @author liuji
 *
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "customer_id" }) })
public class Account {
	/**
	 * 逻辑主键，自增长
	 */
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 与用户关联
	 */
	@ManyToOne(optional=false)
	private Customer customer;
	/**
	 * 余额
	 */
	@Column(nullable=false)
	private Double value;
	/**
	 * 
	 * 支付密码
	 */
	@Column(nullable=false,length=50)
	private String pwd;
	/**
	 * 
	 * 积分
	 */
	@Column(nullable=false,length=50)
	private Double point;
	
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Double getPoint() {
		return point;
	}
	public void setPoint(Double point) {
		this.point=point;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
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
		Account other = (Account) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		return true;
	}
	
}
