package org.li.service;

import org.li.domain.ProductType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品目录 服务类
 * </p>
 *
 * @author solargen
 * @since 2019-07-30
 */
public interface IProductTypeService extends IService<ProductType> {

    /**
     * 加载类型数的数据
     * @return
     */
    List<ProductType> loadTypeTree();

    /**
     * 生成静态化的主页面
     */
    void genHomePage();
}
