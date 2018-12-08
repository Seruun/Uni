
public class FalscheSpeziesException extends Exception {
	public FalscheSpeziesException(String message){
		super(message);
	}
	
	//Defaultkonstruktor
	public FalscheSpeziesException(){
		super("Falsche Art in der Gruppe");
	}
}
