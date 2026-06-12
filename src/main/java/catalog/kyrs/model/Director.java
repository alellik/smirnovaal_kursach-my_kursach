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
@Table(name = "directors")
public class Director {

    @Id
    @GeneratedValue(generator = "director_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "director_id_seq", sequenceName = "director_id_seq", allocationSize = 1)
    private Long id;

    private String nameDirector; //Имя режиссера

    private String surnameDirector; //Фамилия режиссера

    public Director(String nameDirector, String surnameDirector) {
        this.nameDirector = nameDirector;
        this.surnameDirector = surnameDirector;
    }
}
