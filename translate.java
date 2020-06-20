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
            System.out.println("请输入查询的国名： ");
            String str = scan.nextLine();
            FileReader fr = new FileReader("D:\\office\\学前任务\\代码\\dic.txt");
            //创建一个新的 FileReader，给予File读。
            BufferedReader br = new BufferedReader(fr);//从一个字符输入流中读取 文本
            String s = null;//定义一个字符串
            boolean b = false;//判断字典中是否含有输入的词
            while((s = br.readLine()) != null) {//readLine()读一行文本
                //substring(int beginIndex, int endIndex) 
     //返回一个字符串的子串,相当于剪切,从beginIndex开始到endIndex结束(含头不含尾)
                //indexOf(String str) 
                //返回指数在这个字符串指定的子字符串中第一个出现的下标。
                String china = s.substring(s.indexOf("<")+1, s.indexOf(">"));
                //lastIndexOf(String str) 
                //返回指数在这个字符串的指定子字符串中最后出现的下标。
                String english = s.substring(s.lastIndexOf("<")+1,s.lastIndexOf(">"));
                if(str.equals(english)) {
                	//判断输入为英文并存在，则对应输出为中文
                	System.out.println("所对应的中文名为：\n"+china);
                    b = true;
                }
                if(str.equals(china)) {
                	//判断输入为中文并存在，则对应输出为英文
                	System.out.println("所对应的英文名为：\n"+english);
                    b = true;
                }
            }
            if(str.equals("byebye")) {//判断是否输入的为"ByeBye"
                System.out.println("此程序已退出。");
                System.exit(0);//终止当前正在运行的java虚拟机
            }
            if(!b) {
            	//查不到此词语
                System.out.println("请输入正确国名！");
            }
        }
	}
}


