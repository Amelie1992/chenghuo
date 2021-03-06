
package com.wzlue.web.controller.integral;

import com.wzlue.common.base.R;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wzlue.web.controller.sys.AbstractController;

import com.wzlue.integral.entity.IntegralCardEntity;
import com.wzlue.integral.service.IntegralCardService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 积分充值卡
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-03 17:43:00
 */
@RestController
@RequestMapping("/integral/integralcard")
public class IntegralCardController extends AbstractController{
	@Autowired
	private IntegralCardService integralCardService;


    /**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<IntegralCardEntity> integralCardList = integralCardService.queryList(query);
		int total = integralCardService.queryTotal(query);
		
		return R.page(integralCardList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("integral:integralcard:info")
	public R info(@PathVariable("id") Long id){
		IntegralCardEntity integralCard = integralCardService.queryObject(id);
		
		return R.ok().put("integralCard", integralCard);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("integral:integralcard:save")
	public R save(@RequestBody IntegralCardEntity integralCard){
		integralCardService.save(integralCard);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("integral:integralcard:update")
	public R update(@RequestBody IntegralCardEntity integralCard){
		integralCardService.update(integralCard);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("integral:integralcard:delete")
	public R delete(@RequestBody Long[] ids){
		integralCardService.deleteBatch(ids);
		
		return R.ok();
	}


    /**
     * 激活
     */
    @RequestMapping("/activate")
    @RequiresPermissions("integral:integralcard:update")
    public R activate(@RequestBody Long[] ids){
        integralCardService.activateBatch(ids);

        return R.ok();
    }

}
