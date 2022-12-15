package YazGel1.StajTakip.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "nott")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","rapor"})
public class Nott {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "nott_id")
    private Integer nottId;

    @Column(name = "nott_adi")
    private String nottAdi;

    @OneToMany(mappedBy = "nott")
    private List<Rapor> rapor;

}
