package DessinsTriangle;

import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class BarreOutils extends JPanel implements KeyListener, ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;

	int outilSelectionne = 1;
	int nbOutils = 4;
	int tailleOutil = 16;
	int larg, haut;
	int x, y;
	Button bReinit;
	Button beXplose;
	Button bRandomizeCouleurs;
	Button bLisseCouleurs;
	ButtonGroup groupe;
	Panneau monPanneau;
	JRadioButton bouton1;
	JRadioButton bouton2;
	JRadioButton bouton3;
	JComboBox modeDessinList;

	public BarreOutils(Panneau monPanneau) {
		haut = 32;
		larg = 800;
		super.setSize(larg, haut);
		this.monPanneau = monPanneau;

		x = 1;
		y = 1;
		super.setLocation(x, y);

		addKeyListener(this);
		addMouseListener(this);
		// addActionListener (this);

		bReinit = new Button("init");
		this.add(bReinit);
		bReinit.addActionListener(this);

		groupe = new ButtonGroup();
		bouton1 = new JRadioButton("init 1");
		groupe.add(bouton1);
		this.add(bouton1);
		bouton2 = new JRadioButton("init 2");
		groupe.add(bouton2);
		this.add(bouton2);
		bouton3 = new JRadioButton("init 3");
		groupe.add(bouton3);
		this.add(bouton3);

		beXplose = new Button("eXplose");
		beXplose.addActionListener(this);
		this.add(beXplose);
	
		bRandomizeCouleurs = new Button("randomize couleurs");
		bRandomizeCouleurs.addActionListener(this);
		this.add(bRandomizeCouleurs);
		
		bLisseCouleurs = new Button("lisse couleurs");
		bLisseCouleurs.addActionListener(this);
		this.add(bLisseCouleurs);
		
		String[] modeDessinStrings = { "Fil de fer", "Plein", "Dégradé" };

		// Create the combo box, select item at index 4.
		// Indices start at 0, so 4 specifies the pig.
		modeDessinList = new JComboBox(modeDessinStrings);
		modeDessinList.setSelectedIndex(2);
		modeDessinList.addActionListener(this);
		this.add(modeDessinList);
	}

	public void actionPerformed(ActionEvent evt) {

		if (evt.getSource() == bReinit) {
			if (bouton1.isSelected())
				monPanneau.initEcran();
			if (bouton2.isSelected()) {
				monPanneau.initEcran2();
			}
			if (bouton3.isSelected()) {
				monPanneau.initEcran3();
			}
		}
		if (evt.getSource() == modeDessinList) {
			monPanneau.modeDessin = modeDessinList.getSelectedIndex();
		}
		
		if (evt.getSource() == beXplose){
			monPanneau.laListeTriangles.explosion(15);
		}
		
		if (evt.getSource() == bRandomizeCouleurs){
			monPanneau.laListeTriangles.randomizeCouleurs();
		}
		if (evt.getSource() == bLisseCouleurs){
			monPanneau.laListeTriangles.lisseCouleurs();
		}
		monPanneau.repaint();
		monPanneau.requestFocus();
	}

	public void paintComponent(Graphics g) {

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
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// int x, y;
		// x = e.getX();
		// y = e.getY();
		// Si l'utilisateur a cliqué sur un des trianlges existants,
		// on le met en noir

		// SInon on en rajoute un

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
