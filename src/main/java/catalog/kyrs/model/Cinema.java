package catalog.kyrs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cinemas")
public class Cinema {

    @Id
    @GeneratedValue(generator = "cinema_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "cinema_id_seq", sequenceName = "cinema_id_seq", allocationSize = 1)
    private Long id;

    @JoinColumn(name = "director_id")
    @ManyToOne
    private Director director;

    @JoinColumn(name = "screenwriter_id")
    @ManyToOne
    private Screenwriter screenwriter;

    private String cinemaName; //Название фильма

    private String cinemaDesc; //Описание фильма

    private String cinemaType; //Тип фильма (мультфильм, сериал ...)

    private Integer numberActors; //Количество актеров, которых необходимо найти

    private Double likeScore; //Количество лайков

}
