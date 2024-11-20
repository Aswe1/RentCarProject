package org.example.rentcarproject.Repository;


import org.example.rentcarproject.Entities.Car;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository
{
    private final JdbcTemplate jdbcTemplate;

    public CarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Car> findAllByLocationAndIsDeletedFalse(String location) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM CARS WHERE location = '")
                .append(location)
                .append("' AND is_deleted = false");
        return jdbcTemplate.query(query.toString(), (rs, rowNum) -> {
            Car car = new Car();
            car.setId(rs.getInt("id"));
            car.setModel(rs.getString("model"));
            car.setDailyPrice(rs.getDouble("daily_price"));
            car.setLocation(rs.getString("location"));
            car.setDeleted(rs.getBoolean("is_deleted"));
            car.setIsRented(rs.getBoolean("is_rented"));
            return car;
        });
    }

    public Car findById(int id)
    {
        String sql = "SELECT * FROM CARS WHERE id = ?";
        List<Car> cars = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Car car = new Car();
            car.setId(rs.getInt("id"));
            car.setModel(rs.getString("model"));
            car.setDailyPrice(rs.getDouble("daily_price"));
            car.setLocation(rs.getString("location"));
            car.setDeleted(rs.getBoolean("is_deleted"));
            car.setIsRented(rs.getBoolean("is_rented"));
            return car;
        }, id);

        return cars.isEmpty() ? null : cars.get(0);
    }

    public void add(Car car)
    {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO cars (model, daily_price, location, is_deleted, is_rented) ")
                .append("VALUES ('")
                .append(car.getModel())
                .append("', ")
                .append(car.getDailyPrice())
                .append(", '")
                .append(car.getLocation())
                .append("', ")
                .append(car.isDeleted())
                .append(", ")
                .append(car.getIsRented())
                .append(")");

        jdbcTemplate.update(query.toString());
    }

    public void update(Car car)
    {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE cars SET model = '")
                .append(car.getModel())
                .append("', daily_price = ")
                .append(car.getDailyPrice())
                .append(", location = '")
                .append(car.getLocation())
                .append("', is_deleted = ")
                .append(car.isDeleted())
                .append(", is_rented = ")
                .append(car.getIsRented())
                .append(" WHERE id = ")
                .append(car.getId());

        jdbcTemplate.update(query.toString());
    }

    public void softDelete(int id) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE cars SET is_deleted = true WHERE id = ").append(id);
        jdbcTemplate.update(query.toString());
    }
}
