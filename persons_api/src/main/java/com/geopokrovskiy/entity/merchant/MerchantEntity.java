package com.geopokrovskiy.entity.merchant;

import com.geopokrovskiy.entity.status.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table("person.merchants")
public class MerchantEntity implements Persistable<UUID> {

    @Id
    private UUID id;
    @Column
    private String companyName;
    @Column
    private LocalDateTime created;
    @Column
    private LocalDateTime updated;
    @Column
    private String companyId;
    @Column
    private String email;
    @Column
    private String phoneNumber;
    @Column
    private LocalDateTime verifiedAt;
    @Column
    private LocalDateTime archivedAt;
    @Column
    private Status status;
    @Column
    private Boolean filled;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
