package dieter.afro.gim1.maprelated;

import java.util.Random;

public class Map {//Hier drin werden die Pixel für den Screen bearbeitet
	public Tile[][] tiles;
	public int feldGroesse;
	public Tile badTile=new Tile(0);//das Tile wo nix drauf ist
	
	public Map(int xgroesse, int ygroesse,int feldGroesse){
		tiles=new Tile[xgroesse][ygroesse];
		this.feldGroesse=feldGroesse;
		for(int x=0;x<tiles.length;x++){
			for(int y=0;y<tiles[x].length;y++){
				tiles[x][y]=new Tile();//aus den ganzen Farben wird eine genommen uns dessen wert in den Wert geschrieben
			}
		}
	}
	
	public Tile getTile(int x,int y){
		if(x/feldGroesse<tiles.length&&y/feldGroesse<tiles[0].length&&x>=0&&y>=0){
			return tiles[x/feldGroesse][y/feldGroesse];
		}
//		tiles[x/feldGroesse][y/feldGroesse]=random.nextInt(0xffffff);
		return badTile;
	}
}
