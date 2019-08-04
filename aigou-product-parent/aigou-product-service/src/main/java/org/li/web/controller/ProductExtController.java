package org.li.web.controller;

import org.li.service.IProductExtService;
import org.li.domain.ProductExt;
import org.li.query.ProductExtQuery;
import org.li.AjaxResult;
import org.li.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productExt")
public class ProductExtController {
    @Autowired
    public IProductExtService productExtService;

    /**
     * 保存和修改公用的
     * @param productExt 传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody ProductExt productExt) {
        try {
            if (productExt.getId() != null){
                    productExtService.updateById(productExt);
            }else{
                    productExtService.save(productExt);
            }
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setMessage("保存对象失败！" + e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id) {
        try {
                productExtService.removeById(id);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setMessage("删除对象失败！" + e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProductExt get(@RequestParam(value = "id", required = true) Long id) {
        return productExtService.getById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ProductExt> list() {

        return productExtService.list(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public PageList<ProductExt> json(@RequestBody ProductExtQuery query) {
        IPage<ProductExt> page = productExtService.page(new Page<ProductExt>(query.getPageNum(), query.getPageSize()));
        return new PageList<>(page.getTotal(), page.getRecords());
    }
}
