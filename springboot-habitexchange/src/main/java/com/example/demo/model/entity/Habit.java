package com.example.demo.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.demo.model.enums.Frequency;
import com.example.demo.model.enums.Weekday;

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
 * Entity name : Habit
 * Table name  : habit
 *
 * +----+---------+-----------+----------------------+---------+-----------+--------------+-------------+---------------+--------------+------------+------------+---------------+----------------+-------------+---------------------+---------------------+
 * | id | user_id |   name    |     description      |   tag   | frequency | target_times | period_days | plan_weekdays | reward_coins | start_date | end_date   | penalty_coins | is_require_note | is_archived |     updated_at      |     created_at      |
 * +----+---------+-----------+----------------------+---------+-----------+--------------+-------------+---------------+--------------+------------+------------+---------------+----------------+-------------+---------------------+---------------------+
 * | 1  |    1    | Reading   | 30 min reading daily | health  |  daily    |      1       |      7      | Mon,Wed,Fri   |     10.00    | 2025-06-01 | 2025-06-30 |      5.00     |       0        |      0      | 2025-06-03 13:00:00 | 2025-06-01 08:00:00 |
 * | 2  |    2    | Running   | 5 km run             | fitness |  weekly   |      3       |      7      | Tue,Thu,Sat   |     15.00    | 2025-06-01 | 2025-09-01 |     10.00     |       1        |      0      | 2025-06-03 13:00:00 | 2025-06-01 08:00:00 |
 * +----+---------+-----------+----------------------+---------+-----------+--------------+-------------+---------------+--------------+------------+------------+---------------+----------------+-------------+---------------------+---------------------+
 *
 * 欄位說明
 * ───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────
 * id              BIGINT          PK AUTO_INCREMENT                                             習慣 ID
 * user_id         BIGINT          NOT NULL  FK → user(id)                                       所屬使用者 ID
 * name            VARCHAR(50)     UNIQUE(user_id+name)  NOT NULL                                習慣名稱
 * description     TEXT            NULL                                                          習慣說明
 * tag             VARCHAR(50)     NULL                                                          分組標籤
 * frequency       ENUM            DEFAULT 'daily'                                               習慣頻率 (daily / weekly / monthly / once / unlimited / custom)
 * target_times    INT             DEFAULT 1                                                     週期內預期完成次數
 * period_days     INT             DEFAULT 1                                                     週期長度 (自定義)
 * plan_weekdays   SET             NULL    ('mon'...'sun')                                       計畫一週哪些天執行
 * reward_coins    DECIMAL(12,2)   NULL                                                          每次成功打卡獲得金幣
 * start_date      DATE            NULL                                                          開始追蹤日期
 * end_date        DATE            NULL                                                          結束追蹤日期
 * penalty_coins   DECIMAL(12,2)   NULL                                                          未完成打卡扣金幣
 * is_require_note TINYINT(1)      DEFAULT 0                                                     是否需紀錄心情筆記
 * is_archived     TINYINT(1)      DEFAULT 0                                                     是否已結束
 * updated_at      DATETIME        ON UPDATE CURRENT_TIMESTAMP                                   最後更新時間
 * created_at      DATETIME        DEFAULT CURRENT_TIMESTAMP                                     建立時間
 * ───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // 實體類與資料表對應(會自動建立資料表)
@Table(name = "habit") // 可選:可以手動建立資料表名
public class Habit {
	
	// 習慣ID: 主鍵 BIGINT  PK AUTO_INCREMENT
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long habitId; 
	
	// 所屬使用者ID: 外鍵 (BIGINT NOT NULL FK → users(id))
	@Column(name = "user_Id", unique = true, nullable = false, length = 50)
	private Long userId;
	
	// 習慣名稱: VARCHAR(50) NOT NULL (UK: user_id + name)
	@Column(name = "name", length = 50, nullable = false)
	private String habitName;
	
	// 習慣說明: TEXT NULL
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	// 分組標籤: VARCHAR(50) NULL
	@Column(name = "tag", length = 50)
	private String tag;
	
	// 習慣頻率：ENUM('daily','weekly','monthly','once','unlimited','custom')
	// 預設 'daily'
	@Enumerated(EnumType.STRING)
	@Column(name = "frequency",
			columnDefinition = "ENUM('daily','weekly','monthly','once','unlimited','custom') DEFAULT 'daily'")
	private Frequency frequency = Frequency.DAILY;

	// 週期內預期完成次數: INT NULL DEFAULT 1
	@Column(name = "target_times", columnDefinition = "INT DEFAULT 1")
	private Integer targetTimes;
	
	// 自定義週期長度(天): INT NULL DEFAULT 1
	@Column(name = "period_days", columnDefinition = "INT DEFAULT 1")
	private Integer periodDays;
	
    // 計畫一週哪些天完成: SET('mon'...'sun') NULL
    //   - 這裡以 Set<Weekday> 儲存並用 ElementCollection 做簡易字串映射
    //   - 生產資料庫時會以逗號分隔字串存入 SET 欄位
	@Column(name = "plan_weekdays", 
			columnDefinition = "SET('mon','tue','wed','thu','fri','sat','sun')")
	private Set<Weekday> planWeekdays;
	
	// 每次成功打卡獲得金幣: DECIMAL(12,2) NULL
	@Column(name = "reward_coins", precision = 12, scale = 2)
	private BigDecimal rewardCoins;
	
	// 開始追蹤日期: DATE NULL
	@Column(name = "start_date")
	private LocalDate startDate;
	
	// 結束追蹤日期: DATE NULL
	@Column(name = "end_date")
	private LocalDate endDate;
	
	// 每次未完成打卡扣金幣: DECIMAL(12,2) NULL
	@Column(name = "penalty_coins")
	private BigDecimal penaltyCoins;
	
	// 是否需紀錄心情筆記: TINYINT(1) 預設0(false)
	@Column(name = "is_require_note",
	        columnDefinition = "TINYINT(1) DEFAULT 0",
	        nullable = false)
	private Boolean isRequireNote;
	
	// 是否已結束(封存)：TINYINT(1)  預設0(false)
	@Column(name = "is_archived")
	private Boolean isArchived;
	
	// 更新時間: DATETIME，資料表異動時自動更新
	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	// 建立時間: DATETIME，資料表建立時自動填入
	@CreationTimestamp
	@Column(name = "created_at", updatable = false , nullable = false)
	private LocalDateTime createdAt;
	
}
