package org.li.common.controller;

import com.alibaba.fastjson.JSON;
import org.li.AjaxResult;
import org.li.VelocityUtils;
import org.li.common.StaticPage;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *  页面静态化
 */
@RestController
public class StaticPageController {

    @PostMapping("/genStaticPage")
    public AjaxResult genStaticPage(@RequestBody Object model,
                                    @RequestParam("templatePath") String templatePath,
                                    @RequestParam("targetPath") String targetPath){


   /* @PostMapping("/genStaticPage")
    public AjaxResult genStaticPage(@RequestBody Map<String,Object> map){*/

       /* Object model = map.get("model");
        String s = JSON.toJSONString(model);
        System.out.println(s);
        String templatePath = (String) map.get("templatePath");
        String targetPath = (String) map.get("targetPath");*/
        String s = JSON.toJSONString(model);
        System.out.println(s);
        System.out.println("==============================================================");
        try {
            VelocityUtils.staticByTemplate(model, templatePath, targetPath);

            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("生成静态化页面成功!!");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("失败!!原因:"+e.getMessage());
        }

    }
}
