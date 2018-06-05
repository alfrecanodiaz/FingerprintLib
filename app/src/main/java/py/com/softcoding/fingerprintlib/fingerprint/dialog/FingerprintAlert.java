package py.com.softcoding.fingerprintlib.fingerprint.dialog;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;

/**
 * Created by Alfredo Cano on 05/06/18.
 */

public class FingerprintAlert extends FingerprintDialog<FingerprintAlert> {
    private String info;
    private String action;

    private OnClickActionListener onClickActionListener;

    private final static String TAG = "FingerprintAlert";

    private FingerprintAlert(Context context) {
        super(context);
        init();
    }

    private void init() {

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
    public static FingerprintAlert initialize(Context context) {
        return new FingerprintAlert(context);
    }

    public FingerprintAlert info(String info) {
        this.info = info;
        return this;
    }

    public FingerprintAlert action(String action, OnClickActionListener onClickActionListener) {
        this.action = action;
        this.onClickActionListener = onClickActionListener;
        return this;
    }

    public interface OnClickActionListener {
        void onClick();
    }
}
