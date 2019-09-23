package com.wzlue.app.controller.wechat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.wzlue.app.common.config.JwtUtils;
import com.wzlue.common.base.R;
import com.wzlue.member.entity.MemberInfoEntity;
import com.wzlue.member.service.MemberInfoService;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.util.crypt.WxMaCryptUtils;
import me.chanjar.weixin.common.exception.WxErrorException;

@RestController
@RequestMapping("/app/wechat")
public class ApiAuthContoller {
	
	@Autowired
    private WxMaService wxService;
	
	@Autowired
	private MemberInfoService memberInfoService;
	
	@Autowired
    private JwtUtils jwtUtils;
	
	/**
     * 登陆接口
     */
    @GetMapping("login")
    public R login(String code) {
        if (StringUtils.isBlank(code)) {
            return R.error("empty jscode");
        }

        try {
            WxMaJscode2SessionResult session = this.wxService.getUserService().getSessionInfo(code);
            
            //查询用户信息
            MemberInfoEntity memberInfo = memberInfoService.queryByOpenid(session.getOpenid());
            String sessionKey = session.getSessionKey();
            if(memberInfo == null) {
            	return R.error(1, "未注册").put("sessionKey", sessionKey);
            }

            //生成token
            String token = jwtUtils.generateToken(memberInfo.getId());
            //生成token
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("expire", jwtUtils.getExpire());
            map.put("sessionKey", sessionKey);

            return R.ok(map);
        } catch (WxErrorException e) {
            return R.error();
        }
    }
    
    
    /**
     * 用户注册
     */
    @GetMapping("register")
    public R register(String sessionKey, String signature, String rawData, String encryptedData, String iv) {
    	
        // 用户信息校验
        if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return R.error("user check failed");
        }

        // 解密用户信息
        WxMaUserInfo userInfo = this.wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        //注册
        MemberInfoEntity memberInfo = new MemberInfoEntity();
        memberInfo.setAvatarUrl(userInfo.getAvatarUrl());
        memberInfo.setOpenid(userInfo.getOpenId());
        memberInfo.setNickName(userInfo.getNickName());
        memberInfo.setGender(userInfo.getGender());
        memberInfo.setCity(userInfo.getCity());
        memberInfo.setProvince(userInfo.getProvince());
        memberInfo.setCountry(userInfo.getCountry());
        memberInfo.setLanguage(userInfo.getLanguage());
        memberInfo.setUnionid(userInfo.getUnionId());
        memberInfo.setCreateTime(new Date());
        memberInfoService.save(memberInfo);

        return R.ok();
    }
    
    @GetMapping("phone")
    public R phone(String encryptedData, String iv, String sessionKey) {
    	String result = WxMaCryptUtils.decrypt(sessionKey, encryptedData, iv);
    	Gson gson = new Gson();
    	WechatPhone wechatPhone = gson.fromJson(result, WechatPhone.class);
    	return R.ok().put("wechatPhone", wechatPhone);
    }


}
