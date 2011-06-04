package info.piwai.toohardforyou.gwt;

import info.piwai.toohardforyou.core.TooHardForYou;
import forplay.core.ForPlay;
import forplay.html.HtmlAssetManager;
import forplay.html.HtmlGame;
import forplay.html.HtmlPlatform;

public class TooHardForYouEntryPoint extends HtmlGame {

    @Override
    public void start() {
        HtmlAssetManager assets = HtmlPlatform.register().assetManager();
        assets.setPathPrefix("toohardforyou/");
        ForPlay.run(new TooHardForYou());        
    }

}
