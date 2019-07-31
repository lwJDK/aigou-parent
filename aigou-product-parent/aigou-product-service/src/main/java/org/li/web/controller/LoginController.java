package org.li.web.controller;

import org.li.AjaxResult;
import org.li.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    //@RequestMapping(value = "/login",method = RequestMethod.POST)
    @PostMapping("/login")
    public AjaxResult login(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        //登录成功
        if("admin".equals(username) && "admin".equals(password)){
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("登录成功!").setErrorCode(200).setObj(user);
        }
        //登录失败
        return AjaxResult.getAjaxResult().setSuccess(false).setMessage("登录失败!错误原因:用户名或密码错误!!").setErrorCode(400);
    }

}
