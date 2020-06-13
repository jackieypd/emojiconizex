package com.jackieypd.emojiconizex;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.core.view.LayoutInflaterFactory;

import com.jackieypd.emojiconx.EmojiconEditText;
import com.jackieypd.emojiconx.EmojiconMultiAutoCompleteTextView;
import com.jackieypd.emojiconx.EmojiconTextView;

class EmojiconLayoutInflaterFactory implements LayoutInflaterFactory {
    private final Emojiconize.Builder builder;

    public EmojiconLayoutInflaterFactory(Emojiconize.Builder builder) {
        this.builder = builder;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = builder.activity.getDelegate().createView(parent, name, context, attrs);
        if (view instanceof EmojiconTextView
                || view instanceof EmojiconEditText
                || view instanceof EmojiconMultiAutoCompleteTextView) {
            return view;
        }

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            try {
                view = inflater.createView(name, null, attrs);
            } catch (Exception ignored) {
            }
        }

        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            textView.addTextChangedListener(new EmojiconTextWatcher(textView, builder));
        }
        return view;
    }
}
