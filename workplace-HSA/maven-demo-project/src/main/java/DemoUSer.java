import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DemoUSer {

	public static void main(String[] args) {

String string = "aaaabbbccd";
		
		String[] arrstr = string.split("");
		
		Map<String, Integer> hMap = new HashMap<>();
		for (String word : arrstr) {
			if (hMap.get(word) == null) {
				hMap.put(word, 1);
			}else {
				hMap.put(word, hMap.get(word)+1);
			}
		}
		

		Map<Integer, String> map = new TreeMap<>();
		
		//convert map to treemap
		hMap.forEach((e1,e2)->map.put(e2, e1));
		
		System.out.println(map);
		
		//find higest/lowest occuerence 
				java.util.Map.Entry<Integer, String> entry = map.entrySet()
				.stream()
			.sorted((e1,e2)-> -e1.getKey().compareTo(e2.getKey()))
				.skip(0)
				.findFirst().get();
		
		//create new arraylist to store result element
		List<String> l2= new ArrayList<>(); 
		
		for (String string2 : arrstr) {
			l2.add(string2);
		}
		
Set<String> set = l2.stream()
		.filter(e->Collections.frequency(l2, e)==entry.getKey())
			.collect(Collectors.toSet());
			
		System.out.println(set);

		
		
		
		
		
		
		
		
		
		

	}
}
















class Kid {
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
