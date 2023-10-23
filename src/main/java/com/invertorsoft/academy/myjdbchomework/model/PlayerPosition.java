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
public class PlayerPosition {
    @Id
    private Long id;
    private String position;
}
