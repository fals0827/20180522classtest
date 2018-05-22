import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mainframe extends JFrame{
    private Container cp ;
    private JPanel jpnC = new JPanel();
    private JPanel jpnS = new JPanel(new GridLayout(1,6,2,2));
    private JLabel jlb = new JLabel();
    private ImageIcon icon = new ImageIcon("ch.png");
    private JButton btnRun = new JButton("Run");
    private JButton btnUp = new JButton("↑");
    private JButton btnDown = new JButton("↓");
    private JButton btnRight = new JButton("→");
    private JButton btnLeft = new JButton("←");
    private JButton btnExit = new JButton("Exit");
    private int dirFlag = 4 , objX = 50 , objY = 50 , objW = 40 , objH = 40;

    public Mainframe (){
        this.init();
    }

    private void init (){
        setBounds(100,100,800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        cp = this.getContentPane();
        cp.add(jpnC,BorderLayout.CENTER);
        cp.add(jpnS,BorderLayout.SOUTH);
        jpnC.setLayout(null);
        jpnC.add(jlb);
        jlb.setIcon(icon);
        jlb.setBounds(objX,objY,objW,objH);
        jpnS.add(btnRun);
        jpnS.add(btnUp);
        jpnS.add(btnDown);
        jpnS.add(btnRight);
        jpnS.add(btnLeft);
        jpnS.add(btnExit);

        final Timer t1 = new Timer(80, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                switch (dirFlag){
                    case 1 :
                        if (objY - 2 > 0 ){
                            objY -= 2;
                        }else {
                            dirFlag = 2 ;
                        }
                        break;
                    case 2 :
                        if (objY + 2 < 550 - objH ){
                            objY += 2;
                        }else {
                            dirFlag = 1 ;
                        }
                        break;
                    case 3 :
                        if (objX - 2 > 0 ){
                            objX -= 2;
                        }else {
                            dirFlag = 4 ;
                        }
                        break;
                    case 4 :
                        if (objX + 2 < 750 - objW ){
                            objX += 2;
                        }else {
                            dirFlag = 3 ;
                        }
                        break;
                }
                jlb.setLocation(objX,objY);
            }
        });

        btnRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                t1.start();
            }
        });

        btnUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dirFlag = 1 ;
            }
        });

        btnDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dirFlag = 2 ;
            }
        });

        btnLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dirFlag = 3 ;
            }
        });

        btnRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dirFlag = 4 ;
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }
}
