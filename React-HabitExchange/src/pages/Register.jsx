import { Link } from "react-router-dom";

export default function Register() {
    return (
      <div style={{ maxWidth: 320, margin: "80px auto" }}>
        <h2>HabitExchange 註冊</h2>
  
        {/* 使用者名稱 */}
        <div style={{ marginTop: 20 }}>
          <label htmlFor="username">使用者名稱</label>
          <input id="username" type="text" placeholder="例如，HabitCat" style={{ width: "100%" }} />
        </div>
  
        {/* 電子郵件 */}
        <div style={{ marginTop: 16 }}>
          <label htmlFor="email">電子郵件</label>
          <input id="email" type="email" placeholder="例如，react@example.com" style={{ width: "100%" }} />
        </div>
  
        {/* 密碼 */}
        <div style={{ marginTop: 16 }}>
          <label htmlFor="reg-pw">密碼</label>
          <input id="reg-pw" type="password" placeholder="例如，************" style={{ width: "100%" }} />
        </div>
  
        {/* 再次確認密碼 */}
        <div style={{ marginTop: 16 }}>
          <label htmlFor="reg-pw2">確認密碼</label>
          <input id="reg-pw2" type="password" placeholder="必須與密碼一致!" style={{ width: "100%" }} />
        </div>
  
        {/* 送出 */}
        <button style={{ marginTop: 24 }}>建立帳號</button>

        {/* 登入提示 */}
        <p style={{ marginTop: 24, fontSize: 14 }}>
            已經擁有 HabitExchange 的帳號？ <Link to="/login">按此登入</Link>
        </p>
      </div>
    );
  }
  