package com.invertorsoft.academy.myjdbchomework.DAO;

import com.invertorsoft.academy.myjdbchomework.model.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Component;

@Component
public class PlayerDAO {
    public Player showPlayer(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        Player player = em.find(Player.class, id);
        em.close();
        emf.close();
        return player;
    }

    public void save(Player player) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(player);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public void updatePlayer(Long id, Player updatedPlayer) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Player player = em.find(Player.class, id);
        player.setPlayerFullName(updatedPlayer.getPlayerFullName());
        player.setNationality(updatedPlayer.getNationality());
        player.setShirtNumber(updatedPlayer.getShirtNumber());
        player.setPlayerPosition(updatedPlayer.getPlayerPosition());

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public void deletePlayer(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Player player = em.find(Player.class, id);
        em.remove(player);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

 /*   @PreDestroy
    public void close() {
        emf.close();
    }*/
}