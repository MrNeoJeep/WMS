package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hiram
 * @since 2023-06-15
 */
public interface UserService extends IService<User> {

    IPage pageCC(Page<User> page, LambdaQueryWrapper<User> lambdaQueryWrapper);

    IPage pageC(Page<User> page);
}
