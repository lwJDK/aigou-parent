package org.li.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.li.PageList;
import org.li.domain.Product;
import org.li.domain.ProductExt;
import org.li.domain.Specification;
import org.li.domain.ViewProperties;
import org.li.mapper.ProductExtMapper;
import org.li.mapper.ProductMapper;
import org.li.mapper.SpecificationMapper;
import org.li.query.ProductQuery;
import org.li.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author solargen
 * @since 2019-08-04
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductExtMapper productExtMapper;

    @Autowired
    private SpecificationMapper specificationMapper;

    @Override
    public PageList<Product> queryPage(ProductQuery query) {
        IPage<Product> ipage = baseMapper.queryPage(new Page(query.getPageNum(), query.getPageSize()), query);
        return new PageList<>(ipage.getTotal(), ipage.getRecords());
    }

    @Override
    public List<Specification> getViewProperties(Long productId) {
        //先到product_ext中查询
        ProductExt productExt = productExtMapper.selectOne(new QueryWrapper<ProductExt>().eq("productId", productId));
        //如果viewPropeties属性不为null
        if(productExt.getViewProperties()!=null){
            List<Specification> viewPropertiesList = JSON.parseArray(productExt.getViewProperties(), Specification.class);
            return viewPropertiesList;
        }
        //再查询属性表
        Product product = baseMapper.selectById(productId);
        Long productTypeId = product.getProductTypeId();
        List<Specification> specifications = specificationMapper.selectList(new QueryWrapper<Specification>()
                .eq("product_type_id", productTypeId).eq("isSku", 0));
        return specifications;

    }

    /**
     * 修改商品的显示属性
     * @param productId
     * @param viewProperties
     */
    @Override
    public void updateViewProperties(long productId, String viewProperties) {
        productExtMapper.updateViewProperties(productId, viewProperties);
    }

    /**
     * 查询商品的sku属性
     * @param productId
     * @return
     */
    @Override
    public List<Specification> getSkuProperties(Long productId) {
        //先到product_ext中查询
        ProductExt productExt = productExtMapper.selectOne(new QueryWrapper<ProductExt>().eq("productId", productId));
        //如果skuPropeties属性不为null
        if(productExt.getSkuProperties()!=null){
            List<Specification> skuPropertiesList = JSON.parseArray(productExt.getSkuProperties(), Specification.class);
            return skuPropertiesList;
        }
        //再查询属性表
        Product product = baseMapper.selectById(productId);
        Long productTypeId = product.getProductTypeId();
        List<Specification> specifications = specificationMapper.selectList(new QueryWrapper<Specification>()
                .eq("product_type_id", productTypeId).eq("isSku", 1));
        return specifications;
    }


    /**
     * 重写save方法，向product表和productExt表中插入数据
     * @param entity
     * @return
     */
    @Override
    @Transactional //保证在同一个事务里面
    public boolean save(Product entity) {
        //初始化部分字段的值
        entity.setCreateTime(new Date().getTime());
        entity.setState(0);
        baseMapper.insert(entity);

        ProductExt productExt = entity.getProductExt();
        //设置productId
        productExt.setProductId(entity.getId());
        productExtMapper.insert(productExt);

        return true;
    }

}
