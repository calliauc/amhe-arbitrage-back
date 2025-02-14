package org.amhe.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Tag implements Serializable {
    @Id
    @SequenceGenerator(name = "tag_seq", sequenceName = "TAG_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_seq")
    private Long id;
    private String code;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) && Objects.equals(code, tag.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }
}
