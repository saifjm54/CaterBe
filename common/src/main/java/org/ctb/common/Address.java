package org.ctb.common;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Address {

    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zip;


}
