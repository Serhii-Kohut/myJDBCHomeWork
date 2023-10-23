package com.invertorsoft.academy.myjdbchomework.DAO;

import com.invertorsoft.academy.myjdbchomework.model.PlayerPosition;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerPositionDAO {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerPositionDAO.class);
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

    public List<PlayerPosition> getAllPositions() {
        List<PlayerPosition> positions = new ArrayList<>();
        String sql = "SELECT * FROM playerposition";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String position = resultSet.getString("position");
                positions.add(new PlayerPosition(id, position));
            }
        } catch (SQLException e) {
            LOGGER.error("Problem with getting all positions", e);
        }
        return positions;
    }

    public PlayerPosition showPlayerPosition(Long id) {
        PlayerPosition playerPosition = null;
        String sql = "SELECT * FROM playerposition WHERE id = ?";


        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, Math.toIntExact(id));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    playerPosition = new PlayerPosition();
                    playerPosition.setId(resultSet.getLong("id"));
                    playerPosition.setPosition(resultSet.getString("position"));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Problem with getting position", e);
        }
        return playerPosition;
    }

    public void updatePlayerPosition(Long id, PlayerPosition updatedPlayerPosition) {
        String sql = "UPDATE playerposition SET position = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, updatedPlayerPosition.getPosition());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Problem with updating position", e);
        }
    }

    public void deletePlayerPosition(Long id) {
        String sql = "DELETE FROM playerposition WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Problem with deleting position", e);
        }
    }
}
