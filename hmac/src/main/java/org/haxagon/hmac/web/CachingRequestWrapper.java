package org.haxagon.hmac.web;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * Created by mac on 11/27/16.
 */
public class CachingRequestWrapper extends HttpServletRequestWrapper {


    private ByteArrayOutputStream cachedBytes = null;

    public CachingRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (cachedBytes == null) {
            cachedBytes = new ByteArrayOutputStream();
            IOUtils.copy(super.getInputStream(), cachedBytes);
        }
        return new CachedServletInputStream();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public byte[] getContentAsByteArray() {
        try {
            return IOUtils.toByteArray(this.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class CachedServletInputStream extends ServletInputStream {

        ByteArrayInputStream inputStream;

        public CachedServletInputStream() {
            this.inputStream = new ByteArrayInputStream(cachedBytes.toByteArray());
        }

        @Override
        public boolean isFinished() {
            return true;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {

        }

        @Override
        public int read() throws IOException {
            return inputStream.read();
        }
    }
}
