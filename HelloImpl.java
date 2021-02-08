import java.io.*;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements Hello {

    private static final int PORT = 2019;

    public HelloImpl() throws Exception {
        super(PORT,
              new RMISSLClientSocketFactory(),
              new RMISSLServerSocketFactory());
    }

    public String sayHello() {
        return "Hello World!";
    }

    public static void main(String args[]) {

        // Create and install a security manager
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        try {
            // Create SSL-based registry
            Registry registry = LocateRegistry.createRegistry(PORT,
                new RMISSLClientSocketFactory(),
                new RMISSLServerSocketFactory());

            HelloImpl obj = new HelloImpl();

            // Bind this object instance to the name "HelloServer"
            registry.bind("HelloServer", obj);

            System.out.println("HelloServer bound in registry");
        } catch (Exception e) {
            System.out.println("HelloImpl err: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
