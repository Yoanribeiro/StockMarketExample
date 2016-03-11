/**
* Class which implements the Notification interface 
* 
* @author  Yoan Ribeiro
*/

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NotifImpl extends UnicastRemoteObject implements Notif
{
    String order;
    String symbol;
    
    public NotifImpl( String order, String symbol ) throws RemoteException 
    { 
        this.order = order; 
        this.symbol = symbol;
    }
    
    public synchronized void notify ( int quantity, float limit_price )
    {
        System.out.println ( this.order + ":" + this.symbol + ":" + quantity + ":" + limit_price  );
    }
}
