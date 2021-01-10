package com.pclogo.demo.controller;

import com.pclogo.demo.configuration.LogConfig;
import com.pclogo.demo.service.UserService;
import com.pclogo.demo.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/loginByPhone")
    public UserUtil loginByPhone(@RequestParam("phone") String phone, @RequestParam("password") String password)
    {
        LogConfig.LOG.info("loginByPhone" + phone + " " + password);
        return userService.loginByPhone(phone, password);
    }

    @RequestMapping("/loginByName")
    public UserUtil loginByName(@RequestParam("name") String name, @RequestParam("password") String password)
    {
        LogConfig.LOG.info("loginByName" + name + " " + password);
        return userService.loginByName(name, password);
    }

    @RequestMapping("/register")
    public Integer register(@RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("password") String password)
    {
        LogConfig.LOG.info("register" + name + " " + phone + " " + password);
        return userService.register(name, phone, password);
    }

//    @RequestMapping("/jwttest")
//    public String jwttest()
//    {
//        return "如果你看到这句话，说明你的jwt已经可以用了";
//    }

    @RequestMapping("/searchByPhone")
    public UserUtil searchByPhone(@RequestParam("friendPhone") String friendPhone)
    {
        LogConfig.LOG.info("searchByPhone" + friendPhone);
        return userService.searchByPhone(friendPhone);
    }

    @RequestMapping("/searchByName")
    public List<UserUtil> searchByName(@RequestParam("friendName") String name)
    {
        LogConfig.LOG.info("searchByName" + name);
        return userService.searchByName(name);
    }

    @RequestMapping("/loadAvatar")
    public Boolean loadAvatar(@RequestParam("avatar") String avatar, @RequestParam Integer uid)
    {
        LogConfig.LOG.info("loadAvatar" + avatar + " " + uid);
        return userService.setAvatar(avatar, uid);
    }

    @RequestMapping("/getFriend")
    public List<UserUtil> getFriend(@RequestParam("uid") Integer uid)
    {
        LogConfig.LOG.info("getFriend" + uid);
        return userService.getFriend(uid);
    }

    @RequestMapping("/updateInfo")
    public Boolean updateInfo(@RequestParam("uid") Integer uid, @RequestParam("name") String name, @RequestParam("password") String password)
    {
        LogConfig.LOG.info("updateInfo" + uid + " " + name + " " + password);
        return userService.updateInfo(uid, name, password);
    }

    @RequestMapping("/updateAvatar")
    public Boolean updateAvatar(@RequestParam("uid") Integer uid, @RequestParam("avatar") String avatar)
    {
        LogConfig.LOG.info("updateAvatar" + uid + " " + avatar);
        return userService.updateAvatar(uid, avatar);
    }

    @RequestMapping("/sendInvite")
    public void sendInvite(@RequestParam("uid") Integer uid, @RequestParam("touid") Integer touid)
    {
        LogConfig.LOG.info("sendInvite" + uid + " " + touid);
        userService.sendInvite(uid, touid);
    }

    @RequestMapping("/checkInvite")
    public List<UserUtil> checkInvite(@RequestParam("uid") Integer uid)
    {
        LogConfig.LOG.info("checkInvite" + uid);
        return userService.checkInvite(uid);
    }

    @RequestMapping("/acceptInvite")
    public Boolean acceptInvite(@RequestParam("uid") Integer uid, @RequestParam("auid") Integer auid, @RequestParam("accept") Integer accept)
    {
        LogConfig.LOG.info("acceptInvite" + uid + " " + auid + " " + accept);
        return userService.acceptInvite(uid, auid, accept);
    }
}
