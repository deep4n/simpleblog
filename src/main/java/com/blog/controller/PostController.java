package com.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.service.PostService;
import com.blog.vo.Post;

@RestController
public class PostController {
	
	@Autowired
	PostService postService;
	
//	@GetMapping("/post")
//	public Post getPost() {
//		Post post = postService.getPost();
//		return post;
//	}
	
//	@GetMapping("/posts")
//	public Post[] getPosts() {
//		Post[] posts = new Post[] {
//				new Post(1L, "Mike", "Mike's Post", "Welcome to My blog"),
//				new Post(2L, "Jason", "It's Jason", "Hi, My name is Jason")
//	};
//		return posts;
//	}
	
//	@GetMapping("/posts")
//	public List<Post> getPosts() {
//		List<Post> posts = new ArrayList<Post>();
//		
//		posts.add(new Post(1L, "Mike", "Mike's Post", "Welcome to My blog"));
//		posts.add(new Post(2L, "Jason", "It's Jason", "Hi, My name is Jason"));
//		
//		return posts;
//	}
	
	@GetMapping("/posts")
	public List<Post> getPosts() {
		List<Post> posts = postService.getPosts();
		return posts;
	}
	
	@GetMapping("/post")
		public Post getPost (@RequestParam("id") int id) { 
		Post post = postService.getPost(id);
		return post;
	}
	
	@GetMapping("/post/{id}")
	public Post getPostPathParam(@PathVariable("id") int id) { 
		Post post = postService.getPost(id);
		return post;
	}

}

