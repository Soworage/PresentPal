package com.example.presentpal.view.adapter.binding;

import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

/**
 * Klasse, die BindingAdapter-Methoden für benutzerdefinierte Bindungen in XML-Layouts bereitstellt.
 */
public class BindingAdapters {
    /**
     * BindingAdapter, um ein Drawable links neben dem Text eines TextView zu setzen, kompatibel mit allen Android-Versionen.
     *
     * @param textView      Das TextView, an das das linke Drawable angehängt werden soll.
     * @param drawableResId Die Ressourcen-ID des Drawables, das links im TextView angezeigt werden soll.
     */
    /* ChatGPT
    prompt: Could you show me a binding adapter for "app:drawableLeftCompat" ?
     */
    @BindingAdapter("app:drawableLeftCompat")
    public static void setDrawableLeftCompat(TextView textView, Integer drawableResId) {
        if (drawableResId != null) {
            textView.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(textView.getContext(), drawableResId), null, null, null);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    /**
     * Setzt den Text eines TextViews auf den Wert einer Float-Variable. Wenn der Wert null ist, wird 0.00f angezeigt.
     *
     * @param view  Das TextView, dessen Text gesetzt werden soll.
     * @param value Der Float-Wert, der als Text im TextView angezeigt werden soll.
     */
    // https://stackoverflow.com/questions/57555280/android-two-way-databinding-float-to-edittext
    @BindingAdapter("android:text")
    public static void setText(TextView view, Float value) {
        if (value == null) {
            view.setText(String.valueOf(0.00f));
        } else {
            view.setText(String.valueOf(value));
        }


    }

    /**
     * InverseBindingAdapter, der den Text eines TextViews als Float zurückgibt.
     *
     * @param view Das TextView, von dem der Text gelesen wird.
     * @return Der im TextView enthaltene Text als Float-Wert. Gibt 0.00 zurück, wenn das Feld leer ist.
     */
    @InverseBindingAdapter(attribute = "android:text", event = "android:textAttrChanged")
    public static Float getTextString(TextView view) {
        String text = view.getText().toString();
        if (text.isEmpty()) {
            return 0.00f;
        } else {
            try {
                return Float.parseFloat(text);
            } catch (NumberFormatException e) {
                return 0.00f; // Rückfallwert, wenn die Umwandlung fehlschlägt.
            }
        }
    }
}