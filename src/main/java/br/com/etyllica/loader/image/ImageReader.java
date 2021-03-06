package br.com.etyllica.loader.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public interface ImageReader {
    BufferedImage loadImage(URL url) throws IOException;

    BufferedImage loadImage(InputStream input) throws IOException;
}
