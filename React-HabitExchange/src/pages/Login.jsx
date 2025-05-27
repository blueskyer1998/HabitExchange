import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

export default function Login() {
    const navigate = useNavigate();        // ← 取得導頁函式

    const handleLogin = (e) => {
      e.preventDefault();                  // 防止表單預設換頁
      // TODO: 這裡之後加帳密驗證
      navigate("/dashboard");              // 導向主畫面
    };

    // 先不處理狀態、驗證，只放結構
    return (
      <div style={{ maxWidth: 320, margin: "80px auto" }}>
        <h2>HabitExchange 登入</h2>
  
        {/* 帳號 */}
        <div style={{ marginTop: 20 }}>
          <label htmlFor="account">電子郵件或使用者名稱（區分大小寫）</label>
          <input id="account" type="text" placeholder="電子郵件或使用者名稱（區分大小寫）" style={{ width: "100%" }} />
        </div>
  
        {/* 密碼 */}
        <div style={{ marginTop: 16 }}>
          <label htmlFor="password">密碼</label>
          <input id="password" type="password" placeholder="密碼" style={{ width: "100%" }} />
        </div>
  
        {/* 按鈕 */}
        <button onClick={handleLogin} style={{ marginTop: 24 }}>登入</button>

        {/* 註冊提示 */}
        <p style={{ marginTop: 24, fontSize: 14 }}>
        沒有帳號？ <Link to="/register">前往註冊</Link>
        </p>
      </div>
    );
  }
  