package DessinsTriangle;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Panneau
extends JPanel
implements KeyListener, ActionListener, MouseListener{
	private static final long serialVersionUID = 1L;

	Triangle monTriangle;
	listeTriangles laListeTriangles;

	public Panneau(){
		int larg, haut;
		larg = 1024;
		haut = 768;

		laListeTriangles = new listeTriangles();

		initEcran2(larg, haut);

		requestFocusInWindow();	
		addKeyListener(this);
		addMouseListener( this);
	}

// initialise avec des triangles aléatoires
	private void initEcran0(int larg, int haut) {
		monTriangle = new Triangle (10,10,larg,haut);
		laListeTriangles.ajouteTriangle(monTriangle);
		monTriangle = new Triangle (10,10,larg,haut);
		laListeTriangles.ajouteTriangle(monTriangle);
		monTriangle = new Triangle (10,10,larg,haut);
		laListeTriangles.ajouteTriangle(monTriangle);
		monTriangle = new Triangle (10,10,larg,haut);
		laListeTriangles.ajouteTriangle(monTriangle);
		monTriangle = new Triangle (10,10,larg,haut);
		laListeTriangles.ajouteTriangle(monTriangle);
	}	

	// Initialise avec 4 triangles partant des 4 coins et se rretrouvant au centre
	private void initEcran(int larg, int haut) {
		monTriangle = new Triangle (1, 1, larg, 1, larg/2, haut/2);
		laListeTriangles.ajouteTriangle(monTriangle);

		monTriangle = new Triangle (larg, 1,larg/2, haut/2, larg, haut);
		laListeTriangles.ajouteTriangle(monTriangle);

		monTriangle = new Triangle (1, haut,larg/2, haut/2, larg, haut);
		laListeTriangles.ajouteTriangle(monTriangle);

		monTriangle = new Triangle (1, 1,larg/2, haut/2, 1, haut);
		laListeTriangles.ajouteTriangle(monTriangle);
	}
	
	// initialise avec 6 triangles
	private void initEcran2(int larg, int haut) {

		int x1, x2, x3,x4,x5,x6,x7,x8;
		int y1, y2, y3, y4, y5, y6, y7, y8;

		x1 = 1; y1 = 1;
		x2 = larg/2; y2 = 1;
		x3= larg; y3 = 1;

		x4 = 1; y4 = haut /2;
		x5 = larg; y5 =haut/2;

		x6 = 1; y6 = haut;
		x7 = larg/2; y7 = haut;
		x8= larg; y8 = haut;


		monTriangle = new Triangle (x1, y1, x2, y2, x4, y4);
		laListeTriangles.ajouteTriangle(monTriangle);

		monTriangle = new Triangle (x2, y2, x4, y4, x5, y5);
		laListeTriangles.ajouteTriangle(monTriangle);

		monTriangle = new Triangle (x2, y2, x3, y3, x5, y5);
		laListeTriangles.ajouteTriangle(monTriangle);

		monTriangle = new Triangle (x4, y4, x5, y5, x7, y7);
		laListeTriangles.ajouteTriangle(monTriangle);

		monTriangle = new Triangle (x4, y4, x6, y6, x7, y7);
		laListeTriangles.ajouteTriangle(monTriangle);

		monTriangle = new Triangle (x5, y5, x7, y7, x8, y8);
		laListeTriangles.ajouteTriangle(monTriangle);


	}

	public void actionPerformed (ActionEvent evt)
	{
		repaint();
	}

	public void paintComponent(Graphics g){
		laListeTriangles.dessine(g);
	}

	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_F1)
		{
		}
		if(e.getKeyCode()==KeyEvent.VK_F2)
		{

		}
	}

	public void mouseReleased(MouseEvent e) {
		//        int x, y;
		//    	x = e.getX();
		//    	y = e.getY();
		//    	// Si l'utilisateur a cliqué sur un des trianlges existants,
		//    	// on le met en noir
		//    	if (laListeTriangles.chercheTriangle(x, y)!=null){
		//    		laListeTriangles.chercheTriangle(x, y).setColor(0,0,0);
		//    	}
		//    	// SInon on en rajoute un
		//    	else
		//    	{
		//    		laListeTriangles.ajouteTriangle(new Triangle(10,10,255,255));
		//    	}
		//    	
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x, y;
		x = e.getX();
		y = e.getY();
		// Si l'utilisateur a cliqué sur un des trianlges existants,
		// on le met en noir
		if (laListeTriangles.chercheTriangle(x, y)!=null){
			//	    		laListeTriangles.chercheTriangle(x, y).setColor(0,0,0);
			laListeTriangles.ajouteTriangle(x, y);
			//	    		Triangle monTriangle;
			//	    		monTriangle = laListeTriangles.chercheTriangle(x, y);
			//	    		monTriangle.modifieColor(monTriangle.maCouleur);
		}
		// SInon on en rajoute un
		else
		{
			laListeTriangles.ajouteTriangle(new Triangle(10,10,555,555));
		}
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
}