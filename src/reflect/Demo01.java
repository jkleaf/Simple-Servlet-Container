package reflect;

public class Demo01 {
	//��ȡ�ṹ��Ϣ(Դͷ)
	public static void main(String[] args) throws ClassNotFoundException {
		String string="abc";
		Class <?> clz=String.class;
		System.out.println(clz);
		clz=string.getClass();
		System.out.println(clz);
		clz=Class.forName("java.lang.String");
		System.out.println(clz);
	}

}
