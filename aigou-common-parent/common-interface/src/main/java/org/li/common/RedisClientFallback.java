package org.li.common;

import org.li.AjaxResult;
import org.springframework.stereotype.Component;

@Component
public class RedisClientFallback implements RedisClient{

    //当启动熔断器或降级的时候，自动实现该方法
    @Override
    public AjaxResult set(String key, String value) {
        return AjaxResult.getAjaxResult().setSuccess(false).setMessage("系统异常!!→_→");
    }

    @Override
    public AjaxResult get(String key) {
        return AjaxResult.getAjaxResult().setSuccess(false).setMessage("系统异常!!→_→");
    }
}
