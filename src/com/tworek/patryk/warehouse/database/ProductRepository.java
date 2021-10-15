package com.tworek.patryk.warehouse.database;

import com.tworek.patryk.warehouse.model.Cloth;
import com.tworek.patryk.warehouse.model.Jewellery;
import com.tworek.patryk.warehouse.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    List<Product> products = new ArrayList<>();
    private static final ProductRepository productRepository = new ProductRepository();

    private ProductRepository() {
        this.products.add(new Cloth("Sukienka", 40, "Zielony", 10, "Medium"));
        this.products.add(new Cloth("Spódnica", 38, "Czerwony", 5, "Medium"));
        this.products.add(new Jewellery("Pierścień", 18, "Złoty", 2, "Pierścień"));
        this.products.add(new Jewellery("Naszyjnik", 20, "Srebrny", 3, "Naszyjnik"));
    }
    public static ProductRepository getInstance() {
        return productRepository;
    }
    public List<Product> getAllProducts() {
        return this.products;
    }

    public boolean deliverProduct(String productName, int pieces) {
        for (Product currentProduct : this.products) {
            if (currentProduct.getName().equals(productName) && currentProduct.getPieces() >= pieces) {
                    currentProduct.setPieces(currentProduct.getPieces() - pieces);
                    return true;
            }
        }
        return false;
    }
    public Product findProduct(String productName) {
        for (Product currentProduct : this.products) {
            if (currentProduct.getName().equals(productName)) {
                return currentProduct;
            }
        }
        return null;
    }
    public void addProductToDataBase(Product product) {
        this.products.add(product);
    }
}
