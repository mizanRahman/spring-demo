package  com.konasl.epg.ep.security.hmac;

import com.konasl.epg.ep.filter.CachingRequestWrapper;
import com.konasl.epg.ep.security.ApiAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;

@Slf4j
@Service
public class HmacApiAuthenticationService implements ApiAuthenticationService {

    public static final String AUTH_SCHEME = "KONA-HMAC-SHA256";
    public static final String PAYLOAD_DIGEST_ALGORITHM = "SHA-256";

    @Override
    public boolean isAuthenticated(HttpServletRequest httpRequest) {
        String NL = System.lineSeparator();
        String stringToSign;
        try {
            stringToSign = new StringBuilder(AUTH_SCHEME)
                    .append(NL)
                    .append(httpRequest.getMethod())
                    .append(NL)
                    .append(httpRequest.getRequestURI())
                    .append(NL)
                    .append(httpRequest.getContentType() == null ? "" : httpRequest.getContentType())
                    .append(NL)
                    .append(
                            new URI(
                                    httpRequest.getQueryString() != null ? httpRequest.getQueryString() : "")
                                    .normalize())
                    .append(NL)
                    .append(
                            DatatypeConverter.printHexBinary(
                                    MessageDigest.getInstance(PAYLOAD_DIGEST_ALGORITHM)
                                            .digest(
                                                    IOUtils.toByteArray(
                                                            new CachingRequestWrapper(httpRequest).getInputStream()))))
                    .toString();

            log.info("String to Sign = {} ", stringToSign);

            String signature = calculateSignature("12345", stringToSign);
            log.info("hmac={}", signature);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public static String calculateSignature(String key, String data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        byte[] hmac = sha256_HMAC.doFinal(data.getBytes("UTF-8"));

        return DatatypeConverter.printBase64Binary(hmac);
    }
}
