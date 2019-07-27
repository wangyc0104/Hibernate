package wyc.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色类
 * @author Yicheng Wang
 */
public class Role {
	
	private Integer id;
	private String name;
	
	/**
	 * 一对多
	 */
	private Set<User> users = new HashSet<User>();
	
	public Role() {
	}
	public Role(String name) {
		super();
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}	
