import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Score extends JFrame {
     
    Toolkit miPantalla = Toolkit.getDefaultToolkit();
    Dimension sizePantalla = miPantalla.getScreenSize();
    private Image miIcono = miPantalla.getImage("2048.png");
    private JPanel panel; 
    private JLabel [] name, score;
    private JLabel nombre, pts;
    private JLabel name1, name2, name3, name4, name5;
    private JLabel pts1, pts2, pts3, pts4, pts5;
    private String n[];
    private int p[];
    
    public Score(){
        
        setTitle("Puntuaciones");
        setResizable(false);
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(miIcono);
        colocarComponentes();
        setVisible(true);
        
        n= new String[6];
        
        for (int i=1; i<6; i++){
            n[i] = "";
        }

        name = new JLabel[6];
        name[1] = name1;
        name[2] = name2; 
        name[3] = name3; 
        name[4] = name4;
        name[5] = name5;
        
        for (int i=1; i<6; i++){
            
            name[i] = new JLabel();
            name[i].setBounds(40,45*i,120,20);
            name[i].setFont(new Font("Consolas",1,14));
            panel.add(name[i]);
        }
        
        p = new int[6];
        
        for (int i=1; i<6; i++){
            p[i] = 0;
        }
        
        score = new JLabel[6];
        score[1] = pts1;
        score[2] = pts2;
        score[3] = pts3;
        score[4] = pts4;
        score[5] = pts5;

        for (int i=1; i<6; i++){
              
            score[i] =  new JLabel();
            score[i].setBounds(170,45*i,120,20);
            score[i].setFont(new Font("Consolas",1,14));
            panel.add(score[i]);
        }
        
        //270
        
        if (Juego.intento == 1){
            name[Juego.intento].setText(Juego.n1);
            score[Juego.intento].setText(String.valueOf(Juego.p1));
        }
         if (Juego.intento == 2){   
                name[Juego.intento-1].setText(Juego.n1);
                name[Juego.intento].setText(Juego.n2);
                score[Juego.intento-1].setText(String.valueOf(Juego.p1));
                score[Juego.intento].setText(String.valueOf(Juego.p2));
        }
         
         if (Juego.intento == 3){
                name[Juego.intento-2].setText(Juego.n1);
                name[Juego.intento-1].setText(Juego.n2);
                name[Juego.intento].setText(Juego.n3);
                score[Juego.intento-2].setText(String.valueOf(Juego.p1));
                score[Juego.intento-1].setText(String.valueOf(Juego.p2));
                score[Juego.intento].setText(String.valueOf(Juego.p3));
         }
         
         if (Juego.intento == 4){
                name[Juego.intento-3].setText(Juego.n1);
                name[Juego.intento-2].setText(Juego.n2);
                name[Juego.intento-1].setText(Juego.n3);
                name[Juego.intento].setText(Juego.n4);
                score[Juego.intento-3].setText(String.valueOf(Juego.p1));
                score[Juego.intento-2].setText(String.valueOf(Juego.p2));
                score[Juego.intento-1].setText(String.valueOf(Juego.p3));
                score[Juego.intento].setText(String.valueOf(Juego.p4));
         }
         
         if (Juego.intento == 5){
                name[Juego.intento-4].setText(Juego.n1);
                name[Juego.intento-3].setText(Juego.n2);
                name[Juego.intento-2].setText(Juego.n3);
                name[Juego.intento-1].setText(Juego.n4);
                name[Juego.intento].setText(Juego.n5);
                score[Juego.intento-4].setText(String.valueOf(Juego.p1));
                score[Juego.intento-3].setText(String.valueOf(Juego.p2));
                score[Juego.intento-2].setText(String.valueOf(Juego.p3));
                score[Juego.intento-1].setText(String.valueOf(Juego.p4));
                score[Juego.intento].setText(String.valueOf(Juego.p5));
         }
    }
    
    private void colocarComponentes(){
        
        colocarPanel();
        colocarEtiquetas();
    }
    
    private void colocarPanel(){
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        this.add(panel);
    }
    
    private void colocarEtiquetas(){
        
        nombre = new JLabel();
        nombre.setBounds(40,10,120,20);
        nombre.setFont(new Font("Consolas",1,14));
        panel.add(nombre);
        nombre.setText("Nombre");
                
        pts = new JLabel();
        pts.setBounds(140,10,120,20);
        pts.setFont(new Font("Consolas",1,14));
        panel.add(pts);
        pts.setText("Puntuaciones");
    }
}
