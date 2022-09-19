package com.kway.support.web;

import com.kway.support.domain.inquiry.Inquiry;
import com.kway.support.message.RestMessage;
import com.kway.support.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/support")
@RequiredArgsConstructor
@RestController
public class InquiryController {
    private final InquiryService inquiryService;

    @GetMapping("/list")
    public ResponseEntity<RestMessage> findAll(Inquiry.Request request,
                                               @RequestParam(required = false, defaultValue = "0") Integer page,
                                               @RequestParam(required = false, defaultValue = "8") Integer pageSize) {
        return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .body(new RestMessage(HttpStatus.OK, inquiryService.findAll(request, page, pageSize)));
    }

    @GetMapping("/save")
    public ResponseEntity<RestMessage> save(Inquiry.Request request) {
        return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .body(new RestMessage(HttpStatus.OK, inquiryService.save(request)));
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
