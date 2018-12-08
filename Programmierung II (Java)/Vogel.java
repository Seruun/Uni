
public class Vogel {
	private int ringNummer;
	private Spezies spezies;
	
	public Vogel(int ringNummer, Spezies spezies){
		this.ringNummer = ringNummer;
		this.spezies = spezies;
	}
	
	public String toString() {
		return spezies + " Ringnummer: " + ringNummer;
		}
	
	public int getRingNummer(){
		return ringNummer;
	}
	public void setRingNummer(int ringNummer){
		this.ringNummer = ringNummer;
	}

	public Spezies getSpezies() {
		return spezies;
	}

	public void setSpezies(Spezies spezies) {
		this.spezies = spezies;
	}
	

}
