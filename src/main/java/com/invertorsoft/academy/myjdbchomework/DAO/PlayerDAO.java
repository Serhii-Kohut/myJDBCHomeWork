package com.invertorsoft.academy.myjdbchomework.DAO;

import com.invertorsoft.academy.myjdbchomework.model.Player;
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

@Component
public class PlayerDAO {
    @Value("${spring.datasource.url}")
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


    public Player showPlayer(Long id) {
        Player player = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM players WHERE id = ?")) {

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    player = new Player();
                    player.setId(resultSet.getLong("id"));
                    player.setPlayerFullName(resultSet.getString("playerfullname"));
                    player.setNationality(resultSet.getString("nationality"));
                    player.setShirtNumber(resultSet.getLong("shirtNumber"));
                    player.setPlayerPosition(resultSet.getString("playerposition"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error showing player", e);
        }
        return player;
    }

    public void save(Player player) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO players (playerfullname, nationality, shirtnumber, playerposition) VALUES (?,?,?,?)")) {

            preparedStatement.setString(1, player.getPlayerFullName());
            preparedStatement.setString(2, player.getNationality());
            preparedStatement.setLong(3, player.getShirtNumber());
            preparedStatement.setString(4, player.getPlayerPosition());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error saving player", e);
        }
    }

    public void updatePlayer(Long id, Player updatedPlayer) {
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
    }

    public void deletePlayer(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM players WHERE id=?")) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error deleting player", e);
        }
    }
}
