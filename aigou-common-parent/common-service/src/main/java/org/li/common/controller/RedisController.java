package org.li.common.controller;

import org.li.AjaxResult;
import org.li.JedisUtil;
import org.li.common.RedisClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController implements RedisClient {

    /**
     * 存入 缓存数据
     *
     * @return
     */
    @PostMapping("/redis")
    public AjaxResult set(@RequestParam("key")String key, @RequestParam("value")String value) {

        try {
            JedisUtil.INSTANCE.getJedis().set(key, value);
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("保存成功!!");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("保存失败!!");
        }

    }

    /**
     * 取出 缓存数据
     *
     * @return
     */
    @GetMapping("/redis")
    public AjaxResult get(@RequestParam("key")String key) {
        try {
            String value = JedisUtil.INSTANCE.getJedis().get(key);
            return AjaxResult.getAjaxResult().setObj(value).setMessage("取出成功!!");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("系统异常!!具体原因:" + e.getMessage());
        }
    }
}
