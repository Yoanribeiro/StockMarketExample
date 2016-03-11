/**
* Class Implementing Stock 
* 
* @author  Yoan Ribeiro
*/

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class StockImpl extends UnicastRemoteObject implements Stock
{
    List< Share > buys;
    List< Share > sells;
    
    public StockImpl( ) throws RemoteException
    {
        this.buys  = new ArrayList< Share >();
        this.sells = new ArrayList< Share >();
    }   
    
    public synchronized void buy ( int quantity, float limit_price, Notif callback )
    {
        Iterator it = sells.iterator();
        boolean flag = false;
        while( it.hasNext() && flag == false )
        {
            Share s = (Share)it.next();
            if( s.quantity >= quantity && s.limit_price <= limit_price  )
            {
                flag = true;
                if(s.quantity - quantity == 0)
                {
                    try
                    {
                        s.callback.notify( quantity, (limit_price + s.limit_price)/2 );
                        callback.notify( quantity, (limit_price + s.limit_price)/2 );
                        // request removed from the stock
                        this.sells.remove(s);
                    }
                    catch( Exception e ){ e.printStackTrace(); }
                }
                else
                {
                    try
                    {
                        //Notify buyer
                        callback.notify( quantity, (limit_price + s.limit_price)/2 );
                        //Notify seller
                        s.callback.notify( quantity - s.quantity, (limit_price + s.limit_price)/2 );
                        //Update of the request
                        int indexOfS = this.buys.indexOf(s); 
                        this.sells.set(indexOfS, s);
                    }
                    catch( Exception e ){ e.printStackTrace(); }
                }
            }
        }
        Share newShare = new Share( quantity, limit_price, callback );
        
        if( flag != true )
                this.buys.add( newShare );
        
    }
    
    public synchronized void sell( int quantity, float limit_price, Notif callback )
    {
        Iterator it = buys.iterator();
        boolean flag = false;
        while( it.hasNext() && flag == false )
        {
            Share s = (Share)it.next();
            if( s.quantity <= quantity && s.limit_price >= limit_price  )
            {
                flag = true;
                if(s.quantity - quantity == 0)
                {
                    try
                    {
                        s.callback.notify( quantity, (limit_price + s.limit_price)/2 );
                        callback.notify( quantity, (limit_price + s.limit_price)/2 );
                        // request removed from the stock
                        this.buys.remove(s);
                    }
                    catch( Exception e ){ e.printStackTrace(); }
                }
                else
                {
                    try
                    {
                        //Notify seller
                        callback.notify( quantity, (limit_price + s.limit_price)/2 );
                        ////Notify buyer
                        s.callback.notify( quantity - s.quantity, (limit_price + s.limit_price)/2 );
                        //Update of the request
                        int indexOfS = this.buys.indexOf(s); 
                        this.buys.set(indexOfS, s);
                    }
                    catch( Exception e ){ e.printStackTrace(); }
                }
            }
        }
        Share newShare = new Share( quantity, limit_price, callback );
        
        if( flag != true )
                this.sells.add( newShare );
        
    }
}
