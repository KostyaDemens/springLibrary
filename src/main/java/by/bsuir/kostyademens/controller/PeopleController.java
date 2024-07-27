package by.bsuir.kostyademens.controller;

import by.bsuir.kostyademens.model.Person;
import by.bsuir.kostyademens.repository.PeopleRepository;
import by.bsuir.kostyademens.service.PeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService, PeopleRepository peopleRepository) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", peopleService.showAll());
        return "people/main";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("person") Person person) {
        return "/people/new";
    }

    @PostMapping("/new")
    public String createNewPerson(Person person) {
        peopleService.add(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("person", peopleService.showOne(id));
        return "people/edit";
    }

    @GetMapping("/{id}")
    public String personPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("person", peopleService.showOne(id));
        return "people/personPage";
    }

    @PatchMapping("/{id}")
    public String editPerson(Person person, @PathVariable("id") Long id) {
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") Long id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}
