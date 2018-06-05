package py.com.softcoding.fingerprintlib;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import py.com.softcoding.fingerprintlib.fingerprint.callback.FailAuthCounterCallback;
import py.com.softcoding.fingerprintlib.fingerprint.callback.FingerprintDialogCallback;
import py.com.softcoding.fingerprintlib.fingerprint.dialog.FingerprintPrompt;
import py.com.softcoding.fingerprintlib.fingerprint.facade.Fingerprint;
import py.com.softcoding.fingerprintlib.fingerprint.utils.FingerprintToken;

public class MainActivity extends AppCompatActivity {
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);

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
                            tvStatus.setText("onAuthenticationSucceeded");
                            tvStatus.setTextColor(ActivityCompat.getColor(MainActivity.this, android.R.color.holo_green_dark));
                        }

                        @Override
                        public void onAuthenticationCancel() {
                            tvStatus.setText("onAuthenticationCancel");
                            tvStatus.setTextColor(ActivityCompat.getColor(MainActivity.this, android.R.color.holo_red_dark));
                        }

                        @Override
                        public void onNewFingerprintEnrolled(FingerprintToken token) {
                            tvStatus.setText("onNewFingerprintEnrolled");
                            tvStatus.setTextColor(ActivityCompat.getColor(MainActivity.this, android.R.color.holo_blue_dark));
                        }
                    }, Fingerprint.Keys.FINGERPRINT_KEY)
                    .tryLimit(Fingerprint.DefaultConfiguration.TRY_LIMIT, new FailAuthCounterCallback() {
                        @Override
                        public void onTryLimitReached() {

                        }
                    })
                    .show();
        }
    }
}