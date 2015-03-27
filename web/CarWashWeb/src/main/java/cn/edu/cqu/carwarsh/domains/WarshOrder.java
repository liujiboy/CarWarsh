package cn.edu.cqu.carwarsh.domains;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 用户订单
 * 
 * @author liuji
 *
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "coupon_id" }) })
public class WarshOrder {
	/**
	 * 逻辑主键，自增长
	 */
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 与用户关联
	 */
	@ManyToOne(optional = false)
	private Customer customer;
	/**
	 * 与优惠券关联
	 */
	@ManyToOne(optional = false)
	private Coupon coupon;
	/**
	 * 洗 车的车辆信息
	 */
	@Column(nullable=false,length=100)
	private String vehicleInfo;
	/**
	 * 停放地址的信息
	 */
	@Column(nullable=false,length=100)
	private String addressInfo;
	/**
	 * 预约的洗车时间
	 */
	@Column(nullable=false)
	private Date bookTime;
	/**
	 * 洗车类型
	 */
	@Column(nullable=false,length=100)
	private String warshTypeInfo;
	/**
	 * 订单状态
	 */
	@Column(nullable=false,length=100)
	private String status;
	/**
	 * 记录操作订单时间，如下单、出单、完成订单.
	 */
	@Column(nullable=false)
	private Date time;
	/**
	 * 原始订单价格
	 */
	@Column(nullable=false)
	private Double price;
	/**
	 * 优惠信息
	 */
	@Column(nullable=false)
	private Double couponInfo;
	/**
	 * 是否支付
	 */
	@Column(nullable=false)
	private boolean isPay;
	/**
	 * 最张订单总价
	 */
	@Column(nullable=false)
	private Double priceSum;
	/**
	 * 关联洗车站点 
	 */
	@ManyToOne
	//@Column(nullable=false,unique=true)
	private WarshStation warshStation;
	/**
	 * 关联洗车工 
	 */
	@ManyToOne
	//@Column(nullable=true,unique=true)
	private Worker worker;
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
	
	public Coupon getCoupon() {
		return coupon;
	}
	
	public void setCoupon(Coupon coupon) {
		this.coupon=coupon;
	}
	
	public String getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(String vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	public String getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}

	public Date getBookTime() {
		return bookTime;
	}

	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}

	public String getWarshTypeInfo() {
		return warshTypeInfo;
	}

	public void setWarshTypeInfo(String warshTypeInfo) {
		this.warshTypeInfo = warshTypeInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getCouponInfo() {
		return couponInfo;
	}

	public void setCouponInfo(Double couponInfo) {
		this.couponInfo = couponInfo;
	}

	public Double getPriceSum() {
		return priceSum;
	}

	public void setPriceSum(Double priceSum) {
		this.priceSum = priceSum;
	}

	public boolean isPay() {
		return isPay;
	}

	public void setPay(boolean isPay) {
		this.isPay = isPay;
	}

	public WarshStation getWarshStation() {
		return warshStation;
	}

	public void setWarshStation(WarshStation warshStation) {
		this.warshStation = warshStation;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		WarshOrder other = (WarshOrder) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	
}
