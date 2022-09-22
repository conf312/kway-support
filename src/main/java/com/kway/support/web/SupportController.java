package com.kway.support.web;

import com.kway.support.domain.support.Support;
import com.kway.support.message.RestMessage;
import com.kway.support.service.SupportService;
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
public class SupportController {

    private final SupportService supportService;

    @GetMapping("/list")
    public ResponseEntity<RestMessage> findAll(Support.Request request,
                                               @RequestParam(required = false, defaultValue = "0") Integer page,
                                               @RequestParam(required = false, defaultValue = "8") Integer pageSize) {
        return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .body(new RestMessage(HttpStatus.OK, supportService.findAll(request, page, pageSize)));
    }

    @GetMapping("/save")
    public ResponseEntity<RestMessage> save(Support.Request request) {
        return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .body(new RestMessage(HttpStatus.OK, supportService.save(request)));
    }

    @GetMapping("/update-support")
    public ResponseEntity<RestMessage> updateSupport(Support.Request request) {
        return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .body(new RestMessage(HttpStatus.OK, supportService.updateSupport(request)));
    }

    @GetMapping("/delete")
    public ResponseEntity<RestMessage> deleteSupport(Support.Request request) {
        return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .body(new RestMessage(HttpStatus.OK, supportService.deleteSupport(request)));
    }
}
