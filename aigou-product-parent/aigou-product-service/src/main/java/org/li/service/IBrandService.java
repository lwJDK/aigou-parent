package org.li.service;

import org.li.PageList;
import org.li.domain.Brand;
import com.baomidou.mybatisplus.extension.service.IService;
import org.li.query.BrandQuery;

/**
 * <p>
 * 品牌信息 服务类
 * </p>
 *
 * @author solargen
 * @since 2019-07-30
 */
public interface IBrandService extends IService<Brand> {

    /**
     * 分页查询
     * @param query
     * @return
     */
    PageList<Brand> queryPage(BrandQuery query);
}
