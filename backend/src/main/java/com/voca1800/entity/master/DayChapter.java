package com.voca1800.entity.master;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "M_DAY_CHAPTER")
@Getter
@Setter
@NoArgsConstructor
public class DayChapter {
    @Id
    @Column(name = "chapter_id")
    private Integer chapterId;

    @Column(nullable = false)
    private String title;
}
