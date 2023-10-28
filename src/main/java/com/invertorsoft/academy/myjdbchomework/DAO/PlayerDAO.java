package com.invertorsoft.academy.myjdbchomework.DAO;

import com.invertorsoft.academy.myjdbchomework.model.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Component;

@Component
public class PlayerDAO {

 /*   @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerDAO.class);
    public static String URL;
    public static String USERNAME;
    public static String PASSWORD;
    private static Connection connection;

    @PostConstruct
    public void init() {
        URL = url;
        USERNAME = username;
        PASSWORD = password;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("Problem with initializing connection", e);
        }
    }

    @PreDestroy
    public void cleanup() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Problem with closing connection", e);
        }
    }
*/
/* var emf = Persistence.createEntityManagerFactory("default");
    var em = emf.createEntityManager();
    var player = em.find(Player.class, 1L);
        em.close();
        System.out.println(player);*/

    public Player showPlayer(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Player player = em.find(Player.class, id);
        em.close();
        return player;
    }

/*    public void save(Player player) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO players (playerfullname, nationality, shirtnumber, playerposition) VALUES (?,?,?,?)")) {

            preparedStatement.setString(1, player.getPlayerFullName());
            preparedStatement.setString(2, player.getNationality());
            preparedStatement.setLong(3, player.getShirtNumber());
            preparedStatement.setString(4, player.getPlayerPosition());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error saving player", e);
        }
    }*/

/*    public void updatePlayer(Long id, Player updatedPlayer) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET playerfullname=?, nationality=?, shirtnumber=?, playerposition=? WHERE id=?")) {

            preparedStatement.setString(1, updatedPlayer.getPlayerFullName());
            preparedStatement.setString(2, updatedPlayer.getNationality());
            preparedStatement.setLong(3, updatedPlayer.getShirtNumber());
            preparedStatement.setString(4, updatedPlayer.getPlayerPosition());
            preparedStatement.setLong(5, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error updating player", e);
        }
    }*/

/*    public void deletePlayer(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM players WHERE id=?")) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error deleting player", e);
        }
    }*/
}
