package org.example;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;


@WebServlet("/hello.png")
public class MainServlet extends HttpServlet {
    private final int ARBITARY_SIZE = 1048;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {


        createImage();

        resp.setContentType("image/png");

        try(InputStream in = req.getServletContext().getResourceAsStream("/WEB-INF/classes/hello.png");
            OutputStream out = resp.getOutputStream()) {

            byte[] buffer = new byte[ARBITARY_SIZE];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }


    }


    private static void createImage() {
        String text = "Hello World!";

        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, g, b);

        randomColor.brighter();

        BufferedImage img = new BufferedImage(640, 120, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font("Arial", Font.BOLD, 72);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(text);
        int height = fm.getHeight();
        g2d.dispose();

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setColor(randomColor.brighter());
        g2d.drawString(text, 0, fm.getAscent());
        g2d.dispose();
        try {
            File file = new File("/Users/danilaberdnikov/Desktop/f/java-2019-master/l6/target/servlets-1.0-SNAPSHOT/WEB-INF/classes/hello.png");
            ImageIO.write(img, "png", file);
            File filee = new File("/Users/danilaberdnikov/Desktop/f/java-2019-master/l6/web/WEB-INF/pdf/hello.png");
            ImageIO.write(img, "png", filee);
            System.out.println(file.getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
