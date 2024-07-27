package by.bsuir.kostyademens.controller;

import by.bsuir.kostyademens.model.Book;
import by.bsuir.kostyademens.model.Person;
import by.bsuir.kostyademens.service.BookService;
import by.bsuir.kostyademens.service.PeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PeopleService peopleService;

    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookService.showAll());
        return "books/main";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping("/new")
    public String addNewBook(Book book) {
        bookService.add(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("book", bookService.showOne(id));
        return "books/edit";
    }

    @GetMapping("/{id}")
    public String bookPage(Model model, @ModelAttribute("person") Person person, @PathVariable("id") Long id) {

        model.addAttribute("book", bookService.showOne(id));
        model.addAttribute("people", peopleService.showAll());
        return "books/bookPage";
    }

    @PatchMapping("/{id}")
    public String editBook(Book book, @PathVariable("id") Long id) {
        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") Long id, @ModelAttribute("person") Person person) {
        bookService.assign(id, person);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") Long id) {
        bookService.release(id);
        return "redirect:/books";
    }



}
