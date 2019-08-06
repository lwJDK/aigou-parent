package org.li.common;

import org.li.AjaxResult;
import org.li.common.domain.ProductDoc;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductESFallback implements ProductESClient{
    @Override
    public AjaxResult save(ProductDoc productDoc) {
        return AjaxResult.getAjaxResult().setSuccess(false).setMessage("系统异常!!→_→");
    }

    @Override
    public AjaxResult saveBath(List<ProductDoc> list) {
        return AjaxResult.getAjaxResult().setSuccess(false).setMessage("系统异常!!→_→");
    }

    @Override
    public AjaxResult delete(Long productId) {
        return AjaxResult.getAjaxResult().setSuccess(false).setMessage("系统异常!!→_→");
    }

    @Override
    public AjaxResult deleteBatch(List<Long> ids) {
        return AjaxResult.getAjaxResult().setSuccess(false).setMessage("系统异常!!→_→");
    }
}
