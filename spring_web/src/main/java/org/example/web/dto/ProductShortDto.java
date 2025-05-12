package org.example.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.example.model.ProductType;

@Accessors(chain = true)
@Getter
@Setter
public class ProductShortDto {
    private Long id;

    private String accountNumber;

    private Integer balance;

    private ProductType productType;
}
