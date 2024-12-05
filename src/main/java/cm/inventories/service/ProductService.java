package cm.inventories.service;

import cm.inventories.model.Product;
import cm.inventories.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public List<Product> listProduct() {
        return iProductRepository.findAll();
    }

    @Override
    public Product searchProductById(Integer idProduct) {
        Product product = iProductRepository.findById(idProduct).orElse(null);
        return product;
    }

    @Override
    public Product saveProduct(Product product) {
        return iProductRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer idProduct) {
        iProductRepository.deleteById(idProduct);
    }
}
