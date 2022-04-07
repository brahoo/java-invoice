package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new LinkedHashMap<>();
    private static int nextNumber = 0;
    private final int number = ++nextNumber;

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        Integer totalQuantity = products.getOrDefault(product, 0) + quantity;
        products.put(product, totalQuantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(number + "\n");
        for (Product tempProduct : products.keySet()) {
            stringBuilder.append(
                    tempProduct.getName()
                            + " ilość: " + products.get(tempProduct)
                            + " cena: " + tempProduct.getPriceWithTax().multiply(new BigDecimal(products.get(tempProduct)))
                            + "\n"
            );
        }
        stringBuilder.append("Liczba pozycji: " + products.size() +"\n");
        return stringBuilder.toString();
    }
}
