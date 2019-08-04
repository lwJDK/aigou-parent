package org.li.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.li.PageList;
import org.li.domain.Product;
import org.li.domain.ProductExt;
import org.li.mapper.ProductExtMapper;
import org.li.mapper.ProductMapper;
import org.li.query.ProductQuery;
import org.li.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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

    @Override
    public PageList<Product> queryPage(ProductQuery query) {
        IPage<Product> ipage = baseMapper.queryPage(new Page(query.getPageNum(), query.getPageSize()), query);
        return new PageList<>(ipage.getTotal(), ipage.getRecords());
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
