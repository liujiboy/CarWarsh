package cn.edu.cqu.carwarsh.domains;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
/**
 * 订单消费明细
 * @author pxj
 *
 */
@Entity
public class OrderDetail {
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 生成订单时间
	 */
	@Column(nullable=false)
	private Date createTime;
	/**
	 * 预约洗车时间
	 */
	@Column(nullable=false)
	private Date bookTime;
	/**
	 * 实际洗车开始时间
	 */
	@Column(nullable=false)
	private Date doWarshTime;
	/**
	 * 实际洗车结束时间
	 */
	@Column(nullable=false)
	private Date completeTime;
	/**
	 * 关联洗车工
	 */
	@ManyToOne
	//@Column(nullable=false,unique=true)
	private Worker worker;
	/**
	 * 关联订单
	 */
	@ManyToOne
	//@Column(nullable=false,unique=true)
	private WarshOrder warshOrder;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getBookTime() {
		return bookTime;
	}
	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}
	public Date getDoWarshTime() {
		return doWarshTime;
	}
	public void setDoWarshTime(Date doWarshTime) {
		this.doWarshTime = doWarshTime;
	}
	public Date getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	public WarshOrder getWarshOrder() {
		return warshOrder;
	}
	public void setWarshOrder(WarshOrder warshOrder) {
		this.warshOrder = warshOrder;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((warshOrder == null) ? 0 : warshOrder.hashCode());
		result = prime * result + ((worker == null) ? 0 : worker.hashCode());
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
		OrderDetail other = (OrderDetail) obj;
		if (warshOrder == null) {
			if (other.warshOrder != null)
				return false;
		} else if (!warshOrder.equals(other.warshOrder))
			return false;
		if (worker == null) {
			if (other.worker != null)
				return false;
		} else if (!worker.equals(other.worker))
			return false;
		return true;
	}
	
	
	
}
