package cn.edu.cqu.carwarsh.services;

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
		return this.getFirst(Address.class, "from Address where mobile=?",
				mobile);
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
	public void edit(Address newAddress) throws Exception{
		this.update(newAddress);
	}
	
	
}
