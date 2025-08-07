package com.jhonatanfreitas.LibraryAPI.Book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository <BookModel, Long> {

    Optional<BookModel> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

}
