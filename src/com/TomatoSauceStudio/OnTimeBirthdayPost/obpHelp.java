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
import android.text.style.*;
import android.widget.TextView;
import android.widget.TextView.BufferType;

/**
 * This is just to display formatted text in the help screen.
 */
public class obpHelp extends Activity {
	@Override
	protected void onCreate(Bundle s) {
		super.onCreate(s);
		setContentView(R.layout.help_page);
		TextView t = (TextView)findViewById(R.id.help);
		SpannableStringBuilder str = new SpannableStringBuilder("Hello There!\n\n");
		str.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),0,str.length(),0);
		str.setSpan(new StyleSpan(Typeface.BOLD),0,str.length(),0);
		str.setSpan(new RelativeSizeSpan(1.3f),0,str.length(),0);
		int oldLen = str.length();
		
		str.append("Click on friends in the list to visit their wall in a browser!\n\n");
		str.setSpan(new RelativeSizeSpan(1.3f),oldLen,oldLen+1,0);
		oldLen = str.length();
		str.append("Highlighted friends are celebrating their birthdays now across the world, the app takes timezones into account so you never miss birthdays!\n\n");
		str.setSpan(new RelativeSizeSpan(1.3f),oldLen,oldLen+1,0);
		oldLen = str.length();
		str.append("It takes a while to fetch your data from facebook the first time. Should be speedy thereafter!\n\n");
		str.setSpan(new RelativeSizeSpan(1.3f),oldLen,oldLen+1,0);
		oldLen = str.length();
		
		str.append("Click here to learn more and Like us on Facebook!\n\n");
		str.setSpan(new URLSpan("http://www.facebook.com/pages/OnTime-Birthday-Post/227886563949063"),oldLen,oldLen+11,0);
		oldLen = str.length();
		
		str.append("Privacy:\n\n");
		str.setSpan(new StyleSpan(Typeface.BOLD),oldLen,str.length(),0);
		str.setSpan(new RelativeSizeSpan(1.2f),oldLen,str.length(),0);
		oldLen = str.length();
		str.append("OnTime Birthday Post does not intrude on your privacy.\n");
		str.setSpan(new RelativeSizeSpan(1.1f),oldLen,oldLen+1,0);
		oldLen = str.length();
		str.append("This app reads friends' names, birthdays and locations from your Facebook account.\n");
		str.setSpan(new RelativeSizeSpan(1.1f),oldLen,oldLen+1,0);
		oldLen = str.length();
		str.append("This data is stored only on your phone and NEVER shared with anyone else!\n\n");
		str.setSpan(new RelativeSizeSpan(1.1f),oldLen,oldLen+1,0);
		
		str.append("For more info on how OnTime Birthday Post works, check the 'About' page.");
		
		t.setMovementMethod(LinkMovementMethod.getInstance());
		t.setText(str, BufferType.SPANNABLE);
	}
}
