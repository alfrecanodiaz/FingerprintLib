package py.com.softcoding.fingerprintlib.fingerprint.dialog;

import android.content.Context;

import py.com.softcoding.fingerprintlib.fingerprint.facade.Fingerprint;

/**
 * Created by Alfredo Cano on 05/06/18.
 */

public class FingerprintAlert extends FingerprintDialog<FingerprintAlert> {
    private final static String TAG = "FingerprintAlert";

    private String info;
    private String action;

    private OnClickActionListener onClickActionListener;

    private FingerprintAlert(Context context) {
        super(context);
        this.info = "";
        this.action = "";
        this.onClickActionListener = null;
        init();
    }

    private void init() {
        if (info.isEmpty()) {

        }

        if (action.isEmpty()) {

        }
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
     * Create a FingerprintAlert instance.
     * @param context Activity Context
     * @return FingerprintAlert instance
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

    public FingerprintAlert info(int resInfo) {
        this.info = context.getResources().getString(resInfo);
        return this;
    }

    public FingerprintAlert action(int resAction, OnClickActionListener onClickActionListener) {
        this.action = context.getResources().getString(resAction);
        this.onClickActionListener = onClickActionListener;
        return this;
    }

    public interface OnClickActionListener {
        void onClick();
    }
}
