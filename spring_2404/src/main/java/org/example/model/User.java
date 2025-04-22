package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * User.
 *
 * @author Lina_Dautova
 */

@Getter
@Setter
@Accessors(chain = true)
public class User {
    private Integer id;
    private String userName;
}
