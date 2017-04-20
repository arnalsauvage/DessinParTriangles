package DessinsTriangle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.Random;

public class Triangle {
	Point point1;
	Point point2;
	Point point3;
	Color maCouleur;

	public Triangle (int x1, int y1, int x2, int y2, int x3, int y3)
	{
		point1 = new Point(x1, y1);
		point2 = new Point(x2, y2);
		point3 = new Point(x3, y3);
		maCouleur = new Color(10,20,30);
		setColorRandom();
	}

	public Triangle (int x1, int y1, int x2, int y2){
		Rectangle monRect = new Rectangle(x1,y1, x2, y2);
		Random rand = new Random();
		point1 = new Point (monRect.x1+rand.nextInt(monRect.x2-monRect.x1), monRect.y1+rand.nextInt(monRect.y2-monRect.y1));
		point2 = new Point (monRect.x1+rand.nextInt(monRect.x2-monRect.x1), monRect.y1+rand.nextInt(monRect.y2-monRect.y1));
		point3 = new Point (monRect.x1+rand.nextInt(monRect.x2-monRect.x1), monRect.y1+rand.nextInt(monRect.y2-monRect.y1));

		setColorRandom();
	}
	
	// Constructeur par recopie
	public Triangle (Triangle autreTriangle){
		point1 = new Point(autreTriangle.point1.x, autreTriangle.point1.y);
		point2 = new Point(autreTriangle.point2.x, autreTriangle.point2.y);
		point3 = new Point(autreTriangle.point3.x, autreTriangle.point3.y);
		int r,g,b;
		r= autreTriangle.maCouleur.getRed();
		g= autreTriangle.maCouleur.getGreen();
		b= autreTriangle.maCouleur.getBlue();
		
		maCouleur = new Color(r,g,b);
	}

	public void afficheTexte(){
		System.out.println(" x1 : " + point1.getX() + " - y1 :" +point1.getY());
		System.out.println(" x2 : " + point2.getX() + " - y2 :" +point2.getY());
		System.out.println(" x3 : " + point3.getX() + " - y3 :" +point3.getY());
	}

	public void dessine(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		BasicStroke bs;
		bs = new BasicStroke(1,1,1,2);
		g2.setStroke(bs);
		g2.setColor(maCouleur);

		// Dessin d'une contour de triangle
		//		g2.drawLine((int)(point1.getX()), (int)point1.getY(),(int)point2.getX(), (int)point2.getY());
		//		g2.drawLine((int)(point3.getX()), (int)point3.getY(),(int)point2.getX(), (int)point2.getY());
		//		g2.drawLine((int)(point1.getX()), (int)point1.getY(),(int)point3.getX(), (int)point3.getY());

		// Définir les coordonnées de chaque point du polygone
		int[] tx = new int[3];
		int[] ty = new int[3];
		tx[0] = (int) point1.getX();  ty[0] = (int) point1.getY();
		tx[1] = (int) point2.getX();  ty[1] = (int) point2.getY();
		tx[2] = (int) point3.getX(); ty[2] = (int) point3.getY();

		g2.fillPolygon(tx, ty, 3);
	}

	public boolean pointInclus(int x, int y){

		Rectangle monRectangle = new Rectangle(this);

		// Si le point n'est pas dans le rectangle circonscrit au triangle, c'est ko
		if (monRectangle.pointInclus(x, y)==false)
			return false;

		int x1 = (int) point1.getX(), y1 = (int) point1.getY();
		int x2 = (int) point2.getX(), y2 = (int) point2.getY();
		int x3 = (int) point3.getX(), y3 = (int) point3.getY();


		// on calcule la surface du triangle abc
		double ABC = Math.abs (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
		// Si sa surface est égale à la somme des trois triangles composés
		// avec le point, le point est bien dans le triangle
		double ABP = Math.abs (x1 * (y2 - y) + x2 * (y - y1) + x * (y1 - y2));
		double APC = Math.abs (x1 * (y - y3) + x * (y3 - y1) + x3 * (y1 - y));
		double PBC = Math.abs (x * (y2 - y3) + x2 * (y3 - y) + x3 * (y - y2));

		boolean isInTriangle = ABP + APC + PBC == ABC;
		return isInTriangle;
	}

	public static void main(String[] args) {
		Triangle monTriangle = new Triangle (10,11, 20,20, 10,20);
		monTriangle.afficheTexte();
	}

	public void setColor(int r, int g, int b){
		maCouleur = new Color(r,g,b);
	}	

	public void setColorRandom(){
		int r,  g,  b;
		Random rand = new Random();
		r = rand.nextInt(255);
		g = rand.nextInt(255);
		b = rand.nextInt(255);

		maCouleur = new Color(r,g,b);
	}

	public void modifieMaCouleur(){
		modifieColor(maCouleur);
	}
	
	public void modifieColor(Color modele){
		int r,  g,  b;

		r = modifieComposante(modele.getRed());
		g = modifieComposante(modele.getGreen());
		b = modifieComposante(modele.getBlue());
		maCouleur = new Color(r,g,b);
	}

	private int modifieComposante(int valeur){
		Random rand = new Random();
		int hasard = 10 + rand.nextInt(90);

		valeur += rand.nextInt(hasard)-hasard/2;
		if (valeur<0)
			valeur = 0;
		if (valeur>255)
			valeur = 255;
		return valeur;
	}

	public listeTriangles eclateTriangle (int x, int y){
		listeTriangles laListe = new listeTriangles();

		Triangle autreTriangle;

		autreTriangle = new Triangle (x, y, point1.x, point1.y, point2.x, point2.y);
		autreTriangle.modifieColor(maCouleur);
		laListe.ajouteTriangle(autreTriangle);
		autreTriangle = new Triangle (x, y, point1.x, point1.y, point3.x, point3.y);
		autreTriangle.modifieColor(maCouleur);
		laListe.ajouteTriangle(autreTriangle);
		autreTriangle = new Triangle (x, y, point2.x, point2.y, point3.x, point3.y);
		autreTriangle.modifieColor(maCouleur);
		laListe.ajouteTriangle(autreTriangle);

		return laListe;
	}


	// Dit si le triangle est dans un coin de l'écran
	public boolean estDansUnCoin (int x1, int y1, int x2, int y2 ){
		Point monPoint;
		
		monPoint = new Point (x1,y1);
		if (estUnSommet(monPoint))
			return true;
		monPoint = new Point (x1,y2);
		if (estUnSommet(monPoint))
			return true;
		monPoint = new Point (x2,y1);
		if (estUnSommet(monPoint))
			return true;
		monPoint = new Point (x2,y2);
		if (estUnSommet(monPoint))
			return true;
		
		return false;
	}
	
	// Cette méthode dit si le point passé en paramètre est un des sommets du triangle
	public boolean estUnSommet(Point monPoint){
		return ((monPoint.equals(point1))||(monPoint.equals(point2))||(monPoint.equals(point3)));
	}

	// Cette méthode remplace un des sommets du triangle par un autre point
	public boolean remplace(Point monPoint, Point autrePoint){
		if (point1.equals(monPoint)){
			point1 = autrePoint;
			return true;
		}
		if (point2.equals(monPoint)){
			point2 = autrePoint;
			return true;
		}
		if (point3.equals(monPoint)){
			point3 = autrePoint;
			return true;
		}
		return false;
	}

}
