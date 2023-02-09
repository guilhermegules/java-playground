package org.example;

import org.example.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        productStream();
    }

    private static void productStream() {
        List<Product> products = new ArrayList<>();

        products.add(new Product(1,"HP Laptop",25000f));
        products.add(new Product(2,"Dell Laptop",30000f));
        products.add(new Product(3,"Lenevo Laptop",28000f));
        products.add(new Product(4,"Sony Laptop",28000f));
        products.add(new Product(5,"Apple Laptop",90000f));

        List<Float> productPrices = products.stream()
                .filter(product -> product.getPrice() > 30000)
                .map(product -> product.getPrice())
                .collect(Collectors.toList());

        products.stream()
                .filter(p -> p.getPrice() == 30000)
                .forEach(p -> System.out.println(p.getName()));

        float totalPrice = products.stream()
                .map(product -> product.getPrice())
                .reduce(0.0f, (sum, price) -> sum + price);

        System.out.println("Total price: " + totalPrice);

        float totalPrice2 = products.stream()
                .map(product -> product.getPrice())
                .reduce(0.0f, Float::sum);

        System.out.println("Total price 2: " + totalPrice2);

        double totalPrice3 = products.stream()
                .collect(Collectors.summingDouble(product -> product.getPrice()));

        System.out.println("Total price 3: " + totalPrice3);

        Product productA = products.stream()
                .max((p1, p2) -> p1.getPrice() > p2.getPrice() ? 1 : -1)
                .get();

        System.out.println("Product A price: " + productA.getPrice());

        Product productB = products.stream()
                .min((p1, p2) -> p1.getPrice() > p2.getPrice() ? 1 : -1)
                .get();

        System.out.println("Product B: " + productB.getPrice());

        long count = products.stream()
                .filter(product -> product.getPrice() < 30000)
                .count();

        System.out.println("Count: " + count);

        Set<Float> productPriceList = products.stream()
                .filter(product -> product.getPrice() < 30000)
                .map(product -> product.getPrice())
                .collect(Collectors.toSet());

        System.out.println("Set of prices: " + productPriceList);

        Map<Integer, String> productPriceList2 = products.stream().collect(Collectors.toMap(p -> p.getId(), p -> p.getName()));

        System.out.println("Product price list map: " + productPriceList2);

        List<Float> productPriceList3 = products.stream()
                .map(Product::getPrice)
                .collect(Collectors.toList());

        System.out.println("product price list 3: " + productPriceList3);

        System.out.println(productPrices);
    }
}