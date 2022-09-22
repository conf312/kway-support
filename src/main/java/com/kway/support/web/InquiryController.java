package com.kway.support.web;

import com.kway.support.domain.inquiry.Inquiry;
import com.kway.support.message.RestMessage;
import com.kway.support.service.InquiryService;
import com.kway.support.util.HttpApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequestMapping("/inquiry")
@RequiredArgsConstructor
@RestController
public class InquiryController {
    @Value("${api.endpoint.recaptchaSiteVerify}")
    private String recaptchaSiteVerifyEndpoint;
    @Value("${api.secret.recaptcha}")
    private String secretRecaptcha;

    private final InquiryService inquiryService;

    @GetMapping("/list")
    public ResponseEntity<RestMessage> findAll(Inquiry.Request request,
                                               @RequestParam(required = false, defaultValue = "0") Integer page,
                                               @RequestParam(required = false, defaultValue = "8") Integer pageSize) {
        return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .body(new RestMessage(HttpStatus.OK, inquiryService.findAll(request, page, pageSize)));
    }

    @PostMapping("/save")
    public ResponseEntity<RestMessage> save(Inquiry.Request request) throws Exception {
        String url = recaptchaSiteVerifyEndpoint + "?secret=" + secretRecaptcha + "&response=" + request.getRecaptchaValue();
        HashMap<String, Object> recaptchaMap = new HttpApiUtil().getDataFromJson(
                url, "UTF-8", "post", "", "application/x-www-form-urlencoded");
        boolean recaptchaResult = (boolean) recaptchaMap.get("success");
        Long resultSave = 0L;

        if (recaptchaResult) {
            resultSave = inquiryService.save(request);
        }

        return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .body(new RestMessage(HttpStatus.OK, resultSave));
    }

    @GetMapping("/update-inquiry")
    public ResponseEntity<RestMessage> updateInquiry(Inquiry.Request request) {
        return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .body(new RestMessage(HttpStatus.OK, inquiryService.updateInquiry(request)));
    }

    @GetMapping("/delete")
    public ResponseEntity<RestMessage> deleteInquiry(Inquiry.Request request) {
        return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .body(new RestMessage(HttpStatus.OK, inquiryService.deleteInquiry(request)));
    }
}
