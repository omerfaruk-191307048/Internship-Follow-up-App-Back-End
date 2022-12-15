package YazGel1.StajTakip.entities.concretes;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "rapor")
@AllArgsConstructor
@NoArgsConstructor
public class Rapor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increase
    @NotNull
    @Column(name = "rapor_id")
    private Integer raporId;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "ogr_no")
    private Ogrenci ogrenci;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "hoca_id")
    private Hoca hoca;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "durum_id")
    private Durum durum;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "not_id")
    private Nott nott;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "stj_tur_id")
    private StjTur stjTur;

    @Column(name = "stj_bas")
    private String stjBas;

    @Column(name = "stj_bit")
    private String stjBit;

    @Column(name = "stj_gun")
    private String stjGun;

    @Column(name = "stj_firma")
    private String stjFirma;

    @Column(name = "stj_donem")
    private String stjDonem;

    @Column(name = "rapor_path")
    private String raporPath;
}
