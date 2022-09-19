package com.kway.support.service;

import com.kway.support.domain.support.QSupport;
import com.kway.support.domain.support.Support;
import com.kway.support.domain.support.SupportRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SupportService {

    private final SupportRepository supportRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private QSupport support = QSupport.support;

    public BooleanExpression eqType(String type) {
        if (!StringUtils.hasText(type)) return null;
        return support.type.eq(type);
    }

    public BooleanExpression containsTitle(String title) {
        if (!StringUtils.hasText(title)) return null;
        return support.type.containsIgnoreCase(title);
    }

    public BooleanExpression containsContents(String contents) {
        if (!StringUtils.hasText(contents)) return null;
        return support.type.containsIgnoreCase(contents);
    }

    public Long save(Support.Request request) {
        return supportRepository.save(request.toEntity()).getId();
    }

    public HashMap<String, Object> findAll(Support.Request request, Integer page, Integer pageSize) {
        HashMap<String, Object> resultMap = new HashMap<>();

        List<Support.Response> list = jpaQueryFactory.from(support)
            .where (
                eqType(request.getType()),
                containsTitle(request.getTitle()),
                containsContents(request.getContents())
            )
            .offset(page)
            .limit(pageSize)
            .orderBy(support.registerTime.desc())
            .fetch()
            .stream()
            .map(Support.Response::new)
            .collect(Collectors.toList());

        resultMap.put("list", list);

        return resultMap;
    }

    public Long updateSupport(Support.Request request) {
        return jpaQueryFactory.update(support)
            .set(support.title, request.getTitle())
            .set(support.contents, request.getContents())
            .execute();
    }

    public Long deleteSupport(Support.Request request) {
        return jpaQueryFactory.delete(support)
            .where(support.id.in(request.getId()))
            .execute();
    }
}
