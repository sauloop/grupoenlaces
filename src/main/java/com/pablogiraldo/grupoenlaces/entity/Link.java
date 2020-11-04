package com.pablogiraldo.grupoenlaces.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Link implements Serializable, Comparable<Link> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String title;

	private String subtitle;

	@NotEmpty
	private String url;

	@ManyToOne
	private Category category;

	public Link() {
	}

	public Link(String title, String subtitle, String url, Category category) {

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

	public void setLink(String url) {
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
