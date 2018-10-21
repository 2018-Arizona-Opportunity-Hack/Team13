package models;

import java.util.HashSet;
import java.util.Set;

public class CategoryResponse extends Response {

	public CategoryResponse(int code, String msg) {
		super(code, msg);
	}
	private static final long serialVersionUID = 1359557830144998713L;

	Set<String> categoryList = new HashSet<>();

	public Set<String> getCategoryList() {
		return categoryList;
	}

	public void addCategoryList(Set<String> categoryList) {
		this.categoryList.addAll(categoryList);
	}

	
}
