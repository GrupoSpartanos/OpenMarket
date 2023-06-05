
package com.ragjc.software.openmarketclient.access;


import com.ragjc.software.openmarketcommons.domain.Product;
import java.util.List;

/**
 *
 * @author Libardo, Julio
 */
public interface IProductRepository {
    boolean save(Product newProduct);
    
    boolean edit(Long id, Product product);
    
    boolean delete(Long id);
    
    boolean buy(Long id);
    
    List<Product> findByName(String name);
    List<Product> findByDescription(String description);

    Product findById(Long id);
    
    List<Product> findAll();

}
