/**
* Client Side of a Share Market System made in Java RMI
* 
* @author  Yoan Ribeiro 
*
*/

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client 
{
    public static void main( String[] args ) 
    {
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new RMISecurityManager());
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userIn = new String();
        try
        {
            Broker market = (Broker)Naming.lookup("rmi://localhost/broker");
            while(userIn != null)
            {
                Share s = new Share();
                System.out.print("Order? BUY/SELL\n> ");
                userIn = stdIn.readLine();
                if(userIn.equals("BUY"))
                {
                    System.out.print("\nSymbol?\n> ");
                       userIn = stdIn.readLine();
                       Stock stock = (Stock)market.lookup(userIn);
                       Notif cb = new NotifImpl( "BOUGHT", userIn ); 
                    System.out.print("\nQuantity?\n> ");
                       userIn = stdIn.readLine();
                       s.quantity = (Integer.parseInt(userIn));
                    System.out.print("\nLimit Price?\n> ");
                       userIn = stdIn.readLine();
                       s.limit_price = Float.parseFloat(userIn);
                       
                    stock.buy(s.quantity, s.limit_price, cb);
                }
                else
                   if(userIn.equals("SELL"))
                   {
                       System.out.print("\nSymbol?\n> ");
                           userIn = stdIn.readLine();
                           Stock stock = market.lookup(userIn);
                           Notif cb = new NotifImpl( "SOLD", userIn ); 
                       System.out.print("\nQuantity?\n> ");
                           userIn = stdIn.readLine();
                           s.quantity = (Integer.parseInt(userIn));
                       System.out.print("\nLimit Price?\n> ");
                           userIn = stdIn.readLine();
                           s.limit_price = Float.parseFloat(userIn);
                            
                       stock.sell(s.quantity, s.limit_price, cb);
                    }
            }
        }
        catch(Exception e) { e.printStackTrace(); }
    }
}
