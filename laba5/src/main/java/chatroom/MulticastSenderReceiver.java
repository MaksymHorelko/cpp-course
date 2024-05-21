package chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSenderReceiver {

    private final String name;
    private InetAddress addr;
    private final int port = 3456;
    private MulticastSocket group;

    public MulticastSenderReceiver(String name) {
        this.name = name;
        try {
            addr = InetAddress.getByName("224.0.0.1");
            group = new MulticastSocket(port);
            new Receiver().start();
            new Sender().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Sender extends Thread {
        @Override
        public void run() {
            try {
                BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
                while(true) {
                    String msg = "[" + name + "]: " + fromUser.readLine();
                    if (msg.equalsIgnoreCase("exit")) {
                        System.out.println("Ви покинули конференцію.");
                        group.leaveGroup(addr);
                        group.close();
                        break;
                    }
                    byte[] out = msg.getBytes();
                    DatagramPacket pkt = new DatagramPacket(out, out.length, addr, port);
                    group.send(pkt);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class Receiver extends Thread {
        @Override
        public void run() {
            try {
                byte[] in = new byte[256];
                DatagramPacket pkt = new DatagramPacket(in, in.length);
                group.joinGroup(addr);
                while(true) {
                    group.receive(pkt);
                    String received = new String(pkt.getData(), 0, pkt.getLength());
                    String[] parts = received.split(":", 2);
                    String senderName = parts[0];
                    String message = parts[1];
                    System.out.println(senderName + ": " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new MulticastSenderReceiver(args[0]);
    }
}