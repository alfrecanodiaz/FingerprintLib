package py.com.softcoding.fingerprintlib.fingerprint.dialog;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import py.com.softcoding.fingerprintlib.R;
import py.com.softcoding.fingerprintlib.fingerprint.callback.FailAuthCounterCallback;
import py.com.softcoding.fingerprintlib.fingerprint.callback.FingerprintDialogCallback;
import py.com.softcoding.fingerprintlib.fingerprint.callback.FingerprintCallback;
import py.com.softcoding.fingerprintlib.fingerprint.facade.Fingerprint;
import py.com.softcoding.fingerprintlib.fingerprint.utils.FingerprintToken;
import py.com.softcoding.fingerprintlib.fingerprint.view.FingerprintView;

/**
 * Created by Alfredo Cano on 03/06/18.
 */

public class FingerprintPrompt extends FingerprintDialog<FingerprintPrompt> {
    private final static String TAG = "FingerprintPrompt";

    private FingerprintView fingerprintView;
    private TextView dialogTitle, dialogMessage, dialogStatus;
    private AppCompatButton cancelButton;

    private FingerprintDialogCallback fingerprintDialogCallback;

    private int statusScanningColor, statusSuccessColor, statusErrorColor;
    private Handler handler;

    private int delayAfterError, delayAfterSuccess;

    private FingerprintPrompt(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.handler = new Handler();
        this.delayAfterError = Fingerprint.DefaultConfiguration.DELAY_AFTER_ERROR;
        this.delayAfterSuccess = Fingerprint.DefaultConfiguration.DELAY_AFTER_ERROR;

        this.statusScanningColor = R.color.status_scanning;
        this.statusSuccessColor = R.color.status_success;
        this.statusErrorColor = R.color.status_error;

        this.dialogView = layoutInflater.inflate(R.layout.fingerprint_dialog, null);
        this.fingerprintView = dialogView.findViewById(R.id.fingerprint_dialog_fp);
        this.dialogTitle = dialogView.findViewById(R.id.fingerprint_dialog_title);
        this.dialogMessage = dialogView.findViewById(R.id.fingerprint_dialog_message);
        this.dialogStatus = dialogView.findViewById(R.id.fingerprint_dialog_status);
        this.cancelButton = dialogView.findViewById(R.id.fingerprint_dialog_cancel);
    }

    /**
     * Check if a fingerprint scanner is available and if at least one finger is enrolled in the phone.
     * @param context A context
     * @return True if authentication is available, False otherwise
     */
    public static boolean isAvailable(Context context) {
        return Fingerprint.isAvailable(context);
    }

    /**
     * Create a FingerprintPrompt instance.
     * @param context Activity Context
     * @return FingerprintPrompt instance
     */
    public static FingerprintPrompt initialize(Context context) {
        return new FingerprintPrompt(context);
    }

    /**
     * Set a callback for secured authentication.
     * @param fingerprintDialogCallback The callback
     * @param KEY_NAME An arbitrary string used to create a cipher pair in the Android KeyStore
     * @return FingerprintPrompt object
     */
    public FingerprintPrompt callback(FingerprintDialogCallback fingerprintDialogCallback, String KEY_NAME) {
        this.fingerprintDialogCallback = fingerprintDialogCallback;
        this.fingerprintView.callback(fingerprintCallback, KEY_NAME);
        return this;
    }

    /**
     * Set a fail limit. Android blocks automatically when 5 attempts failed.
     * @param limit number of tries
     * @param counterCallback callback to be triggered when limit is reached
     * @return FingerprintPrompt object
     */
    public FingerprintPrompt tryLimit(int limit, FailAuthCounterCallback counterCallback) {
        this.fingerprintView.tryLimit(limit, counterCallback);
        return this;
    }

    /**
     * Show the dialog.
     */
    public void show() {
        if (title == null || message == null) {
            throw new RuntimeException("Title or message cannot be null.");
        }

        if (fingerprintCallback == null) {
            throw new RuntimeException("You must specify a callback.");
        }

        showDialog();
    }

    private void showDialog() {
        dialogTitle.setText(title);
        dialogMessage.setText(message);
        cancelButton.setText(R.string.fingerprint_cancel);
        setStatus(R.string.fingerprint_state_scanning, statusScanningColor);

        builder.setView(dialogView);
        dialog = builder.create();

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fingerprintView.cancel();
                if (fingerprintDialogCallback != null) {
                    fingerprintDialogCallback.onAuthenticationCancel();
                }
                dialog.cancel();
            }
        });

        if (dialog.getWindow() != null && !dimBackground) {
            dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        dialog.setCanceledOnTouchOutside(cancelOnTouchOutside);
        dialog.setCancelable(cancelOnPressBack);
        dialog.show();

        authenticate();
    }

    private void authenticate() {
        fingerprintView.authenticate();
    }

    private void setStatus(int textId, int textColorId) {
        setStatus(context.getResources().getString(textId), textColorId);
    }

    private void setStatus(String text, int textColorId) {
        dialogStatus.setTextColor(ResourcesCompat.getColor(context.getResources(), textColorId, context.getTheme()));
        dialogStatus.setText(text);
    }

    private Runnable returnToScanning = new Runnable() {
        @Override
        public void run() {
            setStatus(R.string.fingerprint_state_scanning, statusScanningColor);
        }
    };

    private FingerprintCallback fingerprintCallback = new FingerprintCallback() {
        @Override
        public void onAuthenticationSucceeded() {
            handler.removeCallbacks(returnToScanning);
            setStatus(R.string.fingerprint_state_success, statusSuccessColor);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.cancel();
                    if (fingerprintDialogCallback != null) {
                        fingerprintDialogCallback.onAuthenticationSucceeded();
                    }
                }
            }, delayAfterSuccess);
        }

        @Override
        public void onAuthenticationFailed() {
            setStatus(R.string.fingerprint_state_failure, statusErrorColor);
            handler.postDelayed(returnToScanning, delayAfterError);
        }

        @Override
        public void onNewFingerprintEnrolled(FingerprintToken token) {
            dialog.cancel();
            if (fingerprintDialogCallback != null) {
                fingerprintDialogCallback.onNewFingerprintEnrolled(token);
            }
        }

        @Override
        public void onAuthenticationError(int errorCode, String error) {
            setStatus(error, statusErrorColor);
            handler.postDelayed(returnToScanning, delayAfterError);
        }
    };
}