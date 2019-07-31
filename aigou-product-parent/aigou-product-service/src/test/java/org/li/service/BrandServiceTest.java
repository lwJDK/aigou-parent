package org.li.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.li.PageList;
import org.li.domain.Brand;
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

}