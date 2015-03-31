package cn.edu.cqu.carwarsh.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * 车辆型号
 * @author liuji
 *
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name","brand_id" }) })
public class VehicleModel {

		/**
		 * 逻辑主键，自增长
		 */
		@Id
		@GeneratedValue
		private Long id;
		/**
		 * 车辆品牌关联
		 */
		@ManyToOne(optional=false)
		private VehicleBrand brand;
		/**
		 * 车辆类型关联
		 */
		@ManyToOne(optional=false)
		private VehicleCategory category;
		/**
		 * 车型名，例如福克斯
		 */
		@Column(nullable=false,length=50)
		private String name;
		/**
		 * 车型名拼音首字母
		 */
		@Column(nullable=false,length=1)
		private String pinyin;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
		public VehicleBrand getBrand() {
			return brand;
		}
		public void setBrand(VehicleBrand brand) {
			this.brand = brand;
		}
		
		public VehicleCategory getCategory() {
			return category;
		}
		public void setCategory(VehicleCategory category) {
			this.category = category;
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
			result = prime * result + ((getBrand() == null) ? 0 : getBrand().hashCode());
			result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
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
			VehicleModel other = (VehicleModel) obj;
			if (getBrand() == null) {
				if (other.getBrand() != null)
					return false;
			} else if (!getBrand().equals(other.getBrand()))
				return false;
			if (getName() == null) {
				if (other.getName() != null)
					return false;
			} else if (!getName().equals(other.getName()))
				return false;
			return true;
		}
		
		
}
