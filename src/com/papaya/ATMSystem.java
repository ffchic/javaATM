package com.papaya;

import java.util.ArrayList;
import java.util.Scanner;

public class ATMSystem {
    public static void main(String[] args) {
        // 准备系统所需容器
        ArrayList<Account> accounts = new ArrayList<>();
        showMain(accounts);

    }

    /**
     * 系统首页
     *
     * @param accounts
     */
    public static void showMain(ArrayList<Account> accounts) {
        // 准备系统首页
        System.out.println("=======欢迎进入首页=======");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1、登录");
            System.out.println("2、开户");
            System.out.println("请输入您想做的操作:");
            int command = sc.nextInt();
            switch (command) {
                case 1:
                    //登录
                    break;
                case 2:
                    //开户
                    register(accounts);
                    break;
                default:
                    System.out.println("您当前的输入操作不支持");
            }
        }
    }
    /**
     * 注册
     *
     * @param accounts
     */
    public static void register(ArrayList<Account> accounts) {

    }

}
