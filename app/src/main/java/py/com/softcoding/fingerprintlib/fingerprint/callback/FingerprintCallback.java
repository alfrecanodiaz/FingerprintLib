package py.com.softcoding.fingerprintlib.fingerprint.callback;

/**
 * Created by alfre on 03/06/18.
 */

public interface FingerprintCallback {
    void onAuthenticationSucceeded();
    void onAuthenticationFailed();
    void onAuthenticationError(int errorCode, String error);
}