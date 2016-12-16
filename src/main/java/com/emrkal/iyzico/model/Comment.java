package com.emrkal.iyzico.model;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.function.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Comment Entity.
 * <p>
 * 
 * @see Comment
 * @version 1.0
 * @author Emrullah KALKAN
 */

@Entity
@Table(name = "comment")
public class Comment implements Serializable {

	private static final long serialVersionUID = 5263115637581174774L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "description", nullable = false)
	@NotEmpty(message = "Açıklama")
	private String description;

	@Column(name = "commentDate", nullable = false, updatable = true)
	@Type(type = "date")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date commentDate;

	@Column(name = "active", nullable = false)
	private boolean active;

	@JoinColumn(name = "user", nullable = true, updatable = true, insertable = true)
	@ManyToOne
	private User user;

	@Transient
	private String day;

	@Transient
	private String month;

	public Comment() {
	}

	public static Comment create(final Supplier<Comment> supplier) {
		return supplier.get();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((commentDate == null) ? 0 : commentDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Comment other = (Comment) obj;
		if (active != other.active)
			return false;
		if (commentDate == null) {
			if (other.commentDate != null)
				return false;
		} else if (!commentDate.equals(other.commentDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", description=" + description + ", commentDate=" + commentDate + ", active=" + active + ", user=" + user + "]";
	}

	public String getDay() {
		String[] parts = commentDate.toString().split("-");
		this.day = parts[2];
		return day;
	}


	public String getMonth() {
		String[] parts = commentDate.toString().split("-");
		this.month = new DateFormatSymbols().getMonths()[Integer.parseInt(parts[1]) - 1];
		return month;
	}


}
