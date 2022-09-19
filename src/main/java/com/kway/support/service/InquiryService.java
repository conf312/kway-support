package com.kway.support.service;

import com.kway.support.domain.inquiry.Inquiry;
import com.kway.support.domain.inquiry.InquiryRepository;
import com.kway.support.domain.inquiry.QInquiry;
import com.kway.support.domain.support.Support;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InquiryService {
    private final InquiryRepository inquiryRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private QInquiry inquiry = QInquiry.inquiry;

    public BooleanExpression eqType(String type) {
        if (!StringUtils.hasText(type)) return null;
        return inquiry.type.eq(type);
    }

    public BooleanExpression containsEmail(String email) {
        if (!StringUtils.hasText(email)) return null;
        return inquiry.email.containsIgnoreCase(email);
    }

    public BooleanExpression containsTitle(String title) {
        if (!StringUtils.hasText(title)) return null;
        return inquiry.title.containsIgnoreCase(title);
    }

    public BooleanExpression containsContents(String contents) {
        if (!StringUtils.hasText(contents)) return null;
        return inquiry.contents.containsIgnoreCase(contents);
    }

    public Long save(Inquiry.Request request) {
        return inquiryRepository.save(request.toEntity()).getId();
    }

    public HashMap<String, Object> findAll(Inquiry.Request request, Integer page, Integer pageSize) {
        HashMap<String, Object> resultMap = new HashMap<>();

        List<Support.Response> list = jpaQueryFactory.from(inquiry)
            .where (
                eqType(request.getType()),
                containsEmail(request.getEmail()),
                containsTitle(request.getTitle()),
                containsContents(request.getContents())
            )
            .offset(page)
            .limit(pageSize)
            .orderBy(inquiry.registerTime.desc())
            .fetch()
            .stream()
            .map(Support.Response::new)
            .collect(Collectors.toList());

        resultMap.put("list", list);

        return resultMap;
    }

    public Long updateInquiry(Inquiry.Request request) {
        return jpaQueryFactory.update(inquiry)
            .set(inquiry.title, request.getTitle())
            .set(inquiry.contents, request.getContents())
            .set(inquiry.modifyTime, LocalDateTime.now())
            .execute();
    }

    public Long deleteInquiry(Inquiry.Request request) {
        return jpaQueryFactory.delete(inquiry)
            .where(inquiry.id.in(request.getId()))
            .execute();
    }
}
