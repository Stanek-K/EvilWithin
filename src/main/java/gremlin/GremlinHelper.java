package gremlin;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import gremlin.orbs.GremlinStandby;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GremlinHelper { //TODO Rebuild me! and do it without Orbs!!
    public ArrayList<String> gremlins;
    public ArrayList<Integer> gremlinHP;
    public boolean inCombat = false;
    // Workaround for not have ascension available at creation
    public boolean unset = false;

    public GremlinHelper() {
        gremlins = new ArrayList<>();
        gremlinHP = new ArrayList<>();
    }

    public void initialRandom(int hp){
        for(int i = 0; i<5; i++ ){
            gremlinHP.add(hp);
        }
        gremlins.add("angry");
        gremlins.add("fat");
        gremlins.add("shield");
        gremlins.add("sneak");
        gremlins.add("wizard");
        if(AbstractDungeon.miscRng != null) {
            Collections.shuffle(gremlins, AbstractDungeon.miscRng.random);
        }
        unset = true;
    }

    public void setAll(int hp) {
        unset = false;
        for(int i = 0; i < 5; i++ ){
            gremlinHP.set(i, hp);
        }
    }

    public String getFrontGremlin() {
        return gremlins.get(0);
    }

    // Never returns the active gremlin
    public String getRearLivingGremlin() {
        String returnVal = "";
        for(int i=1; i<5; i++){
            if(gremlinHP.get(i) > 0){
                returnVal = gremlins.get(i);
            }
        }
        return returnVal;
    }

    public String getFrontAnimation() {
        return GremlinMod.getGremlinOrb(gremlins.get(0), 0).animationName;
    }

    public void postBossHeal(int max, float multiplier){
        for(int i = 0; i<5; i++ ){
            int hp = gremlinHP.get(i);
            if (hp > 0) {
                int heal = MathUtils.round((max - hp) * multiplier);
                gremlinHP.set(i, hp + heal);
            }
        }
    }

    public void damageAll(int dmg){
        for(int i = 0; i<5; i++ ){
            int hp = gremlinHP.get(i);
            if(hp > 0) {
                if(hp > dmg) {
                    gremlinHP.set(i, hp - dmg);
                }
                else {
                    gremlinHP.set(i, 0);
                }
            }
        }
    }

    public void campfireHeal(int heal, int max){
        for(int i = 0; i<5; i++ ){
            int hp = gremlinHP.get(i);
            if(hp > 0) {
                int newHp = hp + heal;
                if(newHp > max){
                    newHp = max;
                }
                gremlinHP.set(i, newHp);
            }
        }
    }

    public void setToMax(int max) {
        for(int i = 0; i<5; i++ ){
            int hp = gremlinHP.get(i);
            if(hp > 0) {
                gremlinHP.set(i, max);
            }
        }
    }

    public boolean canRez() {
        for(int i=1; i<5; i++){
            if(gremlinHP.get(i) <= 0){
                return true;
            }
        }
        return false;
    }

    public void resurrect(int max){
        GremlinMod.logger.debug(this);
        int firstDead = -1;
        for(int i=1; i<5; i++){
            if(gremlinHP.get(i) <= 0){
                firstDead = i;
                break;
            }
        }
        if(firstDead < 0){
            return;
        }
        ArrayList<String> deadGrems = new ArrayList<>();
        for(int i=firstDead; i<5; i++){
            deadGrems.add(gremlins.get(i));
        }
        Random rand = new Random();
        String randomGrem = deadGrems.get(rand.nextInt(deadGrems.size()));
        swap(randomGrem, firstDead);
        gremlinHP.set(firstDead, max);
        GremlinMod.logger.debug(this);
    }

    public void startOfBattle(GremlinCharacter character){
        inCombat = true;
        GremlinStandby grem = GremlinMod.getGremlinOrb(gremlins.get(0));
        character.swapBody(grem.assetFolder, grem.animationName);
        for(int i = 4; i > 0; i--){
            if(gremlinHP.get(i) > character.maxHealth){
                gremlinHP.set(i, character.maxHealth);
            }
            if(gremlinHP.get(i) > 0){
                AbstractDungeon.actionManager.addToTop(new ChannelAction(
                        GremlinMod.getGremlinOrb(gremlins.get(i), gremlinHP.get(i))
                ));
            }
        }
    }

    private void swap(String gremlin, int pos) {
        if(!gremlins.get(pos).equals(gremlin)){
            int otherPos = gremlins.indexOf(gremlin);
            GremlinMod.logger.debug(otherPos);
            String temp = gremlins.get(otherPos);
            GremlinMod.logger.debug(temp);
            gremlins.set(otherPos, gremlins.get(pos));
            gremlins.set(pos, temp);
        }
    }

    public void updateMobState(GremlinCharacter character){
        inCombat = false;
        swap(character.currentGremlin, 0);
        gremlinHP.set(0, character.currentHealth);

        int index = 1;

        for(int i=0;i<character.maxOrbs;i++){
            if(character.orbs.get(i) instanceof GremlinStandby){
                GremlinStandby grem = (GremlinStandby) AbstractDungeon.player.orbs.get(i);
                swap(grem.assetFolder, index);
                gremlinHP.set(index, grem.hp);
                index++;
            }
        }
        for(int i=index;i<5;i++){
            gremlinHP.set(i, 0);
        }
        GremlinMod.logger.debug(this);
    }

    public String getGremlinName(int index) {
        return GremlinMod.getGremlinOrb(gremlins.get(index)).name;
    }

    public int getGremlinHP(int index) {
        String grem = gremlins.get(index);
        if (gremlinHP.get(index) < 0) {
            return 0;
        }
        return gremlinHP.get(index);
    }
}
