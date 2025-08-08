package com.jhonatanfreitas.LibraryAPI.Book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/books")
@CrossOrigin(origins = "*")
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

    @GetMapping("/book/{id}")
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
                    .body("Book with ID "+id+" is not on our records.");
        }
    }

    @DeleteMapping("delete/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }

    @PatchMapping("/rent/{id}")
    public ResponseEntity<String> rentBook(@PathVariable Long id){
        try {
            BookModel rentedBook = bookService.rentBook(id);
            return ResponseEntity.ok("The book " + rentedBook.getTitle() + " has been reserved.");
        } catch (BookException e){
            HttpStatus status = switch (e.getErrorType()){
                case NOT_FOUND -> HttpStatus.NOT_FOUND;
                case NOT_RENTED -> HttpStatus.BAD_REQUEST;
                default -> HttpStatus.INTERNAL_SERVER_ERROR;
            };
            return ResponseEntity.status(status).body(e.getMessage());
        }
    }

    @PatchMapping("/return/{id}")
    public ResponseEntity<String> returnBook(@PathVariable Long id){
        try {
            BookModel returnedBook = bookService.returnBook(id);
            return ResponseEntity.ok("The book " + returnedBook.getTitle() + " has been returned.");
        } catch (BookException e){
            HttpStatus status = switch (e.getErrorType()){
                case NOT_FOUND -> HttpStatus.NOT_FOUND;
                case NOT_RENTED -> HttpStatus.BAD_REQUEST;
                default -> HttpStatus.INTERNAL_SERVER_ERROR;
            };
            return ResponseEntity.status(status).body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public List<BookModel> search(@RequestParam String type, @RequestParam String q){
        if (q == null || q.trim().isEmpty()){
            throw new BookException("Search params cannot be empty", BookException.ErrorType.INVALID_DATA);
        }
        switch (type.toLowerCase()){
            case "title":
                return bookService.findByTitleContainingIgnoreCase(q);
            case "author":
                return bookService.findByAuthorContainingIgnoreCase(q);
            case "genre":
                return bookService.findByGenreContainingIgnoreCase(q);
            case "isbn":
                return bookService.findByIsbn(q);
            default:
                throw new BookException("Searching type is not valid: "+type+". Try to use author, genre, title or isbn",
                        BookException.ErrorType.INVALID_DATA);
        }

    }
}



