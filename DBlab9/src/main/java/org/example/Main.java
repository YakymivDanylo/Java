package org.example;

import com.mongodb.DBRef;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import org.bson.Document;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class Main {
    public static void liner() {
        System.out.println("--------------------------------------------------");
    }

    /*1.Вивести повну інформацію про порушників.*/
    public static void task1(MongoCollection<Document> collectionViolation) {
        AggregateIterable<Document> result = collectionViolation.aggregate(Arrays.asList(
                project(new Document("_id", 0)
                        .append("violation_id", "$violation_id")
                        .append("violation_type", "$violation_type")
                        .append("violation_data", "$violation_data")
                        .append("violation_time", "$violation_time"))
        ));
        for (Document document : result) {
            System.out.println(document);
        }
    }

    /*2.Знайти порушника з найбільшою кількістю порушень.*/
    public static void task2(MongoCollection<Document> collectionViolation) {
        AggregateIterable<Document> result = collectionViolation.aggregate(Arrays.asList(
                group("$Car_car_number", sum("totalViolations", 1)),
                Aggregates.sort(descending("totalViolations")),
                Aggregates.limit(1),
                Aggregates.lookup("Car", "_id", "car_number", "car_info"),
                Aggregates.lookup("Owner", "car_info.Owner_owner_id", "id_owner", "owner_info"),
                project(new Document("_id", 0)
                        .append("car_number", "$_id")
                        .append("totalViolations", 1))
        ));
        for (Document document : result) {
            System.out.println(document);
        }
    }

    /*3.Знайти усіх порушників, чий розмір штрафу більше середнього. */
    public static void task3(MongoCollection<Document> collectionFine) {

        List<Bson> averageFinePipeline = Arrays.asList(
                group(null, avg("avgFine", "$fine_sum"))
        );

        Document avgFineResult = collectionFine.aggregate(averageFinePipeline).first();
        if (avgFineResult == null) {
            System.out.println("Не вдалося знайти середній штраф.");
            return;
        }

        double avgFine = avgFineResult.getDouble("avgFine");

        List<Bson> violatorsPipeline = Arrays.asList(
                Aggregates.match(Filters.gt("fine_sum", avgFine)),
                Aggregates.lookup("Violation", "violation_id", "violation_id", "violation_info"),
                Aggregates.lookup("Car", "car_id", "car_number", "car_info"),
                Aggregates.lookup("Owner", "owner_id", "id_owner", "owner_info"),
                project(new Document("_id", 1) // Включаємо _id
                        .append("fine_payment_status", "$fine_payment_status")
                        .append("violation_info", "$violation_info")
                        .append("car_info", "$car_info")
                        .append("owner_info", "$owner_info")
                        .append("fine_data_of_discharge", "$fine_data_of_discharge")
                        .append("fine_id", "$fine_id")
                        .append("fine_sum", "$fine_sum"))
        );


        AggregateIterable<Document> result = collectionFine.aggregate(violatorsPipeline);
        for (Document document : result) {
            System.out.println(document);
        }
    }

    /*4. Знайти порушника з найбільшим штрафом за весь час*/
    public static void task4(MongoCollection<Document> collectionFine) {

        List<Bson> pipeline = Arrays.asList(

                group("$owner_id", Accumulators.max("maxFine", "$fine_sum")),

                Aggregates.lookup("Violation", "_id", "violation_id", "violation_info"),

                Aggregates.lookup("Car", "car_id", "car_number", "car_info"),

                Aggregates.lookup("Owner", "owner_id", "id_owner", "owner_info"),

                Aggregates.sort(descending("maxFine")),

                Aggregates.limit(1),

                project(new Document("_id", 1)
                        .append("maxFine", "$maxFine")
                        .append("violation_info", "$violation_info")
                        .append("car_info", "$car_info")
                        .append("owner_info", "$owner_info"))
        );

        AggregateIterable<Document> result = collectionFine.aggregate(pipeline);
        Document highestFineDocument = result.first();
        if (highestFineDocument != null) {
            System.out.println("Порушник з найбільшим штрафом:");
            System.out.println(highestFineDocument);
        } else {
            System.out.println("Не вдалося знайти порушника з найбільшим штрафом.");
        }
    }

    /*5.Знайти усі штрафи*/
    public static void task5(MongoCollection<Document> collectionFine) {
        MongoCursor<Document> cursor = collectionFine.find().iterator();

        if (!cursor.hasNext()) {
            System.out.println("У колекції немає штрафів.");
            return;
        }

        while (cursor.hasNext()) {
            Document fineDocument = cursor.next();
            System.out.println(fineDocument);
        }
    }

    /*6.Знайти усі автомобілі певного бренду*/
    public static void task6(MongoCollection<Document> collectionCar, String brand) {
        MongoCursor<Document> cursor = collectionCar.find(Filters.eq("car_brand", brand)).iterator();

        if (!cursor.hasNext()) {
            System.out.println("Автомобілі бренду " + brand + " не знайдено.");
            return;
        }

        System.out.println("Автомобілі бренду " + brand + ":");
        while (cursor.hasNext()) {
            Document carDocument = cursor.next();
            System.out.println(carDocument);
        }
    }
    /*7. Знайти порушення за конкретним типом*/
    public static void task7(MongoCollection<Document> collectionViolation,String violationType){

        MongoCursor<Document> cursor = collectionViolation.find(Filters.eq("vioaltion_type", violationType)).iterator();

        System.out.println("Порушення типу \"" + violationType + "\":");
        while (cursor.hasNext()) {
            Document violationDocument = cursor.next();
            System.out.println(violationDocument);
        }
    }

    /*8.Знайти власника за ID 2*/
    public static void task8(MongoCollection<Document> collectionOwner, int ownerId){
        // Використовуємо фільтр для пошуку власника за ID
        MongoCursor<Document> cursor = collectionOwner.find(Filters.eq("id_owner", ownerId)).iterator();


        if (!cursor.hasNext()) {
            System.out.println("Власника з ID " + ownerId + " не знайдено.");
            return;
        }


        System.out.println("Знайдено власника з ID " + ownerId + ":");
        while (cursor.hasNext()) {
            Document ownerDocument = cursor.next();
            System.out.println(ownerDocument.toJson()); 
        }
    }

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("test");

        MongoCollection<Document> collectionViolation = database.getCollection("Violation");
        MongoCollection<Document> collectionCar = database.getCollection("Car");
        MongoCollection<Document> collectionOwner = database.getCollection("Owner");
        MongoCollection<Document> collectionEmployee = database.getCollection("Employee");
        MongoCollection<Document> collectionFine = database.getCollection("Fine");
        MongoCollection<Document> collectionSystem = database.getCollection("System");

        liner();
        System.out.println("1.Вивести повну інформацію про порушників.");
        task1(collectionViolation);
        liner();
        System.out.println("2.Знайти порушника з найбільшою кількістю порушень.");
        task2(collectionViolation);
        liner();
        System.out.println("3.Знайти усіх порушників, чий розмір штрафу більше середнього.");
        task3(collectionFine);
        liner();
        System.out.println("4. Знайти порушника з найбільшим штрафом за весь час");
        task4(collectionFine);
        liner();
        System.out.println("5.Знайти усі штрафи");
        task5(collectionViolation);
        liner();
        System.out.println("6.Знайти усі автомобілі певного бренду");
        task6(collectionCar, "Toyota");
        liner();
        System.out.println("7. Знайти порушення за конкретним типом");
        task7(collectionViolation,"Неправильне паркування");
        liner();
        System.out.println("8.Знайти власника за ID 2");
        task8(collectionOwner,2);
        liner();
    }
}