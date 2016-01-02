package gujarat.papancatur;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import gujarat.papancatur.controller.Network.TCPClient;
import gujarat.papancatur.controller.PapanCaturManager;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String currentPositon = "Ka1 Qg3 Be6 Ne4 Rd1 kg6 qa4 bf5 ng1 rf3";
    TCPClient st = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi(){
        PapanCaturManager.getInstance().setChessPosition(this, currentPositon);
    }

    @Override
    public void onResume() {
        startMonitoring();

        super.onResume();
    }

    @Override
    public void onPause() {
        stopMonitoring();

        super.onPause();
    }

    private void startMonitoring() {
        stopMonitoring();

        st = new TCPClient("xinuc.org", 7387);
        st.setListener(new TCPClient.OnReadListener() {
            @Override
            public void onRead(TCPClient tcpClientBaru, final String response) {
                Log.d(TAG, "onRead() called with: " + "tcpClientBaru = [" + tcpClientBaru + "], response = [" + response + "]");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        PapanCaturManager.getInstance().cleanPapanCatur(MainActivity.this, currentPositon);
                        currentPositon = response;
                        PapanCaturManager.getInstance().setChessPosition(MainActivity.this, currentPositon);
                    }
                });

            }
        });
        st.start();
    }

    private void stopMonitoring() {
        if (st != null) {
            st.interrupt();
            st = null;
        }
    }
}
