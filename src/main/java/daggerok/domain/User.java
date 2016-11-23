package daggerok.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = -5252172190065816437L;

    @Id @GeneratedValue Long id;
    String username;
}
