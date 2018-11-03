package online_store_group_project;

public class Category implements Comparable<Category> {

	private String Name; 
	
	//Constructor 
	public Category(String name) {
		this.Name = name; 
	}
	
	//Setters and Getters 
	public void setName(String name) {
		this.Name = name; 
	}
	
	public String getName() {
		return Name; 
	}
	
	//Over-riding compare method to sort Categories alphabetically by name
	public int compareTo(Category cg1) {
	
		return this.getName().compareTo(cg1.getName());
	}	
	
}
