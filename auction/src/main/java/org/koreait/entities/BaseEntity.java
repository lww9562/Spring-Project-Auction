package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime regDt;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(insertable = false)
	private LocalDateTime modDt;
}
