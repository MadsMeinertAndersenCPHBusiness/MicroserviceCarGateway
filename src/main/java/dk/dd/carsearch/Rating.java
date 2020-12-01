package dk.dd.carsearch;

public class Rating {
    private User user;
    private double rating;
    private String carBrand;

    public Rating(User user, double rating, String carBrand) {
        this.user = user;
        this.rating = rating;
        this.carBrand = carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public User getUser() {
        return user;
    }

    public double getRating() {
        return rating;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
