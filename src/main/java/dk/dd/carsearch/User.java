package dk.dd.carsearch;
import org.bson.types.ObjectId;
public class User {


    private ObjectId id;
    private String name;

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
