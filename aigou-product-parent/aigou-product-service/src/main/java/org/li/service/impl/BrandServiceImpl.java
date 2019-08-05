package org.li.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.li.AjaxResult;
import org.li.PageList;
import org.li.common.RedisClient;
import org.li.domain.Brand;
import org.li.domain.Brand;
import org.li.mapper.BrandMapper;
import org.li.query.BrandQuery;
import org.li.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private RedisClient redisClient;

    @Override
    //当前有事务在，则加入到当前事务中，当前没有事务，以非事务方式执行
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public PageList<Brand> queryPage(BrandQuery query) {
        //查询总数
        //查询当前页的数据
        Page<Brand> page = new Page<>(query.getPageNum(),query.getPageSize());
        IPage<Brand> ip = baseMapper.queryPage(page, query);

        System.out.println("total=="+ip.getTotal());
        System.out.println("size=="+ip.getSize());
        System.out.println("records=="+ip.getRecords());

        //封装到PageList返回
        return new PageList<>(ip.getTotal(),ip.getRecords());
    }
    

    /**
     * 加载品牌 类型数的数据
     * @return
     */
    @Override
    public List<Brand> loadTypeTree() {
        //用来装不重复的品牌类型
        List<Brand> listType = new ArrayList<>();

        //从redis获取数据
        AjaxResult result = redisClient.get("brandTypes");
        String str = (String) result.getObj();
        //将字符串转换为json格式
        List<Brand> list = JSON.parseArray(str, Brand.class);
        if(list==null || list.size()<=0){
            //如果没有，则从数据库中查询数据
            list = baseMapper.selectList(null);
            for (Brand brand : list) {
                for (Brand brand1 : listType) {

                }
            }
            //将查询出来的数据存入redis缓存中
            redisClient.set("brandTypes", JSON.toJSONString(list));
        }
        //返回数据
        return list;
    }
}
