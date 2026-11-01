package org.amhe.models.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Echange {
    @Id
    @SequenceGenerator(name = "echange_seq", sequenceName = "ECHANGE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "echange_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;
    private Item type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:s")
    private LocalDateTime timecode;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "inscrit_a_id")
    private Inscrit inscritA;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coup_a_id", referencedColumnName = "id")
    private Coup coupA;
    @Column(name = "score_a")
    private Long scoreA;

    @ManyToOne
    @JoinColumn(name = "inscrit_b_id")
    private Inscrit inscritB;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coup_b_id", referencedColumnName = "id")
    private Coup coupB;
    @Column(name = "score_b")
    private Long scoreB;


}
