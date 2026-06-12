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
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(generator = "comment_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "comment_id_seq", sequenceName = "comment_id_seq", allocationSize = 1)
    private Long id;

    @JoinColumn(name = "cinema_id")
    @ManyToOne
    private Cinema cinema;

    private String textOfComment; //Текст комментария

    private Date dateOfComment; //Дата оставления комментария
}
