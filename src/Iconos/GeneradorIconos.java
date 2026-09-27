/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Iconos;

import java.awt.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 *
 * @author chris
 */

public class GeneradorIconos {

    // --- ICONOS MENÚ IZQUIERDO (24x24) ---
    public static Icon getAvion() {
        return new Icon() {
            @Override public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                int[] xPoints = {12, 14, 14, 23, 23, 14, 14, 17, 17, 12, 7, 7, 10, 10, 1, 1, 10, 10};
                int[] yPoints = {1,  4,  9,  12, 14, 14, 19, 21, 23, 21, 23, 21, 19, 14, 14, 12, 9,  4};
                g2.translate(x, y); g2.fillPolygon(xPoints, yPoints, xPoints.length); g2.dispose();
            }
            @Override public int getIconWidth() { return 24; }
            @Override public int getIconHeight() { return 24; }
        };
    }

    public static Icon getPiloto() {
        return new Icon() {
            @Override public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE); g2.translate(x, y);
                g2.fillOval(7, 2, 10, 10); g2.fillArc(2, 13, 20, 18, 0, 180); g2.dispose();
            }
            @Override public int getIconWidth() { return 24; }
            @Override public int getIconHeight() { return 24; }
        };
    }

    public static Icon getVuelo() {
        return new Icon() {
            @Override public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE); g2.translate(x, y); g2.setStroke(new BasicStroke(2));
                g2.drawOval(2, 2, 20, 20); g2.drawOval(7, 2, 10, 20); g2.drawLine(2, 12, 22, 12); g2.dispose();
            }
            @Override public int getIconWidth() { return 24; }
            @Override public int getIconHeight() { return 24; }
        };
    }

    public static Icon getTiquete() {
        return new Icon() {
            @Override public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE); g2.translate(x, y); g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(2, 5, 20, 14, 4, 4); g2.drawLine(8, 5, 8, 19); g2.drawOval(13, 9, 4, 4); g2.dispose();
            }
            @Override public int getIconWidth() { return 24; }
            @Override public int getIconHeight() { return 24; }
        };
    }

    // ICONO PARA EL MENÚ DE LISTADO TIQUETES (Boleto con Lupa)
    public static Icon getListadoTiquetes() {
        return new Icon() {
            @Override public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE); g2.translate(x, y); g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(2, 3, 16, 12, 3, 3); g2.drawLine(7, 3, 7, 15);
                g2.drawOval(11, 10, 6, 6); g2.drawLine(16, 15, 20, 19); g2.dispose();
            }
            @Override public int getIconWidth() { return 24; }
            @Override public int getIconHeight() { return 24; }
        };
    }


    // --- ICONOS DE ACCIONES / PANTALLAS INTERNAS (16x16) ---

    // Registrar Nuevo / Comprar Tiquete (Símbolo de más '+')
    public static Icon getAccionRegistrar() {
        return new Icon() {
            @Override public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE); g2.translate(x, y); g2.setStroke(new BasicStroke(2.5f));
                g2.drawLine(8, 2, 8, 14); g2.drawLine(2, 8, 14, 8); g2.dispose();
            }
            @Override public int getIconWidth() { return 16; }
            @Override public int getIconHeight() { return 16; }
        };
    }

    // Modificar Dato (Lápiz)
    public static Icon getAccionModificar() {
        return new Icon() {
            @Override public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE); g2.translate(x, y); g2.setStroke(new BasicStroke(2));
                g2.drawRect(3, 11, 10, 3); int[] xPts = {3, 6, 13, 10}; int[] yPts = {11, 8, 2, 5};
                g2.drawPolygon(xPts, yPts, 4); g2.dispose();
            }
            @Override public int getIconWidth() { return 16; }
            @Override public int getIconHeight() { return 16; }
        };
    }

    // Limpiar Formulario (Escoba / Goma de borrar)
    public static Icon getAccionLimpiar() {
        return new Icon() {
            @Override public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE); g2.translate(x, y); g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(2, 6, 12, 8, 2, 2); g2.drawLine(2, 10, 14, 10);
                g2.fillRect(5, 2, 6, 4); g2.dispose();
            }
            @Override public int getIconWidth() { return 16; }
            @Override public int getIconHeight() { return 16; }
        };
    }

    // Cancelar Vuelo (Una 'X' de alerta)
    public static Icon getAccionCancelar() {
        return new Icon() {
            @Override public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE); g2.translate(x, y); g2.setStroke(new BasicStroke(2.5f));
                g2.drawLine(3, 3, 13, 13); g2.drawLine(13, 3, 3, 13); g2.dispose();
            }
            @Override public int getIconWidth() { return 16; }
            @Override public int getIconHeight() { return 16; }
        };
    }

    // Finalizar Vuelo (Checkmark de éxito '✓')
    public static Icon getAccionFinalizar() {
        return new Icon() {
            @Override public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE); g2.translate(x, y); g2.setStroke(new BasicStroke(2.5f));
                g2.drawLine(2, 8, 6, 12); g2.drawLine(6, 12, 14, 3); g2.dispose();
            }
            @Override public int getIconWidth() { return 16; }
            @Override public int getIconHeight() { return 16; }
        };
    }

    // Actualizar (Flechas circulares de recarga)
    public static Icon getAccionActualizar() {
        return new Icon() {
            @Override public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE); g2.translate(x, y); g2.setStroke(new BasicStroke(2));
                g2.drawArc(2, 2, 12, 12, 45, 270); g2.drawLine(11, 2, 14, 5); g2.drawLine(14, 5, 10, 8); g2.dispose();
            }
            @Override public int getIconWidth() { return 16; }
            @Override public int getIconHeight() { return 16; }
        };
    }
}


