
package com.wzlue.web.controller.contact;

import com.wzlue.common.base.R;
import java.util.List;
import java.util.Map;

import com.wzlue.contact.entity.ContactEntity;
import com.wzlue.contact.service.ContactService;
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
 * 
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-03-27 16:58:22
 */
@RestController
@RequestMapping("/contact/wcontact")
public class ContactController extends AbstractController{
	@Autowired
	private ContactService wContactService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ContactEntity> wContactList = wContactService.queryList(query);
		int total = wContactService.queryTotal(query);
		return R.page(wContactList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("contact:wcontact:info")
	public R info(@PathVariable("id") Long id){
		ContactEntity wContact = wContactService.queryObject(id);
		
		return R.ok().put("wContact", wContact);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("contact:wcontact:save")
	public R save(@RequestBody ContactEntity wContact){
		wContactService.save(wContact);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("contact:wcontact:update")
	public R update(@RequestBody ContactEntity wContact){
		wContactService.update(wContact);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("contact:wcontact:delete")
	public R delete(@RequestBody Long[] ids){
		wContactService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
