import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Repurpose extends CustomCard {
    public static final String ID = "Repurpose";
    public static final String IMG = "images/Test.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int DAMAGE = 5;
    private static final int BLOCK = 7;
    private static final int DRAW = 2;

    public Repurpose() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.SKILL,
                CardColor.GREEN,
                CardRarity.UNCOMMON,
                CardTarget.SELF);

        this.baseDamage = DAMAGE;
        this.baseBlock = BLOCK;
        this.baseMagicNumber = DRAW;

        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
            this.upgradeBlock(2);
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Repurpose();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new RepurposeAction(this.damage, this.block, this.magicNumber));
    }
}
