package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Base64;

//加密文件
public class encoder_file {
    private String target;
    private String file_path;
    public encoder_file(String file_path,String target_path){
        this.file_path=file_path;
        this.target = target_path;
    }
    //Base64加密，target_path为加密文件输出路径
    public String jiaMi(){
        String encodedText="";
        try {
            File file = new File(file_path);
            if (file.exists() && !file.isDirectory()) {
                //读取文件内容
                FileInputStream fin = new FileInputStream(file);
                byte[] bt=new byte[1024*1024];
                int length = fin.read(bt);
                //生成编码器
                Base64.Encoder encoder = Base64.getEncoder();
                //加密
                encodedText = encoder.encodeToString(bt);
                //储存
                file = new File(target);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file);
                fw.write(encodedText);
                fw.flush();
                fw.close();

                fin.close();
            }
        }catch (Exception e){
            System.out.println("加密失败");
        }
        return encodedText;
    }
}
