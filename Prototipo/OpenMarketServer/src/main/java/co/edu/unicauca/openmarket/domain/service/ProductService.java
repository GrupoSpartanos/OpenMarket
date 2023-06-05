package co.edu.unicauca.openmarket.domain.service;


import co.edu.unicauca.openmarket.access.IProductRepository;
import co.edu.unicauca.openmarket.domain.Product;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Libardo, Julio
 */
public class ProductService{

    // Ahora hay una dependencia de una abstracción, no es algo concreto,
    // no sabe cómo está implementado.
    private IProductRepository repository;

    /**
     * Inyección de dependencias en el constructor. Ya no conviene que el mismo
     * servicio cree un repositorio concreto
     *
     * @param repository una clase hija de IProductRepository
     */
    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }


    public synchronized boolean saveProduct(Product newProduct) {
        
        //Validate product
        if (newProduct.getName().isBlank() ) {
            return false;
        }
        boolean respuesta = repository.save(newProduct);
        return respuesta ;
        

    }

    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        products = repository.findAll();;

        return products;
    }
    
    public Product findProductById(Long id){
        return repository.findById(id);
    }
    
    public synchronized boolean deleteProduct(Long id){
        boolean result;
        result = repository.delete(id);
        //this.notificar();
        return result;
    }
    
    public synchronized boolean buyProduct(Long id){
        boolean result;
        result = repository.buy(id);
        //this.notificar();
        return result;
    }

    public synchronized boolean editProduct(Long productId, Product prod) {
        
        //Validate product
        if (prod == null || prod.getName().isBlank() ) {
            return false;
        }
        return repository.edit(productId, prod);
    }

    public List<Product> findProductByName(String name) {
        List<Product> products = new ArrayList<>();
        products = repository.findByName(name);;

        return products;
    }

    public List<Product> findProductByDescription(String description) {
        List<Product> products = new ArrayList<>();
        products = repository.findByDescription(description);;

        return products;
    }

}
