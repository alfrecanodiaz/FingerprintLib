package py.com.softcoding.fingerprintlib.fingerprint.callback;

import py.com.softcoding.fingerprintlib.fingerprint.utils.FingerprintToken;

/**
 * Created by Alfredo Cano on 03/06/18.
 */

public interface FingerprintSecureCallback {
    void onAuthenticationSucceeded();
    void onAuthenticationFailed();
    void onNewFingerprintEnrolled(FingerprintToken token);
    void onAuthenticationError(int errorCode, String error);
}