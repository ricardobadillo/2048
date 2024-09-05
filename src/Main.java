import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class Main extends JFrame {
    private JPanel panel;
    private Toolkit miPantalla = Toolkit.getDefaultToolkit();
    private Icon aviso = new ImageIcon("assets/aviso.jpg");
    private Icon icono = new ImageIcon("assets/2048.gif");
    private Image miIcono = miPantalla.getImage("assets/2048.png");
    public static String nickname;

    public static void main(String[] args) {
        Main principal = new Main();
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        principal.setResizable(false);
        principal.setVisible(true);
    }

    public Main() {
        setTitle("2048");
        setSize(500,500);
        setIconImage(miIcono);
        setLocationRelativeTo(null);
        this.setComponents();
        this.setUndecorated(true);
    }

    private void setComponents() {
        this.setPanel();
        this.setImage();
        this.setButtons();
    }

    private void setPanel() {
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBackground(Color.WHITE);
        this.add(this.panel);
    }

    private void setImage() {
        ImageIcon imagen = new ImageIcon("assets/2048.png");
        JLabel portada = new JLabel(imagen);
        portada.setBounds(125,20,250, 250);
        this.panel.add(portada);
    }

    private void setButtons() {
        JButton buttonPlay = new JButton("Jugar");
        buttonPlay.setBackground(new Color(238,228,218));
        buttonPlay.setBounds(163,290,175,30);
        buttonPlay.setFont(new Font("Consolas", Font.PLAIN, 16));
        buttonPlay.setForeground(Color.BLACK);
        this.panel.add(buttonPlay);

        buttonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UIManager();
                UIManager.put("OptionPane.background", new ColorUIResource(Color.WHITE));
                UIManager.put("Panel.background", new ColorUIResource(Color.WHITE));
                UIManager.put("Button.background", Color.WHITE);

                do {
                    nickname = JOptionPane.showInputDialog(null, "Ingrese nombre de usuario: ", "2048", JOptionPane.INFORMATION_MESSAGE);
                } while(nickname == null || nickname.isEmpty());

                dispose();

                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new JFrame();
                    frame.setTitle("2048");
                    frame.setResizable(true);
                    Image miIcono = miPantalla.getImage("assets/2048.png");
                    frame.setIconImage(miIcono);
                    frame.add(new Juego(), BorderLayout.CENTER);
                    frame.setUndecorated(true);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                });
            }
        });

        JButton instructionsButton = new JButton("Instrucciones");
        instructionsButton.setBackground(new Color(238,228,218));
        instructionsButton.setBounds(163,340,175,30);
        instructionsButton.setFont(new Font("Consolas",Font.PLAIN,16));
        instructionsButton.setForeground(Color.BLACK);
        this.panel.add(instructionsButton);

        instructionsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new UIManager();
                UIManager.put("OptionPane.background", new ColorUIResource(Color.WHITE));
                UIManager.put("Panel.background", new ColorUIResource(Color.WHITE));
                UIManager.put("Button.background", Color.WHITE);
                JOptionPane.showMessageDialog(null, "El objetivo es combinar números hasta alcanzar el 2048.\n" +
                        "Al principio, hay dos cuadrados con un número en su interior.\n" +
                        "Para mover los cuadrados tienes que elegir una dirección\n"+
                        "(arriba, derecha, abajo o izquierda).\n"+
                        "Cuando chocan dos cuadrados idénticos se unen,\n"+
                        "formando un cuadrado con su suma:\n"+
                        "2+2=4, 4+4=8 ,..., 1024+1024 = 2048, llegando a su fin.\n" +
                        "Todas las fichas se mueven en la dirección elegida,\n"+
                        "hasta que se funden con un cuadrado que sea idéntico\n"+
                        "o bloqueado por un cuadrado diferente.\n" +
                        "El jugador pierde cuando la pizarra está llena\n"+
                        "y no puede combinar cuadrados.\n\n"+
                        "Juego creado por Ricardo Badillo.", "Instrucciones de 2048", JOptionPane.INFORMATION_MESSAGE, icono);
            }
        });

        JButton scoreButton = new JButton("Puntuaciones");
        scoreButton.setBackground(new Color(238,228,218));
        scoreButton.setBounds(163,390,175,30);
        scoreButton.setFont(new Font("Consolas", Font.PLAIN, 16));
        scoreButton.setForeground(Color.BLACK);
        this.panel.add(scoreButton);

        scoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Score score = new Score();
                score.setVisible(true);
            }
        });

        JButton quitButton = new JButton("Salir");
        quitButton.setBackground(new Color(238,228,218));
        quitButton.setBounds(163,440,175,30);
        quitButton.setFont(new java.awt.Font("Consolas", Font.PLAIN, 16));
        quitButton.setForeground(Color.BLACK);
        this.panel.add(quitButton);

        quitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new UIManager();
                UIManager.put("OptionPane.background", new ColorUIResource(Color.WHITE));
                UIManager.put("Panel.background", new ColorUIResource(Color.WHITE));
                UIManager.put("Button.background", Color.WHITE);

                if (JOptionPane.showConfirmDialog(null,  "¿Desea salir de la aplicación?",
                        "2048", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, aviso) == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                } else {
                    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }
}