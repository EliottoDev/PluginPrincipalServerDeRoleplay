public class Threads implements Runnable{


    public static void main(String[] args) throws InterruptedException {


        Runnable runnable = new Threads();

        Thread thread = new Thread(runnable);

        thread.start();

        while (true){


            Thread.sleep(2000);
            System.out.println("Adios");
        }
    }


    @Override
    public void run(){

        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Hola");

        }
    }
}
