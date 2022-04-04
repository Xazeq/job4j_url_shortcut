package ru.job4j.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.model.Site;
import ru.job4j.model.SiteDTO;
import ru.job4j.repository.SiteRepository;
import ru.job4j.utils.RandomStringGenerator;

@Service
public class SiteService {
    private final SiteRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public SiteService(SiteRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public SiteDTO signUp(Site site) {
        Site check = repository.findByName(site.getName());
        if (check != null) {
            return new SiteDTO(true, "login", "password");
        } else {
            String password = RandomStringGenerator.generateString(30);
            site.setLogin(RandomStringGenerator.generateString(20));
            site.setPassword(passwordEncoder.encode(password));
            repository.save(site);
            return new SiteDTO(false, site.getLogin(), password);
        }
    }

    public Site findByLogin(String login) {
        return repository.findByLogin(login);
    }
}
