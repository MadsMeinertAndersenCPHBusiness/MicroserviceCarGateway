package dk.dd.carsearch;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserSearchController {
    private UserSearchClient userSearchClient;
    private static List<Rating> ratingList = new ArrayList<>();
    private CarSearchClient carClient;


    public UserSearchController(UserSearchClient userSearchClient, CarSearchClient carClient) {
        this.userSearchClient = userSearchClient;
        this.carClient = carClient;
    }

    @PostMapping("/")
    @ResponseBody
    @CrossOrigin(origins = "*") // allow request from any client
    public User createUser(@RequestBody User user){
        Collection<User> users = userSearchClient.postUser().getContent();
        if (users.contains(user))
            return user;
        return null;
    }


}
