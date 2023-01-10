package core;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URL;
import java.nio.file.Path;
import java.util.*;
import java.io.*;
import java.util.concurrent.TransferQueue;

/**
 * This class implements the behaviour expected from the CORE
* as required for 6COM1037 Cwk Assignment - Nov 2022
 * 
 * @author 
 * @version 05/10/22
 */

public class Tournament implements CORE
{
    // Fields
    public static LinkedHashSet<Champion> championList = new LinkedHashSet<>();
    public static LinkedHashSet<Challenge> challengeList = new LinkedHashSet<>();
    public static LinkedHashSet<Champion> playerTeam = new LinkedHashSet<>();

    String playerName;

    Treasury treasury = new Treasury();

    
    //**************** CORE ************************** 


    public static void setUpChampion(){
        Champion ganfrank = new Champion("Ganfrank", new ChampionClass(Cons.wizard, 7, true, null, "transmutation", "", false), ChampionState.WAITING);
        championList.add(ganfrank);
        Champion rudolf = new Champion("Rudolf", new ChampionClass(Cons.wizard, 6, true, null, "invisibility", "", false),ChampionState.WAITING);
        championList.add(rudolf);
        Champion elblond = new Champion("Elblond", new ChampionClass(Cons.warrior, 1, false, 150, "", "sword", false), ChampionState.WAITING);
        championList.add(elblond);
        Champion flimsi = new Champion("Flimsi", new ChampionClass(Cons.warrior, 2, false, 200, "", "", false) ,ChampionState.WAITING);
        championList.add(flimsi);
        Champion dranina = new Champion("Drabina", new ChampionClass(Cons.dragon, 7, false, 500, "", "", false), ChampionState.WAITING);
        championList.add(dranina);
        Champion golum = new Champion("Golum", new ChampionClass(Cons.dragon, 7, false, 500, "", "", false), ChampionState.WAITING);
        championList.add(golum);
        Champion argon = new Champion("Argon", new ChampionClass(Cons.warrior, 9, false, 900, "", "mace", false), ChampionState.WAITING);
        championList.add(argon);
        Champion neon = new Champion("Neon", new ChampionClass(Cons.wizard, 2, false, 300, "translocation", "", false), ChampionState.WAITING);
        championList.add(neon);
        Champion atlanta = new Champion("Atlanta", new ChampionClass(Cons.warrior, 5, false, 500, "", "bow", false), ChampionState.WAITING);
        championList.add(atlanta);
        Champion kryton = new Champion("Kryton", new ChampionClass(Cons.wizard, 8, false, 300, "fireballs", "", false), ChampionState.WAITING);
        championList.add(kryton);
        Champion hedwig = new Champion("Hedwig", new ChampionClass(Cons.wizard, 1, true, 400, "flying", "", false), ChampionState.WAITING);
        championList.add(hedwig);
    }
    public static void setUpChallenge(){
        Challenge chal1 = new Challenge(1, ChallengeType.MAGIC, "Borg", 3, 100);
        challengeList.add(chal1);
        Challenge chal2 = new Challenge(2, ChallengeType.FIGHT, "Huns", 3, 120);
        challengeList.add(chal2);
        Challenge chal3 = new Challenge(3, ChallengeType.MYSTERY, "Ferengi", 3, 150);
        challengeList.add(chal3);
        Challenge chal4 = new Challenge(4, ChallengeType.MAGIC, "Vadal", 9, 200);
        challengeList.add(chal4);
        Challenge chal5 = new Challenge(5, ChallengeType.MYSTERY, "Borg", 7, 90);
        challengeList.add(chal5);
        Challenge chal6 = new Challenge(6, ChallengeType.FIGHT, "Goth", 8, 45);
        challengeList.add(chal6);
        Challenge chal7 = new Challenge(7, ChallengeType.MAGIC, "Frank", 10, 200);
        challengeList.add(chal7);
        Challenge chal8 = new Challenge(8, ChallengeType.FIGHT, "Sith", 10, 170);
        challengeList.add(chal8);
        Challenge chal9 = new Challenge(9, ChallengeType.MYSTERY, "Cardashian", 9, 300);
        challengeList.add(chal9);
        Challenge chal10 = new Challenge(10, ChallengeType.FIGHT, "Jute", 2, 300);
        challengeList.add(chal10);
        Challenge chal11 = new Challenge(11, ChallengeType.MAGIC, "Celt", 2, 250);
        challengeList.add(chal11);
        Challenge chal12 = new Challenge(12, ChallengeType.MYSTERY, "Celt", 1, 250);
        challengeList.add(chal12);
    }


    public static void main(String[] args){

    }

    /** Constructor requires the name of the player
     * @param pl the name of the player
     */
    public Tournament(String pl)
    {
       playerName = pl;
    }
    
    
    //******* Implements interface CORE *******************
    /**Returns a String representation of the state of the game,
     * including the name of the player, state of the treasury,
     * whether defeated or not, and the champions currently in the 
     * team,(or, "No champions" if team is empty)
     * 
     * @return a String representation of the state of the game,
     * including the name of the player, state of the treasury,
     * whether defeated or not, and the champions currently in the 
     * team,(or, "No champions" if team is empty)
     */
    public String toString() {
        String gameStatus;
        String teamStatus;
        if(!isDefeated()){
            gameStatus = "Is OK";
        }else {
            gameStatus = "Defeated";
        }

        if(playerTeam.isEmpty()){
            teamStatus = "No champions";
        }else {
            teamStatus = playerTeam.toString();
        }


        return playerName + " " +
                Treasury.balance + " " +
                gameStatus + " " +
                teamStatus;
     }
  
    /** returns true if Treasury <=0 and the player's team has no 
     * champions which can be withdrawn. 
     * @return true if Treasury <=0 and the player's team has no 
     * champions which can be decommissioned. 
     */
    public boolean isDefeated(){
        int count = 0;
        for(Champion ch : playerTeam){
            if(ch.getState() == ChampionState.DEAD){
                count ++;
            }
        }
        if(count == playerTeam.size() && Treasury.balance < 0){
            return true;
        }

        return false;
    }
    
    /** returns the amount of money in the Treasury
     * @return the amount of money in the Treasury
     */
    public int getMoney(){
        
       return Treasury.balance;
    }    
    
    /**Returns a String representation of all champions in reserve
     * @return a String representation of all champions in reserve
     **/
    public String getReserve(){
        setUpChampion();
       return championList.toString();
    }
       
    /** Returns details of any champion with the given name
     * @param nme is the name of the champion
     * @return details of any champion with the given name
     **/
    public String getChampionDetails(String nme){
        Champion result = null;
        for(Champion ch : championList){
            if(ch.getName().equals(nme)){
                result = ch;
                System.out.println(ch.state);
            }
        }
        return result.toString();
    }
    
    /** returns whether champion is in reserve
    * @param nme is the name of the champion
    * @return true if champion in reserve, false otherwise
    */
    public boolean isInReserve(String nme) {
        setUpChampion();
        for(Champion ch: playerTeam){
            if(ch.getName().equals(nme)){
                return false;
            }
        }
        return true;
    }
    
 // ***************** Players Team************************   
    /** Allows a champion to be entered for the player's team, if there 
     * is enough money in the Treasury for the entry fee.The champion's 
     * state is set to "active"
     * 0 if champion is entered in the player's team, 
     * 1 if champion is not in reserve,
     * 2 if not enough money in the treasury, 
     * -1 if there is no such champion 
     * @param nme represents the name of the champion
     * @return as shown above
     **/        
    public int enterChampion(String nme){
        for(Champion ch : championList){
            if(ch.getName().equals(nme)){
                if(Treasury.balance < ch.getChampClass().getEntryFee()){
                    return 2;
                }else {
                    ch.setState(ChampionState.ACTIVE);
                    if(playerTeam.add(ch)){
                        championList.remove(ch);
                        Treasury.balance -= ch.getChampClass().getEntryFee();
                        return 0;
                    }else {
                        ch.setState(ChampionState.WAITING);
                        return 1;
                    }
                }
            }
        }
        return -1;
    }
    
        
    /** Returns true if the champion with the name is in 
     * the player's team, false otherwise.
     * @param nme is the name of the champion
     * @return  true if the champion with the name
     * is in the player's team, false otherwise.
     **/
    public boolean isInPlayersTeam(String nme){
        for(Champion ch : playerTeam){
            if(ch.getName().equals(nme)){
                return true;
            }
        }
        return false;
    }

    /** Removes a champion from the team to the reserves (if they are in the team)
     * Pre-condition: isChampion()
     * 0 - if champion is retired to reserves
     * 1 - if champion not retired because dead
     * 2 - if champion not retired because not in team
     * -1 - if no such champion
     * @param nme is the name of the champion
     * @return as shown above 
     **/
    public int retireChampion(String nme){
        int count = 0;
        for(Champion ch : playerTeam){
            count++;
            if(ch.getName().equals(nme)){
                if(ch.state.equals(ChampionState.DEAD)){
                    return 1;
                }else {
                    Treasury.balance += ch.getChampClass().getEntryFee()/2;
                    playerTeam.remove(ch);
                    return 0;
                }
            }
        }
        if(count == playerTeam.size()){
            return 2;
        }
       return -1;
    }
        
        
    /**Returns a String representation of the champions in the player's team
     * or the message "No champions entered"
     * @return a String representation of the champions in the player's team
     **/
    public String getTeam(){
        
       return "";
    }
    
    
//**********************Challenges************************* 
    /** returns true if the number represents a challenge
     * @param num is the number of the challenge
     * @return true if the number represents a challenge
     **/
     public boolean isChallenge(int num){
         setUpChallenge();
         for(Challenge cha : challengeList){
             if(cha.challengeNo == num) {
                 return true;
             }
         }
         return false;
    }
     
    /** Provides a String representation of an challenge given by 
     * the challenge number
     * @param num the number of the challenge
     * @return returns a String representation of a challenge given by 
     * the challenge number
     **/
    public String getChallenge(int num){
        setUpChallenge();
        String type = null;
        for(Challenge ch : challengeList){
            if(ch.challengeNo == num){
                type = String.valueOf(ch.getType());
            }
        }
        return " " + type;
    }
    
    /** Provides a String representation of all challenges 
     * @return returns a String representation of all challenges
     **/
    public String getAllChallenges(){
        setUpChallenge();
        ArrayList<String> challengeDetail = new ArrayList<>();
       for(Challenge ch : challengeList){
            challengeDetail.add(String.valueOf(ch));
        }
        String result = String.join(", ", challengeDetail);
        return result;
    }
    
    /** Retrieves the challenge represented by the challenge 
     * number.Finds a champion from the team which can challenge the 
     * challenge. The results of fighting an challenge will be 
     * one of the following:  
     * 0 - challenge won, add reward to the treasury, 
     * 1 - challenge lost on battle skills  - deduct reward from
     * treasury and record champion as "dead"
     * 2 - challenge lost as no suitable champion is  available, deduct
     * the reward from treasury 
     * 3 - If a challenge is lost and player completely defeated (no money and 
     * no champions to withdraw) 
     * -1 - no such challenge 
     * @param chalNo is the number of the challenge
     * @return an int showing the result(as above) of fighting the challenge
     */ 
    public int fightChallenge(int chalNo){
        //Get Challenge
        Challenge targetedChallenge = null;
        for(Challenge challenge : challengeList){

            if(challenge.getChallengeNo() == chalNo){
                targetedChallenge = challenge;
            }
        }

        if(targetedChallenge == (null)){
            return -1;
        } else {
            for(Champion ch : playerTeam){
                if(ch.getChampClass().name.equals(Cons.wizard) && ch.state.equals(ChampionState.ACTIVE)){
                    if(ch.getChampClass().getSkillLevel() > targetedChallenge.skillRequired){
                        Treasury.balance += targetedChallenge.Reward;
                        return 0;
                    }else {
                        Treasury.balance -= targetedChallenge.Reward;
                        ch.state = ChampionState.DEAD;
                        championList.add(ch);
                        return 1;
                    }
                }
                if(ch.getChampClass().name.equals(Cons.warrior) && targetedChallenge.type.equals(ChallengeType.FIGHT) && ch.state.equals(ChampionState.ACTIVE)){
                    if(ch.getChampClass().getSkillLevel() > targetedChallenge.skillRequired ){
                        Treasury.balance += targetedChallenge.Reward;
                        return 0;
                    }else {
                        Treasury.balance -= targetedChallenge.Reward;
                        ch.state = ChampionState.DEAD;
                        championList.add(ch);
                        return 1;
                    }
                }

                if(ch.getChampClass().name.equals(Cons.dragon) &&
                        (targetedChallenge.type.equals(ChallengeType.FIGHT) || targetedChallenge.type.equals(ChallengeType.MYSTERY))
                        && (ch.state.equals(ChampionState.ACTIVE))){
                    if(targetedChallenge.type.equals(ChallengeType.FIGHT)){
                        if(ch.getChampClass().getSkillLevel() > targetedChallenge.skillRequired){
                            Treasury.balance += targetedChallenge.Reward;
                            return 0;
                        }else {
                            Treasury.balance -= targetedChallenge.Reward;
                            ch.state = ChampionState.DEAD;
                            championList.add(ch);
                            return 1;
                        }
                    }

                    if(targetedChallenge.type.equals(ChallengeType.MYSTERY)){
                        if(ch.getChampClass().talk){
                            if(ch.getChampClass().getSkillLevel() > targetedChallenge.skillRequired){
                                Treasury.balance += targetedChallenge.Reward;
                                return 0;
                            }else {
                                Treasury.balance -= targetedChallenge.Reward;
                                ch.state = ChampionState.DEAD;
                                championList.add(ch);
                                if(Treasury.balance < 0){
                                    for(Champion ch1 : playerTeam){
                                        if(!ch1.state.equals(ChampionState.DEAD)){
                                            return 3;
                                        }
                                    }
                                }
                                return 1;
                            }
                        }
                    }
                }
            }
            Treasury.balance -= targetedChallenge.getReward();
            int count = 0;
            for(Champion ch : playerTeam){
                if(ch.state.equals(ChampionState.DEAD)){
                    count++;
                }
            }
            if(count == playerTeam.size() && Treasury.balance < 0){
                return 3;
            }
            return 2;
        }
    }
  
// These methods are not needed until Task 4.4
    // ***************   file write/read  *********************
    /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
    public void saveGame(String fname) throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("playerName", ow.writeValueAsString(playerName));
        jsonObj.put("treasury", ow.writeValueAsString(Treasury.balance));
        jsonObj.put("championList", ow.writeValueAsString(championList));
        jsonObj.put("playerTeam", ow.writeValueAsString(playerTeam));
        //URL url = getClass().getClassLoader().getResource("savedGame");
        OutputStream fos = new FileOutputStream(Path.of("savedGame").toAbsolutePath().toString()+"/"+fname);
        fos.write(jsonObj.toString().getBytes());

    }
    private static String readString(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream result = new ByteArrayOutputStream();
        final byte[] buffer = new byte[1024];
        int length;

        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        return result.toString();
    }

    /** reads all information about the game from the specified file
     * and returns a CORE reference to a Tournament object
     * @param fname name of file storing the game
     * @return the game (as a Tournament object)
     */
    public CORE loadGame(String fname) throws Exception {
        InputStream fis = new FileInputStream(Path.of("savedGame").toAbsolutePath().toString()+"/"+fname);
        String fileReader = readString(fis).trim();
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(fileReader);
        System.out.println(jsonObj.get("playerName"));




        CORE core = new Tournament((String) jsonObj.get("playerName"));
        Treasury.balance = Integer.parseInt((String) jsonObj.get("treasury"));
        Object arr = jsonObj.get("championList");
        Tournament.championList = new LinkedHashSet<>();
        Object obj = parser.parse((String) arr);
        JSONArray jarr = (JSONArray) obj;
        System.out.println(jarr);
        for(Object ch : jarr){
            Champion cham = new Champion();
            JSONObject jObj1 = (JSONObject) ch;
            cham.setName((String) jObj1.get("name"));
            for(ChampionState championState : ChampionState.values()){
                if(championState.equals(jsonObj.get("state"))){
                    cham.setState(championState);
                }
            }
            JSONObject jsonChampionClass = (JSONObject) jObj1.get("champClass");

            ChampionClass championClass = new ChampionClass((String) jsonChampionClass.get("name"),
                    ((Long) jsonChampionClass.get("skillLevel")).intValue(), (Boolean) jsonChampionClass.get("nacromancer"),
                    ((Long) jsonChampionClass.get("entryFee")).intValue(), (String) jsonChampionClass.get("spell"),
                    (String) jsonChampionClass.get("weapon"), (Boolean) jsonChampionClass.get("talk"));

            cham.setChampClass(championClass);
            Tournament.championList.add(cham);
            }

        Object arr1 = jsonObj.get("playerTeam");
        Tournament.playerTeam = new LinkedHashSet<>();
        Object obj1 = parser.parse((String) arr1);
        JSONArray jarr1 = (JSONArray) obj1;
        System.out.println(jarr1);
        for(Object ch : jarr1){
            Champion cham = new Champion();
            JSONObject jObj1 = (JSONObject) ch;
            cham.setName((String) jObj1.get("name"));
            for(ChampionState championState : ChampionState.values()){
                if(championState.equals(jsonObj.get("state"))){
                    cham.setState(championState);
                }
            }
            JSONObject jsonChampionClass = (JSONObject) jObj1.get("champClass");

            ChampionClass championClass = new ChampionClass((String) jsonChampionClass.get("name"),
                    ((Long) jsonChampionClass.get("skillLevel")).intValue(), (Boolean) jsonChampionClass.get("nacromancer"),
                    ((Long) jsonChampionClass.get("entryFee")).intValue(), (String) jsonChampionClass.get("spell"),
                    (String) jsonChampionClass.get("weapon"), (Boolean) jsonChampionClass.get("talk"));

            cham.setChampClass(championClass);
            Tournament.playerTeam.add(cham);
        }



        System.out.println(Tournament.championList);


       return core;
    }

    /**
     * reads challenges from a comma-separated textfile and stores
     * @param filename of the comma-separated textfile storing information about challenges
     */
    public void readChallenges(String filename){

    }
}



