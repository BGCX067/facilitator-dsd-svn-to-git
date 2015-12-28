package th.ac.kmutt.dsd.train.webservice.restClient;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SimpleConvertImage {
   
	public static void main(String[] args) throws IOException{

   
		 final Image image = ImageIO.read(new File("D:\\no.jpg"));
		  final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		  try {
		    final BufferedImage bufferedImage = createBufferedImageFrom(image);
		    ImageIO.write(bufferedImage, "png", baos);
		    
		    System.out.println(bufferedImage);
		  } finally {
		    
		  }

}
	
	 
	private static BufferedImage createBufferedImageFrom(final Image image) {
	  if (image instanceof BufferedImage) {
	    return (BufferedImage) image;
	  } else {
	    final BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null),
	        BufferedImage.TYPE_INT_RGB);
	    final Graphics2D g2 = bi.createGraphics();
	    g2.drawImage(image, 0, 0, null);
	    return bi;
	  }
	}
}