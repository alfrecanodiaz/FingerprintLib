package py.com.softcoding.fingerprintlib.fingerprint.callback;

/**
 * Created by alfre on 03/06/18.
 */

public interface FingerprintDialogCallback {
    void onAuthenticationSucceeded();
    void onAuthenticationCancel();
}