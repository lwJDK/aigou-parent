package org.li.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.li.AjaxResult;
import org.li.JedisUtil;
import org.li.common.RedisClient;
import org.li.common.StaticPage;
import org.li.domain.ProductType;
import org.li.mapper.ProductTypeMapper;
import org.li.service.IProductTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author solargen
 * @since 2019-07-30
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {

    //由于依赖的openfeign，会创建接口的动态代理对象，交给spring管理
    @Autowired
    private RedisClient redisClient;

    @Autowired
    private StaticPage staticPage;

    /**
     * 生成主静态化页面
     *
     * 1.先根据product.type.vm生成product.type.vm.html
     *
     * 2.再根据home.vm生成主页面
     */
    @Override
    public void genHomePage() {

       /* 用map的做法

        Map<String,Object> map = new HashMap<>();

        String templatePath = "E:\\workpaces\\IDEAworkpaces\\aigou-parent\\aigou-product-parent\\aigou-product-service\\src\\main\\resources\\template\\product.type.vm";
        String targetPath = "E:\\workpaces\\IDEAworkpaces\\aigou-parent\\aigou-product-parent\\aigou-product-service\\src\\main\\resources\\template\\product.type.vm.html";
        //model 就是List 存放所有的商品类型
        List<ProductType> productTypes = loadTypeTree();
        map.put("model",productTypes);
        map.put("templatePath",templatePath);
        map.put("targetPath",targetPath);
        staticPage.genStaticPage(map);
        System.out.println("=========================");
        productTypes.forEach(e -> System.out.println(e));

        //第二步 ： 生成home.html
        map = new HashMap<>();
        templatePath = "E:\\workpaces\\IDEAworkpaces\\aigou-parent\\aigou-product-parent\\aigou-product-service\\src\\main\\resources\\template\\home.vm";
        targetPath = "E:\\workpaces\\IDEAworkpaces\\aigou-web-parent\\aigou-web-home\\home.html";
        //model 中要有一个数据是staticRoot
        Map<String,String> model = new HashMap<>();
        model.put("staticRoot","E:\\workpaces\\IDEAworkpaces\\aigou-parent\\aigou-product-parent\\aigou-product-service\\src\\main\\resources\\");
        map.put("model",model);
        map.put("templatePath",templatePath);
        map.put("targetPath",targetPath);

        staticPage.genStaticPage(map);*/






//        ====================================================


        /**
         * 用参数的做法
         */
        //第一步生成product.type.vm.html
        List<ProductType> productTypes = loadTypeTree();

        String templatePath = "E:\\workpaces\\IDEAworkpaces\\aigou-parent\\aigou-product-parent\\aigou-product-service\\src\\main\\resources\\template\\product.type.vm";
        String targetPath = "E:\\workpaces\\IDEAworkpaces\\aigou-parent\\aigou-product-parent\\aigou-product-service\\src\\main\\resources\\template\\product.type.vm.html";
        staticPage.genStaticPage(productTypes, templatePath, targetPath);
        System.out.println("=========================");
        productTypes.forEach(e -> System.out.println(e));

        //第二步生成前端主页面home.html
        //model中要有一个数据是staticRoot
        HashMap<String,String> map = new HashMap<>();
        map.put("staticRoot", "E:\\workpaces\\IDEAworkpaces\\aigou-parent\\aigou-product-parent\\aigou-product-service\\src\\main\\resources\\");
        templatePath = "E:\\workpaces\\IDEAworkpaces\\aigou-parent\\aigou-product-parent\\aigou-product-service\\src\\main\\resources\\template\\home.vm";
        targetPath = "E:\\workpaces\\IDEAworkpaces\\aigou-web-parent\\aigou-web-home\\home.html";
        staticPage.genStaticPage(map, templatePath, targetPath);

        /*//调用公共的页面静态化接口
        //数据
        Object model = loadTypeTree();
        //模板路径
        String templatePath = "E:\\workpaces\\IDEAworkpaces\\aigou-parent\\aigou-product-parent\\aigou-product-service\\src\\main\\resources\\template\\home.vm";
        //生成目标路径----前端项目服务目录下
        String targetPath = "E:\\workpaces\\IDEAworkpaces\\aigou-web-parent\\aigou-web-home\\home.html";
        staticPage.genStaticPage(model, templatePath, targetPath);*/
    }


    @Override
    public List<ProductType> loadTypeTree() {
        //从redis获取数据
        AjaxResult result = redisClient.get("productTypes");
        String str = (String) result.getObj();
        //将字符串转换为json格式
        List<ProductType> list = JSON.parseArray(str, ProductType.class);
        if(list==null || list.size()<=0){
            //如果没有，则从数据库中查询数据
            list = loop();
            //将查询出来的数据存入redis缓存中
            redisClient.set("productTypes", JSON.toJSONString(list));
        }
        //返回数据
        return list;


        //递归方式
        //return recursive(0L);

        //循环方式
        //return loop();
    }

    /**
     * 循环方式
     * @return
     */
    private List<ProductType> loop() {
        List<ProductType> productTypes = baseMapper.selectList(null);
        //定义一个list存放结果
        List<ProductType> list = new ArrayList<>();

        //定义一个map, 存放所有的productType, key是id, value是类型对象
        Map<Long, ProductType> map = new HashMap<>();
        for (ProductType pt : productTypes) {
            map.put(pt.getId(), pt);
        }
        //循环
        for (ProductType productType : productTypes) {
            //一级类型
            if(productType.getPid() == 0){
                list.add(productType);
            }else{
                ProductType parent = map.get(productType.getPid());
                List<ProductType> children = parent.getChildren();
                if (children == null){
                    children = new ArrayList<>();
                }
                children.add(productType);
                parent.setChildren(children);
            }
        }
        return list;
    }


    /**
     * 递归方式实现加载类型树
     */
    /*private List<ProductType> recursive(Long id) {
        //查询所有的一级菜单
        List<ProductType> parents = baseMapper.selectList(new QueryWrapper<ProductType>().eq("pid", id));
        for (ProductType parent : parents) {
            //取到所有的子类型
            List<ProductType> children = recursive(parent.getId());
            if(!children.isEmpty()){
                parent.setChildren(children);
            }
        }
        return parents;
    }*/


    /**
     * 同步缓存
     * @param entity
     * @return
     */
    @Override
    public boolean updateById(ProductType entity) {
        return super.updateById(entity);
    }
}
