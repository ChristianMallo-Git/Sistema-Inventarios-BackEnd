package cm.inventories.service;

import cm.inventories.model.Product;

import java.util.List;

public interface IProductService {

    public List<Product> listProduct();

    public Product searchProductById (Integer idProduct);

    public Product saveProduct(Product product);

    public void deleteProduct(Integer idProduct);




}
