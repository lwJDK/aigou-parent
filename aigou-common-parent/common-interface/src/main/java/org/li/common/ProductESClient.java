package org.li.common;


import org.li.AjaxResult;
import org.li.common.domain.ProductDoc;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "COMMON-SERVICE",fallback = ProductESFallback.class)
@RequestMapping("/es")
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
    @GetMapping("/delete")
    public AjaxResult delete(@RequestParam("productId") Long productId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/deleteBatch")
    public AjaxResult deleteBatch(@RequestBody List<Long> ids);


}
