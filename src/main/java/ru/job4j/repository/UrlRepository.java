package ru.job4j.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.model.Url;

import java.util.List;

public interface UrlRepository extends CrudRepository<Url, Integer> {
    Url findByEncodedUrl(String encodedUrl);

    @Transactional
    @Modifying
    @Query("update Url u set u.callCount = u.callCount + 1 where u.id = :id")
    void updateUrlCallCount(@Param("id") int id);

    List<Url> findBySiteId(int id);
}
