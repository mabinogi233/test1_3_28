package controller;
import service.op_package;
import service.encoder_file;
import service.decoder_file;

import java.io.File;
import java.util.Scanner;


public class management {
    public void run(){
        System.out.println("欢迎使用本系统，本系统操作如下：");
        System.out.println("进入指定文件夹：             cd 绝对路径");
        System.out.println("进入子文件夹：               to 子文件夹名称");
        System.out.println("输出当前目录下的文件:        pr");
        System.out.println("在当前目录下创建文件夹:      cr 文件夹名称");
        System.out.println("删除当前目录下的文件夹:      de 文件夹名称");
        System.out.println("复制当前目录下的文件或文件夹：copy 文件名称 目标绝对路径");
        System.out.println("加密当前路径下的文件：        encode 文件名称");
        System.out.println("解密当前路径下的文件：        decode 文件名称");
        System.out.println("退出系统：                    quit");
        op_package opPackage = new op_package("c:\\");
        System.out.println("当前路径为："+opPackage.getPackage_path());
        System.out.println("请输入操作");
        Scanner scanner = new Scanner(System.in);
        String op = scanner.next();
        System.out.println(op);
        while (!op.equals("quit")){
            switch (op) {
                case "cd":
                    String file_path1 = scanner.next();
                    boolean flag1 = opPackage.move_package(file_path1);
                    if(flag1) {
                        System.out.println("文件夹打开成功");
                    }else {
                        System.out.println("文件夹打开失败");
                    }
                    break;
                case "to":
                    String file_name1 = scanner.next();
                    boolean flag2 = opPackage.move_child_package(file_name1);
                    if(flag2) {
                        System.out.println("子文件夹打开成功");
                    }else {
                        System.out.println("子文件夹打开失败");
                    }
                    break;
                case "pr":
                    System.out.println("当前目录下的文件为：");
                    File[] files = opPackage.listall();
                    for(File file:files){
                        System.out.println(file.getName());
                    }
                    break;
                case "cr":
                    String file_name2 = scanner.next();
                    boolean flag3 = opPackage.create_package(file_name2);
                    if (flag3){
                        System.out.println("文件夹创建成功");
                    }else {
                        System.out.println("文件夹创建失败");
                    }
                    break;
                case "de":
                    String file_name3 = scanner.next();
                    boolean flag4 = opPackage.delete_package(file_name3);
                    if (flag4){
                        System.out.println("文件删除成功");
                    }else {
                        System.out.println("文件删除失败");
                    }
                    break;
                case "copy":
                    String file_name4 = scanner.next();
                    String file_path2 = scanner.next();
                    boolean flag5 = opPackage.copy(file_name4,file_path2);
                    if (flag5){
                        System.out.println("文件复制成功");
                    }else {
                        System.out.println("文件复制失败");
                    }
                    break;
                case "encode":
                    String file_name5 = scanner.next();
                    encoder_file encoder = new encoder_file(opPackage.getPackage_path()+"\\"+file_name5,opPackage.getPackage_path()+"\\jiami.txt");
                    String str = encoder.jiaMi();
                    System.out.println("加密完成");
                    break;
                case "decode":
                    String file_name6 = scanner.next();
                    decoder_file decoder = new decoder_file(opPackage.getPackage_path()+"\\"+file_name6,opPackage.getPackage_path()+"\\jiemi.txt");
                    decoder.jieMI();
                    System.out.println("解密完成");
                    break;
                default:
                    System.out.println("输入错误");
            }
            System.out.println("当前路径为："+opPackage.getPackage_path());
            System.out.println("请输入操作");
            op = scanner.next();
        }
        System.out.println("程序结束");
    }
}
