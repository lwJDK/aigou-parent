package org.li.common;

import org.li.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "COMMON-SERVER",fallback = RedisClientFallback.class)
public interface RedisClient {
    /**
     * 存入 缓存数据
     * @return
     */
    @PostMapping("/redis")
    public AjaxResult set(@RequestParam("key")String key, @RequestParam("value")String value);

    /**
     * 取出 缓存数据
     * @return
     */
    @GetMapping("/redis")
    public AjaxResult get(@RequestParam("key")String key);
}
