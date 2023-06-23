package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.entity.GoodsType;
import com.wms.entity.Storage;
import com.wms.mapper.GoodsMapper;
import com.wms.mapper.GoodsTypeMapper;
import com.wms.service.GoodsTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hiram
 * @since 2023-06-15
 */
@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType> implements GoodsTypeService {

    @Resource
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public IPage pageCC(IPage<GoodsType> page, Wrapper wrapper) {
        return goodsTypeMapper.pageCC(page,wrapper);
    }

}
