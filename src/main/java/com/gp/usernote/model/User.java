package com.gp.usernote.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * Class : User
 * Purpose : This is Entity Class User
 * @author dhiren
 *
 */

@Entity
@Table(name = "USER")
public class User implements Serializable, IBaseModel {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;

	@NotNull
	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;

	@NotNull
	@Column(name = "PASSWORD", nullable = false)
	@Min(8)
	private String password;

	@Column(name = "CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createTime;

	@Column(name = "UPDATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updateTime;

	@OneToMany(mappedBy = "note")
	private List<Note> notes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
}
