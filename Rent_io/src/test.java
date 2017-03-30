import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class test {
    public static void main(String[] args) throws IOException {
        JFrame test = new JFrame("Google Maps");

        try {
        	//swap longtitude and latitude based on input files, swap zoom, draw point based on locations (do the math based on current long/la)
            String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=43.259605,-79.9259135&zoom=14&size=612x612&scale=3&maptype=roadmap";
            String destinationFile = "image.jpg";
            String str = destinationFile;
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(destinationFile);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        test.add(new JLabel(new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(630, 600,   java.awt.Image.SCALE_SMOOTH))));

        test.setVisible(true);
        test.pack();

    }
}