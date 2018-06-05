package py.com.softcoding.fingerprintlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import py.com.softcoding.fingerprintlib.fingerprint.callback.FailAuthCounterCallback;
import py.com.softcoding.fingerprintlib.fingerprint.callback.FingerprintDialogCallback;
import py.com.softcoding.fingerprintlib.fingerprint.callback.FingerprintDialogSecureCallback;
import py.com.softcoding.fingerprintlib.fingerprint.dialog.FingerprintPrompt;
import py.com.softcoding.fingerprintlib.fingerprint.facade.Fingerprint;
import py.com.softcoding.fingerprintlib.fingerprint.utils.FingerprintToken;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnFingerprint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fingerprint();
            }
        });
    }

    private void fingerprint() {
        if (FingerprintPrompt.isAvailable(this)) {
            FingerprintPrompt.initialize(this)
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
                    .tryLimit(Fingerprint.DefaultConfiguration.TRY_LIMIT, new FailAuthCounterCallback() {
                        @Override
                        public void onTryLimitReached() {

                        }
                    })
                    .show();
        }
    }

    private void fingerprintSecure() {
        if (FingerprintPrompt.isAvailable(this)) {
            FingerprintPrompt.initialize(this)
                    .title("Test de Fingerprint")
                    .message("Confirme su huella dactilar")
                    .callback(new FingerprintDialogSecureCallback() {
                        @Override
                        public void onAuthenticationSucceeded() {

                        }

                        @Override
                        public void onAuthenticationCancel() {

                        }

                        @Override
                        public void onNewFingerprintEnrolled(FingerprintToken token) {

                        }
                    }, getResources().getString(R.string.fingerprint_key))
                    .tryLimit(Fingerprint.DefaultConfiguration.TRY_LIMIT, new FailAuthCounterCallback() {
                        @Override
                        public void onTryLimitReached() {

                        }
                    })
                    .show();
        }
    }
}