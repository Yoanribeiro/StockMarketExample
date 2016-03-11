import java.rmi.Remote;
import java.rmi.RemoteException;
public interface StockFactory extends Remote
{
    Stock create( ) throws RemoteException;
}
