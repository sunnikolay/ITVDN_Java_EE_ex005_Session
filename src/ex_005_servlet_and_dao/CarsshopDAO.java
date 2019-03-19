package ex_005_servlet_and_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarsshopDAO {
	
	public List<Car> getAll() {
        Connection connection = null;
        Statement statement = null;

        List<Car> cars = new ArrayList<>();

        try {
            connection   = DriverManager.getConnection("jdbc:mysql://172.30.10.73:3306/carsshop?serverTimezone=UTC", "administrator", "rjvec2016");
            statement    = connection.createStatement();
            String sql   = "SELECT id, mark_id, model, price, (SELECT mark FROM marks WHERE id = mark_id) as mark FROM cars";
            ResultSet rs = statement.executeQuery( sql );

            while (rs.next()) {
                long id = rs.getLong("id");
                String model = rs.getString("model");
                int price = rs.getInt("price");
                String mark = rs.getString("mark");
                Car car = new Car();
                car.setId(id);
                car.setMark(mark);
                car.setModel(model);
                car.setPrice(price);
                cars.add(car);
            }

        } 
        catch ( SQLException e ) {
            e.printStackTrace();
        }
        finally {
            if (connection != null && statement != null) {

                try {
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return cars;
    }

}
