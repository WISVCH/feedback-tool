package ch.wisv.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class CaptchaService {

    private static Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

    @Getter
    @Value("${wisvch.recaptcha.secret}")
    private String secret;

    @Getter
    @Value("${wisvch.recaptcha.verifyURI}")
    private String verifyURI;

    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public CaptchaService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public boolean validateCaptcha(String clientResponse) {
        if (!responseSanityCheck(clientResponse)) {
            return false;
        }
        Map<String, String> body = new HashMap<>();
        body.put("secret", getSecret());
        body.put("response", clientResponse);

        ResponseEntity<Map> recaptchaResponseEntity = restTemplateBuilder.build()
                .postForEntity(getVerifyURI() + "?secret={secret}&response={response}", body, Map.class, body);

        Map<String, Object> responseBody = recaptchaResponseEntity.getBody();

        return (boolean) responseBody.get("success");
    }

    private boolean responseSanityCheck(String response) {
        return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
    }

}
