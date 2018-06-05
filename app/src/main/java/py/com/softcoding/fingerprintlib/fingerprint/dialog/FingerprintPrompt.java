package py.com.softcoding.fingerprintlib.fingerprint.dialog;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import py.com.softcoding.fingerprintlib.R;
import py.com.softcoding.fingerprintlib.fingerprint.callback.FailAuthCounterCallback;
import py.com.softcoding.fingerprintlib.fingerprint.callback.FingerprintCallback;
import py.com.softcoding.fingerprintlib.fingerprint.callback.FingerprintDialogCallback;
import py.com.softcoding.fingerprintlib.fingerprint.callback.FingerprintDialogSecureCallback;
import py.com.softcoding.fingerprintlib.fingerprint.callback.FingerprintSecureCallback;
import py.com.softcoding.fingerprintlib.fingerprint.utils.FingerprintToken;
import py.com.softcoding.fingerprintlib.fingerprint.view.FingerprintView;

/**
 * Created by Alfredo Cano on 03/06/18.
 */

public class FingerprintPrompt extends FingerprintDialog<FingerprintPrompt> {
    private FingerprintView fingerprintView;
    private TextView dialogTitle, dialogMessage, dialogStatus;
    private AppCompatButton cancelButton;

    private FingerprintDialogCallback fingerprintDialogCallback;
    private FingerprintDialogSecureCallback fingerprintDialogSecureCallback;

    private int statusScanningColor, statusSuccessColor, statusErrorColor;
    private Handler handler;

    private int delayAfterError, delayAfterSuccess;

    private final static String TAG = "FingerprintPrompt";

    private FingerprintPrompt(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.handler = new Handler();
        this.delayAfterError = FingerprintView.DEFAULT_DELAY_AFTER_ERROR;
        this.delayAfterSuccess = FingerprintView.DEFAULT_DELAY_AFTER_ERROR;

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
        FingerprintManager manager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
        return (manager != null && manager.isHardwareDetected() && manager.hasEnrolledFingerprints());
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
     * Set an authentication callback.
     * @param fingerprintDialogCallback The callback
     * @return FingerprintPrompt object
     */
    public FingerprintPrompt callback(FingerprintDialogCallback fingerprintDialogCallback) {
        this.fingerprintDialogCallback = fingerprintDialogCallback;
        this.fingerprintView.callback(fingerprintCallback);
        return this;
    }

    /**
     * Set a callback for secured authentication.
     * @param fingerprintDialogSecureCallback The callback
     * @param KEY_NAME An arbitrary string used to create a cipher pair in the Android KeyStore
     * @return FingerprintPrompt object
     */
    public FingerprintPrompt callback(FingerprintDialogSecureCallback fingerprintDialogSecureCallback, String KEY_NAME) {
        this.fingerprintDialogSecureCallback = fingerprintDialogSecureCallback;
        this.fingerprintView.callback(fingerprintSecureCallback, KEY_NAME);
        return this;
    }

    /**
     * Perform a secured authentication using that particular CryptoObject.
     * @param cryptoObject CryptoObject to use
     * @return FingerprintPrompt object
     */
    public FingerprintPrompt cryptoObject(FingerprintManager.CryptoObject cryptoObject) {
        this.fingerprintView.cryptoObject(cryptoObject);
        return this;
    }

    /**
     * Set color of the fingerprint scanning status.
     * @param fingerprintScanningColor resource color
     * @return FingerprintPrompt object
     */
    public FingerprintPrompt fingerprintScanningColor(int fingerprintScanningColor) {
        this.fingerprintView.fingerprintScanningColor(fingerprintScanningColor);
        return this;
    }

    /**
     * Set color of the fingerprint success status.
     * @param fingerprintSuccessColor resource color
     * @return FingerprintPrompt object
     */
    public FingerprintPrompt fingerprintSuccessColor(int fingerprintSuccessColor) {
        this.fingerprintView.fingerprintSuccessColor(fingerprintSuccessColor);
        return this;
    }

    /**
     * Set color of the fingerprint error status.
     * @param fingerprintErrorColor resource color
     * @return FingerprintPrompt object
     */
    public FingerprintPrompt fingerprintErrorColor(int fingerprintErrorColor) {
        this.fingerprintView.fingerprintErrorColor(fingerprintErrorColor);
        return this;
    }

    /**
     * Set color of the circle scanning status.
     * @param circleScanningColor resource color
     * @return FingerprintPrompt object
     */
    public FingerprintPrompt circleScanningColor(int circleScanningColor) {
        this.fingerprintView.circleScanningColor(circleScanningColor);
        return this;
    }

    /**
     * Set color of the circle success status.
     * @param circleSuccessColor resource color
     * @return FingerprintPrompt object
     */
    public FingerprintPrompt circleSuccessColor(int circleSuccessColor) {
        this.fingerprintView.circleSuccessColor(circleSuccessColor);
        return this;
    }

    /**
     * Set color of the circle error status.
     * @param circleErrorColor resource color
     * @return FingerprintPrompt object
     */
    public FingerprintPrompt circleErrorColor(int circleErrorColor) {
        this.fingerprintView.circleErrorColor(circleErrorColor);
        return this;
    }

    /**
     * Set color of the text scanning status.
     * @param statusScanningColor resource color
     * @return FingerprintPrompt object
     */
    public FingerprintPrompt statusScanningColor(int statusScanningColor) {
        this.statusScanningColor = statusScanningColor;
        return this;
    }

    /**
     * Set color of the text success status.
     * @param statusSuccessColor resource color
     * @return FingerprintPrompt object
     */
    public FingerprintPrompt statusSuccessColor(int statusSuccessColor) {
        this.statusSuccessColor = statusSuccessColor;
        return this;
    }

    /**
     * Set color of the text error status.
     * @param statusErrorColor resource color
     * @return FingerprintPrompt object
     */
    public FingerprintPrompt statusErrorColor(int statusErrorColor) {
        this.statusErrorColor = statusErrorColor;
        return this;
    }

    /**
     * Set delay before triggering callback after a failed attempt to authenticate.
     * @param delayAfterError delay in milliseconds
     * @return FingerprintPrompt object
     */
    public FingerprintPrompt delayAfterError(int delayAfterError) {
        this.delayAfterError = delayAfterError;
        this.fingerprintView.delayAfterError(delayAfterError);
        return this;
    }

    /**
     * Set delay before triggering callback after a successful authentication.
     * @param delayAfterSuccess delay in milliseconds
     * @return FingerprintPrompt object
     */
    public FingerprintPrompt delayAfterSuccess(int delayAfterSuccess) {
        this.delayAfterSuccess = delayAfterSuccess;
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
        if(title==null || message==null) {
            throw new RuntimeException("Title or message cannot be null.");
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
                if (fingerprintDialogSecureCallback != null) {
                    fingerprintDialogSecureCallback.onAuthenticationCancel();
                } else {
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
        public void onAuthenticationError(int errorCode, String error) {
            setStatus(error, statusErrorColor);
            handler.postDelayed(returnToScanning, delayAfterError);
        }
    };

    private FingerprintSecureCallback fingerprintSecureCallback = new FingerprintSecureCallback() {
        @Override
        public void onAuthenticationSucceeded() {
            handler.removeCallbacks(returnToScanning);
            setStatus(R.string.fingerprint_state_success, statusSuccessColor);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.cancel();
                    if (fingerprintDialogSecureCallback!=null) {
                        fingerprintDialogSecureCallback.onAuthenticationSucceeded();
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
            if (fingerprintDialogSecureCallback != null) {
                fingerprintDialogSecureCallback.onNewFingerprintEnrolled(token);
            }
        }

        @Override
        public void onAuthenticationError(int errorCode, String error) {
            setStatus(error, statusErrorColor);
            handler.postDelayed(returnToScanning, delayAfterError);
        }
    };
}