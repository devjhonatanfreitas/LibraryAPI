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

}
