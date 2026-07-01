package org.example.bookstore.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_amount_id", nullable = false)
    private BookAmount bookAmount;

    public Cart copy(){
        return builder()
                .id(id)
                .user(user)
                .bookAmount(bookAmount)
                .build();
    }
}
