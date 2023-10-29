package com.invertorsoft.academy.myjdbchomework.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "players")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "playerfullname")
    private String playerFullName;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "shirtnumber")
    private Long shirtNumber;

    @Column(name = "playerposition")
    private String playerPosition;
}
