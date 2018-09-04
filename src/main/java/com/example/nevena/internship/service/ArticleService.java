package com.example.nevena.internship.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nevena.internship.domain.Article;
import com.example.nevena.internship.domain.User;
import com.example.nevena.internship.repository.ArticleRepository;
import com.example.nevena.internship.repository.UserRepository;

@Service
public class ArticleService{

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Article createArticle(String name, String description, Long principalId) {
		Article article = new Article();
		article.setName(name);
		article.setDescription(description);
		
		User user = userRepository.findOneById(principalId);
		article.setUser(user);
		user.getLista().add(article);
		userRepository.save(user);
		
		articleRepository.save(article);
		return article;
	}
	
	
	public void deleteArticle(Long id) {
		Article article = articleRepository.findOneById(id);
		articleRepository.delete(article);
		
	}
	
	public Article findOne(Long id) {
		return articleRepository.findOneById(id);
		
	}
	
	public Article editArticle(Long id, String name, String description, Long principalId) {
		Article article = articleRepository.findOneById(id);
		article.setName(name);
		article.setDescription(description);
		User user = userRepository.findOneById(principalId);
		article.setUser(user);
		articleRepository.save(article);
		return article;
	}
	
	public List<Article>findAll() {
		return articleRepository.findAll();
	}
}
