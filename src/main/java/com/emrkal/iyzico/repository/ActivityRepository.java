package com.emrkal.iyzico.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emrkal.iyzico.enums.ToDoStatus;
import com.emrkal.iyzico.model.Activity;
import com.emrkal.iyzico.model.User;

/**
 * Activity {@link com.emrkal.iyzico.model.Activity} modeline ait "Persistence"
 * işlemlerinin yapıldığı Repository sınıftır.
 * <p>
 * 
 * @see ActivityRepository
 * @version 1.0
 * @author Emrullah KALKAN
 */

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	/**
	 * Activity {@link com.emrkal.iyzico.model.Activity} modeli üzerindeki tüm
	 * kayıtların StartDate alanına göre order yapılarak döndürülmesi sağlanır.
	 * <p>
	 * 
	 * @version 1.0
	 * @return {@link com.emrkal.iyzico.model.Activity}
	 * @author Emrullah KALKAN
	 */
	List<Activity> findAllByUserOrderByStartDateAsc(User user);

	/**
	 * Activity {@link com.emrkal.iyzico.model.Activity} modelinde statusu
	 * "DONE" olmayan ve EndDate alanı bugünün tarihinden küçük dataların
	 * döndürülmesi sağlanır.
	 * <p>
	 * 
	 * @version 1.0
	 * @return {@link com.emrkal.iyzico.model.Activity}
	 * @author Emrullah KALKAN
	 */
	@Query("SELECT a FROM Activity a WHERE a.endDate < CURRENT_DATE and a.user= :user and a.status!= com.emrkal.iyzico.enums.ToDoStatus.DONE")
	List<Activity> findAllByUserOrderByEndDateAsc(@Param("user") User user);

	/**
	 * Activity {@link com.emrkal.iyzico.model.Activity} modelindeki statusu,
	 * startdate, user bilgilerine göre göre kayıtlar döndürülür.
	 * <p>
	 * 
	 * @param startDate
	 * @param startDate
	 * @param endDate
	 * @param status
	 * @param user
	 * @version 1.0
	 * @return {@link com.emrkal.iyzico.model.Activity}
	 * @author Emrullah KALKAN
	 */
	@Query("SELECT a FROM Activity a WHERE a.endDate <= :endDate and a.startDate >= :startDate and a.status = :status and a.user = :user order by a.startDate Asc")
	List<Activity> findActivityListByStartAndEndDate(//
			@Param("startDate") Date startDate, //
			@Param("endDate") Date endDate, //
			@Param("status") ToDoStatus status, //
			@Param("user") User user);

	/**
	 * Activity {@link com.emrkal.iyzico.model.Activity} modelindeki statusu,
	 * startdate, user, activityName bilgilerine göre kayıtlar döndürülür.
	 * <p>
	 * 
	 * @param startDate
	 * @param startDate
	 * @param endDate
	 * @param activityName
	 * @param status
	 * @param user
	 * @version 1.0
	 * @return {@link com.emrkal.iyzico.model.Activity}
	 * @author Emrullah KALKAN
	 */
	@Query("SELECT a FROM Activity a WHERE a.endDate <= :endDate and a.startDate >= :startDate and  a.user = :user and a.activityName = :activityName and a.status = :status  order by a.startDate Asc")
	List<Activity> findActivityListByStartAndEndDateAndByActivityName(//
			@Param("startDate") Date startDate, //
			@Param("endDate") Date endDate, //
			@Param("activityName") String activityName, //
			@Param("status") ToDoStatus status, //
			@Param("user") User user);

	/**
	 * Id ve User parametrelerine göre Activity
	 * {@link com.emrkal.iyzico.model.Activity} bilgisi döndürülür.
	 * <p>
	 * 
	 * @param id
	 * @param user
	 * @version 1.0
	 * @return {@link com.emrkal.iyzico.model.Activity}
	 * @author Emrullah KALKAN
	 */
	Activity findByIdAndUser(Long id, User user);

	/**
	 * TodoStatus parametresine göre Activity
	 * {@link com.emrkal.iyzico.model.Activity} kayıtlarının toplam sayı bilgisi
	 * döndürülür.
	 * <p>
	 * 
	 * @param doStatus
	 * @param user
	 * @version 1.0
	 * @return {@link java.lang.Long}
	 * @author Emrullah KALKAN
	 */
	Long countByStatusAndUser(ToDoStatus doStatus, User user);

}
