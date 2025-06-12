package com.example.demo.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.demo.model.enums.Frequency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity name : Wishlist
 * Table name  : wishlist
 * +----+---------+-----------+-------------+-----------+--------------+-------------+----------------+----------------+--------------+-------------+---------------------+---------------------+
 * | id | user_id |   name    | description | frequency | target_times | period_days | required_coins | is_require_note| redeemed_cnt | is_archived |     updated_at      |     created_at      |
 * +----+---------+-----------+-------------+-----------+--------------+-------------+----------------+----------------+--------------+-------------+---------------------+---------------------+
 * | .. |    ..   |   ..      |     ..      |   once    |      1       |     ..      |      50.00     |       0        |       0      |      0      | 2025-06-12 15:00:00 | 2025-06-12 09:00:00 |
 * +----+---------+-----------+-------------+-----------+--------------+-------------+----------------+----------------+--------------+-------------+---------------------+---------------------+
 *
 * 欄位說明
 * ──────────────────────────────────────────────────────────────────────────────────────────────────────────────
 * id              BIGINT          PK AUTO_INCREMENT                      願望 ID
 * user_id         BIGINT          NOT NULL, FK → users(id)              所屬使用者 ID
 * name            VARCHAR(50)     UK (user_id+name), NOT NULL           願望名稱
 * description     TEXT            NULL                                  願望說明
 * frequency       ENUM            DEFAULT 'once'                        兌換週期
 * target_times    INT             DEFAULT 1                             週期內可兌換次數
 * period_days     INT             NULL                                  自定義週期長度（天）
 * required_coins  DECIMAL(12,2)   NOT NULL                              所需金幣
 * is_require_note TINYINT(1)      DEFAULT 0                             是否需紀錄心情筆記
 * redeemed_count  INT             DEFAULT 0                             已兌換次數
 * is_archived     TINYINT(1)      DEFAULT 0                             是否已結束
 * updated_at      DATETIME        ON UPDATE CURRENT_TIMESTAMP           最後更新時間
 * created_at      DATETIME        DEFAULT CURRENT_TIMESTAMP             建立時間
 * ──────────────────────────────────────────────────────────────────────────────────────────────────────────────
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wishlist")
public class Wishlist {

    // ─── 主鍵 ───────────────────────────────────────────────
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 所屬使用者 ID：BIGINT  NOT NULL  (FK → users.id)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    // 願望名稱：VARCHAR(50)  NOT NULL  (UK: user_id + name)
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    // 願望說明：TEXT  NULL
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // 兌換週期：ENUM(...)  DEFAULT 'once'
    @Enumerated(EnumType.STRING)
    @Column(
        name = "frequency",
        columnDefinition = "ENUM('daily','weekly','monthly','once','unlimited','custom') DEFAULT 'once'"
    )
    private Frequency frequency = Frequency.ONCE;

    // 週期內可兌換次數：INT  DEFAULT 1
    @Column(name = "target_times", columnDefinition = "INT DEFAULT 1")
    private Integer targetTimes = 1;

    // 自定義週期長度（天）：INT  NULL
    @Column(name = "period_days")
    private Integer periodDays;

    // 所需金幣：DECIMAL(12,2)  NOT NULL
    @Column(name = "required_coins", precision = 12, scale = 2, nullable = false)
    private BigDecimal requiredCoins;

    // 是否需紀錄心情筆記：TINYINT(1)  DEFAULT 0
    @Column(name = "is_require_note", columnDefinition = "TINYINT(1) DEFAULT 0", nullable = false)
    private Boolean isRequireNote = false;

    // 已兌換次數：INT  DEFAULT 0
    @Column(name = "redeemed_count", columnDefinition = "INT DEFAULT 0", nullable = false)
    private Integer redeemedCount = 0;

    // 是否結束：TINYINT(1)  DEFAULT 0
    @Column(name = "is_archived", columnDefinition = "TINYINT(1) DEFAULT 0", nullable = false)
    private Boolean isArchived = false;

    // 最後更新時間：DATETIME  自動更新
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // 建立時間：DATETIME  自動填入
    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;
}
