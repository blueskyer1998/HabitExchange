package com.example.demo.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity name : RedemptionLog
 * Table name  : redemption_log
 * +----+---------+-------------+------+-------------+---------------------+
 * | id | user_id | wishlist_id | note | coins_spent |     created_at      |
 * +----+---------+-------------+------+-------------+---------------------+
 * | .. |    ..   |      ..     | ...  |    20.00    | 2025-06-12 10:00:00 |
 * +----+---------+-------------+------+-------------+---------------------+
 *
 * 欄位說明
 * ──────────────────────────────────────────────────────────────────────────
 * id           BIGINT          PK AUTO_INCREMENT        兌換紀錄 ID
 * user_id      BIGINT          NOT NULL, FK → users(id) 發起兌換的使用者 ID
 * wishlist_id  BIGINT          NOT NULL, FK → wishlist(id) 對應願望項目 ID
 * note         TEXT            NULL                     心情筆記
 * coins_spent  DECIMAL(12,2)   DEFAULT 0                實際花費金幣
 * created_at   DATETIME        DEFAULT CURRENT_TIMESTAMP 建立時間 (兌換時間)
 * ──────────────────────────────────────────────────────────────────────────
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "redemption_log")
public class RedemptionLog {

    // 主鍵：BIGINT  PK AUTO_INCREMENT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 發起兌換的使用者 ID：BIGINT  NOT NULL  (FK → users.id)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    // 對應願望項目 ID：BIGINT  NOT NULL  (FK → wishlist.id)
    @Column(name = "wishlist_id", nullable = false)
    private Long wishlistId;

    // 心情筆記：TEXT  NULL
    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    // 實際花費金幣：DECIMAL(12,2)  DEFAULT 0
    @Column(name = "coins_spent", precision = 12, scale = 2, columnDefinition = "DECIMAL(12,2) DEFAULT 0")
    private BigDecimal coinsSpent = BigDecimal.ZERO;

    // 建立時間 (兌換時間)：DATETIME  DEFAULT CURRENT_TIMESTAMP
    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;
}
