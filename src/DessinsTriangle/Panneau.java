package DessinsTriangle;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Panneau extends JPanel implements KeyListener, ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;

	Triangle monTriangle;
	ListeTriangles laListeTriangles;
	Rectangle monCadre;
	int modeDessin = 2;

	public Panneau(Rectangle monCadre) {
		this.monCadre = monCadre;
		super.setSize(monCadre.getLargeur(), monCadre.getHauteur());

		this.setLocation(monCadre.getX1(), monCadre.getY1());

		laListeTriangles = new ListeTriangles(monCadre);

		initEcran3();

		requestFocusInWindow();
		addKeyListener(this);
		addMouseListener(this);
	}

	// initialise avec des triangles aléatoires 
	private void initEcran0(int larg, int haut) { 
		laListeTriangles = new ListeTriangles(monCadre);
		monTriangle = new Triangle(monCadre.getX1(), monCadre.getY1(),  monCadre.getX1() +monCadre.getLargeur(), monCadre.getY1() + monCadre.getHauteur());
		laListeTriangles.ajouteTriangle(monTriangle);
		monTriangle = new Triangle(monCadre.getX1(), monCadre.getY1(),  monCadre.getX1() +monCadre.getLargeur(), monCadre.getY1() + monCadre.getHauteur());
		laListeTriangles.ajouteTriangle(monTriangle);
		monTriangle = new Triangle(monCadre.getX1(), monCadre.getY1(),  monCadre.getX1() +monCadre.getLargeur(), monCadre.getY1() + monCadre.getHauteur());
		laListeTriangles.ajouteTriangle(monTriangle);
		monTriangle = new Triangle(monCadre.getX1(), monCadre.getY1(),  monCadre.getX1() +monCadre.getLargeur(), monCadre.getY1() + monCadre.getHauteur());
		laListeTriangles.ajouteTriangle(monTriangle);
		monTriangle = new Triangle(monCadre.getX1(), monCadre.getY1(),  monCadre.getX1() +monCadre.getLargeur(), monCadre.getY1() + monCadre.getHauteur());
		laListeTriangles.ajouteTriangle(monTriangle);
	}

	// Initialise avec 4 triangles partant des 4 coins et se rretrouvant au
	// centre
	void initEcran() {
		laListeTriangles = new ListeTriangles(monCadre);

		monTriangle = new Triangle(monCadre.getX1(), monCadre.getY1(),
				monCadre.getX1() +monCadre.getLargeur(),
				monCadre.getX1(), monCadre.getX1() +(monCadre.getLargeur() / 2), monCadre.getY1() +(monCadre.getHauteur()) / 2);
		laListeTriangles.ajouteTriangle(monTriangle);

		monTriangle = new Triangle(monCadre.getX1() +monCadre.getLargeur(), monCadre.getY1(), 
				monCadre.getX1() +(monCadre.getLargeur() / 2), monCadre.getY1() +(monCadre.getHauteur() / 2),
				monCadre.getX1() +monCadre.getLargeur(), monCadre.getY1() +monCadre.getHauteur());
		laListeTriangles.ajouteTriangle(monTriangle);

		monTriangle = new Triangle(monCadre.getX1(), monCadre.getY1() +monCadre.getHauteur(), 
				monCadre.getX1() +(monCadre.getLargeur() / 2), monCadre.getY1() +(monCadre.getHauteur() / 2),
				monCadre.getX1() +monCadre.getLargeur(), monCadre.getY1() +(monCadre.getHauteur()));
		laListeTriangles.ajouteTriangle(monTriangle);

		monTriangle = new Triangle(monCadre.getX1(), monCadre.getY1(), 
				monCadre.getX1() +(monCadre.getLargeur() / 2), monCadre.getY1() +(monCadre.getHauteur() / 2), 
				monCadre.getX1(), monCadre.getY1() +monCadre.getHauteur() );
		laListeTriangles.ajouteTriangle(monTriangle);
	}

	// initialise avec 6 triangles
	void initEcran2() {

		int x1, x2, x3, x4, x5, x6, x7, x8;
		int y1, y2, y3, y4, y5, y6, y7, y8;
		laListeTriangles = new ListeTriangles(monCadre);
		x1 = monCadre.getX1();
		y1 = monCadre.getY1();
		x2 = monCadre.getX1() + monCadre.getLargeur() / 2;
		y2 = monCadre.getY1();
		x3 = monCadre.getX1() + monCadre.getLargeur();
		y3 = monCadre.getY1();
		x4 = monCadre.getX1();
		y4 = monCadre.getY1() + monCadre.getHauteur() / 2;
		x5 = monCadre.getX1() + monCadre.getLargeur();
		y5 = monCadre.getY1() + monCadre.getHauteur() / 2;
		x6 = monCadre.getX1();
		y6 = monCadre.getY1() + monCadre.getHauteur();
		x7 = monCadre.getX1() + monCadre.getLargeur() / 2;
		y7 = monCadre.getY1() + monCadre.getHauteur();
		x8 = monCadre.getX1() + monCadre.getLargeur();
		y8 = monCadre.getY1() + monCadre.getHauteur();

		monTriangle = new Triangle(x1, y1, x2, y2, x4, y4);
		laListeTriangles.ajouteTriangle(monTriangle);

		monTriangle = new Triangle(x2, y2, x4, y4, x5, y5);
		laListeTriangles.ajouteTriangle(monTriangle);

		monTriangle = new Triangle(x2, y2, x3, y3, x5, y5);
		laListeTriangles.ajouteTriangle(monTriangle);

		monTriangle = new Triangle(x4, y4, x5, y5, x7, y7);
		laListeTriangles.ajouteTriangle(monTriangle);

		monTriangle = new Triangle(x4, y4, x6, y6, x7, y7);
		laListeTriangles.ajouteTriangle(monTriangle);

		monTriangle = new Triangle(x5, y5, x7, y7, x8, y8);
		laListeTriangles.ajouteTriangle(monTriangle);
	}

	void initEcran3() {
		laListeTriangles = new ListeTriangles(monCadre);
		laListeTriangles = monCadre.genereListeTriangles(5, 3);
		laListeTriangles.lisseCouleurs();
	}

	public void actionPerformed(ActionEvent evt) {
		repaint();
	}

	public void paintComponent(Graphics g) {
		g.clearRect(monCadre.getX1(), monCadre.getY1(), monCadre.getX2() - monCadre.getX1(), monCadre.getY2() - monCadre.getY1());
		laListeTriangles.dessine(g, modeDessin);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F1) {
		}
		if (e.getKeyCode() == KeyEvent.VK_F2) {

		}
	}

	public void mouseReleased(MouseEvent e) {
		// int x, y;
		// x = e.getX();
		// y = e.getY();
		// // Si l'utilisateur a cliqué sur un des trianlges existants,
		// // on le met en noir
		// if (laListeTriangles.chercheTriangle(x, y)!=null){
		// laListeTriangles.chercheTriangle(x, y).setColor(0,0,0);
		// }
		// // SInon on en rajoute un
		// else
		// {
		// laListeTriangles.ajouteTriangle(new Triangle(10,10,255,255));
		// }
		//
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == 'x') {
			laListeTriangles.explosion(10);
			laListeTriangles.lisseCouleurs();
			repaint();
		}
		if (e.getKeyChar() == 'l') {
			laListeTriangles.lisseCouleurs();
			repaint();
		}
		if (e.getKeyChar() == 'r') {
			laListeTriangles.randomizeCouleurs();
			repaint();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// laListeTriangles.explosion(10);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x, y;
		x = e.getX();
		y = e.getY();
		// Si l'utilisateur a cliqué sur un des trianlges existants,
		// on le met en noir
		if (laListeTriangles.chercheTriangle(x, y) != null) {
			// laListeTriangles.chercheTriangle(x, y).setColor(0,0,0);
			laListeTriangles.ajouteTriangle(x, y);
			// Triangle monTriangle;
			// monTriangle = laListeTriangles.chercheTriangle(x, y);
			// monTriangle.modifieColor(monTriangle.maCouleur);
		}
		// SInon on en rajoute un
		else {
			laListeTriangles.ajouteTriangle(new Triangle(10, 10, 555, 555));
		}
		laListeTriangles.lisseCouleurs();
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public int getLargeur() {
		return monCadre.getLargeur();
	}

	public int getHauteur() {
		return monCadre.getHauteur();
	}

}