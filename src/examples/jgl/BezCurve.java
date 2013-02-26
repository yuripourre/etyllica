package examples.jgl;

import br.com.etyllica.core.video.Grafico;
import jgl.GL;

public class BezCurve extends GL{

	public BezCurve() {
		super(640,480);
	}

	public void load(){
		myinit ();

		loading = 100;
	}
	private static final float ctrlpoints [][] = {
		{-4.0f, -4.0f, 0.0f},
		{-2.0f,  4.0f, 0.0f},
		{ 2.0f, -4.0f, 0.0f},
		{ 4.0f,  4.0f, 0.0f}};

	private void myinit() {
		
		glClearColor (0.0f, 0.0f, 0.0f, 1.0f);
		glMap1f (GL_MAP1_VERTEX_3, 0.0f, 1.0f, 3, 4, ctrlpoints);
		glEnable (GL_MAP1_VERTEX_3);
		glShadeModel (GL_FLAT);
		
		myReshape (w, h);
	}

	public void draw(Grafico g) {
		int i;

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glColor3f(1.0f, 1.0f, 1.0f);
		
		glBegin(GL_LINE_STRIP);
		
		for(i = 0; i <= 30; i++){
			
			glEvalCoord1f ((float)i/30.0f);
			
		}
		glEnd();
		/* The following code displays the control points as dots. */
		glPointSize(5.0f);
		glColor3f(1.0f, 1.0f, 0.0f);
		glBegin(GL_POINTS);
		
		for (i = 0; i < 4; i++) {
			glVertex3fv(ctrlpoints[i]);
		}
		
		glEnd();
		
		glFlush(g);
	}

	private void myReshape (int w, int h) {
				
		glViewport (0, 0, w, h);
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity ();
		if (w <= h) {
			glOrtho (-5.0f, 5.0f,
					-5.0f *(float)h/(float)w,
					5.0f *(float)h/(float)w,
					-5.0f, 5.0f);
		} else {
			glOrtho (-5.0f *(float)w/(float)h,
					5.0f *(float)w/(float)h,
					-5.0f, 5.0f,
					-5.0f, 5.0f);
		}
		
		glMatrixMode (GL_MODELVIEW);
		glLoadIdentity ();
	}

}
