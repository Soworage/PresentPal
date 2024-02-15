package com.example.presentpal.view.adapter.binding;

import android.widget.TextView;
        import androidx.core.content.ContextCompat;
        import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

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


    // https://stackoverflow.com/questions/57555280/android-two-way-databinding-float-to-edittext
    @BindingAdapter("android:text")
    public static void setText(TextView view, Float value) {
        if (value == null){
            view.setText(String.valueOf(0.00f));
        }else {
            view.setText(String.valueOf(value));
        }


    }

    @InverseBindingAdapter(attribute = "android:text", event = "android:textAttrChanged")
    public static Float getTextString(TextView view) {
        if(view.getText().toString().equals("")){
           return Float.valueOf("0.00");
        }
        else{
            return Float.valueOf(view.getText().toString());
        }
    }
}