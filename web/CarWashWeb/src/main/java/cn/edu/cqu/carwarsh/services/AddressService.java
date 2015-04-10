package cn.edu.cqu.carwarsh.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.edu.cqu.carwarsh.domains.Address;
/**
 * 常用地址的CRUD方法
 * @author liuji
 *
 */
@Service
public class AddressService extends BaseService {
	/**
	 * 根据手机号码查找Address
	 * @param mobile 手机号码
	 * @return 找到的Address
	 * @throws Exception
	 */
	public Address findByMobile(String mobile) throws Exception {
		return this.getFirst(Address.class, "from Address where customer.mobile=?",mobile);
	}
	/**
	 * 根据手机号码查找所有的Address
	 * @param mobile 手机号码
	 * @return 找到的List<Address>
	 * @throws Exception
	 */
	public List<Address> findByMobileAll(String mobile) throws Exception {
		return this.getAll(Address.class, "from Address where customer.mobile=?",mobile);
	}
	/**
	 * 根据Id查找具体某条指令
	 * @param addressID 表Address的ID值
	 * @return 找到的Address
	 * @throws Exception
	 */
	public Address findByAddressId(Long id) throws Exception{
		return this.getFirst(Address.class, "from Address where id=?", id);
	}
	/**
	 * 添加Address
	 * @param Address 用户信息
	 * @throws Exception
	 */
	@Transactional
	public void add(Address Address) throws Exception{
		this.save(Address);
	}
	/**
	 * 修改Address
	 * @param newAddress 新用户信息
	 * @throws Exception
	 */
	@Transactional
	public void edit(Long id,Address newAddress) throws Exception{
		Address address=this.get(Address.class, id);
		address.setLatitude(newAddress.getLatitude());
		address.setLongitude(newAddress.getLongitude());
		address.setName(newAddress.getName());
		this.update(address);
	}
	/**
	 * 删除Address
	 * @param Address 用户信息
	 * @throws Exception
	 */
	@Transactional
	public void delete(Long id) throws Exception{
		Address address=this.get(Address.class, id);
		this.delete(address);
	}
	
}
