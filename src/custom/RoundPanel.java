package custom;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import swing.shadow.ShadowRenderer;

public class RoundPanel extends JPanel {

    private BufferedImage imageShadow;
    private Color shadowColor = new Color(170, 170, 170);
    private int shadowSize = 5; // Tamaño de la sombra

    public RoundPanel() {
        setLayout(null);  // Establecer layout a null
        setOpaque(false);  // Para que el fondo sea transparente
        setBackground(Color.WHITE);  // Fondo blanco del panel
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);  // Llamar al método padre

        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar sombra
        if (imageShadow == null) {
            createShadowImage();
        }
        g2.drawImage(imageShadow, 0, 0, null);

        // Dibujar el panel con fondo blanco por encima de la sombra
        g2.setColor(getBackground());
        g2.fillRoundRect(shadowSize, shadowSize, getWidth() - shadowSize * 2, getHeight() - shadowSize * 2, 20, 20);

        g2.dispose();
    }

    private void createShadowImage() {
        int width = getWidth();
        int height = getHeight();
        if (width > 0 && height > 0) {
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = img.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Crear el fondo redondeado para aplicar sombra
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(shadowSize, shadowSize, width - shadowSize * 2, height - shadowSize * 2, 20, 20);
            g2.dispose();
            
            // Aplicar la sombra utilizando la librería ShadowRenderer
            imageShadow = new ShadowRenderer(shadowSize, 0.3f, shadowColor).createShadow(img);
        }
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        imageShadow = null;  // Resetear sombra cuando cambian los tamaños
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);  // Establecer layout a null para el JFrame
        frame.setLocationRelativeTo(null);
        
        RoundPanel profilePanel = new RoundPanel();
        profilePanel.setBounds(50, 50, 300, 200);  // Establecer posición y tamaño del RoundPanel

        // Ejemplo de añadir texto en cualquier parte del RoundPanel
        JLabel label1 = new JLabel("jLabel1", SwingConstants.CENTER);
        label1.setForeground(Color.BLACK);
        label1.setBounds(10, 10, 280, 30);  // Ajusta la posición y el tamaño
        profilePanel.add(label1);

        frame.add(profilePanel);  // Añadir el RoundPanel al JFrame
        frame.setVisible(true);
    }
}
