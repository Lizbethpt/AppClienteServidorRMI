import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

public class BackupTask extends TimerTask {
    private MyRemoteImpl server;

    public BackupTask(MyRemoteImpl server) {
        this.server = server;
    }

    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("backup.ser"))) {
            out.writeObject(server); // Serializa el objeto servidor
            System.out.println("Backup created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // Crea la instancia del servidor
            MyRemoteImpl server = new MyRemoteImpl();

            // Configura el temporizador para crear respaldos cada minuto
            Timer timer = new Timer(true);
            timer.scheduleAtFixedRate(new BackupTask(server), 0, 60000); // Cada minuto

            System.out.println("BackupTask is running. Press Ctrl+C to stop.");
            
            // Mantén el programa corriendo
            Thread.sleep(Long.MAX_VALUE);

        } catch (RemoteException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
