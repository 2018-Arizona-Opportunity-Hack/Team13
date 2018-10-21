package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class CategorySingleton {

	private static Map<String, String> map = null;
	
	private static final String configFile = "./config_file/init_category.csv";
	
	public static Map<String,String> getInstance() {
		if(map == null) {
			synchronized (CategorySingleton.class) {
				if(map == null) {
					map = new HashMap<String,String>();
					try {
						ExtractCSV.readCategoryInit(new FileReader(new File(configFile)), map);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return map;
	}
	
	
	
}
