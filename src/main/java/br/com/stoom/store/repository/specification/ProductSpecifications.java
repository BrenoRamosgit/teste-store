package br.com.stoom.store.repository.specification;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.stoom.store.dto.request.ProductFilterRequest;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;

@Component
public class ProductSpecifications {

    public  Specification<Product> withFilters(ProductFilterRequest filter) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (nonNull(filter.getSku())) {
                predicates.add(builder.equal(root.get("sku"), filter.getSku()));
            }

            if (nonNull(filter.getName())) {
                predicates.add(builder.like(root.get("name"), "%" + filter.getName() + "%"));
            }

            if (nonNull(filter.getDescription())) {
                predicates.add(builder.like(root.get("description"), "%" + filter.getDescription() + "%"));
            }

            if (nonNull(filter.getCategoriesIds()) && !filter.getCategoriesIds().isEmpty()) {
                Join<Product, Category> categoryJoin = root.join("categories");
                predicates.add(categoryJoin.get("id").in(filter.getCategoriesIds()));
                
                if (nonNull(filter.getCategoryActive())) {
                    predicates.add(builder.equal(categoryJoin.get("active"), filter.getCategoryActive()));
                }
            }else  if (nonNull(filter.getCategoryActive())) {
            	Join<Product, Category> categoryJoin = root.join("categories");
            	predicates.add(builder.equal(categoryJoin.get("active"), filter.getCategoryActive()));
            }

            if (nonNull(filter.getBrandsIds())) {
                Join<Product, Brand> brandJoin = root.join("brand");
                predicates.add(brandJoin.get("id").in(filter.getBrandsIds()));

                if (nonNull(filter.getBrandActive())) {
                    predicates.add(builder.equal(brandJoin.get("active"), filter.getBrandActive()));
                }
            } else 
                if (nonNull(filter.getBrandActive())) {
                    Join<Product, Brand> brandJoin = root.join("brand");
                    predicates.add(builder.equal(brandJoin.get("active"), filter.getBrandActive()));
                }
            

            if (nonNull(filter.getActive())) {
                predicates.add(builder.equal(root.get("active"), filter.getActive()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
