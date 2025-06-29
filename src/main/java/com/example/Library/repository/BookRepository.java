package com.example.Library.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByGenreContainingIgnoreCase(String genre);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByPublishedYearBetween(Integer startYear, Integer endYear);
    List<Book> findAllByOrderByPublishedYearDesc(Pageable pageable);
    List<Book> findByCopiesGreaterThan(int count);





}
