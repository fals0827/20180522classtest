import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private int tarX , tarY , nX , nY ;
    private float m = 0.0f;
    private Timer t1 , t2;
    private Boolean flag = false;

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

        t1 = new Timer(80, new ActionListener() {
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

        jlb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                flag = true;
            }
        });

        jpnC.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if (flag){
                    tarX = mouseEvent.getX(); tarY = mouseEvent.getY();
                    m = (float) (tarY - jlb.getY()) / (float) (tarX - jlb.getX());
                    t2.start();
                    flag = false;
                }
            }
        });

        t2 = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (Math.abs(jlb.getX() - tarX) < 10 && Math.abs(jlb.getY() - tarY) < 10){
                    t2.stop();
                }else {
                    if (jlb.getX() - tarX < 0){
                        nX = jlb.getX() +1 ;
                    }else {
                        nX = jlb.getX() -1 ;
                    }
                }
                nY = Math.round(m * (float)(nX - tarX) + tarY);
                jlb.setLocation(nX , nY);
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
