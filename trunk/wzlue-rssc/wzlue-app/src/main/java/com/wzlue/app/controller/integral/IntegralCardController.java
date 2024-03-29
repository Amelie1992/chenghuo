
package com.wzlue.app.controller.integral;

import com.wzlue.app.common.annotation.Login;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;
import com.wzlue.integral.entity.IntegralCardEntity;
import com.wzlue.integral.service.IntegralCardService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import  com.wzlue.web.controller.sys.AbstractController;

import java.util.List;
import java.util.Map;


/**
 * 积分充值卡
 *
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-03 17:43:00
 */
@RestController
@RequestMapping("/app/integralcard")
public class IntegralCardController {
    @Autowired
    private IntegralCardService integralCardService;


    /**
     * 积分充值
     */
    @Login
    @PostMapping("/recharge")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cardNumber", dataType = "string", value = "充值码", paramType = "query")
    })
    public R recharge(@RequestParam Map<String, Object> params,@RequestAttribute("userId") Long userId) {
        String cardNumber = (String) params.get("cardNumber");
        if (null == cardNumber) {
            return R.error(1,"请先填写充值码");
        }
        if (null == userId) {
            return R.error(1,"请先登录");
        }
        IntegralCardEntity integralCard = integralCardService.queryByCardNumber(cardNumber);
        if (null == integralCard) {
            return R.error(1, "充值码无效");
        } else if (null != integralCard.getActivationState() && integralCard.getActivationState() == 2) {
            return R.error(1, "充值码尚未激活");
        } else if (null != integralCard.getWriteOffStatus() && integralCard.getWriteOffStatus() == 1) {
            return R.error(1, "充值码已被核销");
        } else {
            integralCardService.recharge(userId, integralCard);
            return R.ok();
        }
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<IntegralCardEntity> integralCardList = integralCardService.queryList(query);
        int total = integralCardService.queryTotal(query);

        return R.page(integralCardList, total);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("integral:integralcard:info")
    public R info(@PathVariable("id") Long id) {
        IntegralCardEntity integralCard = integralCardService.queryObject(id);

        return R.ok().put("integralCard", integralCard);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("integral:integralcard:save")
    public R save(@RequestBody IntegralCardEntity integralCard) {
        integralCardService.save(integralCard);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("integral:integralcard:update")
    public R update(@RequestBody IntegralCardEntity integralCard) {
        integralCardService.update(integralCard);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("integral:integralcard:delete")
    public R delete(@RequestBody Long[] ids) {
        integralCardService.deleteBatch(ids);

        return R.ok();
    }


    /**
     * 激活
     */
    @RequestMapping("/activate")
    @RequiresPermissions("integral:integralcard:update")
    public R activate(@RequestBody Long[] ids) {
        integralCardService.activateBatch(ids);

        return R.ok();
    }

}
