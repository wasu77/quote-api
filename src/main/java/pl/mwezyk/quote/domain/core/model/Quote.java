package pl.mwezyk.quote.domain.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "quotes")
@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @Column
    @Setter
    private String text;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    public Quote(String text, String firstName, String lastName) {
        this.text = text;
        this.firstName = firstName;
        this.lastName = lastName;
    }


}
