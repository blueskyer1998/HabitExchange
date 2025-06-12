package com.example.demo.model.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
 * Entity name : User
 * Table name  : users
 *
 * +----+----------+------------------+-------------+---------------+------+---------+--------+---------------------+---------------------+
 * | id | username | email            | is_verified | password_hash | salt | role_id | active |     updated_at      |     created_at      |
 * +----+----------+------------------+-------------+---------------+------+---------+--------+---------------------+---------------------+
 * |  1 | john     | john@example.com |     true    |   Qwd1234...  | $ED..|    1    |  true  | 2025-06-03 13:00:00 | 2025-06-01 08:00:00 |
 * |  2 | mary     | mary@example.com |     false   |   Qwd5678...  | $AW..|    2    | false  | 2025-06-03 13:00:00 | 2025-06-01 08:00:00 |
 * +----+----------+------------------+-------------+---------------+------+---------+--------+---------------------+---------------------+
 *
 * 欄位說明
 * ───────────────────────────────────────────────────────────────────────────────────────────────────────────────
 * id              BIGINT           PK AUTO_INCREMENT                     使用者 ID
 * username        VARCHAR(50)      UNIQUE  NOT NULL                      使用者名稱
 * email           VARCHAR(50)      UNIQUE  NOT NULL                      信箱
 * is_verified     TINYINT(1)       NOT NULL                              信箱是否已驗證
 * password_hash   VARCHAR(255)     NOT NULL                              密碼雜湊值
 * salt            VARCHAR(255)     NOT NULL                              密碼鹽值
 * role_id         BIGINT           NULL   FK → role(id)                  角色 ID
 * active          TINYINT(1)       DEFAULT 1                             帳號是否啟用
 * updated_at      DATETIME         ON UPDATE CURRENT_TIMESTAMP           最後更新時間
 * created_at      DATETIME         DEFAULT CURRENT_TIMESTAMP             註冊時間
 * ───────────────────────────────────────────────────────────────────────────────────────────────────────────────
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // 實體類與資料表對應(會自動建立資料表)
@Table(name = "user") // 若資料表名稱與實體類一致可以不用設定此行，可選:可以手動建立資料表名
public class User {
	
	// 使用者ID: 主鍵 BIGINT PK AUTO_INCREMENT 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自動生成 id
	@Column(name = "id") // 資料表 user 中預設的欄位名稱
	private Long userId;
	
	// 使用者名稱: VARCHAR(50) UNIQUE NOT NULL
	@Column(name = "username", length = 50, unique = true, nullable = false)
	private String userName;
	
	// 信箱: VARCHAR(50) UNIQUE NOT NULL
	@Column(name = "email", length = 50, unique = true, nullable = false)
	private String email;
	
	// 是否以驗證信箱: TINYINT(1) NOT NULL，預設 false
	@Column(name = "is_verified", columnDefinition = "TINYINT(1) DEFAULT 0", nullable = false)
	private Boolean isVerified = false;
	
	// 密碼雜湊值: VARCHAR(255) NOT NULL
	@Column(name = "password_hash", length = 255, nullable = false)
	private String passwordHash;
	
    // 密碼鹽值: VARCHAR(255) NOT NULL
	@Column(name = "salt", length = 255, nullable = false)
	private String salt;
	
	// 角色外鍵: BIGINT ;可改成 @ManyToOne Role role
	@Column(name = "role_id")
	private String roleId;
	
	// 帳號是否啟用: TINYINT(1)，預設 1
	@Column(name = "active",
			columnDefinition = "TINYINT(1) DEFAULT 1", 
			nullable = false)
	private Boolean active;
	
	// 更新時間: DATETIME，資料表異動時自動更新
	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	// 建立時間: DATETIME，資料表建立時自動填入
	@CreationTimestamp
	@Column(name = "created_at", updatable = false , nullable = false)
	private LocalDateTime createdAt;
	
}
