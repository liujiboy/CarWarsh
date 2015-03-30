package cn.edu.cqu.carwarsh.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 价格表
 * @author liuji
 *
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "category_id","type_id" }) })
public class PriceList {
	/**
	 * 逻辑主键，自增长
	 */
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 车辆类别
	 */
	@ManyToOne(optional=false)
	private VehicleCategory category;
	/**
	 * 洗车类别
	 */
	@ManyToOne(optional=false)
	private WarshType type;
	@Column(nullable=false)
	private Double price;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public VehicleCategory getCategory() {
		return category;
	}
	public void setCategory(VehicleCategory category) {
		this.category = category;
	}
	public WarshType getType() {
		return type;
	}
	public void setType(WarshType type) {
		this.type = type;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		PriceList other = (PriceList) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
