package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.model.Site;
import ru.job4j.model.SiteDTO;
import ru.job4j.service.SiteService;

@RestController
@RequestMapping("/registration")
public class SiteController {
    private final SiteService service;

    public SiteController(SiteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SiteDTO> signUp(@RequestBody String site) {
        String siteName = site.split("\"")[3];
        return new ResponseEntity<>(
                service.signUp(Site.of(siteName, null, null)),
                HttpStatus.OK
        );
    }
}
