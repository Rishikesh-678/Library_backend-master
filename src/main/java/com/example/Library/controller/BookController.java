package com.example.Library.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.Library.model.Book;
import com.example.Library.repository.BookRepository;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    private final BookRepository repo;
    

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    @GetMapping("/search/author-contains")
    public ResponseEntity<List<Book>> searchByPartialAuthor(@RequestParam String author) {
        List<Book> books = repo.findByAuthorContainingIgnoreCase(author);
        return books.isEmpty()
            ? ResponseEntity.notFound().build()
            : ResponseEntity.ok(books);
    }

    @GetMapping("/search/genre-contains")
    public ResponseEntity<List<Book>> searchByPartialGenre(@RequestParam String genre) {
        List<Book> books = repo.findByGenreContainingIgnoreCase(genre);
        return books.isEmpty()
            ? ResponseEntity.notFound().build()
            : ResponseEntity.ok(books);
    }
    @GetMapping("/search/title-contains")
    public ResponseEntity<List<Book>> searchByPartialTitle(@RequestParam String title) {
        List<Book> books = repo.findByTitleContainingIgnoreCase(title);
        return books.isEmpty()
            ? ResponseEntity.notFound().build()
            : ResponseEntity.ok(books);
    }
    
    @GetMapping("/search/year-range")
    public ResponseEntity<List<Book>> getBooksByYearRange(@RequestParam Integer start, @RequestParam Integer end) {
        List<Book> books = repo.findByPublishedYearBetween(start, end);
        return books.isEmpty()
            ? ResponseEntity.notFound().build()
            : ResponseEntity.ok(books);
    }
    @GetMapping("/recent")
    public ResponseEntity<List<Book>> getTopNRecentBooks(@RequestParam(defaultValue = "5") int count) {
        List<Book> books = repo.findAllByOrderByPublishedYearDesc(PageRequest.of(0, count));
        return books.isEmpty()
            ? ResponseEntity.notFound().build()
            : ResponseEntity.ok(books);
    }
    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        List<Book> books = repo.findByCopiesGreaterThan(0);
        return books.isEmpty()
            ? ResponseEntity.notFound().build() 
            : ResponseEntity.ok(books);
}



    @PostMapping
    public Book addBook(@Valid @RequestBody Book book) {
        return repo.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book book = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Book with ID " + id + " not found"));

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setGenre(updatedBook.getGenre());
        book.setPublishedYear(updatedBook.getPublishedYear());
        book.setCopies(updatedBook.getCopies());

        return ResponseEntity.ok(repo.save(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBook(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with ID " + id + " not found");
        }

        repo.deleteById(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Book with ID " + id + " deleted successfully");

        return ResponseEntity.ok(response);
    }

}
