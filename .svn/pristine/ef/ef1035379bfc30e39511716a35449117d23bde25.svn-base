package com.wzlue.app.controller.goods;

import com.wzlue.app.common.annotation.Login;
import com.wzlue.app.common.config.JwtUtils;
import com.wzlue.common.base.Query;
import com.wzlue.common.base.R;
import com.wzlue.goods.dao.GoodsDao;
import com.wzlue.goods.dao.GoodsSpecDao;
import com.wzlue.goods.dao.SpecDao;
import com.wzlue.goods.entity.GoodsEntity;
import com.wzlue.goods.entity.GoodsSpecEntity;
import com.wzlue.goods.entity.SpecEntity;
import com.wzlue.goods.service.GoodsCollectionService;
import com.wzlue.goods.service.GoodsFootprintService;
import com.wzlue.goods.service.GoodsService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


/**
 * 商品
 *
 * @author wzlue
 */
@RestController
@RequestMapping("/app/goods")
public class ApiGoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsFootprintService goodsFootprintService;

    @Autowired
    private GoodsCollectionService goodsCollectionService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private GoodsSpecDao goodsSpecDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private SpecDao specDao;

    /**
     * 商品列表
     *
     * @return
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        if (params.get("tags") != null) {
            params.put("tags", params.get("tags").toString().split(","));
        }
        if (params.get("categoryId") != null) {
            if (params.get("categoryId").equals("0")) {
                params.put("categoryId", "");

            }
        }
        params.put("status", 1);
        Query query = new Query(params);
        List<GoodsEntity> goodsList = goodsService.queryList(query);


        return R.ok().put("goodsList", goodsList);
    }

    /**
     * 商品详情
     *
     * @return
     */
    @RequestMapping("/detail")
    public R detail(Long id, String token) {
        GoodsEntity goods = goodsService.queryObject(id);
        if (!StringUtils.isEmpty(token)) {
            Claims claims = jwtUtils.getClaimByToken(token);
            String userId = claims.getSubject();

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("goodsId", id);
            map.put("memberId", userId);

            int total = goodsCollectionService.queryTotal(map);
            if (total > 0) {
                goods.setCollection(true);
            }
        }

        return R.ok().put("goods", goods);
    }

    //根据类别id查询产品
    @RequestMapping("/selectGoods")
    public R selectGoods(@RequestParam Map<String, Object> params) {
        params.put("status", 1);
        Query query = new Query(params);
        List<GoodsEntity> goodsList = goodsService.queryList(query);
        return R.ok().put("goodsList", goodsList);
    }

    @Login
    @RequestMapping("/footprint")
    public R footprint(Long id, @RequestAttribute("userId") Long userId) {
        goodsFootprintService.footprint(id, userId);
        return R.ok();
    }

    @Login
    @RequestMapping("/collection")
    public R collection(Long id, @RequestAttribute("userId") Long userId) {
        goodsCollectionService.collection(id, userId);
        return R.ok();
    }

    @RequestMapping("/isCollection")
    public R isCollection(Long id, @RequestAttribute("userId") Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("memberId", userId);
        map.put("goodsId", id);

        int total = goodsCollectionService.queryTotal(map);
        if (total > 0) {
            return R.ok().put("isCollection", true);
        }
        return R.ok().put("isCollection", false);
    }

    /**
     * 获取规格的信息
     *
     * @return
     */
    @RequestMapping("/getSpecs")
    public R getSpecs(Long id) {
        List<Map> list = new ArrayList<Map>();
        List<Map> list2 = new ArrayList<Map>();
        Map<String, List<Map>> stringListHashMap = new LinkedHashMap<String, List<Map>>();
        SpecEntity specEntity = new SpecEntity();
        SpecEntity specEntity2 = new SpecEntity();
        //根据产品获取规格
        List<GoodsSpecEntity> goodsSpecEntities = goodsSpecDao.getSpecs(id);
        if (goodsSpecEntities != null) {
            if (goodsSpecEntities.get(0).getSpecId() != null) {
                SpecEntity spec = specDao.queryObject(goodsSpecEntities.get(0).getSpecId());
                specEntity = specDao.queryObject(spec.getParentId());//规格1总名称
            }else{
                specEntity.setSpecName("无");
            }
            if (goodsSpecEntities.get(0).getSpecIdTwo() != null) {
                SpecEntity spec2 = specDao.queryObject(goodsSpecEntities.get(0).getSpecIdTwo());
                specEntity2 = specDao.queryObject(spec2.getParentId());//规格2总名称
            }else{
                specEntity2.setSpecName("无");
            }
            for (int i = 0; i < goodsSpecEntities.size(); i++) {
                Map<String, Object> gui = new TreeMap<String, Object>();
                Map<String, Object> gui2 = new TreeMap<String, Object>();

                if(goodsSpecEntities.get(i).getSpecId()!=null){
                    gui.put("id", goodsSpecEntities.get(i).getSpecId().toString());
                }else{
                    gui.put("id", "0");
                }
                if(goodsSpecEntities.get(i).getSpecIdTwo()!=null){
                    gui2.put("id", goodsSpecEntities.get(i).getSpecIdTwo().toString());
                }else{
                    gui2.put("id", "0");
                }
                if(goodsSpecEntities.get(i).getSpecName()!=null){
                    gui.put("name", goodsSpecEntities.get(i).getSpecName());
                }else{
                    gui.put("name", "无");
                }
                if(goodsSpecEntities.get(i).getSpecNameTwo()!=null){
                    gui2.put("name", goodsSpecEntities.get(i).getSpecNameTwo());
                }else{
                    gui2.put("name", "无");
                }

                list.add(gui);
                list2.add(gui2);
            }
        }



        stringListHashMap.put(specEntity.getSpecName(), list);
        stringListHashMap.put(specEntity2.getSpecName(), list2);
        return R.ok().put("goodsSpecEntities", goodsSpecEntities).put("stringListHashMap", stringListHashMap);
    }

    @RequestMapping("/getSpecPrice")
    public R getSpecPrice(Long goodsId, String specId, String specIdTwo) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("goodsId",goodsId);
        map.put("specId",specId);
        map.put("specIdTwo",specIdTwo);
        GoodsSpecEntity goodsSpecEntity = goodsSpecDao.querySpecPrice(map);
        return R.ok().put("goodsSpecEntity", goodsSpecEntity);
    }

    @RequestMapping("/getId")
    public R getList(String ids) {
        List<GoodsSpecEntity> goodsList = goodsSpecDao.goodSpecId(ids);
        List<Object> listid = new ArrayList<>();
        for (int i = 0; i < goodsList.size(); i++) {
            listid.add(goodsList.get(i).getId());
        }

        return R.ok().put("goodsList", listid);
    }

    /*商品中已上架的是充值卡商品*/
    @RequestMapping("/getCardGood")
    public R getCardGood() {
        List<GoodsEntity> goodsEntityList = goodsDao.cardGood();
        Long id = goodsEntityList.get(0).getId();
        return R.ok().put("cardGood", id);
    }

}
