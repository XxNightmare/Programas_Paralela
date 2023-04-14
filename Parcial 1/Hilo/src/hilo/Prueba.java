package hilo;

public class Prueba extends Thread {
    public void run(){
        while(true){
            try{
                System.out.println("Holaaa");
                sleep(2000);
            } catch (Exception e) {
            }
        }
    }

}
