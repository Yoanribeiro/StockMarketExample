/**
* Class Implementing Stocks of the Share Market 
* 
* @author  Yoan Ribeiro
*/

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.Map;
import java.util.HashMap;

public class BrokerImpl extends UnicastRemoteObject implements Broker
{

    public Map <String, Stock> market;
    public StockFactoryManager manager;
    
    public BrokerImpl( StockFactoryManager manager ) throws RemoteException 
    { 
        // Where all the Stocks are stored
        this.market = new HashMap< String, Stock >();
        // With Remote factorys responsable for creating Stock objects
        this.manager = manager;
    }
    

    /**
      * Method responsable for retreving the Stock corresponding to the given symbol
      * @param String Symbol 
      * @return Stock object.
      * @exception RemoteException.
      * @see IOException
      */
    /*
        
    */
    public Stock lookup ( String symbol ) throws RemoteException
        {        
            synchronized(this.market)
            {
                Stock s = market.get( symbol );
                if( s == null )
                {
                    try
                    {
                        s = manager.create( );
                    }
                    catch (Exception e) { System.out.println("exception: " + e.getMessage()); }
                    market.put( symbol, s );
                    return s;
                }
                else
                    return s;
            }
        }
    
    /**
     * Without factorys
        public Stock lookup ( String symbol )
        {        
            synchronized(this.market)
            {
                Stock s = market.get( symbol );
                if( s == null )
                {
                    try
                    {
                        s = new StockImpl();
                    }
                    catch (Exception e) { System.out.println("exception: " + e.getMessage()); }
                    market.put( symbol, s );
                    return s;
                }
                else
                    return s;
            }
        }
    */
    /*
    public BrokerImpl( ) throws RemoteException { }
    
    public synchronized Stock lookup ( String symbol )
    {
        if (System.getSecurityManager() == null) 
          System.setSecurityManager(new RMISecurityManager());
        try 
        {
            Stock s = (Stock)Naming.lookup( symbol );
            if( s == null )
            {
                s = new StockImpl();
                Naming.rebind("//localhost/"+symbol, s);
                return s;
            }
            else
                return s;
        } catch (Exception e) { System.out.println("exception: " + e.getMessage()); }
    }*/
}
