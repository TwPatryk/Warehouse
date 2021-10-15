package com.tworek.patryk.warehouse.gui;

import com.tworek.patryk.warehouse.database.ProductRepository;
import com.tworek.patryk.warehouse.model.Cloth;
import com.tworek.patryk.warehouse.model.Jewellery;
import com.tworek.patryk.warehouse.model.Product;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GUI {

    private static Scanner scanner = new Scanner(System.in);

    public static void showMainMenu() {
        System.out.println("Welcome to the Warehouse, select: ");
        System.out.println("1. Add product");
        System.out.println("2. Deliver product");
        System.out.println("3. Show all products");
        System.out.println("4. Exit");

        switch (scanner.nextInt()) {
            case 1:
                addProduct();
                showMainMenu();
                break;
            case 2:
                deliverProduct();
                showMainMenu();
                break;
            case 3:
                showAllProducts();
                showMainMenu();
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println("Incorrect selection, try again");
                showMainMenu();
                break;


        }
    }
    private static void showAllProducts() {
        List<Product> products = ProductRepository.getInstance().getAllProducts();
        for (Product currentProduct : products) {
            System.out.println(currentProduct);
        }

    }
    private static void deliverProduct() {
        System.out.print("Enter product: ");
        String productToDeliver = scanner.next();
        System.out.print("Enter pieces to deliver: ");
        int piecesToDeliver = Integer.parseInt(scanner.next());
        boolean success = ProductRepository.getInstance().deliverProduct(productToDeliver, piecesToDeliver);
        if (success) {
            System.out.println("Product successfully delivered!");
        } else {
            System.out.println("Product not delivered, try again");
        }
    }
    private static void addProduct() {
        System.out.print("Enter product's name: ");
        String productName = scanner.next();
        Product productFromDataBase = ProductRepository.getInstance().findProduct(productName);
        if (productFromDataBase !=null) {
            try {
                System.out.print("Enter pieces to add: ");
                int productPieces = Integer.parseInt(scanner.next());
                productFromDataBase.setPieces(productFromDataBase.getPieces() + productPieces);
                System.out.println("Product successfully added!");
            } catch (NumberFormatException e) {
                System.out.println("Incorrect data, try againt!");
                addProduct();
            }

        } else {
            addNewProduct(productName);
        }

    }
    private static void addNewProduct(String productName) {
        System.out.println("Enter category of the product: ");
        System.out.println("1. Cloth");
        System.out.println("2. Jewellery");
        String choose = scanner.next();
        DataWrapper dataWrapper;
        switch(choose) {
            case "1" :
                dataWrapper = readCommonData();
                System.out.println("Enter length: ");
                String length = scanner.next();
                Cloth cloth = new Cloth(productName, dataWrapper.size,dataWrapper.color,dataWrapper.pieces, length);
                ProductRepository.getInstance().addProductToDataBase(cloth);
                System.out.println("Product added successfully!");
                break;
            case "2":
                dataWrapper = readCommonData();
                System.out.println("Enter type: ");
                String type = scanner.next();
                Jewellery jewellery = new Jewellery(productName, dataWrapper.size, dataWrapper.color, dataWrapper.pieces, type);
                ProductRepository.getInstance().addProductToDataBase(jewellery);
                break;
            default:
                System.out.println("Incorrect selection, try again");
                addNewProduct(productName);
                break;
        }
    }
    private static DataWrapper readCommonData() {
        try {
            System.out.println("Enter size: ");
            int size = scanner.nextInt();
            System.out.println("Enter color: ");
            String color = scanner.next();
            System.out.println("Enter pieces: ");
            int pieces = scanner.nextInt();

            return new DataWrapper(size, color, pieces);
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("Incorrect data!");
            return readCommonData();
        }
    }
    static class DataWrapper {
        int size;
        String color;
        int pieces;

        DataWrapper(int size, String color, int pieces) {
           this.size = size;
           this.color = color;
           this.pieces = pieces;
        }
    }
}
