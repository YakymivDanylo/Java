package org.example.Violation;
import com.mongodb.client.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.example.Car.CarDTO;
import org.example.Owner.OwnerDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ViolationDAO {
    private List<ViolationDTO> violations= new ArrayList<>();

    private OwnerDTO setOwner(String id){
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        try(MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("Owner");

            FindIterable<Document> documents = collection.find();
            for (Document doc: documents) {
                if (doc.getObjectId("_id").toString().equals(id)) {
                    String newId = doc.getObjectId("_id").toString();
                    String owner_fullanem = doc.getString("owner_fullanem");
                    String owner_mobile_phone = doc.getString("owner_mobile_phone");
                    String owner_email = doc.getString("owner_email");
                    String owner_address = doc.getString("owner_address");
                    return new OwnerDTO(id,owner_fullanem,owner_mobile_phone,owner_email,owner_address);

                }
            }
            return null;
        }
    }

    private CarDTO setCar(String id){
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        try(MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("Car");

            FindIterable<Document> documents = collection.find();
            for (Document doc: documents) {
                if (doc.getObjectId("_id").toString().equals(id)) {
                    String newId = doc.getObjectId("_id").toString();
                    String car_number = doc.getString("car_number");
                    String model = doc.getString("car_model");
                    String color = doc.getString("car_color");
                    String year = doc.getString("year_of_manufacturing");
                    String brand = doc.getString("car_brand");
                    OwnerDTO ownerDTO = setOwner(doc.getObjectId("_id").toString());
                    return new CarDTO(id,car_number,model,color,year,brand,ownerDTO);
                }
            }
            return null;
        }
    }

    private void setViolation(){
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        try(MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> collection = database.getCollection("Violation");
            FindIterable<Document> documents = collection.find();
            for (Document doc: documents) {
                String id = doc.getObjectId("_id").toString();
                String type = doc.getString("violation_type");
                String date = doc.getString("violation_date");
                String place = doc.getString("violation_place");
                String time = doc.getString("violation_time");
                CarDTO carDTO = setCar(doc.getObjectId("_id").toString());

                ViolationDTO violationDTO = new ViolationDTO(id,carDTO,type,date,place,time);
                violations.add(violationDTO);

            }
        }
    }
}
