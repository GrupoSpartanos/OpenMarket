package co.edu.unicauca.openmarket.access;

import co.edu.unicauca.openmarket.domain.Category;
import co.edu.unicauca.openmarket.domain.Product;
import co.edu.unicauca.openmarket.domain.Location;
import co.edu.unicauca.openmarket.domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Es una implementación que tiene libertad de hacer una implementación del
 * contrato. Lo puede hacer con Sqlite, postgres, mysql, u otra tecnología
 *
 * @author Libardo, Julio
 */
public class ProductRepository implements IProductRepository {

    private Connection conn;

    public ProductRepository() {
        initDatabase();
    }

    private void initDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS products (\n"
                + "    productId integer PRIMARY KEY,\n"
                + "    name text NOT NULL,\n"
                + "    description text NULL,\n"
                + "    price real NULL,\n"
                + "    stock integer NULL,\n"
                + "    categoryId real NULL,\n"
                + "    latitude real NULL,\n"
                + "    longitude real NULL,\n"
                + "    sellerId real NULL,\n"
                + "    suspended integer NULL\n"
                + ");";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean save(Product newProduct) {
        try {
            // Validate product
            if (newProduct == null || newProduct.getName().isBlank()) {
                return false;
            }
            this.connect();

            String sql = "INSERT INTO products (name, description, price, stock, categoryId, latitude, longitude, sellerId, suspended) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newProduct.getName());
            pstmt.setString(2, newProduct.getDescription());
            pstmt.setDouble(3, newProduct.getPrice());
            pstmt.setInt(4, newProduct.getStock());
            pstmt.setLong(5, newProduct.getCategory().getCategoryId());
            pstmt.setDouble(6, newProduct.getLocation().getLatitude());
            pstmt.setDouble(7, newProduct.getLocation().getLongitude());
            pstmt.setLong(8, newProduct.getSeller().getUserId());
            pstmt.setBoolean(9, newProduct.isSuspended());
            pstmt.executeUpdate();
            this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void connect() {
        // SQLite connection string
        //String url = "jdbc:sqlite:./myDatabase.db"; //Para Linux/Mac
        String url = "jdbc:sqlite:myDatabase.db"; //Para Windows
        //String url = "jdbc:sqlite::memory:";

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            this.connect();
            String sql = "SELECT * FROM products";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(rs.getLong("productId"));
                newProduct.setName(rs.getString("name"));
                newProduct.setDescription(rs.getString("description"));
                newProduct.setPrice(rs.getDouble("price"));
                newProduct.setStock(rs.getInt("stock"));

                Category category = new Category();
                category.setCategoryId(rs.getLong("categoryId"));
                newProduct.setCategory(category);

                Location location = new Location(0, 0);
                location.setLatitude(rs.getDouble("latitude"));
                location.setLongitude(rs.getDouble("longitude"));
                newProduct.setLocation(location);

                User seller = new User();
                seller.setUserId(rs.getLong("sellerId"));
                newProduct.setSeller(seller);

                newProduct.setSuspended(rs.getBoolean("suspended"));

                products.add(newProduct);
            }
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public boolean edit(Long id, Product product) {
        try {
            // Validate product
            this.connect();
            if (id <= 0 || product == null) {
                return false;
            }

            String sql = "UPDATE products "
                    + "SET name=?, description=?, price=?, stock=?, categoryId=?, latitude=?, longitude=?, sellerId=?, suspended=? "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStock());
            pstmt.setLong(5, product.getCategory().getCategoryId());
            pstmt.setDouble(6, product.getLocation().getLatitude());
            pstmt.setDouble(7, product.getLocation().getLongitude());
            pstmt.setLong(8, product.getSeller().getUserId());
            pstmt.setBoolean(9, product.isSuspended());
            pstmt.setLong(10, id);
            pstmt.executeUpdate();
            this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Product findById(Long id) {
        try {
            this.connect();
            String sql = "SELECT * FROM products "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Product prod = new Product();
                prod.setProductId(res.getLong("productId"));
                prod.setName(res.getString("name"));
                prod.setDescription(res.getString("description"));
                prod.setPrice(res.getDouble("price"));
                prod.setStock(res.getInt("stock"));

                Category category = new Category();
                category.setCategoryId(res.getLong("categoryId"));
                prod.setCategory(category);

                Location location = new Location(0, 0);
                location.setLatitude(res.getDouble("latitude"));
                location.setLongitude(res.getDouble("longitude"));
                prod.setLocation(location);

                User seller = new User();
                seller.setUserId(res.getLong("sellerId"));
                prod.setSeller(seller);

                prod.setSuspended(res.getBoolean("suspended"));
                this.disconnect();
                return prod;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        try {
            this.connect();
            String sql = "SELECT * FROM products "
                    + "WHERE name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);

            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                Product prod = new Product();
                prod.setProductId(res.getLong("productId"));
                prod.setName(res.getString("name"));
                prod.setDescription(res.getString("description"));
                prod.setPrice(res.getDouble("price"));
                prod.setStock(res.getInt("stock"));

                Category category = new Category();
                category.setCategoryId(res.getLong("categoryId"));
                prod.setCategory(category);

                Location location = new Location(0, 0);
                location.setLatitude(res.getDouble("latitude"));
                location.setLongitude(res.getDouble("longitude"));
                prod.setLocation(location);

                User seller = new User();
                seller.setUserId(res.getLong("sellerId"));
                prod.setSeller(seller);

                prod.setSuspended(res.getBoolean("suspended"));

                products.add(prod);
            }
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public boolean delete(Long id) {
        try {
            //Validate product
            if (id <= 0) {
                return false;
            }
            this.connect();

            String sql = "DELETE FROM products "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}