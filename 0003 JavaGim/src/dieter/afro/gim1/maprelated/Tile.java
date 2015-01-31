package dieter.afro.gim1.maprelated;

import java.util.Random;

public class Tile {
	public int farbe;
	private Random random=new Random();
	
	
	public Tile(){
		farbe=random.nextInt(0xffffff);//aus den ganzen Farben wird eine genommen uns dessen wert in den Wert geschrieben
	}
	
	public Tile(int farb){
		farbe=farb;//aus den ganzen Farben wird eine genommen uns dessen wert in den Wert geschrieben
	}
}
