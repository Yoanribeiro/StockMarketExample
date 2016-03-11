import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
public class StockFactoryImpl extends UnicastRemoteObject implements StockFactory
{
    public StockFactoryImpl() throws RemoteException {}
    
    public Stock create ( ) throws RemoteException
    {
        Stock res = new StockImpl();
        return res;
    }
    
}
