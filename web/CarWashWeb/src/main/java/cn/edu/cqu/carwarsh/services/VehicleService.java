package cn.edu.cqu.carwarsh.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.edu.cqu.carwarsh.domains.Address;
import cn.edu.cqu.carwarsh.domains.Vehicle;
import cn.edu.cqu.carwarsh.domains.VehicleBrand;
import cn.edu.cqu.carwarsh.domains.VehicleCategory;
import cn.edu.cqu.carwarsh.domains.VehicleModel;

/**
 * 车辆信息的CRUD方法
 * @author liuji
 *
 */
@Service
public class VehicleService extends BaseService {
	/**
	 * 根据手机号码查找所有的Vehicle
	 * @param mobile 手机号码
	 * @return 找到的List<Vehicle>
	 * @throws Exception
	 */
	public List<Vehicle> findByMobileAll(String mobile) throws Exception {
		return this.getAll(Vehicle.class, "from Vehicle where customer.mobile=?",mobile);
	}
	/**
	 * 根据vehicleID码查找Vehicle
	 * @param vehicleID  车辆信息ID
	 * @return 找到的Vehicle
	 * @throws Exception
	 */
	public Vehicle findByVehicleId(Long vehicleId) throws Exception {
		return this.get(Vehicle.class, vehicleId);
	}
	/**
	 * 根据modelId查找VehicleModel
	 * @param modelId 车辆型号ID
	 * @return 找到的VehicleModel
	 * @throws Exception
	 */
	public VehicleModel findByModelId(Long modelId) throws Exception {
		return this.get(VehicleModel.class, modelId);
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
	 * 添加vehicleBrand
	 * @param Vehicle 车辆信息
	 * @throws Exception
	 */
	@Transactional
	public void addBrand(VehicleBrand VehicleB) throws Exception{
		this.save(VehicleB);
	}
	/**
	 * 添加vehicleModel
	 * @param Vehicle 车辆信息
	 * @throws Exception
	 */
	@Transactional
	public void addModel(VehicleModel VehicleM) throws Exception{
		this.save(VehicleM);
	}
	/**
	 * 添加vehicleBrand
	 * @param Vehicle 车辆信息
	 * @throws Exception
	 */
	@Transactional
	public void addCategory(VehicleCategory VehicleC) throws Exception{
		this.save(VehicleC);
	}
	/**
	 * 修改Vehicle
	 * @param newVehicle 新车辆信息
	 * @throws Exception
	 */
	@Transactional
	public void edit(Long id,Vehicle newVehicle) throws Exception{
		Vehicle vehicle=this.get(Vehicle.class, id);
		vehicle.setColor(newVehicle.getColor());
		vehicle.setLicenseNumber(newVehicle.getLicenseNumber());
		vehicle.setVehicleModel(newVehicle.getVehicleModel());
		this.update(vehicle);
	}
	/**
	 * 删除Vehicle
	 * @param Vehicle 车辆信息
	 * @throws Exception
	 */
	@Transactional
	public void delete(Long id) throws Exception{
		Vehicle vehicle=this.get(Vehicle.class, id);
		this.delete(vehicle);
	}
}
