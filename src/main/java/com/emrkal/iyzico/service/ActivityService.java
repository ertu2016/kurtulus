package com.emrkal.iyzico.service;

import java.util.Date;
import java.util.List;

import com.emrkal.iyzico.enums.ToDoStatus;
import com.emrkal.iyzico.model.Activity;
import com.emrkal.iyzico.model.User;

/**
 * Activity {@link com.emrkal.iyzico.model.Activity} modeline ait interface
 * sınıftır.
 * <p>
 * 
 * @see ActivityService
 * @version 1.0
 * @author Emrullah KALKAN
 */

public interface ActivityService {

	/**
	 * Activity {@link com.emrkal.iyzico.model.Activity} modelinin kaydedildiği
	 * metoddur.
	 * <p>
	 * 
	 * @param activity
	 * @param user
	 * @version 1.0
	 * @return {@link com.emrkal.iyzico.model.Activity}
	 * @author Emrullah KALKAN
	 */
	Activity saveActivity(Activity activity, User user);

	/**
	 * Activity {@link com.emrkal.iyzico.model.Activity} modelinde statusu
	 * "DONE" olmayan ve EndDate alanı bugünün tarihinden küçük dataların
	 * döndürülmesi sağlanır.
	 * <p>
	 * 
	 * @param user
	 * @version 1.0
	 * @return {@link com.emrkal.iyzico.model.Activity}
	 * @author Emrullah KALKAN
	 */
	List<Activity> findAllByUserOrderByEndDateAsc(User user);

	/**
	 * Id bilgisine göre Activity {@link com.emrkal.iyzico.model.Activity}
	 * sorgulanır.
	 * <p>
	 * 
	 * @param id
	 * @version 1.0
	 * @author Emrullah KALKAN
	 */
	Activity findActivityByIdAndUserAndUser(Long id, User user);

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
	List<Activity> findActivityListByStartAndEndDateAndByActivityName(Date startDate, Date endDate, String activityName, ToDoStatus status, User user);

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
	List<Activity> findActivityListByStartAndEndDate(Date startDate, Date endDate, ToDoStatus status, User user);

	/**
	 * Activity {@link com.emrkal.iyzico.model.Activity} modeli üzerindeki tüm
	 * kayıtların StartDate alanına göre order yapılarak döndürülmesi sağlanır.
	 * <p>
	 * 
	 * @param user
	 * @version 1.0
	 * @return {@link com.emrkal.iyzico.model.Activity}
	 * @author Emrullah KALKAN
	 */
	List<Activity> findAllByUserOrderByStartDateAsc(User user);

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
	Long CountByStatus(ToDoStatus doStatus, User user);

}
