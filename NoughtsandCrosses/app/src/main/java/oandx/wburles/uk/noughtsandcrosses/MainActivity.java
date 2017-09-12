package oandx.wburles.uk.noughtsandcrosses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private int[] board = new int[9];
    private ImageAdapter boardAdapter;
    private GridView boardGrid;

    private int playerNo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boardGrid = (GridView) findViewById(R.id.board_grid);
        boardAdapter = new ImageAdapter(this,board);
        boardGrid.setAdapter(boardAdapter);


        AdapterView.OnItemClickListener boardListener = new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(board[position]==0) {
                    board[position] = playerNo;
                    if (playerNo == 1) {
                        ;
                        playerNo = 2;
                    } else {
                        playerNo = 1;
                    }
                    boardAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(),"Scace occupado",Toast.LENGTH_LONG).show();
                }
                if(hasWon(1)||hasWon(2)||allCellsTaken()){
                    displayDialog();
                }
            }
        };

        boardGrid.setOnItemClickListener(boardListener);

    }

    private boolean hasWon(int playerNo){
        boolean foundSoFar=false;
        for(int row=0; row < 3; row++){
            if(getCell(row,0)==playerNo && getCell(row,1)==playerNo && getCell(row,2)==playerNo) {
                foundSoFar = true;
            }
        }
        for(int col=0; col < 3; col++){
            if(getCell(0,col)==playerNo && getCell(1,col)==playerNo && getCell(2,col)==playerNo) {
                foundSoFar = true;
            }
        }
        if(getCell(0,0)==playerNo && getCell(1,1)==playerNo && getCell(2,2)==playerNo){
            foundSoFar=true;
        }
        if(getCell(0,2)==playerNo && getCell(1,1)==playerNo && getCell(2,0)==playerNo){
            foundSoFar=true;
        }
        return foundSoFar;
    }

    private boolean allCellsTaken(){
        boolean anyCellsFreeYet = false;
        for(int i=0; i<board.length;i++){
            anyCellsFreeYet|=board[i]==0;
        }
        return !anyCellsFreeYet;
    }

    private void displayDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Game Over");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setNegativeButton("New Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < board.length; i++) {
                    board[i] = 0;
                }
                boardAdapter.notifyDataSetChanged();
                playerNo = 1;
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private int getCell(int row, int column){
        return board[row+column*3];
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
