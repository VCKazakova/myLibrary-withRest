package com.kazakova.mylibrarywithrest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "book_title")
    private String bookTitle;

    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.PERSIST) // onetoone -> manytoone
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.PERSIST, mappedBy = "book", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Comment> comment = new ArrayList<>();


    public Book(Long id, String bookTitle, Author author, Genre genre) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.author = author;
        this.genre = genre;
    }

    public Book(Long bookId) {
        this.id = bookId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                '}';
    }
}
