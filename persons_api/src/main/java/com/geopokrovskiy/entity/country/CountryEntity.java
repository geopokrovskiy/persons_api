package com.geopokrovskiy.entity.country;

import io.r2dbc.spi.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table("person.countries")
public class CountryEntity implements Persistable<Integer> {
    @Id
    @Column("id")
    private Integer id;
    @Column
    private LocalDateTime created;
    @Column
    private LocalDateTime updated;
    @Column
    private String name;
    @Column
    private String alpha2;
    @Column
    private String alpha3;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
