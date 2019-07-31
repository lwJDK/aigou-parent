package org.li.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.li.PageList;
import org.li.domain.Brand;
import org.li.mapper.BrandMapper;
import org.li.query.BrandQuery;
import org.li.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 品牌信息 服务实现类
 * </p>
 *
 * @author solargen
 * @since 2019-07-30
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Override
    //当前有事务在，则加入到当前事务中，当前没有事务，以非事务方式执行
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public PageList<Brand> queryPage(BrandQuery query) {
        System.out.println(query.getPageNum());
        System.out.println(query.getPageSize());
        //查询总数
        //查询当前页的数据
        Page<Brand> page = new Page<>(query.getPageNum(),query.getPageSize());
        System.out.println(page);
        System.out.println(query);
        IPage<Brand> ip = baseMapper.queryPage(page, query);



        //封装到PageList返回
        return new PageList<>(ip.getTotal(),ip.getRecords());
    }
}
