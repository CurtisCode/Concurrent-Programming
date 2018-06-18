/*
 * Author: Curtis Warren
 *
 *
 */
package threadexample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExample {



    public static void main(String[] args) {

        //  Allows only two threads to run at one time.
        ExecutorService exec = Executors.newFixedThreadPool(2);

        //  Executes these four threads.
        exec.execute(new AccessControl("Lion"));
        exec.execute(new AccessControl("Tiger"));
        exec.execute(new AccessControl("Hippo"));
        exec.execute(new AccessControl("Hog"));


        //  Ends ExecutorService.
        exec.shutdown();


    }

}
