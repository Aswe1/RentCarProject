package org.example.rentcarproject.Repository;

import org.example.rentcarproject.Entities.Car;
import org.example.rentcarproject.Entities.Offer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OfferRepository
{
    private final JdbcTemplate jdbcTemplate;

    public OfferRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Offer> findAllByCustomerName(String customerName) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM offers WHERE customer_name = '")
                .append(customerName)
                .append("'");

        return jdbcTemplate.query(query.toString(), (rs, rowNum) -> {
            Offer offer = new Offer();
            offer.setId(rs.getInt("id"));
            offer.setCarId(rs.getInt("car_id"));
            offer.setCustomerName(rs.getString("customer_name"));
            offer.setRentalDays(rs.getInt("rental_days"));
            offer.setTotalPrice(rs.getDouble("total_price"));
            offer.setAccepted(rs.getBoolean("accepted"));
            offer.setAccidents(rs.getBoolean("accidents"));
            return offer;
        });
    }

    public Offer findById(int id) {
        String sql = "SELECT * FROM offers WHERE id = ?";
        List<Offer> offers = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Offer offer = new Offer();
            offer.setId(rs.getInt("id"));
            offer.setCarId(rs.getInt("car_id"));
            offer.setCustomerName(rs.getString("customer_name"));
            offer.setRentalDays(rs.getInt("rental_days"));
            offer.setTotalPrice(rs.getDouble("total_price"));
            offer.setAccepted(rs.getBoolean("accepted"));
            offer.setAccidents(rs.getBoolean("accidents"));
            return offer;
        }, id);

        return offers.isEmpty() ? null : offers.get(0);
    }

    public void create(Offer offer)
    {
         StringBuilder query = new StringBuilder();
               query.append("INSERT INTO offers (car_id, customer_name, rental_days, total_price, accepted, accidents) ")
                .append("VALUES (")
                .append(offer.getCarId())
                .append(", '")
                .append(offer.getCustomerName())
                .append("', ")
                .append(offer.getRentalDays())
                .append(", ")
                .append(offer.getTotalPrice())
                .append(", ")
                .append(offer.getAccepted())
                .append(", ")
                .append(offer.getAccidents())
                .append(")");

        jdbcTemplate.update(query.toString());
    }

    public void accept(int id) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE offers SET accepted = true WHERE id = ").append(id);
        jdbcTemplate.update(query.toString());
    }

    public void softDelete(int id) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE offers SET is_deleted = true WHERE id = ").append(id);
        jdbcTemplate.update(query.toString());
    }
}
