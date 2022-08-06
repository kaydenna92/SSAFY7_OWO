package com.sos.owo.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "rank")
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rank_id")
    private int recordId;

    @Column(name="member_id")
    private int member_id;

    @Column(name="rank_score")
    private int rank_score;

}
