import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class StartManager
{
    public static void main ( String args[] ) throws Exception
    {
         if (System.getSecurityManager() == null) 
            System.setSecurityManager(new RMISecurityManager()  );
        
         StockFactoryManager manager = new StockFactoryManagerImpl();
         Naming.rebind("//localhost/manager", manager);
         System.out.println("Manager added to Naming Server...");
    }
}
