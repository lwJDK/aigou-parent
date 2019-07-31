package org.li.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.li.domain.ProductType;
import org.li.mapper.ProductTypeMapper;
import org.li.service.IProductTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Override
    public List<ProductType> loadTypeTree() {
        //递归方式
        //return recursive(0L);

        //循环方式
        return loop();
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
                parent.getChildren().add(productType);
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
}
