package com.emrkal.iyzico.model;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.emrkal.iyzico.enums.ToDoStatus;
import com.emrkal.iyzico.listener.ActivityListener;

/**
 * Activity Entity.
 * <p>
 * 
 * @see Activity
 * @version 1.0
 * @author Emrullah KALKAN
 */

@Entity
@EntityListeners(ActivityListener.class)
@Table(name = "activity")
public class Activity implements Cloneable, Serializable {

	private static final long serialVersionUID = 5263115637581174774L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "activityName", nullable = false)
	@NotEmpty(message = "Aktivite Adı")
	private String activityName;

	@Column(name = "startDate", nullable = false, updatable = true)
	@NotNull(message = "Başlangış Tarihi")
	@Type(type = "date")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date startDate;

	@Column(name = "endDate", nullable = false, updatable = true)
	@NotNull(message = "Bitiş Tarihi")
	@Type(type = "date")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date endDate;

	@Column(name = "status", nullable = false)
	@NotNull(message = "Statu")
	@Enumerated(EnumType.ORDINAL)
	private ToDoStatus status;

	@Column(name = "description", nullable = false)
	@NotEmpty(message = "Açıklama")
	private String description;

	@JoinColumn(name = "user", nullable = true, updatable = true, insertable = true)
	@ManyToOne
	private User user;

	public Activity() {
	}

	public Activity(String activityName, Date startDate, Date endDate, ToDoStatus status, String description, User user) {
		super();
		this.activityName = activityName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.description = description;
		this.user = user;
	}

	public static Activity create(final Supplier<Activity> supplier) {
		return supplier.get();
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ToDoStatus getStatus() {
		return status;
	}

	public void setStatus(ToDoStatus status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityName == null) ? 0 : activityName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (activityName == null) {
			if (other.activityName != null)
				return false;
		} else if (!activityName.equals(other.activityName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", activityName=" + activityName + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + ", description=" + description + "]";
	}

}
