package DessinsTriangle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
//import java.util.Random;
import java.util.Random;

public class ListeTriangles {
	Rectangle monCadre;
	ArrayList<Triangle> mesTriangles;

	// Constructeur par défaut
	public ListeTriangles(Rectangle monCadre) {
		mesTriangles = new ArrayList<Triangle>();
		this.monCadre = monCadre;
	}

	// Ajouter un triangle à la liste
	public void ajouteTriangle(Triangle monTriangle) {
		mesTriangles.add(monTriangle);
	}

	// Dessiner la liste de triangles
	public void dessine(Graphics g, int modeDessin) {
		for (Triangle unTriangle : mesTriangles) {
			unTriangle.dessine(g, modeDessin);
		}
		// Affiche le nombre de triangles
		Font font = new Font("Serif", Font.PLAIN, 50);
		Font font24 = font.deriveFont(18.0f);
		g.setFont(font24);
		g.setColor(Color.black);
		g.drawString(mesTriangles.size() + " triangles", 20, 50);

	}

	// Renvoie le premier triangle de la liste contenant le point x,y
	public Triangle chercheTriangle(int x, int y) {
		for (Triangle unTriangle : mesTriangles) {
			if (unTriangle.pointInclus(x, y))
				return (unTriangle);
		}
		return null;
	}

	// Ajoute un triangle avec un point supplémentaire en x,y
	public void ajouteTriangle(int x, int y) {
		for (Triangle unTriangle : mesTriangles) {
			if (unTriangle.pointInclus(x, y)) {
				Point monPoint = unTriangle.barycentre();
				x = monPoint.x;
				y = monPoint.y;
				eclateTriangle(x, y, unTriangle);
				return;
			}
		}
	}

	// ajoute à la liste courante les triangles d'une autre liste
	public void fusionne(ListeTriangles autreListe) {
		for (Triangle unTriangle : autreListe.mesTriangles) {
			this.mesTriangles.add(unTriangle);
		}
	}

	// Renvoie la liste des triangles ayant 2 sommets sur les trois proposés
	private ListeTriangles chercheTrianglesVoisins(Triangle leTriangle) {
		int sommets;
		ListeTriangles autreListe = new ListeTriangles(monCadre);

		for (Triangle unTriangle : mesTriangles) {
			sommets = 0;
			if (unTriangle.estUnSommet(leTriangle.point1))
				sommets++;
			if (unTriangle.estUnSommet(leTriangle.point2))
				sommets++;
			if (unTriangle.estUnSommet(leTriangle.point3))
				sommets++;
			if (sommets == 2) {
				autreListe.ajouteTriangle(unTriangle);
				if (autreListe.mesTriangles.size() == 3)
					return autreListe;
			}
		}
		return autreListe;
	}

	// Soir le triangle ABC
	// pour chaque segment BC : s'il n'y a pas de triangle voisin, on fait un
	// segment xBC
	// s'il ya un triangle voisin ABC, on fait deux triangles ABx et ACx ,
	// enfin, on supprime ABC
	public void eclateTriangle(int x, int y, Triangle triangle) {

		Point nouveauPoint = new Point(x, y);
		Triangle triangleA, triangleB, triangleC;

		// On va extraire les trois segments du triangle
		Rectangle seg1 = new Rectangle(triangle.point1.x, triangle.point1.y, triangle.point2.x, triangle.point2.y);
		Rectangle seg2 = new Rectangle(triangle.point1.x, triangle.point1.y, triangle.point3.x, triangle.point3.y);
		Rectangle seg3 = new Rectangle(triangle.point3.x, triangle.point3.y, triangle.point2.x, triangle.point2.y);

		// On va chercher les 3 triangles voisins possibles
		ListeTriangles trianglesAgerer = chercheTrianglesVoisins(triangle);
		Triangle voisinSeg1 = null;
		Triangle voisinSeg2 = null;
		Triangle voisinSeg3 = null;

		// On affecte les triangles à leur variable
		for (Triangle unTriangle : trianglesAgerer.mesTriangles) {
			if (unTriangle.contientDeuxSommets(seg3))
				voisinSeg3 = unTriangle;
			if (unTriangle.contientDeuxSommets(seg2))
				voisinSeg2 = unTriangle;
			if (unTriangle.contientDeuxSommets(seg1))
				voisinSeg1 = unTriangle;
		}

		Triangle nouveauTriangle;

		// Si le segment1 est collé à un autre triangle ABD, on crée DAx et DBx
		if (voisinSeg1 != null){
			ajouteTrianglesPointVsSegment(seg1, nouveauPoint, voisinSeg1);
			mesTriangles.remove(voisinSeg1);
		}
		else {
			nouveauTriangle = new Triangle(seg1.getX1(), seg1.getY1(), seg1.getX2(), seg1.getY2(), x, y);
			ajouteTriangle(nouveauTriangle);
		}

		// Si le segment2 est collé à un autre triangle BCE, on crée EBx et ECx
		if (voisinSeg2 != null){
			ajouteTrianglesPointVsSegment(seg2, nouveauPoint, voisinSeg2);
			mesTriangles.remove(voisinSeg2);
		}
		else {
			nouveauTriangle = new Triangle(seg2.getX1(), seg2.getY1(), seg2.getX2(), seg2.getY2(), x, y);
			ajouteTriangle(nouveauTriangle);
		}
		// Si le segment3 est collé à un autre triangle ACF, on crée FAx et FCx
		if (voisinSeg3 != null){
			ajouteTrianglesPointVsSegment(seg3, nouveauPoint, voisinSeg3);
			mesTriangles.remove(voisinSeg3);
		}
		else {
			nouveauTriangle = new Triangle(seg3.getX1(), seg3.getY1(), seg3.getX2(), seg3.getY2(), x, y);
			ajouteTriangle(nouveauTriangle);
		}
		// On peut maintenant supprimer le triangle éclaté
		mesTriangles.remove(triangle);
	}

	// ajoute les deux triangles obtenus en supprimant segmentBC et ajoutant
	// nouveau point x au triangle ABC ... ABC ==> ABx et ACx
	private void ajouteTrianglesPointVsSegment(Rectangle segment, Point nouveauPoint, Triangle voisinSeg1) {
		Triangle triangleA = new Triangle(voisinSeg1);
		triangleA.modifieMaCouleur();
		Triangle triangleB = new Triangle(voisinSeg1);
		triangleB.modifieMaCouleur();
		Triangle triangleC = new Triangle(voisinSeg1);
		triangleC.modifieMaCouleur();

		// On crée les triangles ABx ACx et BCx, et on les ajoute
		// si AB est le segment, on n'ajoute pas ABx
		if (triangleA.remplace(voisinSeg1.point1, nouveauPoint)) {
			if (triangleA.contientDeuxSommets(segment) == false)
				ajouteTriangle(triangleA);
		}
		if (triangleB.remplace(voisinSeg1.point2, nouveauPoint))
			if (triangleB.contientDeuxSommets(segment) == false)
				ajouteTriangle(triangleB);

		if (triangleC.remplace(voisinSeg1.point3, nouveauPoint))
			if (triangleC.contientDeuxSommets(segment) == false)
				ajouteTriangle(triangleC);
	}

	public Color couleurMoyenne() {
		int r = 0, g = 0, b = 0;
		int compteur;

		compteur = this.mesTriangles.size();
		for (Triangle unTriangle : mesTriangles) {
			r += unTriangle.maCouleur.getRed();
			g += unTriangle.maCouleur.getGreen();
			b += unTriangle.maCouleur.getBlue();
		}
		if (compteur > 0) {
			r /= compteur;
			g /= compteur;
			b /= compteur;
		}
		return new Color(r, g, b);
	}

	public Color couleurMoyenne(Color c1, Color c2) {
		int r = 0, g = 0, b = 0;

		r += c1.getRed() + c2.getRed();
		g += c1.getGreen() + c2.getGreen();
		b += c1.getBlue() + c2.getBlue();

		r /= 2;
		g /= 2;
		b /= 2;

		return new Color(r, g, b);
	}

	public void lisseCouleurs() {
		ListeTriangles mesVoisins;
		for (Triangle unTriangle : mesTriangles) {
			mesVoisins = chercheTrianglesVoisins(unTriangle);
			Color nlleCouleur = mesVoisins.couleurMoyenne();
			nlleCouleur = couleurMoyenne(nlleCouleur, unTriangle.maCouleur);
			nlleCouleur = couleurMoyenne(nlleCouleur, unTriangle.maCouleur);
			unTriangle.setColor(nlleCouleur);
		}
	}
	
	public void randomizeCouleurs() {
		ListeTriangles mesVoisins;
		for (Triangle unTriangle : mesTriangles) {
			mesVoisins = chercheTrianglesVoisins(unTriangle);
			Color nlleCouleur = mesVoisins.couleurMoyenne();
			nlleCouleur = couleurMoyenne(nlleCouleur, unTriangle.maCouleur);
			unTriangle.setColorRandom();
		}
	}
	public Rectangle getMonCadre() {
		return (monCadre);
	}

	public boolean estUnSommet(Point monPoint) {

		for (Triangle unTriangle : mesTriangles) {
			if (unTriangle.estUnSommet(monPoint))
				return true;
		}
		return false;
	}

	public void explosion(int nombre) {
		Random hasard = new Random();
		for (int index = 0; index != nombre; index++) {
			int x = hasard.nextInt(monCadre.getLargeur());
			int y = hasard.nextInt(monCadre.getHauteur());
			Point monPoint = new Point(x, y);
			if (estUnSommet(monPoint))
				index--;
			else
				ajouteTriangle(x, y);
		}
	}

}