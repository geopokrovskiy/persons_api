package com.geopokrovskiy.entity.address;

import com.geopokrovskiy.entity.country.CountryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("person.addresses")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AddressEntity implements Persistable<UUID> {
    @Id
    private UUID id;
    @Column
    private LocalDateTime created;
    @Column
    private LocalDateTime updated;
    @Column
    private int countryId;
    private String alpha3;
    @Transient
    private CountryEntity country;
    @Column
    private String address;
    @Column
    private String zipCode;
    @Column
    private LocalDateTime archived;
    @Column
    private String city;
    @Column
    private String state;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
