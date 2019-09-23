package com.wzlue.web.controller.sys;

import com.wzlue.common.base.R;
import com.wzlue.sys.entity.SysCityInfoEntity;
import com.wzlue.sys.service.SysCityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 省市区信息表
 * Created by Administrator on 2018/11/26.
 */
@RestController
@RequestMapping("/sys/provinces")
public class SysCityInfoController {
    @Autowired
    private SysCityInfoService sysCityInfoService;

    @RequestMapping("/city/{cityCode}")
    public R list(@PathVariable("cityCode")String subCityCode){
        //查询列表
        List<SysCityInfoEntity> sysCityInfoEntityList=sysCityInfoService.queryByCode(subCityCode);
        return R.ok().put("sysCityInfoEntityList", sysCityInfoEntityList);
    }
}
