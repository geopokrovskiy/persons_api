package com.geopokrovskiy.entity.invitation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("person.merchant_members_invitations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class InvitationEntity implements Persistable<UUID> {
    @Id
    private UUID id;
    @Column
    private LocalDateTime created;
    @Column
    private LocalDateTime expires;
    @Column
    private UUID merchantId;
    @Column
    private String email;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private Status status;


    @Override
    public boolean isNew() {
        return id == null;
    }
}
