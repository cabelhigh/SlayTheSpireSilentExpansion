import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RegenAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RiskyGambit extends CustomCard {

    private static final Logger logger = LogManager.getLogger(ImprovisedStrike.class.getName());

    public static final String ID = "Risky Gambit";
    public static final String IMG = "images/Test.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final int COST = 1;

    public RiskyGambit() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.ATTACK,
                CardColor.GREEN,
                CardRarity.RARE,
                CardTarget.SELF);

        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new RiskyGambit() ;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(
                        abstractPlayer,
                        abstractPlayer,
                        new DexterityPower(AbstractDungeon.player, -1),
                        -1));
        AbstractDungeon.actionManager.addToBottom(new RegenAction(abstractPlayer, abstractPlayer.hand.size()-1));
    }
}
