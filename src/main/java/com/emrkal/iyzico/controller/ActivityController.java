/**
 * 
 */
package com.emrkal.iyzico.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emrkal.iyzico.datamodel.CalendarDTO;
import com.emrkal.iyzico.enums.ToDoStatus;
import com.emrkal.iyzico.model.Activity;
import com.emrkal.iyzico.model.Comment;
import com.emrkal.iyzico.service.ActivityService;
import com.emrkal.iyzico.service.CommentService;
import com.emrkal.iyzico.service.UserService;
import com.google.gson.Gson;

/**
 * Activity {@link com.emrkal.iyzico.model.Activity} modeline ait controller
 * sınıfıdır..
 * <p>
 * 
 * @see ActivityController
 * @version 1.0
 * @author Emrullah KALKAN
 */
@Controller
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);

	/**
	 * Aktivite Takvimine ait dataların çekildiği metoddur. Datalar yüklendiktn
	 * sonra {@link com.emrkal.iyzico.enums.ToDoStatus} statu durumlarına göre
	 * renk bilgileri set edilerek
	 * {@link com.emrkal.iyzico.datamodel.CalendarDTO} CalndarDTO objesine
	 * dönüştürülür.
	 * <p>
	 * 
	 * @param response
	 * @version 1.0
	 * @author Emrullah KALKAN
	 */
	@RequestMapping(value = "/calendar/setToDoCalendarData", method = RequestMethod.GET)
	public void setToDoCalendarData(HttpServletResponse response) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		List<CalendarDTO> calendarList = new ArrayList<CalendarDTO>();

		Optional.ofNullable(activityService.findAllByUserOrderByStartDateAsc(getLoggedUser()))

				.ifPresent(l -> l.forEach(item -> {

					CalendarDTO dto = new CalendarDTO();
					dto.setTitle(item.getActivityName());
					dto.setStart(item.getStartDate().toString());
					dto.setEnd(item.getEndDate().toString());
					dto.setId(item.getId());

					Optional<Activity> activityOptional = Optional.of(item);
					activityOptional.filter(x -> ToDoStatus.TODO.equals(x.getStatus())).ifPresent(x -> dto.setColor(ToDoStatus.TODO.getColour()));
					activityOptional.filter(x -> ToDoStatus.INPROGRESS.equals(x.getStatus())).ifPresent(x -> dto.setColor(ToDoStatus.INPROGRESS.getColour()));
					activityOptional.filter(x -> ToDoStatus.DONE.equals(x.getStatus())).ifPresent(x -> dto.setColor(ToDoStatus.DONE.getColour()));

					activityOptional.filter(x -> ToDoStatus.TODO.equals(x.getStatus())).ifPresent(x -> dto.setTooltip(ToDoStatus.TODO.toString()));
					activityOptional.filter(x -> ToDoStatus.INPROGRESS.equals(x.getStatus())).ifPresent(x -> dto.setTooltip(ToDoStatus.INPROGRESS.toString()));
					activityOptional.filter(x -> ToDoStatus.DONE.equals(x.getStatus())).ifPresent(x -> dto.setTooltip(ToDoStatus.DONE.toString()));

					calendarList.add(dto);
				}));

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			logger.error("Aktivite takvimi sorgulanırken hata alındı", e);
		}
		out.write(new Gson().toJson(calendarList));
	}

	/**
	 * Aktivite Takviminde hedef tarihi geçen dataların
	 * {@link com.emrkal.iyzico.enums.ToDoStatus} 'DONE'edildiği metoddur.
	 * <p>
	 * 
	 * @param id:Update
	 *            edilecek Activity {@link com.emrkal.iyzico.model.Activity}
	 *            modeline ait id bilgisi.
	 * @return Home sayfasına yönlendirilir.
	 * @version 1.0
	 * @author Emrullah KALKAN
	 */
	@RequestMapping(value = "/updateConfirmActivity/{id}", method = RequestMethod.GET)
	public String updateConfirmActivity(@PathVariable Long id) {
		com.emrkal.iyzico.model.User user = getLoggedUser();
		Optional.ofNullable(activityService.findActivityByIdAndUserAndUser(id, user)).flatMap(ob -> {
			ob.setStatus(ToDoStatus.DONE);
			activityService.saveActivity(ob, user);
			return Optional.of(ob);
		})

				.orElseGet(() -> {
					logger.error(id + " : id li Aktivite bulunamadı!");
					return null;
				});

		return "redirect:/home";
	}

	/**
	 * Oluşturulan yeni bir Activity nin
	 * {@link com.emrkal.iyzico.model.Activity} save edildiği metoddur. Formda
	 * zorunlu alanların doldurulmaması durumunda 'home' sayfasına redirect
	 * edilerek tekrardan formun render edilmesi sağlanır.
	 * <p>
	 * 
	 * Formun validasyondan geçmesi durumunda save edilmesi sağlanır.
	 * <p>
	 * 
	 * @param Activity:
	 *            Formdan doldurulan Activity
	 *            {@link com.emrkal.iyzico.model.Activity} model bilgisi
	 * @return Home sayfasına yönlendirilir.
	 * @version 1.0
	 * @author Emrullah KALKAN
	 */
	@RequestMapping(value = "/addActivity", method = RequestMethod.POST)
	public ModelAndView addActivity(@Valid @ModelAttribute("activity") Activity activity, BindingResult bindingResult, Model model) throws ParseException {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		activity.setStatus(ToDoStatus.TODO);

		if (bindingResult.hasErrors()) {
			modelMap.put("notValid", true);
			return new ModelAndView("/home", modelMap);
		}
		activityService.saveActivity(activity, getLoggedUser());

		return new ModelAndView("redirect:/home", "activity", new Activity());
	}

	/**
	 * Activity {@link com.emrkal.iyzico.model.Activity} modeli üzerinde
	 * doldurulan field lar üzerinden search yapılması sağlanır.
	 * <p>
	 * 
	 * @param Activity:
	 *            Formdan doldurulan Activity
	 *            {@link com.emrkal.iyzico.model.Activity} model bilgisi
	 * @return Home sayfasına yönlendirilir.
	 * @version 1.0
	 * @author Emrullah KALKAN
	 */
	@RequestMapping(value = "/searchActivity", method = RequestMethod.POST)
	@ResponseBody
	public String searchActivity(String searchActivityName, Date searchStartDate, Date searchEndDate, ToDoStatus searchStatus) {
		List<Activity> activitiList = new ArrayList<Activity>();
		activitiList = activityService.findActivityListByStartAndEndDateAndByActivityName(searchStartDate, searchEndDate, searchActivityName, searchStatus, getLoggedUser());
		return new Gson().toJson(activitiList);
	}

	/**
	 * Id bilgisine göre Activity {@link com.emrkal.iyzico.model.Activity}
	 * sorgulanır
	 * <p>
	 * 
	 * @param id
	 * @version 1.0
	 * @author Emrullah KALKAN
	 */
	@RequestMapping(value = "/findActivityById", method = RequestMethod.POST)
	@ResponseBody
	public Activity findActivityById(Long id) {
		return activityService.findActivityByIdAndUserAndUser(id, getLoggedUser());
	}

	/**
	 * Activity {@link com.emrkal.iyzico.model.Activity} modelinin güncellendiği
	 * metoddur. Validate edildikten sonra update işlemi gerçekleşir.
	 * <p>
	 * 
	 * @param activity
	 * @version 1.0
	 * @author Emrullah KALKAN
	 */
	@RequestMapping(value = "/updateActivity", method = RequestMethod.POST)
	public ModelAndView updateActivity(@Valid @ModelAttribute("activity") Activity activity, BindingResult bindingResult) throws ParseException, CloneNotSupportedException {
		Map<String, Object> model = new HashMap<String, Object>();

		if (bindingResult.hasErrors()) {
			model.put("notValidUpdate", true);
			return new ModelAndView("/home", model);
		}
		activityService.saveActivity(activity, getLoggedUser());
		return new ModelAndView("redirect:/home", "activity", new Activity());
	}

	/**
	 * Yeni bir comment {@link com.emrkal.iyzico.model.Comment} eklenmesi
	 * durumunda fire edilen metoddur.
	 * <p>
	 * 
	 * @param Activcommentity
	 * @version 1.0
	 * @author Emrullah KALKAN
	 */
	@RequestMapping(value = "/saveComment", method = RequestMethod.POST)
	public ModelAndView saveComment(@Valid @ModelAttribute("comment") Comment comment, BindingResult bindingResult) throws ParseException {
		commentService.saveComment(comment);
		return new ModelAndView("redirect:/home", "comment", new Comment());
	}

	/**
	 * Tarihsel alanların "dd/MM/yyyy" patterne göre formatlanması sağlanır.
	 * <p>
	 * 
	 * @version 1.0
	 * @author Emrullah KALKAN
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	private com.emrkal.iyzico.model.User getLoggedUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		com.emrkal.iyzico.model.User userEnt = userService.getUserByUsername(user.getUsername());
		return userEnt;
	}
}
