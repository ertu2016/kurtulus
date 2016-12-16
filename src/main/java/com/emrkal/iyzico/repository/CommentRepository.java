package com.emrkal.iyzico.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emrkal.iyzico.model.Comment;
import com.emrkal.iyzico.model.User;

/**
 * Activity {@link com.emrkal.iyzico.model.Comment} modeline ait "Persistence"
 * işlemlerinin yapıldığı Repository sınıftır.
 * <p>
 * 
 * @see CommentRepository
 * @version 1.0
 * @author Emrullah KALKAN
 */

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

	/**
	 * User parametresine göre {@link com.emrkal.iyzico.model.User} tüm yorumlar
	 * döndürülür.
	 * <p>
	 * 
	 * @param user
	 * @version 1.0
	 * @return {@link com.emrkal.iyzico.model.Comment}
	 * @author Emrullah KALKAN
	 */
	List<Comment> findAllByUserOrderByCommentDateDesc(User user);
}
