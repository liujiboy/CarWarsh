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
	 * 车辆品牌
	 */
	@Column(nullable=false,length=100)
	private String brandName;
	/**
	 * 车辆类别
	 */
	@Column(nullable=false,length=100)
	private String categoryName;
	/**
	 * 车辆型号
	 */
	@Column(nullable=false,length=100)
	private String modelName;
	/**
	 * 预约的洗车时间
	 */
	@Column(nullable=false)
	private Date bookTime;
	/**
	 * 洗车类型
	 */
	@Column(nullable=false,length=100)
	private String warshTypeName;
	/**
	 * 订单状态
	 */
	@Column(nullable=false)
	private Boolean status;
	/**
	 * 记录操作订单时间，如下单、出单、完成订单.
	 */
	@Column(nullable=false)
	private Date orderTime;
	/**
	 * 原始订单价格
	 */
	@Column(nullable=false)
	private Double price;
	/**
	 * 最张订单总价
	 */
	@Column(nullable=false)
	private Double priceSum;
	/**
	 * 洗车站点 
	 */
	@Column(nullable=false,unique=true)
	private WarshStation warshStation;
	/**
	 * 关联洗车工 
	 */
	@ManyToOne
	//@Column(nullable=true,unique=true)
	private Worker worker;
	/**
	 * 详细地址
	 */
	@Column(nullable=false,length=255)
	private String addName;
	/**
	 * 经度
	 */
	@Column(nullable=false)
	private Double addLatitude;
	/**
	 * 纬度
	 */
	@Column(nullable=false)
	private Double addLongitude;
	/**
	 * 实际洗车开始时间
	 */
	@Column(nullable=true)
	private Date startTime;
	/**
	 * 实际洗车结束时间
	 */
	@Column(nullable=true)
	private Date completeTime;
	/**
	 * 洗车前照片1，相对路径
	 */
	@Column(nullable=true,length=50)
	private String startPicture1;
	/**
	 * 洗车前照片2，相对路径
	 */
	@Column(nullable=true,length=50)
	private String startPicture2;
	/**
	 * 洗车后照片1，相对路径
	 */
	@Column(nullable=true,length=50)
	private String completePicture1;
	/**
	 * 洗车后照片2,相对路径
	 */
	@Column(nullable=true,length=50)
	private String completePicture2;
	/**
	 * 备注
	 */
	@Column(nullable=true,length=255)
	public String message;
	/**
	 * 评分
	 */
	@Column(nullable=true)
	public int rank;
	/**
	 * 评价
	 */
	@Column(nullable=true,length=255)
	public String remark;
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
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Date getBookTime() {
		return bookTime;
	}

	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}

	public String getWarshTypeName() {
		return warshTypeName;
	}

	public void setWarshTypeInfo(String warshTypeName) {
		this.warshTypeName = warshTypeName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getTime() {
		return orderTime;
	}

	public void setTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPriceSum() {
		return priceSum;
	}

	public void setPriceSum(Double priceSum) {
		this.priceSum = priceSum;
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
	
	public String addName() {
		return addName;
	}
	public void setDetailAddress(String addName) {
		this.addName = addName;
	}
	public Double getAddLatitude() {
		return addLatitude;
	}
	public void setAddLatitude(Double addLatitude) {
		this.addLatitude = addLatitude;
	}
	public Double getAddLongitude() {
		return addLongitude;
	}
	public void setAddLongitude(Double addLongitude) {
		this.addLongitude = addLongitude;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime=startTime;
	}
	public Date getCompletetTime() {
		return completeTime;
	}
	public void setCompleteTime(Date completeTime) {
		this.startTime=completeTime;
	}
	public String getStartPicture1() {
		return startPicture1;
	}
	public void setStartPicture1(String startPicture1) {
		this.startPicture1=startPicture1;
	}
	public String getStartPicture2() {
		return startPicture2;
	}
	public void setStartPicture2(String startPicture2) {
		this.startPicture2=startPicture2;
	}
	public String getCompletePicture1() {
		return completePicture1;
	}
	public void setCompletePicture1(String completePicture1) {
		this.completePicture1=completePicture1;
	}
	public String getCompletePicture2() {
		return completePicture2;
	}
	public void setCompletePicture2(String completePicture2) {
		this.completePicture2=completePicture2;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message=message;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank=rank;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark=remark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((orderTime == null) ? 0 : orderTime.hashCode());
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
		if (orderTime == null) {
			if (other.orderTime != null)
				return false;
		} else if (!orderTime.equals(other.orderTime))
			return false;
		return true;
	}
	
}