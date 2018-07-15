import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;


@SpireInitializer
public class FiveNewGreenCards implements PostInitializeSubscriber, EditCardsSubscriber, EditStringsSubscriber {

    private static final Logger logger = LogManager.getLogger(FiveNewGreenCards.class.getName());

    private static final String MODNAME = "Five New Greens";
    private static final String AUTHOR = "Cabelhigh";
    private static final String DESCRIPTION = "New cards for the Silent, based around exhausting cards and large hand sizes.";

    public FiveNewGreenCards(){
        BaseMod.subscribe(this);
    }

    public static void initialize(){
        FiveNewGreenCards fiveNewGreenCards = new FiveNewGreenCards();
    }

    @Override
    public void receiveEditCards() {
        logger.info("Begin adding new green cards");
        BaseMod.addCard(new Repurpose());
        BaseMod.addCard(new MasterPlan());
        BaseMod.addCard(new ImprovisedStrike());
        BaseMod.addCard(new RiskyGambit());
        BaseMod.addCard(new HastyDefense());
        logger.info("Finish adding new green cards");
    }

    @Override
    public void receivePostInitialize() {
        Texture badgeTexture = new Texture("images/Test.png");
        ModPanel settingsPanel = new ModPanel();
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
    }

    @Override
    public void receiveEditStrings() {
        logger.info("begin adding new strings");
        BaseMod.loadCustomStrings(CardStrings.class, loadJson("localization/eng/new-cards.json"));
        BaseMod.loadCustomStrings(UIStrings.class, loadJson("localization/eng/new-ui.json"));
        BaseMod.loadCustomStrings(PowerStrings.class, loadJson("localization/eng/new-powers.json"));
        logger.info("end adding new strings");
    }

    private static String loadJson(String jsonPath) {
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
    }
}
