package br.com.etyllica.loader;

import java.net.MalformedURLException;
import java.net.URL;

import br.com.etyllica.util.io.IOHelper;

/**
 * 
 * @author yuripourre
 *
 */

public class LoaderImpl implements Loader {

	protected URL url;
	
	//protected String folder = "http://www.etyllica.com.br/imagens/";
	protected String folder;
	
	public void setUrl(String s) {
		try {
			url = new URL(s);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	} 

	public URL getUrl() {
		return url;
	}
	
	public URL getFullURL(String filename) throws MalformedURLException {
		return new URL(url, folder+filename);
	}
	
	public String getPath() {
		
		String path = url.toString();
		path = path.substring(IOHelper.FILE_PREFIX.length());
		path = path.replaceAll("%20", " ");
		
		return path;
	}
	
	@Override
	public void initLoader() {
		// TODO Auto-generated method stub
		
	}
	
	public String fullPath(String path) {
		return fullPath(path, false);
	}
	
	public String fullPath(String path, boolean absolute) {

		StringBuilder sb = new StringBuilder();

		if(!absolute) {
			sb.append(url.getPath());
			sb.append(folder);
		}

		sb.append(path);

		return sb.toString();
	}
	
}
