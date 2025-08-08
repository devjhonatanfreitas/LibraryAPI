package com.jhonatanfreitas.LibraryAPI.Book;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookModel addBook(BookModel book){

        if (book.getIsbn() == null || book.getIsbn().trim().isEmpty()){
            throw new BookException(
                    "ISBN is required.",
                    BookException.ErrorType.INVALID_DATA);
        }

        if (bookRepository.existsByIsbnContainingIgnoreCase(book.getIsbn())){
            throw new BookException(
                    "Book with ISBN "+book.getIsbn()+"already exists",
                    BookException.ErrorType.ALREADY_EXISTS);
        }

        book.setAvailability(true);
        return bookRepository.save(book);
    }

    public List<BookModel> getAllBooks(){

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

    public List<BookModel> findByTitleContainingIgnoreCase(String title){
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<BookModel> findByGenreContainingIgnoreCase(String genre){
        return bookRepository.findByGenreContainingIgnoreCase(genre);
    }

    public List<BookModel> findByAuthorContainingIgnoreCase(String author){
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    public List<BookModel> findByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }


}
