/**
* Interface Stock of the Share Market 
* 
* @author  Yoan Ribeiro
*/

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Broker extends Remote
{
    Stock lookup ( String symbol ) throws RemoteException;
}
