import api.ApiResponse;
import bean.CarBean;
import bean.UserBean;
import db.DB;
import resource.AuthResource;
import resource.CarResource;

import java.util.List;
import java.util.Scanner;

public class Car_Store_App {
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);
    static AuthResource authResource = new AuthResource();
    static CarResource carResource = new CarResource();

    public static void main(String[] args) {
        showMenu();
    }

    private static void showMenu() {
        System.out.println("0. \uD83D\uDEAA Exit");
        if (DB.session == null) {
            System.out.println("1. \uD83D\uDC64 Register");
            System.out.println("2. \uD83D\uDD12 Login");
        } else {
            System.out.println("3. \uD83D\uDE97 Add car");
            System.out.println("4. \uD83D\uDD27 Show my cars");
            System.out.println("5. \uD83D\uDCB0 Buy");
            System.out.println("6. \uD83D\uDCC4 Sell");
            System.out.println("7. \uD83D\uDD0D Logout");
        }
        System.out.print("Enter option: ");
        int option = scannerInt.nextInt();
        scannerInt = new Scanner(System.in);


        switch (option) {
            case 0 -> {
                return;
            }
            case 1 -> register();
            case 2 -> login();
            case 3 -> add();
            case 4 -> showMyCar();
            case 5 -> buy();
            case 6 -> sell();
            case 7 -> DB.session = null;
        }
        System.out.println("\n");
        showMenu();
    }

    private static void add() {
        System.out.print("Enter name of car: ");
        String name = scannerStr.next();
        System.out.print("Enter color: ");
        String color = scannerStr.next();
        System.out.print("Enter price: ");
        double price = scannerInt.nextDouble();
        System.out.print("Enter car number: ");
        String carNumber = scannerInt.next();
        ApiResponse response = carResource.add(new CarBean(name, color, price, carNumber));
        System.out.println(response.getMessage());

        if (response.getCode().equals(200)) {
            CarBean car = (CarBean) response.getData();
            car.setUserId(DB.session.getId());
        }
    }

    private static void showMyCar() {
        ApiResponse response = carResource.showMyCar();
        List<CarBean> myCars = (List<CarBean>) response.getData();
        System.out.println(response.getMessage());

        if (myCars.size() <= 0) {
            System.out.println("There is no any car in your garage 😫");
            return;
        }
        System.out.println("🚗************ My Garage ************🚗");
        for (CarBean myCar : myCars) {
            System.out.println("🚘 Id: " + myCar.getId() +
                    " | Name: " + myCar.getName() +
                    " | Color: " + myCar.getColor() +
                    " | 💲 Price: $" + myCar.getPrice() +
                    " | 👤 User Id: " + myCar.getUserId() +
                    " | 🏢 In Store: " + (myCar.isInStore() ? "Yes" : "No"));
        }
        System.out.println("🏎***********************************🏎");

    }

    private static void buy() {
        showAllCars();
        System.out.print("Enter the id you want to buy: ");
        int buyId = scannerInt.nextInt();
        ApiResponse response = carResource.buy(buyId);
        System.out.println(response.getMessage());
        System.out.printf("Your balance: %s", DB.session.getBalance());
    }

    private static void sell() {
        showMyCar();
        System.out.print("Enter the id you want to sell: ");
        int sellId = scannerInt.nextInt();
        ApiResponse response = carResource.sell(sellId);
        System.out.println(response.getMessage());
        System.out.printf("Your balance: %s", DB.session.getBalance());
    }

    private static void showAllCars() {
        ApiResponse response = CarResource.showAvailableCars();

        List<CarBean> availableCars = (List<CarBean>) response.getData();
        System.out.println(response.getMessage());


        if (availableCars.size() <= 0) {
            System.out.println("There is no any cars 😫");
            return;
        }

        System.out.println("🚗************ Car List ************🚗");
        for (CarBean myCar : availableCars) {
            System.out.println("🚘 Id: " + myCar.getId() +
                    " | Name: " + myCar.getName() +
                    " | Color: " + myCar.getColor() +
                    " | 💲 Price: $" + myCar.getPrice() +
                    " | 👤 User Id: " + myCar.getUserId() +
                    " | 🏢 In Store: " + (myCar.isInStore() ? "Yes" : "No"));
        }
        System.out.println("🏎***********************************🏎");

    }

    private static void register() {
        System.out.print("Enter user name: ");
        String userName = scannerStr.next();
        System.out.print("Enter password: ");
        String password = scannerStr.next();

        ApiResponse response = authResource.register(new UserBean(userName, password));
        System.out.println(response.getMessage());

        if (response.getCode().equals(200)) {
            DB.session = (UserBean) response.getData();
        }
    }

    private static void login() {
        System.out.print("Enter user name: ");
        String userName = scannerStr.next();
        System.out.print("Enter password: ");
        String password = scannerStr.next();

        ApiResponse response = authResource.login(new UserBean(userName, password));
        System.out.println(response.getMessage());

        if (response.getCode().equals(200)) {
            UserBean user = (UserBean) response.getData();
            DB.session = user;
        }
    }
}
