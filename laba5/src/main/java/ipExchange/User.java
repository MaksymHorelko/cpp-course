package ipExchange;

import java.io.Serializable;
import java.net.InetAddress;

public class User implements Serializable {

    private static final long serialVersionUID = -1L;

    private final InetAddress address;
    private final int port;

    public User(InetAddress address, int port){
        this.address = address;
        this.port = port;
    }

    @Override
    public String toString() {
        return "Host: " + address.getHostName() +
                "\tAddress: " + address.getHostAddress() + " Port: " + port;
    }
}