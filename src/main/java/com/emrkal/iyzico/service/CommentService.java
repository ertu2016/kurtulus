package com.emrkal.iyzico.service;

import java.util.List;

import com.emrkal.iyzico.model.Comment;

/**
 * Comment {@link com.emrkal.iyzico.model.Comment} modeline ait interface
 * sınıftır.
 * <p>
 * 
 * @see CommentService
 * @version 1.0
 * @author Emrullah KALKAN
 */
public interface CommentService {

	/**
	 * Comment {@link com.emrkal.iyzico.model.Comment} modelinin kaydedildiği
	 * metoddur.
	 * <p>
	 * 
	 * @param comment
	 * @version 1.0
	 * @return {@link com.emrkal.iyzico.model.Comment}
	 * @author Emrullah KALKAN
	 */
	Comment saveComment(Comment comment);

	/**
	 * Tüm kayıtların {@link com.emrkal.iyzico.model.Comment} döndürüldüğü
	 * metoddur.
	 * <p>
	 * 
	 * @version 1.0
	 * @return {@link com.emrkal.iyzico.model.Comment}
	 * @author Emrullah KALKAN
	 */
	List<Comment> getAllComment();

}
