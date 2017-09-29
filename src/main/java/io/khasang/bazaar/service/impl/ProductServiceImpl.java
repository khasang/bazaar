package io.khasang.bazaar.service.impl;

import io.khasang.bazaar.dao.ProductDao;
import io.khasang.bazaar.entity.Product;
import io.khasang.bazaar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product getById(Long id) {
        return productDao.getById(id);
    }

    @Override
    public Product add(Product product) {
        return productDao.add(product);
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public List<Product> getList() {
        return productDao.getList();
    }

    @Override
    public List<Product> getByParam(String name) {
        return productDao.getProductsByName(name);
    }

    @Override
    public Product deleteById(Long id) {
        Product product = productDao.getById(id);
        return productDao.delete(product);
    }
}
