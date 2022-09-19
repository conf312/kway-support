package com.kway.support.domain.support;

import com.kway.support.domain.BaseTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.function.Function;

@DynamicInsert
@Getter
@NoArgsConstructor
@Entity(name = "support")
public class Support extends BaseTime {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String type;
    private String title;
    private String contents;
    private int readCnt;

    @Builder
    public Support(Long id, String type, String title, String contents, int readCnt) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.contents = contents;
        this.readCnt = readCnt;
    }

    @Setter
    @Getter
    public static class Request {
        private Long id;
        private String type;
        private String title;
        private String contents;

        public Support toEntity() {
            return Support.builder()
                    .type(type)
                    .title(title)
                    .contents(contents)
                    .build();
        }
    }

    @Getter
    public static class Response {
        private final Long id;
        private final String type;
        private final String title;
        private final String contents;
        private final int readCnt;

        public Response(Object o) {
            Support support = (Support) o;
            this.id = support.getId();
            this.type = support.getType();
            this.title = support.getTitle();
            this.contents = support.getContents();
            this.readCnt = support.getReadCnt();
        }
    }
}
