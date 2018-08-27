package com.example.nevena.internship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nevena.internship.controller.dto.ArticleDTO;
import com.example.nevena.internship.domain.Article;
import com.example.nevena.internship.service.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@PostMapping("")
	public Article createArticle(@RequestBody ArticleDTO articleDto ) {
		return articleService.createArticle(articleDto.getName(), articleDto.getDescription(), articleDto.getUserId());
	}
	
	@DeleteMapping("/{id}")
	public void deleteArticle(@PathVariable ("id") Long id) {
		articleService.deleteArticle(id);
	}
	
	@GetMapping("/{id}")
	public Article findOne(@PathVariable ("id") Long id) {
		return articleService.findOne(id);
	}
	
	@PostMapping("/{id}")
	public Article editArticle(@PathVariable ("id") Long id, @RequestBody ArticleDTO articleDto) {
		return articleService.editArticle(id, articleDto.getName(), articleDto.getDescription(), articleDto.getUserId());
	}
	
	@GetMapping("") 
	public List<Article>findAll() {
		return articleService.findAll();
	}
	
}
