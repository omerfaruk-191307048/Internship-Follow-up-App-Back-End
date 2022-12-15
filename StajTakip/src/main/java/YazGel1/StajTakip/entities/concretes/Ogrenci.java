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
@Table(name = "ogrenci")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","rapor"})
public class Ogrenci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increase
    @NotNull
    @Column(name = "ogr_no")
    private Integer ogrNo;

    @Column(name = "ogr_ad")
    private String ogrAd;

    @Column(name = "ogr_soyad")
    private String ogrSoyad;

    @Column(name = "ogr_okul")
    private String ogrOkul;

    @Column(name = "ogr_fakulte")
    private String ogrFakulte;

    @Column(name = "ogr_bolum")
    private String ogrBolum;

    @Column(name = "ogr_sinif")
    private String ogrSinif;

    @Column(name = "ogr_email")
    private String ogrEmail;

    @Column(name = "ogr_sifre")
    private String ogrSifre;

    @Column(name = "ogr_tel")
    private String ogrTel;

    @OneToMany(mappedBy = "ogrenci")
    private List<Rapor> rapor;
}
