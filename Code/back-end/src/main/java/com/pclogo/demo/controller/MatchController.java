package com.pclogo.demo.controller;

import com.pclogo.demo.configuration.LogConfig;
import com.pclogo.demo.service.MatchService;
import com.pclogo.demo.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/match")
public class MatchController {
    @Autowired
    MatchService matchService;

    @RequestMapping("/createRoom")
    Integer createRoom(@RequestParam("uid") Integer uid, @RequestParam("isSingle") Integer isSingle)
    {
        LogConfig.LOG.info("createRoom" + uid + "\t" + isSingle);
        return matchService.createRoom(uid, isSingle);
    }

    @RequestMapping("/joinById")
    Boolean joinById(@RequestParam("uid") Integer uid, @RequestParam("rid") Integer rid, @RequestParam("isSingle") Integer isSingle)
    {
        LogConfig.LOG.info("joinById" + uid + "\t" + rid + "\t" + isSingle);
        return matchService.joinById(uid, rid, isSingle);
    }

    @RequestMapping("/joinSrand")
    Integer joinSrand(@RequestParam("uid") Integer uid, @RequestParam("isSingle") Integer isSingle)
    {
        LogConfig.LOG.info("joinSrand" + uid + "\t" + isSingle);
        return matchService.joinSrand(uid, isSingle);
    }

    @RequestMapping("/sendCommand")
    Boolean sendCommand(@RequestParam("uid") Integer uid, @RequestParam("rid") Integer rid, @RequestParam("command") String command)
    {
        LogConfig.LOG.info("sendCommand" + uid + "\t" + rid + "\t" + command);
        return matchService.sendCommand(uid, rid, command);
    }

    @RequestMapping("/getCommand")
    List<String> getCommand(@RequestParam("uid") Integer uid, @RequestParam("rid") Integer rid)
    {
        LogConfig.LOG.info("getCommand" + uid + "\t" + rid);
        return matchService.getCommand(uid, rid);
    }

    @RequestMapping("/getOtherPlayer")
    UserUtil getOtherPlayer(@RequestParam("uid") Integer uid, @RequestParam("rid") Integer rid)
    {
        LogConfig.LOG.info("getOtherPlayer" + uid + "\t" + rid);
        return matchService.getOtherPlayer(uid, rid);
    }

    @RequestMapping("/removeRoom")
    void removeRoom(@RequestParam("uid") Integer uid, @RequestParam("rid") Integer rid)
    {
        LogConfig.LOG.info("removeRoom" + uid + " " + rid);
        matchService.removeRoom(uid, rid);
    }
}
