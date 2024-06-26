package chatroom;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MessanderImpl implements Messanger{

    private final UITasks ui;
    private MulticastSocket group = null;
    private final InetAddress addr;
    private final int port;
    private final String name;

    private boolean canceled = false;

    public MessanderImpl(InetAddress addr, int port, String name, UITasks ui) {
        this.name = name;
        this.ui = ui;
        this.addr = addr;
        this.port = port;
        try {
            group = new MulticastSocket(port);
            group.setTimeToLive(2);
            group.joinGroup(addr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start() {
        Thread t = new Receiver();
        t.start();
    }

    @Override
    public void stop() {
        cancel();
        try {
            if (!group.isClosed()) {
                group.leaveGroup(addr);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Помилка від'єднання\n" + e.getMessage());
        } finally {
            group.close();
        }
    }

    @Override
    public void send() {
        new Sender().start();
    }

    private class Sender extends Thread {
        public void run() {
            try {
                String msg = name + ": " + ui.getMessage();
                byte[] out = msg.getBytes();
                DatagramPacket pkt = new DatagramPacket(out, out.length, addr, port);
                group.send(pkt);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Помилка відправлення\n" + e.getMessage());
            }
        }
    }

    private class Receiver extends Thread {
        public void run() {
            try {
                byte[] in = new byte[512];
                DatagramPacket pkt = new DatagramPacket(in, in.length);
                while (!isCanceled()) {
                    group.receive(pkt);
                    ui.setText(new String(pkt.getData(), 0, pkt.getLength()));
                }
            } catch (IOException e) {
                if (isCanceled()) {
                    JOptionPane.showMessageDialog(null, "З'єднання завершено");
                } else {
                    JOptionPane.showMessageDialog(null, "Помилка прийому\n" + e.getMessage());
                }
            }
        }
    }

    private synchronized boolean isCanceled() {
        return canceled;
    }

    public synchronized void cancel() {
        canceled = true;
    }
}
