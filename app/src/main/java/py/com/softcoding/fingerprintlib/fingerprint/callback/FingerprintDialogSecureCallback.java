package py.com.softcoding.fingerprintlib.fingerprint.callback;

import py.com.softcoding.fingerprintlib.fingerprint.utils.FingerprintToken;

/**
 * Created by Alfredo Cano on 03/06/18.
 */

public interface FingerprintDialogSecureCallback {
    void onAuthenticationSucceeded();
    void onAuthenticationCancel();
    void onNewFingerprintEnrolled(FingerprintToken token);
}