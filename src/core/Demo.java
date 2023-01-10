package core;

import java.io.*;
/**
 *
 *
 * @author Duc Lo
 * @version 13/01/23
 */
public class Demo {

    public static void main(String[] args) {
        //Task 1
        CORE boss = new Tournament("Olga");
        Tournament.setUpChampion();
        Tournament.setUpChallenge();
//        System.out.println(boss.toString());
//        System.out.println("*************");
//        boss.enterChampion("Ganfrank");
//        System.out.println(boss.getTeam());
//        boss.fightChallenge(2);
//        System.out.println(boss.getMoney());
//        boss.retireChampion("Ganfrank");
//        System.out.println(boss.getMoney());

        //Task2
//        Challenge newChallenge = new Challenge(10, ChallengeType.MAGIC, "Squds", 1, 100);
//        //Tournament.challengeList.contains(newChallenge.getChallengeNo());
//        for(Challenge ch : Tournament.challengeList){
//            if(ch.getChallengeNo() == newChallenge.getChallengeNo()){
//                Tournament.challengeList.remove(ch);
//                Tournament.challengeList.add(newChallenge);
//                break;
//            }
//        }
//        System.out.println(boss.getAllChallenges());
//        Tournament.challengeList.add(new Challenge(10, ChallengeType.MAGIC, "Squds", 1, 100));
//        System.out.println(boss.getAllChallenges());
//        Champion wizzo = new Champion("Wizzo", new ChampionClass(Cons.wizard, 0, false, null, "disappears", null, null), ChampionState.WAITING);
//        Tournament.championList.add(wizzo);
//        System.out.println(boss.getReserve());

        //Task 3
        ChampionClass butterAtrributes = new ChampionClass(Cons.mergim, 5, false, 100,null,null,null);
        butterAtrributes.setLifeForce(60);
        Champion butter = new Champion("Butter", butterAtrributes, ChampionState.WAITING);
        Tournament.championList.add(butter);
        System.out.println(boss.getReserve());
    }

}
