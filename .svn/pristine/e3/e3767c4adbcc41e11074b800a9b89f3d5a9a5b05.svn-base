
package com.wzlue.web.controller.member;

import com.wzlue.common.base.R;
import java.util.List;
import java.util.Map;

import com.wzlue.member.entity.MemberRecommendEntity;
import com.wzlue.member.service.MemberRecommendService;
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
 * 会员推荐人列表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-18 16:57:45
 */
@RestController
@RequestMapping("/member/mmemberRecommend")
public class MemberRecommendController extends AbstractController{
	@Autowired
	private MemberRecommendService memberRecommendService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<MemberRecommendEntity> mMemberRecommendList = memberRecommendService.queryList(query);
		int total = memberRecommendService.queryTotal(query);
		
		return R.page(mMemberRecommendList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("contact:mmemberRecommend:info")
	public R info(@PathVariable("id") Long id){
		MemberRecommendEntity mMemberRecommend = memberRecommendService.queryObject(id);
		
		return R.ok().put("mMemberRecommend", mMemberRecommend);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("contact:mmemberRecommend:save")
	public R save(@RequestBody MemberRecommendEntity mMemberRecommend){
		memberRecommendService.save(mMemberRecommend);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("contact:mmemberRecommend:update")
	public R update(@RequestBody MemberRecommendEntity mMemberRecommend){
		memberRecommendService.update(mMemberRecommend);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("contact:mmemberRecommend:delete")
	public R delete(@RequestBody Long[] ids){
		memberRecommendService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
