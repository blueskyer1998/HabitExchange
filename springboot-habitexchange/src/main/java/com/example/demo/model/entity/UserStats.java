package com.example.demo.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity name : UserStats
 * Table name  : user_stats
 *
 * +---------+-------------+----------------+---------------------+
 * | user_id | total_coins | total_checkins |     updated_at      |
 * +---------+-------------+----------------+---------------------+
 * |    1    |   1500.75   |     365.00     | 2025-06-12 15:00:00 |
 * |    2    |    250.00   |      12.00     | 2025-06-12 15:00:00 |
 * +---------+-------------+----------------+---------------------+
 *
 * 欄位說明
 * ───────────────────────────────────────────────────────────────────────────────
 * user_id         BIGINT          PK, FK → users(id)            對應使用者 ID
 * total_coins     DECIMAL(12,2)   NOT NULL                      使用者累積金幣
 * total_checkins  DECIMAL(12,2)   NOT NULL                      使用者累積完成打卡次數
 * updated_at      DATETIME         ON UPDATE CURRENT_TIMESTAMP  最後彙總時間
 * ───────────────────────────────────────────────────────────────────────────────
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_stats")
public class UserStats {

    // 使用者Id: 主鍵／外鍵 BIGINT  PK  (FK → users.id)
    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    // 使用者累積金幣：DECIMAL(12,2)  NOT NULL
    @Column(name = "total_coins", precision = 12, scale = 2, nullable = false)
    private BigDecimal totalCoins;

    // 使用者累積完成打卡次數：DECIMAL(12,2) NOT NULL
    @Column(name = "total_checkins", precision = 12, scale = 2, nullable = false)
    private BigDecimal totalCheckins;

    // 最後彙總時間：DATETIME  ON UPDATE CURRENT_TIMESTAMP
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
