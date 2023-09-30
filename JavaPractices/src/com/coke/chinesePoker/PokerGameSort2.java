package com.coke.chinesePoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class PokerGameSort2 {
  static HashMap<String, Integer> pokerWeights = new HashMap<>();
  static ArrayList<String> deck = new ArrayList<>();

  static {
    String[] color = {"♦", "♣", "♥", "♠"};
    String[] number = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    String[] others = {"大王", "小王"};

    // 准备牌
    for (String num : number) {
      for (String s : color) {
        deck.add(s + num);
      }
    }

    deck.add(others[0]);
    deck.add(others[1]);

//    System.out.println(deck);
    pokerWeights.put("J", 11);
    pokerWeights.put("Q", 12);
    pokerWeights.put("K", 13);
    pokerWeights.put("A", 14);
    pokerWeights.put("2", 15);
    pokerWeights.put(others[1], 16);
    pokerWeights.put(others[0], 17);

  }

  public PokerGameSort2() {
    Collections.shuffle(deck);
//    System.out.println(deck);
    ArrayList<String> lord = new ArrayList<>();
    ArrayList<String> player1 = new ArrayList<>();
    ArrayList<String> player2 = new ArrayList<>();
    ArrayList<String> player3 = new ArrayList<>();

    for (int i = 0; i < deck.size(); i++) {
      String card = deck.get(i);
      if (i <= 2) {
        lord.add(card);
        continue;
      }
      if (i % 3 == 0) {
        player1.add(card);
      } else if (i % 3 == 1) {
        player2.add(card);
      } else {
        player3.add(card);
      }
    }
    sortPoker(lord);
    sortPoker(player1);
    sortPoker(player2);
    sortPoker(player3);

    readPoker("牌堆", lord);
    readPoker("玩家1", player1);
    readPoker("玩家2", player2);
    readPoker("玩家3", player3);
  }

  void sortPoker(ArrayList<String> playPoker) {
    playPoker.sort((o1,o2) -> {
      String color1 = o1.substring(0, 1);
      String color2 = o1.substring(0, 1);
      int value1 = getValue(o1);
      int value2 = getValue(o2);

      int i = value1 - value2;
      return i == 0 ? color1.compareTo(color2) : i;
    });
  }

  int getValue(String cardName) {
    if ("大王".equals(cardName)) return 17;
    if ("小王".equals(cardName)) return 16;

    String num = cardName.substring(1);
    if (pokerWeights.containsKey(num)) {
      return pokerWeights.get(num);
    } else {
      return Integer.parseInt(num);
    }
  }

  void readPoker(String playerName, ArrayList<String> playerPoker) {
    System.out.print(playerName + ": ");
    for (String card : playerPoker) {
      System.out.print(card + " ");
    }
    System.out.println();
  }
}
