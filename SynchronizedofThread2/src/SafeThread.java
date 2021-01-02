
import java.util.logging.Level;
import java.util.logging.Logger;

public class SafeThread {
    /*
    count üzerinde biri çalışsın onun işlemi bitince diğeri girebilsin yapmaya çalıştığımız bu 
    Senkron sorununu çözmek için synchronized anahtar kelimesini kullanıyoruz
    */
    private int count = 0;
    public synchronized void arttır(){
        count++;
        //bu yapı sayesinde sadece biri işleme giricek ve onun işlemi bitmeden diğer işlem başlamayacak sonucumuz standartlaşacak
        //anahtar kilit ilişkisi ile düşünülmeli bu yapı 
        //bir işlem başlayınca anahtarı alıyor ve işlem bitene kadar kimseye bu anahtar verilmiyor gibi düşünebiliriz
        //eğer biz bir metoda synchronized yazarsak bu metodun üzerinde değil bu classın üzerinde bir anahtar oluyor.Başka hiçbir thread başka hiçbir synchronized metoduna dahi giremiyor bu yapı kurulduğunda 
    }   //Tek bir anahtar oluyor ve synchronized metodlarına o anahtar ile girilebiliyor.
        //bu yapı herzaman tercih edilmez mühendislik açısındanda zaten edilemez avantajı kadar devavantajıda bulunmakta...
    
    public  void  threadlericalıstır(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
            for(int i = 0;i<5000;i++){
               arttır();
            }
            }
        });
            Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
            for(int i = 0;i<5000;i++){
               arttır();
            }
            }
        });
            thread1.start();
            thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(SafeThread.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            System.out.println("count değişkenimizin değeri = "+count);
            System.out.println("buradki yazdırmalarda main threade ait işlemlerdir");
    }
    public static void main(String[] args) {

        SafeThread safeThread = new SafeThread();
        safeThread.threadlericalıstır();
    }
}
