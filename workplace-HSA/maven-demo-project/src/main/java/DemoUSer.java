import java.util.ArrayList;
import java.util.List;

public class DemoUSer {

	public static void main(String[] args) {
	
		List< Kid>kids = new ArrayList<>();
		
		kids.add(new Kid("a", 10));
		kids.add(new Kid("b", 12));
		kids.add(new Kid("c", 14));
		kids.add(new Kid("d", 16));
		
		boolean isMatched = kids.stream().allMatch(e->e.getAge()<16);
		
		System.out.println(isMatched);
		
		
		
		
		
		
	}
}

class Kid{
	private String kidName;
	private Integer age;

	public Kid(String kidName, Integer age) {
		super();
		this.kidName = kidName;
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getKidName() {
		return kidName;
	}

	public void setKidName(String kidName) {
		this.kidName = kidName;
	}

}
