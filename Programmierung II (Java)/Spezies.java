
public enum Spezies {
	SCHWALBE("Schwable"), WILDGANS("Wildgans");
	
	private String name;
	
	private Spezies(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}
