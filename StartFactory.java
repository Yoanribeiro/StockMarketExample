import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class StartFactory
{
    public static void main ( String args[] ) throws Exception
    {
         if (System.getSecurityManager() == null) 
            System.setSecurityManager(new RMISecurityManager()  );
        
         StockFactoryManager manager = (StockFactoryManager)Naming.lookup("rmi://localhost/manager");
         for( int i = 0; i < 4; i++)
         {
             StockFactory newFact = new StockFactoryImpl();
             manager.addFactory( newFact );
             System.out.println("Factory "+ i + " created...");
         }  
    }
}