package com.company;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        HMACGenerator generator = new HMACGenerator();
        byte[] bytes = generator.generateBytes();
        String secretKey = generator.toHex(bytes);
        SecureRandom random = new SecureRandom();
        List<String> argsList = Arrays.asList(args);
        String computerMove = argsList.get(random.nextInt(argsList.size()));
        byte[] macBytes = generator.generateHMAC(computerMove, bytes);
        System.out.format("HMAC:\n%s\n",generator.toHex(macBytes).toUpperCase());
        HashSet<String> set = new HashSet<>();
        Collections.addAll(set, args);
        boolean isDuplicate = args.length != set.size();
        if (args.length < 3 || isDuplicate) {
            System.out.println("Please, enter the correct data: it must be several non-repeating strings " +
                    "(your moves), for example 'paper rock paper' or '1 2 3 4 5' ");
            System.exit(0);
        }
        System.out.println("Available moves:");
        HashMap<String,String> menu = new HashMap<>();
        int paragraph = 1;
        for(String str: args) {
            menu.put(String.valueOf(paragraph), str);
            paragraph++;
        }
        menu.put("0", "exit");
        menu.put("?","help");
        ArrayList<HashMap.Entry<String,String>> entries = new ArrayList<>(menu.entrySet());
        for(HashMap.Entry<String, String> entry: entries )
            System.out.format("%s - %s\n", entry.getKey(), entry.getValue());

        System.out.print("Enter your move: ");
        Scanner scanner = new Scanner(System.in);
        String menuItem = scanner.next();
        scanner.close();
        String userMove = menu.get(menuItem);
        if (userMove == null) {
            System.out.println("No such menu item.Try again");
            System.exit(0);
        }
        System.out.format("Your choice: %s\n", userMove);
        System.out.format("Computer choice: %s\n", computerMove);
        if (userMove.equals("0"))
            System.exit(0);
        else if (userMove.equals("?")) {
            HelpTable helpTable = new HelpTable();
            helpTable.generateHelpTable();
        }
        else {
            Game game = new Game();
            String winner = game.defineWinner(args, userMove, computerMove);
            System.out.println(winner);
            System.out.format("HMAC Key: %s", secretKey.toUpperCase());
        }

    }
}
