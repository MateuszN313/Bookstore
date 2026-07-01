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
@Table(name = "book_amount")
public class BookAmount {
    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private int amount;

    public BookAmount copy(){
        return builder()
                .id(id)
                .book(book)
                .amount(amount)
                .build();
    }
}
