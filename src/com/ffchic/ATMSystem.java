package com.ffchic;

import org.jetbrains.annotations.NotNull;
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
            System.out.print("请输入您想做的操作:");
            int command = sc.nextInt();
            switch (command) {
                case 1:
                    //登录
                    login(accounts, sc);
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
     * 用户登录
     * @param accounts
     * @param sc
     */
    public static void login(ArrayList<Account> accounts, @NotNull Scanner sc){
        if (accounts.size() < 1){
            System.out.println("当前没有账户，请先进行注册");
            return;
        }
        System.out.println("=======进行用户登录=======");
        Account account;
        String cardId;
        // 1、用户输入卡号，判断卡号是否存在
        while (true){
            System.out.print("请输入登录卡号");
            cardId = sc.next();
            account = getAccountByCardId(cardId,accounts);
            if (account!=null){
                break;
            }else{
                System.out.println("输入的卡号不存在，请重新输入卡号");
            }
        }
        // 2、用户输入密码，判断密码是否正确
        while (true){
            System.out.print("请输入密码：");
            String password = sc.next();
            if (password.equals(account.getPassWord())){
                break;
            }else{
                System.out.println("密码输入错误，请重新输入");
            }
        }
        System.out.println("登录成功！");
        showUserCommand(account, sc);
    }

    /**
     * * 注册
     * @param accounts 账户集合对象
     * @param sc  扫描器
     */
    public static void register(ArrayList<Account> accounts, @NotNull Scanner sc) {
        // 2.键盘录入 姓名 密码 确认密码
        System.out.println("=======用户开户=======");
        System.out.print("请输入开户名:");
        String name = sc.next();
        String password;
        String okpassword;
        while (true){
            System.out.print("请输入密码:");
            password = sc.next();

            System.out.print("请输入确认密码:");
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
        System.out.print("请您输入当日限额----");
        double quotaMoney = sc.nextDouble();


        // 4.创建账户信息
        Account account = new Account(carId,name,password,quotaMoney);
        // 5.账户对象添加到集合中去
        accounts.add(account);

        System.out.println("创建成功您的卡号为："+account.getCardId()+" 请返回首页登录");

    }

    /**
     * 进入用户命令行
     * @param acc
     * @param sc
     */
    public static void showUserCommand(Account acc, @NotNull Scanner sc){
        while (true){
            System.out.println("=======用户操作页面=======");
            System.out.println("1、查询账户");
            System.out.println("2、存款");
            System.out.println("3、取款");
            System.out.println("4、转账");
            System.out.println("5、修改密码");
            System.out.println("6、退出");
            System.out.println("7、注销账户");
            System.out.print("请输入要操作的内容：");
            int command = sc.nextInt();
            switch (command) {
                case 1:
                    //查询账户
                    showAccount(acc, sc);
                    break;
                case 2:
                    //存款
                    depositMoney(acc,sc);
                    break;
                case 3:
                    //取款
                    break;
                case 4:
                    //转账
                    break;
                case 5:
                    //修改密码
                    break;
                case 6:
                    //退出
                    System.out.println("退出系统，欢迎下次光临");
                    return;
                case 7:
                    //注销账户
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 存款
     * @param acc
     */
    public static void depositMoney(Account acc, Scanner sc){
        System.out.println("存钱操作");
        System.out.print("请输入存款金额：");
        double money = sc.nextDouble();

        acc.setMoney(acc.getMoney() + money);
        System.out.println("-----存钱成功-----");
        showAccount(acc, sc);
    }

    /**
     * 取款
     * @param acc
     * @param sc
     */
    public static void drawMoney(Account acc, Scanner sc){
        System.out.println("=======取款操作=======");
        if (acc.getMoney() >= 100){
            while (true) {
                System.out.print("请输入取款金额");
                double money = sc.nextDouble();
                if (money > acc.getQuotaMoney()){
                    System.out.println("超出当日限额，少取一点.当前限额为："+acc.getQuotaMoney());
                }else{
                    if(money >= acc.getMoney()){
                        //取钱
                        acc.setMoney(acc.getMoney()-money);
                        System.out.println("取钱成功，当前账户还剩余："+acc.getMoney());
                        return;
                    }else {
                        System.out.println("余额不足，请重新输入");
                    }
                }
            }
        }else {
            System.out.println("余额小于100元");
        }
    }

    /**
     * 查询账户
     * @param acc
     * @param sc
     */
    public static void showAccount(Account acc, Scanner sc){
        System.out.println("账户卡号为："+acc.getCardId());
        System.out.println("账户用户名为："+acc.getUserName());
        System.out.println("账户余额为："+acc.getMoney());
        System.out.println("账户剩余额度为："+acc.getQuotaMoney());
        sc.next();
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
