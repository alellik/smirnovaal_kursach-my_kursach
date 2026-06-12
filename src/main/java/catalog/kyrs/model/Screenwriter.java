package catalog.kyrs.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "screenwriters")
public class Screenwriter {

    @Id
    @GeneratedValue(generator = "screenwriter_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "screenwriter_id_seq", sequenceName = "screenwriter_id_seq", allocationSize = 1)
    private Long id;

    private String nameScreenwriter; //Имя сценариста

    private String surnameScreenwriter; //Фамилия сценариста

    public Screenwriter(String nameScreenwriter, String surnameScreenwriter) {
        this.nameScreenwriter = nameScreenwriter;
        this.surnameScreenwriter = surnameScreenwriter;
    }
}
