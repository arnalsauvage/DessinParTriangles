package DessinsTriangle;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// TODO : G�rer des annulations
// TODO : G�rer l'enregistrement dans un fichier png
// TODO : G�rer le redimensionnement du Panneau avec la fen�tre
// TODO : G�rer le d�placement des points
// TODO : G�rer l'enregistrement d'un dessin dans un format de fichier
// TODO : G�rer l'affichage d'une photo en transparence
// TODO : G�rer le coloriage d'apr�s une photo

public class Fenetre extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Panneau pan;

	private BarreOutils maBarre;

	int panteste; 

	public Fenetre() {
		int panteste = 1;
		this.setTitle("Dessins triangle");
		this.setSize(1024, 768);
		Rectangle monRect = new Rectangle(50, 50, 950, 700);
		pan = new Panneau(monRect);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		if (panteste == 1) {
			this.setContentPane(pan);
			pan.paintComponent(this.getGraphics());
			addKeyListener(pan);
		}
		maBarre = new BarreOutils(pan);
		this.getContentPane().add(maBarre);
		maBarre.paintComponent(this.getGraphics());
		addKeyListener(maBarre);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fenetre fen;
		fen = new Fenetre();
		fen.isCursorSet();
	}
}