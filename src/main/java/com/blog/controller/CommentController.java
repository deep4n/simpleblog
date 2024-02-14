package com.blog.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.service.CommentService;
import com.blog.vo.Comment;
import com.blog.vo.Result;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class CommentController {
	@Autowired
	CommentService commentService;

	@PostMapping("/comment")
	public Object savePost(HttpServletResponse response, @RequestBody Comment commentParam) {
		Comment comment = new Comment(commentParam.getPostId(), commentParam.getUser(), commentParam.getComment()); 
		boolean isSuccess = commentService.saveComment(comment);
		
		if(isSuccess) {
			return new Result(200, "Success");
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return new Result(500, "Fail");
		}
	}
	
    @GetMapping("/comments")
    public List<Comment> getComments(@RequestParam("post_id") Long postId) {
        List<Comment> comments = commentService.getCommentList(postId);
        return comments;
    }
    
    @GetMapping("/comment")
    public Object getComment(@RequestParam("id") Long id) {
        Comment comment = commentService.getComment(id);
        if (comment != null) {
            return comment;
        } else {
            // Handle the case where the comment is not found
            return new Result(404, "Comment not found");
        }
    }
    
    @DeleteMapping("/comment")
    public Object deleteComment(HttpServletResponse response, @RequestParam("id") Long id) {
        boolean isDeleted = commentService.deleteComment(id);
        if (isDeleted) {
            return new Result(200, "Comment deleted successfully");
        } else {
            // Handle the case where the comment is not found
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new Result(404, "Comment not found");
        }
    }
}