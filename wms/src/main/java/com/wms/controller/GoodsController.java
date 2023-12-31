package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Goods;
import com.wms.entity.Storage;
import com.wms.service.GoodsService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hiram
 * @since 2023-06-15
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    //新增
    @RequiresAuthentication
    @PostMapping("/save")
    public Result save(@RequestBody Goods goods){
        return goodsService.save(goods)?Result.suc():Result.fail();
    }
    //更新
    @RequiresAuthentication
    @PostMapping("/update")
    public Result update(@RequestBody Goods goods){
        return goodsService.updateById(goods)?Result.suc():Result.fail();
    }
    //删除
    @RequiresAuthentication
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return goodsService.removeById(id)?Result.suc():Result.fail();
    }

    @RequiresAuthentication
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");
        String goodsType = (String)param.get("goodsType");
        String storage = (String)param.get("storage");

        Page<Goods> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Goods::getName,name);
        }
        if(StringUtils.isNotBlank(goodsType) && !"null".equals(goodsType)){
            lambdaQueryWrapper.eq(Goods::getGoodsType,goodsType);
        }
        if(StringUtils.isNotBlank(storage) && !"null".equals(storage)){
            lambdaQueryWrapper.eq(Goods::getStorage,storage);
        }

        IPage result = goodsService.pageCC(page,lambdaQueryWrapper);
        return Result.suc(result.getRecords(),result.getTotal());
    }

    @RequiresAuthentication
    @GetMapping("/findByName")
    public Result findByName(@RequestParam String name,@RequestParam int storage){
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Goods::getName,name);
        lambdaQueryWrapper.eq(Goods::getStorage,storage);
        List<Goods> list = goodsService.list(lambdaQueryWrapper);
        Long total = (long)list.size();
        return list.size() > 0 ? Result.suc(list,total):Result.fail();
    }

}
