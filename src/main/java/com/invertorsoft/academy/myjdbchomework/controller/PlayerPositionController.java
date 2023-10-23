package com.invertorsoft.academy.myjdbchomework.controller;

import com.invertorsoft.academy.myjdbchomework.DAO.PlayerPositionDAO;
import com.invertorsoft.academy.myjdbchomework.model.PlayerPosition;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/playerpositions")
@AllArgsConstructor
public class PlayerPositionController {
    private final PlayerPositionDAO playerPositionDAO;

    @GetMapping("/")
    public List<PlayerPosition> showAllPositions() {
        return playerPositionDAO.getAllPositions();
    }

    @GetMapping("/{id}")
    public PlayerPosition showPlayerPosition(@PathVariable Long id) {
        return playerPositionDAO.showPlayerPosition(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePlayerPosition(@PathVariable Long id, PlayerPosition updatedPlayerPosition) {
        playerPositionDAO.updatePlayerPosition(id, updatedPlayerPosition);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePlayerPosition(@PathVariable Long id) {
        playerPositionDAO.deletePlayerPosition(id);
    }
}
