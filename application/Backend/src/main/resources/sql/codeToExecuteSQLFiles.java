import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public class DatabaseInitializer {

    public static void initializeDatabase(Connection connection) {
        try (InputStream inputStream = DatabaseInitializer.class.getClassLoader().getResourceAsStream("starterSQLFiles.sql")) {
            String sql = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
