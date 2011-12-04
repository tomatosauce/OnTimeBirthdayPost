/*
 * Copyright (C) 2011 Deepika Padmanabhan.
 * This file is part of OnTimeBirthdayPost.
 * Contact the developers at tsaucestudio@gmail.com

 * OnTimeBirthdayPost is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * OnTimeBirthdayPost is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with OnTimeBirthdayPost.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.TomatoSauceStudio.OnTimeBirthdayPost;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.AlignmentSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.widget.TextView;
import android.widget.TextView.BufferType;

/**
 * This is just to display formatted text in the about screen.
 */
public class obpAbout extends Activity {
	@Override
	protected void onCreate(Bundle s) {
		super.onCreate(s);
		setContentView(R.layout.about_page);
		TextView t = (TextView)findViewById(R.id.about);
		
		SpannableStringBuilder str = new SpannableStringBuilder("Hello There!\n\n");
		str.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),0,str.length(),0);
		str.setSpan(new StyleSpan(Typeface.BOLD),0,str.length(),0);
		str.setSpan(new RelativeSizeSpan(1.3f),0,str.length(),0);
		int oldLen = str.length();
		
		str.append("OnTime Birthday Post is a Facebook app from Tomato Sauce Studio!\n\n");
		str.setSpan(new RelativeSizeSpan(1.3f),oldLen,oldLen+1,0);
		oldLen = str.length();
		
		str.append("Tomato Sauce is all about fixing the hitches & glitches in our digital lives over lazy Sunday afternoons!\n\n");
		str.setSpan(new RelativeSizeSpan(1.3f),oldLen,oldLen+1,0);
		oldLen = str.length();
		
		str.append("OnTime Birthday Post is fully free and open source.\n\n");
		str.setSpan(new RelativeSizeSpan(1.3f),oldLen,oldLen+1,0);
		oldLen = str.length();
		
		str.append("See our source code here so you can see how it all works\n\n");
		str.setSpan(new URLSpan("http://github.com/tomatosauce/OnTimeBirthdayPost"),oldLen+15,oldLen+20,0);
		str.setSpan(new RelativeSizeSpan(1.3f),oldLen,oldLen+1,0);
		oldLen = str.length();
		
		str.append("License:\n\n");
		str.setSpan(new StyleSpan(Typeface.BOLD),oldLen,str.length(),0);
		str.setSpan(new RelativeSizeSpan(1.2f),oldLen,str.length(),0);
		oldLen = str.length();
		
		str.append("OnTime Birthday Post is under the GPL v3.0 license. More info in the source code repo.\n");
		str.setSpan(new RelativeSizeSpan(1.3f),oldLen,oldLen+1,0);
		oldLen = str.length();
		
		str.append("Contact:\n\n");
		str.setSpan(new StyleSpan(Typeface.BOLD),oldLen,str.length(),0);
		str.setSpan(new RelativeSizeSpan(1.2f),oldLen,str.length(),0);
		oldLen = str.length();
		str.append("Email us for any questions.");
		str.setSpan(new URLSpan("mailto:tsaucestudio@gmail.com"),oldLen,oldLen+8,0);
		
		
		t.setMovementMethod(LinkMovementMethod.getInstance());
		t.setText(str, BufferType.SPANNABLE);
	}
}
