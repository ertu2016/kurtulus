package com.emrkal.iyzico.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.emrkal.iyzico.enums.ToDoStatus;
import com.emrkal.iyzico.model.Activity;
import com.emrkal.iyzico.model.User;
import com.emrkal.iyzico.repository.ActivityRepository;

/**
 * Activity {@link com.emrkal.iyzico.model.Activity} modeline ait servis imp.
 * sınıftır.
 * <p>
 * 
 * @see ActivityService
 * @version 1.0
 * @author Emrullah KALKAN
 */
@Service("activityService")
public class ActivityServiceImp implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public Activity saveActivity(Activity activity, User user) {
		activity.setUser(user);
		return activityRepository.save(activity);

	}

	@Override
	public List<Activity> findAllByUserOrderByStartDateAsc(User user) {
		return activityRepository.findAllByUserOrderByStartDateAsc(user);
	}

	@Override
	public List<Activity> findAllByUserOrderByEndDateAsc(User user) {
		return activityRepository.findAllByUserOrderByEndDateAsc(user);

	}

	@Override
	public Activity findActivityByIdAndUserAndUser(Long id, User user) {
		return activityRepository.findByIdAndUser(id, user);
	}

	@Override
	public List<Activity> findActivityListByStartAndEndDate(Date startDate, Date endDate, ToDoStatus status, User user) {
		List<Activity> activitiList = activityRepository.findActivityListByStartAndEndDate(startDate, endDate, status, user);
		return activitiList;

	}

	@Override
	public List<Activity> findActivityListByStartAndEndDateAndByActivityName(Date startDate, Date endDate, String activityName, ToDoStatus status, User user) {
		if (StringUtils.isEmpty(activityName)) {
			return activityRepository.findActivityListByStartAndEndDate(startDate, endDate, status, user);
		}
		return activityRepository.findActivityListByStartAndEndDateAndByActivityName(startDate, endDate, activityName, status, user);

	}

	@Override
	public Long CountByStatus(ToDoStatus doStatus, User user) {
		return activityRepository.countByStatusAndUser(doStatus, user);
	}

}
