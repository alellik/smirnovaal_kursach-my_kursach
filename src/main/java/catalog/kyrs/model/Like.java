package catalog.kyrs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(generator = "like_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "like_id_seq", sequenceName = "like_id_seq", allocationSize = 1)
    private Long Id;

    @JoinColumn(name = "cinema_id")
    @ManyToOne
    private Cinema cinema;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    private Integer valueOfLike; //Оценка

    private Date dateOfLike; //Дата оставления оценки
}
