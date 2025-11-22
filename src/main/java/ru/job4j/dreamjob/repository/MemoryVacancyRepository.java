package ru.job4j.dreamjob.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Vacancy;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
@Repository
public class MemoryVacancyRepository implements VacancyRepository {
    private int nextId = 1;

    private final Map<Integer, Vacancy> vacancies = new ConcurrentHashMap<>();

    private MemoryVacancyRepository() {
        save(new Vacancy(0, "Intern Java Developer", "Lorem ipsum dolor sit amend", true));
        save(new Vacancy(0, "Junior Java Developer", "Lorem ipsum dolor sit amend", true));
        save(new Vacancy(0, "Junior+ Java Developer", "Lorem ipsum dolor sit amend", true));
        save(new Vacancy(0, "Middle Java Developer", "Lorem ipsum dolor sit amend", false));
        save(new Vacancy(0, "Middle+ Java Developer", "Lorem ipsum dolor sit amend", false));
        save(new Vacancy(0, "Senior Java Developer", "Lorem ipsum dolor sit amend", false));
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        vacancy.setId(nextId++);
        vacancies.put(vacancy.getId(), vacancy);
        return vacancy;
    }

    @Override
    public void deleteById(int id) {
        vacancies.remove(id);
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return vacancies.computeIfPresent(vacancy.getId(), (id, oldVacancy) -> {
            return new Vacancy(oldVacancy.getId(), vacancy.getTitle(), vacancy.getDescription(), vacancy.getCreationDate(), vacancy.getVisible());
        }) != null;
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        return Optional.ofNullable(vacancies.get(id));
    }

    @Override
    public Collection<Vacancy> findAll() {
        return vacancies.values();
    }
}