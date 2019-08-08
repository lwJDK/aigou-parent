package org.li.common.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.li.common.CommonApplication2000;
import org.li.common.domain.ProductDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApplication2000.class)
public class ESTest {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void test(){
        elasticsearchTemplate.deleteIndex("aigou-parent");
        elasticsearchTemplate.createIndex("aigou-parent");
        elasticsearchTemplate.putMapping(ProductDoc.class);
    }
}
