/*
 * Copyright 2011-2012 the original author or authors.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package piuk.blockchain.android.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import piuk.blockchain.android.R;

/**
 * @author Andreas Schildbach
 */
public class QrDialog extends Dialog {
	public QrDialog(final Context context, final Bitmap qrCodeBitmap) {
		super(context);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.qr_dialog);
		final ImageView imageView = (ImageView) findViewById(R.id.qr_dialog_image);
		imageView.setImageBitmap(qrCodeBitmap);
		setCanceledOnTouchOutside(true);

		imageView.setOnClickListener(new View.OnClickListener() {
			public void onClick(final View v) {
				dismiss();
			}
		});
	}
}