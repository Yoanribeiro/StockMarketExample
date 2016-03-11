import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;

public class StockFactoryManagerImpl extends UnicastRemoteObject implements StockFactoryManager
{
    public Map<StockFactory, Integer> factories;
    public StockFactoryManagerImpl (  ) throws RemoteException
    {
        this.factories = new HashMap<StockFactory, Integer>();
    }
    
    public synchronized void addFactory( StockFactory f ) throws RemoteException
    {
        this.factories.put( f, 0 );
    }
    
    public synchronized Stock create ( ) throws RemoteException
    { 
        Stock res;
        Iterator< StockFactory > it = this.factories.keySet().iterator();
        StockFactory lower = (StockFactory)it.next();
        if( lower != null )
        {
            int min = this.factories.get( lower );
            StockFactory nextFact = null;
            while(it.hasNext())
            {
                nextFact = it.next();
                int value = this.factories.get( nextFact );
                if( value <= min )
                {
                    min = value;
                    lower = nextFact;
                }
            }
            this.factories.put( nextFact, (min + 1) );
            res = nextFact.create();
            
        }
        else
            res = null;
        return res;
    }
}
