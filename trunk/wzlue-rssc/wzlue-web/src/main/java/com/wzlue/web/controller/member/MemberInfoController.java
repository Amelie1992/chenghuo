
package com.wzlue.web.controller.member;

import com.wzlue.common.base.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wzlue.member.dao.MemberInfoDao;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wzlue.web.controller.sys.AbstractController;

import com.wzlue.member.entity.MemberInfoEntity;
import com.wzlue.member.service.MemberInfoService;
import com.wzlue.common.utils.PageUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;




/**
 * 用户详情
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 20:58:06
 */
@RestController
@RequestMapping("/member/memberinfo")
public class MemberInfoController extends AbstractController{
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private MemberInfoDao memberInfoDao;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<MemberInfoEntity> memberInfoList = memberInfoService.queryList(query);
		int total = memberInfoService.queryTotal(query);
		
		return R.page(memberInfoList,total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("member:memberinfo:info")
	public R info(@PathVariable("id") Long id){
		MemberInfoEntity memberInfo = memberInfoService.queryObject(id);
		
		return R.ok().put("memberInfo", memberInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("member:memberinfo:save")
	public R save(@RequestBody MemberInfoEntity memberInfo){
		memberInfoService.save(memberInfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("member:memberinfo:update")
	public R update(@RequestBody MemberInfoEntity memberInfo){
		memberInfoService.update(memberInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("member:memberinfo:delete")
	public R delete(@RequestBody Long[] ids){
		memberInfoService.deleteBatch(ids);
		
		return R.ok();
	}

	/**
	 * 冻结
	 */
	@RequestMapping("/activate")
	public R activate(@RequestBody Long[] ids){
		memberInfoService.activateBatch(ids);
		return R.ok();
	}
	/**
	 * 解冻
	 */
	@RequestMapping("/activateTwo")
	public R activateTwo(@RequestBody Long[] ids){
		memberInfoDao.activateBatchTwo(ids);
		return R.ok();
	}
	/*数量统计*/
	@RequestMapping("/getCountList")
	public R countList(){
		R r = new R();
		String isVip;
		isVip="";
		int countZong=memberInfoDao.zongCount(isVip);
		isVip="1";
		int countVip=memberInfoDao.zongCount(isVip);
		isVip="2";
		int countYou=memberInfoDao.zongCount(isVip);
		r.put("countZong",countZong);//总数
		r.put("countYou",countYou);//游客
		r.put("countVip",countVip);//会员
		return r;
	}

}
