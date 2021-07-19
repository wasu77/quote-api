package pl.mwezyk.quote.domain.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@EqualsAndHashCode
@NoArgsConstructor
public class Quote {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @Column
    @Setter
    private String text;
    @Column
    private String firstName;
    @Column
    private String lastName;

    public Quote(String text, String firstName, String lastName) {
        this.text = text;
        this.firstName = firstName;
        this.lastName = lastName;
    }


}
