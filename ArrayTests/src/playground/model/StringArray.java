package playground.model;

public class StringArray {
	
	private String[] fruits = {"Apple","Banana","Pear","Kiwi"};
	
	public void showStringArray() {
		for(String fruit: fruits) {
			System.out.println(fruit);
		}	
	}
}

