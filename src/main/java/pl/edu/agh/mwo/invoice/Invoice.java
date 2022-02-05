package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<>();



    public void addProduct(Product product) {
       // this.products.put(product, 1);
        addProduct(product,1);
    }

    public void addProduct(Product product, Integer quantity) {
        this.products.put(product, quantity);
    }

    public BigDecimal getSubtotal() {

        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : this.products.keySet()) {
            for (int i = 0; i < products.get(product); i++) {
                sum = sum.add(product.getPriceWithTax());
            }
        }
        return sum;
    }

    public BigDecimal getTax() {
        return BigDecimal.ZERO;
    }

    public BigDecimal getTotal() {
        return BigDecimal.ZERO;
    }
}
