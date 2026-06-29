package org.example.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "status")
public class Status {
    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private Set<Order> orders;
}
