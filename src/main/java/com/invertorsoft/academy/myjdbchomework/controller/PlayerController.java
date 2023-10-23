package com.invertorsoft.academy.myjdbchomework.controller;

import com.invertorsoft.academy.myjdbchomework.DAO.PlayerDAO;
import com.invertorsoft.academy.myjdbchomework.model.Player;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
@AllArgsConstructor
public class PlayerController {
    private final PlayerDAO playerDAO;

    @GetMapping("/{id}")
    public Player showPlayer(@PathVariable Long id) {
        return playerDAO.showPlayer(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePlayer(@RequestBody Player player) {
        playerDAO.save(player);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePlayer(@PathVariable Long id, @RequestBody Player updatedPlayer) {
        playerDAO.updatePlayer(id, updatedPlayer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePlayer(@PathVariable Long id) {
        playerDAO.deletePlayer(id);
    }
}
