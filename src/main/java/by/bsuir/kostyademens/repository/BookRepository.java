package by.bsuir.kostyademens.repository;

import by.bsuir.kostyademens.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
