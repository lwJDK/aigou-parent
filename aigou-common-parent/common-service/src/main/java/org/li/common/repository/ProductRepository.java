package org.li.common.repository;

import org.li.common.domain.ProductDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface ProductRepository extends ElasticsearchRepository<ProductDoc,Long> {
}
