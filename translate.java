package test_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class translate {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.println("�������ѯ�Ĺ����� ");
            String str = scan.nextLine();
            FileReader fr = new FileReader("D:\\office\\ѧǰ����\\����\\dic.txt");
            //����һ���µ� FileReader������File����
            BufferedReader br = new BufferedReader(fr);//��һ���ַ��������ж�ȡ �ı�
            String s = null;//����һ���ַ���
            boolean b = false;//�ж��ֵ����Ƿ�������Ĵ�
            while((s = br.readLine()) != null) {//readLine()��һ���ı�
                //substring(int beginIndex, int endIndex) 
     //����һ���ַ������Ӵ�,�൱�ڼ���,��beginIndex��ʼ��endIndex����(��ͷ����β)
                //indexOf(String str) 
                //����ָ��������ַ���ָ�������ַ����е�һ�����ֵ��±ꡣ
                String china = s.substring(s.indexOf("<")+1, s.indexOf(">"));
                //lastIndexOf(String str) 
                //����ָ��������ַ�����ָ�����ַ����������ֵ��±ꡣ
                String english = s.substring(s.lastIndexOf("<")+1,s.lastIndexOf(">"));
                if(str.equals(english)) {
                	//�ж�����ΪӢ�Ĳ����ڣ����Ӧ���Ϊ����
                	System.out.println("����Ӧ��������Ϊ��\n"+china);
                    b = true;
                }
                if(str.equals(china)) {
                	//�ж�����Ϊ���Ĳ����ڣ����Ӧ���ΪӢ��
                	System.out.println("����Ӧ��Ӣ����Ϊ��\n"+english);
                    b = true;
                }
            }
            if(str.equals("byebye")) {//�ж��Ƿ������Ϊ"ByeBye"
                System.out.println("�˳������˳���");
                System.exit(0);//��ֹ��ǰ�������е�java�����
            }
            if(!b) {
            	//�鲻���˴���
                System.out.println("��������ȷ������");
            }
        }
	}
}


