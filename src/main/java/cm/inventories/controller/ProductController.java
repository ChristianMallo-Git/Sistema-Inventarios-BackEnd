package cm.inventories.controller;

import cm.inventories.exception.ResourceNotFoundException;
import cm.inventories.model.Product;
import cm.inventories.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("inventory-app")
@CrossOrigin(value="http://localhost:4200")
public class ProductController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts(){
        List<Product> listProducts = productService.listProduct();
        LOGGER.info("Products obtained: ");
        listProducts.forEach((product)->LOGGER.info(product.toString()));
        return listProducts;
    }

    @PostMapping("/products")
    public Product addProducts(@RequestBody Product product){
        LOGGER.info("Product to add: " + product);
        return productService.saveProduct(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> searchProductById(
            @PathVariable int id){
        Product product=productService.searchProductById(id);
        if(product != null){
            return ResponseEntity.ok(product);
        }else{
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable int id,
            @RequestBody Product productReceived){
        Product product = productService.searchProductById(id);
        if(product==null){
            throw new ResourceNotFoundException("Not id found: " + id);
        }
        product.setDescription(productReceived.getDescription());
        product.setPrice(productReceived.getPrice());
        product.setStock(productReceived.getStock());
        productService.saveProduct(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>>
    deleteProduct(@PathVariable int id){
        Product product = productService.searchProductById(id);
        if(product==null){
            throw new ResourceNotFoundException("Not id found: " + id);
        }
        this.productService.deleteProduct(product.getIdProduct());
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
