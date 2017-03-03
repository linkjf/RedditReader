package com.android.redditreader.views.viewdefinition;

/**
 * Interface definition for a view basic functionality.
 */
public interface BasicUIView {

    /**
     * Show view loading screen.
     *
     * @param title title to put in loading screen.
     */
    public void showLoadingScreen(String title);

    /**
     * Hide view loading screen.
     */
    public void hideLoadingScreen();

    /**
     * Show an error in the view.
     *
     * @param title Error title.
     * @param error Error message.
     */
    public void showError(String title, String error);

    /**
     * Method call if the view main responsibility is complete
     */
    public void success();

}
