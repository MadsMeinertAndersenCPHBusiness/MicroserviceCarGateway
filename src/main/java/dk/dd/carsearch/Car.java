package dk.dd.carsearch;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class Car
{
        //@Id
        //@GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @NonNull private String brand;
        @NonNull private int year;
        @NonNull private long km;
        private List<Rating> ratings;

        public Car() {
                this.ratings = new ArrayList<>();
        }

        public Long getId() {
                return id;
        }

        public String getBrand() {
                return brand;
        }

        public int getYear() {
                return year;
        }

        public long getKm() {
                return km;
        }

        public List<Rating> getRatings() {
                return ratings;
        }
}
