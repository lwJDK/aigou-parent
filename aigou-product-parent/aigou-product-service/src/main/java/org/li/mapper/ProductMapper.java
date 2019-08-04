package org.li.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.li.domain.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.li.query.ProductQuery;

import javax.management.Query;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author solargen
 * @since 2019-08-04
 */
public interface ProductMapper extends BaseMapper<Product> {

    //查询商品类型
    IPage<Product>  queryPage(Page page,@Param("query") ProductQuery query);
}
