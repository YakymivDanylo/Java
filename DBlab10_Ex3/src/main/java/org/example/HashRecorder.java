package org.example;

import redis.clients.jedis.Jedis;
import java.util.HashMap;
import java.util.Map;

public class HashRecorder {

    private static final String REDIS_HOST = "localhost";
    private static final int REDIS_PORT = 6379;

    private final Jedis jedis;
    public HashRecorder() {jedis = new Jedis(REDIS_HOST, REDIS_PORT);}

    public void createOwner(String id, String address, String email, String name, String phone){
        Map<String,String> owner = new HashMap<>();
        owner.put("id", id);
        owner.put("address", address);
        owner.put("email", email);
        owner.put("name", name);
        owner.put("phone", phone);
        jedis.hset("owner:" + id, owner);
    }
    public void createCar(String id, String Owner_owner_id, String car_brand, String car_color, String car_model, String car_number, String year_of_manufacturing){
        Map<String,String> car = new HashMap<>();
        car.put("id", id);
        car.put("Owner_owner_id", Owner_owner_id);
        car.put("car_brand", car_brand);
        car.put("car_color", car_color);
        car.put("car_model", car_model);
        car.put("car_number", car_number);
        car.put("year_of_manufacturing", year_of_manufacturing);
        jedis.hset("car:" + id, car);
    }
    public void createEmployee(String id, String name, String position){
        Map<String,String> employee = new HashMap<>();
        employee.put("id", id);
        employee.put("name", name);
        employee.put("position", position);
        jedis.hset("employee:" + id, employee);
    }
    public void createFine(String id, String Violation_violation_id, String data, String payment_status, String sum){
        Map<String,String> fine = new HashMap<>();
        fine.put("id", id);
        fine.put("Violation_violation_id", Violation_violation_id);
        fine.put("data", data);
        fine.put("payment_status", payment_status);
        fine.put("sum", sum);
        jedis.hset("fine:" + id, fine);
    }
    public void createSystem(String id, String Employee_id, String Violation_violation_id,String position, String type){
        Map<String,String> system = new HashMap<>();
        system.put("id", id);
        system.put("Employee_id", Employee_id);
        system.put("Violation_violation_id", Violation_violation_id);
        system.put("position", position);
        system.put("type", type);
        jedis.hset("system:" + id, system);
    }
    public void createViolation(String id, String Car_car_number, String violation_type, String data, String place, String time){
        Map<String,String> violation = new HashMap<>();
        violation.put("id", id);
        violation.put("Car_car_number", Car_car_number);
        violation.put("violation_type", violation_type);
        violation.put("data", data);
        violation.put("place", place);
        violation.put("time", time);
        jedis.hset("violation:" + id, violation);
    }
    public Map<String,String> read(String key){return jedis.hgetAll(key);}

    public void updateCar(String id, String Owner_owner_id, String car_brand, String car_color, String car_model, String car_number, String year_of_manufacturing){
        Map<String,String> car = new HashMap<>();
        car.put("id", id);
        car.put("Owner_owner_id", Owner_owner_id);
        car.put("car_brand", car_brand);
        car.put("car_color", car_color);
        car.put("car_model", car_model);
        car.put("car_number", car_number);
        car.put("year_of_manufacturing", year_of_manufacturing);
        jedis.hset("car:" + id, car);
    }
    public void updateEmployee(String id, String name, String position){
        Map<String,String> employee = new HashMap<>();
        employee.put("id", id);
        employee.put("name", name);
        employee.put("position", position);
        jedis.hset("employee:" + id, employee);
    }
    public void delete(String key){jedis.del(key);}
}
