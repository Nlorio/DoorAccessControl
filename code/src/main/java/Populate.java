//import com.sun.xml.internal.txw2.output.DumpSerializer;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Scanner;

public class Populate {
    public static void main(String[] args) throws IOException, SQLException {
        // Delete if exists. For testing purposes.
        Files.deleteIfExists(Paths.get("src/main/resources/database/test.db"));

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:sqlite:src/main/resources/database/test.db");
        HikariDataSource ds = new HikariDataSource(hikariConfig);

        // Read sql file into string
        InputStream sqlFile = Populate.class.getResourceAsStream("/database/tables.sql");

        // Split each statement by semi colons (parse)
        Scanner sql_snippet = new Scanner(sqlFile).useDelimiter(";");
//        System.out.println(sql_snippet.next());

        try (Connection connection = ds.getConnection()) {

            // Run each statement to create the tables
            while (sql_snippet.hasNext()) {
                Statement stmnt = connection.createStatement();
                String sql_to_execute = sql_snippet.next();
                System.out.println(sql_to_execute);
                stmnt.executeUpdate(sql_to_execute);
            }

            // Execute Query.
            // Fill dummy values
            try (PreparedStatement s = connection.prepareStatement("INSERT INTO users VALUES (0, 'dummyuser', 'true');")) {
                s.executeUpdate();
            }


            // Execute query on DB
            try (PreparedStatement s = connection.prepareStatement("SELECT * FROM users;")) {
                ResultSet rs = s.executeQuery();
                String userName = rs.getString("userName");
                int id = rs.getInt("userId");
                String access = rs.getString("grantAccess");
//                System.out.println(rs.getInt("userId"));
                System.out.println(userName + ", "  + id + ", " + access);

            }

        }
        System.out.println("Hello world");
    }
}
