package org.example.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * UserDto.
 *
 * @author Lina_Dautova
 */
@Accessors(chain = true)
@Getter
@Setter
public class UserDto {
    private Long id;

    private String userName;

    private List<ProductShortDto> productList;
}
