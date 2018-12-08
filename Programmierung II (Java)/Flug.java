import java.util.*;
import java.lang.StringBuilder;

public class Flug {
	private String ziel;
	private FlugGruppe g;

	public Flug(FlugGruppe g, String route) {
		this.g = g;
		this.ziel = route;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		Iterator<Vogel> i = this.g.getGruppe().iterator();
		sb.append("Ziel: " + this.ziel + "\n");
		sb.append("Spezies: " + this.g.getSpezies() + "\n");
		while(i.hasNext()){
			Vogel v = i.next();
			sb.append(v.getRingNummer()+ "\n");
			
		}
		return sb.toString();
	}
	
}