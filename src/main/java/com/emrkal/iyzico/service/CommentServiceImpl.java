package com.emrkal.iyzico.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.emrkal.iyzico.model.Comment;
import com.emrkal.iyzico.repository.CommentRepository;

/**
 * Comment {@link com.emrkal.iyzico.model.Comment} modeline ait servis imp.
 * sınıftır.
 * <p>
 * 
 * @see CommentService
 * @version 1.0
 * @author Emrullah KALKAN
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserService userService;

	@Override
	public Comment saveComment(Comment comment) {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		com.emrkal.iyzico.model.User userEnt = userService.getUserByUsername(user.getUsername());
		comment.setActive(true);
		comment.setCommentDate(new Date());
		comment.setUser(userEnt);
		return commentRepository.save(comment);
	}

	@Override
	public List<Comment> getAllComment() {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		com.emrkal.iyzico.model.User userEnt = userService.getUserByUsername(user.getUsername());
		return commentRepository.findAllByUserOrderByCommentDateDesc(userEnt);
	}

}
