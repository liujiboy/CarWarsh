package cn.edu.cqu.carwarsh.domains;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 洗车图片
 * @author pxj
 *
 */
@Entity
public class WarshPicture {
	/**
	 * 逻辑主键
	 */
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 图片名     以当前时间命名
	 * 20150320203720.jpg
	 */
	@Column(nullable=false,length=30)
	private String pictureName;
	/**
	 * 上传时间
	 */
	@Column(nullable=false)
	private Date uploadTime;
	/**
	 * 关联洗车工
	 */
	@ManyToOne(optional=false)
	private Worker worker;
	/**
	 * 关联订单
	 */
	@ManyToOne(optional=false)
	private WarshOrder warshOrder;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
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
				+ ((uploadTime == null) ? 0 : uploadTime.hashCode());
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
		WarshPicture other = (WarshPicture) obj;
		if (uploadTime == null) {
			if (other.uploadTime != null)
				return false;
		} else if (!uploadTime.equals(other.uploadTime))
			return false;
		if (worker == null) {
			if (other.worker != null)
				return false;
		} else if (!worker.equals(other.worker))
			return false;
		return true;
	}
	
}
