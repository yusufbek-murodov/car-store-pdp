package db;

import bean.CarBean;
import bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class DB {
    /**
     * The DB class represents a simple in-memory database for managing users and cars.
     * <p>
     * <p>
     * List of registered users
     */
    private static final List<UserBean> USER_LIST = new ArrayList<>();

    /**
     * List of available cars
     */
    private static final List<CarBean> CAR_LIST = new ArrayList<>();

    /**
     * Current user session
     */
    public static UserBean session = null;

    /**
     * Adds a new user to the database.
     *
     * @param bean The UserBean object representing the user to be added.
     * @return The added UserBean object if successfully added, null otherwise.
     */
    public static UserBean addUser(UserBean bean) {
        if (checkUserExistByUserName(bean.getUserName())) {
            return null;
        }
        bean.setId(USER_LIST.size());
        bean.setBalance(10000D);
        USER_LIST.add(bean);
        return bean;
    }

    /**
     * Checks if a user exists in the database based on username.
     *
     * @param userName The username to check for existence.
     * @return true if the user exists, false otherwise.
     */
    private static boolean checkUserExistByUserName(String userName) {
        for (UserBean userBean : USER_LIST) {
            if (userBean.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a user from the database based on username and password.
     *
     * @param bean The UserBean object containing the username and password for authentication.
     * @return The UserBean object if authentication is successful, null otherwise.
     */
    public static UserBean getUser(UserBean bean) {
        if (USER_LIST.isEmpty()) return null;

        for (UserBean userBean : USER_LIST) {
            if (userBean.getUserName().equals(bean.getUserName()) && userBean.getPassword().equals(bean.getPassword())) {
                return userBean;
            }
        }
        return null;
    }

    /**
     * Adds a new car to the database.
     *
     * @param bean The CarBean object representing the car to be added.
     * @return The added CarBean object if successfully added, null otherwise.
     */
    public static CarBean addCar(CarBean bean) {
        if (checkCarExist(bean)) {
            return null;
        }
        bean.setId(CAR_LIST.size());
        bean.setInStore(false);
        CAR_LIST.add(bean);
        return bean;
    }

    /**
     * Checks if a car already exists in the database.
     *
     * @param bean The CarBean object representing the car to check.
     * @return true if the car exists, false otherwise.
     */
    private static boolean checkCarExist(CarBean bean) {
        for (CarBean carBean : CAR_LIST) {
            if (carBean.getName().equals(bean.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a list of cars owned by the current session user.
     *
     * @return List of CarBean objects owned by the current session user, or null if no cars are owned.
     */
    public static List<CarBean> showMyCar() {
        if (CAR_LIST.isEmpty()) return null;

        List<CarBean> myCars = new ArrayList<>();
        for (CarBean carBean : CAR_LIST) {
            if (session.getId().equals(carBean.getUserId())) {
                myCars.add(carBean);
            }
        }
        return myCars;
    }

    /**
     * Allows the current session user to buy a car.
     *
     * @param buyId The ID of the car to buy.
     * @return The bought CarBean object if the purchase is successful, null otherwise.
     */
    public static CarBean buyCar(Integer buyId) {
        for (CarBean carBean : CAR_LIST) {
            if (carBean.getId().equals(buyId) && (carBean.getPrice() <= DB.session.getBalance()) && carBean.getUserId() == null) {
                double newBalance = DB.session.getBalance() - carBean.getPrice();
                DB.session.setBalance(newBalance);
                carBean.setUserId(DB.session.getId());
                carBean.setInStore(false);
                return carBean;
            }
        }
        return null;
    }

    /**
     * Allows the current session user to sell a car.
     *
     * @param sellId The ID of the car to sell.
     * @return The sold CarBean object if the sale is successful, null otherwise.
     */
    public static CarBean sellCar(Integer sellId) {
        for (CarBean carBean : CAR_LIST) {
            if (sellId.equals(carBean.getId()) && carBean.getUserId() != null) {
                double newBalance = DB.session.getBalance() + carBean.getPrice();
                carBean.setUserId(null);
                carBean.setInStore(true);
                DB.session.setBalance(newBalance);
                return carBean;
            }
        }
        return null;
    }

    /**
     * Retrieves a list of available cars (not owned by any user).
     *
     * @return List of available CarBean objects, or null if no cars are available.
     */
    public static List<CarBean> showAllCars() {
        if (CAR_LIST.isEmpty()) {
            return null;
        }

        List<CarBean> availableCars = new ArrayList<>();
        for (CarBean carBean : CAR_LIST) {
            if (carBean.getUserId() == null) {
                carBean.setInStore(true);
                availableCars.add(carBean);
            }
        }
        return availableCars;
    }

}
