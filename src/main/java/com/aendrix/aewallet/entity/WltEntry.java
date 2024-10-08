package com.aendrix.aewallet.entity;

import com.aendrix.aewallet.dto.wallets.EntryDto;
import com.aendrix.aewallet.utils.DtoMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "wlt_entry")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class WltEntry implements DtoMapper<EntryDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "value")
    private Float value;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDateTime date;
    
    @Column(name = "deleted")
    private boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_wallet")
    private WltWallet wltWallet;

    @Override
    public EntryDto toDto() {
        return EntryDto.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .date(this.date)
                .value(this.value)
                .walletId(this.wltWallet.getId())
                .build();
    }
}
