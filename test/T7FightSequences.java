/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import core.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedHashSet;

import static org.junit.Assert.*;

/**
 *
 * @author comqaam
 */
public class T7FightSequences {
    CORE game;
    public T7FightSequences() {
    }
    
     @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        game = new Tournament("Jean");
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    //Action sequences
    @Test
    public void warriorDeadUsedAgain() {
        int expected = 2;
        Treasury.balance = 1000;
        Tournament.playerTeam = new LinkedHashSet<>();
        game.enterChampion("Flimsi");
        game.fightChallenge(2);  //should be dead
        int actual = game.fightChallenge(2); //re-used ?
        assertEquals(expected, actual);
    }
    
    @Test
    public void warriorDeadUsedAgainMoney() {
        Tournament.setUpChampion();
        Tournament.setUpChallenge();
        int expected = 1000-200-120-120;
        Tournament.playerTeam = new LinkedHashSet<>();
        Treasury.balance = 1000;
        game.enterChampion("Flimsi");
        game.fightChallenge(2);  //should be dead
        game.fightChallenge(2); //re-used ?
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
    @Test
    public void warriorWithdrawnBeforeFight() {
        int expected = 2;
        Tournament.playerTeam = new LinkedHashSet<>();
        Treasury.balance = 1000;
        game.enterChampion("Argon");
        game.retireChampion("Argon");
        int actual = game.fightChallenge(2); //used ?
        assertEquals(expected, actual);
    }
    
    @Test
    public void warriorWithdrawnBeforeFightMoney() {
        Tournament.setUpChampion();
        Tournament.setUpChallenge();
        int expected = 1000-900+450-120;
        Tournament.playerTeam = new LinkedHashSet<>();
        Treasury.balance = 1000;
        game.enterChampion("Argon");
        game.retireChampion("Argon");
        game.fightChallenge(2);  //not available
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
    @Test
    public void defeatAchieved() {
        int expected = 3;
        game.enterChampion("Rudolf");
        game.enterChampion("Flimsi");
        game.fightChallenge(8);  //lose
        game.fightChallenge(8);  //lose
        game.fightChallenge(8);  //no one left
        int actual = game.fightChallenge(8);  //no one left
        assertEquals(expected, actual);
    }
    
    @Test
    public void defeatWithWithdrawalMoney() {
        int expected = 1000-400-200-170+100-170-170;
        Tournament.playerTeam = new LinkedHashSet<>();
        Treasury.balance = 1000;
        game.enterChampion("Rudolf");
        game.enterChampion("Flimsi");
        game.fightChallenge(8);  //lose
        game.retireChampion("Flimsi");
        game.fightChallenge(8);  //no one left
        game.fightChallenge(8);  //no one left
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
    @Test
    public void defeatNotAchieved() {
        Tournament.setUpChampion();
        Tournament.setUpChallenge();
        int expected = 2;
        game.enterChampion("Drabina");
        game.enterChampion("Flimsi");
        game.fightChallenge(4);  //lose as no one available
        game.fightChallenge(4);  //lose as no one available
        int actual = game.fightChallenge(4);  //lose as no one available
        assertEquals(expected, actual);
    }
    
    @Test
    public void defeatNotAchievedMoney() {
        Tournament.setUpChampion();
        Tournament.setUpChallenge();
        int expected = 1000-500-200-200-200-200;
        Tournament.playerTeam = new LinkedHashSet<>();
        Treasury.balance = 1000;
        game.enterChampion("Drabina");
        game.enterChampion("Flimsi");
        game.fightChallenge(4);  //lose as no one available
        game.fightChallenge(4);  //lose as no one available
        game.fightChallenge(4);  //lose as no one available
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }

    @Test
    public void defeatAchievedAfterWithdraw() {
        int expected = 3;
        //1000 - 500 - 200 - 200 -200 + 250 + 100 -200
        game.enterChampion("Drabina");
        game.enterChampion("Flimsi");
        game.fightChallenge(4);  //lose as no one available
        game.fightChallenge(4);  //lose as no one available
        game.retireChampion("Drabina");
        game.retireChampion("Flimsi"); //no one in Team
        game.fightChallenge(4);  //lose as no one available
        int actual = game.fightChallenge(4);  //lose as no one available
        assertEquals(expected, actual);
    }
    
    @Test
    public void defeatAchievedAfterWithdrawMoney() {
        Tournament.setUpChampion();
        Tournament.setUpChallenge();
        int expected = 1000-500-200-200-200+250+100-200-200;
        Tournament.playerTeam = new LinkedHashSet<>();
        Treasury.balance = 1000;
        game.enterChampion("Drabina");
        game.enterChampion("Flimsi");
        game.fightChallenge(4);  //lose as no one available
        game.fightChallenge(4);  //lose as no one available
        game.retireChampion("Drabina");
        game.retireChampion("Flimsi"); //no one in Team
        game.fightChallenge(4);  //lose as no one available
        game.fightChallenge(4);  //lose as no one available
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
// Add your own tests    

}
