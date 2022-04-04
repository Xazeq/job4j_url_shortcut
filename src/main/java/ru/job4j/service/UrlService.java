package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.StatisticDTO;
import ru.job4j.model.Url;
import ru.job4j.repository.UrlRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UrlService {
    private final UrlRepository repository;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public Url findByEncodedUrl(String shortcut) {
        Url url = repository.findByEncodedUrl(shortcut);
        repository.updateUrlCallCount(url.getId());
        return url;
    }

    public Url save(Url url) {
        return repository.save(url);
    }

    public List<StatisticDTO> getStatistics(int siteId) {
        List<StatisticDTO> statisticList = new ArrayList<>();
        List<Url> urls = repository.findBySiteId(siteId);
        for (Url url : urls) {
            statisticList.add(new StatisticDTO(url.getUrl(), url.getCallCount()));
        }
        return statisticList;
    }
}
