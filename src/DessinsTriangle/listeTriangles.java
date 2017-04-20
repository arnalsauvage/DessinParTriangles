package DessinsTriangle;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class listeTriangles {
	ArrayList<Triangle> mesTriangles;

	public listeTriangles(){
		mesTriangles = new ArrayList<Triangle>();
	}

	public void ajouteTriangle(Triangle monTriangle)
	{
		mesTriangles.add(monTriangle);
	}

	public void dessine(Graphics g){
		for (Triangle unTriangle : mesTriangles)
		{
			unTriangle.dessine(g);
		}
	}

	// Renvoie le premier triangle de la liste contenant le point x,y
	public Triangle chercheTriangle(int x, int y){
		for (Triangle unTriangle : mesTriangles)
		{
			if (unTriangle.pointInclus(x, y))
				return (unTriangle);
		}
		return null;
	}

	public void ajouteTriangle(int x, int y){
		listeTriangles autreListe = null;
		for (Triangle unTriangle : mesTriangles)
		{
			if (unTriangle.pointInclus(x, y))
			{
//				autreListe = unTriangle.eclateTriangle(x, y);
//				mesTriangles.remove(unTriangle);
//				fusionne(autreListe);
//				
				eclateTriangleBis(x, y);
				return;
			}
		}
	}

	public void fusionne (listeTriangles autreListe){
		for (Triangle unTriangle : autreListe.mesTriangles)
		{
			this.mesTriangles.add(unTriangle);
		}
	}

	// Renvoie la liste des triangles ayant 2 sommets sur les trois propos�s
	private listeTriangles chercheTriangles (Triangle leTriangle){
		int sommets;
		listeTriangles autreListe = new listeTriangles();

		for (Triangle unTriangle : mesTriangles)
		{
			sommets = 0;
			if (unTriangle.estUnSommet(leTriangle.point1))
				sommets ++ ;
			if (unTriangle.estUnSommet(leTriangle.point2))
				sommets ++ ;
			if (unTriangle.estUnSommet(leTriangle.point3))
				sommets ++ ;
			if (sommets==2)
			{
				autreListe.ajouteTriangle(unTriangle);
			}
		}
		return autreListe;
	}

	// trouve le triangle contenant x, y et l'�clate en le supprimant, et recomposant 6 nouveaux triangles
	// � partir de ses trois triangles voisins 
	public void eclateTriangleBis (int x, int y){

		Triangle triangleA, triangleB, triangleC;
		
		// On cherche le triangle ABC contenant le pointx pour le supprimer
		Triangle autreTriangle = chercheTriangle(x, y);

		// On construit la liste des triangles contenant deux sommets communs
		listeTriangles trianglesAgerer = chercheTriangles(autreTriangle);

		Point nouveauPoint = new Point(x,y);

		for (Triangle unTriangle : trianglesAgerer.mesTriangles)
		{
			// On copie le triangle 3 fois
			triangleA = new Triangle (unTriangle); triangleA.modifieMaCouleur();
			triangleB = new Triangle (unTriangle); triangleB.modifieMaCouleur();
			triangleC = new Triangle (unTriangle); triangleC.modifieMaCouleur();

			// On cherche le triangleABd, avant de le supprimer, on fait deux triangles : AdX, BdX
			if (triangleA.remplace(autreTriangle.point1, nouveauPoint))
				ajouteTriangle(triangleA);
			if (triangleB.remplace(autreTriangle.point2, nouveauPoint))
				ajouteTriangle(triangleB);
			if (triangleC.remplace(autreTriangle.point3, nouveauPoint))
				ajouteTriangle(triangleC);

			// On supprime ce triangle qui a �t� scind� en 2
			mesTriangles.remove(unTriangle);

		}
		
		
		// Si ABC a un sommet A dans un coin, il faut cr�er les triangles ABx et ACx
		if (autreTriangle.estDansUnCoin(1, 1, 1024, 768)){
			
			triangleA = new Triangle (autreTriangle); triangleA.modifieMaCouleur();
			triangleB = new Triangle (autreTriangle); triangleB.modifieMaCouleur();
			triangleC = new Triangle (autreTriangle); triangleC.modifieMaCouleur();
			
			if (triangleA.remplace(autreTriangle.point1, nouveauPoint))
				ajouteTriangle(triangleA);
			if (triangleB.remplace(autreTriangle.point2, nouveauPoint))
				ajouteTriangle(triangleB);
			if (triangleC.remplace(autreTriangle.point3, nouveauPoint))
				ajouteTriangle(triangleC);
			// TODO : ne pas cr�er le triangle BCx
		}
		
		
		// On peut maintenant supprimer le triangle �clat�
		mesTriangles.remove(autreTriangle);
	}


}