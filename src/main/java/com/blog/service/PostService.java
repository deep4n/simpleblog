package com.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.repository.PostJpaRepository;
import com.blog.repository.PostRepository;
import com.blog.vo.Post;

import io.micrometer.common.util.StringUtils;

@Service
public class PostService {
	private static List<Post> posts;
	
//	public Post getPost() {
//		Post post = new Post(1L, "Mike", "Mike's Post", "Welcome to My blog");
//		
//		return post;
//	}
	
//	public List<Post> getPosts() {
//		posts = new ArrayList<Post>();
//		posts.add(new Post(1L, "Mike", "Mike's Post", "Welcome to My blog"));
//		posts.add(new Post(2L, "Jason", "It's Jason", "Hi, My name is Jason"));
//		
//		return posts;
//	}
	
	public Post getPost(int id) {
		Post post = posts.get(id-1);
		
		return post;
	}
	
	@Autowired
	PostRepository postRepository;
//	public Post getPost(Long id) {
//		Post post = postRepository.findOne(id);
//		return post;
//	}
	
//	public List<Post> getPosts() {
//		List<Post> postList = postRepository.findPost();
//		
//		return postList;
//	}
	
//	public List<Post> getPostsOrderByUpdtAsc() {
//	    List<Post> posts = postRepository.findPostOrderByUpdtDateAsc();
//	    return posts;
//	}
	
	public List<Post> getPostsOrderByRegDesc() {
	    List<Post> posts = postRepository.findPostOrderByRegDateDesc();
	    return posts;
	}
	
//	public List<Post> searchPostByTitle(String query) {
//		List<Post> posts = postRepository.findPostLikeTitle(query);
//		return posts;
//	}
	
//	public List<Post> searchPostByContent(String query) {
//	    List<Post> posts = postRepository.findPostLikeContent(query);
//	    return posts;
//	}
	
//	public boolean savePost (Post post) {
//		int result = postRepository.savePost (post);
//		boolean isSuccess = true;
//		
//		if(result == 0) {
//			isSuccess = false;
//		}
//		return isSuccess;
//	}
	public boolean savePost(Post post) {
		Post result = jpaRepository.save(post);
		boolean isSuccess = true;
		
		if(result == null) {
			isSuccess = false;
		}
		
		return isSuccess;
	}
	
	public boolean deletePost (Long id) {
		Post result = jpaRepository.findOneById(id);
		
		if(result == null)
			return false;
		
		jpaRepository.deleteById(id);
		return true;
	}
	
	public boolean updatePost (Post post) {
		Post result = jpaRepository.findOneById(post.getId());
		
		if(result == null)
			return false;
		
		if(!StringUtils.isEmpty(post.getTitle())) {
			result.setTitle(post.getTitle());
		}
		
		if(!StringUtils.isEmpty(post.getContent())) { 
			result.setContent(post.getContent());
		}
		
		jpaRepository.save(result);
		return true;
		}
	
	@Autowired
	PostJpaRepository jpaRepository;
	public Post getPost(Long id) {
		Post post = jpaRepository.findOneById(id);
		return post;
	}
	
	public List<Post> getPosts() {
		List<Post> posts = jpaRepository.findAllByOrderByUpdtDateDesc();
		return posts;
	}
	
	public List<Post> getPostsOrderByUpdtAsc() {
	    List<Post> posts = jpaRepository.findAllByOrderByUpdtDateAsc();
	    return posts;
	}
	
	public List<Post> searchPostByTitle(String query) {
		List<Post> posts = jpaRepository.findByTitleContainingOrderByUpdtDateDesc(query);
		return posts;
	}
	
	public List<Post> searchPostByContent(String query) {
	    List<Post> posts = jpaRepository.findByContentContaining(query);
	    return posts;
	}
	
	public List<Post> searchPostByTitleCont(String query) {
	    List<Post> posts = jpaRepository.findByTitleContaining(query);
	    return posts;
	}

	
}