package com.vvits.miw.se9.libraryDemo.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer copyId;

    private Boolean available = true;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bookId", referencedColumnName = "bookId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Book book;

    @OneToMany(mappedBy = "book")
    private List<Copy> copies;

    public Integer getCopyId() {
        return copyId;
    }

    public void setCopyId(Integer copyId) {
        this.copyId = copyId;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
