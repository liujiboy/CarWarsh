package cn.edu.cqu.carwarsh.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.cqu.carwarsh.domains.Address;
import cn.edu.cqu.carwarsh.domains.Customer;
import cn.edu.cqu.carwarsh.domains.Vehicle;
import cn.edu.cqu.carwarsh.domains.VehicleBrand;
import cn.edu.cqu.carwarsh.domains.VehicleCategory;
import cn.edu.cqu.carwarsh.domains.VehicleModel;
import cn.edu.cqu.carwarsh.services.VehicleService;
import cn.edu.cqu.carwarsh.services.CustomerService;
import cn.edu.cqu.carwarsh.vos.JSONResult;

/**
 * 用户常用洗车车辆服务端接口
 * 
 * @author liuji
 *
 */
@RestController
public class VehicleController {

	/**
	 * 用于输出日志
	 */
	private static Logger logger = LoggerFactory
			.getLogger(VehicleController.class);
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 添加车辆BCM信息
	 * @param BrandName 车辆品牌名称
	 * @param pinyin  品牌首拼
	 * @return
	 */
	@RequestMapping(value="vehicle/addVehicleBrandCategoryModel.do")
	public JSONResult addVehicleBrandCategoryModel(String brandName,String brandPinyin,String categoryName,String modelName,String modelPinyin)
	{
		JSONResult result = new JSONResult();
		try {
			VehicleBrand vehicleBrand=new VehicleBrand();
			VehicleModel vehicleModel=new VehicleModel();
			VehicleCategory vehicleCategory=new VehicleCategory();
			vehicleBrand.setName(brandName);
			vehicleBrand.setPinyin(brandPinyin);
			vehicleCategory.setName(categoryName);
			vehicleModel.setName(modelName);
			vehicleModel.setPinyin(modelPinyin);
			vehicleModel.setBrand(vehicleBrand);
			vehicleModel.setCategory(vehicleCategory);
			vehicleService.addBrand(vehicleBrand);
			vehicleService.addCategory(vehicleCategory);
			vehicleService.addModel(vehicleModel);
			result.setMsg("添加车辆品牌信息成功");
			result.setState(true);
		} catch (Exception e) {			
			e.printStackTrace();
			result.setMsg("添加车辆品牌信息失败");
			result.setState(true);
		}
		return result;
	}
	/**
	 * 添加常用洗车车辆信息
	 * @param mobile 手机号
	 * @param pwd 密码
	 * @param licenseNumber 车牌号
	 * @param color 车辆颜色
	 * @param vehicleBrandName 车辆品牌名称
	 * @param vehicleModelName 车辆型号名称
	 //* @param vehicleCategoryName 车辆类别名称
	 * @return
	 */
	@RequestMapping(value="vehicle/add.do")
	public JSONResult addVehicle(String mobile,String pwd,String licenseNumber,String color,Long modelId)
	{
		JSONResult result = new JSONResult();
		try{
			Customer customer = customerService.findByMobile(mobile);
			VehicleModel vehicleModel=vehicleService.findByModelId(modelId);
			if(customer!=null){
				if(customerService.isValid(mobile, pwd)){
					Vehicle vehicle = new Vehicle();
					vehicle.setCustomer(customer);
					vehicle.setColor(color);
					vehicle.setLicenseNumber(licenseNumber);
					vehicle.setVehicleModel(vehicleModel);
					vehicleService.add(vehicle);
					result.setMsg("添加车辆信息成功");
					result.setState(true);
				}else{
					result.setMsg("密码错误！");
					result.setState(false);
				}
			}else{
				result.setMsg("手机号错误！");
				result.setState(false);
			}
		}catch (Exception e) {
			e.printStackTrace();
			result.setMsg("系统发生异常，添加车辆信息失败！");
			result.setState(true);
		}
		return result;
	}
	/**
	 * 删除常用洗车车辆信息
	 * @param mobile 手机号
	 * @param pwd 密码
	 * @param vehicle 要删除的车辆信息
	 * @return
	 */
	@RequestMapping(value="vehicle/delete.do")
	public JSONResult deleteVehicle(String mobile,String pwd,Long vehicleId)
	{
		JSONResult result = new JSONResult();
		try{
			Customer customer = customerService.findByMobile(mobile);
			
			if(customer.getId()!=null){
				if(customerService.isValid(mobile, pwd)){
					vehicleService.delete(vehicleId);
					result.setMsg("删除车辆信息成功");
					result.setState(true);
				}else{
					result.setMsg("密码错误！");
					result.setState(false);
				}
			}else{
				result.setMsg("手机号错误！");
				result.setState(false);
			}
		}catch (Exception e) {
			e.printStackTrace();
			result.setMsg("系统发生异常，删除车辆信息失败！");
			result.setState(true);
		}
		return result;
	}
	/**
	 * 修改常用洗车车辆信息
	 * @param mobile 手机号
	 * @param pwd 密码
	 * @param licenseNumber 车牌号
	 * @param color 车辆颜色
	 * @param vehicleBrandName 车辆品牌名称
	 * @param vehicleModelName 车辆型号名称
	 * @return
	 */
	@RequestMapping(value="vehicle/edit.do")
	public JSONResult editVehicle(String mobile,String pwd,int vehicleId,String licenseNumber,String color,Long vehicleModelId)
	{
		JSONResult result = new JSONResult();
		try{
			Customer customer = customerService.findByMobile(mobile);
			if(customer!=null){
				if(customerService.isValid(mobile, pwd)){
					List<Vehicle> vehicleList=vehicleService.findByMobileAll(mobile);
					Vehicle vehicle=vehicleList.get(vehicleId-1);
					VehicleModel vehicleModel=vehicleService.findByModelId(vehicleModelId);
					Vehicle newVehicle=new Vehicle();
					newVehicle.setColor(color);
					newVehicle.setLicenseNumber(licenseNumber);
					newVehicle.setVehicleModel(vehicleModel);
					vehicleService.edit(vehicle.getId(),newVehicle);
					result.setMsg("修改车辆信息成功");
					result.setState(true);
				}else{
					result.setMsg("密码错误！");
					result.setState(false);
				}
			}else{
				result.setMsg("手机号错误！");
				result.setState(false);
			}
		}catch (Exception e) {
			e.printStackTrace();
			result.setMsg("系统发生异常，修改车辆信息失败！");
			result.setState(true);
		}
		return result;
	}
}