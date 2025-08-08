package com.jhonatanfreitas.LibraryAPI.Book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository <BookModel, Long> {

    List<BookModel> findByIsbn (String isbn);
    boolean existsByIsbnContainingIgnoreCase(String isbn);
    List<BookModel> findByTitleContainingIgnoreCase(String title);
    List<BookModel> findByAuthorContainingIgnoreCase(String author);
    List<BookModel> findByGenreContainingIgnoreCase(String genre);

}

