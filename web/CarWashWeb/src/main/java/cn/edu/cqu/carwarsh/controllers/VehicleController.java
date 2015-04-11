package cn.edu.cqu.carwarsh.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.cqu.carwarsh.domains.Vehicle;
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
	 * 添加常用洗车车辆信息
	 * @param mobile 手机号
	 * @param pwd 密码
	 * @param licenseNumber 车牌号
	 * @param color 车辆颜色
	 * @param vehicleModelId 车辆型号ID
	 * @return
	 */
	@RequestMapping(value="vehicle/add.do")
	public JSONResult addVehicle(String mobile,String pwd,String licenseNumber,String color,Long vehicleModelId)
	{
		JSONResult result = new JSONResult();
		try{
			    VehicleModel vehicleModel=vehicleService.findByModelId(vehicleModelId);			
				if(customerService.isValid(mobile, pwd)){
					Vehicle vehicle = new Vehicle();
					vehicle.setCustomer(customerService.findByMobile(mobile));
					vehicle.setColor(color);
					vehicle.setLicenseNumber(licenseNumber);
					vehicle.setVehicleModel(vehicleModel);
					vehicleService.add(vehicle);
					result.setMsg("添加车辆信息成功");
					result.setState(true);
				}else{
					result.setMsg("系统错误,添加车辆信息失败！");
					result.setState(false);
				}
		}catch (Exception e) {
			e.printStackTrace();
			result.setMsg("系统错误,添加车辆信息失败！");
			result.setState(true);
		}
		return result;
	}
	/**
	 * 删除常用洗车车辆信息
	 * @param mobile 手机号
	 * @param pwd 密码
	 * @param vehicleId 要删除的车辆ID
	 * @return
	 */
	@RequestMapping(value="vehicle/delete.do")
	public JSONResult deleteVehicle(String mobile,String pwd,Long vehicleId)
	{
		JSONResult result = new JSONResult();
		try{			
				if(customerService.isValid(mobile, pwd)){
					vehicleService.delete(vehicleId);
					result.setMsg("删除车辆信息成功");
					result.setState(true);
				}else{
					result.setMsg("系统错误,删除车辆信息失败！");
					result.setState(false);
				}
		}catch (Exception e) {
			e.printStackTrace();
			result.setMsg("系统错误,删除车辆信息失败！");
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
	 * @param vehicleModelId 车辆型号ID
	 * @return
	 */
	@RequestMapping(value="vehicle/edit.do")
	public JSONResult editVehicle(String mobile,String pwd,int vehicleId,String licenseNumber,String color,Long vehicleModelId)
	{
		JSONResult result = new JSONResult();
		try{
				if(customerService.isValid(mobile, pwd)){
					List<Vehicle> vehicleList=vehicleService.findByMobileAll(mobile);
					Vehicle vehicle=vehicleList.get(vehicleId);
					VehicleModel vehicleModel=vehicleService.findByModelId(vehicleModelId);
					Vehicle newVehicle=new Vehicle();
					newVehicle.setColor(color);
					newVehicle.setLicenseNumber(licenseNumber);
					newVehicle.setVehicleModel(vehicleModel);
					vehicleService.edit(vehicle.getId(),newVehicle);
					result.setMsg("修改车辆信息成功");
					result.setState(true);
				}else{
					result.setMsg("系统错误,编辑车辆信息失败！");
					result.setState(false);
				}
		}catch (Exception e) {
			e.printStackTrace();
			result.setMsg("系统错误,编辑车辆信息失败！");
			result.setState(true);
		}
		return result;
	}
	/**
	 * 列出所有的车辆信息
	 * @param mobile 手机号
	 * @param pwd 密码
	 * @return
	 */
	@RequestMapping(value = "/vehicle/listAll.do")
	public JSONResult listAllAddress(String mobile,String pwd)
	{
		JSONResult result = new JSONResult();
		try {
			if (customerService.isValid(mobile, pwd)) {
				List<Vehicle> vehicleList=vehicleService.findByMobileAll(mobile);
			 	result.setList(vehicleList);			
			}			
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("系统错误,列出车辆信息失败！");
			result.setState(true);
		}
		return result;
	}
	/**
	 * 列出指定的车辆信息
	 * @param mobile 手机号
	 * @param pwd 密码
	 * @param vehicleId 车辆ID
	 * @return
	 */
	@RequestMapping(value = "/vehicle/listTheOne.do")
	public JSONResult listTheOneAddress(String mobile,String pwd,Long vehicleId)
	{
		JSONResult result = new JSONResult();
		try {
			if (customerService.isValid(mobile, pwd)) {
				List<Vehicle> vehicleList=new ArrayList<Vehicle>();
				vehicleList.add(vehicleService.findByVehicleId(vehicleId));
				result.setList(vehicleList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("系统错误,列出车辆信息失败！");
			result.setState(true);
		}
		return result;
	}
}
