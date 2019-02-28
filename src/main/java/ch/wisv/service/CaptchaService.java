package ch.wisv.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@ConfigurationProperties(prefix = "wisvch.recaptcha")
public class CaptchaService {

    private static Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

    @Getter
    private String secret;

    @Getter
    private String verifyURI;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public CaptchaService() {}

    public boolean validateCaptcha(String clientResponse) {
        if (!responseSanityCheck(clientResponse)) {
            return false;
        }
        Map<String, String> body = new HashMap<>();
        body.put("secret", secret);
        body.put("response", clientResponse);

        ResponseEntity<Map> recaptchaResponseEntity = restTemplateBuilder.build()
                .postForEntity(verifyURI + "?secret={secret}&response={response}", body, Map.class, body);

        Map<String, Object> responseBody = recaptchaResponseEntity.getBody();

        return (boolean) responseBody.get("success");
    }

    private boolean responseSanityCheck(String response) {
        return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
    }

}
