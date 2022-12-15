package YazGel1.StajTakip.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "stjtur")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","rapor"})
public class StjTur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "stj_tur_id")
    private Integer stjTurId;

    @Column(name = "stj_tur_ad")
    private String stjTurAd;

    @OneToMany(mappedBy = "stjTur")
    private List<Rapor> rapor;
}
