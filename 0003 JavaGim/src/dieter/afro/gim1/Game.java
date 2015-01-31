package dieter.afro.gim1;

import javax.swing.JFrame;

import dieter.afro.gim1.grafiks.Fenster;

public class Game implements Runnable {
	//Konstruktor
	public Game(){
		
	}
	
	
	//Deklarationen
	public static final String title="Gaim";
	public static int breite=400;//Hier steckt die groesse hinter
	public static int hoehe=breite*10/16;
	public static int skalierung=3;
	//BEHOBEN:ACHTUNG: Das ist echt traurig, aber hier wird das Fenster nur auf die richtige Groesse gesetzt, weil die vorher deklariert wurde. Steht es dahinter klappt es bescheuerterweise nicht
	public static Fenster fenster=new Fenster();//hoffentlich brauchen wir nur ein Fenster. ich will die Rendermethode nämlich im Fenster machen
	public static GameSachen sachen=new GameSachen();
	
	
	//Threadkram
	private Thread thread;
	private boolean laeuft=false;//laeuft wohl nicht
	public synchronized void start(){
		laeuft=true;//bezieht sich auf die hauptmwhile
		thread=new Thread(this);//thread bekommt wird die Referenz auf genau diese Game-Klasse
		thread.setName("Disploey");//name für den Thread...geht aus direkt bei der zuweisung mit this dahinter
		thread.start();//startet (dadurch wird die Run-Methode automatisch ausgeführt)
	}
	public synchronized void stop(){
		laeuft=false;//bezieht sich auf die hauptmwhile
		try{
			thread.join();//fuehrt die vorhandenen Threads zusammen(?)
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	//bis hierhin sind die tollen Thread Methoden, die den Main Thread verwalten

	
	//Hier fängt das an, was in der inneren Ausfuehrschleife ist
	@Override//achtet darauf, dass auch wirklich was überschrieben wird...nicht sooo nötig
	public void run() {//Das Interface Runnable setzt diese Methode vorraus
		int frames=0;int updates=0;
		long timer = System.currentTimeMillis();
		long lastTime=System.nanoTime();
		long now=System.nanoTime();
		final double ns=1000000000.0/60.0;
		double delta=0;
		while(laeuft){//solangs laeuft laeuft es
			now=System.nanoTime();
			delta+=(now-lastTime)/ns;//delta=delta + ...
			lastTime=now;
			while(delta >=1){//passiert 1 mal alle sechzigstelsekunde
				update();//läuft ne begrenzte Anzahl pro Sekunde
				updates++;//zaehlt die updates
				delta--;
			}
			fenster.render();//läuft so oft es geht (liegt in der Klasse Fenster)
			frames++;//zaehlt die rendervorgaenge
			if(System.currentTimeMillis()-timer>1000){//Zum ruecksetzen der update und frames zaehler
				timer+=1000;//timer tickt 1 sekunde weiter
				fenster.frame.setTitle(title+" FPS: "+frames+" Updates: "+updates); //dolle sache mit den angaben in der fucking leiste
				updates=0;
				frames=0;
			}
		}
	}
	
	
	public void update(){//rechnet
		
	}
	
	
	//Na endlich die Main
	public static void main(String[] args){
		//Fensterkram
		fenster.machFertig();//war nicht mit dem Konstruktor möglich, weil dann teilweise die static werte aus game noch nicht vorlagen
		fenster.frame.setTitle(title);//Hier ist der Name des Fensters
		fenster.frame.add(fenster);//weil Fenster vom Typ Canvas ist, kann man es reinpacken
		fenster.frame.pack();//jetzt wo ne Komponente drin ist, wird das Fenster mit pack an die Größe der Komponente angepasst
		fenster.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Damit Jframe weiss, was es tun soll wenns close-geklickt wird
		fenster.frame.setLocationRelativeTo(null);//Fenster in die Mitte
		fenster.machBuff();//Hier wird der Buffer erzeugt, damit man nicht bei jedem Rendervorgang abfragen muss obs den schon gibt...man bin ich clever
		
		//Gamekram
		Game game=new Game();//Damit man ne Instanz von der Gameklasse hat von der aus man ihre Methoden aufrufen kann(hätte wohl auch mit alles static geklappt)
		game.start();
	}
}
