package service;

import java.io.*;

//文件夹操作
public class op_package {

    private String package_path;
    private File thisPackage;

    //进入基础路径
    public op_package(String package_path){
        try {
            thisPackage = new File(package_path);
            this.package_path=package_path;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //进入非子文件夹的其他文件夹
    public boolean move_package(String package_path){
        boolean flag = false;
        try {
            File linshi= new File(package_path);
            //判断是否存在
            if(linshi.exists()) {
                this.package_path = package_path;
                thisPackage = linshi;
                flag=true;
            }else {
                flag=false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    //进入子文件夹
    public boolean move_child_package(String package_name){
        boolean flag = false;
        try {
            File linshi = new File(this.package_path,package_name);
            if(linshi.exists()) {
                this.package_path = this.package_path + "\\" + package_name;
                thisPackage=linshi;
                flag=true;
            }else {
                flag=false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    //创建子文件夹
    public boolean create_package(String package_name){
        boolean flag = false;
        try {
            File new_File = new File(this.package_path, package_name);
            flag = new_File.mkdir();
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    //删除子文件夹
    public boolean delete_package(String package_name) {
        boolean flag=false;
        try {
            File linshi=new File(this.package_path + "\\" + package_name);
            if(linshi.exists()) {
                deleteDir(this.package_path + "\\" + package_name);
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    //绝对路径删除文件夹
    private void deleteDir(String dirPath) {
        File file = new File(dirPath);
        if(file.isFile()) {
            file.delete();
        }else {
            File[] files = file.listFiles();
            if(files == null) {
                file.delete();
            }else {
                for (int i = 0; i < files.length; i++)
                {
                    deleteDir(files[i].getAbsolutePath());
                }
                file.delete();
            }
        }
    }

    //列出所有文件信息
    public File[] listall(){
        return thisPackage.listFiles();
    }

    //将当前目录下的文件或文件夹复制到指定目录(绝对路径)
    public boolean copy(String file_name,String target){
        boolean flag = false;
        File move_from = new File(package_path,file_name);
        //是目录
        if(move_from.isDirectory()){
            copy_dir(package_path+"\\"+file_name,target);
            flag = true;
        }else {
            //是文件
            copy_file(package_path+"\\"+file_name,target);
            flag = true;
        }
        return flag;
    }

    //目录复制(绝对路径)
    private void copy_dir(String file_path,String target){
        File move_from=new File(file_path);
        File[] allFile = move_from.listFiles();
        for(File fl:allFile){
            String file_end_path = target + "\\" + fl.getName();
            if(fl.isDirectory()){
                File newfile = new File(file_end_path);
                newfile.mkdir();
                copy_dir(fl.getAbsolutePath(),file_end_path);
            }else {
                copy_file(fl.getAbsolutePath(),file_end_path);
            }
        }
    }

    //文件复制(绝对路径)
    private void copy_file(String file_path,String target){
        try {
            File move_from = new File(file_path);
            File move_to = new File(target);
            FileInputStream fin = new FileInputStream(move_from);
            FileOutputStream fout = new FileOutputStream(move_to);
            byte[] bt =new byte[1024*1024];
            int i=0;
            do{
                i = fin.read(bt);
                if(i!=-1) {
                    fout.write(bt, 0, i);
                }
            }while (i!=-1);
            fout.close();
            fin.close();
        }catch (Exception e){
            System.out.println("复制失败");
        }
    }

    public String getPackage_path() {
        return package_path;
    }
}
