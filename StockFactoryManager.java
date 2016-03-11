import java.rmi.Remote;
import java.rmi.RemoteException;
public interface StockFactoryManager extends Remote
{
    void addFactory( StockFactory sf ) throws RemoteException;
    Stock create( ) throws RemoteException;
}
