package com.kway.support.domain.inquiry;

import com.kway.support.domain.BaseTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity(name = "inquiry")
public class Inquiry extends BaseTime {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Long id;
    private String type;
    private String email;
    private String title;
    private String contents;

    @Builder
    public Inquiry(Long id, String type, String email, String title, String contents) {
        this.id = id;
        this.type = type;
        this.email = email;
        this.title = title;
        this.contents = contents;
    }

    @Setter
    @Getter
    public static class Request {
        private Long id;
        private String type;
        private String email;
        private String title;
        private String contents;
        private String recaptchaValue;

        public Inquiry toEntity() {
            return Inquiry.builder()
                    .type(type)
                    .email(email)
                    .title(title)
                    .contents(contents)
                    .build();
        }
    }

    @Getter
    public static class Response {
        public final Long id;
        private final String type;
        private final String email;
        private final String title;
        private final String contents;

        public Response(Object o) {
            Inquiry inquiry = (Inquiry) o;
            this.id = inquiry.getId();
            this.type = inquiry.getType();
            this.email = inquiry.getEmail();
            this.title = inquiry.getTitle();
            this.contents = inquiry.getContents();
        }
    }
}
