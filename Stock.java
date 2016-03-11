import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Stock extends Remote
{
    void buy  ( int quantity, float limit_price, Notif callback ) throws RemoteException;;
    void sell ( int quantity, float limit_price, Notif callback ) throws RemoteException;;
}
