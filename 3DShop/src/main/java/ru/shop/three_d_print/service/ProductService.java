package ru.shop.three_d_print.service;

import org.springframework.stereotype.Service;
import ru.shop.three_d_print.enums.ProductCategory;
import ru.shop.three_d_print.entities.Product;
import ru.shop.three_d_print.repository.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService
{
    @PersistenceContext
    private EntityManager em;
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) { this.productRepository = productRepository; }

    public Product findById(Long id)
    {
        var optionalProduct = productRepository.findById(id);
        var product = optionalProduct.orElseThrow(NullPointerException::new);
        product.loadImageLinks();

        return product;
    }

    public List<Product> findAllWithCategory(ProductCategory category)
    {
        String SQL = "SELECT * FROM t_product WHERE category = ?";
        Query query = em.createNativeQuery(SQL, Product.class);
        query.setParameter(1, ProductCategory.getDatabaseId(category));

        @SuppressWarnings("unchecked")
        List<Product> products = query.getResultList();

        return products;
    }
}
