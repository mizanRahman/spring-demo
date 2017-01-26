package org.haxagon.hmac.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by mac on 12/31/16.
 */
@Component
@Data
@ConfigurationProperties(prefix = "hmac")
public class HmacProperties {
    private boolean enabled=true;
}
