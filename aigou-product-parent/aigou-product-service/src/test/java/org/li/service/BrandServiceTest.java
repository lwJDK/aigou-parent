package org.li.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.li.PageList;
import org.li.domain.Brand;
import org.li.mapper.BrandMapper;
import org.li.query.BrandQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrandServiceTest {
    @Autowired
    private IBrandService brandService;

    @Autowired
    private BrandMapper baseMapper;

    @Test
    public void test(){
        List<Brand> list = brandService.list();
        list.forEach(e -> System.out.println(e));
    }

    @Test
    public void test2(){
        BrandQuery query = new BrandQuery();
        PageList<Brand> pageList = brandService.queryPage(query);
        System.out.println("total="+pageList.getTotal());
        pageList.getRows().forEach(e -> System.out.println(e));
    }

    /**
     * 查询total
     */
    @Test
    public void test3(){
        BrandQuery query = new BrandQuery();
        query.setPageNum(1);
        query.setPageSize(10);
        IPage<Brand> ip = baseMapper.queryPage(new Page(query.getPageNum(), query.getPageSize()), query);
        System.out.println("page=="+ip.getPages());
        System.out.println("total=="+ip.getTotal());
    }
}