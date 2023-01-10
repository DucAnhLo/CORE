package core;

import java.util.Objects;

public class Challenge {
    Integer challengeNo;
    ChallengeType type;
    String enemyName;
    Integer skillRequired;
    Integer Reward;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Challenge challenge = (Challenge) o;
        return Objects.equals(challengeNo, challenge.challengeNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(challengeNo);
    }

    public Challenge(Integer challengeNo, ChallengeType type, String enemyName, Integer skillRequired, Integer reward) {
        this.challengeNo = challengeNo;
        this.type = type;
        this.enemyName = enemyName;
        this.skillRequired = skillRequired;
        Reward = reward;
    }

    public Integer getChallengeNo() {
        return challengeNo;
    }

    public void setChallengeNo(Integer challengeNo) {
        this.challengeNo = challengeNo;
    }

    public ChallengeType getType() {
        return type;
    }

    public void setType(ChallengeType type) {
        this.type = type;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public Integer getSkillRequired() {
        return skillRequired;
    }

    public void setSkillRequired(Integer skillRequired) {
        this.skillRequired = skillRequired;
    }

    public Integer getReward() {
        return Reward;
    }

    public void setReward(Integer reward) {
        Reward = reward;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "challengeNo=" + challengeNo +
                ", type=" + type +
                ", enemyName='" + enemyName + '\'' +
                ", skillRequired=" + skillRequired +
                ", Reward=" + Reward +
                '}';
    }
}
