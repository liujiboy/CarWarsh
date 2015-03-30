package cn.edu.cqu.carwarsh.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 洗 车站点
 * @author pxj
 *
 */
@Entity
public class WarshStation {
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 站点名称
	 */
	@Column(nullable=false,unique=true,length=100)
	private String stationName;
	/**
	 * 所署行政区
	 */
	@Column(nullable=false,length=100)
	private String area;
	/**
	 * 经度
	 */
	@Column(nullable=false,length=30)
	private String latitude;
	/**
	 * 纬度
	 */
	@Column(nullable=false,length=30)
	private String longitude;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((stationName == null) ? 0 : stationName.hashCode());
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
		WarshStation other = (WarshStation) obj;
		if (stationName == null) {
			if (other.stationName != null)
				return false;
		} else if (!stationName.equals(other.stationName))
			return false;
		return true;
	}
	
	
}
