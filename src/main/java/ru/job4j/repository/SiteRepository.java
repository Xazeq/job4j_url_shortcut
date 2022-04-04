package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.Site;

public interface SiteRepository extends CrudRepository<Site, Integer> {
    Site findByName(String name);
    Site findByLogin(String login);
}
