import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Populate {
    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("");

        HikariDataSource ds = new HikariDataSource(hikariConfig);
        try (Connection connection = ds.getConnection()) {
            try (PreparedStatement s = connection.prepareStatement("SELECT * FROM table")) {
                s.executeQuery()
            }
        }


        System.out.println("Hello world");
    }
}
