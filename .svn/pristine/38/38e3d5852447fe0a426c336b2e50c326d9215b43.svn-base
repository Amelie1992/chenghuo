package com.wzlue.integral.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.wzlue.common.utils.DateUtils;
import com.wzlue.integral.dao.IntegralCardDao;
import com.wzlue.integral.entity.IntegralCardEntity;
import com.wzlue.sys.dao.SysConfigDao;
import com.wzlue.sys.entity.SysConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.wzlue.integral.dao.IntegralCardBatchDao;
import com.wzlue.integral.entity.IntegralCardBatchEntity;
import com.wzlue.integral.service.IntegralCardBatchService;

import static com.wzlue.common.utils.DateUtils.DATE_TIME_PATTERN;


@Service("integralCardBatchService")
public class IntegralCardBatchServiceImpl implements IntegralCardBatchService {
    @Autowired
    private IntegralCardBatchDao integralCardBatchDao;
    @Autowired
    private IntegralCardDao integralCardDao;
    @Autowired
    private SysConfigDao sysConfigDao;

    @Override
    public IntegralCardBatchEntity queryObject(Long id) {
        return integralCardBatchDao.queryObject(id);
    }

    @Override
    public List<IntegralCardBatchEntity> queryList(Map<String, Object> map) {
        return integralCardBatchDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return integralCardBatchDao.queryTotal(map);
    }

    @Override
    public void save(IntegralCardBatchEntity integralCardBatch) {
        //批次号
        String batchNumber = "PCH" + DateUtils.format(new Date(), "yyyyMMddHHmm") + String.format("%04d", new Random().nextInt(9999));
        integralCardBatch.setBatchNumber(batchNumber);
        integralCardBatchDao.save(integralCardBatch);
        //制卡
        SysConfigEntity coding_rule = sysConfigDao.queryByKey("CODING_RULE");
        String front = null;
        String tail = null;
        int bound = 9999;
        if (null != coding_rule) {
            Map<String, String> map = JSONObject.parseObject(coding_rule.getValue(), new TypeReference<Map<String, String>>() {});
            String codingRule = map.get("codingRule");
            Map<String, String> rule = JSONObject.parseObject(codingRule, new TypeReference<Map<String, String>>() {});
            front = rule.get("front");//前缀
            tail = rule.get("tail");//校验位
        }
        if (null != tail) {
            switch (Integer.valueOf(tail)) {
                case 1:
                    bound = 9;
                    break;
                case 2:
                    bound = 99;
                    break;
                case 3:
                    bound = 999;
                    break;
            }
        }
        for (int i = 1; i <= integralCardBatch.getNumber(); i++) {
            IntegralCardEntity integralCard = new IntegralCardEntity();
            //充值码
            String cardNumber = front + String.format("%05d", i) + new Random().nextInt(bound);
            integralCard.setCardNumber(cardNumber);
            integralCard.setType(1);
            integralCard.setBatchNumber(batchNumber);
            integralCard.setIntegral(integralCardBatch.getIntegral());
            integralCard.setRemarks(integralCardBatch.getRemarks());
            integralCardDao.save(integralCard);
        }
    }

    @Override
    public void update(IntegralCardBatchEntity integralCardBatch) {
        integralCardBatchDao.update(integralCardBatch);
    }

    @Override
    public void delete(Long id) {
        integralCardBatchDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        integralCardBatchDao.deleteBatch(ids);
    }

}
