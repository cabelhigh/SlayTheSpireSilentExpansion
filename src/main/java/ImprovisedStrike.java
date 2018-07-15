import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImprovisedStrike extends CustomCard {

    private static final Logger logger = LogManager.getLogger(ImprovisedStrike.class.getName());

    public static final String ID = "Improvised Strike";
    public static final String IMG = "images/Test.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 2;

    public ImprovisedStrike() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.ATTACK,
                CardColor.GREEN,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY);

        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ImprovisedStrike();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        logger.info("Magic number is " + this.magicNumber);
        logger.info("Hand size is " + p.hand.group.size());
        logger.info("Upgraded is " + this.upgraded);

        AbstractDungeon.actionManager.addToBottom(new DamageAction(
                m,
                new DamageInfo(
                        p,
                        (p.hand.group.size() - 1) * this.magicNumber,
                        this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

}
