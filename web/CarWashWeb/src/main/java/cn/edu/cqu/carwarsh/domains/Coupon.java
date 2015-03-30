package cn.edu.cqu.carwarsh.domains;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
/**
 * 优惠券
 * @author liuji
 *
 */
@Entity
public class Coupon {
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
	 *优惠券类型
	 */
	@Column(nullable=false)
	private String category;
	/**
	 * 优惠券数值
	 */
	@Column(nullable=false)
	private double Value;
	/**
	 *优惠券截止日期
	 *2015-12-12
	 */
	@Column(nullable=false,length=10)
	private Date endDate;
	/**
	 *优惠标题
	 */
	@Column(nullable=false)
	private String name;
	
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category=category;
	}
	public double getCouValue() {
		return Value;
	}
	public void setCouMoney(double Value) {
		this.Value=Value;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate=endDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Coupon other = (Coupon) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
