package org.example.bookstore.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(nullable = false, unique = true)
    private String id;

    private String title;
    private String author;
    private String category;
    private int year;
    private int pagesNum;

    @Column(columnDefinition = "NUMERIC")
    private double price;

    public Book copy(){
        return Book.builder()
                .id(id)
                .title(title)
                .author(author)
                .category(category)
                .year(year)
                .pagesNum(pagesNum)
                .price(price)
                .build();
    }
}
