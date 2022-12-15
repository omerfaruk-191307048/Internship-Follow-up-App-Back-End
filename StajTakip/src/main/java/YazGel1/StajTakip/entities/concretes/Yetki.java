package YazGel1.StajTakip.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "yetki")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","hoca"})
public class Yetki {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "yetki_id")
    private Integer yetkiId;

    @Column(name = "yetki_adi")
    private String yetkiAdi;

    @OneToMany(mappedBy = "yetki")
    private List<Hoca> hoca;
}
