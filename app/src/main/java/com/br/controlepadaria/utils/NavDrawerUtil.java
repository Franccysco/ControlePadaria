package com.br.controlepadaria.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.controlepadaria.R;

/**
 * Created by ricardo on 08/08/15.
 */
public class NavDrawerUtil {

    public static void setHeaderValues(View navDrawerView, int listViewContainerId, int imgNavDrawerHeaderId, int imgUserUserPhotoId, int stringNavUserName, int stringNavUserEmail) {

        View view = navDrawerView.findViewById(listViewContainerId);
        if(view == null) {
            return;
        }

        view.setVisibility(View.VISIBLE);

        ImageView imgUserBackground =  view.findViewById(R.id.imgUserBackground);

        if (imgUserBackground == null) {
            return;
        }

        imgUserBackground.setImageResource(imgNavDrawerHeaderId);

        TextView tUserName =  view.findViewById(R.id.tUserName);
        TextView tUserEmail =  view.findViewById(R.id.tUserEmail);

        ImageView imgUserPhoto =  view.findViewById(R.id.imgUserPhoto);
        if (imgUserPhoto != null) {
            imgUserPhoto.setImageResource(imgUserUserPhotoId);
        }

        if (tUserName != null && tUserEmail != null) {
            tUserName.setText(stringNavUserName);
            tUserEmail.setText(stringNavUserEmail);
        }
    }
}
