package org.ctb.orderservice.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.ctb.common.Money;

@Embeddable
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class MenuItem {

    private String id;
    private String name;
    private Money price;

}
