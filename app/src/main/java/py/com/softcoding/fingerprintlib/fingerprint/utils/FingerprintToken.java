package py.com.softcoding.fingerprintlib.fingerprint.utils;

/**
 * Created by Alfredo Cano on 03/06/18.
 */

public class FingerprintToken {
    private final static String TAG = "FingerprintToken";

    private CipherHelper cipherHelper;

    public FingerprintToken(CipherHelper cipherHelper) {
        this.cipherHelper = cipherHelper;
    }

    public void validate() {
        if (cipherHelper != null) {
            cipherHelper.generateNewKey();
        }
    }

    public boolean exists() {
        // logic
        // return from SharedPreferences
        return true;
    }

    public void store(String value) {
        // logic
        // save in SharedPrefences
        // Fingerprint.Keys.FINGERPRINT_TOKEN - cipherHelper.encrypt(value)
    }

    public String retrieve() {
        // logic
        // retrieve from SharedPreferences
        // Fingerprint.Keys.FINGERPRINT_TOKEN - cipherHelper.decrypt(value) -> return
        return "";
    }
}