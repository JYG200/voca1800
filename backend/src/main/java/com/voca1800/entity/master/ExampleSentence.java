package com.voca1800.entity.master;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "M_EXAMPLE_SENTENCE")
@Getter
@Setter
@NoArgsConstructor
public class ExampleSentence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "example_id")
    private Integer exampleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "word_id", nullable = false)
    private WordMaster wordMaster;

    @Column(name = "sentence_english", nullable = false)
    private String sentenceEnglish;

    @Column(name = "sentence_korean", nullable = false)
    private String sentenceKorean;

    @Column(name = "grammar_point")
    private String grammarPoint;
}