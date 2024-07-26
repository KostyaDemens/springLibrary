package by.bsuir.kostyademens.repository;

import by.bsuir.kostyademens.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {
}
