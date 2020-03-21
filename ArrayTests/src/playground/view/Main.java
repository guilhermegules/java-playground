package playground.view;

import playground.model.ArrayNumber;
import playground.model.MultiDimensionalArray;
import playground.model.StringArray;

public class Main {

	public static void main(String[] args) {
		ArrayNumber arrayNumber = new ArrayNumber();
		arrayNumber.showNumberArray();
		
		StringArray stringArray = new StringArray();
		stringArray.showStringArray();
		
		MultiDimensionalArray multiArray = new MultiDimensionalArray();
		multiArray.showMultiDimensional();
	}

}
