package org.li.common;


import org.li.AjaxResult;
import org.li.common.domain.ProductDoc;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "COMMON-SERVICE",fallback = ProductESFallback.class)
public interface ProductESClient {

    /**
     * 保存
     * @param productDoc
     * @return
     */
    @PostMapping("/save")
    public AjaxResult save(@RequestBody ProductDoc productDoc);

    /**
     * 批量保存
     * @param list
     * @return
     */
    @PostMapping("/saveBath")
    public AjaxResult saveBath(@RequestBody List<ProductDoc> list);

    /**
     * 删除
     * @param productId
     * @return
     */
    @PostMapping("/delete")
    public AjaxResult delete(@RequestParam Long productId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/deleteBatch")
    public AjaxResult deleteBatch(@RequestBody List<Long> ids);


}
