package org.li.web.controller;

import org.li.service.ISpecificationService;
import org.li.domain.Specification;
import org.li.query.SpecificationQuery;
import org.li.AjaxResult;
import org.li.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specification")
public class SpecificationController {
    @Autowired
    public ISpecificationService specificationService;

    /**
     * 保存和修改公用的
     * @param specification 传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody Specification specification) {
        try {
            if (specification.getId() != null){
                    specificationService.updateById(specification);
            }else{
                    specificationService.save(specification);
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
                specificationService.removeById(id);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setMessage("删除对象失败！" + e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Specification get(@RequestParam(value = "id", required = true) Long id) {
        return specificationService.getById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Specification> list() {

        return specificationService.list(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public PageList<Specification> json(@RequestBody SpecificationQuery query) {
        IPage<Specification> page = specificationService.page(new Page<Specification>(query.getPageNum(), query.getPageSize()));
        return new PageList<>(page.getTotal(), page.getRecords());
    }
}
