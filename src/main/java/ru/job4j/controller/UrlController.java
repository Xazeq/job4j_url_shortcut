package ru.job4j.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Site;
import ru.job4j.model.StatisticDTO;
import ru.job4j.model.Url;
import ru.job4j.service.SiteService;
import ru.job4j.service.UrlService;
import ru.job4j.utils.RandomStringGenerator;

import java.net.URI;
import java.util.List;

@RestController
public class UrlController {
    private final UrlService urlService;
    private final SiteService siteService;

    public UrlController(UrlService urlService, SiteService siteService) {
        this.urlService = urlService;
        this.siteService = siteService;
    }

    @PostMapping("/convert")
    public ResponseEntity<String> saveUrl(@RequestBody Url url) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Site site = siteService.findByLogin(auth.getName());
        String encodedUrl = RandomStringGenerator.generateString(7);
        url.setEncodedUrl(encodedUrl);
        url.setSite(site);
        urlService.save(url);
        String json = "{"
                + "\"code\" : \"" + encodedUrl + "\""
                + "}";
        return new ResponseEntity<>(
                json,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/redirect/{shortcut}")
    public ResponseEntity<Void> redirect(@PathVariable String shortcut) {
        Url url = urlService.findByEncodedUrl(shortcut);
        if (url == null) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getUrl()));
        return new ResponseEntity<>(
                headers,
                HttpStatus.FOUND
        );
    }

    @GetMapping("/statistics")
    public ResponseEntity<List<StatisticDTO>> getStatistic() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Site site = siteService.findByLogin(auth.getName());
        return new ResponseEntity<>(
                urlService.getStatistics(site.getId()),
                HttpStatus.OK
        );
    }
}
