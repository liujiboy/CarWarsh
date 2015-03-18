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
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((brand == null) ? 0 : brand.hashCode());
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
			VehicleModel other = (VehicleModel) obj;
			if (brand == null) {
				if (other.brand != null)
					return false;
			} else if (!brand.equals(other.brand))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		
}
