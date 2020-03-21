package playground.model;

public class ArrayNumber {
	
	private int [] values = new int[3];
	private int value = 0;
	
	public void showNumberArray() {
		
		for(int i = 0; i < values.length; i++) {
			value += 10;
			values[i] = value;
		}
		
		for(int i = 0; i < values.length; i++) {
			System.out.println(values[i]);
		}
	}
}
