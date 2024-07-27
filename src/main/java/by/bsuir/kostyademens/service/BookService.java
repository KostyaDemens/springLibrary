package by.bsuir.kostyademens.service;

import by.bsuir.kostyademens.model.Book;
import by.bsuir.kostyademens.model.Person;
import by.bsuir.kostyademens.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {


    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> showAll() {
        return bookRepository.findAll();
    }

    @Transactional
    public void add(Book book) {
        bookRepository.save(book);
    }

    public Book showOne(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(Long id, Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void assign(Long id, Person person) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            book.get().setOwner(person);
            update(id, book.get());
        }
    }

    @Transactional
    public void release(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(value -> value.setOwner(null));
    }
}
