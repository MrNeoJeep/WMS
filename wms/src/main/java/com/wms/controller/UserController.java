package com.wms.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.dto.PasswordForm;
import com.wms.entity.Menu;
import com.wms.entity.User;
import com.wms.service.MenuService;
import com.wms.service.UserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public List<User> list(int i){
        return userService.list();
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no){
        List<User> list = userService.lambdaQuery().eq(User::getNo,no).list();
        Long total = (long) list.size();
        return list.size() > 0 ? Result.suc(list,total) : Result.fail();
    }
    @PostMapping("/checkPasswordByNo")
    public Result checkPasswordByNo(@RequestBody PasswordForm passwordForm){
        List<User> list = userService.lambdaQuery().eq(User::getNo, passwordForm.getNo()).list();
        String pwd = list.get(0).getPassword();
        if(pwd.equals(passwordForm.getOldPassword())){
            return Result.suc();
        }else{
            return Result.fail();
        }
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody User user){
        //MD5加密
        user.setPassword(SecureUtil.md5(user.getPassword()));
        return userService.save(user)?Result.suc():Result.fail();
    }
    @PostMapping("/savePwd")
    public Result savePwd(@RequestBody PasswordForm passwordForm){
        User user = new User();
        user.setId(passwordForm.getId());
        user.setPassword(SecureUtil.md5(passwordForm.getPassword()));
        return userService.updateById(user)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody User user){
//        System.out.println("user = "+user);
        return userService.updateById(user)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return userService.removeById(id)?Result.suc():Result.fail();
    }

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        //MD5加密验证
        List list = userService.lambdaQuery()
                .eq(User::getNo,user.getNo())
                .eq(User::getPassword, SecureUtil.md5(user.getPassword())).list();


        if(list.size()>0){
            User user1 = (User)list.get(0);
            List menuList = menuService.lambdaQuery().like(Menu::getMenuRight,user1.getRoleId()).list();
            HashMap res = new HashMap();
            res.put("user",user1);
            res.put("menu",menuList);
            if(user1.getIsValid().equals("N")){
                return Result.fail("您的账户已被禁用");
            }
            return Result.suc(res);
        }
        return Result.fail("校验失败，用户名或密码错误！");
    }

    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody User user){
        return userService.updateById(user);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }
    //删除
    @GetMapping("/delete")
    public boolean delete(Integer id){
        return userService.removeById(id);
    }
    //查询（模糊、匹配）
    @PostMapping("/listP")
    public Result listP(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(user.getName())){
            lambdaQueryWrapper.like(User::getName,user.getName());
        }

        return Result.suc(userService.list(lambdaQueryWrapper));
    }
    @PostMapping("/listPage")
    public List<User> listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");
        System.out.println("name==="+(String)param.get("name"));


        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getName,name);


        IPage result = userService.page(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());

        return result.getRecords();
    }

    //分页查询
    @PostMapping("/listPageC1")
    public Result listPageC1(@RequestBody QueryPageParam query){

        HashMap param = query.getParam();
        String name = (String)param.get("name");
        String sex = (String)param.get("sex");
        String roleId = (String)param.get("roleId");

        System.out.println("name = "+name);
        System.out.println("sex = "+sex);

        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(User::getName,name);
        }
        if(StringUtils.isNotBlank(sex)){
            lambdaQueryWrapper.eq(User::getSex,sex);
        }
        if(StringUtils.isNotBlank(roleId)){
            lambdaQueryWrapper.eq(User::getRoleId,roleId);
        }

        //查询时不返回密码
        IPage result = userService.pageCC(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());

        return Result.suc(result.getRecords(),result.getTotal());
    }

    @GetMapping("/changeState")
    public Result changeState(@RequestParam String id){
        List<User> list = userService.lambdaQuery().eq(User::getId,id).list();
        if(list.size() > 0){
            User user = list.get(0);
            String isValid = user.getIsValid();
            if(isValid.equals("Y")){
                user.setIsValid("N");
            }else{
                user.setIsValid("Y");
            }
            boolean b = userService.updateById(user);
            if(b){
                return Result.suc();
            }
            else{
                return Result.fail();
            }
        }else{
            return Result.fail();
        }
    }

}
