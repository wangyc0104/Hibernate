package wyc.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * 功能
 * @author Yicheng Wang
 */
public class Func {
	
	private int id;
	private String name;
	private String url;
	private Set<Role> roles = new HashSet<Role>();

	public Func() {
	}

	public Func(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
