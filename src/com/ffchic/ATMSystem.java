package com.ffchic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATMSystem {
    public static void main(String[] args) {
        // 准备系统所需容器
        ArrayList<Account> accounts = new ArrayList<>();
        showMain(accounts);

    }

    /**
     * 系统首页
     * @param accounts
     */
    public static void showMain(ArrayList<Account> accounts) {
        // 1、准备系统首页
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
                    register(accounts, sc);
                    break;
                default:
                    System.out.println("您当前的输入操作不支持");
            }
        }
    }
    /**
     * * 注册
     * @param accounts 账户集合对象
     * @param sc  扫描器
     */
    public static void register(ArrayList<Account> accounts, Scanner sc) {
        // 2.键盘录入 姓名 密码 确认密码
        System.out.println("=======用户开户=======");
        System.out.println("请输入开户名:");
        String name = sc.next();
        String password;
        String okpassword;
        while (true){
            System.out.println("请输入密码:");
            password = sc.next();

            System.out.println("请输入确认密码:");
            okpassword = sc.next();
            if (!password.equals(okpassword)){
                System.out.println("两次密码必须一致----");
            }else {
                break;
            }
        }

        // 3、生成卡号，卡号是8位，并且不能与其他卡号重复
        String carId = createCardId(accounts);

        // 4、用户输入限额
        System.out.println("请您输入当日限额----");
        double quotaMoney = sc.nextDouble();


        // 4.创建账户信息
        Account account = new Account(carId,name,password,quotaMoney);
        // 5.账户对象添加到集合中去
        accounts.add(account);

        System.out.println("创建成功您的卡号为："+account.getCardId()+"请返回首页登录");

    }

    /**
     * 生成卡号id
     * @param accounts
     * @return 卡号id
     */
    public static String createCardId(ArrayList<Account> accounts) {
        // 生成8位随机数字
        String cardId = "";
        Random r = new Random();
        while (true){
            for (int i = 0; i < 8; i++) {
                cardId += r.nextInt(10);
            }
            // 判断卡号是否重复
            Account acc = getAccountByCardId(cardId, accounts);
            if (acc == null){
                return cardId;
            }
        }
    }

    /**
     * 根据卡号查找账户对象
     * @param cardId  卡号id
     * @param accounts  账户列表
     * @return 账户对象，没有则返回null
     */
    public static Account getAccountByCardId(String cardId, ArrayList<Account> accounts){
        // 根据卡号查询账户对象
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            if (acc.getCardId().equals(cardId)){
                return acc;
            }
        }
        return null;
    }
}
