package by.bsuir.kostyademens.service;

import by.bsuir.kostyademens.model.Person;
import by.bsuir.kostyademens.repository.PeopleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> showAll() {
        return peopleRepository.findAll();
    }

    public Person showOne(Long id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void add(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(Long id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    public void delete(Long id) {
        peopleRepository.deleteById(id);
    }

}
