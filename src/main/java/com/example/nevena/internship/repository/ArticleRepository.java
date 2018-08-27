package com.example.nevena.internship.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nevena.internship.domain.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	Article findOneById(Long id);
	List<Article>findAll();
}
