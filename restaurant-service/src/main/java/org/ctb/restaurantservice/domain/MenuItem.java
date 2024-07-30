package org.ctb.restaurantservice.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.ctb.common.CommonJsonMapperInitializer;
import org.ctb.common.Money;
import org.ctb.common.MoneyConverter;

@Embeddable
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class MenuItem {

    private String id;
    private String name;
    @Convert(converter = MoneyConverter.class )
    private Money price;

}
