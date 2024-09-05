import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class Juego extends JPanel{

enum Estados {
        iniciar, ganar, arrancar, perder
    }

public Color[] colores = {
        new Color(0x701710), new Color(0xFFE4C3), new Color(0xfff4d3),
        new Color(0xffdac3), new Color(0xe7b08e), new Color(0xe7bf8e),
        new Color(0xffc4c3), new Color(0xE7948e), new Color(0xbe7e56),
        new Color(0xbe5e56), new Color(0x9c3931), new Color(0x701710)};
 
private int objetivo = 2048, puntaje;
static int highest;
static int score;
private Color emptyColor = new Color(0xCDC1B4), startColor = new Color(0xFFEBCD),  gridColor = new Color(0xBBADA0);
private Random aleatorio = new Random();
private Cuadrado[][] cuadrado;
public static int side;
private int x;
private Estados estado_juego = Estados.iniciar;
private boolean disponibilidad;
public static int intento=0;
public static String n1 = "";
public static String n2 = ""; 
public static String n3 = "";
public static String n4 = "";
public static String n5 = "";
public static int p1 = 0;
public static int p2 = 0;
public static int p3 = 0;
public static int p4 = 0;
public static int p5 = 0;
    
public Juego() {
        setPreferredSize(new Dimension(900, 620));
        setBackground(new Color(0xFAF8EF));
        setFont(new Font("SansSerif", Font.BOLD, 48));
        setFocusable(true);
        
        JButton boton = new JButton("Salir");
        this.add(boton);
        boton.setBackground(new Color(0xFFEBCD));
        boton.setVisible(true);
        boton.setBounds(100,20,70,30);
        boton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                hide();
                Main p = new Main();
                p.setVisible(true);
            }            
        });
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String opciones[] = {"3x3","4x4"};
                Integer opc[] = {0,1};   
                do{
                x = JOptionPane.showOptionDialog(null, "Elige la dificultad: ", "2048", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opc[0]);
                } while(x == -1);
               
                UIManager UI = new UIManager();
                UI.put("OptionPane.background", new ColorUIResource(Color.WHITE));
                UI.put("Panel.background", new ColorUIResource(Color.WHITE));
                UI.put("Button.background", Color.WHITE);
                
                side = x+3;
                iniciarJuego();
                repaint();
            }
        });
 
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        moverArriba();
                        break;
                    case KeyEvent.VK_DOWN:
                        moverAbajo();
                        break;
                    case KeyEvent.VK_LEFT:
                        moverIzquierda();
                        break;
                    case KeyEvent.VK_RIGHT:
                        moverDerecha();
                        break;
                }
                repaint();
            }
        });
    }

@Override
public void paintComponent(Graphics G) {
        
        super.paintComponent(G);
        Graphics2D g = (Graphics2D) G;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        dibujarGrid(g);
    }
  
private void iniciarJuego() {
        if (estado_juego != Estados.arrancar) {
            score = 0;
            highest = 0;
            estado_juego = Estados.arrancar;
            cuadrado = new Cuadrado[side][side];
            agregarCuadrado();
            agregarCuadrado();
        }
    }

// ---------------------------------------------------------------------------- CUADRADOS ---------------------------------------------------------------------------- //

public class Cuadrado {
    private boolean combinar;
    private int valor;
 
    Cuadrado(int val) {
        valor = val;
    }
 
    int getValor() {
        return valor;
    }
 
    void setCombinar(boolean c) {
        combinar = c;
    }
 
    boolean puedeCombinarCon(Cuadrado cuadrado) {
        return !combinar && cuadrado != null && !cuadrado.combinar && valor == cuadrado.getValor();
    }
 
    int CombinarCon(Cuadrado cuadrado) {
        if (puedeCombinarCon(cuadrado)) {
            valor *= 2;
            combinar = true;
            puntaje += cuadrado.getValor();
            return valor;
        }
        return -1;
    }
}

private void dibujarGrid(Graphics2D g) {
    
        g.setColor(gridColor);
        g.fillRoundRect(200, 100, 499, 499, 15, 15);

        if (estado_juego == Estados.arrancar) {
            g.setColor(Color.LIGHT_GRAY);
            g.setFont(new Font("Consolas",1,40));
            g.drawString(""+puntaje, 30, 60);

            for (int r = 0; r < side; r++) {
                for (int c = 0; c < side; c++) {
                    if (cuadrado[r][c] == null) {
                        
                        g.setColor(emptyColor);
                        g.fillRoundRect(215 + c * 121, 115 + r * 121, 106, 106, 7, 7);
                    } else {
                        dibujarCuadrado(g, r, c);
                    }
                }
            }
        } else {
            g.setColor(startColor);
            g.fillRoundRect(215, 115, 469, 469, 7, 7);
 
            g.setColor(gridColor.darker());
            g.setFont(new Font("SansSerif", Font.BOLD, 128));
            g.drawString("2048", 310, 270);
            g.setFont(new Font("SansSerif", Font.BOLD, 20));
 
            if (estado_juego == Estados.ganar) {
                g.drawString("Felicitaciones, has ganado", 390, 350);
                puntaje=0;
 
            } else if (estado_juego == Estados.perder)
                g.drawString("Has perdido", 400, 350);
                puntaje=0;
                
            g.setColor(gridColor);
            g.drawString("Click para comenzar a jugar", 320, 470);
            g.drawString("(Usa las teclas de direcciones para jugar)", 250, 530);
        }
    }
 
private void dibujarCuadrado(Graphics2D g, int r, int c) {
        int value = cuadrado[r][c].getValor();
 
        g.setColor(colores[(int) (Math.log(value) / Math.log(2)) + 1]);
        g.fillRoundRect(215 + c * 121, 115 + r * 121, 106, 106, 7, 7);
        String s = String.valueOf(value);
 
        g.setColor(value < 128 ? colores[0] : colores[1]);
 
        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int dec = fm.getDescent();
 
        int x = 215 + c * 121 + (106 - fm.stringWidth(s)) / 2;
        int y = 115 + r * 121 + (asc + (106 - (asc + dec)) / 2);
 
        g.drawString(s, x, y);
    }
 
private void agregarCuadrado() {
        int posicion = aleatorio.nextInt(side * side);
        int row, col;
        do {
            posicion = (posicion + 1) % (side * side);
            row = posicion / side;
            col = posicion % side;
        } while (cuadrado[row][col] != null);
 
        int val = aleatorio.nextInt(10) == 0 ? 4 : 2;
        cuadrado[row][col] = new Cuadrado(val);
    }
 
private void limpiarCuadrado() {
        for (Cuadrado[] row : cuadrado)
            for (Cuadrado cuadrado : row)
                if (cuadrado != null)
                    cuadrado.setCombinar(false);
    }
 
// ---------------------------------------------------------------------------- MOVIMIENTOS ---------------------------------------------------------------------------- //

private boolean movimientosDisponibles() {
        disponibilidad = true;
        boolean hayMov = moverArriba() || moverAbajo() || moverIzquierda() || moverDerecha();
        disponibilidad = false;
        return hayMov;
    }
 
private boolean mover(int contador, int y, int x) {
        boolean moved = false;
 
        for (int i = 0; i < side * side; i++) {
            int j = Math.abs(contador - i);
 
            int r = j / side;
            int c = j % side;
 
            if (cuadrado[r][c] == null)
                continue;
 
            int nextR = r + y;
            int nextC = c + x;
 
            while (nextR >= 0 && nextR < side && nextC >= 0 && nextC < side) {
 
                Cuadrado next = cuadrado[nextR][nextC];
                Cuadrado curr = cuadrado[r][c];
 
                if (next == null) {
 
                    if (disponibilidad)
                        return true;
 
                    cuadrado[nextR][nextC] = curr;
                    cuadrado[r][c] = null;
                    r = nextR;
                    c = nextC;
                    nextR += y;
                    nextC += x;
                    moved = true;
 
                } else if (next.puedeCombinarCon(curr)) {
 
                    if (disponibilidad)
                        return true;
 
                    int value = next.CombinarCon(curr);
                    if (value > highest)
                        highest = value;
                    score += value;
                    cuadrado[r][c] = null;
                    moved = true;
                    break;
                } else
                    break;
            }
        }
        
        if (moved) {
            if (highest < objetivo) {
                limpiarCuadrado();
                agregarCuadrado();
                if (!movimientosDisponibles()) {
                   intento+=1;
                   if (intento == 1){
                        n1 = Main.nickname;
                        p1 = puntaje;
                        estado_juego = Estados.perder;
                    }
                   
                   if (intento == 2){
                       n2 = Main.nickname;
                       p2 = puntaje;
                       estado_juego = Estados.perder;
                   }
                   
                   if (intento == 3){
                       n3 = Main.nickname;
                       p3 = puntaje;
                       estado_juego = Estados.perder;
                   }
                   
                   if (intento == 4){
                       n4 = Main.nickname;
                       p4 = puntaje;
                       estado_juego = Estados.perder;
                   }
                   
                   if (intento == 5){
                       n5 = Main.nickname;
                       p5 = puntaje;
                       estado_juego = Estados.perder;
                   }
                    
                }
            } else if (highest == objetivo)
                estado_juego = Estados.ganar;
        }
 
        return moved;
    }

    private boolean moverArriba() {
        return mover(0, -1, 0);
    }
 
    private boolean moverAbajo() {
        return mover(side * side - 1, 1, 0);
    }
 
    private boolean moverIzquierda() {
        return mover(0, 0, -1);
    }
 
    private boolean moverDerecha() {
            return mover(side * side - 1, 0, 1);
    }
}