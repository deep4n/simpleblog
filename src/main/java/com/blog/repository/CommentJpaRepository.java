package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype. Repository;

import com.blog.vo.Comment;

@Repository
public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
	List<Comment> findAllByPostIdOrderByRegDateDesc(Long postId);
	List<Comment> findByPostIdAndCommentContaining(Long postId, String query);
}