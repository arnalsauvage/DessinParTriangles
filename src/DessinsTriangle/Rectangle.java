package DessinsTriangle;

import java.util.Arrays;

public class Rectangle {
	int x1;
	int y1;
	int x2;
	int y2;

	public Rectangle(int a, int b, int c, int d){
		x1 = a;
		y1 = b;
		x2 = c;
		y2 = d;
	}

	public Rectangle(Triangle monTriangle){
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
	public boolean pointInclus(int x, int y){
		if (((x<=x2)&&(x>=x1))&&((y<=y2)&&(y>=y1)))
			return true;
		else
			return false;
	}
}