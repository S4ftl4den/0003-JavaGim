package dieter.afro.gim1.grafiks;

import java.awt.Canvas;//Leinwand
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import dieter.afro.gim1.Game;

public class Fenster extends Canvas {
	private static final long serialVersionUID = 1L;//Weils ne serialisierbare Klasse ist sollte man es reinschreiben sonst warnt es rum
	
	//Deklaration is alles public, weil ich nicht sicher bin ob ichs von aussen brauch. frame zB brauch ich gerne mal
	public JFrame frame;
	public int breite=Game.breite;public int hoehe=Game.hoehe;
	public BufferStrategy bs;//hier wird die BS gemacht
	public BufferedImage image=new BufferedImage(breite, hoehe ,BufferedImage.TYPE_INT_RGB);//sollte nicht von hier Breite und hoehe nehmen, weils spaeter noch sklaiert wird...wir wollen ja grad wenig aufwand haben Durch das mit dem RGB haben wir keine Helligkeit(Alpha), aber immerhin Transparenz
	public int[] pixels=((DataBufferInt)image.getRaster().getDataBuffer()).getData();//das imageobjekt wird in ein array aus integern verwandelt durch das man dann arbeiten kann. über pixels kommt man also an die pixel aus dem Buffered Image ran
	
	
//	Konstruktor
	public Fenster(){//Die machFertig() ersetzt den Konstruktor, weil sie siecherstellt, dass zu dem Zeitpunkt die entsprechenden Werte bereits existieren
		
	}
	
	public void render(){
		//Start
		Graphics g=bs.getDrawGraphics();//erstellt ne verbindung zwischen dem buffer und dem Graphics Objekt
//		g.setColor(Color.BLACK); //Der schwarze Kram wird wohl nicht mehr gebraucht
//		g.fillRect(0, 0, getWidth(), getHeight());//gibt die Werte desCanvas zurueck und das Schwarze dient uns als Hintergrund
		clear();
		
		
		for(int y=0;y<hoehe;y++){//warum in ne externe klasse auslagern? will see
			for(int x=0;x<breite;x++){
				pixels[x+y*breite]=Game.sachen.map.getTile(x, y).farbe;//(0x macht ne hexadez zahl draus)der pixel an der jeweiligen position im array wird angesprochen, weils ja ein eindimensionales array ist muss man *breite nehmen
			}
		}
		g.drawImage(image, 0, 0, getWidth(),getHeight(),null);//hier malt man mit getwidt, weil das buffered image irgendwie automatisch auf die fenstergroesse getreckt wird
		
		
		//Ende
		g.dispose();//muss gemacht werden, weil sonst mit jedem frame mehr dazukommt
		bs.show();//nächstes Bild der Buffstrat wird rausgehauen
	}
	
	
	//Rendermethoden
	public void clear(){//macht die alten Werte des pixelarrays schwarz(0)
		for(int i=0;i<pixels.length;i++){
			pixels[i]=0;
		}
	}
	
	
	//anfangsmethoden
	public void machBuff(){//macht die Buffer-related Methoden
		createBufferStrategy(3);//jetzt wird die BS hergestellt mit 3fach buffer
		bs=getBufferStrategy();//Canvas haut hier jetzt Strategy raus, die es schon hat, weil wir davon erben
	}
	
	public void machFertig(){//hier stehen die sachen für fenster drin, die man nicht unbedingt aus Game heraus ausführen muss
		Dimension groesse=new Dimension(Game.breite*Game.skalierung,Game.hoehe*Game.skalierung);//Dimension speichert ne Dimension wie zB fürn Fenster
		setPreferredSize(groesse);//bevorzugte Groesse des Fenster wird auf die Dimension gesetzt
		frame=new JFrame();
		frame.setResizable(false);//verhindert doofe Grafikbugs
		frame.setVisible(true);//macht es sichtbar
		}
}
