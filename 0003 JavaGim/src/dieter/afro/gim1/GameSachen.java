package dieter.afro.gim1;

import dieter.afro.gim1.maprelated.Map;

public class GameSachen {//Hier kommen die Sachen rein die game bräuchte, aber game hat ja keine Instanzen
	public Map map;
	public GameSachen(){
		map=new Map(64,64,4);
	}
}
