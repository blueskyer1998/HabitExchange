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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
Entity name: Habit
Table name: habit
+----+---------+----------+----------------------+---------+-----------+--------------+-------------+---------------+--------------+------------+------------+---------------+----------------+-------------+---------------------+---------------------+
| id | user_id | name     | description          | tag     | frequency | target_times | period_days | plan_weekdays | reward_coins | start_date | end_date   | penalty_coins | is_require_note | is_archived | updated_at          | created_at          |
+----+---------+----------+----------------------+---------+-----------+--------------+-------------+---------------+--------------+------------+------------+---------------+----------------+-------------+---------------------+---------------------+
| 1  | 1       | Reading  | 30 min reading daily | health  | daily     | 1            | 7           | Mon,Wed,Fri   | 10           | 2025-06-01 | 2025-06-30 | 5             | false          | false       | 2025-06-03 13:00:00 | 2025-06-01 08:00:00 |
| 2  | 2       | Running  | 5 km run             | fitness | weekly    | 3            | 7           | Tue,Thu,Sat   | 15           | 2025-06-01 | 2025-09-01 | 10            | true           | false       | 2025-06-03 13:00:00 | 2025-06-01 08:00:00 |
+----+---------+----------+----------------------+---------+-----------+--------------+-------------+---------------+--------------+------------+------------+---------------+----------------+-------------+---------------------+---------------------+
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // 實體類與資料表對應(會自動建立資料表)
@Table(name = "habit") // 可選:可以手動建立資料表名
public class Habit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自動生成 id
	@Column(name = "id") // 資料表 user 中預設的欄位名稱
	private Long habitId; // 使用者 ID
	
	@Column(name = "user_Id", unique = true, nullable = false, length = 50)
	private Long userId;
	
	@Column(name = "name", length = 50, nullable = false)
	private String habitName;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "tag", length = 50)
	private String tag;
	
	@Column(name = "frequency")
	private Frequency frequency = Frequency.DAILY;
	
	@Column(name = "target_times", columnDefinition = "INT DEFAULT 1")
	private Integer targetTimes;
	
	@Column(name = "period_days", columnDefinition = "INT DEFAULT 1")
	private Integer periodDays;
	
	@Column(name = "plan_weekdays")
	private Set<Weekday> planWeekdays;
	
	@Column(name = "reward_coins", precision = 12, scale = 2)
	private BigDecimal rewardCoins;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@Column(name = "penalty_coins")
	private BigDecimal penaltyCoins;
	
	@Column(name = "is_require_note")
	private Boolean isRequireNote;
	
	@Column(name = "is_archived")
	private Boolean isArchived;
	
	// 更新時間：DATETIME，資料表異動時自動更新
	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	// 建立時間：DATETIME，資料表建立時自動填入
	@CreationTimestamp
	@Column(name = "created_at", updatable = false , nullable = false)
	private LocalDateTime createdAt;
	
}
