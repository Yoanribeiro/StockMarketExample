import java.io.Serializable;
public class Share implements Serializable
{
    public int quantity;
    public float limit_price;
    public Notif callback;
    
    public Share(){}
    
    public Share( int quantity, float limit_price, Notif callback )
    {
        this.quantity = quantity;
        this.limit_price = limit_price;
        this.callback = callback;
    }
}