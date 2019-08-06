package org.li.common.controller;

import org.li.AjaxResult;
import org.li.common.domain.ProductDoc;
import org.li.common.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/es")
public class ProductESController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * 保存
     * @param productDoc
     * @return
     */
    @PostMapping("/save")
    public AjaxResult save(@RequestBody ProductDoc productDoc){
        try {
            productRepository.save(productDoc);
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("失败!"+e.getMessage());
        }
    }

    /**
     * 批量保存
     * @param list
     * @return
     */
    @PostMapping("/saveBath")
    public AjaxResult saveBath(@RequestBody List<ProductDoc> list){
        try {
            productRepository.saveAll(list);
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("失败!"+e.getMessage());
        }
    }

    /**
     * 删除
     * @param productId
     * @return
     */
    @PostMapping("/delete")
    public AjaxResult delete(@RequestParam Long productId){
        try {
            productRepository.deleteById(productId);
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("失败!"+e.getMessage());
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/deleteBatch")
    public AjaxResult deleteBatch(@RequestBody List<Long> ids){
        try {
            for (Long id : ids) {
                productRepository.deleteById(id);
            }
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("失败!"+e.getMessage());
        }
    }
}
