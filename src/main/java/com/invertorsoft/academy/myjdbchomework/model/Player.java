package com.invertorsoft.academy.myjdbchomework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    private Long id;
    private String playerFullName;
    private String nationality;
    private Long shirtNumber;
    private String playerPosition;
}
