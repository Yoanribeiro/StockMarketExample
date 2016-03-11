import java.rmi.Naming;
import java.rmi.RMISecurityManager;
public class Tester
{
     public static void main ( String args[] ) throws Exception
     {
         if (System.getSecurityManager() == null) 
            System.setSecurityManager(new RMISecurityManager()  );
         String [] names = Naming.list("//localhost/");
         for( String s : names)
         {
             System.out.println( s );
         }
     }
}
