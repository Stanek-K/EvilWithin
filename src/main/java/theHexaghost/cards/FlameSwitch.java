package theHexaghost.cards;

import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theHexaghost.HexaMod;
import theHexaghost.powers.BurnPower;

public class FlameSwitch extends AbstractHexaCard {
    public final static String ID = makeID("FlameSwitch");

    public FlameSwitch() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseTagMagic = tagMagic = 20;
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
        this.tags.add(HexaMod.DEALS_SOULBURN);
    }

    public void upp() {
        upgradeTagMagic(8);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        burn(m, tagMagic);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                AbstractPower po = m.getPower(BurnPower.POWER_ID);
                if (po instanceof TwoAmountPower)
                    ((TwoAmountPower) po).amount2 += magicNumber;
            }
        });
    }

    /*
    needs to implement OctopusCard
    public ArrayList<OctoChoiceCard> choiceList() {
        ArrayList<OctoChoiceCard> cardList = new ArrayList<>();
        cardList.add(new OctoChoiceCard("octo:OctoCrush", this.name, HexaMod.makeCardPath("FlameSwitch.png"), this.EXTENDED_DESCRIPTION[1]));
        cardList.add(new OctoChoiceCard("octo:OctoEmpower", this.name, HexaMod.makeCardPath("FlameSwitch.png"), this.EXTENDED_DESCRIPTION[0]));
        cardList.add(new OctoChoiceCard("octo:OctoSear", this.name, HexaMod.makeCardPath("FlameSwitch.png"), this.EXTENDED_DESCRIPTION[2]));

        return cardList;
    }

    public void doChoiceStuff(AbstractMonster m, OctoChoiceCard card) {
        switch (card.cardID) {
            case "octo:OctoCrush":
                atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        //   HexaMod.renderFlames = true;
                        isDone = true;
                        AbstractGhostflame gf = new CrushingGhostflame(GhostflameHelper.activeGhostFlame.lx, GhostflameHelper.activeGhostFlame.ly);
                        GhostflameHelper.hexaGhostFlames.set(GhostflameHelper.hexaGhostFlames.indexOf(GhostflameHelper.activeGhostFlame), gf);
                        gf.activate();
                    }
                });
                break;
            case "octo:OctoEmpower":
                atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        //  HexaMod.renderFlames = true;
                        isDone = true;
                        AbstractGhostflame gf = new BolsteringGhostflame(GhostflameHelper.activeGhostFlame.lx, GhostflameHelper.activeGhostFlame.ly);
                        GhostflameHelper.hexaGhostFlames.set(GhostflameHelper.hexaGhostFlames.indexOf(GhostflameHelper.activeGhostFlame), gf);
                        gf.activate();
                    }
                });
                break;
            case "octo:OctoSear":
                atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        //  HexaMod.renderFlames = true;
                        isDone = true;
                        AbstractGhostflame gf = new SearingGhostflame(GhostflameHelper.activeGhostFlame.lx, GhostflameHelper.activeGhostFlame.ly);
                        GhostflameHelper.hexaGhostFlames.set(GhostflameHelper.hexaGhostFlames.indexOf(GhostflameHelper.activeGhostFlame), gf);
                        gf.activate();
                    }
                });
                break;
        }

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new OctoChoiceAction(m, this));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            isEthereal = false;
            selfRetain = true;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
     */
}