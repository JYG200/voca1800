package com.voca1800.entity.master;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "M_AUDIO_RESOURCE")
@Getter @Setter
@NoArgsConstructor
public class AudioResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audio_id")
    private Integer audioId;

    @Column(name = "reference_id", nullable = false)
    private Integer referenceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "reference_type", nullable = false)
    private AudioType referenceType;

    @Column(name = "audio_file_url", nullable = false)
    private String audioFileUrl;

    public enum AudioType {
        WORD, EXAMPLE
    }
}
