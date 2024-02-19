package resource;

import api.ApiResponse;
import bean.CarBean;
import db.DB;

import java.util.List;

/**
 * The CarResource class provides CRUD operations for managing cars.
 */
public class CarResource implements BaseCrudResource<CarBean> {

    /**
     * Adds a new car.
     *
     * @param bean The CarBean object representing the car to be added.
     * @return ApiResponse object with status code 200 and success message if the car is successfully added,
     * otherwise returns ApiResponse object with status code 400 and an error message.
     */
    @Override
    public ApiResponse add(CarBean bean) {
        CarBean car = DB.addCar(bean);

        if (car == null) {
            return new ApiResponse(400, "Error", car);
        } else {
            return new ApiResponse(200, "Success", car);
        }
    }

    /**
     * Retrieves cars owned by the current session user.
     *
     * @return ApiResponse object with status code 200 and list of owned cars if retrieval is successful,
     * otherwise returns ApiResponse object with status code 400 and an error message.
     */
    @Override
    public ApiResponse showMyCar() {
        List<CarBean> cars = DB.showMyCar();

        if (cars == null) {
            return new ApiResponse(400, "Error", cars);
        } else {
            return new ApiResponse(200, "Success", cars);
        }

    }

    /**
     * Allows the current session user to buy a car.
     *
     * @param buyId The ID of the car to buy.
     * @return ApiResponse object with status code 200 and the bought car if the purchase is successful,
     * otherwise returns ApiResponse object with status code 400 and an error message.
     */
    @Override
    public ApiResponse buy(Integer buyId) {
        CarBean car = DB.buyCar(buyId);

        if (car == null) {
            return new ApiResponse(400, "Error", car);
        } else {
            return new ApiResponse(200, "Success", car);
        }
    }

    /**
     * Allows the current session user to sell a car.
     *
     * @param sellId The ID of the car to sell.
     * @return ApiResponse object with status code 200 and the sold car if the sale is successful,
     * otherwise returns ApiResponse object with status code 400 and an error message.
     */
    @Override
    public ApiResponse sell(Integer sellId) {
        CarBean car = DB.sellCar(sellId);
        return null;
    }

    /**
     * Retrieves available cars and returns an ApiResponse object.
     * @return ApiResponse object indicating the result of the operation.
     */
    public static ApiResponse showAvailableCars() {
        List<CarBean> availableCars = DB.showAllCars();

        if (availableCars == null) {
            return new ApiResponse(500, "Internal Server Error", null);
        } else {
            return new ApiResponse(200, "Success", availableCars);
        }
    }

}
