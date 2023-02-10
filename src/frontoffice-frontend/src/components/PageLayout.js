import Header from "./Header";
import {Layout} from "antd";
import {Content, Footer} from "antd/es/layout/layout";

const PageLayout = ({ children }) => {
  return (
      <Layout className="layout">
          <Header />
          <Content style={{ padding: '50px', minHeight: 'calc(100vh - 64px - 70px)', backgroundColor: '#fff' }}>
            {children}
          </Content>
          <Footer style={{ textAlign: 'center' }}>Codemmunity ©2023</Footer>
      </Layout>
  );
}

export default PageLayout;