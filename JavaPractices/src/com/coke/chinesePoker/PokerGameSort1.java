package com.coke.chinesePoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

/*
 * 1. 准备牌
 * 2. 洗牌
 * 3. 发牌
 * 4. 看牌(同时给牌排序)
 *
 * */
public class PokerGameSort1 {

  static HashMap<Integer, String> matchRules = new HashMap<>();
  static ArrayList<Integer> deck = new ArrayList<>();

  static {
    String[] color = {"♦", "♣", "♥", "♠"};
    String[] number = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    String[] others = {"大王", "小王"};

    int sortindex = 0;
    // 准备牌
    for (String num : number) {
      for (String s : color) {
        sortindex++;
        matchRules.put(sortindex, s + num);
        deck.add(sortindex);
      }
    }
    sortindex++;
    matchRules.put(sortindex, others[0]);
    deck.add(sortindex);

    sortindex++;
    matchRules.put(sortindex, others[1]);
    deck.add(sortindex);

//    System.out.println(deck);
//    System.out.println(matchRules);
  }

  public PokerGameSort1() {
     // 2. 洗牌
    Collections.shuffle(deck);
//    System.out.println(deck);

    // 3. 发牌
    TreeSet<Integer> lord = new TreeSet<>();
    TreeSet<Integer> player1 = new TreeSet<>();
    TreeSet<Integer> player2 = new TreeSet<>();
    TreeSet<Integer> player3 = new TreeSet<>();

    for (int i = 0; i < deck.size(); i++) {
      if (i <= 2) {
        lord.add(deck.get(i));
        continue;
      }

      if(i % 3 == 0){
        player1.add(deck.get(i));
      } else if (i % 3 == 1) {
        player2.add(deck.get(i));
      }else{
        player3.add(deck.get(i));
      }
    }

    readPoker("牌堆", lord);
    readPoker("玩家1", player1);
    readPoker("玩家2", player2);
    readPoker("玩家3", player3);
  }

  void readPoker(String playerName, TreeSet<Integer> playerPoker) {
    System.out.print(playerName + " :");
    for (Integer card : playerPoker) {
      String cardName = matchRules.get(card);
      System.out.print(cardName + " ");
    }
    System.out.println();
  }
}
