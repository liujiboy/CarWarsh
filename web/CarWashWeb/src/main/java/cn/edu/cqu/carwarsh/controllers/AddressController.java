package cn.edu.cqu.carwarsh.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.cqu.carwarsh.domains.Address;
import cn.edu.cqu.carwarsh.domains.Customer;
import cn.edu.cqu.carwarsh.services.AddressService;
import cn.edu.cqu.carwarsh.services.CustomerService;
import cn.edu.cqu.carwarsh.vos.JSONResult;
/**
 * 用户常用洗车地址服务端接口
 * @author liuji
 *
 */
@RestController
public class AddressController {
	/**
	 * 用于输出日志
	 */
	private static Logger logger = LoggerFactory
			.getLogger(AddressController.class);
	@Autowired
	private AddressService addressService;
	@Autowired
	private CustomerService customerService;
	/**
	 * 添加洗车地址
	 * @param mobile 手机号
	 * @param pwd 密码
	 * @param address 详细地址
	 * @param remark 备注
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @return
	 */
	@RequestMapping(value = "/address/add.do")
	public JSONResult addAddress(String mobile,String pwd,String address,Double longitude,Double latitude)
	{
		JSONResult result = new JSONResult();
		try {
			Customer customer = customerService.findByMobile(mobile);
			if(customer!=null){
				if(customerService.isValid(mobile, pwd)){
					Address addr = new Address();
					addr.setCustomer(customer);
					addr.setDetailAddress(address);
					addr.setLatitude(latitude);
					addr.setLongitude(longitude);
					addressService.add(addr);
					result.setMsg("添加地址成功");
					result.setState(true);
				}else{
					result.setMsg("密码错误");
					result.setState(false);
				}
			}else{
				result.setMsg("手机号错误");
				result.setState(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("系统发生异常，添加地址失败！");
			result.setState(true);
		}
		return result;
	}
	/**
	 * 删除洗车地址
	 * @param mobile 手机号
	 * @param pwd 密码
	 * @return
	 */
	@RequestMapping(value = "/address/delete.do")
	public JSONResult deleteAddress(String mobile,String pwd)
	{
		JSONResult result = new JSONResult();
		try {
			Customer customer = customerService.findByMobile(mobile);
			if(customer!=null){
				if(customerService.isValid(mobile, pwd)){
					addressService.delete(addressService.findByMobile(mobile));
					result.setMsg("删除地址成功");
					result.setState(true);
				}else{
					result.setMsg("密码错误");
					result.setState(false);
				}
			}else{
				result.setMsg("手机号错误");
				result.setState(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("系统发生异常，删除地址失败！");
			result.setState(true);
		}
		return result;
	}
	/**
	 * 修改洗车地址
	 * @param mobile 手机号
	 * @param pwd 密码
	 * @param new_Address 新的详细地址
	 * @param newLongitude 新的经度
	 * @param newLatitude 新的纬度
	 * @return
	 */
	@RequestMapping(value = "/address/edit.do")
	public JSONResult editAddress(String mobile,String pwd,String new_Address,Double newLongitude,Double newLatitude)
	{
		JSONResult result = new JSONResult();
		try {
			Customer customer = customerService.findByMobile(mobile);
			if(customer!=null){
				if(customerService.isValid(mobile, pwd)){
					Address newAddress=addressService.findByMobile(mobile);
					newAddress.setCustomer(customer);
					newAddress.setDetailAddress(new_Address);
					newAddress.setLongitude(newLongitude);
					newAddress.setLatitude(newLatitude);
					addressService.edit(newAddress);
					result.setMsg("修改地址成功");
					result.setState(true);
				}else{
					result.setMsg("密码错误");
					result.setState(false);
				}
			}else{
				result.setMsg("手机号错误");
				result.setState(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("系统发生异常，修改地址失败！");
			result.setState(true);
		}
		return result;
	}

}
