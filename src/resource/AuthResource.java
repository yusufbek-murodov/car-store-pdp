package resource;

import api.ApiResponse;
import bean.UserBean;
import db.DB;

/**
 * The AuthResource class provides methods for user authentication.
 */
public class AuthResource {

    /**
     * Registers a new user.
     *
     * @param userBean The UserBean object representing the user to be registered.
     * @return ApiResponse object indicating the result of the registration process.
     */
    public static ApiResponse register(UserBean userBean) {
        UserBean bean = DB.addUser(userBean);

        if (bean == null) {
            return new ApiResponse(400, "Error", userBean);
        } else {
            return new ApiResponse(200, "Success", userBean);
        }
    }

    /**
     * Logs in a user.
     *
     * @param userBean The UserBean object representing the user trying to log in.
     * @return ApiResponse object indicating the result of the login process.
     */
    public static ApiResponse login(UserBean userBean) {
        UserBean bean = DB.getUser(userBean);

        if (bean == null) {
            return new ApiResponse(400, "Error", userBean);
        } else {
            return new ApiResponse(200, "Success", userBean);
        }
    }
}
