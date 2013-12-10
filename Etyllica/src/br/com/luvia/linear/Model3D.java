package br.com.luvia.linear;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgl.GL;
import org.jgl.GLAUX;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import br.com.etyllica.core.loader.ImageLoader;
import br.com.luvia.GLDrawable;
import br.com.luvia.material.Material;
import br.com.luvia.material.Texture;
import br.com.luvia.vbo.Face;
import br.com.luvia.vbo.Group;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Model3D extends Polygon3D implements GLDrawable{

	private Set<Integer> vertexSelection = new HashSet<Integer>();

	public List<Vector3f> vertexes = new ArrayList<Vector3f>();
	public List<Vector3f> normals = new ArrayList<Vector3f>();
	public List<Vector2f> textures = new ArrayList<Vector2f>();

	public List<Face> faces = new ArrayList<Face>();

	private List<Group> groups = new ArrayList<Group>();

	private Map<String, Material> materials = new HashMap<String, Material>();

	private boolean drawTexture = true;
	private boolean drawFaces = true;

	//TODO Just for test, Remove!
	public Integer specialVertex = 0;

	public Model3D() {
		super(0,0,0);
	}

	public Map<String, Material> getMaterials() {
		return materials;
	}

	public void setMaterials(Map<String, Material> materials) {
		this.materials = materials;
	}

	public List<Vector3f> getVertexes() {
		return vertexes;
	}

	public void setVertexes(List<Vector3f> vertexes) {
		this.vertexes = vertexes;
	}

	@Override
	public void draw(GLAUX gl) {

		gl.glPushMatrix();

		gl.glColor3i(color.getRed(), color.getGreen(), color.getBlue());
		//gl.glScaled(2, 2, 2);

		//gl.glTranslated(x, y, z);
		gl.glRotated(anguloY, 0, 1, 0);
		gl.glRotated(anguloZ, 0, 0, 1);
		//gl.glBegin(GL.GL_QUADS);

		if(drawFaces){

			for(Group group: groups){

				String map = group.getMaterial().getMap_d();
				//map = "";

				if(!map.isEmpty()){

					//System.out.println("Trying to load: "+map);

					if(drawTexture){
						Texture texture = new Texture(ImageLoader.getInstance().getImage(map,true));

						setTexture(gl, texture);
						gl.glEnable (GL.GL_DEPTH_TEST);
						gl.glEnable (GL.GL_TEXTURE_2D);
						gl.glEnable (GL.GL_CULL_FACE);

					}

					//gl.glBegin(GL.GL_QUADS);

					float offsetX = 1f;
					float offsetZ = -3f;
					float size = 1.0f;

					/*gl.glTexCoord2f(0.0f, 1.0f);
				gl.glVertex3f(-offsetX-size, 0, +size+offsetZ);
				gl.glTexCoord2f(1.0f, 1.0f);
				gl.glVertex3f(-offsetX+size, 0.8f, +size+offsetZ);
				gl.glTexCoord2f(1.0f, 0.0f);
				gl.glVertex3f(-offsetX+size, 0.6f, +offsetZ);
				gl.glTexCoord2f(0.0f, 0.0f);
				gl.glVertex3f(-offsetX-size, 0, +offsetZ);*/


					//gl.glEnd();
				}

				for(Face face: group.getFaces()){

					if(face.vertex.length==3){

						gl.glBegin(GL.GL_TRIANGLES);

					}else{ //TODO Transform all faces in tris

						gl.glBegin(GL.GL_QUADS);

					}

					for(int i=0;i<face.vertex.length;i++){

						if(drawTexture){
							gl.glNormal3d(face.normal[i].getX(), face.normal[i].getY(), face.normal[i].getZ());
							gl.glTexCoord2d(face.texture[i].getX(), face.texture[i].getY());
						}
						gl.glVertex3d(face.vertex[i].getX(), face.vertex[i].getY(), face.vertex[i].getZ());
					}

					gl.glEnd();

				}
			}
		}

		for(int i=0;i<vertexes.size(); i++){

			double vsize = 0.005;

			if(vertexSelection.contains(i)){
				gl.glColor3i(0xff,0xff,0xff);
			}else{
				//gl.glColor3i(0xdd,0x88,0x55);
				gl.glColor3i(0x66,0x44,0x44);
			}

			//TODO Remove
			if(i==specialVertex){
				gl.glColor3i(0xff,0xff,0x00);
				vsize*=2;
			}

			gl.glPushMatrix();
			gl.glTranslated(vertexes.get(i).getX(), vertexes.get(i).getY(), vertexes.get(i).getZ());
			gl.auxSolidCube(vsize);
			gl.glPopMatrix();
		}

		//gl.glEnd();

		gl.glPopMatrix();

	}

	protected void setTexture(GL gl, Texture texture){

		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, texture.getW(), texture.getH(), 0,
				GL.GL_RGB, GL.GL_UNSIGNED_BYTE, texture.getBytes());

		//gl.glTexGeni(GL.GL_S, GL.GL_TEXTURE_GEN_MODE, GL.GL_OBJECT_LINEAR);

		//int sgenIparams[] = {1, 1, 1, 0};

		//gl.glTexGeniv(GL.GL_S, GL.GL_OBJECT_PLANE, sgenIparams);

	}

	protected void setAlphaTexture(GL gl, Texture texture){


		//gl.glEnable(GL.GL_ALPHA_TEST);

		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, texture.getW(), texture.getH(), 0,
				GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, texture.getAlphaBytes());

		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

	}

	public Set<Integer> getVertexSelection() {
		return vertexSelection;
	}

	public void setVertexSelection(Set<Integer> vertexSelection) {
		this.vertexSelection = vertexSelection;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}


	public boolean isDrawTexture() {
		return drawTexture;
	}

	public void setDrawTexture(boolean drawTexture) {
		this.drawTexture = drawTexture;
	}

	public boolean isDrawFaces() {
		return drawFaces;
	}

	public void setDrawFaces(boolean drawFaces) {
		this.drawFaces = drawFaces;
	}

}