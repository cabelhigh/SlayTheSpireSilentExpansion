import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MasterPlan extends CustomCard {

    public static final String ID = "Master Plan";
    public static final String IMG = "images/Test.png";
    private static final CardStrings cardString = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardString.NAME;
    public static final String DESCRIPTION = cardString.DESCRIPTION;
    public static final int COST = 3;

    public MasterPlan() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.SKILL,
                CardColor.GREEN,
                CardRarity.COMMON,
                CardTarget.SELF);

        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(-1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MasterPlan();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new MasterPlanAction(this.magicNumber));
    }
}
