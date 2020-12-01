package dk.dd.carsearch;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/car")
public class CarSearchController
{
    private CarSearchClient carClient = null;
    public static List<Car> carList = new ArrayList<>();
    private static List<Rating> ratingList = new ArrayList<>();

    public CarSearchController(CarSearchClient carClient)
    {
        this.carClient = carClient;
    }

    @GetMapping("/{name}")
    @ResponseBody
    @CrossOrigin(origins = "*") // allow request from any client
    public List<Rating> getMyRatings(@PathVariable String name){
        carList.addAll(carClient.readCars().getContent());

        for (Car c:carList) {
            for (Rating r: c.getRatings()) {
                if(r.getUser().getName().equalsIgnoreCase(name)){
                    ratingList.add(r);
                }

            }
        }
        return ratingList;
    }

    @GetMapping("/{carBrand}")
    @ResponseBody
    @CrossOrigin(origins = "*") // allow request from any client
    @HystrixCommand(fallbackMethod = "fallback") // in case of failure
    public Car getCar(@PathVariable String carBrand)
    {
        carList.addAll(carClient.readCars().getContent());
        for (Car c:carList) {
            if (c.getBrand().equalsIgnoreCase(carBrand)){
                return c;
            }
        }
        return null;
    }


    @PutMapping("/{name}")
    @ResponseBody
    @CrossOrigin(origins = "*") // allow request from any client
    public Car giveRating(@PathVariable String name, @RequestBody Rating rating){
        carList.addAll(carClient.readCars().getContent());
        for (Car c: carList) {
            if(giveRating(c, name)) {
                c.getRatings().add(rating);
                return c;
            }
        }
        return new Car();
    }
    private boolean giveRating(Car car, String name){
        return car.getBrand().equals(name);
    }
    @GetMapping("/")
    @ResponseBody
    @CrossOrigin(origins = "*") // allow request from any client
    @HystrixCommand(fallbackMethod = "fallback") // in case of failure
    public Collection<Car> getCars(){
       carList.addAll(carClient.readCars().getContent());
       return carList;
    }

    private boolean car(Car car){
        return car.getKm() > 0;
    }

    @PostMapping("/")
    @ResponseBody
    @CrossOrigin(origins = "*") // allow request from any client
    public void createCar(@RequestBody User user){
        carClient.readCars();
    }

    private Collection<Car> fallback()
    {
        return new ArrayList<>();
    }
}