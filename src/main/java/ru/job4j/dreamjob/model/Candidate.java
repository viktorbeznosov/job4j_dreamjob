package ru.job4j.dreamjob.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Candidate {
    private int id;

    private String name;

    private String description;

    private Date creationDate;

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
        this.creationDate = new Date();
    }

    public Candidate(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = new Date();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getCreationDateString() {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.YYYY");
        return df.format(creationDate);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
