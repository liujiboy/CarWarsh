package cn.edu.cqu.carwarsh.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.edu.cqu.carwarsh.domains.Customer;
/**
 * 对Customer表的CRUD操作
 * @author liuji
 *
 */
@Service
public class CustomerService extends BaseService {
	/**
	 * 根据手机号码查找Customer
	 * @param mobile 手机号码
	 * @return 找到的Customer
	 * @throws Exception
	 */
	public Customer findByMobile(String mobile) throws Exception {
		return this.getFirst(Customer.class, "from Customer where mobile=?",
				mobile);
	}
	/**
	 * 添加Customer
	 * @param customer 用户信息
	 * @throws Exception
	 */
	@Transactional
	public void add(Customer customer) throws Exception{
		this.save(customer);
	}
	/**
	 * 修改Customer
	 * @param newCustomer 新用户信息
	 * @throws Exception
	 */
	@Transactional
	public void edit(Customer newCustomer) throws Exception{
		this.update(newCustomer);
	}
	/**
	 * 判断用户是否有效
	 * @param mobile 手机号
	 * @param pwd	密码
	 * @return	true有效，false无效
	 */
	public Boolean isValid(String mobile,String pwd)
	{
		try {
			Customer c=findByMobile(mobile);
			return c.getPwd().equals(pwd);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
