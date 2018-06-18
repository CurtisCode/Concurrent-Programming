/*
 * Author: Curtis Warren
 *
 *
 */
package helloworld;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class  Writing implements Runnable {

    private JLabel label;

    Writing(JLabel newLabel) {

        this.label = newLabel;

    }

    public void run() {

        String message = "Hello, world! I am a software engineer!";
        StringBuilder builder = new StringBuilder();

        int counter = 0;
        while (counter < message.length()) {

            builder.append(message.charAt(counter));
            HelloWorld.setTextLabel(builder.toString(), label);
            try {
                Thread.sleep(500);
            } catch (InterruptedException exp) {

                exp.printStackTrace();

            }

            counter++;
        }


    }

}
