package reflect;

import com.server02.Servlet;

public class Demo02 {
	//����ʵ��,���ÿչ���
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class <?> clz=Class.forName("com.server03.LoginServer");
		//���ÿչ���,ȷ���չ������
		@SuppressWarnings("unused")
		Servlet serv=(Servlet) clz.newInstance();
	}

}
