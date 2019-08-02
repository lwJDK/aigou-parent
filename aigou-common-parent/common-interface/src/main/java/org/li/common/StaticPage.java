package org.li.common;

import org.li.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "COMMON-SERVER",fallback = StaticPageFallback.class)
public interface StaticPage {
    @PostMapping("/genStaticPage")
    public AjaxResult genStaticPage(@RequestBody Object model,
                                    @RequestParam("templatePath") String templatePath,
                                    @RequestParam("targetPath") String targetPath);
    /*@PostMapping("/genStaticPage")
    public AjaxResult genStaticPage(@RequestBody Map<String,Object> map);*/
}
