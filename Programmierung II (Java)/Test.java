
public class Test {

	public static void main(String[] args) throws FalscheSpeziesException {
		
		FlugGruppe schwalben = new FlugGruppe("Schwalbe");
//		FlugGruppe wildgaense = new FlugGruppe("WILDGANS");
		
//		Spezies Schwalbe = new Spezies("Schwalbe");
		
//		Vogel v1 = new Vogel(5, Spezies.SCHWALBE);
//		Vogel v2 = new Vogel(6, Spezies.SCHWALBE);
//		Vogel v3 = new Vogel(7, Spezies.WILDGANS);
		Vogel v4 = new Vogel(8, Spezies.SCHWALBE);
		
		
		System.out.println(v4.toString());

		try{
//		schwalben.addVogel(v1);
//		schwalben.addVogel(v2);
//		schwalben.addVogel(v3);
		schwalben.addVogel(v4);
		} catch(FalscheSpeziesException e){
			//e.printStackTrace();
		}
		
		Flug f = new Flug(schwalben, "BrandenburgBerlin");
		System.out.println(f.toString());

	}

}
