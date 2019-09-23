
package com.wzlue.web.controller.goods;

import com.wzlue.common.base.R;
import java.util.List;
import java.util.Map;

import com.wzlue.goods.entity.MerchantAddressEntity;
import com.wzlue.goods.service.MerchantAddressService;
import com.wzlue.sys.service.SysCityInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wzlue.web.controller.sys.AbstractController;

import com.wzlue.common.base.Query;




/**
 * 商户发货地址
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-09 15:47:43
 */
@RestController
@RequestMapping("/goods/merchantAddress")
public class MerchantAddressController extends AbstractController{
	@Autowired
	private MerchantAddressService merchantAddressService;

	@Autowired
	private SysCityInfoService sysCityInfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<MerchantAddressEntity> merchantAddressList = merchantAddressService.queryList(query);
		int total = merchantAddressService.queryTotal(query);
		
		return R.page(merchantAddressList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goods:merchantAddress:info")
	public R info(@PathVariable("id") Long id){
		MerchantAddressEntity merchantAddress = merchantAddressService.queryObject(id);
		
		return R.ok().put("merchantAddress", merchantAddress);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goods:merchantAddress:save")
	public R save(@RequestBody MerchantAddressEntity merchantAddress){
		merchantAddressService.save(merchantAddress);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goods:merchantAddress:update")
	public R update(@RequestBody MerchantAddressEntity merchantAddress){
		merchantAddressService.update(merchantAddress);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goods:merchantAddress:delete")
	public R delete(@RequestBody Long[] ids){
		merchantAddressService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
