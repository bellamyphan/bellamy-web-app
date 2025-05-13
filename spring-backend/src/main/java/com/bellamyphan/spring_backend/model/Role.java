package com.bellamyphan.spring_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data // Lombok will generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank(message = "Role name is required")
    private String name;

    // Constructor with parameters excluding id
    public Role(String name) {
        this.name = name;
    }
}
