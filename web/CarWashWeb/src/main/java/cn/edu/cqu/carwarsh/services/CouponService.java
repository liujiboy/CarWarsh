package cn.edu.cqu.carwarsh.services;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.edu.cqu.carwarsh.domains.Coupon;
import cn.edu.cqu.carwarsh.domains.Customer;
/**
 * 优惠券CRUD操作
 * @author liuji
 *
 */
@Service
public class CouponService extends BaseService{
	/**
	 * 根据优惠券类型查找Coupon
	 * @param category 优惠券类型
	 * @return 找到的Coupon
	 * @throws Exception
	 */
	public List findByCategory(String category,Customer customer) throws Exception {
		return  getAll(Coupon.class, "from Coupon where category=? and customer=?",
				category,customer);
	}
	
	/**
	 * 根据优惠券编号查找Coupon
	 * @param couponID 优惠券编号
	 * @return 找到的Coupon
	 * @throws Exception
	 */
	public Coupon findById(Long id) throws Exception {
		return this.getFirst(Coupon.class, "from Conpon where id=?",
				id);
	}
	/**
	 * 添加Coupon
	 * @param Coupon 优惠券信息
	 * @throws Exception
	 */
	@Transactional
	public void add(Coupon coupon) throws Exception{
		this.save(coupon);
	}
	/**
	 *判断Coupon是否过期
	 *过期直接删除
	 *@param coyponID 优惠券ID
	 */
	public Boolean isValid(Long id)
	{
	
		try {
			 Coupon coupon=findById(id);
			 Calendar currentDate = Calendar.getInstance();
			 Calendar EndDate = Calendar.getInstance();
			 EndDate.setTime(coupon.getEndDate());
			if(currentDate.after(EndDate))
			   return false;
			 else
			 {
				 this.delete(coupon);
			  return true;
			  }
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
