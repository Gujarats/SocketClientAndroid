package gujarat.papancatur.controller.Network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Gujarat Santana on 02/01/16.
 */
public class TCPClient extends Thread {
    public interface OnReadListener {
        void onRead(TCPClient tcpClientBaru, String response);
    }

    Socket socket;
    String address;
    int port;
    OnReadListener listener = null;

    public TCPClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(InetAddress.getByName(address), port);

            // read the incoming messege from server
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            //loop the messege if exist
            while ((line = br.readLine()) != null) {
                if (listener != null) {
                    listener.onRead(this, line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setListener(OnReadListener listener) {
        this.listener = listener;
    }
}
