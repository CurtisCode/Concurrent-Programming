/*
 * Author: Curtis Warren
 * Date: 06/18/18
 * Purpose: To illustrate how I would perform thread safety.
 * Description:
 * This program defines the AccessControl class.
 * This class ensures that only one thread has access to the important program resources.
 * I have illustrated this with apples as an example of a program resource that should be kept safe when the
 * program is running.
 *
 */
package threadexample;

import java.util.concurrent.Semaphore;

public class AccessControl implements Runnable {

    //  The semaphore here controls access to the data (apples).
    private static Semaphore dataController;
    //  Stores the thread's name.
    private String threadName;
    //  The apples datafield stores the important resource, that the program will attempt to keep safe.
    private static int apples;

    public AccessControl (String name) {
        //  Places a value into threadName.
        this.threadName = name;
    }
    static {
        //  Creates a semaphore with one permit.
        dataController = new Semaphore(1);
        //  Starts the program with a intial value of 100 for the apples datafield.
        //  This indicates that 100 apples are avaliable for use in the program.
        apples = 100;
    }
    //  Handles the request for apples by separate threads.
    public int pickApples (int request) {

       if (apples <= 0)
           return 0;

       apples = apples - request;
       return apples;

    }
    @Override
    public void run() {

        try {
            //  A thread aquires a semaphore permit.
            dataController.acquire();
            //  Handles the case where all apples have been requested before they can be "picked" by the next thread.
            if (apples <= 0) {
                System.out.println("Sorry, " + this.threadName + ".") ;
                System.out.println("All the apples are gone!") ;
                return;
            }
            //  A random number is used as the value for the number of apples a thread should request.
            int requested = (int)(Math.random() * 100);
            //  In the case that zero apples remain the following message is displayed.
            if (pickApples (requested) <= 0) {
               System.out.println(requested + " apples have been requested by " + this.threadName + ", 0 remain.") ;
            } else {
            //  In the case that one or more apples remain the following message is displayed.
             System.out.println(requested + " apples have been requested by " + this.threadName + ", " +  apples + " remain.") ;
            }
        } catch (InterruptedException exp) {
            exp.printStackTrace();
        } finally {
            //  Semaphore permit is let go for another thread to use.
            dataController.release();
        }

    }
}
