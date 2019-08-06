package org.li.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.li.PageList;
import org.li.domain.Product;
import org.li.domain.Specification;
import org.li.domain.ViewProperties;
import org.li.query.ProductQuery;

import java.util.List;
import java.util.Map;

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

    List<Specification> getViewProperties(Long productId);

    void updateViewProperties(long productId, String viewProperties);

    List<Specification> getSkuProperties(Long productId);

    void updateSkuProperties(long productId, List<Map<String, String>> skus, List<Map<String, String>> skuProperties);
}
