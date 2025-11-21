package ru.job4j.dreamjob.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class MemoryCandidateRepository implements CandidateRepository {
    private int nextId = 1;

    private final Map<Integer, Candidate> candidates = new HashMap<>();

    public MemoryCandidateRepository() {
        save(new Candidate(0, "John Doe", "Lorem ipsum dolor sit amend"));
        save(new Candidate(0, "Amidala Ashbluff", "Lorem ipsum dolor sit amend"));
        save(new Candidate(0, "Gen Bozzelli", "Lorem ipsum dolor sit amend"));
        save(new Candidate(0, "Arwen Missandei", "Lorem ipsum dolor sit amend"));
        save(new Candidate(0, "Olenna Hayes", "Lorem ipsum dolor sit amend"));
        save(new Candidate(0, "Sam  Ashbluff", "Lorem ipsum dolor sit amend"));
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId++);
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public void deleteById(int id) {
        candidates.remove(id);
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(),
                (id, oldCandidate) -> new Candidate(oldCandidate.getId(), candidate.getName(), candidate.getDescription())) != null;
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return Optional.ofNullable(candidates.get(id));
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
