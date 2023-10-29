package com.invertorsoft.academy.myjdbchomework.DAO;

import com.invertorsoft.academy.myjdbchomework.model.PlayerPosition;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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
    public List<PlayerPosition> getAllPositions() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        List<PlayerPosition> playerPositions =
                em.createQuery("SELECT p FROM PlayerPosition p", PlayerPosition.class).getResultList();
        em.close();
        emf.close();
        return playerPositions;
    }

    public PlayerPosition showPlayerPosition(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        PlayerPosition playerPosition = em.find(PlayerPosition.class, id);

        em.close();
        emf.close();
        return playerPosition;
    }

    public void updatePlayerPosition(Long id, PlayerPosition updatedPlayerPosition) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        PlayerPosition playerPosition = em.find(PlayerPosition.class, id);
        playerPosition.setPosition(updatedPlayerPosition.getPosition());

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public void deletePlayerPosition(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        PlayerPosition playerPosition = em.find(PlayerPosition.class, id);
        em.remove(playerPosition);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}