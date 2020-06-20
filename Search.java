package test_1;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL ;
import  java.net.HttpURLConnection ;

public class Search {
 
    private String key ;
    public Search(String key ) {
        this.key =key ;
 
    }
 
    public  String seatchID(String id){
        String url = "http://apis.juhe.cn/idcard/index?key="+this.key+ "&cardno="+ id ;
        URL urlNet = null ;
        InputStream is = null ;
        ByteArrayOutputStream bao = null ;
        String result = null ;
        try {
            urlNet = new URL(url);
            try {
                HttpURLConnection conn = (HttpURLConnection)urlNet.openConnection() ;
                conn.setReadTimeout(5*1000 );
                conn.setRequestMethod("GET");
                 is = conn.getInputStream() ;
                int len = -1 ;
                byte[] buf = new byte[128] ;
                bao = new ByteArrayOutputStream() ;
                while ((len = is.read(buf))!=-1){
                    bao.write(buf,0,len);
 
                }
                bao.flush();
                result = new String(bao.toByteArray()) ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bao!=null){
                try {
                    bao.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
 
        return result ;
 
    }
}
