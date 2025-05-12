package org.example.web.mapper;

import org.apache.commons.collections4.CollectionUtils;
import org.example.model.Product;
import org.example.model.User;
import org.example.web.dto.ProductDto;
import org.example.web.dto.ProductShortDto;
import org.example.web.dto.UserDto;
import org.example.web.dto.UserShortDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * MapperService.
 *
 * @author Lina_Dautova
 */
@Service
public class MapperService {

    public ProductDto toProductDto(Product product) {
        return new ProductDto()
                .setId(product.getId())
                .setProductType(product.getProductType())
                .setBalance(product.getBalance())
                .setAccountNumber(product.getAccountNumber())
                .setUser(toUserShortDto(product.getUser()));
    }

    private UserShortDto toUserShortDto(User user) {
        return new UserShortDto()
                .setUserName(user.getUserName())
                .setId(user.getId());
    }

    public UserDto toUserDto(User user) {
        return new UserDto()
                .setId(user.getId())
                .setUserName(user.getUserName())
                .setProductList(toProductShortListDto(user.getProductList()));
    }

    private List<ProductShortDto> toProductShortListDto(List<Product> productList) {
        if (CollectionUtils.isEmpty(productList)) {
            return List.of();
        }
        return productList.stream()
                .map(this::toProductShortDto)
                .collect(Collectors.toList());
    }

    public ProductShortDto toProductShortDto(Product product) {
        return new ProductShortDto()
                .setId(product.getId())
                .setProductType(product.getProductType())
                .setBalance(product.getBalance())
                .setAccountNumber(product.getAccountNumber());
    }
}
