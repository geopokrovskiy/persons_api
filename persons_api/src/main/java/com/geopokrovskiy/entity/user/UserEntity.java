package com.geopokrovskiy.entity.user;

import com.geopokrovskiy.entity.address.AddressEntity;
import com.geopokrovskiy.entity.status.Status;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table("person.users")
public class UserEntity implements Persistable<UUID> {
    @Id
    private UUID id;
    @Column
    private String secretKey;
    @Column
    private LocalDateTime created;
    @Column
    private LocalDateTime updated;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private LocalDateTime verifiedAt;
    @Column
    private LocalDateTime archivedAt;
    @Column
    private Status status;
    @Column
    private boolean filled;
    @Column
    private UUID addressId;
    @Transient
    private AddressEntity address;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
