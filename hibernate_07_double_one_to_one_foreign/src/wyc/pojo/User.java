package wyc.pojo;

/**
 * 用户
 * @author Yicheng Wang
 */
public class User {

	private int id;
	private String name;
	private int age;
	
	/**
	 * 双向一对一
	 */
	private IdCard idCard;

	public User() {
	}

	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public IdCard getIdCard() {
		return idCard;
	}

	public void setIdCard(IdCard idCard) {
		this.idCard = idCard;
	}

}
