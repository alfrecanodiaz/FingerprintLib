package py.com.softcoding.fingerprintlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import py.com.softcoding.fingerprintlib.fingerprint.callback.FingerprintDialogCallback;
import py.com.softcoding.fingerprintlib.fingerprint.dialog.FingerprintDialog;
import py.com.softcoding.fingerprintlib.fingerprint.view.Fingerprint;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Fingerprint.isAvailable(this)) {
            FingerprintDialog.initialize(this)
                    .title("Test de Fingerprint")
                    .message("Confirme su huella dactilar")
                    .callback(new FingerprintDialogCallback() {
                        @Override
                        public void onAuthenticationSucceeded() {

                        }

                        @Override
                        public void onAuthenticationCancel() {

                        }
                    })
                    .show();
        }
    }
}
