export default function Dashboard() {
    /* 先用簡單 Flex 四欄 */
    return (
      <div style={{ padding: 20 }}>
        <h2>主畫面</h2>
  
        {/* 4 欄容器 */}
        <div style={{ display: "flex", gap: 20, marginTop: 20 }}>
          {/* －－－－ 習慣欄 －－－－ */}
          <section style={colStyle}>
            <h3>習慣</h3>
            <button>新增一個習慣</button>
            <ul style={listStyle}>
              <li>＋ 有氧運動 10 分鐘</li>
              <li>－ 晚睡</li>
              {/* 之後把 li 換成真正的元件 */}
            </ul>
          </section>
  
          {/* －－－－ 每日任務欄 －－－－ */}
          <section style={colStyle}>
            <h3>每日任務</h3>
            <button>新增一個每日</button>
            <ul style={listStyle}>
              <li>伸展 ➤ 每天固定鎖鏈</li>
              <li>完成今天最重要的任務</li>
            </ul>
          </section>
  
          {/* －－－－ 待辦事項欄 －－－－ */}
          <section style={colStyle}>
            <h3>待辦事項</h3>
            <button>新增一個待辦</button>
            <ul style={listStyle}>
              <li>（尚無待辦，可以加一個）</li>
            </ul>
          </section>
  
          {/* －－－－ 獎勵欄 －－－－ */}
          <section style={colStyle}>
            <h3>獎勵</h3>
            <button>新增一個獎勵</button>
            <ul style={listStyle}>
              <li>🌙 月牙刀 — 90 金幣</li>
              <li>♞ 鎧甲 — 90 金幣</li>
            </ul>
          </section>
        </div>
      </div>
    );
  }
  
  /* Inline style 方便看結構，之後可抽成 css */
  const colStyle = {
    flex: 1,
    border: "1px solid #ccc",
    padding: 10,
    minWidth: 180,
  };
  
  const listStyle = { marginTop: 10, listStyle: "none", paddingLeft: 0 };
  