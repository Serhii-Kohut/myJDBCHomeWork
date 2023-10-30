package com.invertorsoft.academy.myjdbchomework.DAO;

import com.invertorsoft.academy.myjdbchomework.model.PlayerPosition;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Component;

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

        em.persist(playerPosition);
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