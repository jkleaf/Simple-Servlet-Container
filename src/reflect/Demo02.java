package reflect;

import com.server02.Servlet;

public class Demo02 {
	//创建实例,调用空构造
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class <?> clz=Class.forName("com.server03.LoginServer");
		//调用空构造,确保空构造存在
		@SuppressWarnings("unused")
		Servlet serv=(Servlet) clz.newInstance();
	}

}
