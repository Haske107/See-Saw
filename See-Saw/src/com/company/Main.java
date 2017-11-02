package com.company;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    private static boolean GoingUp = false;
    private static boolean CurrentTurn = false;
    private static int Iterations = 10;
    public static void main(String[] args) {
	    try {
	        console();
        } catch (IOException e) {
	        e.printStackTrace();
        }
    }
    private static void console() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Press any key to start simulator");
        keyboard.nextLine();
        Start_Simulator();
    }
    private static void Start_Simulator()  {
        A a = new A();
        B b = new B();
        a.start();
        b.start();
    }
    public static class A extends Thread   {
        double Height = 1;
        private void goUp() {
            Height++;
        }
        private void goDown()   {
            Height = Height - 1.5;
        }
        private void checkDirectionChange() {
            if(Height <= 1) {
                GoingUp = true;
                System.out.println("Fred going Up");

            }
            if(Height == 7) {
                GoingUp = false;
                System.out.println("Fred going Down");
            }
        }
        private void releaseLock()   {
            CurrentTurn = !CurrentTurn;
        }
        private void processStep()  {
            System.out.println("Fred's Height is: " + Height);

            while(!CurrentTurn)  {
                // Locked out
            }
            checkDirectionChange();
            releaseLock();
            if(GoingUp) {
                goUp();
            } else  {
                goDown();
            }
        }

        @Override
        public void run(){
           for(int i = 0; i < Iterations; ++i)  {
               try {
                   Thread.sleep(1000);
               } catch (Exception e) {
                   e.printStackTrace();
               }
               processStep();
           }
        }
    }
    public static class B extends Thread   {
        double Height = 7;
        private void goUp() {
            Height = Height + 1.5;
        }
        private void goDown()   {
            Height--;
        }
        private void checkDirectionChange() {
            if(Height <= 1) {
                GoingUp = false;
                System.out.println("Wilma going Up");
            }
            if(Height == 7) {
                GoingUp = true;
                System.out.println("Wilma going Down");

            }
        }
        private void releaseLock()   {
            CurrentTurn = !CurrentTurn;
        }
        private void processStep()  {
            System.out.println("Wilma's Height is: " + Height);

            while(CurrentTurn)  {
                // Locked out
            }
            checkDirectionChange();
            releaseLock();
            if(!GoingUp) {
                goUp();
            } else  {
                goDown();
            }
        }

        @Override
        public void run(){
            for(int i = 0; i < Iterations; ++i)  {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                processStep();
            }
        }
    }
}
