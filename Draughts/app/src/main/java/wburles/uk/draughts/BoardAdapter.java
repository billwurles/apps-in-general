package wburles.uk.draughts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class BoardAdapter extends BaseAdapter {
    private Context context;
    GamePiece[] board;
    final GamePiece EMPTY_CELL;
    boolean boardType;

    public BoardAdapter(Context c, GamePiece[] board, GamePiece empty, boolean boardType){
        context = c;
        this.board = board;
        this.EMPTY_CELL=empty;
        this.boardType = boardType;
    }

    @Override
    public int getCount() {
        return board.length;
    }

    @Override
    public Object getItem(int position) {
        return board[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View view;

        if (convertView == null) { // Load the cell layout
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.board_cell, null);
        } else {
            view = convertView;
        }
        // Background
        ImageView cellView = (ImageView) view.findViewById(R.id.image_cell_background);

        if(boardType){
            if(position < 8 | (16 <= position && position <= 23) |
                    (32 <= position && position <= 39) | (48 <= position && position <= 55)){
                if(position%2!=0){  cellView.setImageResource(R.drawable.cell_black);
                } else {            cellView.setImageResource(R.drawable.cell_white);
                }} else{
                if(position%2!=0){  cellView.setImageResource(R.drawable.cell_white);
                } else {            cellView.setImageResource(R.drawable.cell_black);
                }   }
        } else{
            cellView.setImageResource(R.drawable.cell_white);
        }


        if(board[position] != EMPTY_CELL){ // Pieces
            ImageView pieceView = (ImageView) view.findViewById(R.id.image_piece);
            if(board[position].getTeam()==1){
                if(board[position].isKing()){
                    pieceView.setImageResource(R.drawable.piece_black_king);
                } else { pieceView.setImageResource(R.drawable.piece_black); }
            }
            else if(board[position].getTeam()==2){
                if(board[position].isKing()){
                    pieceView.setImageResource(R.drawable.piece_white_king);
                } else { pieceView.setImageResource(R.drawable.piece_white); }
            }
            if(board[position].isSelected()){
                cellView.setImageResource(R.drawable.cell_selected);
            } else if(board[position].isPossibility()){
                cellView.setImageResource(R.drawable.cell_possible);
            }
        } else { // deletes moved pieces
            ImageView pieceView = (ImageView) view.findViewById(R.id.image_piece);
            pieceView.setImageResource(0);
        }
        return view;
    }
}
