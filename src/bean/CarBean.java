package bean;

public class CarBean extends BaseIdBean {
    private String name;
    private String color;
    private Double price;
    private String carNumber;
    private Integer userId;
    private boolean isInStore;

    public CarBean() {
    }

    public CarBean(String name, String color, Double price, String carNumber) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.carNumber = carNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isInStore() {
        return isInStore;
    }

    public void setInStore(boolean inStore) {
        isInStore = inStore;
    }
}
