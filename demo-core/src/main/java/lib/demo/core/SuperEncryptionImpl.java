package lib.demo.core;

/**
 * Created by mac on 8/6/16.
 */
public class SuperEncryptionImpl implements SuperEncryption {

    @Override
    public String encrypt(String s) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            sb.append((char) (c+1));
        }

        return sb.toString();
    }

    @Override
    public String decrypt(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            sb.append((char) (c-1));
        }
        return sb.toString();
    }
}
