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
 * Entity name : Role
 * Table name  : role
 * +----+--------+--------------+---------------------+---------------------+
 * | id | name   | description  |     updated_at      |     created_at      |
 * +----+--------+--------------+---------------------+---------------------+
 * | 1  | admin  | ...          | 2025-06-12 15:00:00 | 2025-06-12 09:00:00 |
 * +----+--------+--------------+---------------------+---------------------+
 *
 * 欄位說明
 * ──────────────────────────────────────────────────────────────────────────
 * id          BIGINT          PK AUTO_INCREMENT          角色 ID
 * name        VARCHAR(50)     UK, NOT NULL               角色名稱 (admin/user/…)
 * description TEXT            NULL                       角色功能描述
 * updated_at  DATETIME        自動時間戳                 最後更新時間
 * created_at  DATETIME        自動時間戳                 建立時間
 * ──────────────────────────────────────────────────────────────────────────
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    // 主鍵：BIGINT  PK AUTO_INCREMENT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 角色名稱：VARCHAR(50)  UNIQUE NOT NULL
    @Column(name = "name", length = 50, unique = true, nullable = false)
    private String name;

    // 角色功能描述：TEXT  NULL
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // 最後更新時間：DATETIME  ON UPDATE CURRENT_TIMESTAMP
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // 建立時間：DATETIME  DEFAULT CURRENT_TIMESTAMP
    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;
}
