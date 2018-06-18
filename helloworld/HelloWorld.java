/*
 * Author: Curtis Warren
 * Date: 06/18/18
 * Description: This program writes a message using threads.
 */
package helloworld;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;

public class HelloWorld extends JFrame {

    private static JLabel helloLabel1;
    private static JLabel helloLabel2;
    private static JLabel helloLabel3;

    private static JPanel firstPane;
    private static JPanel secondPane;
    private static JPanel thirdPane;

    HelloWorld () {

        super();

        this.setSize(500, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setLayout(new GridLayout(4,0));

        firstPane = new JPanel();
        secondPane = new JPanel();
        thirdPane = new JPanel();

        helloLabel1 = new JLabel("Click Me");
        helloLabel2 = new JLabel();
        helloLabel3 = new JLabel();

        Font font = new Font("Arial", 25, 25);

        helloLabel1.setFont(font);
        helloLabel2.setFont(font);
        helloLabel3.setFont(font);

        firstPane.add(helloLabel1);
        secondPane.add(helloLabel2);
        thirdPane.add(helloLabel3);

        this.add(firstPane, 0, 0);
        this.add(secondPane, 1, 0);
        this.add(thirdPane, 2, 0);

        helloLabel1.addMouseListener( new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                Thread t = new Thread (new Writing(helloLabel1));
                Thread t2 = new Thread (new Writing(helloLabel2));
                Thread t3 = new Thread (new Writing(helloLabel3));

                t.start();
                t2.start();
                t3.start();

            }


        });

    }

    public  static void setTextLabel (String text, JLabel label) {

        label.setText(text);

    }
    public static void main(String[] args) {

        HelloWorld hello = new HelloWorld();

        hello.setVisible(true);


    }

}
