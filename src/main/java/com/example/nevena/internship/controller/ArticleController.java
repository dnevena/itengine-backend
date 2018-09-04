package com.example.nevena.internship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.example.nevena.internship.controller.dto.ArticleDTO;
import com.example.nevena.internship.domain.Article;
import com.example.nevena.internship.service.ArticleService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@PostMapping
	@PreAuthorize("hasAuthority('USER')")
	public Article createArticle(@RequestBody ArticleDTO articleDto, @ApiIgnore @AuthenticationPrincipal Long principalId) {
		return articleService.createArticle(articleDto.getName(), articleDto.getDescription(), principalId);
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@DeleteMapping("/{id}")
	public void deleteArticle(@PathVariable ("id") Long id) {
		articleService.deleteArticle(id);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public Article findOne(@PathVariable ("id") Long id) {
		return articleService.findOne(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public Article editArticle(@PathVariable ("id") Long id, @RequestBody ArticleDTO articleDto,  @ApiIgnore @AuthenticationPrincipal Long principalId) {
		return articleService.editArticle(id, articleDto.getName(), articleDto.getDescription(), principalId);
	}
	
	@GetMapping
	public List<Article>findAll() {
		return articleService.findAll();
	}
	
}
