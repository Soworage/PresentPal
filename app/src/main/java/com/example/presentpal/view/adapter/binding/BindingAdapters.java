package com.example.presentpal.view.adapter.binding;

import android.widget.TextView;
        import androidx.core.content.ContextCompat;
        import androidx.databinding.BindingAdapter;

public class BindingAdapters {

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
}