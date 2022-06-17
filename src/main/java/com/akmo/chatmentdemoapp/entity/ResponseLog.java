package com.akmo.chatmentdemoapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long responseId;
    private String IP;
    private String message;
    private LocalDateTime dateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ResponseLog that = (ResponseLog) o;
        return responseId != null && Objects.equals(responseId, that.responseId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
