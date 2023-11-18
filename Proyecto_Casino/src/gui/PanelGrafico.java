package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import domain.Point;

public class PanelGrafico extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private List<Point> dataPoints;

	public PanelGrafico(List<Point> dataPoints) {
		this.dataPoints = dataPoints;
	}
	
	public void setDataPoints(List<Point> dataPoints) {
        this.dataPoints = dataPoints;
    }
	public void ajustarTamaño() {
        // Ajusta el tamaño del gráfico según tus preferencias
        // Puedes establecer un tamaño fijo o calcularlo en base al tamaño del panel
        int ancho = getWidth(); // o establecer un valor específico
        int alto = getHeight(); // o establecer un valor específico
        setPreferredSize(new Dimension(ancho, alto));
        revalidate();
    }
	//chat.openai.com
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
	    int padding = 50;

	    int width = getWidth() - 2 * padding;
	    int height = getHeight() - 2 * padding;

	    int maxX = dataPoints.stream().mapToInt(Point::getX).max().orElse(0);
	    int maxY = dataPoints.stream().mapToInt(Point::getY).max().orElse(0);

	    double xScale = (double) width / maxX;
	    double yScale = (double) height / maxY;

	    // Dibujar ejes X e Y
	    g2d.drawLine(padding, getHeight() - padding, padding + width, getHeight() - padding); // eje X
	    g2d.drawLine(padding, getHeight() - padding, padding, getHeight() - padding - height); // eje Y

	    for (int i = 0; i <= maxX; i++) {
	        int x = (int) (i * xScale) + padding;
	        int y = getHeight() - padding;
	        g2d.drawLine(x, y - 5, x, y + 5);
	        g2d.drawString(Integer.toString(i), x - 5, y + 20);
	    }

	    // Marcas y etiquetas en el eje Y
	    for (Point point : dataPoints) {
	        int y = getHeight() - padding - (int) (point.getY() * yScale);
	        g2d.drawLine(padding - 5, y, padding + 5, y);
	        g2d.drawString(Integer.toString(point.getY()), padding - 30, y + 5);
	    }

	    Point prevPoint = null;
	    for (Point point : dataPoints) {
	        int x = (int) (point.getX() * xScale) + padding;
	        int y = getHeight() - padding - (int) (point.getY() * yScale);
	        g2d.fillOval(x - 3, y - 3, 6, 6);

	        if (prevPoint != null) {
	            int prevX = (int) (prevPoint.getX() * xScale) + padding;
	            int prevY = getHeight() - padding - (int) (prevPoint.getY() * yScale);
	            g2d.drawLine(prevX, prevY, x, y);
	        }

	        prevPoint = point;
		}
	}
}
