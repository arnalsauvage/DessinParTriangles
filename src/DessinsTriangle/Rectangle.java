package DessinsTriangle;

import java.util.Arrays;

public class Rectangle {
	private int x1;
	private int y1;
	private int x2;
	private int y2;

	// Constructeur par 4 points
	public Rectangle(int a, int b, int c, int d) {

		x1 = a;
		x2 = c;
		y1 = b;
		y2 = d;
		arrangeOrdrePoints();
	}

	// Constructeur par un triangle
	public Rectangle(Triangle monTriangle) {
		int monTab[];

		monTab = new int[3];

		monTab[0] = (int) monTriangle.point1.getX();
		monTab[1] = (int) monTriangle.point2.getX();
		monTab[2] = (int) monTriangle.point3.getX();

		Arrays.sort(monTab);

		x1 = monTab[0];
		x2 = monTab[2];

		monTab[0] = (int) monTriangle.point1.getY();
		monTab[1] = (int) monTriangle.point2.getY();
		monTab[2] = (int) monTriangle.point3.getY();

		Arrays.sort(monTab);

		y1 = monTab[0];
		y2 = monTab[2];
	}

	// Le point x,y est-il dans le rectangle ?
	public boolean pointInclus(int x, int y) {
		if (((x <= x2) && (x >= x1)) && ((y <= y2) && (y >= y1)))
			return true;
		else
			return false;
	}

	// Génère un lot de triangles dans notre rectangle avec nb en largeur
	// et nb en hauteur
	public ListeTriangles genereListeTriangles(int largeur, int hauteur) {
		ListeTriangles maListeTriangles = new ListeTriangles(this);
		int tailleCarreauLargeur = this.getLargeur() / largeur;
		int tailleCarreauHauteur = this.getHauteur() / hauteur;

		for (int lignes = 0; lignes < largeur; lignes++) {
			for (int colonnes = 0; colonnes < hauteur; colonnes++) {
				Rectangle petitRectangle = new Rectangle(x1 + lignes * tailleCarreauLargeur,
						y1 + colonnes * tailleCarreauHauteur, x1 + (lignes + 1) * tailleCarreauLargeur,
						y1 + (colonnes + 1) * tailleCarreauHauteur);
				petitRectangle.ajouteDeuxTriangle(maListeTriangles);
			}
		}
		return maListeTriangles;
	}

	// Renvoie une liste de deux triangles couvrant le rectangle courant
	private void ajouteDeuxTriangle(ListeTriangles maListeTriangles) {
		Triangle monTriangle;

		monTriangle = new Triangle(x1, y1, x2, y1, x2, y2);
		maListeTriangles.ajouteTriangle(monTriangle);
		monTriangle = new Triangle(x1, y1, x1, y2, x2, y2);
		maListeTriangles.ajouteTriangle(monTriangle);
	}

	// Renvoie la largeur
	public int getLargeur() {
		return 1 + x2 - x1;
	}

	// Renvoie la hauteur
	public int getHauteur() {
		return 1 + y2 - y1;
	}

	public int getX1() {
		return x1;
	}

	private void arrangeOrdrePoints() {
		int pivot;

		// Si besoin, on échange x1 et x2
		if (x2 < x1) {
			pivot = x1;
			x1 = x2;
			x2 = pivot;
		}

		// Si besoin, on échange x1 et x2
		if (y2 < y1) {
			pivot = y1;
			y1 = y2;
			y2 = pivot;
		}
	}

	public void setX1(int x1) {
		this.x1 = x1;
		arrangeOrdrePoints();
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
		arrangeOrdrePoints();
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
		arrangeOrdrePoints();
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
		arrangeOrdrePoints();
	}

	public boolean equals(Rectangle autRect) {
		if (x1 == autRect.x1 && x2 == autRect.x2 && y1 == autRect.y1 && y2 == autRect.y2)
			return true;
		else
			return false;

	}
}