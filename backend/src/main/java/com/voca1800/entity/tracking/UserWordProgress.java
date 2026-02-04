package com.voca1800.entity.tracking;
import com.voca1800.entity.master.User;
import com.voca1800.entity.master.WordMaster;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
@Entity
@Table(name = "T_USER_WORD_PROGRESS")
@Getter @Setter
@NoArgsConstructor
public class UserWordProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_id")
    private Integer progressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "word_id", nullable = false)
    private WordMaster wordMaster;

    @Column(name = "box_number", nullable = false)
    private Integer boxNumber;

    @Column(name = "is_memorized", nullable = false)
    private Boolean isMemorized;

    @Column(name = "next_review_date", nullable = false)
    private LocalDate nextReviewDate;
}
