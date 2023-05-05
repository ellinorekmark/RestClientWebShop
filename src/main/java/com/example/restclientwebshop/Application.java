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
        while (running) {
            showMenu();
        }
    }

    private void showMenu() {

        System.out.println("""
                Shop Admin
                                
                -Menu- 
                                
                1. View All Products
                2. Products by Category
                3. Search by Product Name
                4. Delete by Product ID
                5. Add Product
                6. Add Inventory
                0. Exit
                                
                """);
        int menuChoice = scanner.nextInt();
        scanner.nextLine();
        switch (menuChoice) {
            case 0 -> running = false;
            case 1 -> allProducts();
            case 2 -> productsByCat();
            case 3 -> search();
            case 4 -> delete();
            case 5 -> addProduct();
            case 6 -> updateInventory();
        }
    }

    private void allProducts() {
        System.out.println("All Products: \n");
        System.out.println(shop.getAllProducts());
        System.out.println("\nReturn (type anything)");
        scanner.nextLine();
    }

    private void productsByCat() {
        Category category = categories();
        System.out.println("Products in Category: " + category + "\n");
        System.out.println(shop.getProductsByCategory(category));
        System.out.println("\nReturn (type anything)");
        scanner.nextLine();


    }

    private Category categories() {
        while (true) {
            System.out.println("Product Categories:");
            Category[] values = Category.values();
            for (int i = 0; i < values.length; i++) {
                Category c = values[i];
                System.out.println(i + 1 + ": " + c.toString());
            }
            int category = scanner.nextInt() - 1;
            scanner.nextLine();
            try{
                Category c = values[category];
                return c;

            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Please pick a valid cateogry.");
            }

        }
    }

    private void search() {
        System.out.print("Search: ");
        String searchTerm = scanner.nextLine();

        try {
            List<Product> results = shop.searchProduct(searchTerm);
            System.out.println(results);

        } catch (Exception e) {
            System.out.println("No products found.");
        }
        System.out.println("\nReturn (type anything)");
        scanner.nextLine();
    }

    private void delete() {

        System.out.print("Delete Product by ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println(shop.deleteProduct(id));
        System.out.println("\nReturn (type anything)");
        scanner.nextLine();
    }

    private void addProduct() {
        Category category = categories();
        System.out.println(category);
        System.out.print("Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        double price;
        while(true) {
            try {
                System.out.print("Price: ");
                price = Double.parseDouble(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Price must be numbers only.");
            }
        }
        String product = shop.newProduct(new Product(name,description,price,0,category));
        System.out.println("Product added: "+ product);

    }

    void updateInventory(){
        System.out.println("Product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Amount to add: ");
        int amount = scanner.nextInt();
        scanner.nextLine();
        try{
            System.out.println(shop.addInventory(id, amount));
        }
        catch(Exception e){
            System.out.println("Unable to find product and update inventory.");
        }
        System.out.println("\nReturn (type anything)");
        scanner.nextLine();

    }
}
