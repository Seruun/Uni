import java.util.ArrayList;

public class FlugGruppe{
	private String spezies;
	private ArrayList<Vogel> gruppe;

	public FlugGruppe(String name) {
		this.spezies = name;
		gruppe = new ArrayList<Vogel>();
	}

	public void addVogel(Vogel v) throws FalscheSpeziesException{
		if (spezies.equals(v.getSpezies()))
			gruppe.add(v);
		else
			throw new FalscheSpeziesException();
	}

	public boolean delVogel(Vogel v) {
		return gruppe.remove(v);
	}

	public String getSpezies() {
		return spezies;
	}

	public void setSpezies(String art) {
		this.spezies = art;
	}

	public ArrayList<Vogel> getGruppe() {
		return gruppe;
	}
}