package cn.edu.cqu.carwarsh.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.edu.cqu.carwarsh.domains.Vehicle;
/**
 * 车辆信息的CRUD方法
 * @author liuji
 *
 */
@Service
public class VehicleService extends BaseService {
	/**
	 * 根据手机号码查找Vehicle
	 * @param mobile 手机号码
	 * @return 找到的Vehicle
	 * @throws Exception
	 */
	public Vehicle findByMobile(String mobile) throws Exception {
		return this.getFirst(Vehicle.class, "from Vehicle where mobile=?",
				mobile);
	}
	/**
	 * 添加vehicle
	 * @param Vehicle 车辆信息
	 * @throws Exception
	 */
	@Transactional
	public void add(Vehicle Vehicle) throws Exception{
		this.save(Vehicle);
	}
	/**
	 * 修改Vehicle
	 * @param newVehicle 新车辆信息
	 * @throws Exception
	 */
	@Transactional
	public void edit(Vehicle newVehicle) throws Exception{
		this.update(newVehicle);
	}
	/**
	 * 删除Vehicle
	 * @param Vehicle 车辆信息
	 * @throws Exception
	 */
	@Transactional
	public void delete(Vehicle Vehicle) throws Exception{
		this.delete(Vehicle);
	}
}
