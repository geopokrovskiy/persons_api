package com.geopokrovskiy.entity.user;

import com.geopokrovskiy.entity.address.AddressEntity;
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

@Table("person.individuals")
@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualUserEntity implements Persistable<UUID> {
    @Id
    private UUID id;
    @Column
    private UUID userId;
    @Column
    private String passportNumber;
    @Column
    private String phoneNumber;
    @Column
    private String email;
    private String firstName;
    private String lastName;
    @Column
    private UUID addressId;
    @Transient
    private AddressEntity address;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime archived;
    private LocalDateTime verified;
    private Boolean filled;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
