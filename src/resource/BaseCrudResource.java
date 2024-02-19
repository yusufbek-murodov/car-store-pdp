package resource;

import api.ApiResponse;
import bean.BaseIdBean;

/**
 * The BaseCrudResource interface provides CRUD (Create, Read, Update, Delete) operations for resources.
 *
 * @param <T> The type of bean that extends BaseIdBean.
 */
public interface BaseCrudResource<T extends BaseIdBean> {

    /**
     * Adds a new resource.
     *
     * @param bean The object representing the resource to be added.
     * @return ApiResponse object indicating the result of the operation.
     */
    ApiResponse add(T bean);

    /**
     * Retrieves resources.
     *
     * @return ApiResponse object indicating the result of the operation.
     */
    ApiResponse showMyCar();

    /**
     * Allows the action of buying a resource.
     *
     * @param buyId The ID of the resource to buy.
     * @return ApiResponse object indicating the result of the operation.
     */
    ApiResponse buy(Integer buyId);

    /**
     * Allows the action of selling a resource.
     *
     * @param sellId The ID of the resource to sell.
     * @return ApiResponse object indicating the result of the operation.
     */
    ApiResponse sell(Integer sellId);
}
