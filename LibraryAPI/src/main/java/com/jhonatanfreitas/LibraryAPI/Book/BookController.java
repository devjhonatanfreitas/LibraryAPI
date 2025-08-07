package com.jhonatanfreitas.LibraryAPI.Book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addnew")
    public ResponseEntity <String> addBook(@RequestBody BookModel book){
        BookModel newBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newBook.getTitle()+" adicionado com sucesso!");
    }

    @GetMapping("/getall")
    public ResponseEntity <List<BookModel>> getAllBooks(){
        List<BookModel> allBooks = bookService.getAllBooks();
        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity <BookModel> getBookById(@PathVariable Long id){
        BookModel book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity <?> updateBook(@PathVariable Long id,@RequestBody BookModel updatedBook){
        BookModel book = bookService.updateBook(id, updatedBook);

        if (book != null){
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Livro com o ID "+id+" não consta em nossos registros.");
        }
    }

    @DeleteMapping("delete/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }

}



