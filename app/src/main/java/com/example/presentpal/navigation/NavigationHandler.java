package com.example.presentpal.navigation;

/**
 * Interface zur Handhabung der Navigation innerhalb der App.
 */
public interface NavigationHandler {

    /**
     * Navigiert zur Hauptbildschirm-Aktivität.
     */
    void navigateToMainScreenActivity();

    /**
     * Navigiert zur OptionsDialog
     */
    void navigateToShowOptionsDialog();

    /**
     * Navigiert zur Ansicht, um alle Elemente anzuzeigen.
     */
    void navigateToViewAll();
}
