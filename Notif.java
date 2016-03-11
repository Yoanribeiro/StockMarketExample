/**
* Interface of the Notification remote object 
* 
* @author  Yoan Ribeiro
*/

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Notif extends Remote
{
    /* Notification of the quantity sold and at what price*/
    void notify ( int quantity, float limit_price ) throws RemoteException;
}
