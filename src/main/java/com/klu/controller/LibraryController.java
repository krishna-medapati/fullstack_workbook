package com.klu.controller;

import com.klu.model.Book;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class LibraryController {

    private List<Book> bookList = new ArrayList<>();

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Online Library System!";
    }

    @GetMapping("/count")
    public int getTotalBooks() {
        return 250;
    }

    @GetMapping("/price")
    public double getSamplePrice() {
        return 499.99;
    }

    @GetMapping("/books")
    public List<String> getAllBooks() {
        return Arrays.asList(
            "The Pragmatic Programmer",
            "Clean Code",
            "Introduction to Algorithms",
            "Design Patterns",
            "Spring in Action"
        );
    }

    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable int id) {
        switch (id) {
            case 1: return "Book ID 1: 'The Pragmatic Programmer' by Andrew Hunt";
            case 2: return "Book ID 2: 'Clean Code' by Robert C. Martin";
            case 3: return "Book ID 3: 'Introduction to Algorithms' by CLRS";
            default: return "Book ID " + id + " not found.";
        }
    }

    @GetMapping("/search")
    public String searchByTitle(@RequestParam String title) {
        return "Searching for book with title: '" + title + "'. Results found!";
    }

    @GetMapping("/author/{name}")
    public String getBooksByAuthor(@PathVariable String name) {
        return "Fetching all books written by author: " + name;
    }

    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        bookList.add(book);
        return "Book added successfully: " + book.getTitle() + " by " + book.getAuthor();
    }

    @GetMapping("/viewbooks")
    public List<Book> viewAllBooks() {
        return bookList;
    }
}
