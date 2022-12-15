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
@Table(name = "hoca")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","rapor"})
public class Hoca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "hoca_id")
    private Integer hocaId;

    @Column(name = "hoca_ad")
    private String hocaAd;

    @Column(name = "hoca_soyad")
    private String hocaSoyad;

    @Column(name = "hoca_fakulte")
    private String hocaFakulte;

    @Column(name = "hoca_bolum")
    private String hocaBolum;

    @Column(name = "hoca_email")
    private String hocaEmail;

    @Column(name = "hoca_sifre")
    private String hocaSifre;

    @Column(name = "hoca_tel")
    private String hocaTel;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "yetki_id")
    private Yetki yetki;

    @OneToMany(mappedBy = "hoca")
    private List<Rapor> rapor;
}
