package com.orbit.data.UI.tutorial.pages;

import com.kotcrab.vis.ui.widget.VisTextButton;
import com.orbit.data.UI.tutorial.TutorialPage;
import com.orbit.data.UI.tutorial.TutorialWindow;

/**
 * Created by fraayala19 on 1/24/18.
 */
public class Page3 extends TutorialPage {

    public Page3(TutorialWindow tutorialWindow, float width, VisTextButton previousButton, VisTextButton nextButton) {
        super(tutorialWindow, width, previousButton, nextButton);
    }

    @Override
    public void assignPages(float width) {
        previousPage = TutorialWindow.TWO;
        nextPage = TutorialWindow.FOUR;
    }

    @Override
    public void build(float width) {
        clear();

        addText("To zoom in and out, you can either use the scrollwheel on your mouse or the zoom tools on the " +
                "toolbar. Please try zooming out from the sun now.",width);

        row();

        addImage("tutorial/tutMag.png",width);

        row();

        addText("Zoom out until the sun looks about as small as a pinhead. You will notice that we can't see" +
                " any planets, even from here. Let's resolve this issue.", width);

        row();

        addImage("tutorial/tutSun.png",width);

        row();

        addText("Have a look at the \"Global Magnification\" window. With it, you can change the viewable size" +
                " of the planets to be multiple times their real size. Try using the slider to increase the size of the planets!", width);

        row();

        addImage("tutorial/tutGlobMag.png",width);

        row();

        addText("Now we can see the planets! Great!",width);

        row();

        addImage("tutorial/tutView.png",width);

        row();

        addText("To be more specific about this magnification factor, you can change the factor of each planet " +
                "in the planet list with each planet's respective slider. Try changing the size of venus.", width);

        row();

        addImage("tutorial/tutVenus.png",width);

        row();

        addText("Now that we can see the planets, let us see how we can get this simulation running a bit faster " +
                "than real time.",width);

        row();

    }

    @Override
    public String getPageNumber() {
        return "3";
    }
}
