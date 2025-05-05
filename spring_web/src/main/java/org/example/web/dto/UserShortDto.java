package org.example.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * UserDto.
 *
 * @author Lina_Dautova
 */
@Accessors(chain = true)
@Getter
@Setter
public class UserShortDto {
    private Long id;

    private String userName;
}
