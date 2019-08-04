package org.li.web.controller;

import org.li.AjaxResult;
import org.li.LetterUtil;
import org.li.PageList;
import org.li.domain.Brand;
import org.li.domain.ProductType;
import org.li.query.BrandQuery;
import org.li.service.IBrandService;
import org.li.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    /**
     * 品牌
     */
    @Autowired
    public IBrandService brandService;

    /**
     * 商品类型
     */
    @Autowired
    public IProductTypeService productTypeService;

    /**
    * 保存和修改公用的
    * @param brand  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Brand brand){
        try {
            if(brand.getId()!=null){
                brandService.updateById(brand);
            }else{
                brand.setCreateTime(new Date().getTime());
                //首字母
                String firstLetter = LetterUtil.getFirstLetter(brand.getName());
                brand.setFirstLetter(firstLetter.toUpperCase());
                brandService.save(brand);
            }
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            brandService.removeById(id);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.getAjaxResult().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Brand get(@RequestParam(value="id",required=true) Long id)
    {
        return brandService.getById(id);
    }

    /**
     * 加载类型数的数据
     * @return
     */
    @RequestMapping(value = "/brandTypeList",method = RequestMethod.GET)
    public List<Brand> list(){
        return brandService.loadTypeTree();
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Brand> listType(){

        return brandService.list(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Brand> json(@RequestBody BrandQuery query)
    {
        return brandService.queryPage(query);
    }
}
