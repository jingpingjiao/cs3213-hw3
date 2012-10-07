package util;

import java.util.Comparator;

public class StringComparator implements Comparator<String>{
	//compare 2 strings, ignore case.
	public int compare(String str1, String str2){
		return (str1.toLowerCase().compareTo(str2.toLowerCase()));			
	}
}

