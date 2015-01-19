package tiger.direct;

/**
 *
 * Author: Max Farfaras
 * Date: 20 January 2015
 * Description: Checkout including payment info, shipping info, shipping cost, and receipt
 */

import static tiger.direct.TigerDirect.cart;
import java.io.*;
import java.lang.Double;
import java.util.Scanner;
import static tiger.direct.Employees.numItems;
import static tiger.direct.TigerDirect.items;
import static tiger.direct.TigerDirect.numItemsInCart;

public class Checkout {
    
    static Scanner user = new Scanner(System.in);
    
    public static String company(){
        
        String card;
        int cardChoice;
        String cardChoiceString;
        
        System.out.println("1. American Express\n2. VISA\n3. Mastercard\n");
        
        System.out.print("Please enter the number corresponding to the card you have: ");
        
        cardChoice = Methods.checkNumber(1, 3);
        
        while (cardChoice == -1){
            System.out.print("That was an invalid answer. Please enter another number: ");
            cardChoice = Methods.checkNumber(1, 3);
        }
        
        cardChoiceString = "" + cardChoice;
        
        if (cardChoiceString.equals("1")){
            card = "American Express";
        }
        
        else if (cardChoiceString.equals("2")){
            card = "VISA";
        }
        
        else{
            card = "Mastercard";
        }
        
        return(card);
    }
    
    public static String name(){
        
        
        String name;
        
        System.out.print("Please enter the name of the card holder: ");
        
        name = user.nextLine();
        
        return(name);
    }
    
    public static String number(){
        
        String num;
        System.out.print("Please enter your card number with no spaces: ");
        
        while (!(user.hasNextBigInteger())){
            user.nextLine();
            System.out.print("Sorry, that was an invalid card number. Please try again: ");
        }
        
        num = user.next();
        user.nextLine();
        
        return(num);
    }
    
    public static int expiryMonth(){
        
        int month;
        System.out.println("Now to enter the expiry date for your card.");
        System.out.print("Please enter the month by the number (ex. June = 6): ");
        
        month = Methods.checkNumber(1,12);
        
        while (month == -1){
            System.out.println("That is an invalid month.");
            System.out.print("Please try again: ");
            month = Methods.checkNumber(1,12);
        }
        
        return(month);
    }
    
        public static int expiryYear(){
        
        int year;
        
        System.out.print("Please enter the last two numbers of the year (ex. 2021 = 21): ");
        
        year = Methods.checkNumber(0, 99);
        
        while (year == -1){
            System.out.println("That is an invalid year.");
            System.out.print("Please try again: ");
            year = Methods.checkNumber(0, 99);
        }
        
        
        return(year);
    }
    
    public static int code(){
        
        int num;
        
        System.out.print("Please enter the security code on your card: ");
        
        num = Methods.checkNumber(100,999);
        
        while(num == -1){
            System.out.println("That is an invalid security code. It has to be three numbers.");
            System.out.print("Please try again: ");
            num = Methods.checkNumber(100, 999);
        }
                
        return(num);
    }
    
    public static void editPayment(String type, String name, String number, int month, int year, int code){
        
        int editChoice = 6;
        
        while (!(editChoice == 0)){

            System.out.println("Is there anything you would like to edit?");

            System.out.println("1. Card type: " + type);
            System.out.println("2. Cardholder: " + name);
            System.out.println("3. Card number: " + number);
            System.out.println("4. Card expiry date: " + month + "/" + year);
            System.out.println("5. Security code: " + code);
            System.out.println("0. Continue");

            System.out.println("If yes, enter the number beside the information you\nwould like to edit, or 0 to continue: ");

            editChoice = Methods.checkNumber(0, 5);

            while(editChoice == -1){
                System.out.println("That was an invalid entry. Please try again: ");
                editChoice = Methods.checkNumber(0, 5);
            }

            switch(editChoice){
                case 1:
                    type = company();
                    break;
                case 2:
                    name = name();
                    break;
                case 3:
                    number = number();
                    break;
                case 4:
                    month = expiryMonth();
                    year = expiryYear();
                    break;
                case 5:
                    code = code();
                    break;
                default:
                    break;
            }
            
        }
    }
    
    public static String shippingStreet(){
        
        String street;
        
        System.out.println("Now for the shipping address");
        
        System.out.print("Please enter the name of your street: ");
        
        street = user.nextLine();
        
        return(street);
    }
    
    public static String shippingCity(){
        
        String city;
        
        System.out.print("Please enter the name of your city: ");
        
        city = user.nextLine();
        
        return(city);
    }
    
    public static String shippingProvince(){
        
        int choice;
        String province = "";
        
        System.out.println("1. British Columbia\n2. Alberta\n3. Saskatchewan\n4. Manitoba\n5. Ontario\n6. Quebec\n7. New Brunswick\n8. Nova Scotia\n9. Prince Edward Island\n10. Newfoundland and Labrodor\n11. Yukon\n12. North-West Territories\n13. Nunavut");
        
        System.out.println("Please enter the number corresponding to the province in which you live:");
        
        choice = Methods.checkNumber(1, 13);

            while(choice == -1 || choice > 13 || choice < 1){
                System.out.println("That was an invalid entry. Please try again: ");
                choice = user.nextInt();
            }

            switch(choice){
                case 1:
                    province = "British Columbia";
                    break;
                case 2:
                    province = "Alberta";
                    break;
                case 3:
                    province = "Saskatchewan";
                    break;
                case 4:
                    province = "Manitoba";
                    break;
                case 5:
                    province = "Ontario";
                    break;
                case 6:
                    province = "Quebec";
                    break;
                case 7:    
                    province = "New Brunswick";
                    break;
                case 8:
                    province = "Nova Scotia";
                    break;
                case 9:
                    province = "Prince Edward Island";
                    break;
                case 10:
                    province = "Newfoundland and Labrador";
                    break;
                case 11:
                    province = "Yukon";
                    break;
                case 12:
                    province = "North-West Territories";
                case 13:
                    province = "Nunavut";
                    break;

            }
        
        return(province);
    }
    
    public static String shippingPostal(){
        
        String code = "";
        
        System.out.print("Please enter your postal code: ");
        code = user.nextLine();
        
        return(code);
    }
    
    public static void editShipping(String street, String city, String province, String postalCode, String cardType, String cardHolder, String cardNumber, int cardExpiryMonth, int cardExpiryYear, int cardSecurityCode) throws IOException{
        
        int editChoice = 5;
        
        while (!(editChoice == 0)){

            System.out.println("Is there anything you would like to edit?");

            System.out.println("1. Street: " + street);
            System.out.println("2. City: " + city);
            System.out.println("3. Province: " + province);
            System.out.println("4. Postal Code: " + postalCode);
            System.out.println("0. Continue");

            System.out.println("If yes, enter the number beside the information you\n would like to edit, or 0 to continue: ");

            editChoice = Methods.checkNumber(0, 4);

            while(editChoice == -1){
                System.out.println("That was an invalid entry. Please try again: ");
                editChoice = Methods.checkNumber(0, 5);
            }

            switch(editChoice){
                case 1:
                    street = shippingStreet();
                    break;
                case 2:
                    city = shippingCity();
                    break;
                case 3:
                    province = shippingProvince();
                    break;
                case 4:
                    postalCode = shippingPostal();
                    break;
                default:
                    break;
            }
            
        }
        
        shippingCost(street, city, province, postalCode, cardType, cardHolder, cardNumber, cardExpiryMonth, cardExpiryYear, cardSecurityCode);
        
    }
    
    public static void shippingCost(String street, String city, String province, String postalCode, String cardType, String cardHolder, String cardNumber, int cardExpiryMonth, int cardExpiryYear, int cardSecurityCode) throws IOException{
        
        double shippingPrice = 0.0;
        double tax = 0.0;
        double subtotalPrice = 0.0;
        double totalPrice = 0.0;
        
        for (int i = 0; i < numItemsInCart; i++){
            subtotalPrice+= cart[i].dollarPrice;
            subtotalPrice+= Double.valueOf("0." + cart[i].centPrice);
        }
        
        if (province.equals("British Columbia")){
            shippingPrice = 19.99;
        }
        
        else if (province.equals("Alberta")){
            shippingPrice = 17.99;
        }
        
        else if (province.equals("Saskatchewan")){
            shippingPrice = 16.49;
        }
        
        else if (province.equals("Manitoba")){
            shippingPrice = 14.99;
        }
        
        else if (province.equals("Ontario")){
            shippingPrice = 4.99;
        }
        
        else if (province.equals("Quebec")){
            shippingPrice = 9.99;
        }
        
        else if (province.equals("New Brunswick")){
            shippingPrice = 12.95;
        }
        
        else if (province.equals("Nova Scotia")){
            shippingPrice = 13.99;
        }
        
        else if (province.equals("Prince Edward Island")){
            shippingPrice = 14.99;
        }
        
        else if (province.equals("Newfoundland")){
            shippingPrice = 16.49;
        }
        
        else if (province.equals("Yukon")){
            shippingPrice = 21.99;
        }
        
        else if (province.equals("North-West Territories")){
            shippingPrice = 23.99;
        }
        
        else{
            shippingPrice = 24.99;
        }
        
        tax = subtotalPrice * 0.13;
        
        totalPrice = subtotalPrice + shippingPrice + tax;
        
        invoice(totalPrice, subtotalPrice, shippingPrice, tax, numItems, street, city, province, postalCode, cardType, cardHolder, cardNumber, cardExpiryMonth, cardExpiryYear, cardSecurityCode);
    }
    
    public static void invoice(double total, double subtotal, double shipping, double tax, int numItems, String street, String city, String province, String postalCode, String cardType, String cardHolder, String cardNumber, int cardExpiryMonth, int cardExpiryYear, int cardSecurityCode) throws IOException{
        
        File receipt = new File("receipt.txt");
        PrintWriter order = new PrintWriter(receipt);
        Scanner fileReader = new Scanner(receipt);
        int receiptChoice = 0;
        
        System.out.println("Thank you for shopping with us at Tiger Direct!\nWe hope you had a pleasant visit with us!");
        
        
        for (int i = 0; i < numItems; i++){
            order.println("Item: " + cart[i].name + "\tID: " + cart[i].ID + "\tPrice " + cart[i].dollarPrice + "." + cart[i].centPrice + "\tQuantity: " + cart[i].quantity);
            System.out.println("Item: " + cart[i].name + "\tID: " + cart[i].ID + "\tPrice " + cart[i].dollarPrice + "." + cart[i].centPrice + "\tQuantity: " + cart[i].quantity);
        }
        
        order.println("Subtotal: " + subtotal + "\nShipping: " + shipping + "\nTax: " + tax + "\nTotal: " + total);
        System.out.println("Subtotal: " + subtotal + "\nShipping: " + shipping + "\nTax: " + tax + "\nTotal: " + total);
        order.println("---------------------------------------------------------");
        System.out.println("---------------------------------------------------------");
        order.println("Payment information:" + "\nCard company: " + cardType + "\nCard holder: " + cardHolder + "\nCard number: " + cardNumber + "\nCard expiry: " + cardExpiryMonth + "/" + cardExpiryYear + "\nCard security code: " + "***");
        System.out.println("Payment information:" + "\nCard company: " + cardType + "\nCard holder: " + cardHolder + "\nCard number: " + cardNumber + "\nCard expiry: " + cardExpiryMonth + "/" + cardExpiryYear + "\nCard security code: " + "***");
        order.println("---------------------------------------------------------");
        System.out.println("---------------------------------------------------------");
        order.println("Shipping information:" + "\n" + street + "\n" + city + ", " + province + "\n" + postalCode);
        System.out.println("Shipping information:" + "\n" + street + "\n" + city + ", " + province + "\n" + postalCode);
        order.println("\nThank you for shopping at Tiger Direct!\nHave a great day!");
        System.out.println("\nThank you for shopping at Tiger Direct!\nHave a great day!");
        
        
        
        /*
        System.out.print("Do you want to print the receipt (1) or email you the receipt (2): ");
        
        receiptChoice = Methods.checkNumber(1, 2);
        
        while(receiptChoice == -1){
                System.out.println("That was an invalid entry. Please try again: ");
                receiptChoice = Methods.checkNumber(1, 2);
            }
        
        if (receiptChoice == 1){
            while(fileReader.hasNext()){
                System.out.println(fileReader.nextLine());
            }
        }
        
        else{
            //email
        }
        
        System.out.println("At Tiger Direct, we strive for excelence in our services, products, and overall user experience.");
        System.out.println("If you have a chance, please complete our survey for a chance to win a $1,000 gift card!");
        //survey();
        */
    }

    public static void removeStock() throws FileNotFoundException{
        //Variable declaration
        boolean itemInCart;
        int x;
        
        ReadFiles.readItems();
        PrintWriter file = new PrintWriter(new File("items"));
        file.println(numItems + ";;");
        for(int i = 0; i < numItems; i++){
            itemInCart = false;
            for(x = 0; x < numItemsInCart; x++){
                if(cart[x].ID == items[i].ID){
                    itemInCart = true;
                }
            }
            if(itemInCart){
                file.println(items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + (items[i].stock - cart[x].quantity) + ";;");
            } else {
                file.println(items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + items[i].stock + ";;");
            }
        }
        file.close();
    }
    
    public static void checkout(CartRecord[] cart) throws IOException{
        
        // Variables
        String cardType;
        String cardHolder;
        String cardNumber;
        int cardExpiryMonth;
        int cardExpiryYear;
        int cardSecurityCode;
        String street;
        String city;
        String province;
        String postalCode;
        
        System.out.println("Welcome to the TigerDirect Checkout!");
        
        System.out.println("\nFirst we need your payment method and details.");
        
        cardType = company();
        
        cardHolder = name();
        
        cardNumber = number();
        
        cardExpiryMonth = expiryMonth();
        
        cardExpiryYear = expiryYear();
        
        cardSecurityCode = code();
        
        editPayment(cardType, cardHolder, cardNumber, cardExpiryMonth, cardExpiryYear, cardSecurityCode);
        
        street = shippingStreet();
        
        city = shippingCity();
        
        province = shippingProvince();
        
        postalCode = shippingPostal();
        
        editShipping(street, city, province, postalCode, cardType, cardHolder, cardNumber, cardExpiryMonth, cardExpiryYear, cardSecurityCode);
        
        removeStock();
    }
}