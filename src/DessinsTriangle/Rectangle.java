package DessinsTriangle;

import java.util.Arrays;

public class Rectangle {
	int x1;
	int y1;
	int x2;
	int y2;

	public Rectangle(int a, int b, int c, int d) {
		if (x1 < x2) {
			x1 = a;
			x2 = c;
		} else {
			x1 = c;
			x2 = a;
		}

		if (y1 < y2) {
			y1 = b;
			y2 = d;
		} else {
			y2 = b;
			y1 = d;
		}
	}

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

	public boolean pointInclus(int x, int y) {
		if (((x <= x2) && (x >= x1)) && ((y <= y2) && (y >= y1)))
			return true;
		else
			return false;
	}

	public ListeTriangles genereListeTriangles(int largeur, int hauteur) {
		ListeTriangles maListeTriangles = new ListeTriangles();
		
		int tailleCarreauLargeur = this.largeur() / largeur;
		int tailleCarreauHauteur = this.hauteur() / hauteur;
		
		for (int lignes = 0; lignes < largeur; lignes++) {
			for (int colonnes = 0; colonnes < hauteur; colonnes++) {
				Rectangle petitRectangle = new Rectangle (x1 + lignes * tailleCarreauLargeur,
						y1 + colonnes * tailleCarreauHauteur,
						x1 + (lignes+1) * tailleCarreauLargeur,
						y1 + (colonnes+1) * tailleCarreauHauteur );
				petitRectangle.ajouteDeuxTriangle(maListeTriangles);
			}

		}

		return maListeTriangles;

	}

	private void ajouteDeuxTriangle(ListeTriangles maListeTriangles) {
		Triangle monTriangle;
		
		monTriangle = new Triangle (x1, y1, x2, y1, x2, y2);
		maListeTriangles.ajouteTriangle(monTriangle);
		monTriangle = new Triangle (x1, y1, x1, y2, x2, y2);
		maListeTriangles.ajouteTriangle(monTriangle);
	}

	public int largeur() {
		return x2 - x1;
	}

	public int hauteur() {
		return y2 - y1;
	}
}