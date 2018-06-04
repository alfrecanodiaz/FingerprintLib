package py.com.softcoding.fingerprintlib.fingerprint.utils;

/**
 * Created by Alfredo Cano on 03/06/18.
 */

public class FingerprintToken {
    private CipherHelper cipherHelper;

    public FingerprintToken(CipherHelper cipherHelper) {
        this.cipherHelper = cipherHelper;
    }

    public void validate() {
        if (cipherHelper != null) {
            cipherHelper.generateNewKey();
        }
    }
}