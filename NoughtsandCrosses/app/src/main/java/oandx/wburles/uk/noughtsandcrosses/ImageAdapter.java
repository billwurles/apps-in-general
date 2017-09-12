package oandx.wburles.uk.noughtsandcrosses;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by 14038690 on 22/02/2016.
 */
public class ImageAdapter extends android.widget.BaseAdapter {
    private Context context;
    int[] board;

    public ImageAdapter(Context c, int[] board){
        context = c;
        this.board = board;
    }

    @Override
    public int getCount() {
        return board.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(100,100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
        } else {
            imageView = (ImageView) convertView;
        }
        int[] imageResources= new int[]{R.drawable.blank, R.drawable.cross, R.drawable.nought};
        imageView.setImageResource(imageResources[board[position]]);
        return imageView;
    }
}
