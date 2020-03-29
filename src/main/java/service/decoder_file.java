package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Base64;

//解码器
public class decoder_file {
    private final String target;
    private final String file_path;
    public decoder_file(String file_path,String target_path){
        this.file_path=file_path;
        this.target = target_path;
    }
    //输出文件储存路径
    public boolean jieMI(){
        boolean flag = false;
        File file = new File(target);
        try{
            //读取加密文件
            FileReader fw = new FileReader(new File(file_path));
            char[] ch=new char[1024*1024];
            int len = fw.read(ch);
            String str= new String(ch);
            //解密并输出
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fout = new FileOutputStream(file);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] bt= decoder.decode(str);
            fout.write(bt);
            fout.close();
        }catch (Exception e){
            System.out.println("解码失败");
        }
        return flag;
    }
}
