package com.voca1800.entity.tracking;
import com.voca1800.entity.master.User;
import com.voca1800.entity.master.WordMaster;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_TODAY_LEARNING_LOOP")
@Getter @Setter
@NoArgsConstructor
public class TodayLearningLoop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loop_id")
    private Integer loopId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "word_id", nullable = false)
    private WordMaster wordMaster;

    @Column(name = "is_learned_today", nullable = false)
    private Boolean isLearnedToday = false;

    @Column(name = "today_fail_count", nullable = false)
    private Integer todayFailCount = 0;

    @Column(name = "last_attempt_at")
    private LocalDateTime lastAttemptAt;
}
