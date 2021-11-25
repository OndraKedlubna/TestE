package com.etnetera.hr.data;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

/**
 * Simple data entity describing basic properties of every JavaScript framework.
 * 
 * @author Etnetera
 *
 */
@Entity
public class JavaScriptFramework {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 30)
	private String name;

	@OneToMany(mappedBy="framework", fetch = FetchType.EAGER)
	@Cascade(value={org.hibernate.annotations.CascadeType.ALL})
	private Set<JavaScriptFrameworkVersion> versions;

	@Column(nullable = false, length = 30)
	private String hypeLevel;

	public JavaScriptFramework() {
	}

	public JavaScriptFramework(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<JavaScriptFrameworkVersion> getVersions() {
		return versions;
	}

	public void setVersions(Set<JavaScriptFrameworkVersion> versions) {
		this.versions = versions;
	}

	public String getHypeLevel() {
		return hypeLevel;
	}

	public void setHypeLevel(String hypeLevel) {
		this.hypeLevel = hypeLevel;
	}

	@Override
	public String toString() {
		return String.format("JavaScriptFramework [id = %d, name=%s, hypeLevel=%s]",
				id, name, hypeLevel);
	}

}
