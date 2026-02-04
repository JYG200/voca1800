package com.voca1800.entity.master;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "M_WORD_MASTER")
@Getter 
@Setter 
@NoArgsConstructor
public class WordMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_id")
    private Integer wordId;

    @Column(name = "word_english", nullable = false)
    private String wordEnglish;

    @Column(name = "meaning_korean", nullable = false)
    private String meaningKorean;

    @Column(name = "meaning_japanese", nullable = false)
    private String meaningJapanese;

    @Column(name = "difficulty_level", nullable = false)
    private Integer difficultyLevel = 1;
	
}
