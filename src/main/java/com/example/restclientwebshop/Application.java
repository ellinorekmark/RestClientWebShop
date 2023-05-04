package com.example.restclientwebshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.restclientwebshop.ShopObjects.*;

import java.util.List;
import java.util.Scanner;

@Configuration
public class Application implements CommandLineRunner {
    @Autowired
    RestClient shop;
    Scanner scanner = new Scanner(System.in);
    boolean running = true;
    @Override
    public void run(String... args) throws Exception {
        while (running){
            showMenu();
        }
    }
    private void showMenu() {

        System.out.println("""
                Shop Admin
                
                -Menu- 
                
                1. View All Products
                2. Products by Category
                
                0. Exit
                
                """);
        int menuChoice = scanner.nextInt();
        scanner.nextLine();
        switch (menuChoice){
            case 0 -> running = false;
            case 1 -> allProducts();
            case 2 -> productsByCat();
            case 3 -> search();
            case 4 -> delete();
        }
    }
    private void allProducts() {
        System.out.println("All Products: \n");
        System.out.println(shop.getAllProducts());
        System.out.println("\nReturn (type anything)");
        scanner.nextLine();
    }
    private void productsByCat() {
        boolean done = false;
        while(!done) {
            System.out.println("Product Categories:");
            Category[] values = Category.values();
            for (int i = 0; i < values.length; i++) {
                Category c = values[i];
                System.out.println(i + 1 + ": " + c.toString());
            }
            int category = scanner.nextInt() - 1;
            if (category <= 0 || category > values.length) {
                System.out.println("Products in Category: " + values[category] + "\n");
                System.out.println(shop.getProductsByCategory(values[category]));
                System.out.println("\nReturn (type anything)");
                scanner.nextLine();
                done = true;
            }
        }
    }
    private void search() {
        System.out.print("Search: ");
        String searchTerm = scanner.nextLine();

        try {
            List<Product> results = shop.searchProduct(searchTerm);
            System.out.println(results);

        } catch(Exception e) {
            System.out.println("No products found.");
        }
        System.out.println("\nReturn (type anything)");
        scanner.nextLine();
    }

    private void delete(){

        System.out.print("Delete Product by ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println(shop.deleteProduct(id));
        System.out.println("\nReturn (type anything)");
        scanner.nextLine();
    }
}
