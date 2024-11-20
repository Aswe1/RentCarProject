CREATE TABLE IF NOT EXISTS cars (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      model VARCHAR(255) NOT NULL,
                      daily_price DECIMAL(10, 2) NOT NULL,
                      location VARCHAR(255) NOT NULL,
                      is_deleted BOOLEAN DEFAULT FALSE,
                      is_rented BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS offers (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        car_id INT NOT NULL,
                        customer_name VARCHAR(255) NOT NULL,
                        rental_days INT NOT NULL,
                        total_price DECIMAL(10, 2) NOT NULL,
                        accepted BOOLEAN DEFAULT FALSE,
                        accidents BOOLEAN DEFAULT FALSE,
                        is_deleted BOOLEAN DEFAULT FALSE,
                        FOREIGN KEY (car_id) REFERENCES cars(id)
);

