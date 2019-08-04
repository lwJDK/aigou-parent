package org.li.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.li.PageList;
import org.li.domain.Product;
import org.li.query.ProductQuery;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author solargen
 * @since 2019-08-04
 */
public interface IProductService extends IService<Product> {

    PageList<Product> queryPage(ProductQuery query);

}
