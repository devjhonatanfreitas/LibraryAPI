package com.jhonatanfreitas.LibraryAPI.Book;

import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookModel addBook(BookModel book){

        //TODO verify if ISBN is already present on list

        if (book.getAvailability() == false){
            book.setAvailability(true);
        }
        return bookRepository.save(book);
    }

    public List<BookModel> getAllBooks(){

        //TODO sort by title, publication date, author

        return bookRepository.findAll();
    }

    public BookModel getBookById(Long id){
        Optional<BookModel> bookById = bookRepository.findById(id);
        return bookById.orElse(null);
    }

    public BookModel updateBook(Long id, BookModel updatedBook){
        if(bookRepository.existsById(id)){
            updatedBook.setId(id);
            return bookRepository.save(updatedBook);
        }
        return null;
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    public BookModel rentBook(Long id){

        BookModel book = bookRepository.findById(id)
                .orElseThrow(() -> new BookException(
                        "Book with ID "+id+" not found",
                        BookException.ErrorType.NOT_FOUND));

        if (book.getAvailability() == false){
            throw new BookException(
                    "Book with ID"+id+" is not available",
                    BookException.ErrorType.ALREADY_RENTED);
        }
        book.setAvailability(false);
        return bookRepository.save(book);
    }

    public BookModel returnBook (Long id) {

        BookModel book = bookRepository.findById(id)
        .orElseThrow(() -> new BookException(
                "Book with ID "+id+" not found",
                BookException.ErrorType.NOT_FOUND));

        if (book.getAvailability() == true){
            throw new BookException(
                    "Book with ID "+id+" is already available",
                    BookException.ErrorType.NOT_RENTED);

        }

        book.setAvailability(true);
        return bookRepository.save(book);
    }
}
