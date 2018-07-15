import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.combat.DaggerSprayEffect;

import java.util.Iterator;

public class RepurposeAction extends AbstractGameAction {

    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private int damage;
    private int block;
    private int draw;

    public RepurposeAction(int damage, int block, int magicNumber) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;

        this.damage = damage;
        this.block = block;
        this.draw = magicNumber;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
            } else if (this.p.hand.size() == 1) {
                addActionsToManager(this.p.hand.getBottomCard());

                this.p.hand.moveToExhaustPile(this.p.hand.getBottomCard());
                this.tickDuration();
            } else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                AbstractCard c;
                for(Iterator var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator(); var1.hasNext(); this.p.hand.moveToExhaustPile(c)) {
                    c = (AbstractCard)var1.next();
                    addActionsToManager(c);
                }

                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            }

            this.tickDuration();
        }
    }

    private void addActionsToManager(AbstractCard bottomCard) {
        if (bottomCard.type.equals(AbstractCard.CardType.SKILL)) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new DaggerSprayEffect(), 0.0F));
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(this.p, new int[]{damage}, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
        } else if (bottomCard.type.equals(AbstractCard.CardType.ATTACK)) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.p, this.p, block));
        } else if (bottomCard.type.equals(AbstractCard.CardType.POWER)) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.p, draw));

        }
    }


    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(RepurposeAction.class.getName());
        TEXT = uiStrings.TEXT;
    }
}
