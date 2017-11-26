package cn.loylin.lochat;

import cn.loylin.lochat.xmpp.NotificationManager;
import java.util.Scanner;

public class MinaManager {

    public static void main(String[] args) {
        NotificationManager manager = new NotificationManager();

        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit){
            String str = sc.nextLine();
            if(str.equals("y")){
                manager.sendBroadcast("1234567890", "Hello", "1345641", "");
            } else {
                exit = true;
            }
        }
    }
}
