package org.li.common;

import org.li.AjaxResult;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StaticPageFallback implements StaticPage {
    @Override
    public AjaxResult genStaticPage(Object model, String templatePath, String targetPath) {
        return AjaxResult.getAjaxResult().setSuccess(false).setMessage("系统异常!!→_→");
    }


    /*@Override
    public AjaxResult genStaticPage(Map<String, Object> map) {
        return AjaxResult.getAjaxResult().setSuccess(false).setMessage("系统异常!!→_→");
    }*/
}
