package wyc.pojo;

/**
 * 角色类
 * @author Yicheng Wang
 */
public class Role {
	
	private Integer id;
	private String name;

	public Role() { }

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
	
}
