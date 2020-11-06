package com.pablogiraldo.grupoenlaces.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "links")
public class Link implements Serializable, Comparable<Link> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	@Length(min = 5)
	@NotEmpty
	private String title;

	@Column(nullable = true)
	private String subtitle;

	@Column(nullable = false, unique = true)
	@NotEmpty
	private String url;

	@NotEmpty
	@ManyToOne
	private Category category;

	public Link() {
	}

	public Link(@Length(min = 5) @NotEmpty String title, String subtitle, @NotEmpty String url,
			@NotEmpty Category category) {
		this.title = title;
		this.subtitle = subtitle;
		this.url = url;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int compareTo(Link comparelink) {
		Long compareids = ((Link) comparelink).getId();
		return (int) (compareids - this.id);
	}

	@Override
	public String toString() {
		return "Link [id=" + id + ", title=" + title + ", subtitle=" + subtitle + ", url=" + url + ", category="
				+ category + "]";
	}
}
